package interfaz_grafica;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import definiciones.Definiciones;
import definiciones.DefinicionesInterfazGrafica;
import modelos.AnalisisIndicadosTableModel;
import modelos.EspecialidadesRemitidasTableModel;
import modelos.PacienteComboBoxModel;
import nucleo.CMF;
import utilidades.AuxiliaresInterfazGrafica;
import utilidades.DialogosAuxiliares;
import utilidades.Validaciones;
import componentes.BotonAnimacion;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AgregarVisita extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panelBase;
	private int yMouseDrag;
	private int xMouseDrag;
	private JLabel agregarVisitaLbl;
	private JPanel panelSuperior;
	private BotonAnimacion botonSalir;
	private JLabel pacienteLbl;
	private JButton eliminarAnalisisBtn;
	private JButton adicionarAnalisisBtn;
	private JButton adicionarEspecialidadBtn;
	private JButton eliminarEspecialidadBtn;
	private JScrollPane panelAnalisis;
	private JScrollPane panelEspecialidades;
	private JTextArea diagnosticoTxt;
	private JTextArea tratamientoTxt;
	private JLabel diagnosticoLbl;
	private JLabel tratamientoLbl;
	private JLabel analisisIndicadosLbl;
	private JLabel especialidadesRemitidasLbl;
	private JScrollPane panelDiagnostico;
	private JScrollPane panelTratamiento;
	private BotonAnimacion aceptarBtn;
	private BotonAnimacion cancelarBtn;
	private JComboBox<String> seleccionarPaciente;
	private ArrayList<String> analisisIndicados;
	private ArrayList<String> especialidadesRemitidas;
	private JTable tablaAnalisisIndicados;
	private AnalisisIndicadosTableModel modeloAnalisis;
	private JTable tablaEspecialidades;
	private EspecialidadesRemitidasTableModel modeloEspecialidades;

	public AgregarVisita(JFrame parent) {
		super(parent,true);
		analisisIndicados = new ArrayList<String>();
		especialidadesRemitidas = new ArrayList<String>();
		this.setUndecorated(true);
		setBounds(100, 100, DefinicionesInterfazGrafica.DIMENSION_DIALOGOS.width, DefinicionesInterfazGrafica.DIMENSION_DIALOGOS.height);
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
		panelSuperior.setBounds(3, 3, 594, 40);
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
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(rootPane, DefinicionesInterfazGrafica.PREGUNTA_SALIR_EDITAR, null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		botonSalir.setText("X");
		botonSalir.setForeground(Color.BLACK);
		botonSalir.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		botonSalir.setEffectColor(new Color(178, 34, 34));
		botonSalir.setBackground(Color.LIGHT_GRAY);
		botonSalir.setBounds(556, 4, 32, 32);
		panelSuperior.add(botonSalir);
		
		agregarVisitaLbl = new JLabel("Agregar Visita");
		agregarVisitaLbl.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		agregarVisitaLbl.setBounds(10, 5, 243, 29);
		panelSuperior.add(agregarVisitaLbl);
		
		pacienteLbl = new JLabel("Paciente:");
		pacienteLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		pacienteLbl.setBorder(null);
		pacienteLbl.setBounds(13, 54, 70, 24);
		panelBase.add(pacienteLbl);
		
		seleccionarPaciente = new JComboBox<String>();
		seleccionarPaciente.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				visibilidadComponentes(seleccionarPaciente.getSelectedIndex()>0);
				analisisIndicados.clear();
				modeloAnalisis.actualizar(analisisIndicados);
			}
		});
		seleccionarPaciente.setModel(new PacienteComboBoxModel(CMF.getInstancia().getListadoCI()));
		seleccionarPaciente.setBackground(Color.WHITE);
		seleccionarPaciente.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		seleccionarPaciente.setBounds(84, 55, 267, 24);
		panelBase.add(seleccionarPaciente);
		
		diagnosticoLbl = new JLabel("Diagn\u00F3stico:");
		diagnosticoLbl.setToolTipText("Debe introducir un texto con tama\u00F1o m\u00EDnimo de <"+Definiciones.TAM_MIN_STRING_DIAGNOSTICO+">\r\ncaracteres y tama\u00F1o m\u00E1ximo "
				+ "de <"+Definiciones.TAM_MAX_STRING_DIAGNOSTICO+"> caracteres.");
		diagnosticoLbl.setForeground(Color.BLACK);
		diagnosticoLbl.setEnabled(false);
		diagnosticoLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		diagnosticoLbl.setBorder(null);
		diagnosticoLbl.setBounds(13, 95, 264, 24);
		panelBase.add(diagnosticoLbl);
		
		tratamientoLbl = new JLabel("Tratamiento:");
		tratamientoLbl.setToolTipText("Debe introducir un texto con tama\u00F1o m\u00EDnimo de <"+Definiciones.TAM_MIN_STRING_TRATAMIENTO+">\r\ncaracteres y tama\u00F1o m\u00E1ximo "
				+ "de <"+Definiciones.TAM_MAX_STRING_TRATAMIENTO+"> caracteres.");
		tratamientoLbl.setForeground(Color.BLACK);
		tratamientoLbl.setEnabled(false);
		tratamientoLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		tratamientoLbl.setBorder(null);
		tratamientoLbl.setBounds(326, 95, 264, 24);
		panelBase.add(tratamientoLbl);
		
		analisisIndicadosLbl = new JLabel("An\u00E1lisis Indicados:");
		analisisIndicadosLbl.setForeground(Color.BLACK);
		analisisIndicadosLbl.setEnabled(false);
		analisisIndicadosLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		analisisIndicadosLbl.setBorder(null);
		analisisIndicadosLbl.setBounds(13, 348, 148, 24);
		panelBase.add(analisisIndicadosLbl);
		
		especialidadesRemitidasLbl = new JLabel("Especialidades Remitidas:");
		especialidadesRemitidasLbl.setForeground(Color.BLACK);
		especialidadesRemitidasLbl.setEnabled(false);
		especialidadesRemitidasLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		especialidadesRemitidasLbl.setBorder(null);
		especialidadesRemitidasLbl.setBounds(328, 348, 199, 24);
		panelBase.add(especialidadesRemitidasLbl);
		
		panelDiagnostico = new JScrollPane();
		panelDiagnostico.setBorder(new LineBorder(Color.BLACK, 2));
		panelDiagnostico.setBounds(13, 122, 264, 201);
		panelBase.add(panelDiagnostico);
		
		diagnosticoTxt = new JTextArea();
		diagnosticoTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(diagnosticoTxt.getText().length()==Definiciones.TAM_MAX_STRING_DIAGNOSTICO)
					e.consume();
			}
		});
		diagnosticoTxt.setToolTipText("Debe introducir un texto con tama\u00F1o m\u00EDnimo de <"+Definiciones.TAM_MIN_STRING_DIAGNOSTICO+">\r\ncaracteres y tama\u00F1o m\u00E1ximo "
				+ "de <"+Definiciones.TAM_MAX_STRING_DIAGNOSTICO+"> caracteres.");
		diagnosticoTxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				diagnosticoLbl.setForeground(Color.BLACK);
			}
		});
		diagnosticoTxt.setEnabled(false);
		panelDiagnostico.setViewportView(diagnosticoTxt);
		diagnosticoTxt.setBorder(null);
		diagnosticoTxt.setLineWrap(true);
		diagnosticoTxt.setWrapStyleWord(true);
		diagnosticoTxt.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		
		panelTratamiento = new JScrollPane();
		panelTratamiento.setBorder(new LineBorder(Color.BLACK, 2));
		panelTratamiento.setBounds(326, 122, 264, 201);
		panelBase.add(panelTratamiento);
		
		tratamientoTxt = new JTextArea();
		tratamientoTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(tratamientoTxt.getText().length()==Definiciones.TAM_MAX_STRING_TRATAMIENTO)
					e.consume();
			}
		});
		tratamientoTxt.setToolTipText("Debe introducir un texto con tama\u00F1o m\u00EDnimo de <"+Definiciones.TAM_MIN_STRING_TRATAMIENTO+">\r\ncaracteres y tama\u00F1o m\u00E1ximo "
				+ "de <"+Definiciones.TAM_MAX_STRING_TRATAMIENTO+"> caracteres.");
		tratamientoTxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tratamientoLbl.setForeground(Color.BLACK);
			}
		});
		tratamientoTxt.setEnabled(false);
		tratamientoTxt.setWrapStyleWord(true);
		tratamientoTxt.setLineWrap(true);
		tratamientoTxt.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		tratamientoTxt.setBorder(null);
		panelTratamiento.setViewportView(tratamientoTxt);
		
		panelAnalisis = new JScrollPane();
		panelAnalisis.setEnabled(false);
		panelAnalisis.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelAnalisis.setBorder(new LineBorder(Color.BLACK, 2));
		panelAnalisis.setBackground(Color.WHITE);
		panelAnalisis.setBounds(13, 378, 264, 161);
		panelBase.add(panelAnalisis);
		
		tablaAnalisisIndicados = new JTable();
		modeloAnalisis = new AnalisisIndicadosTableModel();
		tablaAnalisisIndicados.setModel(modeloAnalisis);
		tablaAnalisisIndicados.setTableHeader(null);
		tablaAnalisisIndicados.setSelectionForeground(Color.BLACK);
		tablaAnalisisIndicados.setSelectionBackground(DefinicionesInterfazGrafica.VERDE_AZULADO_1);
		tablaAnalisisIndicados.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		panelAnalisis.setViewportView(tablaAnalisisIndicados);
		
		panelEspecialidades = new JScrollPane();
		panelEspecialidades.setEnabled(false);
		panelEspecialidades.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelEspecialidades.setBorder(new LineBorder(Color.BLACK, 2));
		panelEspecialidades.setBackground(Color.WHITE);
		panelEspecialidades.setBounds(326, 378, 264, 161);
		panelBase.add(panelEspecialidades);
		
		tablaEspecialidades = new JTable();
		modeloEspecialidades = new EspecialidadesRemitidasTableModel();
		tablaEspecialidades.setModel(modeloEspecialidades);
		tablaEspecialidades.setTableHeader(null);
		tablaEspecialidades.setSelectionForeground(Color.BLACK);
		tablaEspecialidades.setSelectionBackground(DefinicionesInterfazGrafica.VERDE_AZULADO_1);
		tablaEspecialidades.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		panelEspecialidades.setViewportView(tablaEspecialidades);
		
		adicionarAnalisisBtn = new JButton("");
		adicionarAnalisisBtn.setEnabled(false);
		adicionarAnalisisBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = DialogosAuxiliares.agregarAnalisisIndicado(analisisIndicados,(String)seleccionarPaciente.getSelectedItem());
				if(s!=null) {
					analisisIndicados.add(s);
					modeloAnalisis.actualizar(analisisIndicados);
				}
			}
		});
		adicionarAnalisisBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		adicionarAnalisisBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(20,20), AgregarVisita.class.getResource("/iconos/add0.png")));
		adicionarAnalisisBtn.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(20,20), AgregarVisita.class.getResource("/iconos/add1.png")));
		adicionarAnalisisBtn.setContentAreaFilled(false);
		adicionarAnalisisBtn.setBounds(253, 348, 24, 24);
		panelBase.add(adicionarAnalisisBtn);
		
		eliminarAnalisisBtn = new JButton("");
		eliminarAnalisisBtn.setEnabled(false);
		eliminarAnalisisBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = tablaAnalisisIndicados.getSelectedRow();
				if(i!=-1) {
					analisisIndicados.remove(modeloAnalisis.getValueAt(i, 0));
					modeloAnalisis.actualizar(analisisIndicados);
				}
			}
		});
		eliminarAnalisisBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		eliminarAnalisisBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(20,20), AgregarVisita.class.getResource("/iconos/erase0.png")));
		eliminarAnalisisBtn.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(20,20), AgregarVisita.class.getResource("/iconos/erase1.png")));
		eliminarAnalisisBtn.setContentAreaFilled(false);
		eliminarAnalisisBtn.setBounds(229, 348, 24, 24);
		panelBase.add(eliminarAnalisisBtn);
		
		adicionarEspecialidadBtn = new JButton("");
		adicionarEspecialidadBtn.setEnabled(false);
		adicionarEspecialidadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = DialogosAuxiliares.agregarEspecialidadRemitida(especialidadesRemitidas);
				if(s!=null) {
					especialidadesRemitidas.add(s);
					modeloEspecialidades.actualizar(especialidadesRemitidas);
				}
			}
		});
		adicionarEspecialidadBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		adicionarEspecialidadBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(20,20), AgregarVisita.class.getResource("/iconos/add0.png")));
		adicionarEspecialidadBtn.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(20,20), AgregarVisita.class.getResource("/iconos/add1.png")));
		adicionarEspecialidadBtn.setContentAreaFilled(false);
		adicionarEspecialidadBtn.setBounds(566, 348, 24, 24);
		panelBase.add(adicionarEspecialidadBtn);
		
		eliminarEspecialidadBtn = new JButton("");
		eliminarEspecialidadBtn.setEnabled(false);
		eliminarEspecialidadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = tablaEspecialidades.getSelectedRow();
				if(i!=-1) {
					especialidadesRemitidas.remove(modeloEspecialidades.getValueAt(i, 0));
					modeloEspecialidades.actualizar(especialidadesRemitidas);
				}
			}
		});
		eliminarEspecialidadBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		eliminarEspecialidadBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(20,20), AgregarVisita.class.getResource("/iconos/erase0.png")));
		eliminarEspecialidadBtn.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(20,20), AgregarVisita.class.getResource("/iconos/erase1.png")));
		eliminarEspecialidadBtn.setContentAreaFilled(false);
		eliminarEspecialidadBtn.setBounds(542, 348, 24, 24);
		panelBase.add(eliminarEspecialidadBtn);
		
		aceptarBtn = new BotonAnimacion();
		aceptarBtn.setEnabled(false);
		aceptarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!validarComponentes())
					JOptionPane.showMessageDialog(rootPane, "Compruebe los datos de los campos señalados","ERROR",JOptionPane.ERROR_MESSAGE);
				else if(JOptionPane.showConfirmDialog(rootPane, "¿Desea agregar la visita?", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					agregarVisita();
					JOptionPane.showMessageDialog(rootPane, "La visita fue agregada correctamente", null, JOptionPane.INFORMATION_MESSAGE);
					AppPrincipal.actualizarDatos();
					dispose();
				}
			}
		});
		aceptarBtn.setText("Aceptar");
		aceptarBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		aceptarBtn.setEffectColor(new Color(51, 202, 223));
		aceptarBtn.setBackground(new Color(47, 184, 176));
		aceptarBtn.setBounds(366, 551, 107, 27);
		panelBase.add(aceptarBtn);
		
		cancelarBtn = new BotonAnimacion();
		cancelarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(rootPane, DefinicionesInterfazGrafica.PREGUNTA_SALIR_EDITAR, null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		cancelarBtn.setText("Cancelar");
		cancelarBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		cancelarBtn.setEffectColor(new Color(178, 34, 34));
		cancelarBtn.setBackground(Color.RED);
		cancelarBtn.setBounds(483, 551, 107, 27);
		panelBase.add(cancelarBtn);
		
		this.setLocationRelativeTo(null);
		
	}
	
	private void visibilidadComponentes(boolean b) {
		diagnosticoLbl.setEnabled(b);
		diagnosticoTxt.setEnabled(b);
		tratamientoLbl.setEnabled(b);
		tratamientoTxt.setEnabled(b);
		analisisIndicadosLbl.setEnabled(b);
		eliminarAnalisisBtn.setEnabled(b);
		adicionarAnalisisBtn.setEnabled(b);
		tablaAnalisisIndicados.setEnabled(b);
		especialidadesRemitidasLbl.setEnabled(b);
		adicionarEspecialidadBtn.setEnabled(b);
		eliminarEspecialidadBtn.setEnabled(b);
		tablaEspecialidades.setEnabled(b);
		aceptarBtn.setEnabled(b);
	}
	
	private boolean validarComponentes() {
		boolean esValido = true;
		
		if(!Validaciones.validarTamString(diagnosticoTxt.getText().trim(), Definiciones.TAM_MIN_STRING_DIAGNOSTICO, Definiciones.TAM_MAX_STRING_DIAGNOSTICO)) {
			esValido=false;
			diagnosticoLbl.setForeground(Color.RED);
		}
		if(!Validaciones.validarTamString(tratamientoTxt.getText().trim(), Definiciones.TAM_MIN_STRING_TRATAMIENTO, Definiciones.TAM_MAX_STRING_TRATAMIENTO)) {
			esValido=false;
			tratamientoLbl.setForeground(Color.RED);
		}
			
		
		return esValido;
	}
	
	private void agregarVisita() {
		String diagnostico = diagnosticoTxt.getText().trim();
		String tratamiento = tratamientoTxt.getText().trim();
		CMF.getInstancia().addVisita(diagnostico, (String)seleccionarPaciente.getSelectedItem(), tratamiento, analisisIndicados, especialidadesRemitidas);
	}
}
