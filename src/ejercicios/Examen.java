package ejercicios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Examen implements Comparable<Examen> {

	private Clave clave;
	private Asignatura asignatura;
	private String descripcion;
	private LocalDate fecha;
	private LocalTime horaInicio;
	private LocalTime horaFin;

	public Examen(Asignatura asignatura, String descripcion, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
		// La clave se construye aprovechando varios de los parámetros del constructor
		clave = new Clave(fecha.getDayOfMonth(), horaInicio);
		this.asignatura = asignatura;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}

	// Constructor privado para ser utilizado exclusivamente por el método copy();
	private Examen(Examen examen) {
		clave = examen.clave;
		asignatura = examen.asignatura;
		descripcion = examen.descripcion;
		fecha = examen.fecha;
		horaInicio = examen.horaInicio;
		horaFin = examen.horaFin;
	}

	public Clave getClave() {
		return clave;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	// El setter será utilizado en el apartado 5 para poder modificar la horaFin
	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	@Override
	public String toString() {
		// Dos formateadores, uno para dar formato a la fecha y otro para dar formato a la hora
		DateTimeFormatter formatterDate, formatterTime;
		formatterDate = DateTimeFormatter.ofPattern("dd/LL/yyyy");
		formatterTime = DateTimeFormatter.ofPattern("HH:mm");

		return String.format("%-9s %-32s %-24s %-11s %-6s %s", getClave().toString(), getAsignatura().getNombre(),
				getDescripcion(), getFecha().format(formatterDate), getHoraInicio().format(formatterTime),
				getHoraFin().format(formatterTime));
	}

	// Método para copiar exámenes
	public Examen copy() {

		return new Examen(this);

	}

	@Override
	public int compareTo(Examen examen) {

		// Orden ascendente
		int comparacion = getAsignatura().getNombre().compareToIgnoreCase(examen.getAsignatura().getNombre());
		if (comparacion == 0) {
			// Orden descendente
			comparacion = examen.getDescripcion().compareToIgnoreCase(getDescripcion());
		}

		return comparacion;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asignatura == null) ? 0 : asignatura.hashCode());
		result = prime * result + ((clave == null) ? 0 : clave.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((horaFin == null) ? 0 : horaFin.hashCode());
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
		Examen other = (Examen) obj;
		if (asignatura != other.asignatura)
			return false;
		if (clave == null) {
			if (other.clave != null)
				return false;
		} else if (!clave.equals(other.clave))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (horaFin == null) {
			if (other.horaFin != null)
				return false;
		} else if (!horaFin.equals(other.horaFin))
			return false;
		if (horaInicio == null) {
			if (other.horaInicio != null)
				return false;
		} else if (!horaInicio.equals(other.horaInicio))
			return false;
		return true;
	}

}
