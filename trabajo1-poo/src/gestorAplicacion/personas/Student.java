package gestorAplicacion.personas;

import java.util.ArrayList;

import gestorAplicacion.school_related.AcademicHistory;
import gestorAplicacion.school_related.Course;
import gestorAplicacion.school_related.EvaluationEvent;
import gestorAplicacion.school_related.Subject;
import uiMain.Main;

public class Student extends User 
{
	private static final long serialVersionUID = -1687054091292727513L;
	
	private Boolean isEnrolled = false;   // Si el estudiante esta inscrito a un curso. Si esto es true, course debe ser no nulo.
	private Course course = null; // Un estudiante puede estar inscrito a un solo curso.
	private ArrayList<Subject> subjects; // Todos los estudiantes deben inicializarse inscritos a las materias que se dictan en un curso.
	private AcademicHistory academic_history;

	public Student(String name, int id) {
		super(name, id);
		subjects = new ArrayList<Subject>();
		
		academic_history = new AcademicHistory();
		Main.school.created_students.add(this);
	}
	
	public Student(String name, int id, String bd, String sex, String bloodtype, String birthplace) {
		super(name, id, bd, sex, bloodtype, birthplace);
		subjects = new ArrayList<Subject>();
		
		academic_history = new AcademicHistory();
		Main.school.created_students.add(this);
	}
	
	public boolean isEnrolled()
	{
		return this.isEnrolled;
	}
	
	public void setCourse(Course c)
	{
		if(c != null)
		{
			this.course = c;
			this.isEnrolled = true;
			c.add_student(this);
		}
		
		else
		{
			this.course = c;
			this.isEnrolled = false;
		}
	}
	
	public Course getCourse()
	{
		return this.course;
	}

	public String check_perf() { // Ver las notas del estudiante (del curso actual)
		if(this.course == null)
			return "El estudiante no se encuentra en ningun curso";
		
		String s = "Informacion academica para el estudiante: " + this.getName() + "\n\n";
		s += "Curso: " + this.getCourse().getCourseName() + "\n";
		double all_subject_prom = 0.0;
		for(Subject sb : this.subjects)
		{
			s += "Materia: " + sb.getSname() + "\n";
			double tg = 0.0;
			for(EvaluationEvent ev : sb.getExamInfo())
			{
				s += "Para la " + ev.getName() + "\n";
				s += "Nota: " + String.format("%.2f", ev.getGrade()) + "\n";
				tg += ev.getGrade();
			}
			s += "Promedio: " + String.format("%.2f",tg/3) + "\n\n";
			all_subject_prom += tg/3;
		}
		
		s += "Promedio de todas las materias: " + String.format("%.2f", all_subject_prom/8);
		return s + "\n" + this.academic_history.dumpHistory();
	}

	public ArrayList<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(ArrayList<Subject> subjects) {
		this.subjects = subjects;
	}
	
	public static Student find(String name) // Find by name
	{
		for(Student t : Main.school.created_students)
		{
			if(t.getName().equals(name))
				return t;
		}
		
		return null; // Retorna null si no existe en la lista un profesor con una cuenta asociada al nombre (en teoria imposible)
	}
	
	public static Student find(int id) // Find by id
	{
		for(Student t : Main.school.created_students)
		{
			if(t.getIdentification() == id)
				return t;
		}
		
		return null; // Retorna null si no existe en la lista un profesor con una cuenta asociada al nombre (en teoria imposible)
	}

	public static ArrayList<Student> getCreated() {
		return Main.school.created_students;
	}

	public String toString() {
		return "Nombre:" + this.getName() + "\n" + "Documento:" + this.getIdentification() + "\n";
	}

	public void inscribeSubjects(Course course2) { // Este metodo inscribe las materias del curso al estudiante, despues de anadirlo a dicho curso.
		for(Subject sb : course2.getCourse_subjects()) // Recorre todos las materias del curso 
		{
			Subject stdsb = new Subject(sb.getSname(), sb.getTeacher(), course2); //y crea una materia especifica al estudiante usando las caracteristicas de la materia del curso.
			
			for(EvaluationEvent ev : sb.getExamInfo()) // Las evaluaciones programadas para dicha materia en el curso tambien se copian al estudiante, con el detalle de que a estas se les asigna una nota al azar de 0 a 5.
			{
				EvaluationEvent stdev = new EvaluationEvent(ev.getSubject_name(), ev.getName());
				stdev.setGrade((float)Math.random() * 5);
				stdsb.getExamInfo().add(stdev);
			}
			this.subjects.add(stdsb); // Crea una nueva instancia de materia con las caracteristicas genericas de la materia del curso (Nombre, profesor y curso) pero con la nota especifica del estudiante.
			
		}
		
	}
	
	public AcademicHistory getAH()
	{
		return this.academic_history;
	}

	public void kick() { // Expulsa al estudiante del colegio, sacandolo del curso en el que esta inscrito y finalizando su periodo academico.
		this.academic_history.finalizeH(this.check_perf());
	}

	public String check_info() {
		String info = "";
		info += "Nombre: " + this.getName() + "\n";
		info += "Cedula: " + this.getIdentification() + "\n";
		info += "Fecha de nacimiento: " + this.getBirthdate() + "\n";
		info += "Lugar de nacimiento: " + this.getBirthplace() + "\n";
		info += "Tipo de sangre: " + this.getBloodtype() + "\n";
		info += "Genero: " + this.getSex() + "\n";
		
		info += "Curso: " + ((this.course == null) ? "no inscrito a ningun curso" : this.course.getCourseName()) + "\n";
		if(this.course != null)
		{
			float avg = 0;
			for(Subject sbj : this.subjects)
				avg += sbj.calculateAvg();
			
			avg = avg/this.subjects.size();
			info += "Promedio de materias: " + String.format("%.2f", avg);
		}
		
		return info;
	}
	
}
