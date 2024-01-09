package modelos;

import definiciones.DefinicionesInterfazGrafica;
import nucleo.Analisis;

public class PacienteAnalisisFaltantesTableModel extends ModeloPrincipalTableModel<Analisis>{
	
	private static final long serialVersionUID = 1L;
	public PacienteAnalisisFaltantesTableModel() {
		super(new String[] {"Fecha de Indicación", "Tipo de Análisis"});
	}
	
	@Override
	public void adicionar(Analisis t) {
		this.addRow(new Object[] {DefinicionesInterfazGrafica.FORMATO_BASE_FECHA.format(t.getFechaIndicacion()),t.getTipoAnalisis()});
		
	}
	
}
