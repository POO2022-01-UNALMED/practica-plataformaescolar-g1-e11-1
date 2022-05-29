package gestorAplicacion.school_related;

import java.io.Serializable;
import java.util.ArrayList;

public enum Day implements Serializable
{
	Monday, Tuesday, Wednesday, Thursday, Friday;
	private ArrayList<String> subjects;
	
	public void setSubjects(ArrayList<String> sbj)
	{
		this.subjects = sbj;
	}
	
	public ArrayList<String> getSubjects()
	{
		return this.subjects;
	}
}