package modelos;

import java.util.List;

public class EnfermedadesCronicasComboBoxModel extends ModeloPrincipalComboBoxModel{

	private static final long serialVersionUID = 1L;

	public EnfermedadesCronicasComboBoxModel(List<String> enfermedades) {
		super(enfermedades);
	}

	@Override
	protected void inicializar() {
		this.addElement("<Seleccione una enfermedad crónica>");
		
	}

}
