package modelos;

public class EspecialidadesRemitidasTableModel extends ModeloPrincipalTableModel<String>{

	private static final long serialVersionUID = 1L;

	public EspecialidadesRemitidasTableModel() {
		super(new String[] {null});
	}
	
	@Override
	public void adicionar(String especialidad) {
		this.addRow(new Object[] {especialidad});
		
	}

}
