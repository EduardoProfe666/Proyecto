package modelos;

import nucleo.Paciente;
import utilidades.Auxiliares;

public class PacienteTableModel extends ModeloPrincipalTableModel<Paciente>{

	private static final long serialVersionUID = 1L;
	
	public PacienteTableModel() {
		super(new String[] {"Nombre y Apellidos", "Sexo","Carnet de Identidad", "No. de Historia Clínica"});
	}
	
	@Override
	public void adicionar(Paciente p) {
		this.addRow(new Object[] {p.getNombreCompleto(),Auxiliares.convertirSexoString(p.getSexo()),p.getCarnetIdentidad(),p.getHistoriaClinica().getNumeroHistoriaClinica()});
		
	}
}

