package baseDatos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import gestorAplicacion.personas.Student;
import gestorAplicacion.personas.Teacher;
import gestorAplicacion.school_related.Course;

import java.io.ObjectOutputStream;

public class Serializer {
	private static ArrayList<File> filenames = new ArrayList<File>();
	static {
		filenames.add(new File("src/baseDatos/temp/Students.txt"));
		filenames.add(new File("src/baseDatos/temp/Teachers.txt"));
		filenames.add(new File("src/baseDatos/temp/Courses.txt"));
	}

	public static void serializeAll() throws IOException {
		for (File f : Serializer.filenames) {
			if (f.exists())
				f.delete();
		}

		FileOutputStream students = new FileOutputStream(Serializer.filenames.get(0));
		FileOutputStream teachers = new FileOutputStream(Serializer.filenames.get(1));
		FileOutputStream courses = new FileOutputStream(Serializer.filenames.get(2));

		try {
			ObjectOutputStream ss = new ObjectOutputStream(students);
			ObjectOutputStream st = new ObjectOutputStream(teachers);
			ObjectOutputStream sc = new ObjectOutputStream(courses);

			ss.writeObject(Student.getCreated());
			st.writeObject(Teacher.getCreated());
			sc.writeObject(Course.getCreated());

			ss.close();
			st.close();
			sc.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
