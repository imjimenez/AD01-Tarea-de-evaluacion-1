
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

/*
		4.	Acceso Aleatorio: (RandomAccessFile)
	Desde la editorial Marvel te han contratado para hacer una aplicación que gestione los datos de sus superhéroes y supervillanos. 
	Para almacenar la información han decidido utilizar ficheros de distintas clases.
	a). Realiza un programa en java para guardar datos de personajes en un fichero aleatorio, dale el nombre Marvel.dat. 
	Introduce la información de los personajes a partir de los arrays que se te proporcionan en la plataforma Moodle. 
	Cuando termine de realizar la carga de datos deberá informar al usuario de que la carga se ha realizado satisfactoriamente o no. 
	b). Desde la editorial quieren tener controlado el peso de sus personajes, ya que últimamente han hecho algún exceso que otro. 
	Realiza un programa en java que te permita modificar los datos de un personaje. 
	El programa recibe desde la línea de comandos el dni y el peso del último mes. 
	Si el personaje no existe devolverá un mensaje de error, sino mostrará en la consola el nombre del personaje y cuantos kilos ha engordado, adelgazado o si se mantiene en su peso.
	c) Realiza un programa en Java que te permita visualizar los personajes de un tipo concreto (héroe o villano). 
	El programa recibe desde la línea de comandos el tipo de personaje y visualiza cuantos personajes hay de dicho tipo y todos los datos de dichos personajes. 
	Verifica que exista dicho tipo en el fichero, si no existe saca un mensaje de error por pantalla
*/
public class AccesoAleatorio {
	private static File dataFile = new File(
			"src" + File.separator + "main" + File.separator + "resources" + File.separator + "Marvel.dat");
	private static Scanner input = new Scanner(System.in);
	private static ArrayList<MarvelCharacter> marvelCharacters = new ArrayList<MarvelCharacter>();

	public static void main(String[] args) {
		try {
			loadInitialData();
		} catch (IOException ex) {
			System.out.println("Se ha producido un error durante la carga de los personajes.");
			return;
		}

		while (true) {
			int opt = selectOpt();
			switch (opt) {
			case 1:
				modifyCharacterWeight();
				break;
			case 2:
				findCharactersByType();
				break;
			case 3:
				System.out.println("\n¡Hasta la próxima!");
				input.close();
				System.exit(0);
				break;
			default:
				System.out.println("Operación no válida.");
			}

		}

	}

	// Si ya existe un fichero Marvel.dat cargamos los datos del mismo,
	// si no inicializamos los datos y creamos el fichero.
	public static void loadInitialData() throws IOException {
		if (dataFile.exists()) {
			System.out.println("Cargando datos del fichero " + dataFile.getName() + "...");
			int pos = 0;
			RandomAccessFile file = new RandomAccessFile(dataFile, "rw");
			while (file.getFilePointer() != file.length()) {
				int id = file.readInt();
				String dni = readChars(file, 9);
				String name = readChars(file, 10);
				String secretIdentity = readChars(file, 20);
				String type = readChars(file, 10);
				int weight = file.readInt();
				int height = file.readInt();
				MarvelCharacter character = new MarvelCharacter(id, dni, name, secretIdentity, type, weight, height);
				marvelCharacters.add(character);
				pos = pos + 110;
				file.seek(pos);
			}
			file.close();
		} else {
			System.out.println("Cargando datos iniciales...");
			int[] ids = { 1, 2, 3, 4, 5, 6, 7 };
			String[] dnis = { "01010101A", "03030303C", "05050505E", "07070707G", "02020202B", "04040404D",
					"06060606F" };
			String[] noms = { "Spiderman", "Green Goblin", "Storm", "Wolverine", "Mystique", "IronMan", "Mandarin" };
			String[] identidades = { "Peter Parker", "Norman Osborn", "Ororo Munroe", "James Howlett",
					"Raven Darkholme", "Tony Stark", "Zhang Tong" };
			String[] tipos = { "heroe", "villano", "heroe", "heroe", "villano", "heroe", "villano" };
			int[] pesos = { 76, 84, 66, 136, 78, 102, 70 };
			int[] alturas = { 178, 183, 156, 152, 177, 182, 188 };
			StringBuffer buffer = null;
			RandomAccessFile file = new RandomAccessFile(dataFile, "rw");
			for (int idx = 0; idx < ids.length; idx++) {
				int id = ids[idx];
				String dni = dnis[idx];
				String name = noms[idx];
				String secretIdentity = identidades[idx];
				String type = tipos[idx];
				int weight = pesos[idx];
				int height = alturas[idx];
				MarvelCharacter character = new MarvelCharacter(id, dni, name, secretIdentity, type, weight, height);

				file.writeInt(character.id);

				buffer = new StringBuffer(character.dni);
				buffer.setLength(9);
				file.writeChars(buffer.toString());

				buffer = new StringBuffer(character.name);
				buffer.setLength(10);
				file.writeChars(buffer.toString());

				buffer = new StringBuffer(character.secretIdentity);
				buffer.setLength(20);
				file.writeChars(buffer.toString());

				buffer = new StringBuffer(character.type);
				buffer.setLength(10);
				file.writeChars(buffer.toString());

				file.writeInt(character.weight);

				file.writeInt(character.height);

				marvelCharacters.add(character);
			}

			file.close();
		}
		System.out.println("La carga de los personajes ha terminado correctamente.\n");

	}

