import tkinter as tk
from tkinter import ttk
from tkinter.font import Font

from PIL import ImageTk, Image

from database_impl.Serializer import Serializer
from database_impl.SchoolData import SchoolData, GLOBAL_SCHOOL_DATA

# Crear la ventana principal del programa.
from logical_impl.Course import Course
from logical_impl.Student import Student
from logical_impl.Teacher import Teacher

WND = tk.Tk()
WND_HEIGHT = 800
WND_WIDTH = 1000

WND_DIM = str(WND_WIDTH) + "x" + str(WND_HEIGHT)

WND.title("CMA")  # Nombre de la ventana
WND.geometry(WND_DIM)  # Dimensiones de la ventana

cv1 = (Image.open("hoja.jpg")).resize((WND_WIDTH // 2 - 40, WND_HEIGHT // 2 + 45), Image.ANTIALIAS)
me = (Image.open("IMG_20210722_134115.jpg")).resize(((WND_WIDTH // 2 - 28) // 2, (WND_HEIGHT // 4 + 45) // 2),
                                                    Image.ANTIALIAS)

schimg1 = (Image.open("schoolimg1.png").resize((WND_WIDTH // 2 + 10, (WND_HEIGHT - 60) // 3), Image.ANTIALIAS))
schimg2 = (Image.open("schoolimg2.png").resize((WND_WIDTH // 2 + 10, (WND_HEIGHT - 60) // 3), Image.ANTIALIAS))
schimg3 = (Image.open("schoolimg3.png").resize((WND_WIDTH // 2 + 10, (WND_HEIGHT - 55) // 3), Image.ANTIALIAS))

schimg1i = ImageTk.PhotoImage(schimg1)
schimg2i = ImageTk.PhotoImage(schimg2)
schimg3i = ImageTk.PhotoImage(schimg3)

cvli = ImageTk.PhotoImage(cv1)
mei = ImageTk.PhotoImage(me)

p1 = tk.Frame(WND, highlightbackground="black", highlightthickness=4, width=WND_WIDTH / 2 - 60,
              height=WND_HEIGHT - 40)  # Superframe P1
p2 = tk.Frame(WND, highlightbackground="black", highlightthickness=4, width=WND_WIDTH / 2 + 10,
              height=WND_HEIGHT - 40)  # Superframe P2

WND.update()


def exit_program():
    Serializer.serialize_all()
    quit()


def show_desc():
    pass


ini = tk.Menu(WND)
iniin = tk.Menu(ini, tearoff=0)
ini.add_cascade(label="Inicio", menu=iniin)
iniin.add_command(label="Salir", command=exit_program)
iniin.add_command(label="Descripcion", command=show_desc)
WND.config(menu=ini)
sel_course = None


def refresh_wnd():
    for widget in p1.winfo_children():
        widget.destroy()
    for widget in p2.winfo_children():
        widget.destroy()

def new_student_form():
    refresh_wnd()
    nextm = tk.Button(p1, text="CANCELAR", height=2, width=20, command=add_student)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.85)

    noml = tk.Label(p2, text="NOMBRE DEL ESTUDIANTE")
    nom = tk.Entry(p2, width=20)

    noml.place(relx = 0.1, rely=0.1)
    nom.place(relx=0.1, rely = 0.15)

    idl = tk.Label(p2, text="IDENTIFICACION")
    id = tk.Entry(p2)

    idl.place(relx=0.1, rely=0.2)
    id.place(relx=0.1, rely=0.25)

    datel = tk.Label(p2, text="FECHA DE NACIMIENTO")
    date = tk.Entry(p2)

    datel.place(relx=0.1, rely=0.3)
    date.place(relx=0.1, rely=0.35)

    sl = tk.Label(p2, text="SEXO")
    se = tk.Entry(p2)

    sl.place(relx=0.1, rely=0.4)
    se.place(relx=0.1, rely=0.45)

    cnl = tk.Label(p2, text="NOMBRE DEL CURSO A SER INSCRITO")
    cn = tk.Entry(p2)

    cnl.place(relx=0.1, rely=0.5)
    cn.place(relx=0.1, rely=0.55)

    def add_new():
        nstd = Student(nom.get(), int(id.get()), date.get(), se.get())
        from database_impl.SchoolData import GLOBAL_SCHOOL_DATA
        for cour in GLOBAL_SCHOOL_DATA.CREATED_COURSES:
            if cour.getName() == cn.get():
                cour.add_student(nstd)



    nextm = tk.Button(p1, text="CREAR", height=3, width=20, command=add_new)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.05)


def add_existing():
    refresh_wnd()
    cnl = tk.Label(p2, text="NOMBRE DEL CURSO A SER INSCRITO")
    cn = tk.Entry(p2)

    cnl.place(relx=0.1, rely=0.05)
    cn.place(relx=0.1, rely=0.1)
    def add_existings():
        from database_impl.SchoolData import GLOBAL_SCHOOL_DATA
        for cour in GLOBAL_SCHOOL_DATA.CREATED_COURSES:
            if cour.getName() == cn.get():
                cour.add_student(Student.SELECTED)

    nextm = tk.Button(p1, text="AÑADIR", height=3, width=20, command=add_existings)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.05)

    nextm = tk.Button(p1, text="REGRESAR", height=3, width=20, command=handle_students)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.85)


def add_student():
    refresh_wnd()
    nextm = tk.Button(p1, text="REGRESAR", height=2, width=20, command=handle_students)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.85)

    nextm = tk.Button(p1, text="ESTUDIANTE EXISTENTE", height=3, width=20, command=add_existing)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.05)

    nextm = tk.Button(p1, text="ESTUDIANTE NUEVO", height=3, width=20, command=new_student_form)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.25)

    canvas = tk.Canvas(p2, width=WND_WIDTH / 2 + 10, height=p2.winfo_height())
    canvas.pack(side="left", fill="both", expand=0)

    scrol = ttk.Scrollbar(p2, orient="vertical", command=canvas.yview)
    scrol.pack(side="right", fill="both")
    canvas.bind('<Configure>', lambda e: canvas.configure(scrollregion=canvas.bbox("all")))

    second_frame = tk.Frame(canvas)
    canvas.create_window((0, 0), window=second_frame, anchor="nw")
    canvas.configure(yscrollcommand=scrol.set)

    from database_impl.SchoolData import GLOBAL_SCHOOL_DATA
    for student in GLOBAL_SCHOOL_DATA.CREATED_STUDENTS:
        cf = tk.Frame(second_frame, width=p2.winfo_width() - 10, highlightbackground="red", highlightthickness=4,
                      height=100)
        cn = tk.Label(cf, text=student.get_name().upper(), bd=0,
                      font=("Times New Roman", 15, "bold"), padx=0, height=2)
        cno = tk.Label(cf, text="Curso: " + student.getCourse().getName() if student.getCourse() is not None else "NA",
                       bd=0,
                       font=("Times New Roman", 12, "bold"), padx=0, height=2)
        cn.place(relx=0.05, rely=0.15)
        cno.place(relx=0.05, rely=0.5)

        bsels = tk.Button(cf, text="SELECT", height=2, width=10, command=student.set_selected)
        bsels['font'] = Font(family="Times New Roman", size=8, weight="bold")
        bsels.place(relx=0.8, rely=0.5)
        cf.pack(anchor="w", expand=0, fill="both")


def show_student_stats():
    s_stats = Student.SELECTED.getStats()
    refresh_wnd()
    marginy = 0.1
    for i in range(4):
        sbn = tk.Label(p2, text=s_stats[i][0])
        sbn.place(relx=0.1, rely=marginy)
        marginy += 0.03
        prom = tk.Label(p2, text=s_stats[i][1])
        prom.place(relx=0.1, rely=marginy)
        marginy += 0.03
        dicta = tk.Label(p2, text=s_stats[i][2])
        dicta.place(relx=0.1, rely=marginy)
        marginy += 0.03
        dicta = tk.Label(p2, text=s_stats[i][3])
        dicta.place(relx=0.1, rely=marginy)
        marginy += 0.03
        dicta = tk.Label(p2, text=s_stats[i][4])
        dicta.place(relx=0.1, rely=marginy)
        marginy += 0.1

    marginy = 0.1
    for i in range(4, 8):
        sbn = tk.Label(p2, text=s_stats[i][0])
        sbn.place(relx=0.6, rely=marginy)
        marginy += 0.03
        prom = tk.Label(p2, text=s_stats[i][1])
        prom.place(relx=0.6, rely=marginy)
        marginy += 0.03
        dicta = tk.Label(p2, text=s_stats[i][2])
        dicta.place(relx=0.6, rely=marginy)
        marginy += 0.03
        dicta = tk.Label(p2, text=s_stats[i][3])
        dicta.place(relx=0.6, rely=marginy)
        marginy += 0.03
        dicta = tk.Label(p2, text=s_stats[i][4])
        dicta.place(relx=0.6, rely=marginy)
        marginy += 0.1

    nextm = tk.Button(p1, text="REGRESAR", height=2, width=20, command=handle_students)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.85)


def handle_students():
    refresh_wnd()
    nextm = tk.Button(p1, text="REGRESAR", height=2, width=20, command=user_main_menu)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.85)

    nextm = tk.Button(p1, text="INSCRIBIR\nESTUDIANTE", height=3, width=20, command=add_student)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.05)

    nextm = tk.Button(p1, text="ESTADISTICAS\nESTUDIANTE", height=3, width=20, command=show_student_stats)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.25)

    canvas = tk.Canvas(p2, width=WND_WIDTH / 2 + 10, height=p2.winfo_height())
    canvas.pack(side="left", fill="both", expand=0)

    scrol = ttk.Scrollbar(p2, orient="vertical", command=canvas.yview)
    scrol.pack(side="right", fill="both")
    canvas.bind('<Configure>', lambda e: canvas.configure(scrollregion=canvas.bbox("all")))

    second_frame = tk.Frame(canvas)
    canvas.create_window((0, 0), window=second_frame, anchor="nw")
    canvas.configure(yscrollcommand=scrol.set)

    from database_impl.SchoolData import GLOBAL_SCHOOL_DATA
    for student in GLOBAL_SCHOOL_DATA.CREATED_STUDENTS:
        cf = tk.Frame(second_frame, width=p2.winfo_width() - 10, highlightbackground="red", highlightthickness=4,
                      height=100)
        cn = tk.Label(cf, text=student.get_name().upper(), bd=0,
                      font=("Times New Roman", 15, "bold"), padx=0, height=2)
        cno = tk.Label(cf, text="Curso: " + student.getCourse().getName() if student.getCourse() is not None else "NA",
                       bd=0,
                       font=("Times New Roman", 12, "bold"), padx=0, height=2)
        cn.place(relx=0.05, rely=0.15)
        cno.place(relx=0.05, rely=0.5)

        bsels = tk.Button(cf, text="SELECT", height=2, width=10, command=student.set_selected)
        bsels['font'] = Font(family="Times New Roman", size=8, weight="bold")
        bsels.place(relx=0.8, rely=0.5)
        cf.pack(anchor="w", expand=0, fill="both")

def add_existingp():
    refresh_wnd()
    cnl = tk.Label(p2, text="NOMBRE DEL CURSO A ASIGNAR")
    cn = tk.Entry(p2)

    cnl.place(relx=0.1, rely=0.05)
    cn.place(relx=0.1, rely=0.1)

    cml = tk.Label(p2, text="NOMBRE DE LA MATERIA A ASIGNAR")
    cm = tk.Entry(p2)

    cml.place(relx=0.1, rely=0.15)
    cm.place(relx=0.1, rely=0.2)

    def add_existingps():
        from database_impl.SchoolData import GLOBAL_SCHOOL_DATA
        for cour in GLOBAL_SCHOOL_DATA.CREATED_COURSES:
            if cour.getName() == cn.get():
                cour.add_prof(Teacher.SELECTED, cm.get())

    nextm = tk.Button(p1, text="AÑADIR", height=3, width=20, command=add_existingps)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.05)

    nextm = tk.Button(p1, text="REGRESAR", height=3, width=20, command=handle_professors)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.85)

