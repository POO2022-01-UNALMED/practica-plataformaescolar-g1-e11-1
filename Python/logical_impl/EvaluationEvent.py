class EvaluationEvent:
    def __init__(self, grade, name, sn):
        self.grade = grade
        self.evname = name
        self.subject_name = sn

    def getGrade(self):
        return self.grade

    def setGrade(self, g):
        self.grade = g

    def getEvname(self):
        return self.evname

    def setEvname(self, g):
        self.evname = g

    def getSubject_name(self):
        return self.subject_name

    def setSubject_name(self, g):
        self.subject_name = g

