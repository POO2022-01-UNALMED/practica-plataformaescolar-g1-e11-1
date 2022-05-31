package baseDatos;

import java.io.Serializable;
import java.util.ArrayList;

import gestorAplicacion.personas.Student;
import gestorAplicacion.personas.Teacher;
import gestorAplicacion.school_related.Course;

public class AllData implements Serializable { // Esta clase existe para evitar desincronizacion de referencias.

	/**
	 * 
	 */
	private static final long serialVersionUID = -3558099496274222613L;
	public ArrayList<Student> created_students;
	public ArrayList<Teacher> created_teachers;
	public ArrayList<Course> created_courses;
	
	public AllData()
	{
		created_students = new ArrayList<Student>();
		created_teachers = new ArrayList<Teacher>();
		created_courses = new ArrayList<Course>();
	}
	
}