def new_teacher_form():
    refresh_wnd()
    nextm = tk.Button(p1, text="CANCELAR", height=2, width=20, command=add_prof)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.85)

    noml = tk.Label(p2, text="NOMBRE DEL PROFESOR")
    nom = tk.Entry(p2, width=20)

    noml.place(relx=0.1, rely=0.1)
    nom.place(relx=0.1, rely=0.15)

    idl = tk.Label(p2, text="IDENTIFICACION")
    id = tk.Entry(p2)

    idl.place(relx=0.1, rely=0.2)
    id.place(relx=0.1, rely=0.25)

    datel = tk.Label(p2, text="FECHA DE NACIMIENTO")
    date = tk.Entry(p2)

    datel.place(relx=0.1, rely=0.3)
    date.place(relx=0.1, rely=0.35)

    sl = tk.Label(p2, text="SEXO")
    se = tk.Entry(p2)

    sl.place(relx=0.1, rely=0.4)
    se.place(relx=0.1, rely=0.45)

    cnl = tk.Label(p2, text="NOMBRE DEL CURSO A SER INSCRITO")
    cn = tk.Entry(p2)

    cnl.place(relx=0.1, rely=0.5)
    cn.place(relx=0.1, rely=0.55)

    cml = tk.Label(p2, text="NOMBRE DE LA MATERIA A SER ASIGNADO")
    cm = tk.Entry(p2)

    cml.place(relx=0.1, rely=0.6)
    cm.place(relx=0.1, rely=0.65)

    def add_new():
        nt = Teacher(nom.get(), int(id.get()), date.get(), se.get())
        from database_impl.SchoolData import GLOBAL_SCHOOL_DATA
        for cour in GLOBAL_SCHOOL_DATA.CREATED_COURSES:
            if cour.getName() == cn.get():
                cour.add_prof(nt, cm.get())

    nextm = tk.Button(p1, text="CREAR", height=3, width=20, command=add_new)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.05)

