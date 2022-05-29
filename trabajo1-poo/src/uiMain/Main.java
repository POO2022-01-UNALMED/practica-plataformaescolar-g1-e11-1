package uiMain;

import java.io.IOException;
import java.util.Scanner;

import baseDatos.Deserializer;
import baseDatos.Serializer;


public class Main {

	public static void main(String[] args) throws IOException 
	{
		Deserializer.deserializeAll();
		
		System.out.println("Ingresando al control maestro academico...\n");
		System.out.println("Usted desea administrar...");
		
		System.out.println("1. Cursos");
		System.out.println("2. Profesores");
		System.out.println("3. Estudiantes\n");
		
		System.out.print("Ingrese su opcion: ");
		
		while(true)
		{
			Scanner sc = new Scanner(System.in);
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
					Serializer.serializeAll();
					sc.close();
					System.exit(0);
					break;
				default:
					System.out.println("Invalid option, please re-enter your option.\n");
					break;
			}
			
			
		}
		
	}

}
