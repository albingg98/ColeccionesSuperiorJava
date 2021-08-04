package ejercicios;

public enum Asignatura {
	PROG("Programación"), BD("Base de datos"), SI("Sistemas informáticos"), ENT("Entornos de desarrollo"),
	MARC("Lenguaje de marcas"), FOL("Formación y orientación laboral");

	private String nombre;

	// El constructor del Enum debe ser privado para que no se modifiquen los valores de cada constante del Enum
	private Asignatura(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	// No se deben establecer setters para que no se modifiquen los valores de las constantes del Enum

}
