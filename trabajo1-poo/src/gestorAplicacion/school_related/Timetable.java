package gestorAplicacion.school_related;

import java.io.Serializable;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;


public class Timetable implements Serializable
{
	private static final long serialVersionUID = -4150616955235965376L;
	
	public int initial_hour = 8;
	public LinkedHashMap<Day, ArrayList<Subject>> dtable;
	private Course course;
	
	public Timetable(Course course)
	{
		this.course = course;
		dtable = new LinkedHashMap<Day, ArrayList<Subject>>();
		
		dtable.put(Day.Monday, new ArrayList<Subject>());
		dtable.put(Day.Tuesday, new ArrayList<Subject>());
		dtable.put(Day.Wednesday, new ArrayList<Subject>());
		dtable.put(Day.Thursday, new ArrayList<Subject>());
		dtable.put(Day.Friday, new ArrayList<Subject>());
		
		ArrayList<Subject> cs = this.course.getCourse_subjects();
		
		for(ArrayList<Subject> k : this.dtable.values()) // Crear un horario al azar para cada dia del horario.
		{
			while(k.size() != 6) 
			{
				int rand_idx = (int) Math.floor(Math.random() * cs.size()); // Generar un numero al azar entre 0 y el numero de materias que ofrece el curso.
				Subject s = cs.get(rand_idx); // Usar dicho indice para agarrar una de las materias al azar.
				if(Collections.frequency(k, s)  < 2) // La materia no puede aparecer mas de dos veces en el horario.S
				{
					if(Collections.frequency(k, s)  == 1) // Si ya esta una vez.
					{
						int idxofprv = k.indexOf(s);
						k.add(idxofprv, s); // Si la materia ya esta una vez en el horario, insertar el nombre de nuevo al lado del que ya esta.
					}
					
					else
						k.add(s); // Si no esta, simplemente se anade.
				}	
			}
			 // Se sale del loop cuando ya hay 6 materias en el horario (6 materias por dia)
			// Y se setea el horario creado para ese dia.
		}
	}
	
	public int getInitial_hour()
	{
		return this.initial_hour;
	}
	
	public void setInitial_hour(int i)
	{
		this.initial_hour = i;
	}

	public String toString() // Imprime el horario del curso en un formato bonito
	{
		String schedule = "          Monday              Tuesday             Wednesday           Thursday            Friday\n\n";
		
		for(int idx = 0; idx < 6; idx++)
		{
			schedule += "          ";
			for(ArrayList<Subject> ds : this.dtable.values())
			{
				String tname = ((ds.get(idx).getTeacher() == null) ? "NA" : ds.get(idx).getTeacher().getName());
				schedule += tname + new String(new char[20 - tname.length()]).replace("\0", " ");
			}
			schedule += "\n";
			schedule += "          ";
			for(ArrayList<Subject> ds : this.dtable.values())
			{
				String sname = ds.get(idx).getSname();
				schedule += sname + new String(new char[20 - sname.length()]).replace("\0", " ");
			}
			
			schedule += "\n\n";
		}
		return schedule;
	}
}
