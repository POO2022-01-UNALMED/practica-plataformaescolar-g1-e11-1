package uiMain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import gestorAplicacion.personas.Student;
import gestorAplicacion.personas.User;
import gestorAplicacion.school_related.Course;

public class Session
{	
	
	public static void studentManagement() throws IOException
	{
		System.out.println("Usted quiere...\n");
		
		System.out.println("1. Inscribir nuevo estudiante");
		System.out.println("2. Consultar estadisticas de estudiante");
		System.out.println("3. Expulsar estudiante");
		System.out.println("4. Salir al menu\n");	
		
		Scanner sc = new Scanner(System.in);
		
		while(true)
		{
			System.out.println("Enter an option: ");
			
			int opt = sc.nextInt();
			
			switch(opt)
			{
				case 1:
					System.out.print("Ingrese el nombre del estudiante (25 caracteres maximo): ");
					String tn = sc.nextLine();
					
					System.out.print("\n");
					
					User existent = null;
					int ti = 0;
					System.out.print("Ingrese la identificacion del estudiante (10 numeros maximo): ");
					
					while(true)
					{
						ti = sc.nextInt(); // Recibe la cedula.
						
						System.out.println();
						
						existent = Student.find(ti); // Buscar la cedula del estudiante en los estudiantes inscritos.
						if(existent != null)
							System.out.print("Ya existe un estudiante con la identificacion ingresada. Ingrese una nueva id:");
						else
							break;			
					}//Para  el input de usuario se asume que el usuario ingresara valores razonables. Identificaciones numericas y nombres alfabeticos. 
					
					User ns = new Student(tn, ti); // Convertir el nombre en string y la cedula a string y luego a int.
					
					System.out.println("Ingrese el tipo de sangre: ");
					String bt = sc.nextLine();
					System.out.println("Ingrese el lugar de nacimiento: ");
					String bp = sc.nextLine();
					System.out.println("Ingrese el dia de nacimiento (DD/MM/AA): ");
					String bd = sc.nextLine();
					System.out.println("Ingrese el genero: ");
					String sex = sc.nextLine();
					
					ns.setAll(bd, bt, bp, sex);
					
					System.out.println("Ahora seleccione el curso: ");
					int idx_c = 1;
					for(Course c : Course.getCreated()) // Muestra al usuario todos los cursos existentes.
					{
						System.out.print(idx_c++ + " ");
						System.out.println(c);
					}
					
					System.out.println("\n");
					System.out.println("Su opcion (numero del curso): ");
					int c_opt;
					
					if(Course.getCreated().size() > 0)
					{
						while(true)
						{
						    c_opt = sc.nextInt();// Lee la opcion del usuario, esta funcion lee un byte, por lo que al leer 1 leera realmente 31.
							if(opt > 0 && opt <= Course.getCreated().size())
								break;
							else
								System.out.println("Numero invalido, ingrese una nueva opcion.");
						}
						
						Course c = Course.getCreated().get(c_opt-1); // El curso que el usuario solicito.
						c.add_student((Student) ns);
					}
						
					System.out.println("Estudiante creado con exito!\n");
					break;
				case 2:
					System.out.println("Ingrese nombres, segundos nombres o apellidos (no es necesario que este completo, se buscaran los match mas cercanos): "); // No es necesario el nombre completo.
				
					String name = sc.nextLine();
					
					ArrayList<Student> found_students = new ArrayList<>();
					for(Student e : Student.getCreated()) // Encuentra todos los estudiantes que dentro de su nombre contengan la string de busqueda. (Filtro)
					{
						if(e.getName().contains(name))
							found_students.add(e);	// Si encuentra un match, lo anade a std, que luego se imprimira al usuario.
					}
					
					int found_idx = 1;
					for(Student e : found_students)
					{
						System.out.print(found_idx++);
						System.out.println(e +"\n");
					}
					
					System.out.println("Seleccione el estudiante que desea consultar: ");
					
					if(found_students.size() > 0) // Si se encontraron estudiantes con ese match
					{
						int sopt = 0;
						while(true)
						{
							sopt = sc.nextInt();
							
							if(sopt > 0 && sopt <= found_students.size())
								break;
							else
								System.out.println("Opcion invalida, por favor ingrese una opcion valida:");
						}
						
						User e = found_students.get(sopt);
						
						/*
						 * 
						 * Imprimir la historia academica del estudiante y sus notas actuales.
						 */
					}
					
					else
						System.out.println("No se encontraron estudiantes con ese nombre/apellido.\n");
					
					break;
				case 3:
					break;
				case 4:
					sc.close();
					return;
				default:
					System.out.println("Opcion no reconocida, por favor ingrese otra opcion.\n");
					break;
			}
		}
	}
	
	public static void teacherManagement() throws IOException
	{
		System.out.println("Usted quiere...\n");
		
		System.out.println("1. Remover profesor (de un curso).");
		System.out.println("2. Despedir profesor.");
		System.out.println("3. Asignar profesor (a un curso y a una materia o materias del curso)");
		System.out.println("3. Salir al menu\n");
		
		Scanner sc = new Scanner(System.in);
		
		while(true)
		{
			System.out.print("Ingrese una opcion: ");
			
			int opt = sc.nextInt();
			
			System.out.println("\n");
			
			switch(opt)
			{
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					sc.close();
					return;
				default:
					System.out.println("Opcion no reconocida, por favor ingrese otra opcion.\n");
					break;
			}
		}
	}
	
	public static void courseManagement() throws IOException {
		System.out.println("Seleccione su accion... \n");

		System.out.println("1. Ver estadisticas de un curso");
		System.out.println("2. Modificar horario de un curso");
		System.out.println("3. Consultar horario de un curso.");
		System.out.println("4. Salir al menu principal\n");
		
		Scanner sc = new Scanner(System.in);
		
		while(true)
		{
			System.out.println("Ingrese una opcion: ");
			
			int opt = sc.nextInt();
			
			switch(opt)
			{
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					sc.close();
					return;
				default:
					System.out.println("Opcion no reconocida, por favor ingrese otra opcion.\n");
					break;
			}
		}
		
		
	}

}
