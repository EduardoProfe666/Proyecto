package modelos;

import nucleo.PacienteFemenina;

public class EmbarazadaTableModel extends ModeloPrincipalTableModel<PacienteFemenina>{

	private static final long serialVersionUID = 1L;

	public EmbarazadaTableModel() {
		super(new String[] {"Nombre y Apellidos","Carnet de Identidad", "No. de Historia Clínica","Dirección"});
	}
	
	@Override
	public void adicionar(PacienteFemenina t) {
		this.addRow(new Object[] {t.getNombreCompleto(),t.getCarnetIdentidad(),t.getHistoriaClinica().getNumeroHistoriaClinica(),t.getDireccion().getDireccionCompleta()});
		
	}
}
