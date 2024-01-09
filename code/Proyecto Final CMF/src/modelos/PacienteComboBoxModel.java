package modelos;

import java.util.List;

public class PacienteComboBoxModel extends ModeloPrincipalComboBoxModel{

	public PacienteComboBoxModel(List<String> listado) {
		super(listado);
	}

	private static final long serialVersionUID = 1L;

	@Override
	protected void inicializar() {
		this.addElement("<Seleccionar un paciente>");
	}

}
