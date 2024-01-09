package modelos;

public class AnalisisIndicadosTableModel extends ModeloPrincipalTableModel<String>{

	private static final long serialVersionUID = 1L;

	public AnalisisIndicadosTableModel() {
		super(new String[] {null});
	}
	
	@Override
	public void adicionar(String analisis) {
		this.addRow(new Object[] {analisis});
	}

}
