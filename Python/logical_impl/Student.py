from User import User

class Student(User):
    def __init__(self, name, id, birthplace, birthday, sex, bloodtype) -> None:
        super().__init__(name, id, birthplace, birthday, sex, bloodtype)
        self.assigned_course = None
        self.assigned_subjects = []
        self.academic_history = None
        self.isEnrolled = False

        from interface_impl.main import GLOBAL_SCHOOL_DATA
        GLOBAL_SCHOOL_DATA.CREATED_STUDENTS.append(self)

    def setCourse(self, course):
        if course is not None:
            self.assigned_course = course
            self.isEnrolled = True

        else:
            self.assigned_course = course
            self.isEnrolled = False

    

