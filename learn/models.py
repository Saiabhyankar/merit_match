from config import db

class Login(db.Model):
    username=db.Column(db.String(255),unique=True,nullable=False,primary_key=True)
    password=db.Column(db.String(5000),nullable=False)
    karmaPoint=db.Column(db.Integer,nullable=False)

    def to_json(self):
        return {
            "id":self.id,
            "userName":self.username,
            "passWord":self.password,
            "karmaPoints":self.karmaPoint
        }
class Transaction(db.Model):
    id=db.Column(db.Integer,primary_key=True,autoincrement=True)
    username=db.Column(db.String(255),nullable=False)
    task=db.Column(db.String(255),nullable=False)
    taskDescription=db.Column(db.String(1000),nullable=False)
    karmaPoint=db.Column(db.Integer,nullable=False)
    reserve=db.Column(db.String(20))
    postedBy=db.Column(db.String(20))
    accepted=db.Column(db.String(20))

    def to_json(self):
        return {
            "id":self.id,
            "userName":self.username,
            "karmaPoints":self.karmaPoint,
            "task":self.task,
            "taskDescription":self.taskDescription,
            "reserve":self.reserve,
            "postedBy":self.postedBy,
            "accepted":self.accepted
        }