from logical_impl.EvaluationEvent import EvaluationEvent
from logical_impl.Subject import Subject
from logical_impl.User import User
import random



class Student(User):
    SELECTED = None

    def __init__(self, name, id, birthday, sex) -> None:
        super().__init__(name, id, birthday, sex)
        self.assigned_course = None
        self.assigned_subjects = []
        self.academic_history = None
        self.isEnrolled = False

        from database_impl.SchoolData import GLOBAL_SCHOOL_DATA
        GLOBAL_SCHOOL_DATA.CREATED_STUDENTS.append(self)

    def inscribeSubjects(self, course):
        for csbj in course.getCourseSubject():
            stdsbj = Subject(csbj.getName(), csbj.getTeacher(), course)
            for i in range(3):
                ev = EvaluationEvent(random.random()*5, "a", course.getName())
                stdsbj.getTests().append(ev)
            self.assigned_subjects.append(stdsbj)

    def setCourse(self, course):
        if course is not None:
            self.assigned_course = course
            self.isEnrolled = True

            self.inscribeSubjects(course)

        else:
            self.assigned_course = course
            self.isEnrolled = False

    def getAssignedSubjects(self):
        return self.assigned_subjects

    def getCourse(self):
        return self.assigned_course

    def set_selected(self):
        Student.SELECTED = self

    def getStats(self):  # [0] nombre de la materia [1] ev1 [2] ev2, [3], ev3 por ultimo el promedio de los 3 examenes
        stats = []
        for sbj in self.assigned_subjects:
            lista = []
            lista.append("Materia: " + sbj.getName())
            no = 1
            avg = 0
            for ev in sbj.getTests():
                lista.append("Evaluation N" + str(no) + " " + '{:.2f}'.format(ev.getGrade()))
                avg += ev.getGrade()
                no += 1
            lista.append("Promedio examenes: " + "{:.2f}".format(avg / 3))
            stats.append(lista)

        return stats
