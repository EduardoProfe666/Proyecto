package modelos;

import definiciones.DefinicionesInterfazGrafica;
import nucleo.Visita;

public class VisitaTableModel extends ModeloPrincipalTableModel<Visita>{

	private static final long serialVersionUID = 1L;
	
	public VisitaTableModel() {
		super(new String[] {"Fecha", "Diagnóstico","Tratamiento", "Análisis Indicados", "Especialidades Remitidas"});
	}
	
	@Override
	public void adicionar(Visita t) { 
		String analisis = "";
		String especialidades = "";
		for(String s : t.getAnalisisIndicados())
			analisis = analisis+"-"+s+"\n";
		for(String s : t.getEspecialidadesRemitidas())
			especialidades = especialidades+"-"+s+"\n";
			
		this.addRow(new Object[] {DefinicionesInterfazGrafica.FORMATO_BASE_FECHA.format(t.getFecha()),t.getDiagnostico(),t.getTratamiento(),analisis,especialidades});
		
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return column>0;
	}
}
