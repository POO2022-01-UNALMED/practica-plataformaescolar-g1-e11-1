package baseDatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

import uiMain.Main;

import java.io.IOException;

public class Deserializer 
{
	private static File f = new File("src/baseDatos/temp/SchoolData.txt");

	
	public static void deserializeAll() throws FileNotFoundException {

		FileInputStream sch = new FileInputStream(f);


		try {
			ObjectInputStream ss = new ObjectInputStream(sch);

			Main.school = (AllData) ss.readObject();
			ss.close();


		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
