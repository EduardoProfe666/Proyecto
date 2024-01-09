package modelos;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;


public class MultiLineaCellRendererEditor extends JScrollPane implements TableCellRenderer, TableCellEditor{

	private static final long serialVersionUID = 1L;
	private JTextArea texto;
	public MultiLineaCellRendererEditor() {
		this.setBorder(null);
		texto = new JTextArea();	
		texto.setBorder(null);
		texto.setEditable(false);
		texto.setLineWrap(true);
		texto.setWrapStyleWord(true);
		this.setViewportView(texto);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (isSelected) {
			texto.setForeground(table.getSelectionForeground());
			texto.setBackground(table.getSelectionBackground());
		} else {
			texto.setForeground(table.getForeground());
			texto.setBackground(table.getBackground());
		}
		texto.setFont(table.getFont());
		texto.setBorder(new EmptyBorder(1, 2, 1, 2));
		texto.setText((value == null) ? "" : value.toString());
		texto.setCaretPosition(0);
		return this;
	}

	protected EventListenerList listadoListeners = new EventListenerList();
	protected transient ChangeEvent ce = null;
	@Override
	public Object getCellEditorValue() {
		return this.texto.getText();
	}

	@Override
	public boolean isCellEditable(EventObject anEvent) {
		return true;
	}

	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		return true;
	}

	@Override
	public boolean stopCellEditing() {
		edicionDetenida();
		return true;
	}

	@Override
	public void cancelCellEditing() {
		edicionCancelada();
	}

	@Override
	public void addCellEditorListener(CellEditorListener l) {
		listadoListeners.add(CellEditorListener.class, l);
	}

	@Override
	public void removeCellEditorListener(CellEditorListener l) {
		listadoListeners.remove(CellEditorListener.class, l);
	}
	public CellEditorListener[] getCellEditorListeners() {
		return listadoListeners.getListeners(CellEditorListener.class);
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (isSelected) {
			texto.setForeground(table.getSelectionForeground());
			texto.setBackground(table.getSelectionBackground());
		} else {
			texto.setForeground(table.getForeground());
			texto.setBackground(table.getBackground());
		}
		texto.setFont(table.getFont());
		texto.setBorder(new EmptyBorder(1, 2, 1, 2));
		texto.setText((value == null) ? "" : value.toString());
		texto.setCaretPosition(0);
		return this;
	}

	protected void edicionDetenida() {
		Object[] listeners = listadoListeners.getListenerList();
		for(int i = listeners.length-2; i>=0; i-=2) {
			if(listeners[i]==CellEditorListener.class) {
				if(ce == null) 
					ce = new ChangeEvent(this);
				((CellEditorListener)listeners[i+1]).editingStopped(ce);
			}
		}
	}

	protected void edicionCancelada() {
		Object[] listeners = listadoListeners.getListenerList();
		for(int i = listeners.length-2; i>=0; i-=2) {
			if(listeners[i]==CellEditorListener.class) {
				if(ce == null) 
					ce = new ChangeEvent(this);
				((CellEditorListener)listeners[i+1]).editingCanceled(ce);
			}
		}
	}


}
