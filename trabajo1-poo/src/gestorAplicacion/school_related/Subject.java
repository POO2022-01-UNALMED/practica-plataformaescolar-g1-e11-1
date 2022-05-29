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
	
	private ArrayList<EvaluationEvent> takenTest;
	
	Subject(String sname, Teacher teacher, Course course)
	{
		this.sname = sname;
		this.assigned_teacher = teacher;
		this.course = course;
	}
	
	Teacher getTeacher()
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
	
	EvaluationEvent getExamInfo(int test_no) // Obtener informacion sobre un test particular de la materia.
	{
		if(this.takenTest.size() > test_no)
		{
			return this.takenTest.get(test_no);
		}
		
		System.out.println("The requested test does not exist."); // Los examenes tienen un numero asociado segun el orden en el que se programan, examen 1, 2, 3... etc.
		return null; // Que es el mismo indice (restandole uno) en el arreglo de examenes (takenTest)
	}
	
	void addEvaluation(EvaluationEvent ev) // Programar un test particular para la materia.
	{
		if(ev != null)
			this.takenTest.add(ev);
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
		return "Nombre de la materia: " + this.sname + "Libro guia: " + this.guidebook + "Dictada por: "  + this.assigned_teacher.getName() + "\n";
	}
}
