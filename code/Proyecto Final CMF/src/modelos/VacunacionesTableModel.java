package modelos;

import definiciones.DefinicionesInterfazGrafica;
import nucleo.Vacuna;

public class VacunacionesTableModel extends ModeloPrincipalTableModel<Vacuna>{

	private static final long serialVersionUID = 1L;
	
	public VacunacionesTableModel() {
		super(new String[] {null,null});
	}

	@Override
	public void adicionar(Vacuna t) {
		this.addRow(new Object[] {t.getNombreVacuna(), DefinicionesInterfazGrafica.FORMATO_BASE_FECHA.format(t.getFechaVacunacion())});
	}	
}
