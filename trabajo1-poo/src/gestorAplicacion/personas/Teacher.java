package gestorAplicacion.personas;

import java.util.ArrayList;

import gestorAplicacion.school_related.Course;
import gestorAplicacion.school_related.Subject;;

public class Teacher extends User
{
	
	private static final long serialVersionUID = 1971354663482676115L;
	private ArrayList<Course> assignedCourses;
	private ArrayList<Subject> assignedSubjects;
	
	private static ArrayList<Teacher> created_teachers = new ArrayList<Teacher>();
	
	Teacher(String name, int id) {
		super(name, id);
		// TODO Auto-generated constructor stub
	}

	public void check_perf() { // Comprobar el desempeno del profesor.
		
	}

	public void startInterface() { // Inicializar la interfaz para usuarios de tipo profesor.
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Course> getAssignedCourses() {
		return this.assignedCourses;
	}

	public ArrayList<Subject> getAssignedSubjects() {
		return assignedSubjects;
	}

	public void setAssignedSubjects(ArrayList<Subject> assignedSubjects) {
		this.assignedSubjects = assignedSubjects;
	}
	
	public static Teacher find(String name) // Find by name
	{
		for(Teacher t : Teacher.created_teachers)
		{
			if(t.getName().equals(name))
				return t;
		}
		
		return null; // Retorna null si no existe en la lista un profesor con una cuenta asociada al nombre (en teoria imposible)
	}
	
	public static Teacher find(int id) // Find by id
	{
		for(Teacher t : Teacher.created_teachers)
		{
			if(t.getIdentification() == id)
				return t;
		}
		
		return null; // Retorna null si no existe en la lista un profesor con una cuenta asociada al nombre (en teoria imposible)
	}

	public static ArrayList<Teacher> getCreated() {
		return Teacher.created_teachers;
	}

	public static void setCreated(ArrayList<Teacher> ar) {
		Teacher.created_teachers = ar;
	}

	public String toString() {
		String s = "Nombre: " + this.getName() + "\n" + "Materias y curso: ";
		for(Subject sb : this.assignedSubjects)
		{
			s += sb.getSname() + "-" + sb.getCourse().getCourseName() + " "; // Anade las materias que dicta en el curso donde las dicta.
		}
		return s;
	}
	
}
