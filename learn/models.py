from config import db

class Login(db.Model):
    username=db.Column(db.String(255),unique=True,nullable=False,primary_key=True)
    password=db.Column(db.String(500),nullable=False)
    karmaPoint=db.Column(db.Integer,nullable=False)

    def to_json(self):
        return {
            "id":self.id,
            "userName":self.username,
            "passWord":self.password,
            "karmaPoints":self.karmaPoint
        }
class transaction(db.Model):
    id=db.Column(db.Integer,primary_key=True,autoincrement=True)
    username=db.Column(db.String(255),unique=True,nullable=False)
    task=db.Column(db.String(255),nullable=False)
    taskDescription=db.Column(db.String(1000),nullable=False)
    karmaPoint=db.Column(db.Integer,nullable=False)
    reserve=db.Column(db.String(10),default="false")

    def to_json(self):
        return {
            "id":self.id,
            "userName":self.username,
            "karmaPoints":self.karmaPoint,
            "task":self.task,
            "taskDescription":self.taskDescription,
            "reserve":self.reserve
        }