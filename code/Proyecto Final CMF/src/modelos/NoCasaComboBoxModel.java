package modelos;

import java.util.List;

public class NoCasaComboBoxModel extends ModeloPrincipalComboBoxModel{
	private static final long serialVersionUID = 1L;
	
	public NoCasaComboBoxModel(List<String> noCasas) {
		super(noCasas);
	}

	@Override
	protected void inicializar() {
		this.addElement("<No. Casa>");
		
	}
}