def add_prof():
    refresh_wnd()
    nextm = tk.Button(p1, text="REGRESAR", height=2, width=20, command=handle_professors)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.85)

    nextm = tk.Button(p1, text="PROFESOR EXISTENTE", height=3, width=20, command=add_existingp)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.05)

    nextm = tk.Button(p1, text="PROFESOR NUEVO", height=3, width=20, command=new_teacher_form)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.25)

    canvas = tk.Canvas(p2, width=WND_WIDTH / 2 + 10, height=p2.winfo_height())
    canvas.pack(side="left", fill="both", expand=0)

    scrol = ttk.Scrollbar(p2, orient="vertical", command=canvas.yview)
    scrol.pack(side="right", fill="both")
    canvas.bind('<Configure>', lambda e: canvas.configure(scrollregion=canvas.bbox("all")))

    second_frame = tk.Frame(canvas)
    canvas.create_window((0, 0), window=second_frame, anchor="nw")
    canvas.configure(yscrollcommand=scrol.set)

    from database_impl.SchoolData import GLOBAL_SCHOOL_DATA
    for prof in GLOBAL_SCHOOL_DATA.CREATED_TEACHERS:
        cf = tk.Frame(second_frame, width=p2.winfo_width() - 10, highlightbackground="red", highlightthickness=4,
                      height=100)
        cn = tk.Label(cf, text=prof.get_name().upper(), bd=0,
                      font=("Times New Roman", 15, "bold"), padx=0, height=2)
        cno = tk.Label(cf, text="Cursos: " + prof.getCourseNames() if len(prof.getCourse()) > 0 else "NA",
                       bd=0,
                       font=("Times New Roman", 12, "bold"), padx=0, height=2)
        cn.place(relx=0.05, rely=0.15)
        cno.place(relx=0.05, rely=0.5)

        bsels = tk.Button(cf, text="SELECT", height=2, width=10, command=prof.set_selected)
        bsels['font'] = Font(family="Times New Roman", size=8, weight="bold")
        bsels.place(relx=0.8, rely=0.5)
        cf.pack(anchor="w", expand=0, fill="both")


