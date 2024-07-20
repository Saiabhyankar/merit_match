from flask import Flask

from flask_sqlalchemy import SQLAlchemy

from flask_cors import CORS

from flask_bcrypt import Bcrypt 

app= Flask(__name__)

CORS(app)

bcrypt=Bcrypt(app)

app.config["SQLALCHEMY_DATABASE_URI"]="mysql+pymysql://root:saiabhi123@localhost:3306/meritmatch"
app.config["SQLALCHEMY_TRACK_MODIFICATIONS"]=False

db=SQLAlchemy(app)

