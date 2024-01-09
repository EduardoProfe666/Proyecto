package interfaz_grafica;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import nucleo.Vacuna;
import utilidades.Auxiliares;
import utilidades.PiscinaDatos;
import utilidades.AuxiliaresInterfazGrafica;
import utilidades.DialogosAuxiliares;
import utilidades.Validaciones;

import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import componentes.Imagen;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import com.toedter.calendar.JDateChooser;
import javax.swing.border.MatteBorder;
import componentes.JTextFieldModificado;
import javax.swing.JFormattedTextField;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;

public class AgregarPaciente extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panelBase = new JPanel();
	private JPanel panelSuperior;
	private int xMouseDrag;
	private int yMouseDrag;
	private BotonAnimacion botonSalir;
	private JLabel agregarPacienteLbl;
	private JFormattedTextField carnetIdentidadTxt;
	private JLabel sexoLbl;
	private JLabel sexoTxt;
	private JLabel edadLbl;
	private JLabel edadTxt;
	private JLabel ultimaPruebaLbl;
	private JDateChooser ultimaPruebaCitologica;
	private JLabel enfermedadesLbl;
	private Imagen logoEnfermedades;
	private JButton eraseEnfermedadBtn;
	private JButton addEnfermedadBtn;
	private JButton eraseVacunacionBtn;
	private JButton addVacunacionBtn;
	private JLabel vacunacionLbl;
	private Imagen logoVacunacion;
	private JTextFieldModificado segundoApellidoTxt;
	private JLabel segundoApellidoLbl;
	private JLabel primerApellidoLbl;
	private JTextFieldModificado primerApellidoTxt;
	private JLabel nombreLbl;
	private JTextFieldModificado nombreTxt;
	private JLabel carnetIdentidadLbl;
	private Imagen logoPaciente;
	private JPanel panelInfPersonal;
	private JPanel panelInfMedica;
	private JScrollPane panelEnfermedadesCronicas;
	private JScrollPane panelVacunacion;
	private BotonAnimacion aceptarBtn;
	private BotonAnimacion cancelarBtn;
	private JComboBox<String> callePrincipalComboBox;
	private JComboBox<String> numeroCasaComboBox;
	private boolean ciValido;
	private JCheckBox embarazada;
	private JCheckBox tienePruebaCitologica;
	private JTable tablaEnfermedadesCronicas;
	private EnfermedadesCronicasTableModel modeloEnfermedades;
	private VacunacionesTableModel modeloVacunacion;
	private JTable tablaVacunacion;
	private ArrayList<String> enfermedadesSeleccionadas;
	private ArrayList<Vacuna> vacunasSeleccionadas;

	public AgregarPaciente(JFrame parent) throws ParseException {
		super(parent,true);
		ciValido=false;
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

		agregarPacienteLbl = new JLabel("Agregar Paciente");
		agregarPacienteLbl.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		agregarPacienteLbl.setBounds(10, 5, 329, 29);
		panelSuperior.add(agregarPacienteLbl);

		panelInfMedica = new JPanel();
		panelInfMedica.setLayout(null);
		panelInfMedica.setBorder(new TitledBorder(new LineBorder(DefinicionesInterfazGrafica.VERDE_AZULADO_1, 3, true), "Informaci\u00F3n M\u00E9dica", TitledBorder.LEADING, TitledBorder.TOP,new Font("Roboto Medium", Font.PLAIN, 18), Color.BLACK));
		panelInfMedica.setBackground(Color.WHITE);
		panelInfMedica.setBounds(13, 286, 666, 252);
		panelBase.add(panelInfMedica);

		ultimaPruebaCitologica = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		ultimaPruebaCitologica.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		ultimaPruebaCitologica.setBounds(189, 27, 142, 24);
		panelInfMedica.add(ultimaPruebaCitologica);

		panelEnfermedadesCronicas = new JScrollPane();
		panelEnfermedadesCronicas.setBackground(Color.WHITE);
		panelEnfermedadesCronicas.setBounds(10, 90, 299, 116);
		panelInfMedica.add(panelEnfermedadesCronicas);

		tablaEnfermedadesCronicas = new JTable();
		modeloEnfermedades = new EnfermedadesCronicasTableModel();
		tablaEnfermedadesCronicas.setModel(modeloEnfermedades);
		tablaEnfermedadesCronicas.setTableHeader(null);
		tablaEnfermedadesCronicas.setSelectionForeground(Color.BLACK);
		tablaEnfermedadesCronicas.setSelectionBackground(DefinicionesInterfazGrafica.VERDE_AZULADO_1);
		tablaEnfermedadesCronicas.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		panelEnfermedadesCronicas.setViewportView(tablaEnfermedadesCronicas);

		logoEnfermedades = new Imagen(new ImageIcon(AgregarPaciente.class.getResource("/iconos/enfermedadCronica.png")));
		logoEnfermedades.setBounds(10, 57, 28, 28);
		panelInfMedica.add(logoEnfermedades);

		enfermedadesLbl = new JLabel("Enfermedades Cr\u00F3nicas");
		enfermedadesLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		enfermedadesLbl.setBounds(48, 62, 170, 24);
		panelInfMedica.add(enfermedadesLbl);

		logoVacunacion = new Imagen(new ImageIcon(AgregarPaciente.class.getResource("/iconos/vacunacion.png")));
		logoVacunacion.setBounds(373, 57, 28, 28);
		panelInfMedica.add(logoVacunacion);

		panelVacunacion = new JScrollPane();
		panelVacunacion.setBackground(Color.WHITE);
		panelVacunacion.setBounds(373, 90, 283, 116);
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
		vacunacionLbl.setBounds(411, 61, 93, 24);
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
		ultimaPruebaLbl.setBounds(10, 27, 299, 24);
		panelInfMedica.add(ultimaPruebaLbl);

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
		addEnfermedadBtn.setBounds(285, 62, 24, 24);
		panelInfMedica.add(addEnfermedadBtn);

		eraseEnfermedadBtn = new JButton("");
		eraseEnfermedadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = tablaEnfermedadesCronicas.getSelectedRow();
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
		eraseEnfermedadBtn.setBounds(261, 62, 24, 24);
		panelInfMedica.add(eraseEnfermedadBtn);

		eraseVacunacionBtn = new JButton("");
		eraseVacunacionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = tablaVacunacion.getSelectedRow();
				if(i!=-1) {
					vacunasSeleccionadas.remove(i);
					modeloVacunacion.actualizar(vacunasSeleccionadas);
				}
			}
		});
		eraseVacunacionBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		eraseVacunacionBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(20,20), EditarPaciente.class.getResource("/iconos/erase0.png")));
		eraseVacunacionBtn.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(20,20), EditarPaciente.class.getResource("/iconos/erase1.png")));
		eraseVacunacionBtn.setContentAreaFilled(false);
		eraseVacunacionBtn.setBounds(608, 61, 24, 24);
		panelInfMedica.add(eraseVacunacionBtn);

		addVacunacionBtn = new JButton("");
		addVacunacionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date fechaNacimiento = Auxiliares.convertirFechaNacimientoCiDate(carnetIdentidadTxt.getText().trim());
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
		addVacunacionBtn.setBounds(632, 61, 24, 24);
		panelInfMedica.add(addVacunacionBtn);

		embarazada = new JCheckBox("Embarazada");
		embarazada.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		embarazada.setBounds(411, 28, 137, 23);
		panelInfMedica.add(embarazada);

		tienePruebaCitologica = new JCheckBox("");
		tienePruebaCitologica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarUltimaPruebaCitologica(tienePruebaCitologica.isSelected(), Auxiliares.convertirFechaNacimientoCiDate(carnetIdentidadTxt.getText().trim()));
			}
		});
		tienePruebaCitologica.setBounds(337, 27, 28, 23);
		panelInfMedica.add(tienePruebaCitologica);

		panelInfPersonal = new JPanel();
		panelInfPersonal.setLayout(null);
		panelInfPersonal.setBorder(new TitledBorder(new LineBorder(DefinicionesInterfazGrafica.VERDE_AZULADO_1, 3, true), "Informaci\u00F3n Personal", TitledBorder.LEADING, TitledBorder.TOP, new Font("Roboto Medium", Font.PLAIN, 18), Color.BLACK));
		panelInfPersonal.setBackground(Color.WHITE);
		panelInfPersonal.setBounds(13, 144, 668, 131);
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
		nombreTxt.setLimite(Definiciones.CANT_MAX_CARACTERES_NOMBRE);
		nombreTxt.setTipoValidacion(JTextFieldModificado.SOLO_LETRAS);
		nombreTxt.setFont(new Font("Roboto Medium", Font.ITALIC, 15));
		nombreTxt.setBounds(77, 22, 190, 24);
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
		primerApellidoTxt.setLimite(Definiciones.CANT_MAX_CARACTERES_APELLIDO);
		primerApellidoTxt.setTipoValidacion(JTextFieldModificado.SOLO_LETRAS);
		primerApellidoTxt.setFont(new Font("Roboto Medium", Font.ITALIC, 15));
		primerApellidoTxt.setBounds(124, 57, 143, 24);
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
		segundoApellidoTxt.setLimite(Definiciones.CANT_MAX_CARACTERES_APELLIDO);
		segundoApellidoTxt.setTipoValidacion(JTextFieldModificado.SOLO_LETRAS);
		segundoApellidoTxt.setFont(new Font("Roboto Medium", Font.ITALIC, 15));
		segundoApellidoTxt.setBounds(137, 92, 130, 24);
		panelInfPersonal.add(segundoApellidoTxt);

		carnetIdentidadLbl = new JLabel("Carnet de Identidad:");
		carnetIdentidadLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		carnetIdentidadLbl.setBorder(null);
		carnetIdentidadLbl.setBounds(299, 22, 138, 24);
		panelInfPersonal.add(carnetIdentidadLbl);

		sexoLbl = new JLabel("Sexo:");
		sexoLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		sexoLbl.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		sexoLbl.setBounds(299, 92, 43, 24);
		panelInfPersonal.add(sexoLbl);

		sexoTxt = new JLabel("");
		sexoTxt.setFont(new Font("Roboto Medium", Font.ITALIC, 15));
		sexoTxt.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		sexoTxt.setBounds(342, 92, 127, 24);
		panelInfPersonal.add(sexoTxt);

		edadLbl = new JLabel("Edad:");
		edadLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		edadLbl.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		edadLbl.setBounds(497, 92, 43, 24);
		panelInfPersonal.add(edadLbl);

		edadTxt = new JLabel("");
		edadTxt.setFont(new Font("Roboto Medium", Font.ITALIC, 15));
		edadTxt.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		edadTxt.setBounds(540, 92, 116, 24);
		panelInfPersonal.add(edadTxt);

		logoPaciente = new Imagen(new ImageIcon(AgregarPaciente.class.getResource("/iconos/pacienteLogo.png")));
		logoPaciente.setBounds(296, 43, 115, 115);
		panelBase.add(logoPaciente);

		aceptarBtn = new BotonAnimacion();
		aceptarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!validarComponentes())
					JOptionPane.showMessageDialog(rootPane, "Compruebe los datos de los campos señalados","ERROR",JOptionPane.ERROR_MESSAGE);
				else if(JOptionPane.showConfirmDialog(rootPane, "¿Desea agregar el paciente?", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					agregarPaciente();
					JOptionPane.showMessageDialog(rootPane, "El paciente fue agregado correctamente", null, JOptionPane.INFORMATION_MESSAGE);
					AppPrincipal.actualizarDatos();
					dispose();
				}
			}
		});
		aceptarBtn.setText("Aceptar");
		aceptarBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		aceptarBtn.setEffectColor(new Color(51, 202, 223));
		aceptarBtn.setBackground(new Color(47, 184, 176));
		aceptarBtn.setBounds(455, 549, 107, 27);
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
		cancelarBtn.setBounds(572, 549, 107, 27);
		panelBase.add(cancelarBtn);

		carnetIdentidadTxt = new JFormattedTextField(new MaskFormatter(DefinicionesInterfazGrafica.MASCARA_CI));
		carnetIdentidadTxt.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				carnetIdentidadTxt.putClientProperty("JComponent.outline", null);
				String ci = carnetIdentidadTxt.getText();
				ci = ci.trim();
				if(Validaciones.validarCI(ci, Definiciones.TAM_STRING_CI, Definiciones.EDAD_MIN, Definiciones.EDAD_MAX) && Validaciones.validarUnicidadCI(ci)) {
					ciValido = true;
					String sexo = Auxiliares.determinarSexoString(ci);
					Date fechaNacimiento = Auxiliares.convertirFechaNacimientoCiDate(ci);
					int edad = Auxiliares.determinarEdad(fechaNacimiento);
					if(sexo.equals("Femenino")) {
						if(Validaciones.validarEnteroRango(edad, Definiciones.EDAD_MIN_PRUEBAS_EMBARAZO_MUJER, Definiciones.EDAD_MAX_PRUEBAS_EMBARAZO_MUJER)) {
							revelarComponentesCi(edad, sexo, fechaNacimiento);
						}
						else {
							revelarComponentesCiMasculino(edad, sexo);
						}
					}
					else {
						revelarComponentesCiMasculino(edad, sexo);
					}
				}
				else {
					esconderComponentesCi();
					ciValido = false;
				}
			}
		});
		carnetIdentidadTxt.setToolTipText("Los campos de Sexo, Edad, \u00DAltima Prueba Citol\u00F3gica, Embarazada,\r\ny Vacunaci\u00F3n dependen de lo dispuesto en este campo.");
		carnetIdentidadTxt.setText("");
		carnetIdentidadTxt.setFont(new Font("Roboto Medium", Font.ITALIC, 15));
		carnetIdentidadTxt.setBounds(437, 22, 219, 24);
		carnetIdentidadTxt.setFocusLostBehavior(JFormattedTextField.COMMIT);
		panelInfPersonal.add(carnetIdentidadTxt);

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
		callePrincipalComboBox.setBounds(376, 58, 150, 22);
		panelInfPersonal.add(callePrincipalComboBox);

		numeroCasaComboBox = new JComboBox<String>();
		numeroCasaComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroCasaComboBox.putClientProperty("JComponent.outline", null);
			}
		});
		numeroCasaComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		numeroCasaComboBox.setModel(new NoCasaComboBoxModel(new ArrayList<String>()));
		numeroCasaComboBox.setEnabled(false);
		numeroCasaComboBox.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		numeroCasaComboBox.setBounds(536, 58, 120, 22);
		panelInfPersonal.add(numeroCasaComboBox);

		JLabel direccionLbl = new JLabel("Direcci\u00F3n:");
		direccionLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		direccionLbl.setBorder(null);
		direccionLbl.setBounds(299, 57, 81, 24);
		panelInfPersonal.add(direccionLbl);

		this.setLocationRelativeTo(null);
	}
	private void esconderComponentesCi() {
		esconderComponentesCiNoPruebas();
		sexoLbl.setEnabled(false);
		sexoTxt.setEnabled(false);
		edadLbl.setEnabled(false);
		edadTxt.setEnabled(false);
		vacunacionLbl.setEnabled(false);
		eraseVacunacionBtn.setEnabled(false);
		addVacunacionBtn.setEnabled(false);
		vacunasSeleccionadas = new ArrayList<Vacuna>();
		modeloVacunacion.actualizar(vacunasSeleccionadas);
	}
	private void esconderComponentesCiNoPruebas() {
		actualizarUltimaPruebaCitologica(false, null);
		tienePruebaCitologica.setEnabled(false);
		embarazada.setEnabled(false);
		embarazada.setEnabled(false);
	}
	private void revelarComponentesCi(int edad, String sexo, Date fechaMin) {
		revelarComponentesCiMasculino(edad,sexo);
		tienePruebaCitologica.setEnabled(true);
		actualizarUltimaPruebaCitologica(tienePruebaCitologica.isSelected(), fechaMin);
		embarazada.setEnabled(true);
		embarazada.setEnabled(true);
	}
	private void revelarComponentesCiMasculino(int edad, String sexo) {
		sexoLbl.setEnabled(true);
		sexoTxt.setEnabled(true);
		sexoTxt.setText(sexo);
		edadLbl.setEnabled(true);
		edadTxt.setEnabled(true);
		edadTxt.setText(""+edad);
		esconderComponentesCiNoPruebas();

		vacunacionLbl.setEnabled(true);
		eraseVacunacionBtn.setEnabled(true);
		addVacunacionBtn.setEnabled(true);
	}
	private void actualizarUltimaPruebaCitologica(boolean estaActivado, Date fechaMin) {
		ultimaPruebaLbl.setEnabled(estaActivado);
		ultimaPruebaCitologica.setEnabled(estaActivado);
		if(estaActivado)
			ultimaPruebaCitologica.setSelectableDateRange(Auxiliares.sumarAnyosFecha(fechaMin, Definiciones.EDAD_MIN_PRUEBAS_EMBARAZO_MUJER), new Date());
	}

	private boolean validarComponentes() {
		boolean esValido = true;

		String nombre = nombreTxt.getText().trim();
		String primerApellido = primerApellidoTxt.getText().trim();
		String segundoApellido = segundoApellidoTxt.getText().trim();
		String ci = carnetIdentidadTxt.getText();

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

		if(ciValido) {
			if(Auxiliares.determinarSexo(ci)=='F' && tienePruebaCitologica.isEnabled() && tienePruebaCitologica.isSelected()) {
				Date fecha = ultimaPruebaCitologica.getDate();
				Date fechaMin = ultimaPruebaCitologica.getMinSelectableDate();
				Date fechaMax = ultimaPruebaCitologica.getMaxSelectableDate();
				if(!Validaciones.validarRangoFecha(fecha, fechaMin, fechaMax)) { 
					esValido=false;
					ultimaPruebaLbl.setForeground(Color.RED);
				}
			}
		}
		else {
			carnetIdentidadTxt.putClientProperty("JComponent.outline", "error");
			esValido=false;
		}

		return esValido;
	}
	private void agregarPaciente() {
		String nombre = nombreTxt.getText().trim();
		String primerApellido = primerApellidoTxt.getText().trim();
		String segundoApellido = segundoApellidoTxt.getText().trim();
		String ci = carnetIdentidadTxt.getText();

		String calle = (String)callePrincipalComboBox.getSelectedItem();
		String noCasa = (String)numeroCasaComboBox.getSelectedItem();


		CMF cmfTemp = CMF.getInstancia();

		if(Auxiliares.determinarSexo(ci)=='F') {
			Date fechaPrueba = ultimaPruebaCitologica.isEnabled() ? ultimaPruebaCitologica.getDate() : null ;
			boolean estaEmbarazada = embarazada.isEnabled() && embarazada.isSelected();

			cmfTemp.addPacienteFemenina(nombre, primerApellido, segundoApellido, ci, new Direccion(calle, noCasa), fechaPrueba, estaEmbarazada);
		}
		else 
			cmfTemp.addPaciente(nombre, primerApellido, segundoApellido, ci, new Direccion(calle, noCasa));

		int indicePacienteGenerado = cmfTemp.getListadoPacientes().size()-1;

		for(String s : enfermedadesSeleccionadas)
			cmfTemp.getPaciente(indicePacienteGenerado).addEnfermedadCronica(s);

		for(Vacuna v : vacunasSeleccionadas) 
			cmfTemp.getPaciente(indicePacienteGenerado).addVacunacion(v.getFechaVacunacion(), v.getNombreVacuna());

	}
}
