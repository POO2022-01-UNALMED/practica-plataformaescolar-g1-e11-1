package baseDatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import gestorAplicacion.personas.Student;
import gestorAplicacion.personas.Teacher;
import gestorAplicacion.school_related.Course;

import java.io.IOException;

public class Deserializer 
{
	private static ArrayList<File> filenames = new ArrayList<File>();
	static {
		filenames.add(new File("src/baseDatos/temp/Students.txt"));
		filenames.add(new File("src/baseDatos/temp/Teachers.txt"));
		filenames.add(new File("src/baseDatos/temp/Courses.txt"));
	}

	@SuppressWarnings("unchecked")
	public static void deserializeAll() throws FileNotFoundException {

		FileInputStream students = new FileInputStream(Deserializer.filenames.get(0));
		FileInputStream teachers = new FileInputStream(Deserializer.filenames.get(1));
		FileInputStream courses = new FileInputStream(Deserializer.filenames.get(2));

		try {
			ObjectInputStream ss = new ObjectInputStream(students);
			ObjectInputStream st = new ObjectInputStream(teachers);
			ObjectInputStream sc = new ObjectInputStream(courses);

			Student.setCreated((ArrayList<Student>) ss.readObject());
			Teacher.setCreated((ArrayList<Teacher>) st.readObject());
			Course.setCreated((ArrayList<Course>) sc.readObject());

			ss.close();
			st.close();
			sc.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
