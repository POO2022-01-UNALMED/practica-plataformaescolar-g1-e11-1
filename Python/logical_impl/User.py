class User:
    def __init__(self, name, id, birthplace, bloodtype, birthday, sex):
        self.name = name
        self.id = id
        self.birthplace = birthplace
        self.birthday = birthday
        self.sex = sex
        self.bloodtype = bloodtype

    def get_name(self):
        return self.name

    def get_id(self):
        return self.id

    def get_birthplace(self):
        return self.birthplace

    def get_birthday(self):
        return self.birthday

    def get_sex(self):
        return self.sex

    def get_bloodtype(self):
        return self.bloodtype

    def check_perf(self): # Este metodo sera sobreescrito en las clases Student y Teacher
        pass
    def kick(self): # Este metodo sera sobreescrito en las clases Student y Teacher
        pass
