class Subject:
    def __init__(self, sname, teacher, course):
        self.name = sname
        self.assigned_teacher = teacher
        self.course = course
        self.student = None
        self.taken_tests = []

    def getTests(self):
        return self.taken_tests

    def getName(self):
        return self.name

    def addEvaluation(self, ev):
        if ev is not None:
            self.taken_tests.append(ev)

    def calculateAvg(self):
        sums = 0
        for ev in self.taken_tests:
            sums += ev.getGrade()
        return sums/3

    def setStudent(self, std):
        self.student = std

    def getTeacher(self):
        return self.assigned_teacher

