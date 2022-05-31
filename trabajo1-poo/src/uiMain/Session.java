package uiMain;

import java.io.IOException;
import java.util.ArrayList;
import gestorAplicacion.personas.Student;
import gestorAplicacion.personas.Teacher;
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
			
		while(true)
		{
			System.out.print("Ingrese una opcion: ");
			
			int opt = Main.sc.nextInt();
			Main.sc.nextLine();
			
			switch(opt)
			{
				case 1:
					System.out.print("Ingrese el nombre del estudiante (25 caracteres maximo): ");
					String tn = Main.sc.nextLine();
					
					System.out.print("\n");
					
					User existent = null;
					int ti = 0;
					System.out.print("Ingrese la identificacion del estudiante (10 numeros maximo): ");
					
					while(true)
					{
						ti = Main.sc.nextInt(); // Recibe la cedula.
						Main.sc.nextLine(); // Despues de cada nextInt hay que colocar un nextLine para agarrar el "\n" que no se lleva.
						
						System.out.println();
						
						if(Student.getCreated().size() > 0)
						{
							existent = Student.find(ti); // Buscar la cedula del estudiante en los estudiantes inscritos.
						}
						if(existent != null)
							System.out.print("Ya existe un estudiante con la identificacion ingresada. Ingrese una nueva id:");
						else
							break;			
					}//Para  el input de usuario se asume que el usuario ingresara valores razonables. Identificaciones numericas y nombres alfabeticos. 
					
					User ns = new Student(tn, ti); // Convertir el nombre en string y la cedula a string y luego a int.
					
					System.out.print("Ingrese el tipo de sangre: ");
					String bt = Main.sc.nextLine();
					System.out.print("Ingrese el lugar de nacimiento: ");
					String bp = Main.sc.nextLine();
					System.out.print("Ingrese el dia de nacimiento (DD/MM/AA): ");
					String bd = Main.sc.nextLine();
					System.out.print("Ingrese el genero: ");
					String sex = Main.sc.nextLine();
					
					ns.setAll(bd, bt, bp, sex);
					
					System.out.println("Ahora seleccione el curso: ");
					int idx_c = 1;
					for(Course c : Course.getCreated()) // Muestra al usuario todos los cursos existentes.
					{
						System.out.print(idx_c++ + ". ");
						System.out.println(c);
					}
					
					System.out.println("\n");
					System.out.println("Su opcion (numero del curso): ");
					int c_opt;
					
					if(Course.getCreated().size() > 0)
					{
						while(true)
						{
						    c_opt = Main.sc.nextInt();// Lee la opcion del usuario, esta funcion lee un byte, por lo que al leer 1 leera realmente 31.
						    Main.sc.nextLine();
							if(opt > 0 && opt <= Course.getCreated().size())
								break;
							else
								System.out.println("Numero invalido, ingrese una nueva opcion.");
						}
						
						Course c = Course.getCreated().get(c_opt-1); // El curso que el usuario solicito.
						c.add_student((Student) ns);
					}
						
					System.out.println("Inscripcion de estudiante finalizada!\n");
					break;
				case 2:
					System.out.println("Ingrese nombres, segundos nombres o apellidos (no es necesario que este completo, se buscaran los match mas cercanos): "); // No es necesario el nombre completo.
				
					String name = Main.sc.nextLine();
					
					ArrayList<Student> found_students = new ArrayList<>();
					for(Student e : Student.getCreated()) // Encuentra todos los estudiantes que dentro de su nombre contengan la string de busqueda. (Filtro)
					{
						if(e.getName().contains(name))
							found_students.add(e);	// Si encuentra un match, lo anade a std, que luego se imprimira al usuario.
					}
					
					int found_idx = 1;
					for(Student e : found_students)
					{
						System.out.print(found_idx++ + ". ");
						System.out.println(e +"\n");
					}
					
					System.out.println("Seleccione el estudiante que desea consultar: ");
					
					if(found_students.size() > 0) // Si se encontraron estudiantes con ese match
					{
						int sopt = 0;
						while(true)
						{
							sopt = Main.sc.nextInt();
							Main.sc.nextLine();
							
							if(sopt > 0 && sopt <= found_students.size())
								break;
							else
								System.out.println("Opcion invalida, por favor ingrese una opcion valida:");
						}
						
						User e = found_students.get(sopt-1);
						
						System.out.println(e.check_perf()); // Imprimir los datos de ese estudiante.
					}
					
					else
						System.out.println("No se encontraron estudiantes con ese nombre/apellido.\n");
					
					break;
				case 3:
					System.out.println("Ingrese nombres, segundos nombres o apellidos (no es necesario que este completo, se buscaran los match mas cercanos): \n");
					
					String sn = Main.sc.nextLine();

					ArrayList<Student> found = new ArrayList<>();
					for (Student e : Student.getCreated()) // Encuentra todos los estudiantes que dentro de su nombre
															// contengan la string de busqueda. (Filtro)
					{
						if (e.getName().contains(sn))
							found.add(e); // Si encuentra un match, lo anade a std, que luego se imprimira al
													// usuario.
					}

					int found_i = 1;
					for (Student e : found) {
						System.out.print(found_i++ + ". ");
						System.out.println(e + "\n");
					}

					System.out.println("Seleccione el estudiante que recibira la accion punitiva: ");
					
					if(found.size() > 0) // Si se encontraron estudiantes con ese match
					{
						int sopt = 0;
						while(true)
						{
							sopt = Main.sc.nextInt();
							
							if(sopt > 0 && sopt <= found.size())
								break;
							else
								System.out.println("Opcion invalida, por favor ingrese una opcion valida:");
						}
						
						User e = found.get(sopt-1);
						
						e.kick();
					}
					
					else
						System.out.println("No se encontraron estudiantes con ese nombre/apellido.\n");
					
					break;
				case 4:
					System.out.println("Regresando al menu principal...\n");
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
		System.out.println("4. Salir al menu\n");
		
		while(true)
		{
			System.out.print("Ingrese una opcion: ");
			
			int opt = Main.sc.nextInt();
			Main.sc.nextLine();
			
			System.out.println("\n");
			
			switch(opt)
			{
				case 1:
					System.out.println("Usted va a remover a un profesor de un curso. Esto dejara a todas las materias del curso que el profesor");
					System.out.println("dicta sin un profesor asignado\n");
					
					System.out.print("Ingrese nombres, segundos nombres o apellidos (no es necesario que este completo, se buscaran los match mas cercanos): ");
					
					String name = Main.sc.nextLine();
					
					ArrayList<Teacher> found_teachers = new ArrayList<>();
					for(Teacher e : Teacher.getCreated()) // Encuentra todos los profesores que dentro de su nombre contengan la string de busqueda. (Filtro)
					{
						if(e.getName().contains(name))
							found_teachers.add(e);	// Si encuentra un match, lo anade a std, que luego se imprimira al usuario.
					}
					
					int found_idx = 1;
					for(Teacher e : found_teachers)
					{
						System.out.print(found_idx++ + ". ");
						System.out.println(e +"\n");
					}
					
					System.out.println("Seleccione el profesor a remover: ");
					
					if(found_teachers.size() > 0) // Si se encontraron profesores con ese match
					{
						int topt = 0;
						while(true)
						{
							topt = Main.sc.nextInt();
							Main.sc.nextLine();
							
							if(topt > 0 && topt <= found_teachers.size())
								break;
							else
								System.out.println("Opcion invalida, por favor ingrese una opcion valida:");
						}
						
						User e = found_teachers.get(topt-1); // Seleccionar el profesor elegido por el admin.
						ArrayList<Course> teacher_courses = ((Teacher)e).getAssignedCourses();
						
						if(teacher_courses.size() > 0)
						{
							int found_crs = 1;
							for(Course cs : teacher_courses) // Iterar los cursos del profesor e imprimirlos
							{
								System.out.print(found_crs++ + ". ");
								System.out.println(cs +"\n");
							}
							
							System.out.println("Seleccione el curso del cual quiere remover al profesor.");
							
							int copt = 0;
							while(true)
							{
								copt = Main.sc.nextInt();
								Main.sc.nextLine();
								
								if(copt > 0 && copt <= found_teachers.size())
									break;
								else
									System.out.println("Opcion invalida, por favor ingrese una opcion valida:");
							}
							
							teacher_courses.get(copt-1).rmvTeacher((Teacher) e); // Llama a la funcion que quita al profesor del curso especifico.
							((Teacher) e).rmvSubjects(teacher_courses.get(copt-1)); // Quita las asignaturas que dictaba el profesor en el curso de su arreglo de asignaturas asignadas.
						}
						
						else
							System.out.println("El profesor no se encuentra en ningun curso.");
					}
					
					else
						System.out.println("No se encontraron profesores con ese nombre/apellido.\n");
					break;
				case 2:
					System.out.println("Usted va a expulsar un profesor de la escuela, esto dejara a todas las materias que");
					System.out.println("dicta sin un profesor asignado\n");
					
					System.out.print("Ingrese nombres, segundos nombres o apellidos (no es necesario que este completo, se buscaran los match mas cercanos): ");
					
					String pname = Main.sc.nextLine();
					
					ArrayList<Teacher> matches = new ArrayList<>();
					for(Teacher e : Teacher.getCreated()) // Encuentra todos los profesores que dentro de su nombre contengan la string de busqueda. (Filtro)
					{
						if(e.getName().contains(pname))
							matches.add(e);	// Si encuentra un match, lo anade a std, que luego se imprimira al usuario.
					}
					
					int found_ix = 1;
					for(Teacher e : matches)
					{
						System.out.print(found_ix++ + ". ");
						System.out.println(e +"\n");
					}
					
					System.out.println("Seleccione el profesor a remover: ");
					
					if(matches.size() > 0) // Si se encontraron profesores con ese match
					{
						int topt = 0;
						while(true)
						{
							topt = Main.sc.nextInt();
							Main.sc.nextLine();
							
							if(topt > 0 && topt <= matches.size())
								break;
							else
								System.out.println("Opcion invalida, por favor ingrese una opcion valida:");
						}
						
						User t = matches.get(topt-1);
						t.kick();
					}
					
					else
						System.out.println("No se encontraron profesores con ese nombre/apellido.");
					break;
				case 3:
					break;
				case 4:
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
		System.out.println("4. Salir al menu\n");
		
		while(true)
		{
			System.out.print("Ingrese una opcion: ");
			
			int opt = Main.sc.nextInt();
			Main.sc.nextLine();
			
			switch(opt)
			{
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					return;
				default:
					System.out.println("Opcion no reconocida, por favor ingrese otra opcion.\n");
					break;
			}
		}
		
		
	}

}