package modelos;

import nucleo.VisitaHojaDeCargos;

public class HojaCargosTableModel extends ModeloPrincipalTableModel<VisitaHojaDeCargos>{

	private static final long serialVersionUID = 1L;
	
	public HojaCargosTableModel() {
		super(new String[]{"Nombre y Apellidos","Carnet de Identidad", "No. de Historia Clínica", "Diagnóstico"});
	}
	
	@Override
	public void adicionar(VisitaHojaDeCargos t) {
		this.addRow(new Object[] {t.getPaciente().getNombreCompleto(),t.getPaciente().getCarnetIdentidad(),t.getPaciente().getHistoriaClinica().getNumeroHistoriaClinica(),t.getDiagnostico()});
		
	}	
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return column==3;
	}
	
}
