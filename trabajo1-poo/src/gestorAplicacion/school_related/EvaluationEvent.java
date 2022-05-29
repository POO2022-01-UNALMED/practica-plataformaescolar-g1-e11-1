package gestorAplicacion.school_related;

import java.io.Serializable;

public class EvaluationEvent implements Serializable {
	
	private static final long serialVersionUID = 7774322636999465955L;
	private String date;
	private final String subject_name;
	
	private final String name;
	private float grade;
	private final int num;
	
	public EvaluationEvent(String subname, String evname, int no_of_ev)
	{
		this.subject_name = subname;
		this.name = evname;
		this.num = no_of_ev;	
	}
	
	void setDate(String d)
	{
		this.date = d;
	}
	
	void setGrade(float grade)
	{
		this.grade = grade;
	}

	public String getSubject_name() {
		return subject_name;
	}

	public String getName() {
		return name;
	}
	
	public float getGrade()
	{
		return this.grade;
	}

	public int getNum() {
		return num;
	}

}