def handle_professors():
    refresh_wnd()
    nextm = tk.Button(p1, text="REGRESAR", height=2, width=20, command=user_main_menu)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.85)

    nextm = tk.Button(p1, text="INSCRIBIR\nPROFESOR", height=3, width=20, command=add_prof)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.05)

    canvas = tk.Canvas(p2, width=WND_WIDTH / 2 + 10, height=p2.winfo_height())
    canvas.pack(side="left", fill="both", expand=0)

    scrol = ttk.Scrollbar(p2, orient="vertical", command=canvas.yview)
    scrol.pack(side="right", fill="both")
    canvas.bind('<Configure>', lambda e: canvas.configure(scrollregion=canvas.bbox("all")))

    second_frame = tk.Frame(canvas)
    canvas.create_window((0, 0), window=second_frame, anchor="nw")
    canvas.configure(yscrollcommand=scrol.set)

    from database_impl.SchoolData import GLOBAL_SCHOOL_DATA
    for prof in GLOBAL_SCHOOL_DATA.CREATED_TEACHERS:
        cf = tk.Frame(second_frame, width=p2.winfo_width() - 10, highlightbackground="red", highlightthickness=4,
                      height=100)
        cn = tk.Label(cf, text=prof.get_name().upper(), bd=0,
                      font=("Times New Roman", 15, "bold"), padx=0, height=2)
        cno = tk.Label(cf, text="Cursos: " + prof.getCourseNames() if len(prof.getCourse()) > 0 else "NA",
                       bd=0,
                       font=("Times New Roman", 12, "bold"), padx=0, height=2)
        cn.place(relx=0.05, rely=0.15)
        cno.place(relx=0.05, rely=0.5)

        bsels = tk.Button(cf, text="SELECT", height=2, width=10, command=prof.set_selected)
        bsels['font'] = Font(family="Times New Roman", size=8, weight="bold")
        bsels.place(relx=0.8, rely=0.5)
        cf.pack(anchor="w", expand=0, fill="both")


