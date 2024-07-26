if bcrypt.check_password_hash(result[0],password=password):
        return jsonify({"bool": "true"})    
    else:
        return jsonify({"bool": "false"})