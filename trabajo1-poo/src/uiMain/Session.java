package uiMain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import gestorAplicacion.personas.Student;
import gestorAplicacion.personas.Teacher;
import gestorAplicacion.personas.User;
import gestorAplicacion.school_related.Course;
import gestorAplicacion.school_related.Day;
import gestorAplicacion.school_related.Subject;

public class Session
{	
	
	public static void studentManagement() throws IOException
	{
		System.out.println("Usted quiere...\n");
			
		while(true)
		{
			System.out.println("1. Inscribir estudiante a un curso");
			System.out.println("2. Consultar estadisticas de estudiante");
			System.out.println("3. Expulsar estudiante");
			System.out.println("4. Salir al menu\n");	
			System.out.print("Ingrese una opcion: ");
			
			int opt = Main.sc.nextInt();
			Main.sc.nextLine();
			
			switch(opt)
			{
				case 1:
					int newstopt;
					System.out.println("1. Inscribir un estudiante nuevo.\n2. Inscribir un estudiante existente.");
					
					newstopt = Main.sc.nextInt();
					Main.sc.nextLine();
					
					if(newstopt == 1) // Crear un nuevo estudiante e inscribirlo a un curso.
					{
					
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
								if(c_opt > 0 && c_opt <= Course.getCreated().size())
									break;
								else
									System.out.println("Numero invalido, ingrese una nueva opcion.");
							}
							
							Course c = Course.getCreated().get(c_opt-1); // El curso que el usuario solicito.
							c.add_student((Student) ns);
						}
							
						System.out.println("Inscripcion de nuevo estudiante finalizada!\n");
					
					}
					
					else if(newstopt == 2) // Buscar en los estudiantes existentes.
					{
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
							System.out.println(e.check_info() +"\n");
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
							
							User es = found_students.get(sopt-1);
							
							if(((Student) es).getAH().isActive())
							{
							
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
										if(c_opt > 0 && c_opt <= Course.getCreated().size())
											break;
										else
											System.out.println("Numero invalido, ingrese una nueva opcion.");
									}
									
									Course c = Course.getCreated().get(c_opt-1); // El curso que el usuario solicito.
									c.add_student((Student) es);
								}
								
								System.out.println("Inscripcion de estudiante finalizada!\n");
							
							}
							
