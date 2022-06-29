from logical_impl.User import User


class Teacher(User):
    SELECTED = None
    def __init__(self, name, id, birthday, sex) -> None:
        super().__init__(name, id, birthday, sex)
        self.assigned_course = []
        self.assigned_subjects = []
        self.academic_history = None
        self.isEnrolled = False

        from database_impl.SchoolData import GLOBAL_SCHOOL_DATA
        GLOBAL_SCHOOL_DATA.CREATED_TEACHERS.append(self)

    def set_selected(self):
        Teacher.SELECTED = self

    def getCourseNames(self):
        strs = ""
        for c in self.assigned_course:
            strs += c.getName() + " "
        return strs

    def removeSubject(self, cname, sname):
        cpy = self.assigned_subjects.copy()
        for sbj in cpy:
            if sbj.name == sname and sbj.course == cname:
                self.assigned_subjects.remove(sbj)

    def getCourse(self):
        return self.assigned_course