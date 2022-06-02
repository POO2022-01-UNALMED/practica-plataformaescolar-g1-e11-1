package uiMain;

import java.io.IOException;
import java.util.Scanner;

import baseDatos.AllData;
import baseDatos.Deserializer;
import baseDatos.Serializer;
import gestorAplicacion.personas.Student;
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
		
//		Course c1 = new Course("Sexto");
//		Course c2 = new Course("Septimo");
//		Course c3 = new Course("Octavo");
//		Course c4 = new Course("Noveno");
//		Course c5 = new Course("Decimo");
//		Course c6 = new Course("Once");
//
//		User t1 = new Teacher("Fulano Perez", 4520);
//		User t2 = new Teacher("Juancho Gomez", 2320);
//		User t3 = new Teacher("Miguel Suarez", 2103);
//		User t4 = new Teacher("Javier Estefano", 5000);
//		User t5 = new Teacher("Nancy Soto", 6000);
//		User t6 = new Teacher("Nicolas Benitez", 1132);
//		User t7 = new Teacher("Pedro Dominguez", 9132);
//		User t8 = new Teacher("Tulio Assia", 9232);
//		User t9 = new Teacher("Paulino Vega", 9036);
//		User t10 = new Teacher("Diovis Ramirez", 2032);
//		User t11 = new Teacher("Javier Robles", 1062);
//		User t12 = new Teacher("Nelly Teheran", 9432);
//		User t13 = new Teacher("Carlos Martinez", 4032);
//		User t14 = new Teacher("Humberto Gomez", 2455);
//		User t15 = new Teacher("Patrix Fox", 4735);
//		
//    	User s1 = new Student("Javier Silva", 4520);
//    	User s2 = new Student("Keder Madera", 2320);
//    	User s3 = new Student("Pacho Gomez", 2103);
//		User s4 = new Student("Dilan Arias", 4126);
//		User s5 = new Student("Brian Buelvas", 8032);
//		User s6 = new Student("Omar Hernandez", 6339);
//		User s7 = new Student("Luis Paternina", 7432);
//		User s8 = new Student("Julio Dario", 9072);
//		User s9 = new Student("Tatis Hernandez", 9732);
//		User s10 = new Student("Fabiana Baron", 2072);
//		User s11 = new Student("Isabella Diaz", 5032);
//		User s12 = new Student("Isabella Torrente", 1032);
//		User s13 = new Student("Estiven Ramirez", 7036);
//		User s14 = new Student("Laureano Romero", 9032);
//		User s15 = new Student("Julio Amador", 3451);
//		User s16 = new Student("Juan Urrego", 4565);
//		User s17 = new Student("Juan Vides", 4444);
//		User s18 = new Student("Juan Gomez", 1111);
//		User s19 = new Student("Juan Vergara", 1112);
//		User s20 = new Student("Diego Ortiz", 2213);
//		User s21 = new Student("Carlos Esteban", 6677);
//    	User s22 = new Student("Daniel Pinto", 8855);
//    	User s23 = new Student("Jesus Esmeral", 3331);
//		User s24 = new Student("Danilo Martinez", 1134);
//		User s25 = new Student("Mateo Perez", 6969);
//		User s26 = new Student("Jassif Assia", 2252);
//		User s27 = new Student("Edwin Navarro", 7651);
//		User s28 = new Student("Gabriela Melgarejo", 1239);
//		User s29 = new Student("Eucaris Meza", 9876);
//		User s30 = new Student("Maria Mendez", 8765);
//		User s31 = new Student("Maria Zambrano", 7654);
//		User s32 = new Student("Maria Fernandez", 6543);
//		User s33 = new Student("Nathaly Diaz", 7117);
//		User s34 = new Student("Maria Santos", 1200);
//		User s35 = new Student("Maria Mora", 1000);
//		User s36 = new Student("Sofia Borre", 4100);
//		User s37 = new Student("Sofia Gomez", 1400);
//		User s38 = new Student("Maria Nunez", 6661);
//		User s39 = new Student("Sharon Socarras", 6161);
//		User s40 = new Student("Alfredo Salas", 9090);
			
		System.out.println("Ingresando al control maestro academico...\n");
		System.out.println("Usted desea administrar...");
		
		
		while(true)
		{
			System.out.println("1. Cursos");
			System.out.println("2. Profesores");
			System.out.println("3. Estudiantes");
			System.out.println("4. Salir y guardar cambios\n");
			
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
