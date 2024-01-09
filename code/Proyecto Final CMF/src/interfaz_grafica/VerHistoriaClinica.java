package interfaz_grafica;

import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import componentes.BotonAnimacion;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import componentes.Imagen;
import definiciones.DefinicionesInterfazGrafica;
import modelos.AnalisisTableModel;
import modelos.MultiLineaCellRendererEditor;
import modelos.VisitaTableModel;
import nucleo.HistoriaClinica;
import utilidades.Comparadores;

import javax.swing.ImageIcon;
import javax.swing.JTable;

public class VerHistoriaClinica extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panelBase;
	private JLabel historiaClinicaLbl;
	private JPanel panelSuperior;
	private BotonAnimacion botonSalir;
	private int xMouseDrag;
	private int yMouseDrag;
	private JLabel historiaClinicaLbl_1;
	private JLabel historiaClinicaTxt;
	private JTable tablaVisitas;
	private Imagen logoVisitas;
	private JLabel visitasLbl;
	private JScrollPane panelVisitas;
	private Imagen logoAnalisis;
	private JLabel analisisLbl;
	private JScrollPane panelAnalisis;
	private JTable tablaAnalisis;
	private HistoriaClinica h;
	private AnalisisTableModel modeloAnalisis;
	private VisitaTableModel modeloVisitas;
	private TableRowSorter<VisitaTableModel> ordenamientoVisitas;
	private TableRowSorter<AnalisisTableModel> ordenamientoAnalisis;
	
	public VerHistoriaClinica(JFrame parent, final HistoriaClinica h) {
		super(parent,true);
		this.h=h;
		this.setUndecorated(true);
		setBounds(100, 100, 670, 540);
		panelBase = new JPanel();
		panelBase.setBackground(Color.WHITE);
		panelBase.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(panelBase);
		panelBase.setLayout(null);
		
		panelSuperior = new JPanel();
		panelSuperior.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x-xMouseDrag,y-yMouseDrag);
			}
		});
		panelSuperior.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xMouseDrag = e.getX();
				yMouseDrag = e.getY();
			}
		});
		panelSuperior.setLayout(null);
		panelSuperior.setBackground(Color.WHITE);
		panelSuperior.setBounds(3, 3, 665, 40);
		panelBase.add(panelSuperior);
		
		botonSalir = new BotonAnimacion();
		botonSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				botonSalir.setBackground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				botonSalir.setBackground(Color.LIGHT_GRAY);
			}
		});
		botonSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				botonSalir.setBackground(Color.RED);
				botonSalir.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				botonSalir.setBackground(Color.LIGHT_GRAY);
				botonSalir.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		botonSalir.setText("X");
		botonSalir.setForeground(Color.BLACK);
		botonSalir.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		botonSalir.setEffectColor(new Color(178, 34, 34));
		botonSalir.setBackground(Color.LIGHT_GRAY);
		botonSalir.setBounds(623, 4, 32, 32);
		panelSuperior.add(botonSalir);
		
		historiaClinicaLbl = new JLabel("Historia Cl\u00EDnica");
		historiaClinicaLbl.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		historiaClinicaLbl.setBounds(10, 5, 243, 29);
		panelSuperior.add(historiaClinicaLbl);
		
		historiaClinicaLbl_1 = new JLabel("No. Historia Clinica:");
		historiaClinicaLbl_1.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		historiaClinicaLbl_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		historiaClinicaLbl_1.setBounds(13, 54, 143, 24);
		panelBase.add(historiaClinicaLbl_1);
		
		historiaClinicaTxt = new JLabel();
		historiaClinicaTxt.setToolTipText("");
		historiaClinicaTxt.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		historiaClinicaTxt.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		historiaClinicaTxt.setBounds(156, 54, 120, 24);
		panelBase.add(historiaClinicaTxt);
		
		panelVisitas = new JScrollPane();
		panelVisitas.setBackground(Color.WHITE);
		panelVisitas.setBounds(13, 134, 647, 167);
		panelBase.add(panelVisitas);
		
		modeloVisitas = new VisitaTableModel();
		ordenamientoVisitas = new TableRowSorter<VisitaTableModel>(modeloVisitas);
		ordenamientoVisitas.setComparator(0, Comparadores.comparadorFechaString());
		ordenamientoVisitas.setSortable(1, false);
		ordenamientoVisitas.setSortable(2, false);
		ordenamientoVisitas.setSortable(3, false);
		ordenamientoVisitas.setSortable(4, false);
		tablaVisitas = new JTable();
		tablaVisitas.setRowSorter(ordenamientoVisitas);
		tablaVisitas.setModel(modeloVisitas);
		tablaVisitas.setRowHeight(tablaVisitas.getRowHeight() * 3);
		tablaVisitas.getColumnModel().getColumn(1).setCellRenderer(new MultiLineaCellRendererEditor());
		tablaVisitas.getColumnModel().getColumn(1).setCellEditor(new MultiLineaCellRendererEditor());
		tablaVisitas.getColumnModel().getColumn(2).setCellRenderer(new MultiLineaCellRendererEditor());
		tablaVisitas.getColumnModel().getColumn(2).setCellEditor(new MultiLineaCellRendererEditor());
		tablaVisitas.getColumnModel().getColumn(3).setCellRenderer(new MultiLineaCellRendererEditor());
		tablaVisitas.getColumnModel().getColumn(3).setCellEditor(new MultiLineaCellRendererEditor());
		tablaVisitas.getColumnModel().getColumn(4).setCellRenderer(new MultiLineaCellRendererEditor());
		tablaVisitas.getColumnModel().getColumn(4).setCellEditor(new MultiLineaCellRendererEditor());
		tablaVisitas.setSelectionForeground(Color.BLACK);
		tablaVisitas.setSelectionBackground(DefinicionesInterfazGrafica.VERDE_AZULADO_1);
		tablaVisitas.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		tablaVisitas.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		panelVisitas.setViewportView(tablaVisitas);
		
		visitasLbl = new JLabel("Visitas");
		visitasLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		visitasLbl.setBounds(51, 106, 208, 24);
		panelBase.add(visitasLbl);
		
		logoVisitas = new Imagen(new ImageIcon(VerHistoriaClinica.class.getResource("/iconos/visita0.png")));
		logoVisitas.setBounds(13, 101, 28, 28);
		panelBase.add(logoVisitas);
		
		analisisLbl = new JLabel("Resultados de los An\u00E1lisis");
		analisisLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		analisisLbl.setBounds(48, 317, 208, 24);
		panelBase.add(analisisLbl);
		
		logoAnalisis = new Imagen(new ImageIcon(VerHistoriaClinica.class.getResource("/iconos/analisis0.png")));
		logoAnalisis.setBounds(10, 312, 28, 28);
		panelBase.add(logoAnalisis);
		
		panelAnalisis = new JScrollPane();
		panelAnalisis.setBackground(Color.WHITE);
		panelAnalisis.setBounds(13, 345, 647, 167);
		panelBase.add(panelAnalisis);
		
		modeloAnalisis = new AnalisisTableModel();
		ordenamientoAnalisis = new TableRowSorter<AnalisisTableModel>(modeloAnalisis);
		ordenamientoAnalisis.setComparator(0, Comparadores.comparadorFechaString());
		ordenamientoAnalisis.setSortable(2, false);
		tablaAnalisis = new JTable();
		tablaAnalisis.setRowSorter(ordenamientoAnalisis);
		tablaAnalisis.setModel(modeloAnalisis);
		tablaAnalisis.setRowHeight(tablaAnalisis.getRowHeight() * 3);
		tablaAnalisis.getColumnModel().getColumn(2).setCellRenderer(new MultiLineaCellRendererEditor());
		tablaAnalisis.getColumnModel().getColumn(2).setCellEditor(new MultiLineaCellRendererEditor());
		tablaAnalisis.setSelectionForeground(Color.BLACK);
		tablaAnalisis.setSelectionBackground(DefinicionesInterfazGrafica.VERDE_AZULADO_1);
		tablaAnalisis.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		tablaAnalisis.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		panelAnalisis.setViewportView(tablaAnalisis);
		
		
		rellenarDatos();
		this.setLocationRelativeTo(null);
	}
	
	private void rellenarDatos() {
		historiaClinicaTxt.setText(h.getNumeroHistoriaClinica());
		modeloVisitas.actualizar(h.getListadoVisitas());
		modeloAnalisis.actualizar(h.analisisConResultados());
		ordenamientoVisitas.toggleSortOrder(0);
		ordenamientoAnalisis.toggleSortOrder(0);
	}
}
