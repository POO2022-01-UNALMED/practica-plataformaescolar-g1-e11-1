package gestorAplicacion.school_related;

import java.io.Serializable;
import java.util.ArrayList;

import gestorAplicacion.personas.Student;
import gestorAplicacion.personas.Teacher;

public class Subject implements Serializable // Subject pertenece a un estudiante que pertenece a un curso.
{
	private static final long serialVersionUID = 5066186753834566065L;
	private final String sname;
	private Teacher assigned_teacher;
	private Course course;
	private Student student;
	private String guidebook;
	
	private ArrayList<EvaluationEvent> taken_tests;
	
	public Subject(String sname, Teacher teacher, Course course)
	{
		this.sname = sname;
		this.assigned_teacher = teacher;
		this.course = course;
		taken_tests = new ArrayList<EvaluationEvent>();
	}
	
	public Teacher getTeacher()
	{
		return this.assigned_teacher;
	}
	
	void setTeacher(Teacher t)
	{
		this.assigned_teacher = t;
	}
	
	String getGuidebook()
	{
		return this.guidebook;
	}
	
	void setGuidebook(String gb)
	{
		this.guidebook = gb;
	}
	
	public ArrayList<EvaluationEvent> getExamInfo() // Obtener informacion sobre un test particular de la materia.
	{
		return this.taken_tests;
	}
	
	void addEvaluation(EvaluationEvent ev) // Programar un test particular para la materia.
	{
		if(ev != null)
			this.taken_tests.add(ev);
	}
	
	public double calculateAvg()
	{
		double ttl = 0.0;
		for(EvaluationEvent ev : this.taken_tests)
		{
			ttl += ev.getGrade();
		}
		
		return ttl/3;
	}

	public String getSname() {
		return this.sname;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	public String toString()
	{
		double ttl = 0.0;
		for(EvaluationEvent ev : this.taken_tests)
		{
			ttl += ev.getGrade();
		}
		return "Nombre de la materia: " + this.sname  + "Dictada por: "  + ((this.assigned_teacher == null)? "Desconocido" : this.assigned_teacher.getName()) + "\n" + "Nota: " + ttl/3;
	}
}
