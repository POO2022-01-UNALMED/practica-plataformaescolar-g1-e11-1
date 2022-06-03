package gestorAplicacion.school_related;

import java.io.Serializable;

/*
 * Una evaluacion con su nota individual.
 * Cada materia tiene 3 evaluaciones, las notas son, por simplicidad, aleatorias para cada asignatura para 
 * cada estudiante. Se usan para calcular el rendimiento de un curso, profesor o estudiante.
 * 
 */

public class EvaluationEvent implements Serializable {
	
	private static final long serialVersionUID = 7774322636999465955L;
	private final String subject_name;
	
	private final String name;
	private double grade;
	
	public EvaluationEvent(String subname, String evname)
	{
		this.subject_name = subname;
		this.name = evname;	
	}
	
	public void setGrade(float grade)
	{
		this.grade = grade;
	}

	public String getSubject_name() {
		return subject_name;
	}

	public String getName() {
		return name;
	}
	
	public double getGrade()
	{
		return this.grade;
	}

}
