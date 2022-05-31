package baseDatos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import uiMain.Main;

import java.io.ObjectOutputStream;

public class Serializer {
	
	private static File f = new File("src/baseDatos/temp/SchoolData.txt");


	public static void serializeAll() throws IOException {
			if (f.exists())
				f.delete();

		FileOutputStream sch = new FileOutputStream(f);

		try {
			ObjectOutputStream ss = new ObjectOutputStream(sch);

			ss.writeObject(Main.school); // Guardar los datos de la escuela
			ss.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
