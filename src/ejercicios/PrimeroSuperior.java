package ejercicios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.ListIterator;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class PrimeroSuperior {

	/* El array lo declaro como atributo de la clase y así cualquier método pueda acceder a él sin que entre como parámetro,
	   como sucede con el método filtrar */
	private Examen[] examenes = new Examen[9];

	public void showAllCollections() {

		// Primero añado los objetos al array
		examenes[0] = new Examen(Asignatura.PROG, "Herencia y colecciones", LocalDate.of(2021, Month.JUNE, 16),
				LocalTime.of(8, 15), LocalTime.of(13, 30));
		;
		examenes[1] = new Examen(Asignatura.BD, "Disparadores y SQL", LocalDate.of(2021, Month.JUNE, 15),
				LocalTime.of(11, 45), LocalTime.of(14, 30));
		;
		examenes[2] = new Examen(Asignatura.SI, "Sistemas en red", LocalDate.of(2021, Month.JUNE, 21),
				LocalTime.of(10, 15), LocalTime.of(11, 45));
		;
		examenes[3] = new Examen(Asignatura.ENT, "Diagrama de clases", LocalDate.of(2021, Month.JUNE, 18),
				LocalTime.of(9, 15), LocalTime.of(11, 15));
		;
		examenes[4] = new Examen(Asignatura.MARC, "Hojas de estilo", LocalDate.of(2021, Month.JUNE, 14),
				LocalTime.of(8, 15), LocalTime.of(11, 15));
		;
		examenes[5] = new Examen(Asignatura.FOL, "Derechos del trabajador", LocalDate.of(2021, Month.JUNE, 9),
				LocalTime.of(10, 15), LocalTime.of(11, 15));
		;
		examenes[6] = new Examen(Asignatura.BD, "Entidad-Relación", LocalDate.of(2021, Month.JUNE, 15),
				LocalTime.of(8, 15), LocalTime.of(11, 30));
		;
		examenes[7] = examenes[0].copy();
		;
		examenes[8] = examenes[4].copy();
		;

		showCollectionOne();
		System.out.printf("\n\n");
		showCollectionTwo();
		System.out.printf("\n\n");
		showCollectionThree();
		System.out.printf("\n\n");
		showCollectionFour();
		System.out.printf("\n\n");
		showCollectionFive();
		System.out.printf("\n\n");
		showFilters();

	}

	public void showCollectionOne() {

		ArrayList<Examen> list = new ArrayList<>();
		for (int i = 0; i < examenes.length; i++) {
			list.add(examenes[i]);
		}

		list.forEach(e -> System.out.println(e.toString()));
	}

	public void showCollectionTwo() {

		// Añado los elementos a un set (manteniendo el orden de inserción) para ignorar los duplicados
		LinkedHashSet<Examen> set = new LinkedHashSet<>();
		for (int i = 0; i < examenes.length; i++) {
			set.add(examenes[i]);
		}
		// Después añado los elementos del set a la lista para poder utilizar el listIterator
		ArrayList<Examen> list = new ArrayList<>();
		for (Examen examen : set) {
			list.add(examen);
		}
		// Coloco el iterador al final de la lista para recorrerla al revés
		ListIterator<Examen> listIterator = list.listIterator(list.size());
		// Esta variable tomará el valor de cada uno de los elementos del examen
		Examen examen;

		while (listIterator.hasPrevious()) {
			examen = listIterator.previous();
			System.out.println(examen.toString());
		}

	}

	public void showCollectionThree() {

		TreeSet<Examen> treeSet = new TreeSet<>();
		for (int i = 0; i < examenes.length; i++) {
			treeSet.add(examenes[i]);
		}

		// Referencia a método
		treeSet.forEach(System.out::println);

	}

	public void showCollectionFour() {

		// Clase anónima inline
		TreeSet<Examen> treeSet1 = new TreeSet<>(new Comparator<Examen>() {
			@Override
			public int compare(Examen examen1, Examen examen2) {
				// Orden ascendente
				int comparacion = examen1.getFecha().compareTo(examen2.getFecha());
				if (comparacion == 0) {
					// Orden ascendente
					comparacion = examen1.getHoraInicio().compareTo(examen2.getHoraInicio());
				}

				return comparacion;
			};
		});
		for (int i = 0; i < examenes.length; i++) {
			treeSet1.add(examenes[i]);
		}
		Iterator<Examen> iterator = treeSet1.iterator();
		Examen examen;

		while (iterator.hasNext()) {
			examen = iterator.next();
			System.out.println(examen);
		}

		// Métodos de la interfaz Comparartor
		TreeSet<Examen> treeSet2 = new TreeSet<>(
				(Comparator.comparing(Examen::getFecha).thenComparing(Examen::getHoraInicio)));
		for (int i = 0; i < examenes.length; i++) {
			treeSet2.add(examenes[i]);
		}
		// Ahora coloco el iterador en el segundo árbol, reutilizando la variable iterator
		iterator = treeSet2.iterator();

		while (iterator.hasNext()) {
			// También reutilizo la variable examen para que tome cada uno de los valores del segundo árbol
			examen = iterator.next();
			System.out.println(examen);
		}

	}

	public void showCollectionFive() {

		// Subapartado 1
		TreeMap<Clave, Examen> treeMap = new TreeMap<>();
		for (int i = 0; i < examenes.length; i++) {
			treeMap.put(examenes[i].getClave(), examenes[i]);
		}

		System.out.println("Apartado5_1:");
		// Expresión Lambda
		treeMap.forEach((key, value) -> System.out.println(value.toString()));

		System.out.println("\nApartado5_2:");
		System.out.println(
				treeMap.computeIfPresent(new Clave(9, LocalTime.of(10, 15)), new BiFunction<Clave, Examen, Examen>() {
					@Override
					public Examen apply(Clave clave, Examen examen) {
						// Se modifica la horaFin aumentándola en una hora
						examen.setHoraFin(examen.getHoraFin().plusHours(1));

						return examen;
					}
				}));

	}

	public void showFilters() {

		// Expresión Lambda
		filtrar(e -> ChronoUnit.HOURS.between(e.getHoraInicio(), e.getHoraFin()) >= 3)
				.forEach(e -> System.out.println(e.toString()));
		System.out.println("\nApartado6_2:");
		// Expresión Lambda
		// La fecha debe ser superior en el día, e igual o superior en el mes y en el año
		filtrar(e -> (e.getFecha().isAfter(LocalDate.of(2021, Month.JUNE, 15))))
				.forEach(e -> System.out.println(e.toString()));

	}

	public ArrayList<Examen> filtrar(Predicate<Examen> predicate) {

		// Añado los exámenes a un set para ignorar los duplicados
		HashSet<Examen> set = new HashSet<>();
		for (int i = 0; i < examenes.length; i++) {
			set.add(examenes[i]);
		}
		// Después añado los elementos del set a la lista
		ArrayList<Examen> list = new ArrayList<>();
		for (Examen examen : set) {
			list.add(examen);
		}
		// ListIterator para modificar la lista a la vez que la recorro
		ListIterator<Examen> listIterator = list.listIterator();
		Examen examen;

		while (listIterator.hasNext()) {
			examen = listIterator.next();
			if (!predicate.test(examen)) {
				// Si no se cumple la condición, se elimina el elemento de la lista
				listIterator.remove();
			}
		}

		return list;

	}

	public static void main(String[] args) {

		new PrimeroSuperior().showAllCollections();

	}

}
