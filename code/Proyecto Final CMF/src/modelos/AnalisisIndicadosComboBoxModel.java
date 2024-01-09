package modelos;

import java.util.List;

public class AnalisisIndicadosComboBoxModel extends ModeloPrincipalComboBoxModel{

	private static final long serialVersionUID = 1L;

	public AnalisisIndicadosComboBoxModel(List<String> listado) {
		super(listado);
	}

	@Override
	protected void inicializar() {
		this.addElement("<Seleccionar un análisis indicado>");
		
	}
	
}
