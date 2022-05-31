package gestorAplicacion.personas;

import java.util.ArrayList;

import gestorAplicacion.school_related.Course;
import gestorAplicacion.school_related.Subject;
import uiMain.Main;;

public class Teacher extends User
{
	
	private static final long serialVersionUID = 1971354663482676115L;
	private ArrayList<Course> assignedCourses;
	private ArrayList<Subject> assignedSubjects;
	
	Teacher(String name, int id) {
		super(name, id);
		assignedCourses = new ArrayList<Course>();
		assignedSubjects = new ArrayList<Subject>();
	}

	public String check_perf() { // Comprobar el desempeno del profesor.
		return null;
	}

	public ArrayList<Course> getAssignedCourses() {
		return this.assignedCourses;
	}

	public ArrayList<Subject> getAssignedSubjects() {
		return this.assignedSubjects;
	}

	public void setAssignedSubjects(ArrayList<Subject> assignedSubjects) {
		this.assignedSubjects = assignedSubjects;
	}
	
	public static Teacher find(String name) // Find by name
	{
		for(Teacher t : Main.school.created_teachers)
		{
			if(t.getName().equals(name))
				return t;
		}
		
		return null; // Retorna null si no existe en la lista un profesor con una cuenta asociada al nombre (en teoria imposible)
	}
	
	public static Teacher find(int id) // Find by id
	{
		for(Teacher t : Main.school.created_teachers)
		{
			if(t.getIdentification() == id)
				return t;
		}
		
		return null; // Retorna null si no existe en la lista un profesor con una cuenta asociada al nombre (en teoria imposible)
	}

	public static ArrayList<Teacher> getCreated() {
		return Main.school.created_teachers;
	}

	public String toString() {
		String s = "Nombre: " + this.getName() + "\n" + "Materias y curso: ";
		for(Subject sb : this.assignedSubjects)
		{
			s += sb.getSname() + "-" + sb.getCourse().getCourseName() + " "; // Anade las materias que dicta en el curso donde las dicta.
		}
		return s;
	}

	public void kick() { // Saca al profesor de todos los cursos y de los profesores inscritos, efectivamente removiendolo del sistema.
		ArrayList<Teacher> cpt = new ArrayList<Teacher>(Main.school.created_teachers);
		for(Teacher t : cpt)
		{
			if(t.getIdentification() == this.identification) // Si encuentra al profesor, sacarlo del arreglo.
				Main.school.created_teachers.remove(t);
		}
		
		for(Course ac : this.assignedCourses) // Luego sacar el profesor de cada curso en el que esta asignado, lo que quiere decir que
			ac.rmvTeacher(this);	// Las referencias a ese profesor en los cursos y todos los objetos del curso (materias) sera null.
	}
	
	public void rmvSubjects(Course c)
	{
		ArrayList<Subject> cp = new ArrayList<Subject>(this.assignedSubjects);
		for(Subject sb : cp)
		{
			if(sb.getCourse().getCourseName() == c.getCourseName())
				this.assignedSubjects.remove(sb);
		}
	}
	
}
