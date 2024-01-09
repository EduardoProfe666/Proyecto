package interfaz_grafica;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import definiciones.Definiciones;
import definiciones.DefinicionesInterfazGrafica;
import modelos.EnfermedadesCronicasTableModel;
import modelos.VacunacionesTableModel;
import nucleo.CMF;
import nucleo.Paciente;
import nucleo.PacienteFemenina;
import utilidades.Auxiliares;
import utilidades.AuxiliaresInterfazGrafica;
import utilidades.Validaciones;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.LineBorder;

import componentes.BotonAnimacion;

import componentes.Imagen;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.Icon;

public class VerPaciente extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelBase;
	private JPanel panelSuperior;
	private BotonAnimacion botonSalir;
	private int xMouseDrag;
	private int yMouseDrag;
	private JPanel panelInfPersonal;
	private JPanel panelInfMedica;
	private JLabel nombreTxt;
	private JLabel historiaClinicaLbl;
	private JLabel historiaClinicaTxt;
	private JLabel primerApellidoLbl;
	private JLabel primerApellidoTxt;
	private JLabel segundoApellidoLbl;
	private JLabel segundoApellidoTxt;
	private JLabel carnetIdentidadLbl;
	private JLabel carnetIdentidadTxt;
	private JLabel direccionLbl;
	private JLabel direccionTxt;
	private JLabel sexoLbl;
	private JLabel sexoTxt;
	private JLabel edadLbl;
	private JLabel edadTxt;
	private JLabel nombreLbl;
	private Imagen imagen;
	private JLabel infPacienteLbl;
	private JButton historiaClinicaBtn;
	private JScrollPane panelEnfermedadesCronicas;
	private JLabel enfermedadesLbl;
	private Imagen logoEnfermedades;
	private JLabel vacunacionLbl;
	private JLabel ultimaPruebaLbl;
	private JLabel ultimaPruebaTxt;
	private JLabel embarazadaLbl;
	private JLabel embarazadaTxt;
	private BotonAnimacion editarBoton;
	private JScrollPane panelVacunacion;
	private Imagen logoVacunacion;
	private Paciente paciente;
	private JTable tablaEnfermedadesCronicas;
	EnfermedadesCronicasTableModel modeloEnfermedades;
	private JTable tablaVacunacion;
	private VacunacionesTableModel modeloVacunacion;
	
	public VerPaciente(JFrame parent, final int indicePaciente, boolean editable) {
		super(parent,true);
		this.paciente = CMF.getInstancia().getPaciente(indicePaciente);
		this.setBounds(100, 100, DefinicionesInterfazGrafica.DIMENSION_DIALOGOS.width, DefinicionesInterfazGrafica.DIMENSION_DIALOGOS.height);
		this.setUndecorated(true);
		this.setResizable(false);
		
		panelBase = new JPanel();
		panelBase.setBackground(Color.WHITE);
		panelBase.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(panelBase);
		panelBase.setLayout(null);
		
		panelSuperior = new JPanel();
		panelSuperior.setBackground(Color.WHITE);
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
		panelSuperior.setBounds(3, 3, 594, 40);
		panelBase.add(panelSuperior);
		panelSuperior.setLayout(null);
		
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
		botonSalir.setBounds(556, 4, 32, 32);
		panelSuperior.add(botonSalir);
		botonSalir.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		botonSalir.setBackground(Color.LIGHT_GRAY);
		botonSalir.setForeground(Color.BLACK);
		botonSalir.setEffectColor(new Color(178, 34, 34));
		botonSalir.setText("X");
		
		infPacienteLbl = new JLabel("Informaci\u00F3n del Paciente");
		infPacienteLbl.setBounds(10, 5, 243, 29);
		panelSuperior.add(infPacienteLbl);
		infPacienteLbl.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		
		imagen = new Imagen(new ImageIcon(VerPaciente.class.getResource("/iconos/pacienteLogo.png")));
		imagen.setBounds(242, 43, 115, 115);
		panelBase.add(imagen);
		
		panelInfPersonal = new JPanel();
		panelInfPersonal.setBackground(Color.WHITE);
		panelInfPersonal.setBorder(new TitledBorder(new LineBorder(DefinicionesInterfazGrafica.VERDE_AZULADO_1, 3, true), "Informaci\u00F3n Personal", TitledBorder.LEADING, TitledBorder.TOP, new Font("Roboto Medium", Font.PLAIN, 18), Color.BLACK));
		panelInfPersonal.setBounds(10, 144, 580, 131);
		panelBase.add(panelInfPersonal);
		panelInfPersonal.setLayout(null);
		
		nombreLbl = new JLabel("Nombre:");
		nombreLbl.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		nombreLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		nombreLbl.setBounds(10, 22, 67, 24);
		panelInfPersonal.add(nombreLbl);
		
		nombreTxt = new JLabel();
		nombreTxt.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		nombreTxt.setFont(new Font("Roboto Medium", Font.ITALIC, 15));
		nombreTxt.setBounds(77, 22, 171, 24);
		panelInfPersonal.add(nombreTxt);
		
		primerApellidoLbl = new JLabel("Primer Apellido:");
		primerApellidoLbl.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		primerApellidoLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		primerApellidoLbl.setBounds(10, 57, 115, 24);
		panelInfPersonal.add(primerApellidoLbl);
		
		primerApellidoTxt = new JLabel();
		primerApellidoTxt.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		primerApellidoTxt.setFont(new Font("Roboto Medium", Font.ITALIC, 15));
		primerApellidoTxt.setBounds(124, 57, 124, 24);
		panelInfPersonal.add(primerApellidoTxt);
		
		segundoApellidoLbl = new JLabel("Segundo Apellido:");
		segundoApellidoLbl.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		segundoApellidoLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		segundoApellidoLbl.setBounds(10, 92, 127, 24);
		panelInfPersonal.add(segundoApellidoLbl);
		
		segundoApellidoTxt = new JLabel();
		segundoApellidoTxt.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		segundoApellidoTxt.setFont(new Font("Roboto Medium", Font.ITALIC, 15));
		segundoApellidoTxt.setBounds(137, 92, 111, 24);
		panelInfPersonal.add(segundoApellidoTxt);
		
		carnetIdentidadLbl = new JLabel("Carnet de Identidad:");
		carnetIdentidadLbl.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		carnetIdentidadLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		carnetIdentidadLbl.setBounds(277, 22, 138, 24);
		panelInfPersonal.add(carnetIdentidadLbl);
		
		carnetIdentidadTxt = new JLabel();
		carnetIdentidadTxt.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		carnetIdentidadTxt.setToolTipText("");
		carnetIdentidadTxt.setFont(new Font("Roboto Medium", Font.ITALIC, 15));
		carnetIdentidadTxt.setBounds(415, 22, 153, 24);
		panelInfPersonal.add(carnetIdentidadTxt);
		
		direccionLbl = new JLabel("Direcci\u00F3n:");
		direccionLbl.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		direccionLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		direccionLbl.setBounds(277, 57, 73, 24);
		panelInfPersonal.add(direccionLbl);
		
		direccionTxt = new JLabel();
		direccionTxt.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		direccionTxt.setToolTipText("");
		direccionTxt.setFont(new Font("Roboto Medium", Font.ITALIC, 15));
		direccionTxt.setBounds(349, 57, 219, 24);
		panelInfPersonal.add(direccionTxt);
		
		sexoLbl = new JLabel("Sexo:");
		sexoLbl.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		sexoLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		sexoLbl.setBounds(277, 92, 43, 24);
		panelInfPersonal.add(sexoLbl);
		
		sexoTxt = new JLabel();
		sexoTxt.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		sexoTxt.setToolTipText("");
		sexoTxt.setFont(new Font("Roboto Medium", Font.ITALIC, 15));
		sexoTxt.setBounds(320, 92, 106, 24);
		panelInfPersonal.add(sexoTxt);
		
		edadLbl = new JLabel("Edad:");
		edadLbl.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		edadLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		edadLbl.setBounds(441, 92, 43, 24);
		panelInfPersonal.add(edadLbl);
		
		edadTxt = new JLabel();
		edadTxt.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		edadTxt.setToolTipText("");
		edadTxt.setFont(new Font("Roboto Medium", Font.ITALIC, 15));
		edadTxt.setBounds(484, 92, 86, 24);
		panelInfPersonal.add(edadTxt);
		
		panelInfMedica = new JPanel();
		panelInfMedica.setBackground(Color.WHITE);
		panelInfMedica.setBorder(new TitledBorder(new LineBorder(DefinicionesInterfazGrafica.VERDE_AZULADO_1, 3, true), "Informaci\u00F3n M\u00E9dica", TitledBorder.LEADING, TitledBorder.TOP,new Font("Roboto Medium", Font.PLAIN, 18), Color.BLACK));
		panelInfMedica.setBounds(10, 286, 580, 252);
		panelBase.add(panelInfMedica);
		panelInfMedica.setLayout(null);
		
		panelEnfermedadesCronicas = new JScrollPane();
		panelEnfermedadesCronicas.setBackground(Color.WHITE);
		panelEnfermedadesCronicas.setBounds(10, 90, 260, 116);
		panelInfMedica.add(panelEnfermedadesCronicas);
		
		tablaEnfermedadesCronicas = new JTable();
		modeloEnfermedades = new EnfermedadesCronicasTableModel();
		tablaEnfermedadesCronicas.setModel(modeloEnfermedades);
		tablaEnfermedadesCronicas.setTableHeader(null);
		tablaEnfermedadesCronicas.setSelectionForeground(Color.BLACK);
		tablaEnfermedadesCronicas.setSelectionBackground(DefinicionesInterfazGrafica.VERDE_AZULADO_1);
		tablaEnfermedadesCronicas.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		panelEnfermedadesCronicas.setViewportView(tablaEnfermedadesCronicas);
		
		logoEnfermedades = new Imagen(new ImageIcon(VerPaciente.class.getResource("/iconos/enfermedadCronica.png")));
		logoEnfermedades.setBounds(10, 57, 28, 28);
		panelInfMedica.add(logoEnfermedades);
		
		enfermedadesLbl = new JLabel("Enfermedades Cr\u00F3nicas");
		enfermedadesLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		enfermedadesLbl.setBounds(48, 62, 208, 24);
		panelInfMedica.add(enfermedadesLbl);
		
		logoVacunacion = new Imagen(new ImageIcon(VerPaciente.class.getResource("/iconos/vacunacion.png")));
		logoVacunacion.setBounds(310, 57, 28, 28);
		panelInfMedica.add(logoVacunacion);
		
		panelVacunacion = new JScrollPane();
		panelVacunacion.setBackground(Color.WHITE);
		panelVacunacion.setBounds(310, 90, 260, 116);
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
		vacunacionLbl.setBounds(348, 61, 208, 24);
		panelInfMedica.add(vacunacionLbl);
		
		ultimaPruebaLbl = new JLabel("\u00DAltima Prueba Citol\u00F3gica:\r\n");
		ultimaPruebaLbl.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		ultimaPruebaLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		ultimaPruebaLbl.setBounds(10, 28, 180, 24);
		panelInfMedica.add(ultimaPruebaLbl);
		
		ultimaPruebaTxt = new JLabel();
		ultimaPruebaTxt.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		ultimaPruebaTxt.setFont(new Font("Roboto Medium", Font.ITALIC, 15));
		ultimaPruebaTxt.setBounds(190, 28, 107, 24);
		panelInfMedica.add(ultimaPruebaTxt);
		
		embarazadaLbl = new JLabel("Embarazada:");
		embarazadaLbl.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		embarazadaLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		embarazadaLbl.setBounds(348, 28, 93, 24);
		panelInfMedica.add(embarazadaLbl);
		
		embarazadaTxt = new JLabel();
		embarazadaTxt.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		embarazadaTxt.setToolTipText("");
		embarazadaTxt.setFont(new Font("Roboto Medium", Font.ITALIC, 15));
		embarazadaTxt.setBounds(441, 28, 55, 24);
		panelInfMedica.add(embarazadaTxt);
		
		historiaClinicaTxt = new JLabel();
		historiaClinicaTxt.setBounds(153, 217, 137, 24);
		panelInfMedica.add(historiaClinicaTxt);
		historiaClinicaTxt.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		historiaClinicaTxt.setToolTipText("");
		historiaClinicaTxt.setFont(new Font("Roboto Medium", Font.ITALIC, 15));
		
		historiaClinicaBtn = new JButton("");
		historiaClinicaBtn.setBounds(289, 217, 24, 24);
		panelInfMedica.add(historiaClinicaBtn);
		historiaClinicaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VerHistoriaClinica ver = new VerHistoriaClinica(null,paciente.getHistoriaClinica());
				ver.setVisible(true);
			}
		});
		historiaClinicaBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		historiaClinicaBtn.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		historiaClinicaBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(24,24), AppPrincipal.class.getResource("/iconos/info1.png")));
		historiaClinicaBtn.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(24,24), AppPrincipal.class.getResource("/iconos/info0.png")));
		historiaClinicaBtn.setContentAreaFilled(false);
		
		historiaClinicaLbl = new JLabel("No. Historia Clinica:");
		historiaClinicaLbl.setBounds(10, 217, 143, 24);
		panelInfMedica.add(historiaClinicaLbl);
		historiaClinicaLbl.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		historiaClinicaLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		
		Imagen enRiesgo = new Imagen((Icon) null);
		if(paciente.enRiesgo())
			enRiesgo.setImagen(new ImageIcon(VerPaciente.class.getResource("/iconos/enRiesgo.png")));
		enRiesgo.setBounds(525, 21, 42, 42);
		panelInfMedica.add(enRiesgo);
		
		editarBoton = new BotonAnimacion();
		editarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				EditarPaciente editar = new EditarPaciente(null,indicePaciente);
				editar.setVisible(true);
			}
		});
		editarBoton.setBackground(DefinicionesInterfazGrafica.VERDE_AZULADO_1);
		editarBoton.setEffectColor(DefinicionesInterfazGrafica.TURQUESA);
		editarBoton.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		editarBoton.setText("Editar");
		if(!editable) 
			editarBoton.setBackground(Color.LIGHT_GRAY);
		editarBoton.setEnabled(editable);
		editarBoton.setBounds(483, 549, 107, 27);
		panelBase.add(editarBoton);
		
		rellenarDatos();
		setLocationRelativeTo(null);
	}
	
	private void rellenarDatos() {
		nombreTxt.setText(paciente.getNombre());
		primerApellidoTxt.setText(paciente.getPrimerApellido());
		segundoApellidoTxt.setText(paciente.getSegundoApellido());
		carnetIdentidadTxt.setText(paciente.getCarnetIdentidad());
		direccionTxt.setText(paciente.getDireccion().getDireccionCompleta());
		char sexo = paciente.getSexo();
		int edad = paciente.getEdad();
		sexoTxt.setText(Auxiliares.convertirSexoString(sexo));
		edadTxt.setText(""+edad);
		historiaClinicaTxt.setText(paciente.getHistoriaClinica().getNumeroHistoriaClinica());
		if(Character.toUpperCase(sexo) == 'F' && Validaciones.validarEnteroRango(edad, Definiciones.EDAD_MIN_PRUEBAS_EMBARAZO_MUJER, Definiciones.EDAD_MAX_PRUEBAS_EMBARAZO_MUJER)) {
			embarazadaTxt.setText(((PacienteFemenina)paciente).getEstaEmbarazada() ? "Sí" : "No");
			if(((PacienteFemenina)paciente).getFechaUltimaPruebaCitologica()==null) {
				ultimaPruebaLbl.setEnabled(false);
				ultimaPruebaTxt.setEnabled(false);
			}
			else
				ultimaPruebaTxt.setText(DefinicionesInterfazGrafica.FORMATO_BASE_FECHA.format(((PacienteFemenina)paciente).getFechaUltimaPruebaCitologica()));
		}
		else {
			ultimaPruebaLbl.setEnabled(false);
			ultimaPruebaTxt.setEnabled(false);
			embarazadaLbl.setEnabled(false);
			embarazadaTxt.setEnabled(false);
		}
		
		modeloEnfermedades.actualizar(paciente.getEnfermedadesCronicas());
		modeloVacunacion.actualizar(paciente.getVacunaciones());
	}
}
