package modelos;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

public abstract class ModeloPrincipalComboBoxModel extends DefaultComboBoxModel<String>{

	private static final long serialVersionUID = 1L;
	
	protected abstract void inicializar();
	protected ModeloPrincipalComboBoxModel(List<String> listado) {
		inicializar();
		if(listado!=null)
			for(String s : listado)
				this.addElement(s);
	}

}
