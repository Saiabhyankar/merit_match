from flask import Flask,request,jsonify
import pymysql


#CREATING AN APP INSTANCE

app=Flask(__name__)

db_config ={
    'host' : 'localhost',
    'user' : 'root',
    'password':'saiabhi123',
    'database':'meritmatch'
}

db_conn=pymysql.connect(**db_config)

cursor=db_conn.cursor()
@app.route('/')
def run():
    return "Welcome To Backend Server"
@app.route('/insert' , methods=['POST'])
def insertIntoDataBase():
    try:
        data=request.get_json()
        username=data.get('username')
        password=data.get('password')
        cursor.execute('insert into login (username , password) values (%s , %s)',(username,password))
        db_conn.commit()
        return jsonify({'value':'added'})
    except Exception as e:
        return jsonify({'value':str(e)})

if __name__ == '__main__':
    app.run(debug=True,port=8080)