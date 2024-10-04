public class MarvelCharacter {
	int id;
	String dni;
	String name;
	String secretIdentity;
	String type;
	int weight;
	int height;

	public MarvelCharacter (int id, String dni, String name, String secretIdentity, String type, int weight, int height) {
		this.id = id;
		this.dni = dni;
		this.name = name;
		this.secretIdentity = secretIdentity;
		this.type = type;
		this.weight = weight;
		this.height = height;
	}

	public String toString() {
		return "Personaje [dni=" + this.dni + ", nombre=" + this.name + ", identidad=" + this.secretIdentity
				+ ", tipo=" + this.type + ", peso=" + this.weight + ", altura=" + this.height + "]";
	}
}