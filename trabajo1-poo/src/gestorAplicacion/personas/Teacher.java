package gestorAplicacion.personas;

import java.util.ArrayList;

import gestorAplicacion.school_related.Course;
import gestorAplicacion.school_related.Subject;
import uiMain.Main;;

/*
 * Los profesores dictan materias en varios cursos. Una materia de un curso especifico puede ser dictada unicamente por un profesor.
 * Esta restriccion no impide que un profesor pueda, por ejemplo, dictar Math en 2 cursos distintos, pero si que Math del curso sexto sea dictada por dos profesores al mismo tiempo.
 * Los profesores tambien tienen un rendimiento y este se mide por como han resultado los estudiantes de las materias que este dicta.
 */

public class Teacher extends User
{
	
	private static final long serialVersionUID = 1971354663482676115L;
	private ArrayList<Course> assignedCourses;
	private ArrayList<Subject> assignedSubjects;
	
	public Teacher(String name, int id) {
		super(name, id);
		assignedCourses = new ArrayList<Course>();
		assignedSubjects = new ArrayList<Subject>();
		Main.school.created_teachers.add(this);
	}
	
	public Teacher(String name, int id, String bd, String sex, String bloodtype, String birthplace) {
		super(name, id, bd, sex, bloodtype, birthplace);
		assignedCourses = new ArrayList<Course>();
		assignedSubjects = new ArrayList<Subject>();
		Main.school.created_teachers.add(this);
	}

	public String check_perf() { // Comprobar el desempeno del profesor.
		String perf = "Los alumnos de este profesor tienen los siguientes resultados: \n";
		if(this.assignedSubjects.size() == 0)
			return "Este profesor no tiene materias asignadas.\n";
		
		for(Subject sbj : this.assignedSubjects)
		{
			perf += "Para la materia " + sbj.getSname() + " del curso " + sbj.getCourse().getCourseName() + "\n";
			double avg = 0.0;
			for(Student s : sbj.getCourse().getCourse_students()) // Para cada estudiante que este en el curso donde el profesor da la materia.
			{
				for(Subject stdsbj : s.getSubjects()) // Calcular el promedio de dicha materia para cada estudiante del curso.
				{
					if(stdsbj.getTeacher() == this)
						avg += stdsbj.calculateAvg(); // Agrego el promedio de cada estudiante de dicha materia a avg.
				}
			}
			
			avg = avg/sbj.getCourse().getCourse_students().size(); // Dividir el total entre el numero de estudiantes del curso.
			perf += "el promedio es " + String.format("%.2f", avg) + " para un total de " + sbj.getCourse().getCourse_students().size() + " estudiantes.\n"; 
		}
		
		return perf;
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

	@Override
	public String check_info() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
