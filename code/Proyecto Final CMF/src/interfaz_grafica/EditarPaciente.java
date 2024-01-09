package interfaz_grafica;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import componentes.BotonAnimacion;
import definiciones.Definiciones;
import definiciones.DefinicionesInterfazGrafica;
import modelos.CallePrincipalComboBoxModel;
import modelos.EnfermedadesCronicasTableModel;
import modelos.NoCasaComboBoxModel;
import modelos.VacunacionesTableModel;
import nucleo.CMF;
import nucleo.Direccion;
import nucleo.Paciente;
import nucleo.PacienteFemenina;
import nucleo.Vacuna;
import utilidades.Auxiliares;
import utilidades.AuxiliaresInterfazGrafica;
import utilidades.DialogosAuxiliares;
import utilidades.PiscinaDatos;
import utilidades.Validaciones;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import componentes.Imagen;
import componentes.JTextFieldModificado;

import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import java.awt.Cursor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class EditarPaciente extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panelBase = new JPanel();
	private JPanel panelSuperior;
	private JLabel editarPacienteLbl;
	private BotonAnimacion botonSalir;
	private int xMouseDrag;
	private int yMouseDrag;
	private Imagen logoPaciente;
	private JPanel panelInfPersonal;
	private JLabel nombreLbl;
	private JTextFieldModificado nombreTxt;
	private JLabel primerApellidoLbl;
	private JTextFieldModificado primerApellidoTxt;
	private JLabel segundoApellidoLbl;
	private JTextFieldModificado segundoApellidoTxt;
	private JLabel carnetIdentidadLbl;
	private JLabel carnetIdentidadTxt;
	private JLabel sexoLbl;
	private JLabel sexoTxt;
	private JLabel edadLbl;
	private JLabel edadTxt;
	private JPanel panelInfMedica;
	private JLabel historiaClinicaLbl;
	private JLabel historiaClinicaTxt;
	private JScrollPane panelEnfermedadesCronicas;
	private Imagen logoEnfermedades;
	private JLabel enfermedadesLbl;
	private Imagen logoVacunacion;
	private JScrollPane panelVacunacion;
	private JLabel vacunacionLbl;
	private JLabel ultimaPruebaLbl;
	private JDateChooser ultimaPruebaCitologica;
	private BotonAnimacion aceptarBtn;
	private BotonAnimacion cancelarBtn;
	private JButton addEnfermedadBtn;
	private JButton eraseEnfermedadBtn;
	private JButton addVacunacionBtn;
	private Paciente paciente;
	private JComboBox<String> numeroCasaComboBox;
	private JComboBox<String> callePrincipalComboBox;
	private JCheckBox estaEmbarazada;
	private JCheckBox tienePruebaCitologica;
	private JTable tablaEnfermedades;
	private JTable tablaVacunacion;
	private EnfermedadesCronicasTableModel modeloEnfermedades;
	private VacunacionesTableModel modeloVacunacion;
	private ArrayList<String> enfermedadesSeleccionadas;
	private ArrayList<Vacuna> vacunasSeleccionadas;
	private JLabel direccionLbl;
	
	public EditarPaciente(JFrame parent, final int indicePaciente) {
		super(parent,true);
		this.paciente=CMF.getInstancia().getPaciente(indicePaciente);
		enfermedadesSeleccionadas = new ArrayList<String>();
		vacunasSeleccionadas = new ArrayList<Vacuna>();
		this.setUndecorated(true);
		setBounds(100, 100, 691, 589);
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
		panelSuperior.setBounds(3, 3, 685, 40);
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
				if(JOptionPane.showConfirmDialog(rootPane, DefinicionesInterfazGrafica.PREGUNTA_SALIR_EDITAR, null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
					dispose();
			}
		});
		botonSalir.setText("X");
		botonSalir.setForeground(Color.BLACK);
		botonSalir.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		botonSalir.setEffectColor(new Color(178, 34, 34));
		botonSalir.setBackground(Color.LIGHT_GRAY);
		botonSalir.setBounds(643, 4, 32, 32);
		panelSuperior.add(botonSalir);
		
		editarPacienteLbl = new JLabel("Editar Paciente");
		editarPacienteLbl.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		editarPacienteLbl.setBounds(10, 5, 243, 29);
		panelSuperior.add(editarPacienteLbl);
		
		logoPaciente = new Imagen(new ImageIcon(EditarPaciente.class.getResource("/iconos/pacienteLogo.png")));
		logoPaciente.setBounds(288, 43, 115, 115);
		panelBase.add(logoPaciente);
		
		panelInfPersonal = new JPanel();
		panelInfPersonal.setLayout(null);
		panelInfPersonal.setBorder(new TitledBorder(new LineBorder(DefinicionesInterfazGrafica.VERDE_AZULADO_1, 3, true), "Informaci\u00F3n Personal", TitledBorder.LEADING, TitledBorder.TOP, new Font("Roboto Medium", Font.PLAIN, 18), Color.BLACK));
		panelInfPersonal.setBackground(Color.WHITE);
		panelInfPersonal.setBounds(10, 144, 671, 131);
		panelBase.add(panelInfPersonal);
		
		nombreLbl = new JLabel("Nombre:");
		nombreLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		nombreLbl.setBorder(null);
		nombreLbl.setBounds(10, 22, 67, 24);
		panelInfPersonal.add(nombreLbl);
		
		nombreTxt = new JTextFieldModificado();
		nombreTxt.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				nombreTxt.putClientProperty("JComponent.outline", null);
			}
		});
		nombreTxt.setLimite(DefinicionesInterfazGrafica.CANT_MAX_CARACTERES_GENERAL);
		nombreTxt.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		nombreTxt.setBounds(77, 22, 220, 24);
		panelInfPersonal.add(nombreTxt);
		
		primerApellidoLbl = new JLabel("Primer Apellido:");
		primerApellidoLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		primerApellidoLbl.setBorder(null);
		primerApellidoLbl.setBounds(10, 57, 115, 24);
		panelInfPersonal.add(primerApellidoLbl);
		
		primerApellidoTxt = new JTextFieldModificado();
		primerApellidoTxt.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				primerApellidoTxt.putClientProperty("JComponent.outline", null);
			}
		});
		primerApellidoTxt.setLimite(DefinicionesInterfazGrafica.CANT_MAX_CARACTERES_GENERAL);
		primerApellidoTxt.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		primerApellidoTxt.setBounds(124, 57, 173, 24);
		panelInfPersonal.add(primerApellidoTxt);
		
		segundoApellidoLbl = new JLabel("Segundo Apellido:");
		segundoApellidoLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		segundoApellidoLbl.setBorder(null);
		segundoApellidoLbl.setBounds(10, 92, 127, 24);
		panelInfPersonal.add(segundoApellidoLbl);
		
		segundoApellidoTxt = new JTextFieldModificado();
		segundoApellidoTxt.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				segundoApellidoTxt.putClientProperty("JComponent.outline", null);
			}
		});
		segundoApellidoTxt.setLimite(DefinicionesInterfazGrafica.CANT_MAX_CARACTERES_GENERAL);
		segundoApellidoTxt.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		segundoApellidoTxt.setBounds(137, 92, 160, 24);
		panelInfPersonal.add(segundoApellidoTxt);
		
		carnetIdentidadLbl = new JLabel("Carnet de Identidad:");
		carnetIdentidadLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		carnetIdentidadLbl.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		carnetIdentidadLbl.setBounds(318, 22, 343, 24);
		panelInfPersonal.add(carnetIdentidadLbl);
		
		carnetIdentidadTxt = new JLabel();
		carnetIdentidadTxt.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		carnetIdentidadTxt.setToolTipText("");
		carnetIdentidadTxt.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		carnetIdentidadTxt.setBounds(456, 22, 205, 24);
		panelInfPersonal.add(carnetIdentidadTxt);
		
		sexoLbl = new JLabel("Sexo:");
		sexoLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		sexoLbl.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		sexoLbl.setBounds(318, 92, 187, 24);
		panelInfPersonal.add(sexoLbl);
		
		sexoTxt = new JLabel();
		sexoTxt.setToolTipText("");
		sexoTxt.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		sexoTxt.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		sexoTxt.setBounds(361, 92, 144, 24);
		panelInfPersonal.add(sexoTxt);
		
		edadLbl = new JLabel("Edad:");
		edadLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		edadLbl.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		edadLbl.setBounds(515, 92, 146, 24);
		panelInfPersonal.add(edadLbl);
		
		edadTxt = new JLabel();
		edadTxt.setToolTipText("");
		edadTxt.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		edadTxt.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		edadTxt.setBounds(558, 92, 103, 24);
		panelInfPersonal.add(edadTxt);
		
		callePrincipalComboBox = new JComboBox<String>();
		callePrincipalComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callePrincipalComboBox.putClientProperty("JComponent.outline", null);
			}
		});
		callePrincipalComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		callePrincipalComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int indice = callePrincipalComboBox.getSelectedIndex();
				String calle = (String)callePrincipalComboBox.getSelectedItem();
				if(indice!=-1) {
					if(indice==0) {
						numeroCasaComboBox.putClientProperty("JComponent.outline", null);
						numeroCasaComboBox.setEnabled(false);
					}
					else {
						numeroCasaComboBox.setEnabled(true);
						numeroCasaComboBox.setModel(new NoCasaComboBoxModel(PiscinaDatos.getListadoNumeroCasaCalle(calle)));
					}
				}
			}
		});
		callePrincipalComboBox.setModel(new CallePrincipalComboBoxModel(PiscinaDatos.getListadoCallesPrincipales()));
		callePrincipalComboBox.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		callePrincipalComboBox.setBounds(391, 58, 145, 22);
		panelInfPersonal.add(callePrincipalComboBox);
		
		
		numeroCasaComboBox = new JComboBox<String>();
		numeroCasaComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroCasaComboBox.putClientProperty("JComponent.outline", null);
			}
		});
		numeroCasaComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		numeroCasaComboBox.setModel(new NoCasaComboBoxModel(new ArrayList<String>()));
		numeroCasaComboBox.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		numeroCasaComboBox.setBounds(546, 58, 115, 22);
		panelInfPersonal.add(numeroCasaComboBox);
		
		direccionLbl = new JLabel("Direcci\u00F3n:");
		direccionLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		direccionLbl.setBorder(null);
		direccionLbl.setBounds(318, 57, 81, 24);
		panelInfPersonal.add(direccionLbl);
		
		panelInfMedica = new JPanel();
		panelInfMedica.setLayout(null);
		panelInfMedica.setBorder(new TitledBorder(new LineBorder(DefinicionesInterfazGrafica.VERDE_AZULADO_1, 3, true), "Informaci\u00F3n M\u00E9dica", TitledBorder.LEADING, TitledBorder.TOP,new Font("Roboto Medium", Font.PLAIN, 18), Color.BLACK));
		panelInfMedica.setBackground(Color.WHITE);
		panelInfMedica.setBounds(10, 286, 671, 252);
		panelBase.add(panelInfMedica);
		
		panelEnfermedadesCronicas = new JScrollPane();
		panelEnfermedadesCronicas.setBackground(Color.WHITE);
		panelEnfermedadesCronicas.setBounds(10, 90, 299, 116);
		panelInfMedica.add(panelEnfermedadesCronicas);
		
		tablaEnfermedades = new JTable();
		modeloEnfermedades = new EnfermedadesCronicasTableModel();
		tablaEnfermedades.setModel(modeloEnfermedades);
		tablaEnfermedades.setTableHeader(null);
		tablaEnfermedades.setSelectionForeground(Color.BLACK);
		tablaEnfermedades.setSelectionBackground(DefinicionesInterfazGrafica.VERDE_AZULADO_1);
		tablaEnfermedades.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		panelEnfermedadesCronicas.setViewportView(tablaEnfermedades);
		
		logoEnfermedades = new Imagen(new ImageIcon(EditarPaciente.class.getResource("/iconos/enfermedadCronica.png")));
		logoEnfermedades.setBounds(10, 57, 28, 28);
		panelInfMedica.add(logoEnfermedades);
		
		enfermedadesLbl = new JLabel("Enfermedades Cr\u00F3nicas");
		enfermedadesLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		enfermedadesLbl.setBounds(48, 62, 170, 24);
		panelInfMedica.add(enfermedadesLbl);
		
		logoVacunacion = new Imagen(new ImageIcon(EditarPaciente.class.getResource("/iconos/vacunacion.png")));
		logoVacunacion.setBounds(379, 57, 28, 28);
		panelInfMedica.add(logoVacunacion);
		
		panelVacunacion = new JScrollPane();
		panelVacunacion.setBackground(Color.WHITE);
		panelVacunacion.setBounds(378, 90, 283, 116);
		panelInfMedica.add(panelVacunacion);
		
		modeloVacunacion = new VacunacionesTableModel();
		tablaVacunacion = new JTable();
		tablaVacunacion.setModel(modeloVacunacion);
		tablaVacunacion.setTableHeader(null);
		tablaVacunacion.setSelectionForeground(Color.BLACK);
		tablaVacunacion.setSelectionBackground(DefinicionesInterfazGrafica.VERDE_AZULADO_1);
		tablaVacunacion.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		panelVacunacion.setViewportView(tablaVacunacion);
		
		vacunacionLbl = new JLabel("Vacunaci\u00F3n");
		vacunacionLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		vacunacionLbl.setBounds(417, 61, 93, 24);
		panelInfMedica.add(vacunacionLbl);
		
		ultimaPruebaLbl = new JLabel("\u00DAltima Prueba Citol\u00F3gica:\r\n");
		ultimaPruebaLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ultimaPruebaLbl.setForeground(Color.BLACK);
			}
		});
		ultimaPruebaLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		ultimaPruebaLbl.setBorder(null);
		ultimaPruebaLbl.setBounds(10, 27, 180, 24);
		panelInfMedica.add(ultimaPruebaLbl);
		
		ultimaPruebaCitologica = new JDateChooser("dd/MM/yyyy","##/##/####",'_');
		ultimaPruebaCitologica.setBounds(189, 27, 143, 24);
		panelInfMedica.add(ultimaPruebaCitologica);
		ultimaPruebaCitologica.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		
		historiaClinicaLbl = new JLabel("No. Historia Clinica:");
		historiaClinicaLbl.setBounds(10, 217, 143, 24);
		panelInfMedica.add(historiaClinicaLbl);
		historiaClinicaLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		historiaClinicaLbl.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		
		historiaClinicaTxt = new JLabel();
		historiaClinicaTxt.setBounds(153, 217, 137, 24);
		panelInfMedica.add(historiaClinicaTxt);
		historiaClinicaTxt.setToolTipText("");
		historiaClinicaTxt.setText("HC-68-155-2");
		historiaClinicaTxt.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		historiaClinicaTxt.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		
		addEnfermedadBtn = new JButton("");
		addEnfermedadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s =DialogosAuxiliares.agregarEnfermedad(enfermedadesSeleccionadas);
				if(s!=null) {
					enfermedadesSeleccionadas.add(s);
					modeloEnfermedades.actualizar(enfermedadesSeleccionadas);
					
				}
			}
		});
		addEnfermedadBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addEnfermedadBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(20,20), EditarPaciente.class.getResource("/iconos/add0.png")));
		addEnfermedadBtn.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(20,20), EditarPaciente.class.getResource("/iconos/add1.png")));
		addEnfermedadBtn.setContentAreaFilled(false);
		addEnfermedadBtn.setBounds(285, 61, 24, 24);
		panelInfMedica.add(addEnfermedadBtn);
		
		eraseEnfermedadBtn = new JButton("");
		eraseEnfermedadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = tablaEnfermedades.getSelectedRow();
				if(i!=-1) {
					enfermedadesSeleccionadas.remove(modeloEnfermedades.getValueAt(i, 0));
					modeloEnfermedades.actualizar(enfermedadesSeleccionadas);
				}
			}
		});
		eraseEnfermedadBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		eraseEnfermedadBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(20,20), EditarPaciente.class.getResource("/iconos/erase0.png")));
		eraseEnfermedadBtn.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(20,20), EditarPaciente.class.getResource("/iconos/erase1.png")));
		eraseEnfermedadBtn.setContentAreaFilled(false);
		eraseEnfermedadBtn.setBounds(261, 61, 24, 24);
		panelInfMedica.add(eraseEnfermedadBtn);
		
		addVacunacionBtn = new JButton("");
		addVacunacionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date fechaNacimiento = paciente.getFechaNacimiento();
				ArrayList<String> vacunas = new ArrayList<String>();
				for(Vacuna v : vacunasSeleccionadas)
					vacunas.add(v.getNombreVacuna());
				
				Vacuna v = DialogosAuxiliares.agregarVacunacion(vacunas, fechaNacimiento);
				if(v!=null) {
					vacunasSeleccionadas.add(v);
					modeloVacunacion.actualizar(vacunasSeleccionadas);
				}
			}
		});
		addVacunacionBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addVacunacionBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(20,20), EditarPaciente.class.getResource("/iconos/add0.png")));
		addVacunacionBtn.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(20,20), EditarPaciente.class.getResource("/iconos/add1.png")));
		addVacunacionBtn.setContentAreaFilled(false);
		addVacunacionBtn.setBounds(637, 61, 24, 24);
		panelInfMedica.add(addVacunacionBtn);
		
		estaEmbarazada = new JCheckBox("Embarazada");
		estaEmbarazada.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		estaEmbarazada.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		estaEmbarazada.setBounds(417, 28, 137, 23);
		panelInfMedica.add(estaEmbarazada);
		
		tienePruebaCitologica = new JCheckBox("");
		tienePruebaCitologica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ultimaPruebaLbl.setEnabled(tienePruebaCitologica.isSelected());
				ultimaPruebaCitologica.setEnabled(tienePruebaCitologica.isSelected());
			}
		});
		tienePruebaCitologica.setBounds(338, 28, 24, 23);
		panelInfMedica.add(tienePruebaCitologica);
		
		aceptarBtn = new BotonAnimacion();
		aceptarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!validarComponentes())
					JOptionPane.showMessageDialog(rootPane, "Compruebe los datos de los campos señalados","ERROR",JOptionPane.ERROR_MESSAGE);
				else if(JOptionPane.showConfirmDialog(rootPane, "¿Desea guardar los cambios?", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					editarPaciente();
					JOptionPane.showMessageDialog(rootPane, "Los cambios fueron guardados correctamente", null, JOptionPane.INFORMATION_MESSAGE);
					AppPrincipal.actualizarDatos();
					dispose();
				}
			}
		});
		aceptarBtn.setText("Aceptar");
		aceptarBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		aceptarBtn.setEffectColor(new Color(51, 202, 223));
		aceptarBtn.setBackground(new Color(47, 184, 176));
		aceptarBtn.setBounds(457, 549, 107, 27);
		panelBase.add(aceptarBtn);
		
		cancelarBtn = new BotonAnimacion();
		cancelarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(rootPane, DefinicionesInterfazGrafica.PREGUNTA_SALIR_EDITAR, null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
					dispose();
			}
		});
		cancelarBtn.setText("Cancelar");
		cancelarBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		cancelarBtn.setEffectColor(new Color(178, 34, 34));
		cancelarBtn.setBackground(Color.RED);
		cancelarBtn.setBounds(574, 549, 107, 27);
		panelBase.add(cancelarBtn);
		
		rellenarDatos();
		this.setLocationRelativeTo(null);
	}
	
	private void rellenarDatos() {
		nombreTxt.setText(paciente.getNombre());
		primerApellidoTxt.setText(paciente.getPrimerApellido());
		segundoApellidoTxt.setText(paciente.getSegundoApellido());
		carnetIdentidadTxt.setText(paciente.getCarnetIdentidad());
		callePrincipalComboBox.setSelectedItem(paciente.getDireccion().getCallePrincipal());
		numeroCasaComboBox.setSelectedItem(paciente.getDireccion().getNumeroCasa());
		
		char sexo = paciente.getSexo();
		int edad = paciente.getEdad();
		
		sexoTxt.setText(Auxiliares.convertirSexoString(sexo));
		edadTxt.setText(""+edad);
		historiaClinicaTxt.setText(paciente.getHistoriaClinica().getNumeroHistoriaClinica());
		
		if(Character.toUpperCase(sexo) == 'F' && Validaciones.validarEnteroRango(edad, Definiciones.EDAD_MIN_PRUEBAS_EMBARAZO_MUJER, Definiciones.EDAD_MAX_PRUEBAS_EMBARAZO_MUJER)) {
			estaEmbarazada.setSelected(((PacienteFemenina)paciente).getEstaEmbarazada());
			ultimaPruebaCitologica.setSelectableDateRange(Auxiliares.sumarAnyosFecha(paciente.getFechaNacimiento(), Definiciones.EDAD_MIN_PRUEBAS_EMBARAZO_MUJER), new Date());
			if(((PacienteFemenina)paciente).getFechaUltimaPruebaCitologica()==null) {
				ultimaPruebaLbl.setEnabled(false);
				ultimaPruebaCitologica.setEnabled(false);
				tienePruebaCitologica.setSelected(false);
			}
			else {
				ultimaPruebaCitologica.setMinSelectableDate(((PacienteFemenina)paciente).getFechaUltimaPruebaCitologica());
				tienePruebaCitologica.setSelected(true);
				tienePruebaCitologica.setEnabled(false);
				ultimaPruebaLbl.setEnabled(true);
				ultimaPruebaCitologica.setDate(((PacienteFemenina)paciente).getFechaUltimaPruebaCitologica());
			}
		}
		else {
			ultimaPruebaLbl.setEnabled(false);
			ultimaPruebaCitologica.setEnabled(false);
			tienePruebaCitologica.setEnabled(false);
			estaEmbarazada.setEnabled(false);
		}
		
		this.enfermedadesSeleccionadas.addAll(paciente.getEnfermedadesCronicas());
		this.vacunasSeleccionadas.addAll(paciente.getVacunaciones());
		
		modeloEnfermedades.actualizar(enfermedadesSeleccionadas);
		modeloVacunacion.actualizar(vacunasSeleccionadas);
	}
	
	public boolean validarComponentes() {
		boolean esValido = true;
		
		String nombre = nombreTxt.getText().trim();
		String primerApellido = primerApellidoTxt.getText().trim();
		String segundoApellido = segundoApellidoTxt.getText().trim();
		
		int indiceCalle = callePrincipalComboBox.getSelectedIndex();
		int indiceNoCasa = numeroCasaComboBox.getSelectedIndex();
		
		if(indiceCalle==-1 || indiceCalle==0) {
			esValido=false;
			callePrincipalComboBox.putClientProperty("JComponent.outline", "error");
		}
		else if(indiceNoCasa==-1 || indiceNoCasa==0) {
			esValido=false;
			numeroCasaComboBox.putClientProperty("JComponent.outline", "error");
		}
		
		if(!Validaciones.validarStringNoVacio(nombre) || !Validaciones.validarStringTodoLetra(nombre) || !Validaciones.validarTamString(nombre, Definiciones.CANT_MIN_CARACTERES_NOMBRE, Definiciones.CANT_MAX_CARACTERES_NOMBRE)) {
			esValido=false;
			nombreTxt.putClientProperty("JComponent.outline", "error");
		}
		if(!Validaciones.validarStringNoVacio(primerApellido) || !Validaciones.validarStringTodoLetra(primerApellido) || !Validaciones.validarTamString(primerApellido, Definiciones.CANT_MIN_CARACTERES_APELLIDO, Definiciones.CANT_MAX_CARACTERES_APELLIDO)) {
			esValido=false;
			primerApellidoTxt.putClientProperty("JComponent.outline", "error");
		}
		if(!Validaciones.validarStringNoVacio(segundoApellido) || !Validaciones.validarStringTodoLetra(segundoApellido) || !Validaciones.validarTamString(segundoApellido, Definiciones.CANT_MIN_CARACTERES_APELLIDO, Definiciones.CANT_MAX_CARACTERES_APELLIDO)) {
			esValido=false;
			segundoApellidoTxt.putClientProperty("JComponent.outline", "error");
		}
		
		if(paciente.getSexo()=='F' && tienePruebaCitologica.isSelected()) {
			Date fecha = ultimaPruebaCitologica.getDate();
			Date fechaMin = ultimaPruebaCitologica.getMinSelectableDate();
			Date fechaMax = ultimaPruebaCitologica.getMaxSelectableDate();
			if(!Validaciones.validarRangoFecha(fecha, fechaMin, fechaMax)) { 
				esValido=false;
				ultimaPruebaLbl.setForeground(Color.RED);
			}
		}
		
		return esValido;
	}
	
	public void editarPaciente() {
		paciente.setNombre(nombreTxt.getText().trim());
		paciente.setPrimerApellido(primerApellidoTxt.getText().trim());
		paciente.setSegundoApellido(segundoApellidoTxt.getText().trim());
		
		Direccion dirActual = new Direccion((String)callePrincipalComboBox.getSelectedItem(), (String)numeroCasaComboBox.getSelectedItem());
		Direccion dirAnt = paciente.getDireccion();
		
		if(dirActual.compareTo(dirAnt)!=0) {
			paciente.setDireccion(dirActual);
			paciente.getHistoriaClinica().setNumeroHistoriaClinica(Auxiliares.generarNumeroHistoriaClinica(dirActual));
		}
		
		
		ArrayList<Vacuna> vacunaciones = paciente.getVacunaciones();
		for(Vacuna v : vacunasSeleccionadas) {
			boolean aux = true;
			for(int i=0;i<vacunaciones.size() && aux;i++) {
				if(vacunaciones.get(i).getNombreVacuna().equals(v.getNombreVacuna()))
					aux=false;
			}
			if(aux)
				paciente.addVacunacion(v.getFechaVacunacion(), v.getNombreVacuna());
		}
		
		int cantEnfermedades = paciente.getEnfermedadesCronicas().size();
		for(int i=0;i<cantEnfermedades;i++)
			paciente.removeEnfermedadCronica(0);
		for(int i=0;i<enfermedadesSeleccionadas.size();i++)
			paciente.addEnfermedadCronica(enfermedadesSeleccionadas.get(i));
		
		
		if(paciente instanceof PacienteFemenina) {
			((PacienteFemenina) paciente).setEstaEmbarazada(estaEmbarazada.isSelected());
			((PacienteFemenina) paciente).setFechaUltimaPruebaCitologica(tienePruebaCitologica.isSelected() ? ultimaPruebaCitologica.getDate() : null);
		}
		
	}
}
