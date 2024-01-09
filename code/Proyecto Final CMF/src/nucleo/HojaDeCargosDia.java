package nucleo;

import java.util.ArrayList;
import java.util.Calendar;
import definiciones.Errores;

public class HojaDeCargosDia implements Comparable<HojaDeCargosDia>, Cloneable{
	private Calendar fecha;
	private ArrayList<VisitaHojaDeCargos> listadoHojaCargos;

	public HojaDeCargosDia(Calendar fecha) {
		setFecha(fecha);
		listadoHojaCargos = new ArrayList<VisitaHojaDeCargos>();
	}
	
	
	public Calendar getFecha() {
		return fecha;
	}

	private void setFecha(Calendar fecha) {   
		if(fecha != null)
			this.fecha = fecha;
		else
			throw new IllegalArgumentException(Errores.ERROR_FECHA_HOJA_CARGOS_DIA);
	}

	public ArrayList<VisitaHojaDeCargos> getListadoHojaCargos() {
		return new ArrayList<VisitaHojaDeCargos>(listadoHojaCargos);
	}

	public void addVisitaDePaciente(String diagnostico, Paciente paciente) {
		listadoHojaCargos.add(new VisitaHojaDeCargos(diagnostico, paciente));
	}

	@Override
	public int compareTo(HojaDeCargosDia o) {
		return fecha.compareTo(o.getFecha());
	}
	
	@Override
	public HojaDeCargosDia clone() {
		HojaDeCargosDia h = new HojaDeCargosDia((Calendar)this.fecha.clone());
		
		h.listadoHojaCargos = new ArrayList<VisitaHojaDeCargos>(this.listadoHojaCargos);
		
		return h;
	}

}
