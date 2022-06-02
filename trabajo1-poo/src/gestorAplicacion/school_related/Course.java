package gestorAplicacion.school_related;

import java.io.Serializable;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;

import gestorAplicacion.personas.Student;
import gestorAplicacion.personas.Teacher;
import uiMain.Main;


public class Course implements Serializable, InfoOperations
{

	private static final long serialVersionUID = -5223735748429732043L;
	
	private String course_name;
	private int no_of_students;
	
	private ArrayList<Student> enrolled_students;
	private ArrayList<Teacher> assigned_teachers;
	private Timetable schedule;
	
	private ArrayList<Subject> course_subjects;
	
	private static ArrayList<String> offered_subjects = new ArrayList<>(
			Arrays.asList(new String[]
	{"Physics", "English", "Math", "Biology", "Spanish", "History", "Comp. Science", "P. Education", "Chemistry", "Philosophy"}));
	
	public Course(String course_name)
	{
		this.course_name = course_name;
		enrolled_students = new ArrayList<Student>();
		assigned_teachers = new ArrayList<Teacher>();
		
		course_subjects = new ArrayList<Subject>();
		// Anadir materias al azar al curso. Seran las materias base del curso.
		
		ArrayList<String> chosen_already = new ArrayList<>(); // Arreglo donde se guardan las materias que se van metiendo al curso (un arreglo helper que ayuda a evitar materias duplicadas en un curso)
		while(course_subjects.size() != 8) // Escoger 7 materias al azar de la lista de materias ofrecidas por el colegio.
		{
			int rand_idx = (int)Math.floor(Math.random() * 9);
			Subject s = new Subject(offered_subjects.get(rand_idx), null, this);
			if(!chosen_already.contains(s.getSname()))
			{
				course_subjects.add(s);
				chosen_already.add(s.getSname()); // Si existe la materia no hacer nada, si no, anadirla a a la lista de materias cursadas.
			}
		}
		
		for(Subject sb : this.course_subjects)
		{
			int nev;
			for(nev = 1; nev < 4; nev++)
			{
				EvaluationEvent ev = new EvaluationEvent(sb.getSname(), "Evaluacion N�" + nev);
				sb.getExamInfo().add(ev);
			}
		}
		
		schedule = new Timetable(this);
		Main.school.created_courses.add(this);
	}
	
	public String add_student(Student s)
	{
		if(!s.isEnrolled() && s.getAH().isActive()) // No puede estar inscrito ya, tampoco puede tener historia academica inactiva.
		{
			enrolled_students.add(s);
			s.setCourse(this);
			this.no_of_students += 1;
			
			s.inscribeSubjects(this);
			
			return "Estudiante inscrito correctamente.";
		}	
		
		return "El estudiante ya se encuentra inscrito a un curso o se encuentra vetado de la institucion.";
	}
	
	public void add_teacher(Teacher t)
	{
		for(Course c: t.getAssignedCourses()) // El profesor no puede estar mas de una vez en el mismo curso.
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
		return Main.school.created_courses;
	}

	public int getNo_of_students() {
		return this.enrolled_students.size();
	}

	public ArrayList<Subject> getCourse_subjects()
	{
		return this.course_subjects;
	}
	
	public ArrayList<Student> getCourse_students()
	{
		return this.enrolled_students;
	}

	public Timetable getSchedule() {
		return schedule;
	}

	public void setSchedule(Timetable schedule) {
		this.schedule = schedule;
	}
	
	public void finalizeC() // Si finalizas el curso, se guarda un registro para cada estudiante del curso en su historia academica.
	{ // Este registro esta conformado por el nombre del curso, el promedio del estudiante al finalizar el curso y las notas de cada materia.
		ArrayList<Student> cps = new ArrayList<Student>(this.enrolled_students);
		for(Student s : cps)
		{
			s.setCourse(null);
			this.enrolled_students.remove(s); // Primero remover al estudiante del curso, ya que este finalizo.
			String reg = "Registro para el curso: " + this.getCourseName() + "\n"; // registro
			double avg_total = 0;
			ArrayList<Subject> cpss = new ArrayList<Subject>(s.getSubjects());
			for(Subject sb : cpss) // Anade el promedio de cada materia
			{
				avg_total = sb.calculateAvg();
				reg += sb.toString() + "\n";
				s.getSubjects().remove(sb);
			}
			
			avg_total = avg_total/this.course_subjects.size(); // Luego calcula el promedio de todas las materias.
			reg += "Promedio: " + String.format("%.2f", avg_total);
			
			s.getAH().add_registry(reg); // Anadir el registro del curso a la historia academica del estudiante.
		}
	}
	
