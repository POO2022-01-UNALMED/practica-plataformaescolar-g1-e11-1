import random


class Course:
    OFFERED_COURSES = ["Physics", "English", "Math", "Biology", "Spanish", "History", "Comp. Science", "P. Education",
                       "Chemistry", "Philosophy"]

    SELECTED = None

    def __init__(self, cname):
        self.course_name = cname
        self.assigned_teachers = []
        self.enrolled_students = []
        self.course_subjects = []

        # Elegir materias al azar para el curso
        chosen_already = []
        while len(self.course_subjects) != 8:
            idx = random.randint(0, 9)
            from logical_impl.Subject import Subject
            cs = Subject(Course.OFFERED_COURSES[idx], None, self)
            if cs.getName() in chosen_already:
                pass
            else:
                self.course_subjects.append(cs)
                chosen_already.append(cs.getName())

        from logical_impl.Timetable import Timetable
        self.timetable = Timetable(self)
        from database_impl.SchoolData import GLOBAL_SCHOOL_DATA
        GLOBAL_SCHOOL_DATA.CREATED_COURSES.append(self)

    def add_student(self, std):
        if std.isEnrolled == False:
            std.setCourse(self)
            self.enrolled_students.append(std)

    def getName(self):
        return self.course_name

    def getCourseSubject(self):
        return self.course_subjects

    def add_prof(self, teacher, sname):
        if teacher in self.assigned_teachers:
            return
        for csbj in self.course_subjects:
            if csbj.getName() == sname:
                if csbj.assigned_teacher is None:
                    csbj.assigned_teacher = teacher
                    self.updatesbjs(teacher, sname)
                    teacher.assigned_course.append(self)
                    teacher.assigned_subjects.append(csbj)
                else:
                    csbj.assigned_teacher.removeSubject(self.course_name, sname)
                    csbj.assigned_teacher = teacher
                    teacher.assigned_course.append(self)
                    teacher.assigned_subjects.append(csbj)
                    self.updatesbjs(teacher, sname)

    def updatesbjs(self, t, sn):
        for std in self.enrolled_students:
            for ssbj in std.getAssignedSubjects():
                if ssbj.name == sn:
                    ssbj.assigned_teacher = t

    def getNoOfStudents(self):
        return len(self.enrolled_students)

    def getTimetable(self):
        return self.timetable

    def set_selected(self):
        Course.SELECTED = self

    # Obtener para todas las materias de un curso ciertos datos (promedio, profesor y nombre)
    def getStats(self):  # en lista[0] el nombre en [1] el promedio y en [2] quien la dicta
        rtn = []
        for sbj in self.course_subjects:
            lista = []
            lista.append("Nombre de la materia: " + sbj.getName())
            avg = 0
            for student in self.enrolled_students:
                for sbjs in student.getAssignedSubjects():
                    if sbj.getName() == sbjs.getName():
                        avg += sbjs.calculateAvg()
            avg = avg / (len(self.enrolled_students) if len(self.enrolled_students) > 0 else 1)
            lista.append("Promedio del curso: " + '{:.2f}'.format(avg))
            lista.append("Dictada por: " + sbj.getTeacher().get_name() if sbj.getTeacher() is not None else "NA")
            rtn.append(lista)
        return rtn
