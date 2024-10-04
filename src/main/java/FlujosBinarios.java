
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/*
 		3.	Flujos binarios: (InputStream)
	Realiza un programa en Java que lea la cabecera de un fichero PDF y verifique si realmente se trata de un archivo PDF válido. 
	Para que un archivo sea un PDF válido, debe comenzar con la siguiente secuencia de bytes: {37, 80, 68, 70}. 
	Si la cabecera no coincide con esta secuencia, el programa debe informar al usuario de que el archivo no es válido.
 */
public class FlujosBinarios {
	private static final byte[] PDF_HEADER_BYTES = { 37, 80, 68, 70 };

	public static void main(String[] args) {
		File filesDir = new File("src" + File.separator + "main" + File.separator + "resources" + File.separator);
		File sourceFile = new File(filesDir, "example.pdf");
		try {
			FileInputStream stream = new FileInputStream(sourceFile);
			byte[] header = new byte[4];
			stream.read(header);
			stream.close();
			for (int i = 0; i < PDF_HEADER_BYTES.length; i++) {
				if (header[i] != PDF_HEADER_BYTES[i]) {
					System.out.println("El fichero NO es un PDF válido.");
					return;
				}
			}
			System.out.println("El fichero es un PDF válido.");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}