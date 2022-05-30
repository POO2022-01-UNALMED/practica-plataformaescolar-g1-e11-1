package gestorAplicacion.school_related;

import java.io.Serializable;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;

import gestorAplicacion.personas.Student;
import gestorAplicacion.personas.Teacher;


public class Course implements Serializable
{

	private static final long serialVersionUID = -5223735748429732043L;
	
	private String course_name;
	private int no_of_students;
	
	private ArrayList<Student> enrolled_students;
	private ArrayList<Teacher> assigned_teachers;
	private Timetable schedule;
	
	private ArrayList<Subject> course_subjects;
	
	private static ArrayList<Course> created_courses = new ArrayList<Course>();
	private static ArrayList<String> offered_subjects = new ArrayList<>(
			Arrays.asList(new String[]
	{"Physics", "English", "Math", "Biology", "Spanish", "History", "Comp. Science", "P. Education", "Chemistry", "Philosophy"}));
	
	public Course(String course_name)
	{
		this.course_name = course_name;
		
		course_subjects = new ArrayList<Subject>();
		// Anadir materias al azar al curso. Seran las materias base del curso.
		
		while(course_subjects.size() != 8) // Escoger 7 materias al azar de la lista de materias ofrecidas por el colegio.
		{
			int rand_idx = (int)Math.floor(Math.random() * 9);
			Subject s = new Subject(offered_subjects.get(rand_idx), null, this);
			if(!course_subjects.contains(s))
				course_subjects.add(s);
		}
		
		for(Subject sb : this.course_subjects)
		{
			int nev;
			for(nev = 1; nev < 4; nev++)
			{
				EvaluationEvent ev = new EvaluationEvent(sb.getSname(), "Evaluacion N°" + nev);
				sb.getExamInfo().add(ev);
			}
		}
		
		this.setSchedule(new Timetable(this)); // Luego inicializar el horario con las materias que tiene el curso.
		created_courses.add(this);
	}
	
	public String add_student(Student s)
	{
		if(!s.isEnrolled())
		{
			enrolled_students.add(s);
			s.setCourse(this);
			this.no_of_students += 1;
			
			s.inscribeSubjects(this);
			
			return "Estudiante inscrito correctamente.";
		}	
		
		return "El estudiante ya se encuentra inscrito a un curso.";
	}
	
	public void add_teacher(Teacher t)
	{
		for(Course c: t.getAssignedCourses())
		{
			if(c.getCourseName().equals(this.course_name))
			{
				return;
			}
		}
		
		t.getAssignedCourses().add(this);
		this.assigned_teachers.add(t);
	}
	
	public String getCourseName()
	{
		return this.course_name;
	}

	public static ArrayList<Course> getCreated() {
		return Course.created_courses;
	}

	public static void setCreated(ArrayList<Course> ar) {
		Course.created_courses = ar;
	}

	public int getNo_of_students() {
		return no_of_students;
	}

	public ArrayList<Subject> getCourse_subjects()
	{
		return this.course_subjects;
	}

	public Timetable getSchedule() {
		return schedule;
	}

	public void setSchedule(Timetable schedule) {
		this.schedule = schedule;
	}
	
	public void updateSubjects(String sname, Teacher t1, Teacher t2) // Cambia el profesor de una materia especifica.
	{
		for(Subject s : this.course_subjects) // Cambiar las referencias del profesor que dicta la materia.
		{
			if(s.getSname() == sname)
			{
				if(s.getTeacher().getIdentification() == t1.getIdentification()) // Si el profesor dicta la materia, cambiarlo por el profesor referenciado por t2.
					s.setTeacher(t2);
			}
		}
		
		for(Student s : this.enrolled_students) // Cambiar las referencias para cada instancia de la materia de cada estudiante.
		{
			for(Subject sb : s.getSubjects())
			{
				if(sb.getSname() == sname)
				{
					if(sb.getTeacher().getIdentification() == t1.getIdentification()) // Si el profesor dicta la materia, cambiarlo por el profesor referenciado por t2.
						sb.setTeacher(t2);
				}
			}
		}
	}
	
	public void updateSubjects(Teacher t1, Teacher t2) // Actualizar las referencias a profesor en las materias dependiendo si se reasigno o se elimino al profesor del curso.
	{
		for(Subject s : this.course_subjects) // Cambiar las referencias del profesor que dicta la materia.
		{
			if(s.getTeacher().getIdentification() == t1.getIdentification()) // Si el profesor dicta la materia, cambiarlo por el profesor referenciado por t2.
				s.setTeacher(t2);
		}
		
		for(Student s : this.enrolled_students) // Cambiar las referencias para cada instancia de la materia de cada estudiante.
		{
			for(Subject sb : s.getSubjects())
			{
				if(sb.getTeacher().getIdentification() == t1.getIdentification()) // Si el profesor dicta la materia, cambiarlo por el profesor referenciado por t2.
					sb.setTeacher(t2);
			}
		}
	}
	
	
	public void rmvTeacher(Teacher t) // Eliminar un profesor del curso.
	{
		for(Teacher at : this.assigned_teachers)
		{
			if(at.getIdentification() == t.getIdentification())
				this.assigned_teachers.remove(at);
		}
		
		/*
		 * ArrayList<Course> ac = t.getAssignedCourses(); // Obtener el arreglo de
		 * cursos del profesor a remover for(Course c : ac) { if(c.getCourseName() ==
		 * this.course_name) { ac.remove(c); // Remover este curso del arreglo de cursos
		 * asignados del profesor. } }
		 */
		this.updateSubjects(t, null);
	}
	
	public String toString()
	{
		String r = "";
		
		r += "Nombre del curso: " + this.course_name + "\n";
		r += "Materias del curso: \n";
		for(Subject s : this.course_subjects)
			r += s.getSname() + ", dictada por: " + (s.getTeacher() == null ? "sin profesor asignado." : s.getTeacher().getName()) + "\n";
		
		r += "\n";
		
		return r;
	}
}
