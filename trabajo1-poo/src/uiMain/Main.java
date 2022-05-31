package uiMain;

import java.io.IOException;
import java.util.Scanner;

import baseDatos.AllData;
import baseDatos.Deserializer;
import baseDatos.Serializer;
import gestorAplicacion.personas.Teacher;
import gestorAplicacion.personas.User;
import gestorAplicacion.school_related.Course;
import gestorAplicacion.school_related.Day;
import gestorAplicacion.school_related.Timetable;

public class Main {
	
	public static Scanner sc = new Scanner(System.in);
	public static AllData school;

	public static void main(String[] args) throws IOException 
	{
		school = new AllData();
		
		Deserializer.deserializeAll();
//		Course c1 = new Course("Octavo");
//		Course c2 = new Course("Noveno");
////
//		User t1 = new Teacher("Fulano Perez", 4520);
//		User t2 = new Teacher("Juancho Gomez", 2320);
//		User t3 = new Teacher("Miguel Suarez", 2103);
//		User t4 = new Teacher("Javier Bolanos", 9032);
		
		System.out.print(school.created_courses.get(0).getSchedule());
		
		System.out.println("Ingresando al control maestro academico...\n");
		System.out.println("Usted desea administrar...");
		
		System.out.println("1. Cursos");
		System.out.println("2. Profesores");
		System.out.println("3. Estudiantes");
		System.out.println("4. Salir y guardar cambios\n");
				
		while(true)
		{
			
			System.out.print("Ingrese su opcion: ");
			int opt = sc.nextInt();
			System.out.println("");
			
			switch (opt)
			{
				case 1:
					System.out.println("Ahora administrando cursos... \n");
					Session.courseManagement();
					break;
				case 2:
					System.out.println("Ahora administrando profesores... \n");
					Session.teacherManagement();
					break;
				case 3:
					System.out.println("Ahora administrando estudiantes... \n");
					Session.studentManagement();
					break;
				case 4:
					System.out.println("Saliendo...");
					Serializer.serializeAll(); // PARA SERIALIZAR LOS CAMBIOS HAY QUE SALIR CORRECTAMENTE DEL PROGRAMA, UN CIERRE BRUSCO NO GUARDARA CAMBIOS!.
					sc.close();
					System.exit(0);
					break;
				default:
					System.out.println("Opcion invalida, por favor reingrese una opcion.\n");
					break;
			}
			
			
		}
		
	}

}
