package gestorAplicacion.personas;

import java.io.Serializable;

import gestorAplicacion.school_related.InfoOperations;



public abstract class User implements Serializable, InfoOperations {
	
	private static final long serialVersionUID = -7789239239658109022L;
	protected final String name; // Nombre de usuario
	protected final int identification; // Cedula o CC
	protected String birthdate; // Fecha de nacimiento
	protected String sex; // Genero
	protected String bloodtype; // Tipo de sangre
	protected String birthplace; // Lugar de nacimiento
	
	User(String name, int id) // Si el usuario no especifica sexo y fecha de nacimiento.
	{
		this(name, id, "ND", "ND", "ND", "ND");
	}
	
	User(String name, int id, String bd, String sex, String bloodtype, String birthplace)
	{
		this.name = name;
		this.identification = id;
		this.birthdate = bd;
		this.sex = sex;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getIdentification() {
		return identification;
	}

	public String getBirthdate() {
		return birthdate;
	}
	
	public String getSex()
	{
		return this.sex;
	}
	
	public String getBirthplace()
	{
		return this.birthplace;
	}
	
	public String getBloodtype()
	{
		return this.bloodtype;
	}
	
	public void setBirthdate(String bd)
	{
		this.birthdate = bd;
	}
	
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	
	public void setBirthplace(String bp)
	{
		this.birthplace = bp;
	}
	
	public void setBloodtype(String bt)
	{
		this.bloodtype = bt;
	}
	
	public void setAll(String bd, String bt, String bp, String sex)
	{
		this.birthdate = bd;
		this.bloodtype = bt;
		this.birthplace = bp;
		this.sex = sex;
	}
	
	public abstract String check_perf();
	public abstract String toString();
	public abstract void kick();
	
	
}
