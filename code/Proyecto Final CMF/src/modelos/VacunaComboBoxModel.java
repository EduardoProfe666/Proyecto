package modelos;

import java.util.List;

public class VacunaComboBoxModel extends ModeloPrincipalComboBoxModel{

	private static final long serialVersionUID = 1L;
	public VacunaComboBoxModel(List<String> vacunas) {
		super(vacunas);
	}
	@Override
	protected void inicializar() {
		this.addElement("<Seleccione una vacuna>");
	}

}