	public static int selectOpt() {
		int selection;

		System.out.println("Seleccione una operación");
		System.out.println("-------------------------");
		System.out.println("1 - Modificar peso de un personaje");
		System.out.println("2 - Buscar personajes por tipo");
		System.out.println("3 - Salir\n");

		System.out.print("Introduzca la operación: ");
		selection = input.nextInt();
		return selection;
	}

	public static void modifyCharacterWeight() {
		System.out.println("Introduzca el DNI (con letra) del personaje para el control de peso:");
		String dni = input.next();

		MarvelCharacter selectedCharacter = null;
		for (MarvelCharacter c : marvelCharacters) {
			if (c.dni.toLowerCase().trim().equals(dni.toLowerCase().trim())) {
				selectedCharacter = c;
				break;
			}
		}
		if (selectedCharacter == null) {
			System.out.println("No existe ningún personaje con ese DNI.");
			return;
		}

		System.out.println("\nIntroduzca su peso actual: ");
		int newWeight = input.nextInt();
		int weightDiff = newWeight - selectedCharacter.weight;
		try {
			int characterIndex = marvelCharacters.indexOf(selectedCharacter);

			RandomAccessFile file = new RandomAccessFile(dataFile, "rw");
			file.seek((characterIndex * 110) + 102); // Nos posicionamos en el peso del personaje
			file.writeInt(newWeight);
			file.close();
			selectedCharacter.weight = newWeight;
		} catch (IOException ex) {
			System.out.println("Se ha producido un error al actualizar el peso de " + selectedCharacter.name);
			return;
		}
		System.out.printf("%s ", selectedCharacter.name);
		if (weightDiff == 0) {
			System.out.println("se mantiene en su peso anterior");
		} else if (weightDiff < 0) {
			System.out.println("ha adelgazado " + Math.abs(weightDiff) + " kilos");
		} else {
			System.out.println("ha engordado " + (weightDiff) + " kilos");
		}
	}

	public static void findCharactersByType() {
		System.out.print("\nIntroduce un tipo de personaje: ");
		String searchType = input.next();

		ArrayList<MarvelCharacter> charactersPerType = new ArrayList<MarvelCharacter>();
		for (MarvelCharacter c : marvelCharacters) {
			if (c.type.toLowerCase().trim().equals(searchType.toLowerCase().trim())) {
				charactersPerType.add(c);
			}
		}
		if (charactersPerType.size() == 0) {
			System.out.println("No existen " + searchType + "s en el fichero");
			return;
		}

		System.out.println("Se han encontrado " + charactersPerType.size() + " " + searchType + ".");
		for (MarvelCharacter character : charactersPerType) {
			System.out.println(character);
		}
	}

	public static String readChars(RandomAccessFile file, int length) throws IOException {
		String result = "";
		for (int i = 0; i < length; i++) {
			result = result + file.readChar();
		}
		return result;
	}

}