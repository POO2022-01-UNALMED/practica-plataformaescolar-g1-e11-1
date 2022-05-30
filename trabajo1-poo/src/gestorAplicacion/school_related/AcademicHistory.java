package gestorAplicacion.school_related;

import java.io.Serializable;
import java.util.ArrayList;

public class AcademicHistory implements Serializable {

	private static final long serialVersionUID = -2798526716793187348L;
	
	private String status;
	private ArrayList<String> registry; // variable que contiene el estatus en el que el estudiante finalizo cada curso.
	
	public AcademicHistory()
	{
		this.setStatus("En progreso");
	}
	
	public void finalizeC(String cregistry)
	{
		this.setStatus("Finalizada");
		this.registry.add(cregistry);
	}
	
	public String dumpHistory()
	{
		if(this.registry.size() == 0)
			return "Historia academica vacia";
		
		String s = "";
		for(String n : this.registry)
			s += n + "\n";
		
		return s;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
