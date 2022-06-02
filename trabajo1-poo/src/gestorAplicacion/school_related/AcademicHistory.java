package gestorAplicacion.school_related;

import java.io.Serializable;
import java.util.ArrayList;

public class AcademicHistory implements Serializable {

	private static final long serialVersionUID = -2798526716793187348L;
	
	private String status;
	private boolean active = true;
	private ArrayList<String> registry; // variable que contiene el estatus en el que el estudiante finalizo cada curso.
	
	public AcademicHistory()
	{
		this.setStatus("En progreso"); // La historia academica inicialmente esta en curso, cuando la creas.
		this.registry = new ArrayList<String>();
	}
	
	public void finalizeH(String cregistry)
	{
		this.setStatus("Finalizada");
		this.setActive(false);
		this.registry.add(cregistry);
	}
	
	public void add_registry(String cregistry) // Agrega un nuevo registro al a historia academica.
	{
		this.registry.add(cregistry);
	}
	
	public String dumpHistory()
	{
		if(this.registry.size() == 0)
			return "Historia academica: \nHistoria academica vacia\n";
		
		String s = "Historia academica:\n\n";
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

	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
