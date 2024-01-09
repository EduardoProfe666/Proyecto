package modelos;

public class EnfermedadesCronicasTableModel extends ModeloPrincipalTableModel<String>{
	private static final long serialVersionUID = 1L;

	public EnfermedadesCronicasTableModel() {
		super(new String[] {null});
	}
	
	@Override
	public void adicionar(String nombreEnfermedad) {
		this.addRow(new Object[] {nombreEnfermedad});
	}
	
}
