package gestorAplicacion.school_related;

import java.io.Serializable;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;

public class Timetable implements Serializable
{
	private static final long serialVersionUID = -4150616955235965376L;
	
	public int initial_hour = 8;
	private ArrayList<Day> dtable;
	private Course course;
	
	public Timetable(Course course)
	{
		this.course = course;
		
		dtable.add(Day.Monday);
		dtable.add(Day.Tuesday);
		dtable.add(Day.Wednesday);
		dtable.add(Day.Thursday);
		dtable.add(Day.Friday);
		
		ArrayList<Subject> cs = this.course.getCourse_subjects();
		
		for(Day d : dtable) // Crear un horario al azar para cada dia del horario.
		{
			ArrayList<String> day_sch = new ArrayList<String>(); // Crear un arreglo de strings nuevo.
			while(day_sch.size() != 6) 
			{
				int rand_idx = (int) Math.floor(Math.random() * cs.size()); // Generar un numero al azar entre 0 y el numero de materias que ofrece el curso.
				String s = cs.get(rand_idx).getSname(); // Usar dicho indice para agarrar una de las materias al azar.
				if(Collections.frequency(day_sch, s)  < 2) // La materia no puede aparecer mas de dos veces en el horario.S
				{
					if(Collections.frequency(day_sch, s)  == 1) // Si ya esta una vez.
					{
						int idxofprv = day_sch.indexOf(s);
						day_sch.add(idxofprv, s); // Si la materia ya esta una vez en el horario, insertar el nombre de nuevo al lado del que ya esta.
					}
					
					day_sch.add(s); // Si no esta, simplemente se anade.
				}	
			}
			
			d.setSubjects(day_sch); // Se sale del loop cuando ya hay 6 materias en el horario (6 materias por dia)
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
	
	public int setDayTime(int idx, ArrayList<String> sbjlist)
	{
		Day td = this.dtable.get(idx);
		if(sbjlist.size() == 6)
			td.setSubjects(sbjlist);
		else
			return -1; // Retorna -1 si no.
		
		return 1; // Retorna 1 si la lista tiene el tamano apropiado (6 materias por dia)
	}
}
