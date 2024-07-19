from flask import request,jsonify
from config import app,db
from models import Login

#CREATE USER 
#USERNAME AND PASSWORD
@app.route("/")
def home():
    return "Hello"

@app.route("/contacts",methods=["GET"])
def get_users():
    user=Login.query.all()
    json_user=list(map(lambda x: x.to_json(),user))
    return jsonify({"users":json_user})
@app.route("/create",methods=['POST'])
def create_contact():
    username=request.json.get("userName")
    password=request.json.get("passWord")
    if not username or not password:
        return (jsonify({"message":"must include an username and password"}),400,)
    new_user = Login(username=username,password=password)
    try:
        db.session.add(new_user)
        db.session.commit()
    except Exception as e:
            return jsonify({"message":str(e)}),400
    return jsonify({"message":"User created!"}),201



if __name__=="__main__":
    with app.app_context():
        db.create_all()
    app.run(debug=True)