	public void updateSubjects(String sname, Teacher t2) // Cambia el profesor de una materia especifica.
	{
		for(Subject s : this.course_subjects) // Cambiar las referencias del profesor que dicta la materia.
		{
			if(s.getSname() == sname)
			{
				if(s.getTeacher() != null)
				{
					Teacher prev_teach = s.getTeacher();
					ArrayList<Subject> prev_assigned = new ArrayList<Subject>(prev_teach.getAssignedSubjects());
					for(Subject sbj : prev_assigned)
					{
						if(sbj.getSname() == sname && sbj.getCourse() == this)
							prev_teach.getAssignedSubjects().remove(sbj);
					}
				}
				s.setTeacher(t2);
			}
		}
		
		for(Student s : this.enrolled_students) // Cambiar las referencias para cada instancia de la materia de cada estudiante.
		{
			for(Subject sb : s.getSubjects())
			{
				if(sb.getSname() == sname)
					sb.setTeacher(t2);
			}
		}
	}
	
	public void updateSubjects(Teacher t1, Teacher t2) // Actualizar las referencias a profesor en las materias dependiendo si se reasigno o se elimino al profesor del curso.
	{
		for(Subject s : this.course_subjects) // Cambiar las referencias del profesor que dicta la materia.
		{
			if(s.getTeacher() != null)
			{
				if(s.getTeacher().getIdentification() == t1.getIdentification()) // Si el profesor dicta la materia, cambiarlo por el profesor referenciado por t2.
					s.setTeacher(t2);
			}
		}
		
		for(Student s : this.enrolled_students) // Cambiar las referencias para cada instancia de la materia de cada estudiante.
		{
			for(Subject sb : s.getSubjects())
			{
				if(sb.getTeacher() != null)
				{
					if(sb.getTeacher().getIdentification() == t1.getIdentification()) // Si el profesor dicta la materia, cambiarlo por el profesor referenciado por t2.
						sb.setTeacher(t2);
				}
			}
		}
	}
	
	
	public void rmvTeacher(Teacher t) // Eliminar un profesor del curso.
	{
		ArrayList<Teacher> cpt = new ArrayList<Teacher>(this.assigned_teachers);
		for(Teacher at : cpt)
		{
			if(at.getIdentification() == t.getIdentification())
				this.assigned_teachers.remove(at);
		}
		
		this.updateSubjects(t, null);
	}
	
	public String toString()
	{
		return "Nombre del curso: " + this.course_name + " numero de estudiantes: " + this.getNo_of_students();
	}

	public String check_perf() { // regresa las estadisticas del curso (promedio de cada materia)
		String perf = "";
		for(Subject csbj : this.course_subjects)
		{
			double avg = 0;
			perf += "Para la materia " + csbj.getSname() + " el promedio es ";;
			for(Student s : this.enrolled_students)
			{
				for(Subject ssbj : s.getSubjects()) // Sumar el promedio de cada estudiante para una materia.
				{
					if(ssbj.getSname() == csbj.getSname())
					{
						avg += ssbj.calculateAvg();
					}
				}
			}
			
			perf += String.format("%.2f", avg/this.getNo_of_students()) + "\n";
		}
		
		perf += "Para un total de " + this.getNo_of_students() + " estudiantes.";
		return perf;
	}

	public String check_info() {
		String r = "";
		
		r += "Nombre del curso: " + this.course_name + "\n";
		r += "Materias del curso: \n";
		for(Subject s : this.course_subjects)
			r += s.getSname() + ", dictada por: " + (s.getTeacher() == null ? "sin profesor asignado." : s.getTeacher().getName()) + "\n";
		
		r += "\n";
		
		return r;
	}
}
