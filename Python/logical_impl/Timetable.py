from logical_impl.Day import Day
import random

class Timetable:
    def __init__(self, course):
        self.schedule = {Day.MONDAY: [], Day.TUESDAY: [], Day.WEDNESDAY: [], Day.THURSDAY: [], Day.FRIDAY: []} # Crear el diccionario de materias.
        sbjs = course.course_subjects
        for val in self.schedule.values():
            while len(val) != 6:
                idx = random.randint(0, 7)
                if val.count(sbjs[idx]) == 1: # Si ya hay la materia en el horario, agregar la nueva instancia al lado, para uqe forme un bloque de 2 horas.
                    val.insert(val.index(sbjs[idx]), sbjs[idx])
                elif val.count(sbjs[idx]) == 0: # Si no, simplemente agregarla.
                    val.append(sbjs[idx])

    def toPrintList(self):
        return self.schedule