def show_course_statistics():
    refresh_wnd()
    marginy = 0.1
    notas = Course.SELECTED.getStats()
    for i in range(4):
        sbn = tk.Label(p2, text=notas[i][0])
        sbn.place(relx=0.1, rely=marginy)
        marginy += 0.03
        prom = tk.Label(p2, text=notas[i][1])
        prom.place(relx=0.1, rely=marginy)
        marginy += 0.03
        dicta = tk.Label(p2, text=notas[i][2])
        dicta.place(relx=0.1, rely=marginy)
        marginy += 0.1

    marginy = 0.1
    for i in range(4, 8):
        sbn = tk.Label(p2, text=notas[i][0])
        sbn.place(relx=0.6, rely=marginy)
        marginy += 0.03
        prom = tk.Label(p2, text=notas[i][1])
        prom.place(relx=0.6, rely=marginy)
        marginy += 0.03
        dicta = tk.Label(p2, text=notas[i][2])
        dicta.place(relx=0.6, rely=marginy)
        marginy += 0.1

    nextm = tk.Button(p1, text="REGRESAR", height=2, width=20, command=handle_courses)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.85)


def show_course_schedule():
    refresh_wnd()

    init_marginx = 0.02

    for day in Course.SELECTED.getTimetable().toPrintList().items():
        init_marginy = 0.1
        dayl = tk.Label(p2, text=day[0].name)
        dayl.place(relx=init_marginx, rely=init_marginy)
        init_marginy += 0.05
        for sbj in day[1]:
            sbjn = tk.Label(p2, text=sbj.getName())
            sbjn.place(relx=init_marginx, rely=init_marginy)
            init_marginy += 0.03
            prfs = tk.Label(p2, text=sbj.getTeacher().get_name() if sbj.getTeacher() is not None else "NA")
            prfs.place(relx=init_marginx, rely=init_marginy)
            init_marginy += 0.05
        init_marginx += 0.2

    nextm = tk.Button(p1, text="REGRESAR", height=2, width=20, command=handle_courses)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.85)


def handle_courses():
    refresh_wnd()
    nextm = tk.Button(p1, text="REGRESAR", height=2, width=20, command=user_main_menu)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.85)

    nextm = tk.Button(p1, text="ESTADISTICAS DE\nCURSOS", height=3, width=20, command=show_course_statistics)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.05)

    nextm = tk.Button(p1, text="VER HORARIO\nDEL CURSO", height=3, width=20, command=show_course_schedule)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.25)

    canvas = tk.Canvas(p2, width=WND_WIDTH / 2 + 10, height=p2.winfo_height())
    canvas.pack(side="left", fill="both", expand=0)

    scrol = ttk.Scrollbar(p2, orient="vertical", command=canvas.yview)
    scrol.pack(side="right", fill="both")
    canvas.bind('<Configure>', lambda e: canvas.configure(scrollregion=canvas.bbox("all")))

    second_frame = tk.Frame(canvas)
    canvas.create_window((0, 0), window=second_frame, anchor="nw")
    canvas.configure(yscrollcommand=scrol.set)

    from database_impl.SchoolData import GLOBAL_SCHOOL_DATA
    for course in GLOBAL_SCHOOL_DATA.CREATED_COURSES:
        cf = tk.Frame(second_frame, width=p2.winfo_width() - 10, highlightbackground="red", highlightthickness=4,
                      height=100)
        cn = tk.Label(cf, text=course.getName().upper(), bd=0,
                      font=("Times New Roman", 15, "bold"), padx=0, height=2)
        cno = tk.Label(cf, text="Numero de estudiantes: " + str(course.getNoOfStudents()), bd=0,
                       font=("Times New Roman", 10, "bold"), padx=0, height=2)
        cn.place(relx=0.05, rely=0.15)
        cno.place(relx=0.05, rely=0.5)

        bsel = tk.Button(cf, text="SELECT", height=2, width=10, command=course.set_selected)
        bsel['font'] = Font(family="Times New Roman", size=8, weight="bold")
        bsel.place(relx=0.8, rely=0.5)
        cf.pack(anchor="w", expand=0, fill="both")


