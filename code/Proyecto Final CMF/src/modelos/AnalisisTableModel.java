package modelos;

import definiciones.DefinicionesInterfazGrafica;
import nucleo.Analisis;

public class AnalisisTableModel extends ModeloPrincipalTableModel<Analisis>{

	private static final long serialVersionUID = 1L;
	
	public AnalisisTableModel() {
		super(new String[] {"Fecha", "Tipo de Análisis","Resultado"});
	}
	@Override
	public void adicionar(Analisis t) {
		this.addRow(new Object[] {DefinicionesInterfazGrafica.FORMATO_BASE_FECHA.format(t.getFechaAnalisis()),t.getTipoAnalisis(),t.getResultadoAnalisis()});
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return column==2;
	}
	
}
