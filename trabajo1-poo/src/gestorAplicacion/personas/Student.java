package gestorAplicacion.personas;

import java.util.ArrayList;

import gestorAplicacion.school_related.Course;
import gestorAplicacion.school_related.Subject;

public class Student extends User 
{
	private static final long serialVersionUID = -1687054091292727513L;
	
	private Boolean isEnrolled;   // Si el estudiante esta inscrito a un curso. Si esto es true, course debe ser no nulo.
	private Course course = null; // Un estudiante puede estar inscrito a un solo curso.
	private ArrayList<Subject> subjects; // Todos los estudiantes deben inicializarse inscritos a las materias que se dictan en un curso.
	
	private static ArrayList<Student> created_students = new ArrayList<Student>();

	public Student(String name, int id) {
		super(name, id);
		Student.created_students.add(this);
		
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
	}
	
	public Course getCourse()
	{
		return this.course;
	}

	public void check_perf() { // Ver las notas del estudiante.
		
	}

	public ArrayList<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(ArrayList<Subject> subjects) {
		this.subjects = subjects;
	}
	
	public static Student find(String name) // Find by name
	{
		for(Student t : Student.created_students)
		{
			if(t.getName().equals(name))
				return t;
		}
		
		return null; // Retorna null si no existe en la lista un profesor con una cuenta asociada al nombre (en teoria imposible)
	}
	
	public static Student find(int id) // Find by id
	{
		for(Student t : Student.created_students)
		{
			if(t.getIdentification() == id)
				return t;
		}
		
		return null; // Retorna null si no existe en la lista un profesor con una cuenta asociada al nombre (en teoria imposible)
	}

	public static ArrayList<Student> getCreated() {
		// TODO Auto-generated method stub
		return Student.created_students;
	}

	public static void setCreated(ArrayList<Student> ar) {
		Student.created_students = ar;
	}

	public String toString() {
		return "Nombre:" + this.getName() + "\n" + "Documento:" + this.getIdentification() + "\n";
	}
	
}
