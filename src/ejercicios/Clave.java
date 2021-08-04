package ejercicios;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Clave implements Comparable<Clave> {

	private int dia;
	private LocalTime horaInicio;

	public Clave(int dia, LocalTime horaInicio) {
		this.dia = dia;
		this.horaInicio = horaInicio;
	}

	public int getDia() {
		return dia;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	@Override
	public String toString() {
		// Para obtener una combinación de la fecha y la hora
		LocalDateTime dateTime = LocalDateTime.of(2021, Month.JUNE, dia, getHoraInicio().getHour(),
				getHoraInicio().getMinute());
		// Para aplicar el formato deseado a la fecha/hora
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-HH:mm");

		return dateTime.format(formatter);

	}

	@Override
	public int compareTo(Clave clave) {
		// Orden ascendente
		int comparacion = Integer.compare(getDia(), clave.getDia());
		if (comparacion == 0) {
			// Orden ascendente
			comparacion = getHoraInicio().compareTo(clave.getHoraInicio());
		}

		return comparacion;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dia;
		result = prime * result + ((horaInicio == null) ? 0 : horaInicio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clave other = (Clave) obj;
		if (dia != other.dia)
			return false;
		if (horaInicio == null) {
			if (other.horaInicio != null)
				return false;
		} else if (!horaInicio.equals(other.horaInicio))
			return false;
		return true;
	}

}
