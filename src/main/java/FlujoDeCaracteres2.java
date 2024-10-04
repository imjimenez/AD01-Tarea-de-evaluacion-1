
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
		2.	Flujos de caracteres: (BufferedReader, BufferedWriter)
	Crea un programa en Java que lea un archivo con nombres y apellidos separados por espacios, 
	y escriba en un nuevo archivo solo los nombres que tienen exactamente cinco letras.
 */
public class FlujoDeCaracteres2 {

	public static void main(String[] args) {
		// Se define el directorio y los ficheros de origen y destino
		File filesDir = new File("src" + File.separator + "main" + File.separator + "resources" + File.separator);
		File sourceFile = new File(filesDir, "people_list.txt");
		File targetFile = new File(filesDir, "only_names.txt");
		try {
			// Lectura del fichero de origen por lineas. Por cada línea se comprueba si cumple la condición.
			// En caso afirmativo, escribe una nueva línea en el archivo destino
			BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile));
			String line;
			while ((line = reader.readLine()) != null) {
				String name = line.split(" ")[0];
				if(name.length() == 5) {
					writer.write(name);
					writer.newLine();
				}
			}
			reader.close();
			writer.close();
			System.out.printf("Resultado guardado en %s%n", targetFile.getAbsolutePath());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}