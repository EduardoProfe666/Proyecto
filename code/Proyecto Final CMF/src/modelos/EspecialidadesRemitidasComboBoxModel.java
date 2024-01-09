package modelos;

import java.util.List;

public class EspecialidadesRemitidasComboBoxModel extends ModeloPrincipalComboBoxModel{

	private static final long serialVersionUID = 1L;

	public EspecialidadesRemitidasComboBoxModel(List<String> listado) {
		super(listado);
	}

	@Override
	protected void inicializar() {
		this.addElement("<Seleccionar una especialidad remitida>");
	}

}
