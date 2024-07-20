from flask import request,jsonify
from config import app,db
from models import Login,transaction
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
    hashed_password = bcrypt.generate_password_hash('password').decode('utf-8') 
    if not username or not password:
        return (jsonify({"message":"must include an username and password"}),400,)
    new_user = Login(username=username,password=hashed_password,karmaPoint=100)
    try:
        db.session.add(new_user)
        db.session.commit()
    except Exception as e:
            return jsonify({"message":str(e)}),400
    return jsonify({"message":"User created!"}),201

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
    hashed_password = bcrypt.generate_password_hash(password).decode('utf-8') 
    if not username or not password:
        return jsonify({"error": "Username and password are required"}), 400

    query = text("SELECT password FROM login WHERE username = :username")
    result = db.session.execute(query, {'username': username}).fetchone()


    if bcrypt.check_password_hash(result[0],password=password):
        return jsonify({"bool": "true"})    
    else:
        return jsonify({"bool": "false"})

@app.route("/karmaPoints", methods=['GET'])
def karma_points():
    _name = request.args.get("username")
    
    if _name:
        # Prepare the SQL query to fetch karma points
        query = text("SELECT karmaPoint FROM login WHERE username = :username")
        
        # Execute the query and fetch the result
        result = db.session.execute(query, {'username': _name}).fetchone()
        
        # Check if result is found
        if result:
            karma_points = result[0]  # Assuming karmaPoint is the first column
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
    new_task=transaction(username=userName,task=task,karmaPoint=karmaPoints,reserve="False")
    try:
        db.session.add(new_task)
        db.session.commit()
    except Exception as e:
            return jsonify({"message":str(e)}),400
    return jsonify({"message":"User created!"}),201
@app.route("/fetch", methods=['GET'])
def get_allTask():
    username = request.args.get("username")
    query = text("SELECT username, task, karmaPoint FROM transaction")
    result = db.session.execute(query)
    values = result.fetchall()
    tasks = []
    for rows in values:
        if rows[0]!=username:
            task = {
                "username": rows[0],
                "tasks": rows[1],
                "karmapoints": rows[2]
            }
            tasks.append(task)
    return jsonify({"tasks": tasks}), 200

@app.route("/reserve",methods=['POST'])
def reserve_task():
    try:
        userName = request.json.get("username")
        if not userName:
            return jsonify({"error": "Username not provided"}), 400
        query = text("UPDATE transaction SET RESERVE = TRUE WHERE username = :username")
        result = db.session.execute(query, {'username': userName})
        if result.rowcount == 0:
            return jsonify({"update": "No rows updated. Check if the username exists"}), 404
        db.session.commit()
        return jsonify({"update": userName})
    except Exception as e:
        print(f"Error: {e}")
        return jsonify({"update": "An error occurred while updating the transaction"}), 500

@app.route("/transaction",methods=['POST'])
def transaction():
    userName=request.json.get("userName")
    karmaPoints=request.json.get("karmaPoint")
    if not userName:
            return jsonify({"error": "Username not provided"}), 400
    query1 = text("UPDATE transaction SET karmaPoint = karmaPoint + :karmaPoints WHERE username = :username")
    result1 = db.session.execute(query1, {'karmaPoints':karmaPoints,'username': userName})

    query2 = text("UPDATE login SET karmaPoint = karmaPoint - :karmaPoints WHERE username = :username")
    result2 = db.session.execute(query2, {'karmaPoints':karmaPoints,'username': userName})
    if result1.rowcount == 0 or result2.rowcount==0:
        return jsonify({"update": "No rows updated. Check if the username exists"}), 404
    db.session.commit()
    return jsonify({"update": userName})


if __name__=="__main__":
    with app.app_context():
        db.create_all()
    app.run(debug=True)

