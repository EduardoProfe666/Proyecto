package modelos;

import java.util.ArrayList;

import clases_auxiliares.AnalisisIndicados;
import definiciones.DefinicionesInterfazGrafica;
import nucleo.Analisis;

public class AnalisisFaltantesTableModel extends ModeloPrincipalTableModel<AnalisisIndicados>{

	private static final long serialVersionUID = 1L;
	public AnalisisFaltantesTableModel() {
		super(new String[]{"Nombre y Apellidos", "Carnet de Identidad","No. de Historia Clínica", "Fecha de Indicación", "Tipo de Análisis"});
	}
	
	@Override
	public void adicionar(AnalisisIndicados t) {
		String nombrePaciente = t.getPaciente().getNombreCompleto();
		String ci = t.getPaciente().getCarnetIdentidad();
		String numHC = t.getPaciente().getHistoriaClinica().getNumeroHistoriaClinica();
		
		ArrayList<Analisis> listado = t.getListadoAnalisis();
		
		for(Analisis a : listado) {
			this.addRow(new Object[] {nombrePaciente,ci,numHC,DefinicionesInterfazGrafica.FORMATO_BASE_FECHA.format(a.getFechaIndicacion()),a.getTipoAnalisis()});
		}
		
	}
	
}
