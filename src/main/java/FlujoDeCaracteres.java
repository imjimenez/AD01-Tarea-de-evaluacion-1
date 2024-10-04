
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* 
		1.	Flujos de caracteres: (FileReader, FileWriter)
	Escribe un programa en Java que lea una línea de texto desde un archivo y devuelva la misma línea,
	pero con las palabras invertidas individualmente. 
	Es decir, si el archivo contiene "Hola Mundo", el archivo de salida debería contener "aloH odnuM".
 */
public class FlujoDeCaracteres {

	public static void main(String[] args) {
		// Se define el directorio y los ficheros de origen y destino
		File filesDir = new File("src" + File.separator + "main" + File.separator + "resources" + File.separator);
		File sourceFile = new File(filesDir, "text_to_reverse.txt");
		File targetFile = new File(filesDir, "reversed.txt");
		try {
			// Lectura del fichero de origen y se almacena el contenido invertido.
			FileReader reader = new FileReader(sourceFile);
			String reversedSentence = "";
			int character;
			while ((character = reader.read()) != -1) {
				reversedSentence = (char) character + reversedSentence;
			}
			reader.close();

			// Escritura del resultado previo en el archivo destino
			FileWriter writer = new FileWriter(targetFile);
			writer.write(reversedSentence);
			writer.close();
			System.out.printf("Resultado guardado en %s%n", targetFile.getAbsolutePath());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}