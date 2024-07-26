from flask import request,jsonify
from config import app,db
from models import Login,Transaction
from sqlalchemy import text
from config import bcrypt


#CREATE USER 
#USERNAME AND PASSWORD
@app.route("/")
def home():
    return "Hello"
@app.route("/create",methods=['POST'])
def create_contact():
    username=request.json.get("userName")
    password=request.json.get("passWord")
    hashed_password = bcrypt.generate_password_hash(password).decode('utf-8') 
    if not username or not password:
        return (jsonify({"message":"must include an username and password"}),400,)
    new_user = Login(username=username,password=hashed_password,karmaPoint=100)
    try:
        db.session.add(new_user)
        db.session.commit()
    except Exception as e:
            return jsonify({"message":str(e)}),400
    return jsonify({"message":"User created!"}),201
@app.route("/check",methods=['GET'])
def check_if_user_aleready_exists():
     name=request.args.get("username")
     query=text("Select username from login")
     result=db.session.execute(query).fetchall()
     names=[]
     for rows in result:
          names.append(rows[0])
     if name in names:
          return jsonify({"message":"false"})
     else:
          return jsonify({"message":name})
          
@app.route("/get",methods=['GET'])
def return_user():
        _name = request.args.get("username")
        name=str(Login.query.get(_name))[6:-1].strip()
        if name!="":
            return jsonify({"getRequest":"found"})
        else:
            return jsonify({"getRequest":"not found"})
@app.route("/password" , methods=['POST'])
def password_check():
    username = request.json.get("userName")
    password = request.json.get("passWord")
    
    if not username or not password:
        return jsonify({"error": "Username and password are required"}), 400

    query = text("SELECT password FROM login WHERE username = :username")
    result = db.session.execute(query, {'username': username}).fetchone()


    if bcrypt.check_password_hash(result[0],password):
        return jsonify({"bool": "true"})    
    else:
        return jsonify({"bool": "false"})

@app.route("/karmaPoints", methods=['GET'])
def karma_points():
    _name = request.args.get("username")
    
    if _name:
      
        query = text("SELECT karmaPoint FROM login WHERE username = :username")
        
        
        result = db.session.execute(query, {'username': _name}).fetchone()
        

        if result:
            karma_points = result[0]  
            return jsonify({"karmaPoints": karma_points})
        else:
            return jsonify({"karmaPoints": 0}), 404
    else:
        return jsonify({"karmaPoints":10}), 400
@app.route("/task",methods=['POST'])
def post_Task():
    userName=request.json.get("username")
    task=request.json.get("task")
    karmaPoints=request.json.get("karmapoints")
    taskDescription=request.json.get("taskDescription")
    new_task = Transaction(
                    username=userName,
                    task=task,
                    taskDescription=taskDescription,
                    karmaPoint=karmaPoints,
                    reserve="False",
                    accepted="False"
                    )               
    try:
        db.session.add(new_task)
        db.session.commit()
    except Exception as e:
            return jsonify({"message":str(e)}),400
    return jsonify({"message":"User created!"}),201
@app.route("/fetch", methods=['GET'])
def get_allTask():
    username = request.args.get("username")
    query = text("SELECT id ,username, task, karmaPoint ,reserve FROM transaction")
    result = db.session.execute(query)
    values = result.fetchall()
    tasksAvailable = []
    for rows in values:
        if (rows[1]!=username and rows[4]=="False"):
            task = {
                 "id":rows[0],
                "username": rows[1],
                "tasks": rows[2],
                "karmapoints": rows[3]
            }
            tasksAvailable.append(task)  
    return jsonify({"tasks": tasksAvailable}), 200

@app.route("/reserve",methods=['POST'])
def reserve_task():
    try:
        id = request.json.get("id")
        name=request.json.get("username")
        if not id:
            return jsonify({"error": "id not provided"}), 400
        query = text("UPDATE transaction SET RESERVE = :RESERVE , postedBy=:postedBy where id=:id")
        params={'RESERVE':'Updated','id':id,'postedBy':name}
        result = db.session.execute(query, params)
        if result.rowcount == 0:
            return jsonify({"update": "No rows updated. Check if the username exists"}), 404
        db.session.commit()
        return jsonify({"update": id})
    except Exception as e:
        print(f"Error: {e}")
        return jsonify({"update": "An error occurred while updating the transaction"}), 500

@app.route("/accept",methods=['GET'])
def accept_task():
    username=request.args.get("name")
    if not username:
        return jsonify({"error": username}), 400
    query1=text("SELECT id ,username, task, karmaPoint,reserve,postedBy,accepted FROM transaction")
    result1=db.session.execute(query1)
    values1=result1.fetchall()
    tasks=[]
    for rows in values1:
            if(rows[1]==username and rows[4]=="Updated" and rows[6]=='False'):
                task = {    
                    "id":rows[0],
                    "username": rows[5],
                    "tasks": rows[2],
                    "karmapoints": rows[3],               
                }
                tasks.append(task)
    return jsonify({"tasks": tasks}), 200


@app.route("/transaction",methods=['POST'])
def transaction():
    userName1=request.json.get("userName1")
    userName2=request.json.get("userName2")
    karmaPoints=request.json.get("karmaPoint")
    if not userName1:
            return jsonify({"update": "Username not provided"}), 400
    query1 = text("UPDATE login SET karmaPoint = karmaPoint + :karmaPoints WHERE username = :username")
    result1 = db.session.execute(query1, {'karmaPoints':karmaPoints,'username': userName2})
    query2 = text("UPDATE login SET karmaPoint = karmaPoint - :karmaPoints WHERE username = :username")
    
    result2 = db.session.execute(query2, {'karmaPoints':karmaPoints,'username': userName1})
    if result1.rowcount == 0 or result2.rowcount==0:
        return jsonify({"update": "No rows updated. Check if the username exists"}), 404
    db.session.commit()
    return jsonify({"update": userName2})
@app.route("/transactionPossible",methods=['POST'])
def transaction_Is_Possible():
     username=request.json.get('username')
     karmapoint=request.json.get('karmapoints')
     query=text("Select karmaPoint from login where username=:username")
     result=db.session.execute(query,{'username':username}).fetchone()
     if(result[0]>karmapoint):
          return jsonify({"result":"possible"})
     else:
          return jsonify({"result":"not possible"})
@app.route("/accepted",methods=['POST'])
def accepted():
     id=request.json.get('id')
     query=text("Update transaction set accepted = :value where id=:id")
     params={"value":"true","id":id}
     db.session.execute(query,params)
     db.session.commit()
     return jsonify({"result":"success"})
if __name__=="__main__":
    with app.app_context():
        db.create_all()
    app.run(debug=True)

