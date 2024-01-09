package modelos;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public abstract class ModeloPrincipalTableModel<T> extends DefaultTableModel{
	
	private static final long serialVersionUID = 1L;
	
	protected ModeloPrincipalTableModel(Object[] identificadoresColumnas) {
		this.setColumnIdentifiers(identificadoresColumnas);
	}
	
	public void eliminarFilas() {
		this.setRowCount(0);
	}
	
	public abstract void adicionar(T t);
	
	
	public void actualizar(List<T> listado, Comparator<? super T> comparador) {
		if(comparador!=null)
			Collections.sort(listado, comparador);
		actualizar(listado);
	}
	
	
	public void actualizar(List<T> listado){
		if(listado!=null) {
			this.eliminarFilas();
			for(T t : listado) 
				adicionar(t);
		}
	}
	
	@Override
	public boolean isCellEditable(int row, int column) { 
		return false;
	}
}