							else
								System.out.println("El estudiante no se encuentra activo dentro del registro.");
						}
					}
					
					else
						System.out.println("Opcion invalida.\n");
					
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
			
		while(true)
		{
			System.out.println("1. Remover profesor (de un curso).");
			System.out.println("2. Despedir profesor.");
			System.out.println("3. Asignar profesor (a un curso y a una materia o materias del curso)");
			System.out.println("4. Estadisticas de profesor. ");
			System.out.println("5. Salir al menu\n");
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
							((Teacher)e).getAssignedCourses().remove(teacher_courses.get(copt-1));
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
					System.out.println("Asignar profesor a una materia (si ya hay un profesor en esa materia, se reemplazara por el nuevo profesor): ");
					System.out.println("Ingrese nombres, segundos nombres o apellidos (no es necesario que este completo, se buscaran los match mas cercanos):");
					
					String paname = Main.sc.nextLine();
					
					ArrayList<Teacher> foundtchs = new ArrayList<>();
					for(Teacher e : Teacher.getCreated()) // Encuentra todos los profesores que dentro de su nombre contengan la string de busqueda. (Filtro)
					{
						if(e.getName().contains(paname))
							foundtchs.add(e);	// Si encuentra un match, lo anade al arreglo de encontrados
					}
					
					int found_i = 1;
					for(Teacher e : foundtchs)
					{
						System.out.print(found_i++ + ". ");
						System.out.println(e +"\n");
					}
					
					System.out.println("Seleccione el profesor a asignar: ");
					
					if(foundtchs.size() > 0) // Si se encontraron profesores con ese match
					{
						int topt = 0;
						while(true)
						{
							topt = Main.sc.nextInt();
							Main.sc.nextLine();
							
							if(topt > 0 && topt <= foundtchs.size())
								break;
							else
								System.out.println("Opcion invalida, por favor ingrese una opcion valida:");
						}
						
						User foundt = foundtchs.get(topt-1);
						
						System.out.println("Seleccione el curso al que desea asignarlo: ");
						int idx_c = 1;
						for(Course c : Course.getCreated()) // Muestra al usuario todos los cursos existentes.
						{
							System.out.print(idx_c++ + ". ");
							System.out.println(c.getCourseName() + "\n");
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
								if(c_opt > 0 && c_opt <= Course.getCreated().size())
									break;
								else
									System.out.println("Numero invalido, ingrese una nueva opcion.");
							}
							
							Course ctoa = Course.getCreated().get(c_opt-1);
							ArrayList<Subject> course_subjects = ctoa.getCourse_subjects(); // Las materias del curso que se selecciono.
							int idxs = 1;
							for(Subject sbj : course_subjects) // Imprimir las materias del curso y dar a elegir al usuario
							{
								System.out.print(idxs++ + ". ");
								System.out.println(sbj);
							}
							
							
							System.out.println("Seleccione la materia a la que desea asignarlo: \n\n");
							int s_opt= 0;
							while(true) // No hay necesidad de rodear este con un if porque el arreglo de materias de un curso nunca esta vacio
							{
							    s_opt = Main.sc.nextInt();// Lee la opcion del usuario, esta funcion lee un solo byte y el "\n" que le sigue hay que leerlo para solventar bugs.
							    Main.sc.nextLine();
								if(s_opt > 0 && s_opt <= course_subjects.size())
									break;
								else
									System.out.println("Numero invalido, ingrese una nueva opcion.");
							}
							
							if(course_subjects.get(s_opt-1).getTeacher() == foundt) // Si la materia ya la dicta este profesor.
								System.out.println("El profesor ya se encuentra asignado a esta materia!\n");
							
							else
							{
								ctoa.add_teacher((Teacher) foundt); // Anadir el profesor al curso
								((Teacher) foundt).getAssignedSubjects().add(course_subjects.get(s_opt-1));
								ctoa.updateSubjects(course_subjects.get(s_opt-1).getSname(), (Teacher) foundt);
							}
						}
					}
					
					else
						System.out.println("No se encontraron profesores con ese nombre/apellido.");
					break;
				case 4:
					System.out.print("Ingrese nombres, segundos nombres o apellidos (no es necesario que este completo, se buscaran los match mas cercanos): ");
					
					String infname = Main.sc.nextLine();
					
					ArrayList<Teacher> found_teacher_inf = new ArrayList<>();
					for(Teacher e : Teacher.getCreated()) // Encuentra todos los profesores que dentro de su nombre contengan la string de busqueda. (Filtro)
					{
						if(e.getName().contains(infname))
							found_teacher_inf.add(e);	// Si encuentra un match, lo anade a std, que luego se imprimira al usuario.
					}
					
					int found_tidx = 1;
					for(Teacher e : found_teacher_inf)
					{
						System.out.print(found_tidx++ + ". ");
						System.out.println(e +"\n");
					}
					
					System.out.println("Seleccione el profesor cuyas estadisticas quiere consultar: ");
					
					if(found_teacher_inf.size() > 0) // Si se encontraron profesores con ese match
					{
						int topt = 0;
						while(true)
						{
							topt = Main.sc.nextInt();
							Main.sc.nextLine();
							
							if(topt > 0 && topt <= found_teacher_inf.size())
								break;
							else
								System.out.println("Opcion invalida, por favor ingrese una opcion valida:");
						}
						
						User e = found_teacher_inf.get(topt-1); // Seleccionar el profesor elegido por el admin.
						System.out.println(((Teacher)e).check_perf()); // Mostrar el rendimiento del profesor.
					}
					
					break;
				case 5:
					return;
				default:
					System.out.println("Opcion no reconocida, por favor ingrese otra opcion.\n");
					break;
			}
		}
	}
	
	public static void courseManagement() throws IOException {
		System.out.println("Seleccione su accion... \n");

		while(true)
		{
			System.out.println("1. Ver estadisticas de un curso");
			System.out.println("2. Modificar horario de un curso");
			System.out.println("3. Consultar horario de un curso.");
			System.out.println("4. Finalizar curso.");
			System.out.println("5. Salir al menu\n");
			System.out.print("Ingrese una opcion: ");
			
			int opt = Main.sc.nextInt();
			Main.sc.nextLine();
			
			switch(opt)
			{
				case 1:
					int id = 1;
					for(Course c: Main.school.created_courses) // Imprimir los cursos.
					{
						System.out.print(id++ + ". ");
						System.out.println(c.getCourseName());
					}
					
					System.out.println("Seleccione el curso que quiere consultar (ingrese el numero): ");
					int curopt = 0;
					
					while(true) 
					{
						curopt = Main.sc.nextInt();
						Main.sc.nextLine();
						
						if(curopt > 0 && curopt <= Main.school.created_courses.size())
							break;
						else
							System.out.print("Opcion invalida ingrese de nuevo una opcion:");
					}
					
					System.out.println(Main.school.created_courses.get(curopt-1).check_perf()); // Imprimir la informacion sobre un curso.
					break;
				case 2:
					
					System.out.println("Ahora modificando el horario de un curso...");
					
					int idc = 1;
					for(Course c: Main.school.created_courses) // Imprimir los cursos.
					{
						System.out.print(idc++ + ". ");
						System.out.println(c.getCourseName());
					}
					
					System.out.println("Seleccione el curso cuyo horario desea modificar (ingrese el numero): ");
					int cur_opt = 0;
					
					while(true) 
					{
						cur_opt = Main.sc.nextInt();
						Main.sc.nextLine();
						
						if(cur_opt > 0 && cur_opt <= Main.school.created_courses.size())
							break;
						else
							System.out.print("Opcion invalida ingrese de nuevo una opcion:");
					}
					
					Course c = Main.school.created_courses.get(cur_opt-1); // Curso elegido para modificar su horario.
					ArrayList<Subject> cs = c.getCourse_subjects(); // Referencia al arreglo de materias del curso.
					
					for(Subject sb : cs) // Imprimir las materias del curso.
					{
						System.out.println((cs.indexOf(sb) + 1) + ". ");
						System.out.println(sb);
					}
					
					ArrayList<Subject> new_day = new ArrayList<Subject>(); // Nuevo horario.
					int picks = 0;
					
					
					while(picks < 6) {
						System.out.println("Seleccione la materia N°" + (picks+1) + " del horario: ");
						while(true)
						{
							int ttopt = Main.sc.nextInt();
							Main.sc.nextLine();
							
							if(ttopt > 0 && ttopt <= cs.size())
							{
								Subject s = cs.get(ttopt-1);
								if(Collections.frequency(new_day, s)  < 2) // La materia no puede aparecer mas de dos veces en el horario.S
								{
									if(Collections.frequency(new_day, s)  == 1) // Si ya esta una vez.
									{
										int idxofprv = new_day.indexOf(s);
										new_day.add(idxofprv, s); // Si la materia ya esta una vez en el horario, insertar el nombre de nuevo al lado del que ya esta.
									}
									
									else
										new_day.add(s); // Si no esta, simplemente se anade.
									
									break;
								}
								
								else
									System.out.print("La materia ya se encuentra 2 veces en la lista, ingrese de nuevo un numero: ");
								
							}
							
							else
								System.out.println("Opcion invalida, ingrese de nuevo el numero de la materia: ");
						}
						
						picks++;
					}
					
					System.out.println("Ahora seleccione el dia para el que quiere modificar el horario (1 lunes, 5 viernes): ");
					Set<Day> days = c.getSchedule().getDaytable().keySet(); // Agarrar el conjunto de keys del horario.
					ArrayList<Day> dlist = new ArrayList<Day>();
					dlist.addAll(days);
					
					while(true)
					{
						int dayopt = Main.sc.nextInt();
						Main.sc.nextLine();
						
						if(dayopt > 0 && dayopt <= 5)
						{
							c.getSchedule().getDaytable().put(dlist.get(dayopt-1), new_day);
							break;
						}
						
						else
							System.out.println("Opcion invalida, vuelva a seleccionar el dia");
						
					}
					
					System.out.println("Modificacion de horario finalizada!");
					break;
				case 3:
					int idch = 1;
					for(Course cc: Main.school.created_courses) // Imprimir los cursos.
					{
						System.out.print(idch++ + ". ");
						System.out.println(cc.getCourseName());
					}
					
					System.out.println("Seleccione el curso cuyo horario desea consultar (ingrese el numero): ");
					int curopt1 = 0;
					
					while(true) 
					{
						curopt1 = Main.sc.nextInt();
						Main.sc.nextLine();
						
						if(curopt1 > 0 && curopt1 <= Main.school.created_courses.size())
							break;
						else
							System.out.print("Opcion invalida ingrese de nuevo una opcion:");
					}
					
					System.out.println(Main.school.created_courses.get(curopt1-1).getSchedule()); // Imprimir el horario del curso seleccionado.
					break;
				case 4:
					int idf = 1;
					for(Course cc: Main.school.created_courses) // Imprimir los cursos.
					{
						System.out.print(idf++ + ". ");
						System.out.println(cc.getCourseName());
					}
					
					System.out.println("Seleccione el curso que quiere finalizar (ingrese el numero): ");
					int curopt2 = 0;
					
					while(true) 
					{
						curopt2 = Main.sc.nextInt();
						Main.sc.nextLine();
						
						if(curopt2 > 0 && curopt2 <= Main.school.created_courses.size())
							break;
						else
							System.out.print("Opcion invalida ingrese de nuevo una opcion:");
					}
					
					Main.school.created_courses.get(curopt2-1).finalizeC(); // Finalizar el horario seleccionado.
					break;
				case 5:
					return;
				default:
					System.out.println("Opcion no reconocida, por favor ingrese otra opcion.\n");
					break;
			}
		}
		
		
	}

}