def user_main_menu():
    refresh_wnd()
    nextm = tk.Button(p1, text="REGRESAR AL MENU", height=2, width=20, command=draw_main_menu)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.85)

    nextm = tk.Button(p1, text="MANEJAR\nESTUDIANTES", height=3, width=20, command=handle_students)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.05)

    nextm = tk.Button(p1, text="MANEJAR\nPROFESORES", height=3, width=20, command=handle_professors)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.25)

    nextm = tk.Button(p1, text="MANEJAR\nCURSOS", height=3, width=20, command=handle_courses)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.1, rely=0.45)

    schim1l = tk.Label(p2, image=schimg1i)
    schim2l = tk.Label(p2, image=schimg2i)
    schim3l = tk.Label(p2, image=schimg3i)

    schim1l.grid()
    schim2l.grid()
    schim3l.grid()


def draw_main_menu():
    refresh_wnd()
    p1.place(x=20, y=20)

    WND.update()  # Esta funcion hace que las funciones winfo_width() y winfo_height regresen los valores correctos. (si no se usa, regresan 1)

    p2.place(x=p1.winfo_width() + 30, y=20)

    WND.update()

    p3 = tk.Frame(p1, highlightbackground="white", highlightthickness=4, height=p1.winfo_height() / 3 + 40,
                  width=p1.winfo_width() - 20)  # Frame superior dentro de P1
    p3.place(x=5, y=10)
    p4 = tk.Frame(p1, highlightbackground="white", highlightthickness=4,
                  height=p1.winfo_height() - (p1.winfo_height() / 3 + 80), width=p1.winfo_width() - 20)
    p4.place(x=5, y=p2.winfo_height() / 3 + 60)

    p5 = tk.Frame(p2, highlightbackground="white", highlightthickness=4, height=p2.winfo_height() / 2 + 80,
                  width=p2.winfo_width() - 20)  # Frame superior dentro de P1
    p5.place(x=5, y=10)
    p6 = tk.Frame(p2, highlightbackground="white", highlightthickness=4,
                  height=p2.winfo_height() - (p2.winfo_height() / 2 + 120), width=p2.winfo_width() - 20)
    p6.place(x=5, y=p2.winfo_height() / 2 + 100)

    greet = tk.Label(p3, text="BIENVENIDO\nAL\nCONTROL\nMAESTRO\nACADÉMICO...", bd=0,
                     font=("Times New Roman", 23, "bold"), padx=40, height=6)
    greet.place(relx=0.14, rely=0.1)

    cvl = tk.Label(p5, image=cvli)
    cvl.place(relx=0.02)

    p6.rowconfigure(2)
    p6.columnconfigure(2)

    meil = tk.Label(p6, image=mei)
    meil2 = tk.Label(p6, image=mei)
    meil3 = tk.Label(p6, image=mei)
    meil4 = tk.Label(p6, image=mei)
    meil.grid(row=0, column=0)
    meil2.grid(row=0, column=1)
    meil3.grid(row=1, column=0)
    meil4.grid(row=1, column=1)

    nextm = tk.Button(p4, text="MENÚ\nPRINCIPAL", height=5, width=15, command=user_main_menu)
    nextm['font'] = Font(family="Times New Roman", size=15, weight="bold")
    nextm.place(relx=0.3, rely=0.35)


if __name__ == "__main__":
    draw_main_menu()
    stdnt = Student("Javier", 11, "2022/12/22", "Male")
    course = Course("Sexto")
    course2 = Course("Septimo")
    course3 = Course("Octavo")
    course4 = Course("Noveno")
    course5 = Course("Decimo")
    course6 = Course("Undecimo")

    WND.mainloop()
