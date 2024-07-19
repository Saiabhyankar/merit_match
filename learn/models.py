from config import db

class Login(db.Model):
    id=db.Column(db.Integer,primary_key=True)
    username=db.Column(db.String(255),unique=True,nullable=False)
    password=db.Column(db.String(100),nullable=False)

    def to_json(self):
        return {
            "id":self.id,
            "userName":self.username,
            "passWord":self.password
        }