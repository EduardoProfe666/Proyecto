package modelos;

import java.util.List;

public class CallePrincipalComboBoxModel extends ModeloPrincipalComboBoxModel{
	private static final long serialVersionUID = 1L;
	
	public CallePrincipalComboBoxModel(List<String> calles) {
		super(calles);
	}

	@Override
	protected void inicializar() {
		this.addElement("<Calle Principal>");
	}
}
