package interfaz_grafica;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;

import definiciones.Definiciones;
import definiciones.DefinicionesInterfazGrafica;
import definiciones.ErroresInterfazGrafica;
import modelos.CallePrincipalComboBoxModel;
import modelos.AnalisisFaltantesTableModel;
import modelos.EmbarazadaTableModel;
import modelos.EnfermedadesCronicasComboBoxModel;
import modelos.HojaCargosTableModel;
import modelos.MultiLineaCellRendererEditor;
import modelos.NoCasaComboBoxModel;
import modelos.PacienteAnalisisFaltantesTableModel;
import modelos.PacienteComboBoxModel;
import modelos.PacienteTableModel;
import modelos.VacunaComboBoxModel;
import nucleo.Analisis;
import nucleo.CMF;
import nucleo.VisitaHojaDeCargos;
import nucleo.Paciente;
import clases_auxiliares.AnalisisIndicados;
import clases_auxiliares.TipoCuentaUsuario;
import utilidades.Auxiliares;
import utilidades.AuxiliaresInterfazGrafica;
import utilidades.BuscadorPacientes;
import utilidades.Comparadores;
import utilidades.PiscinaDatos;
import utilidades.Validaciones;
import componentes.PanelBordeOval;
import componentes.PanelGradienteH;
import componentes.PanelGradienteV;
import componentes.PanelOpcion;

import javax.swing.border.MatteBorder;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import componentes.AvatarCircular;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import componentes.Imagen;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableRowSorter;
import javax.swing.event.ChangeEvent;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import componentes.BotonAnimacion;
import componentes.JTextFieldModificado;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SortOrder;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class AppPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panelBase;
	private JLabel panelSuperiorLbl;
	private PanelBordeOval panelSuperior;

	private int xMouseDrag;
	private int yMouseDrag;
	private JPanel panelContenedor;
	private JButton salirBtn;
	private JButton minimizarBtn;
	private PanelGradienteV panelUsuario;
	private PanelGradienteH panelSuperiorSecundario;
	private JPanel panelContenedorOpciones;
	private JTabbedPane panelPrincipalTab;
	private AvatarCircular avatar;
	private JLabel nombreUsuariolbl;
	private JLabel tipoUsuariolbl;
	private JButton ayudaCreditosBtn;
	private PanelOpcion opcionInicio;
	private Imagen logoInicio;
	private Imagen logoPacientes;
	private JLabel textoPacientes;
	private PanelOpcion opcionPacientes;
	private JLabel textoInico;
	private PanelOpcion opcionModable;
	private Imagen logoModable;
	private JLabel textoModable;
	private PanelOpcion opcionReportes;
	private Imagen logoReportes;
	private JLabel textoReportes;
	private JPanel panelInicio;
	private JPanel panelPacientes;
	private JPanel panelVisitas;
	private JPanel panelAnalisis;
	private JPanel panelReportes;
	private JPanel panelEmbarazadas;
	private JPanel panelVacunados;
	private JPanel panelEnRiesgo;
	private JPanel panelEnfermos;
	private JPanel panelAnalisisFaltantes;
	private JLabel opcionLbl;
	private JPanel panelSinVisitas;
	private JLabel vacunadosLbl;
	private JButton botonAtrasReporte;
	private BotonAnimacion cerrarSesionBtn;
	private JTable tablaPacientes;
	private JTable tablaAnalisisFaltantesEnfermera;
	private PanelBordeOval reporteEmbarazada;
	private PanelBordeOval reporteAnalisisFaltantes;
	private PanelBordeOval reporteEnRiesgo;
	private PanelBordeOval reporteVacunados;
	private PanelBordeOval reporteEnfermos;
	private PanelBordeOval reporteSinVisitas;
	private static TipoCuentaUsuario usuario;
	private static CMF cmf;
	private static AppPrincipal instancia;
	private static PacienteTableModel modeloPaciente;
	private static JLabel numeroEmbarazadaLbl;
	private static JProgressBar barraProgresoEmbarazada;
	private static JLabel porcientoEmbarazadasLbl;
	private static JLabel numeroEnRiesgoLbl;
	private static JProgressBar barraProgresoEnRiesgo;
	private static JLabel porcientoEnRiesgoLbl;
	private static JLabel numeroVisitasHoyLbl;
	private JTable tablaEmbarazadas;
	private static EmbarazadaTableModel modeloEmbarazadas;
	private JTable tablaEnRiesgo;
	private static PacienteTableModel modeloEnRiesgo;
	private PanelOpcion opcionHojaCargos;
	private Imagen logoHojaCargo;
	private JPanel panelHojasCargo;
	private static JTable tablaHojasCargo;
	private static HojaCargosTableModel modeloHojasCargos;
	private JButton masInfoBtn;
	private static ArrayList<Paciente> listadoPacientesPrincipalActual;
	private static JComboBox<String> callePrincipalComboBox;
	private JComboBox<String> numeroCasaComboBox;
	private JButton botonReCargar;
	private static JTextFieldModificado nombreTxt;
	private static JTextFieldModificado primerApellidoTxt;
	private static JTextFieldModificado segundoApellidoTxt;
	private static JTextFieldModificado carnetIdentidadTxt;
	private static JComboBox<String> seleccionarVacuna;
	private BotonAnimacion buscarVacunadoBtn;
	private static JComboBox<String> seleccionarEnfermedad;
	private BotonAnimacion buscarEnfermoBtn;
	private JTable tablaVacunados;
	private static PacienteTableModel modeloVacunados;
	private JTable tablaEnfermos;
	private static PacienteTableModel modeloEnfermos;
	private JButton botonReCargar_Vacuna;
	private JButton botonReCargar_Enfermedad;
	private static PacienteTableModel modeloSinVisitas;
	private JTable tablaSinVisita;
	private static String vacunaSeleccionada;
	private static String enfermedadSeleccionada;
	private static Date fechaSinVisitaSeleccionada;
	private static JDateChooser fechaMinimaSinVisita;
	private JTable tablaHojaCargosDia;
	private static HojaCargosTableModel modeloHojaCargosDia;
	private static JDateChooser fechaVisitaHojaCargo;
	private BotonAnimacion buscarHojaCargo;
	private static JDateChooser fechaMinimaAnalisisFaltante;
	private static JComboBox<String> seleccionarPacienteAnalisisFaltante;
	private BotonAnimacion buscarAnalisisFaltanteBtn;
	private static PacienteAnalisisFaltantesTableModel modeloAnalisisFaltantesReporte;
	private static PacienteComboBoxModel modeloPacienteComboBoxModel;
	private static AnalisisFaltantesTableModel modeloAnalisisEnfermera;
	private JButton botonInfoHojaCargosDia;
	private JButton botonInfoEmbarazadas;
	private JButton botonInfoVacunados;
	private JButton botonInfoEnRiesgo;
	private JButton botonInfoEnfermedad;
	private JButton botonInfoSinVisitas;
	private JButton botonInfoAnalisisFaltante;
	private JButton botonInfoHojaCargos;
	private JLabel direccionLbl;
	private JLabel historiaClinicaLbl;
	private static JTextFieldModificado hcTxt;
	private static TableRowSorter<PacienteTableModel> ordenamientoPacientes;
	private static TableRowSorter<EmbarazadaTableModel> ordenamientoEmbarazadas;
	private static TableRowSorter<HojaCargosTableModel> ordenamientoHojaCargosDia;
	private static TableRowSorter<AnalisisFaltantesTableModel> ordenamientoAnalisisEnfermera;
	private static TableRowSorter<PacienteTableModel> ordenamientoVacunados;
	private static TableRowSorter<PacienteTableModel> ordenamientoEnRiesgo;
	private static TableRowSorter<PacienteTableModel> ordenamientoEnfermos;
	private static TableRowSorter<PacienteTableModel> ordenamientoSinVisitas;
	private static TableRowSorter<PacienteAnalisisFaltantesTableModel> ordenamientoAnalisisFaltantesReporte;
	private static TableRowSorter<HojaCargosTableModel> ordenamientoHojaCargo;
	
	public static AppPrincipal getInstancia(TipoCuentaUsuario usuarioRegistrado) {
		if(instancia==null || (instancia!=null && !usuario.equals(usuarioRegistrado)))
			instancia=new AppPrincipal(usuarioRegistrado);
		
		return instancia;
	}
	
	private AppPrincipal(TipoCuentaUsuario usuarioRegistrado) {
		FlatLightLaf.setup();
		cmf = CMF.getInstancia();
		usuario = usuarioRegistrado;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, DefinicionesInterfazGrafica.DIMENSION_APP_PRINCIPAL.width, DefinicionesInterfazGrafica.DIMENSION_APP_PRINCIPAL.height);

		this.setUndecorated(true);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(AppPrincipal.class.getResource("/iconos/iconoApp.png")));
		this.setBackground(new Color(0,0,0,0));
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				requestFocus();
			}
		
		});
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				e.consume();
				if(e.isAltDown() && e.getKeyCode()==KeyEvent.VK_F4) {
					if(JOptionPane.showConfirmDialog(rootPane, DefinicionesInterfazGrafica.PREGUNTA_SALIR_PRINCIPAL, null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
						System.exit(0);
				}
			}
		});
		
		
		panelBase = new JPanel();
		panelBase.setOpaque(false);
		setContentPane(panelBase);
		
		
		panelSuperior = new PanelBordeOval(40, 40, 0, 0);
		panelSuperior.setBounds(0, 0, 1200, 45);
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
				panelSuperior.setBackground(new Color(DefinicionesInterfazGrafica.TURQUESA_CLARO.getRed(),DefinicionesInterfazGrafica.TURQUESA_CLARO.getGreen(),DefinicionesInterfazGrafica.TURQUESA_CLARO.getBlue(),200));
				xMouseDrag = e.getX();
				yMouseDrag = e.getY();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				panelSuperior.setBackground(new Color(DefinicionesInterfazGrafica.TURQUESA_CLARO.getRed(),DefinicionesInterfazGrafica.TURQUESA_CLARO.getGreen(),DefinicionesInterfazGrafica.TURQUESA_CLARO.getBlue(),150));
			}
		});
		panelSuperior.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		panelSuperior.setBackground(new Color(111, 233, 238, 150));
		
		panelSuperiorLbl = new JLabel("Consultorio M\u00E9dico de la Familia");
		panelSuperiorLbl.setBounds(15, 6, 335, 38);
		panelSuperiorLbl.setFont(new Font("Roboto Black", Font.PLAIN, 21));
		
		panelContenedor = new JPanel();
		panelContenedor.setBounds(0, 45, 1200, 630);
		panelContenedor.setBorder(new MatteBorder(0, 2, 2, 2, (Color) new Color(0, 0, 0)));
		
		panelUsuario = new PanelGradienteV(DefinicionesInterfazGrafica.COLOR_GRADIENTE_A_PANEL_USUARIO, DefinicionesInterfazGrafica.COLOR_GRADIENTE_B_PANEL_USUARIO);
		panelUsuario.setBounds(3, 0, 300, 211);
		
		panelContenedorOpciones = new JPanel();
		panelContenedorOpciones.setBounds(3, 211, 300, 416);
		panelContenedorOpciones.setBackground(DefinicionesInterfazGrafica.COLOR_PANEL_OPCIONES);
		panelContenedor.setLayout(null);
		
		panelSuperiorSecundario = new PanelGradienteH(DefinicionesInterfazGrafica.COLOR_GRADIENTE_A_PANEL_SUPERIOR_SECUNDARIO,DefinicionesInterfazGrafica.COLOR_GRADIENTE_B_PANEL_SUPERIOR_SECUNDARIO);
		panelSuperiorSecundario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelSuperiorSecundario.requestFocus();
			}
		});
		panelSuperiorSecundario.setBounds(303, 0, 894, 55);
		panelContenedor.add(panelSuperiorSecundario);
		panelSuperiorSecundario.setLayout(null);
		
		ayudaCreditosBtn = new JButton("");
		ayudaCreditosBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ayudaCreditosBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JScrollPane panelAyudaCreditos = new JScrollPane();
				panelAyudaCreditos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panelAyudaCreditos.setBounds(144, 135, 600, 400);
				
				JTextArea ayudaCreditos = new JTextArea();
				ayudaCreditos.setWrapStyleWord(true);
				ayudaCreditos.setText("La presente aplicación es resultado del trabajo de curso de DPOO\r\n"
						+ "de los autores Eva Ercilia Vázquez García y Eduardo Alejandro\r\n"
						+ "González Martell; teniendo como tutora a PhD. Sonia Pérez Lovelle. Tiene\r\n"
						+ "como principal objetivo lograr la informatización del consultorio médico de\r\n"
						+ "la familia.\r\n"
						+ "\r\n"
						+ "La mayoría de las secciones de la aplicación se encuentran documentadas\r\n"
						+ "con secciones para asistir al uso correcto del software por parte del \r\n"
						+ "usuario. Existen dos maneras de documentación disponibles: mediante los\r\n"
						+ "llamados \"ToolTipText\", a los cuales se acceden cuando se sobrepone el cursor\r\n"
						+ "en los componentes que presenten dicha característica; y mediante los botones \r\n"
						+ "de MásInfo, localizados en la mayoría de las secciones de la AppPrincipal, en la \r\n"
						+ "parte superior derecha. Todas las tablas pueden ser reordenadas haciendo\r\n"
						+ "click en las columnas de las mismas.\r\n"
						+ "\r\n"
						+ "Durante el desarrollo del presente proyecto se utilizaron diferentes librerías \r\n"
						+ "externas. Se deben dar créditos parciales a Kai Toedter, el creador de la \r\n"
						+ "librería JCalendar; FormDev Software, los creadores de FlatLaf; Rojeru-Sans \r\n"
						+ "y Ra-Ven, los cuales son programadores que diseñaron algunos componentes \r\n"
						+ "visuales en los que se basó para la confección de los componentes de la aplicación.");
				
				ayudaCreditos.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
				panelAyudaCreditos.setViewportView(ayudaCreditos);
				JOptionPane.showMessageDialog(null, panelAyudaCreditos, "Ayuda y Créditos", JOptionPane.PLAIN_MESSAGE);
			}
		});
		ayudaCreditosBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ayudaCreditosBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(48,48), AppPrincipal.class.getResource("/iconos/help0.png")));
			}
			@Override
			public void mouseExited(MouseEvent e){
				ayudaCreditosBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(48,48), AppPrincipal.class.getResource("/iconos/help1.png")));
			}
		});
		ayudaCreditosBtn.setContentAreaFilled(false);
		ayudaCreditosBtn.setBackground(Color.WHITE);
		ayudaCreditosBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(48,48), Autenticacion.class.getResource("/iconos/help1.png")));
		ayudaCreditosBtn.setBounds(845, 8, 42, 42);
		panelSuperiorSecundario.add(ayudaCreditosBtn);
		
		opcionLbl = new JLabel("INICIO");
		opcionLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		opcionLbl.setBounds(58, 11, 396, 33);
		panelSuperiorSecundario.add(opcionLbl);
		
		botonAtrasReporte = new JButton("");
		botonAtrasReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrincipalTab.setSelectedIndex(4);;
			}
		});
		botonAtrasReporte.setVisible(false);
		botonAtrasReporte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonAtrasReporte.setBorder(null);
		botonAtrasReporte.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/atras0.png")));
		botonAtrasReporte.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/atras1.png")));
		botonAtrasReporte.setContentAreaFilled(false);
		botonAtrasReporte.setBounds(10, 9, 36, 36);
		panelSuperiorSecundario.add(botonAtrasReporte);
		
		masInfoBtn = new JButton("");
		masInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JScrollPane panelInfoConsultorio = new JScrollPane();
				panelInfoConsultorio.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panelInfoConsultorio.setBounds(144, 135, 600, 400);
				
				JTextArea infoConsultorio = new JTextArea();
				infoConsultorio.setWrapStyleWord(true);
				infoConsultorio.setText("Datos Generales del Consultorio:\r\n.N\u00FAmero de Consultorio >> "+cmf.getNumeroConsultorio()+"\r\n.Nombre del Policl\u00EDnico >> "+cmf.getNombrePoliclinico()+
						"\r\n.Nombre del Director del Policl\u00EDnico >> "+cmf.getNombreDirectorPoliclinico()+"\r\n.Cantidad de pacientes >> "+cmf.getListadoPacientes().size()+
						"\r\n\r\nDatos del M\u00E9dico:\r\n.Nombre >> "+cmf.getDoctor().getNombreSimple()+"\r\n.CI >> "+cmf.getDoctor().getCarnetIdentidad()+"\r\n.N\u00FAmero Registro M\u00E9dico >> "+
						cmf.getDoctor().getNumeroRegistroMedico()+"\r\n.Fecha de Inscripci\u00F3n CMF >> "+DefinicionesInterfazGrafica.FORMATO_BASE_FECHA.format(cmf.getDoctor().getFechaInscripcionCMF())+
						"\r\n\r\nDatos de la Enfermera:\r\n.Nombre >> "+cmf.getEnfermera().getNombreSimple()+"\r\n.CI >> "+cmf.getEnfermera().getCarnetIdentidad()+"\r\n.Comienzo Laboral >> "+
						DefinicionesInterfazGrafica.FORMATO_BASE_FECHA.format(cmf.getEnfermera().getComienzoLaboral())+"\r\n.Fecha de Inscripci\u00F3n CMF >> "+
						DefinicionesInterfazGrafica.FORMATO_BASE_FECHA.format(cmf.getEnfermera().getFechaInscripcionCMF())+"\r\n.Tiene Licenciatura >> "+(cmf.getEnfermera().getTieneLicenciatura()?"Sí":"No"));
				
				infoConsultorio.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
				panelInfoConsultorio.setViewportView(infoConsultorio);
				JOptionPane.showMessageDialog(null, panelInfoConsultorio, "Info del Consultorio", JOptionPane.PLAIN_MESSAGE);
			}
		});
		masInfoBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(48,48), Autenticacion.class.getResource("/iconos/consultorioInfo0.png")));
		masInfoBtn.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(48,48), Autenticacion.class.getResource("/iconos/consultorioInfo1.png")));
		masInfoBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		masInfoBtn.setContentAreaFilled(false);
		masInfoBtn.setBackground(Color.WHITE);
		masInfoBtn.setBounds(793, 8, 42, 42);
		panelSuperiorSecundario.add(masInfoBtn);
		panelContenedor.add(panelUsuario);
		panelUsuario.setLayout(null);
		
		ImageIcon logoAvatarTemp = new ImageIcon(AppPrincipal.class.getResource("/imagenes/enfermeraLogo.png"));
		if(usuario == TipoCuentaUsuario.MEDICO)
			logoAvatarTemp = new ImageIcon(AppPrincipal.class.getResource("/imagenes/doctorLogo.png"));
		
		avatar = new AvatarCircular(logoAvatarTemp, DefinicionesInterfazGrafica.TAM_BORDE_AVATAR);
		avatar.setForeground(DefinicionesInterfazGrafica.VERDE_VIVO_0);
		avatar.setBounds(95, 5, 110, 110);
		panelUsuario.add(avatar);
		
		nombreUsuariolbl = new JLabel(usuario==TipoCuentaUsuario.MEDICO ? cmf.getDoctor().getNombreSimple() : cmf.getEnfermera().getNombreSimple());
		nombreUsuariolbl.setForeground(Color.WHITE);
		nombreUsuariolbl.setHorizontalAlignment(SwingConstants.CENTER);
		nombreUsuariolbl.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		nombreUsuariolbl.setBounds(55, 115, 189, 28);
		panelUsuario.add(nombreUsuariolbl);
		
		tipoUsuariolbl = new JLabel(usuario==TipoCuentaUsuario.MEDICO ? "Médico" : "Enfermera");
		tipoUsuariolbl.setForeground(Color.WHITE);
		tipoUsuariolbl.setHorizontalAlignment(SwingConstants.CENTER);
		tipoUsuariolbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		tipoUsuariolbl.setBounds(90, 140, 120, 28);
		panelUsuario.add(tipoUsuariolbl);
		
		cerrarSesionBtn = new BotonAnimacion();
		cerrarSesionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(rootPane, DefinicionesInterfazGrafica.PREGUNTA_CERRAR_SESION, null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					dispose();
					Autenticacion l = new Autenticacion();
					l.setVisible(true);
				}		
			}
		});
		cerrarSesionBtn.setText("Cerrar Sesi\u00F3n");
		cerrarSesionBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		cerrarSesionBtn.setEffectColor(DefinicionesInterfazGrafica.VERDE_AZULADO_1);
		cerrarSesionBtn.setBackground(DefinicionesInterfazGrafica.TURQUESA_CLARO);
		cerrarSesionBtn.setBounds(80, 179, 140, 21);
		panelUsuario.add(cerrarSesionBtn);
		
		panelPrincipalTab = new JTabbedPane(JTabbedPane.TOP);
		panelPrincipalTab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		panelPrincipalTab.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				String opcion="";
				switch(panelPrincipalTab.getSelectedIndex()) {
				case 0:
					opcion="INICIO";
					break;
				case 1:
					opcion="PACIENTES";
					break;
				case 2:
					opcion="VISITAS";
					break;
				case 3:
					opcion="ANÁLISIS";
					break;
				case 4:
					opcion="REPORTES";
					break;
				case 5:
					opcion="EMBARAZADAS";
					break;
				case 6:
					opcion="VACUNADOS";
					break;
				case 7:
					opcion="EN RIESGO";
					break;
				case 8:
					opcion="ENFERMOS";
					break;
				case 9:
					opcion="SIN VISITAS";
					break;
				case 10:
					opcion="ANÁLISIS INDICADOS FALTANTES";
					break;
				case 11:
					opcion="HOJAS DE CARGO";
					break;
				default:
					break;
				}
				opcionLbl.setText(opcion);
				
				if(panelPrincipalTab.getSelectedIndex()>4 && panelPrincipalTab.getSelectedIndex()<11)
					botonAtrasReporte.setVisible(true);
				else
					botonAtrasReporte.setVisible(false);
			}
		});
		panelPrincipalTab.setBounds(303, 0, 894, 627);
		panelContenedor.add(panelPrincipalTab);
		
		panelInicio = new JPanel();
		panelInicio.setBackground(Color.WHITE);
		panelPrincipalTab.addTab("a", null, panelInicio, null);
		panelInicio.setLayout(null);
		
		PanelBordeOval tarjetaEmbarazada = new PanelBordeOval(DefinicionesInterfazGrafica.VALOR_ESQUINA_OVAL);
		tarjetaEmbarazada.setBackground(DefinicionesInterfazGrafica.COLOR_TARJETA_INICIO);
		tarjetaEmbarazada.setBounds(56, 147, 360, 160);
		panelInicio.add(tarjetaEmbarazada);
		tarjetaEmbarazada.setLayout(null);
		
		JLabel embarazadasLbl = new JLabel("Embarazadas");
		embarazadasLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 21));
		embarazadasLbl.setBounds(10, 11, 164, 29);
		tarjetaEmbarazada.add(embarazadasLbl);
		
		Imagen embarazadasLogoInicio = new Imagen(new ImageIcon(AppPrincipal.class.getResource("/iconos/embarazada0.png")));
		embarazadasLogoInicio.setBounds(237, 11, 113, 113);
		tarjetaEmbarazada.add(embarazadasLogoInicio);
		
		numeroEmbarazadaLbl = new JLabel("");
		numeroEmbarazadaLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 30));
		numeroEmbarazadaLbl.setBounds(10, 53, 211, 54);
		tarjetaEmbarazada.add(numeroEmbarazadaLbl);
		
		barraProgresoEmbarazada = new JProgressBar();
		barraProgresoEmbarazada.setForeground(Color.WHITE);
		barraProgresoEmbarazada.setBackground(new Color(255,255,255,100));
		barraProgresoEmbarazada.setBounds(10, 135, 291, 9);
		tarjetaEmbarazada.add(barraProgresoEmbarazada);
		
		porcientoEmbarazadasLbl = new JLabel("");
		porcientoEmbarazadasLbl.setHorizontalAlignment(SwingConstants.CENTER);
		porcientoEmbarazadasLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		porcientoEmbarazadasLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		porcientoEmbarazadasLbl.setBounds(301, 131, 49, 14);
		tarjetaEmbarazada.add(porcientoEmbarazadasLbl);
		
		PanelBordeOval tarjetaEnRiesgo = new PanelBordeOval(DefinicionesInterfazGrafica.VALOR_ESQUINA_OVAL);
		tarjetaEnRiesgo.setBackground(DefinicionesInterfazGrafica.COLOR_TARJETA_INICIO);
		tarjetaEnRiesgo.setBounds(472, 147, 360, 160);
		panelInicio.add(tarjetaEnRiesgo);
		tarjetaEnRiesgo.setLayout(null);
		
		JLabel enRiesgoInicioLbl = new JLabel("En Riesgo");
		enRiesgoInicioLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 21));
		enRiesgoInicioLbl.setBounds(10, 11, 164, 29);
		tarjetaEnRiesgo.add(enRiesgoInicioLbl);
		
		Imagen enRiesgoLogoInicio_1 = new Imagen(new ImageIcon(AppPrincipal.class.getResource("/iconos/enRiesgo0.png")));
		enRiesgoLogoInicio_1.setBounds(245, 19, 105, 105);
		tarjetaEnRiesgo.add(enRiesgoLogoInicio_1);
		
		numeroEnRiesgoLbl = new JLabel("56");
		numeroEnRiesgoLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 30));
		numeroEnRiesgoLbl.setBounds(10, 54, 211, 54);
		tarjetaEnRiesgo.add(numeroEnRiesgoLbl);
		
		barraProgresoEnRiesgo = new JProgressBar();
		barraProgresoEnRiesgo.setForeground(Color.WHITE);
		barraProgresoEnRiesgo.setBackground(new Color(255,255,255,100));
		barraProgresoEnRiesgo.setBounds(10, 135, 291, 9);
		tarjetaEnRiesgo.add(barraProgresoEnRiesgo);
		
		porcientoEnRiesgoLbl = new JLabel(barraProgresoEnRiesgo.getValue()+"%");
		porcientoEnRiesgoLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		porcientoEnRiesgoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		porcientoEnRiesgoLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		porcientoEnRiesgoLbl.setBounds(301, 130, 49, 14);
		tarjetaEnRiesgo.add(porcientoEnRiesgoLbl);
		
		JLabel Bienvenida = new JLabel("Consultorio M\u00E9dico de la Familia");
		Bienvenida.setBorder(new MatteBorder(0, 0, 2, 0, DefinicionesInterfazGrafica.COLOR_BORDE_JLABELS_APP_PRINCIPAL));
		Bienvenida.setFont(new Font("Roboto Medium", Font.PLAIN, 24));
		Bienvenida.setBounds(10, 30, 869, 45);
		panelInicio.add(Bienvenida);
		
		PanelBordeOval tarjetaVisitaHoy = new PanelBordeOval(40);
		tarjetaVisitaHoy.setLayout(null);
		tarjetaVisitaHoy.setBackground(new Color(153, 243, 241));
		tarjetaVisitaHoy.setBounds(264, 357, 360, 160);
		panelInicio.add(tarjetaVisitaHoy);
		
		JLabel visitaHoyLbl = new JLabel("Visitas De Hoy");
		visitaHoyLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 21));
		visitaHoyLbl.setBounds(10, 11, 164, 29);
		tarjetaVisitaHoy.add(visitaHoyLbl);
		
		Imagen visitasHoyLogoInicio = new Imagen(new ImageIcon(AppPrincipal.class.getResource("/iconos/visita0.png")));
		visitasHoyLogoInicio.setBounds(230, 20, 120, 120);
		tarjetaVisitaHoy.add(visitasHoyLogoInicio);
		
		numeroVisitasHoyLbl = new JLabel("0");
		numeroVisitasHoyLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 30));
		numeroVisitasHoyLbl.setBounds(10, 53, 211, 96);
		tarjetaVisitaHoy.add(numeroVisitasHoyLbl);
		
		modeloPaciente = new PacienteTableModel();
		
		panelPacientes = new JPanel();
		panelPacientes.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		panelPacientes.setBackground(Color.WHITE);
		panelPrincipalTab.addTab("a", null, panelPacientes, null);
		panelPacientes.setLayout(null);
		
		JButton botonInfoListado = new JButton("");
		botonInfoListado.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonInfoListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panelPrincipalTab, "Haga doble click en uno de los pacientes para acceder a toda su información. Para eliminar un paciente, seleccione el paciente y presione el botón Eliminar",null,JOptionPane.INFORMATION_MESSAGE);
			}
		});
		botonInfoListado.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/infoo0.png")));
		botonInfoListado.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/infoo1.png")));
		botonInfoListado.setContentAreaFilled(false);
		botonInfoListado.setBorder(null);
		botonInfoListado.setBounds(847, 202, 32, 32);
		panelPacientes.add(botonInfoListado);
		
		carnetIdentidadTxt = new JTextFieldModificado();
		carnetIdentidadTxt.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				buscadorPacientes();
			}
		});
		carnetIdentidadTxt.setLimite(Definiciones.TAM_STRING_CI);
		carnetIdentidadTxt.setTipoValidacion(JTextFieldModificado.SOLO_DIGITOS);
		carnetIdentidadTxt.setAdmiteEspacios(false);
		carnetIdentidadTxt.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		carnetIdentidadTxt.setBounds(613, 82, 207, 24);
		panelPacientes.add(carnetIdentidadTxt);
		
		segundoApellidoTxt = new JTextFieldModificado();
		segundoApellidoTxt.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				buscadorPacientes();
			}
		});
		segundoApellidoTxt.setLimite(Definiciones.CANT_MAX_CARACTERES_APELLIDO);
		segundoApellidoTxt.setTipoValidacion(JTextFieldModificado.SOLO_LETRAS);
		segundoApellidoTxt.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		segundoApellidoTxt.setBounds(145, 150, 144, 24);
		panelPacientes.add(segundoApellidoTxt);
		
		primerApellidoTxt = new JTextFieldModificado();
		primerApellidoTxt.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				buscadorPacientes();
			}
		});
		primerApellidoTxt.setLimite(Definiciones.CANT_MAX_CARACTERES_APELLIDO);
		primerApellidoTxt.setTipoValidacion(JTextFieldModificado.SOLO_LETRAS);
		primerApellidoTxt.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		primerApellidoTxt.setBounds(130, 115, 159, 24);
		panelPacientes.add(primerApellidoTxt);
		
		nombreTxt = new JTextFieldModificado();
		nombreTxt.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				buscadorPacientes();
			}
		});
		nombreTxt.setLimite(Definiciones.CANT_MAX_CARACTERES_NOMBRE);
		nombreTxt.setTipoValidacion(JTextFieldModificado.SOLO_LETRAS);
		nombreTxt.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		nombreTxt.setBounds(76, 80, 213, 24);
		panelPacientes.add(nombreTxt);
		
		botonReCargar = new JButton("");
		botonReCargar.setToolTipText("ReCargar\u00E1 el sistema del buscador de pacientes, devolvi\u00E9ndolo\r\na sus valores predeterminados.");
		botonReCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restaurarTablaPacientes();
				restaurarComponentesBusquedaPacientes();
			}
		});
		botonReCargar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonReCargar.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(30,30), AppPrincipal.class.getResource("/iconos/reset0.png")));
		botonReCargar.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(30,30), AppPrincipal.class.getResource("/iconos/reset1.png")));
		botonReCargar.setContentAreaFilled(false);
		botonReCargar.setBorder(null);
		botonReCargar.setBounds(807, 35, 30, 30);
		panelPacientes.add(botonReCargar);
		
		JButton botonEliminar = new JButton("");
		botonEliminar.setToolTipText("Seleccione el paciente que desea eliminar del sistema y\r\npresione este bot\u00F3n. Los cambios ser\u00E1n permanentes. ");
		botonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tablaPacientes.getSelectedRow()!=-1) {
					if(JOptionPane.showConfirmDialog(panelPacientes, DefinicionesInterfazGrafica.MENSAJE_ELIMINAR_PACIENTES, "Eliminar Paciente", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION) {
						cmf.removePaciente(cmf.getIndicePaciente((String)modeloPaciente.getValueAt(ordenamientoPacientes.convertRowIndexToModel(tablaPacientes.getSelectedRow()), 2)));
						actualizarDatos();
						botonReCargar.doClick();
					}
				}
			}
		});
		botonEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonEliminar.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(30,30), EditarPaciente.class.getResource("/iconos/erase0.png")));
		botonEliminar.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(30,30), EditarPaciente.class.getResource("/iconos/erase1.png")));
		botonEliminar.setContentAreaFilled(false);
		botonEliminar.setBorder(null);
		botonEliminar.setBounds(807, 202, 30, 30);
		panelPacientes.add(botonEliminar);
		
		
		JButton botonInfoBuscador = new JButton("");
		botonInfoBuscador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panelPrincipalTab, "Para usar el buscador de pacientes, rellene las opciones de búsqueda que desee: el sistema buscará en tiempo real aquellos pacientes que cumplan las condiciones introducidas. Si desea cancelar la búsqueda y "
						+ "volver a la lista de pacientes original, presione el botón ReCargar en el panel superior de buscador de pacientes. Tenga en cuenta que el motor de búsqueda funciona filtrando aquellos"
						+ " pacientes cuyos datos contengan la información introducida en los campos pertinentes, sin tomar en cuenta las mayúsculas o minúsculas. Por ejemplo si busca con el campo <Nombre> con \"ma\" o \"MA\","
						+ " se obtendrá el mismo resultado, incluyendo aquellos nombres que contengan dicha subexpresión. Ej: \"Marcos\", \"Amanda\".",null,JOptionPane.INFORMATION_MESSAGE);
			}
		});
		botonInfoBuscador.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonInfoBuscador.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/infoo0.png")));
		botonInfoBuscador.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/infoo1.png")));
		botonInfoBuscador.setContentAreaFilled(false);
		botonInfoBuscador.setBorder(null);
		botonInfoBuscador.setBounds(847, 35, 32, 32);
		panelPacientes.add(botonInfoBuscador);
		
		
		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setBackground(Color.LIGHT_GRAY);
		panelTabla.setBorder(null);
		panelTabla.setBounds(10, 245, 869, 278);
		panelPacientes.add(panelTabla);
		tablaPacientes = new JTable();
		tablaPacientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablaPacientes.setSelectionForeground(Color.BLACK);
		tablaPacientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					if(tablaPacientes.getSelectedRow()!=-1) {
						VerPaciente paciente = new VerPaciente(null,cmf.getIndicePaciente((String)modeloPaciente.getValueAt(ordenamientoPacientes.convertRowIndexToModel(tablaPacientes.getSelectedRow()), 2)),true);
						paciente.setVisible(true);
					}
				}
			}
		});
		tablaPacientes.setSelectionBackground(DefinicionesInterfazGrafica.VERDE_AZULADO_1);
		tablaPacientes.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		tablaPacientes.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		tablaPacientes.setModel(modeloPaciente);
		ordenamientoPacientes = new TableRowSorter<PacienteTableModel>(modeloPaciente);
		tablaPacientes.setRowSorter(ordenamientoPacientes);
		panelTabla.setViewportView(tablaPacientes);
		
		JLabel listadoPacientesLbl = new JLabel("Listado de Pacientes");
		listadoPacientesLbl.setBorder(new MatteBorder(0, 0, 2, 0, DefinicionesInterfazGrafica.COLOR_BORDE_JLABELS_APP_PRINCIPAL));
		listadoPacientesLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		listadoPacientesLbl.setBounds(10, 208, 869, 26);
		panelPacientes.add(listadoPacientesLbl);
		
		JLabel buscadorPacientesLbl = new JLabel("Buscador de Pacientes");
		buscadorPacientesLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		buscadorPacientesLbl.setBorder(new MatteBorder(0, 0, 2, 0, DefinicionesInterfazGrafica.COLOR_BORDE_JLABELS_APP_PRINCIPAL));
		buscadorPacientesLbl.setBounds(10, 41, 869, 26);
		panelPacientes.add(buscadorPacientesLbl);
		
		JLabel nombreLbl = new JLabel("Nombre:");
		nombreLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		nombreLbl.setBounds(10, 78, 69, 26);
		panelPacientes.add(nombreLbl);
		
		BotonAnimacion agregarPacienteBtn = new BotonAnimacion();
		agregarPacienteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarPaciente paciente;
				try {
					paciente = new AgregarPaciente(null);
					paciente.setVisible(true);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		agregarPacienteBtn.setEffectColor(new Color(51, 202, 223));
		agregarPacienteBtn.setBackground(new Color(47, 184, 176));
		agregarPacienteBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		agregarPacienteBtn.setText("Agregar");
		agregarPacienteBtn.setBounds(766, 534, 113, 26);
		panelPacientes.add(agregarPacienteBtn);
		
		JLabel primerApellidoLbl = new JLabel("Primer Apellido:");
		primerApellidoLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		primerApellidoLbl.setBorder(null);
		primerApellidoLbl.setBounds(10, 115, 118, 24);
		panelPacientes.add(primerApellidoLbl);
		
		JLabel segundoApellidoLbl = new JLabel("Segundo Apellido:");
		segundoApellidoLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		segundoApellidoLbl.setBorder(null);
		segundoApellidoLbl.setBounds(10, 150, 133, 24);
		panelPacientes.add(segundoApellidoLbl);
		
		JLabel carnetIdentidadLbl = new JLabel("Carnet de Identidad:");
		carnetIdentidadLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		carnetIdentidadLbl.setBounds(460, 80, 155, 26);
		panelPacientes.add(carnetIdentidadLbl);
		
		callePrincipalComboBox = new JComboBox<String>();
		callePrincipalComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		callePrincipalComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int indice = callePrincipalComboBox.getSelectedIndex();
				String calle = (String)callePrincipalComboBox.getSelectedItem();
				if(indice!=-1) {
					if(indice==0) {
						numeroCasaComboBox.setEnabled(false);
						numeroCasaComboBox.setModel(new NoCasaComboBoxModel(new ArrayList<String>()));
					}
					else {
						numeroCasaComboBox.setEnabled(true);
						numeroCasaComboBox.setModel(new NoCasaComboBoxModel(PiscinaDatos.getListadoNumeroCasaCalle(calle)));
					}
				}
				buscadorPacientes();
			}
		});
		callePrincipalComboBox.setModel(new CallePrincipalComboBoxModel(PiscinaDatos.getListadoCallesPrincipales()));
		callePrincipalComboBox.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		callePrincipalComboBox.setBounds(536, 150, 159, 22);
		panelPacientes.add(callePrincipalComboBox);
		
		numeroCasaComboBox = new JComboBox<String>();
		numeroCasaComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				buscadorPacientes();
			}
		});
		numeroCasaComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		numeroCasaComboBox.setModel(new NoCasaComboBoxModel(new ArrayList<String>()));
		numeroCasaComboBox.setEnabled(false);
		numeroCasaComboBox.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		numeroCasaComboBox.setEnabled(false);
		numeroCasaComboBox.setBounds(705, 150, 115, 22);
		panelPacientes.add(numeroCasaComboBox);
		
		direccionLbl = new JLabel("Direcci\u00F3n:");
		direccionLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		direccionLbl.setBounds(460, 148, 90, 26);
		panelPacientes.add(direccionLbl);
		
		historiaClinicaLbl = new JLabel("No. de Historia Cl\u00EDnica:");
		historiaClinicaLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		historiaClinicaLbl.setBounds(460, 115, 179, 26);
		panelPacientes.add(historiaClinicaLbl);
		
		hcTxt = new JTextFieldModificado();
		hcTxt.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				buscadorPacientes();
			}
		});
		hcTxt.setTipoValidacion(JTextFieldModificado.SIN_VALIDAR);
		hcTxt.setText("");
		hcTxt.setLimite(Definiciones.TAM_MAX_NUMERO_HC);
		hcTxt.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		hcTxt.setBounds(630, 115, 190, 24);
		panelPacientes.add(hcTxt);
		
		panelVisitas = new JPanel();
		panelVisitas.setBackground(Color.WHITE);
		panelPrincipalTab.addTab("a", null, panelVisitas, null);
		panelVisitas.setLayout(null);
		
		botonInfoHojaCargosDia = new JButton("");
		botonInfoHojaCargosDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panelPrincipalTab, "Para agregar una nueva visita, presione el botón Agregar "
						+ "e introduzca los datos necesarios.", null, JOptionPane.INFORMATION_MESSAGE);
			}
		});
		botonInfoHojaCargosDia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonInfoHojaCargosDia.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/infoo0.png")));
		botonInfoHojaCargosDia.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/infoo1.png")));
		botonInfoHojaCargosDia.setContentAreaFilled(false);
		botonInfoHojaCargosDia.setBorder(null);
		botonInfoHojaCargosDia.setBounds(847, 36, 32, 32);
		panelVisitas.add(botonInfoHojaCargosDia);
		
		JLabel listadoVisitasLbl = new JLabel("Hoja de Cargos del d\u00EDa de hoy");
		listadoVisitasLbl.setBorder(new MatteBorder(0, 0, 2, 0, DefinicionesInterfazGrafica.COLOR_BORDE_JLABELS_APP_PRINCIPAL));
		listadoVisitasLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 24));
		listadoVisitasLbl.setBounds(10, 36, 869, 33);
		panelVisitas.add(listadoVisitasLbl);
		
		JScrollPane panelHojaCargosDia = new JScrollPane();
		panelHojaCargosDia.setBounds(10, 83, 869, 427);
		panelVisitas.add(panelHojaCargosDia);
		
		modeloHojaCargosDia = new HojaCargosTableModel();
		
		tablaHojaCargosDia = new JTable();
		tablaHojaCargosDia.setModel(modeloHojaCargosDia);
		ordenamientoHojaCargosDia = new TableRowSorter<HojaCargosTableModel>(modeloHojaCargosDia);
		tablaHojaCargosDia.setRowSorter(ordenamientoHojaCargosDia);
		tablaHojaCargosDia.setRowHeight(tablaHojaCargosDia.getRowHeight() * 3);
		tablaHojaCargosDia.getColumnModel().getColumn(3).setCellRenderer(new MultiLineaCellRendererEditor());
		tablaHojaCargosDia.getColumnModel().getColumn(3).setCellEditor(new MultiLineaCellRendererEditor());
		ordenamientoHojaCargosDia.setSortable(3, false);
		tablaHojaCargosDia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablaHojaCargosDia.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		tablaHojaCargosDia.setSelectionBackground(DefinicionesInterfazGrafica.VERDE_AZULADO_1);
		tablaHojaCargosDia.setSelectionForeground(Color.BLACK);
		tablaHojaCargosDia.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		panelHojaCargosDia.setViewportView(tablaHojaCargosDia);
		
		BotonAnimacion agregarHojaCargosBtn = new BotonAnimacion();
		agregarHojaCargosBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarVisita visita = new AgregarVisita(null);
				visita.setVisible(true);
			}
		});
		agregarHojaCargosBtn.setText("Agregar");
		agregarHojaCargosBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		agregarHojaCargosBtn.setEffectColor(new Color(51, 202, 223));
		agregarHojaCargosBtn.setBackground(new Color(47, 184, 176));
		agregarHojaCargosBtn.setBounds(766, 521, 113, 26);
		panelVisitas.add(agregarHojaCargosBtn);
		
		panelAnalisis = new JPanel();
		panelAnalisis.setBackground(Color.WHITE);
		panelPrincipalTab.addTab("a", null, panelAnalisis, null);
		panelAnalisis.setLayout(null);
		
		JButton botonInfoListadoAnalisis = new JButton("");
		botonInfoListadoAnalisis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panelPrincipalTab, "El sistema busca y muestra los análisis faltantes de cada paciente "
						+ "con una obsolescencia máxima permitida de un (1) mes. Haga doble click en uno de los análisis faltantes para "
						+ "agregar los resultados correspondientes",null,JOptionPane.INFORMATION_MESSAGE);
			}
		});
		botonInfoListadoAnalisis.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonInfoListadoAnalisis.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/infoo0.png")));
		botonInfoListadoAnalisis.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/infoo1.png")));
		botonInfoListadoAnalisis.setBorder(null);
		botonInfoListadoAnalisis.setContentAreaFilled(false);
		botonInfoListadoAnalisis.setBounds(847, 32, 32, 32);
		panelAnalisis.add(botonInfoListadoAnalisis);
		
		JLabel listadoAnalisisFaltantesLbl = new JLabel("Listado de An\u00E1lisis Faltantes");
		listadoAnalisisFaltantesLbl.setToolTipText("");
		listadoAnalisisFaltantesLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 24));
		listadoAnalisisFaltantesLbl.setBorder(new MatteBorder(0, 0, 2, 0, DefinicionesInterfazGrafica.COLOR_BORDE_JLABELS_APP_PRINCIPAL));
		listadoAnalisisFaltantesLbl.setBounds(10, 32, 869, 33);
		panelAnalisis.add(listadoAnalisisFaltantesLbl);
		
		JScrollPane panelListadoAnalisis = new JScrollPane();
		panelListadoAnalisis.setBounds(10, 76, 869, 461);
		panelAnalisis.add(panelListadoAnalisis);
		
		modeloAnalisisEnfermera = new AnalisisFaltantesTableModel();
		
		tablaAnalisisFaltantesEnfermera = new JTable();
		tablaAnalisisFaltantesEnfermera.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablaAnalisisFaltantesEnfermera.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					if(tablaAnalisisFaltantesEnfermera.getSelectedRow()!=-1) {
						Paciente p = cmf.getPaciente((String)modeloAnalisisEnfermera.getValueAt(tablaAnalisisFaltantesEnfermera.getSelectedRow(), 1));
						ArrayList<AnalisisIndicados> analisisIndicados = cmf.listadoAnalisisFaltantes(Auxiliares.fechaListadoAnalisisFaltantesEnfermera());
						Analisis analisis = null;
						
						String tipoAnalisis = (String)modeloAnalisisEnfermera.getValueAt(ordenamientoAnalisisEnfermera.convertRowIndexToModel(tablaAnalisisFaltantesEnfermera.getSelectedRow()), 4);
						Date fecha=null;
						try {
							fecha = DefinicionesInterfazGrafica.FORMATO_BASE_FECHA.parse((String)modeloAnalisisEnfermera.getValueAt(tablaAnalisisFaltantesEnfermera.getSelectedRow(), 3));
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						
						for(int i=0;i<analisisIndicados.size() && analisis==null;i++)
							analisis = analisisIndicados.get(i).getAnalisis(fecha, tipoAnalisis);
						
						AgregarAnalisis agregarAnalisis = new AgregarAnalisis(null,analisis,p);
						agregarAnalisis.setVisible(true);
					}
				}
			}
		});
		tablaAnalisisFaltantesEnfermera.setSelectionBackground(DefinicionesInterfazGrafica.VERDE_AZULADO_1);
		tablaAnalisisFaltantesEnfermera.setSelectionForeground(Color.BLACK);
		tablaAnalisisFaltantesEnfermera.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		tablaAnalisisFaltantesEnfermera.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		ordenamientoAnalisisEnfermera = new TableRowSorter<AnalisisFaltantesTableModel>(modeloAnalisisEnfermera);
		tablaAnalisisFaltantesEnfermera.setRowSorter(ordenamientoAnalisisEnfermera);
		ordenamientoAnalisisEnfermera.setComparator(3, Comparadores.comparadorFechaString());
		tablaAnalisisFaltantesEnfermera.setModel(modeloAnalisisEnfermera);
		panelListadoAnalisis.setViewportView(tablaAnalisisFaltantesEnfermera);
		
		panelReportes = new JPanel();
		panelReportes.setBackground(Color.WHITE);
		panelPrincipalTab.addTab("a", null, panelReportes, null);
		panelReportes.setLayout(null);
		
		JLabel elegirReporteLbl = new JLabel("Elija un Reporte");
		elegirReporteLbl.setBorder(new MatteBorder(0, 0, 2, 0,DefinicionesInterfazGrafica.VERDE_AZULADO_1));
		elegirReporteLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 24));
		elegirReporteLbl.setBounds(10, 32, 869, 34);
		panelReportes.add(elegirReporteLbl);
		
		reporteEmbarazada = new PanelBordeOval(DefinicionesInterfazGrafica.VALOR_ESQUINA_OVAL);
		reporteEmbarazada.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		reporteEmbarazada.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				reporteEmbarazada.setBackground(DefinicionesInterfazGrafica.COLOR_OPCIONES_REPORTES_ANIM);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				reporteEmbarazada.setBackground(DefinicionesInterfazGrafica.COLOR_OPCIONES_REPORTES);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				panelPrincipalTab.setSelectedIndex(5);
			}
		});
		reporteEmbarazada.setBackground(DefinicionesInterfazGrafica.COLOR_OPCIONES_REPORTES);
		reporteEmbarazada.setBounds(10, 80, 280, 220);
		panelReportes.add(reporteEmbarazada);
		reporteEmbarazada.setLayout(null);
		
		JLabel embarazadaLbl = new JLabel("Embarazadas");
		embarazadaLbl.setBorder(new MatteBorder(0, 0, 2, 0, DefinicionesInterfazGrafica.COLOR_BORDE_JLABELS_APP_PRINCIPAL));
		embarazadaLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		embarazadaLbl.setBounds(10, 11, 260, 24);
		reporteEmbarazada.add(embarazadaLbl);
		
		Imagen embarazadaLogo = new Imagen(new ImageIcon(AppPrincipal.class.getResource("/iconos/embarazada0.png")));
		embarazadaLogo.setBounds(238, 3, 32, 32);
		reporteEmbarazada.add(embarazadaLogo);
		
		JLabel embarazadasDescripcionLbl = new JLabel("<html>Listado de embarazadas del consultorio.<html>");
		embarazadasDescripcionLbl.setVerticalAlignment(SwingConstants.TOP);
		embarazadasDescripcionLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		embarazadasDescripcionLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		embarazadasDescripcionLbl.setBounds(10, 46, 260, 161);
		reporteEmbarazada.add(embarazadasDescripcionLbl);
		
		reporteEnRiesgo = new PanelBordeOval(DefinicionesInterfazGrafica.VALOR_ESQUINA_OVAL);
		reporteEnRiesgo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				reporteEnRiesgo.setBackground(DefinicionesInterfazGrafica.COLOR_OPCIONES_REPORTES_ANIM);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				reporteEnRiesgo.setBackground(DefinicionesInterfazGrafica.COLOR_OPCIONES_REPORTES);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				panelPrincipalTab.setSelectedIndex(7);
			}
		});
		reporteEnRiesgo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		reporteEnRiesgo.setBackground(DefinicionesInterfazGrafica.COLOR_OPCIONES_REPORTES);
		reporteEnRiesgo.setBounds(305, 80, 280, 220);
		panelReportes.add(reporteEnRiesgo);
		reporteEnRiesgo.setLayout(null);
		
		JLabel enRiesgoLbl = new JLabel("En Riesgo");
		enRiesgoLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		enRiesgoLbl.setBorder(new MatteBorder(0, 0, 2, 0, DefinicionesInterfazGrafica.COLOR_BORDE_JLABELS_APP_PRINCIPAL));
		enRiesgoLbl.setBounds(10, 11, 260, 24);
		reporteEnRiesgo.add(enRiesgoLbl);
		
		Imagen enRiesgoLogo = new Imagen(new ImageIcon(AppPrincipal.class.getResource("/iconos/enRiesgo0.png")));
		enRiesgoLogo.setBounds(238, 3, 32, 32);
		reporteEnRiesgo.add(enRiesgoLogo);
		
		JLabel enRiesgoDescripcionLbl = new JLabel("<html>Listado de pacientes en riesgo. Los criterios de riesgo son tener más de 3 enfermedades crónicas y, en el caso de las mujeres, tener más de 3 años sin someterse a la prueba citológica.<html>");
		enRiesgoDescripcionLbl.setVerticalAlignment(SwingConstants.TOP);
		enRiesgoDescripcionLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		enRiesgoDescripcionLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		enRiesgoDescripcionLbl.setBounds(10, 45, 260, 161);
		reporteEnRiesgo.add(enRiesgoDescripcionLbl);
		
		reporteAnalisisFaltantes = new PanelBordeOval(DefinicionesInterfazGrafica.VALOR_ESQUINA_OVAL);
		reporteAnalisisFaltantes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				reporteAnalisisFaltantes.setBackground(DefinicionesInterfazGrafica.COLOR_OPCIONES_REPORTES_ANIM);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				reporteAnalisisFaltantes.setBackground(DefinicionesInterfazGrafica.COLOR_OPCIONES_REPORTES);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				panelPrincipalTab.setSelectedIndex(10);
			}
		});
		reporteAnalisisFaltantes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		reporteAnalisisFaltantes.setBackground(DefinicionesInterfazGrafica.COLOR_OPCIONES_REPORTES);
		reporteAnalisisFaltantes.setBounds(600, 80, 280, 220);
		panelReportes.add(reporteAnalisisFaltantes);
		reporteAnalisisFaltantes.setLayout(null);
		
		JLabel analisisIndicadosLbl = new JLabel("An\u00E1lisis Indicados Faltantes");
		analisisIndicadosLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		analisisIndicadosLbl.setBorder(new MatteBorder(0, 0, 2, 0, DefinicionesInterfazGrafica.COLOR_BORDE_JLABELS_APP_PRINCIPAL));
		analisisIndicadosLbl.setBounds(10, 11, 260, 24);
		reporteAnalisisFaltantes.add(analisisIndicadosLbl);
		
		Imagen analisisIndicadosLogo = new Imagen(new ImageIcon(AppPrincipal.class.getResource("/iconos/analisisFaltantes0.png")));
		analisisIndicadosLogo.setBounds(238, 3, 32, 30);
		reporteAnalisisFaltantes.add(analisisIndicadosLogo);
		
		JLabel analisisFaltantesDescripcionLbl = new JLabel("<html>Listado de análisis indicados faltantes de un paciente en un rango de fecha dado<html>");
		analisisFaltantesDescripcionLbl.setVerticalAlignment(SwingConstants.TOP);
		analisisFaltantesDescripcionLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		analisisFaltantesDescripcionLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		analisisFaltantesDescripcionLbl.setBounds(10, 46, 260, 161);
		reporteAnalisisFaltantes.add(analisisFaltantesDescripcionLbl);
		
		reporteVacunados = new PanelBordeOval(DefinicionesInterfazGrafica.VALOR_ESQUINA_OVAL);
		reporteVacunados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				reporteVacunados.setBackground(DefinicionesInterfazGrafica.COLOR_OPCIONES_REPORTES_ANIM);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				reporteVacunados.setBackground(DefinicionesInterfazGrafica.COLOR_OPCIONES_REPORTES);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				panelPrincipalTab.setSelectedIndex(6);
			}
		});
		reporteVacunados.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		reporteVacunados.setBackground(DefinicionesInterfazGrafica.COLOR_OPCIONES_REPORTES);
		reporteVacunados.setBounds(10, 320, 280, 220);
		panelReportes.add(reporteVacunados);
		reporteVacunados.setLayout(null);
		
		vacunadosLbl = new JLabel("Vacunados");
		vacunadosLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		vacunadosLbl.setBorder(new MatteBorder(0, 0, 2, 0, DefinicionesInterfazGrafica.COLOR_BORDE_JLABELS_APP_PRINCIPAL));
		vacunadosLbl.setBounds(10, 11, 260, 24);
		reporteVacunados.add(vacunadosLbl);
		
		Imagen vacunadosLogo = new Imagen(new ImageIcon(AppPrincipal.class.getResource("/iconos/vacunados0.png")));
		vacunadosLogo.setBounds(238, 3, 32, 32);
		reporteVacunados.add(vacunadosLogo);
		
		JLabel vacunadosDescripcionLbl = new JLabel("<html>Listado de pacientes con una vacuna dada.<html>");
		vacunadosDescripcionLbl.setVerticalAlignment(SwingConstants.TOP);
		vacunadosDescripcionLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		vacunadosDescripcionLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		vacunadosDescripcionLbl.setBounds(10, 46, 260, 161);
		reporteVacunados.add(vacunadosDescripcionLbl);
		
		reporteEnfermos = new PanelBordeOval(DefinicionesInterfazGrafica.VALOR_ESQUINA_OVAL);
		reporteEnfermos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				reporteEnfermos.setBackground(DefinicionesInterfazGrafica.COLOR_OPCIONES_REPORTES_ANIM);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				reporteEnfermos.setBackground(DefinicionesInterfazGrafica.COLOR_OPCIONES_REPORTES);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				panelPrincipalTab.setSelectedIndex(8);
			}
		});
		reporteEnfermos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		reporteEnfermos.setBackground(DefinicionesInterfazGrafica.COLOR_OPCIONES_REPORTES);
		reporteEnfermos.setBounds(305, 320, 280, 220);
		panelReportes.add(reporteEnfermos);
		reporteEnfermos.setLayout(null);
		
		JLabel EnfermosLbl = new JLabel("Enfermos");
		EnfermosLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		EnfermosLbl.setBorder(new MatteBorder(0, 0, 2, 0, DefinicionesInterfazGrafica.COLOR_BORDE_JLABELS_APP_PRINCIPAL));
		EnfermosLbl.setBounds(10, 11, 260, 24);
		reporteEnfermos.add(EnfermosLbl);
		
		Imagen enfermosLogo = new Imagen(new ImageIcon(AppPrincipal.class.getResource("/iconos/enfermos0.png")));
		enfermosLogo.setBounds(238, 3, 32, 30);
		reporteEnfermos.add(enfermosLogo);
		
		JLabel enfermosDescripcionLbl = new JLabel("<html>Listado de pacientes con una determinada enfermedad crónica.<html>");
		enfermosDescripcionLbl.setVerticalAlignment(SwingConstants.TOP);
		enfermosDescripcionLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		enfermosDescripcionLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		enfermosDescripcionLbl.setBounds(10, 44, 260, 161);
		reporteEnfermos.add(enfermosDescripcionLbl);
		
		reporteSinVisitas = new PanelBordeOval(DefinicionesInterfazGrafica.VALOR_ESQUINA_OVAL);
		reporteSinVisitas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				reporteSinVisitas.setBackground(DefinicionesInterfazGrafica.COLOR_OPCIONES_REPORTES_ANIM);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				reporteSinVisitas.setBackground(DefinicionesInterfazGrafica.COLOR_OPCIONES_REPORTES);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				panelPrincipalTab.setSelectedIndex(9);
			}
		});
		reporteSinVisitas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		reporteSinVisitas.setBackground(DefinicionesInterfazGrafica.COLOR_OPCIONES_REPORTES);
		reporteSinVisitas.setBounds(600, 320, 280, 220);
		panelReportes.add(reporteSinVisitas);
		reporteSinVisitas.setLayout(null);
		
		JLabel sinVisitasLbl = new JLabel("Sin Visitas");
		sinVisitasLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		sinVisitasLbl.setBorder(new MatteBorder(0, 0, 2, 0, DefinicionesInterfazGrafica.COLOR_BORDE_JLABELS_APP_PRINCIPAL));
		sinVisitasLbl.setBounds(10, 11, 260, 24);
		reporteSinVisitas.add(sinVisitasLbl);
		
		Imagen sinVisitasLogo = new Imagen(new ImageIcon(AppPrincipal.class.getResource("/iconos/sinVisitas0.png")));
		sinVisitasLogo.setBounds(238, 3, 32, 32);
		reporteSinVisitas.add(sinVisitasLogo);
		
		JLabel sinVisitasDescripcionLbl = new JLabel("<html>Listado de pacientes que no han realizado visitas al consultorio desde una fecha dada.<html>");
		sinVisitasDescripcionLbl.setVerticalAlignment(SwingConstants.TOP);
		sinVisitasDescripcionLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		sinVisitasDescripcionLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		sinVisitasDescripcionLbl.setBounds(10, 46, 260, 161);
		reporteSinVisitas.add(sinVisitasDescripcionLbl);
		
		
		panelEmbarazadas = new JPanel();
		panelEmbarazadas.setBackground(Color.WHITE);
		panelPrincipalTab.addTab("a", null, panelEmbarazadas, null);
		panelEmbarazadas.setLayout(null);
		
		botonInfoEmbarazadas = new JButton("");
		botonInfoEmbarazadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panelPrincipalTab, "Para acceder a la información general del paciente "
						+ "haga doble click en el paciente deseado.", null, JOptionPane.INFORMATION_MESSAGE);
			}
		});
		botonInfoEmbarazadas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonInfoEmbarazadas.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/infoo0.png")));
		botonInfoEmbarazadas.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/infoo1.png")));
		botonInfoEmbarazadas.setContentAreaFilled(false);
		botonInfoEmbarazadas.setBorder(null);
		botonInfoEmbarazadas.setBounds(847, 35, 32, 32);
		panelEmbarazadas.add(botonInfoEmbarazadas);
		
		JLabel listadoEmbarazadasLbl = new JLabel("Listado de Embarazadas");
		listadoEmbarazadasLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 24));
		listadoEmbarazadasLbl.setBorder(new MatteBorder(0, 0, 2, 0,DefinicionesInterfazGrafica.VERDE_AZULADO_1));
		listadoEmbarazadasLbl.setBounds(10, 35, 869, 34);
		panelEmbarazadas.add(listadoEmbarazadasLbl);
		
		JScrollPane panelTablaEmbarazadas = new JScrollPane();
		panelTablaEmbarazadas.setBorder(null);
		panelTablaEmbarazadas.setBackground(Color.LIGHT_GRAY);
		panelTablaEmbarazadas.setBounds(10, 80, 869, 446);
		panelEmbarazadas.add(panelTablaEmbarazadas);
		
		
		modeloEmbarazadas = new EmbarazadaTableModel();
		tablaEmbarazadas = new JTable();
		tablaEmbarazadas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					if(tablaEmbarazadas.getSelectedRow()!=-1) {
						VerPaciente paciente = new VerPaciente(null,cmf.getIndicePaciente((String)modeloEmbarazadas.getValueAt(ordenamientoEmbarazadas.convertRowIndexToModel(tablaEmbarazadas.getSelectedRow()), 1)),false);
						paciente.setVisible(true);
					}
				}
			}
		});
		tablaEmbarazadas.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		ordenamientoEmbarazadas = new TableRowSorter<EmbarazadaTableModel>(modeloEmbarazadas);
		tablaEmbarazadas.setRowSorter(ordenamientoEmbarazadas);
		tablaEmbarazadas.setModel(modeloEmbarazadas);
		tablaEmbarazadas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablaEmbarazadas.setSelectionForeground(Color.BLACK);
		tablaEmbarazadas.setSelectionBackground(DefinicionesInterfazGrafica.VERDE_AZULADO_1);
		tablaEmbarazadas.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		panelTablaEmbarazadas.setViewportView(tablaEmbarazadas);
		
		panelVacunados = new JPanel();
		panelVacunados.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panelVacunados.setBackground(Color.WHITE);
		panelPrincipalTab.addTab("a", null, panelVacunados, null);
		panelVacunados.setLayout(null);
		
		botonReCargar_Vacuna = new JButton("");
		botonReCargar_Vacuna.setToolTipText("ReCargar\u00E1 la b\u00FAsqueda de la vacuna\r\na sus valores predeterminados.");
		botonReCargar_Vacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTablaVacunados(null);
				restaurarComponentesVacunas();
			}
		});
		
		botonInfoVacunados = new JButton("");
		botonInfoVacunados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panelPrincipalTab, "Seleccione una vacuna y presione el botón Aceptar. "
						+ "El sistema buscará y mostrará aquellos pacientes que se hayan puesto dicha vacuna. "
						+ "En principio, se muestran todas aquellos pacientes vacunadas con al menos una (1) vacuna. "
						+ "Si se desea volver a dicho estado presionar el botón ReCargar, el cual reiniciará la búsqueda. "
						+ "Para acceder a la información general del paciente haga doble click en el paciente deseado.", null, JOptionPane.INFORMATION_MESSAGE);
			}
		});
		botonInfoVacunados.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonInfoVacunados.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/infoo0.png")));
		botonInfoVacunados.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/infoo1.png")));
		botonInfoVacunados.setContentAreaFilled(false);
		botonInfoVacunados.setBorder(null);
		botonInfoVacunados.setBounds(847, 92, 32, 32);
		panelVacunados.add(botonInfoVacunados);
		
		botonReCargar_Vacuna.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonReCargar_Vacuna.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(30,30), AppPrincipal.class.getResource("/iconos/reset0.png")));
		botonReCargar_Vacuna.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(30,30), AppPrincipal.class.getResource("/iconos/reset1.png")));
		botonReCargar_Vacuna.setContentAreaFilled(false);
		botonReCargar_Vacuna.setBorder(null);
		botonReCargar_Vacuna.setBounds(813, 94, 30, 30);
		panelVacunados.add(botonReCargar_Vacuna);
		
		JLabel listadoVacunadosLbl = new JLabel("Listado de Vacunados");
		listadoVacunadosLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 24));
		listadoVacunadosLbl.setBorder(new MatteBorder(0, 0, 2, 0,DefinicionesInterfazGrafica.VERDE_AZULADO_1));
		listadoVacunadosLbl.setBounds(10, 92, 869, 34);
		panelVacunados.add(listadoVacunadosLbl);
		
		JLabel vacunaLbl = new JLabel("Vacuna:");
		vacunaLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		vacunaLbl.setBorder(null);
		vacunaLbl.setBounds(10, 45, 70, 24);
		panelVacunados.add(vacunaLbl);
		
		seleccionarVacuna = new JComboBox<String>();
		seleccionarVacuna.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				buscarVacunadoBtn.setEnabled(seleccionarVacuna.getSelectedIndex()>0);
			}
		});
		seleccionarVacuna.setModel(new VacunaComboBoxModel(PiscinaDatos.getListadoNombresVacunas()));
		seleccionarVacuna.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		seleccionarVacuna.setBackground(Color.WHITE);
		seleccionarVacuna.setBounds(73, 45, 267, 24);
		panelVacunados.add(seleccionarVacuna);
		
		buscarVacunadoBtn = new BotonAnimacion();
		buscarVacunadoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(seleccionarVacuna.getSelectedIndex()>0) {
					vacunaSeleccionada = (String)seleccionarVacuna.getSelectedItem();
					actualizarTablaVacunados(vacunaSeleccionada);
				}
			}
		});
		buscarVacunadoBtn.setEnabled(false);
		buscarVacunadoBtn.setText("Buscar");
		buscarVacunadoBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		buscarVacunadoBtn.setEffectColor(new Color(51, 202, 223));
		buscarVacunadoBtn.setBackground(new Color(47, 184, 176));
		buscarVacunadoBtn.setBounds(730, 44, 113, 26);
		panelVacunados.add(buscarVacunadoBtn);
		
		JScrollPane panelTablaVacunados = new JScrollPane();
		panelTablaVacunados.setBorder(null);
		panelTablaVacunados.setBackground(Color.LIGHT_GRAY);
		panelTablaVacunados.setBounds(10, 137, 869, 409);
		panelVacunados.add(panelTablaVacunados);
		
		modeloVacunados = new PacienteTableModel();
		vacunaSeleccionada = null;
		
		tablaVacunados = new JTable();
		tablaVacunados.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablaVacunados.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		tablaVacunados.setModel(modeloVacunados);
		ordenamientoVacunados = new TableRowSorter<PacienteTableModel>(modeloVacunados);
		tablaVacunados.setRowSorter(ordenamientoVacunados);
		tablaVacunados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					if(tablaVacunados.getSelectedRow()!=-1) {
						VerPaciente paciente = new VerPaciente(null,cmf.getIndicePaciente((String)modeloVacunados.getValueAt(ordenamientoVacunados.convertRowIndexToModel(tablaVacunados.getSelectedRow()), 2)),false);
						paciente.setVisible(true);
					}
				}
			}
		});
		tablaVacunados.setSelectionForeground(Color.BLACK);
		tablaVacunados.setSelectionBackground(new Color(47, 184, 176));
		tablaVacunados.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		panelTablaVacunados.setViewportView(tablaVacunados);
		
		panelEnRiesgo = new JPanel();
		panelEnRiesgo.setBackground(Color.WHITE);
		panelPrincipalTab.addTab("a", null, panelEnRiesgo, null);
		panelEnRiesgo.setLayout(null);
		
		botonInfoEnRiesgo = new JButton("");
		botonInfoEnRiesgo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panelPrincipalTab, "Para acceder a la información general del paciente "
						+ "haga doble click en el paciente deseado.", null, JOptionPane.INFORMATION_MESSAGE);
			}
		});
		botonInfoEnRiesgo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonInfoEnRiesgo.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/infoo0.png")));
		botonInfoEnRiesgo.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/infoo1.png")));
		botonInfoEnRiesgo.setContentAreaFilled(false);
		botonInfoEnRiesgo.setBorder(null);
		botonInfoEnRiesgo.setBounds(847, 39, 32, 32);
		panelEnRiesgo.add(botonInfoEnRiesgo);
		
		JLabel listadoEnRiesgoLbl = new JLabel("Listado de Pacientes en Riesgo");
		listadoEnRiesgoLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 24));
		listadoEnRiesgoLbl.setBorder(new MatteBorder(0, 0, 2, 0,DefinicionesInterfazGrafica.VERDE_AZULADO_1));
		listadoEnRiesgoLbl.setBounds(10, 39, 869, 34);
		panelEnRiesgo.add(listadoEnRiesgoLbl);
		
		JScrollPane panelTablaEnRiesgo = new JScrollPane();
		panelTablaEnRiesgo.setBorder(null);
		panelTablaEnRiesgo.setBackground(Color.LIGHT_GRAY);
		panelTablaEnRiesgo.setBounds(10, 84, 869, 446);
		panelEnRiesgo.add(panelTablaEnRiesgo);
		
		modeloEnRiesgo = new PacienteTableModel();
		tablaEnRiesgo = new JTable();
		tablaEnRiesgo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablaEnRiesgo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					if(tablaEnRiesgo.getSelectedRow()!=-1) {
						VerPaciente paciente = new VerPaciente(null,cmf.getIndicePaciente((String)modeloEnRiesgo.getValueAt(ordenamientoEnRiesgo.convertRowIndexToModel(tablaEnRiesgo.getSelectedRow()), 2)),false);
						paciente.setVisible(true);
					}
				}
			}
		});
		tablaEnRiesgo.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		ordenamientoEnRiesgo = new TableRowSorter<PacienteTableModel>(modeloEnRiesgo);
		tablaEnRiesgo.setRowSorter(ordenamientoEnRiesgo);
		tablaEnRiesgo.setModel(modeloEnRiesgo);
		tablaEnRiesgo.setSelectionForeground(Color.BLACK);
		tablaEnRiesgo.setSelectionBackground(DefinicionesInterfazGrafica.VERDE_AZULADO_1);
		tablaEnRiesgo.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		panelTablaEnRiesgo.setViewportView(tablaEnRiesgo);
		
		panelEnfermos = new JPanel();
		panelEnfermos.setToolTipText("ReCargar\u00E1 la b\u00FAsqueda de la enfermedad a\r\nsus valores predeterminados.");
		panelEnfermos.setBackground(Color.WHITE);
		panelPrincipalTab.addTab("a", null, panelEnfermos, null);
		panelEnfermos.setLayout(null);
		
		botonReCargar_Enfermedad = new JButton("");
		botonReCargar_Enfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTablaEnfermos(null);
				restaurarComponentesEnfermedad();
			}
		});
		
		botonInfoEnfermedad = new JButton("");
		botonInfoEnfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panelPrincipalTab, "Seleccione una enfermedad crónica y presione el botón Aceptar. "
						+ "El sistema buscará y mostrará aquellos pacientes que presenten dicha enfermedad. "
						+ "En principio, se muestran todas aquellos pacientes con al menos una (1) enfermedad crónica. "
						+ "Si se desea volver a dicho estado presionar el botón ReCargar, el cual reiniciará la búsqueda. "
						+ "Para acceder a la información general del paciente haga doble click en el paciente deseado.", null, JOptionPane.INFORMATION_MESSAGE);
			}
		});
		botonInfoEnfermedad.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonInfoEnfermedad.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/infoo0.png")));
		botonInfoEnfermedad.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/infoo1.png")));
		botonInfoEnfermedad.setContentAreaFilled(false);
		botonInfoEnfermedad.setBorder(null);
		botonInfoEnfermedad.setBounds(847, 97, 32, 32);
		panelEnfermos.add(botonInfoEnfermedad);
		
		botonReCargar_Enfermedad.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonReCargar_Enfermedad.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(30,30), AppPrincipal.class.getResource("/iconos/reset0.png")));
		botonReCargar_Enfermedad.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(30,30), AppPrincipal.class.getResource("/iconos/reset1.png")));
		botonReCargar_Enfermedad.setContentAreaFilled(false);
		botonReCargar_Enfermedad.setBorder(null);
		botonReCargar_Enfermedad.setBounds(813, 99, 30, 30);
		panelEnfermos.add(botonReCargar_Enfermedad);
		
		JLabel enfermedadLbl = new JLabel("Enfermedad:");
		enfermedadLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		enfermedadLbl.setBorder(null);
		enfermedadLbl.setBounds(10, 53, 101, 24);
		panelEnfermos.add(enfermedadLbl);
		
		seleccionarEnfermedad = new JComboBox<String>();
		seleccionarEnfermedad.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				buscarEnfermoBtn.setEnabled(seleccionarEnfermedad.getSelectedIndex()>0);
			}
		});
		seleccionarEnfermedad.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		seleccionarEnfermedad.setModel(new EnfermedadesCronicasComboBoxModel(PiscinaDatos.getListadoEnfermedadesCronicas()));
		seleccionarEnfermedad.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		seleccionarEnfermedad.setBackground(Color.WHITE);
		seleccionarEnfermedad.setBounds(108, 53, 327, 24);
		panelEnfermos.add(seleccionarEnfermedad);
		
		buscarEnfermoBtn = new BotonAnimacion();
		buscarEnfermoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enfermedadSeleccionada = (String)seleccionarEnfermedad.getSelectedItem();
				actualizarTablaEnfermos(enfermedadSeleccionada);
			}
		});
		buscarEnfermoBtn.setEnabled(false);
		buscarEnfermoBtn.setText("Buscar");
		buscarEnfermoBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		buscarEnfermoBtn.setEffectColor(new Color(51, 202, 223));
		buscarEnfermoBtn.setBackground(new Color(47, 184, 176));
		buscarEnfermoBtn.setBounds(730, 52, 113, 26);
		panelEnfermos.add(buscarEnfermoBtn);
		
		JLabel listadoEnfermosLbl = new JLabel("Listado de Pacientes Enfermos");
		listadoEnfermosLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 24));
		listadoEnfermosLbl.setBorder(new MatteBorder(0, 0, 2, 0,DefinicionesInterfazGrafica.VERDE_AZULADO_1));
		listadoEnfermosLbl.setBounds(10, 95, 869, 34);
		panelEnfermos.add(listadoEnfermosLbl);
		
		JScrollPane panelTablaEnfermos = new JScrollPane();
		panelTablaEnfermos.setBorder(null);
		panelTablaEnfermos.setBackground(Color.LIGHT_GRAY);
		panelTablaEnfermos.setBounds(10, 140, 869, 409);
		panelEnfermos.add(panelTablaEnfermos);
		
		modeloEnfermos = new PacienteTableModel();
		enfermedadSeleccionada = null;
		
		tablaEnfermos = new JTable();
		tablaEnfermos.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		tablaEnfermos.setModel(modeloEnfermos);
		ordenamientoEnfermos = new TableRowSorter<PacienteTableModel>(modeloEnfermos);
		tablaEnfermos.setRowSorter(ordenamientoEnfermos);
		tablaEnfermos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					if(tablaEnfermos.getSelectedRow()!=-1) {
						VerPaciente paciente = new VerPaciente(null,cmf.getIndicePaciente((String)modeloEnfermos.getValueAt(ordenamientoEnfermos.convertRowIndexToModel(tablaEnfermos.getSelectedRow()), 2)),false);
						paciente.setVisible(true);
					}
				}
			}
		});
		tablaEnfermos.setSelectionForeground(Color.BLACK);
		tablaEnfermos.setSelectionBackground(new Color(47, 184, 176));
		tablaEnfermos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablaEnfermos.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		panelTablaEnfermos.setViewportView(tablaEnfermos);
		
		panelSinVisitas = new JPanel();
		panelSinVisitas.setBackground(Color.WHITE);
		panelPrincipalTab.addTab("a", null, panelSinVisitas, null);
		panelSinVisitas.setLayout(null);
		
		botonInfoSinVisitas = new JButton("");
		botonInfoSinVisitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panelPrincipalTab, "Seleccione una fecha mínima válida y presione el botón Aceptar. "
						+ "Tenga en cuenta que el rango válido es entre el día de hoy y los años de obsolescencia permitidos <"+Definiciones.AGNOS_OBSOLESCENCIA_SISTEMA+">. "
						+ "El sistema buscará y mostrará aquellos pacientes que no hayan realizado visitas después de la fecha seleccionada (inclusiva). "
						+ "Para acceder a la información general del paciente haga doble click en el paciente deseado.", null, JOptionPane.INFORMATION_MESSAGE);
			}
		});
		botonInfoSinVisitas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonInfoSinVisitas.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/infoo0.png")));
		botonInfoSinVisitas.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/infoo1.png")));
		botonInfoSinVisitas.setContentAreaFilled(false);
		botonInfoSinVisitas.setBorder(null);
		botonInfoSinVisitas.setBounds(847, 97, 32, 32);
		panelSinVisitas.add(botonInfoSinVisitas);
		
		JLabel fechaVisitaLbl = new JLabel("Fecha M\u00EDnima:");
		fechaVisitaLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		fechaVisitaLbl.setBorder(null);
		fechaVisitaLbl.setBounds(10, 60, 113, 24);
		panelSinVisitas.add(fechaVisitaLbl);
		
		JScrollPane panelTablaSinVisita = new JScrollPane();
		panelTablaSinVisita.setBorder(null);
		panelTablaSinVisita.setBackground(Color.LIGHT_GRAY);
		panelTablaSinVisita.setBounds(10, 140, 869, 409);
		panelSinVisitas.add(panelTablaSinVisita);
		
		modeloSinVisitas = new PacienteTableModel();
		fechaSinVisitaSeleccionada = null;
		
		tablaSinVisita = new JTable();
		tablaSinVisita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					if(tablaSinVisita.getSelectedRow()!=-1) {
						VerPaciente paciente = new VerPaciente(null,cmf.getIndicePaciente((String)modeloSinVisitas.getValueAt(ordenamientoSinVisitas.convertRowIndexToModel(tablaSinVisita.getSelectedRow()), 2)),false);
						paciente.setVisible(true);
					}
				}
			}
		});
		tablaSinVisita.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablaSinVisita.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		ordenamientoSinVisitas = new TableRowSorter<PacienteTableModel>(modeloSinVisitas);
		tablaSinVisita.setRowSorter(ordenamientoSinVisitas);
		tablaSinVisita.setModel(modeloSinVisitas);
		tablaSinVisita.setSelectionForeground(Color.BLACK);
		tablaSinVisita.setSelectionBackground(new Color(47, 184, 176));
		tablaSinVisita.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		panelTablaSinVisita.setViewportView(tablaSinVisita);
		
		BotonAnimacion buscarSinVisitaBtn = new BotonAnimacion();
		buscarSinVisitaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date fecha = fechaMinimaSinVisita.getDate();
				if(fecha==null || !Validaciones.validarRangoFecha(fecha, fechaMinimaSinVisita.getMinSelectableDate(), fechaMinimaSinVisita.getMaxSelectableDate())) {
					JOptionPane.showMessageDialog(panelSinVisitas, ErroresInterfazGrafica.ERROR_FECHA_SIN_VISITAS_REPORTE, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
					fechaSinVisitaSeleccionada=fecha;
					actualizarTablaSinVisitas(fechaSinVisitaSeleccionada);
				}
			}
		});
		buscarSinVisitaBtn.setText("Buscar");
		buscarSinVisitaBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		buscarSinVisitaBtn.setEffectColor(new Color(51, 202, 223));
		buscarSinVisitaBtn.setBackground(new Color(47, 184, 176));
		buscarSinVisitaBtn.setBounds(730, 59, 113, 26);
		panelSinVisitas.add(buscarSinVisitaBtn);
		
		JLabel listadoSinVisitaLbl = new JLabel("Listado de Pacientes Sin Visitas al Consultorio\r\n");
		listadoSinVisitaLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 24));
		listadoSinVisitaLbl.setBorder(new MatteBorder(0, 0, 2, 0,DefinicionesInterfazGrafica.VERDE_AZULADO_1));
		listadoSinVisitaLbl.setBounds(10, 95, 869, 34);
		panelSinVisitas.add(listadoSinVisitaLbl);
		
		fechaMinimaSinVisita = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		fechaMinimaSinVisita.setSelectableDateRange(Auxiliares.sumarAnyosFecha(new Date(), -Definiciones.AGNOS_OBSOLESCENCIA_SISTEMA), new Date());
		fechaMinimaSinVisita.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		fechaMinimaSinVisita.setBounds(123, 60, 142, 24);
		panelSinVisitas.add(fechaMinimaSinVisita);
		
		panelAnalisisFaltantes = new JPanel();
		panelAnalisisFaltantes.setBackground(Color.WHITE);
		panelPrincipalTab.addTab("a", null, panelAnalisisFaltantes, null);
		panelAnalisisFaltantes.setLayout(null);
		
		botonInfoAnalisisFaltante = new JButton("");
		botonInfoAnalisisFaltante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panelPrincipalTab, "Seleccione un paciente por el carnet de identidad. A continuación, "
						+ "seleccione una fecha mínima válida y presione el botón Aceptar. "
						+ "Tenga en cuenta que el rango válido es entre el día de hoy y los años de obsolescencia permitidos <"+Definiciones.AGNOS_OBSOLESCENCIA_SISTEMA+">. "
						+ "El sistema buscará y mostrará los análisis faltantes después de la fecha seleccionada (inclusiva) del paciente seleccionado. "
						+ "Para acceder a la información general del paciente haga doble click en el paciente deseado.", null, JOptionPane.INFORMATION_MESSAGE);
			}
		});
		botonInfoAnalisisFaltante.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonInfoAnalisisFaltante.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/infoo0.png")));
		botonInfoAnalisisFaltante.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/infoo1.png")));
		botonInfoAnalisisFaltante.setContentAreaFilled(false);
		botonInfoAnalisisFaltante.setBorder(null);
		botonInfoAnalisisFaltante.setBounds(847, 104, 32, 32);
		panelAnalisisFaltantes.add(botonInfoAnalisisFaltante);
		
		JLabel fechaAnalisisLbl = new JLabel("Fecha M\u00EDnima:");
		fechaAnalisisLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		fechaAnalisisLbl.setBorder(null);
		fechaAnalisisLbl.setBounds(10, 69, 113, 24);
		panelAnalisisFaltantes.add(fechaAnalisisLbl);
		
		fechaMinimaAnalisisFaltante = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		fechaMinimaAnalisisFaltante.setSelectableDateRange(Auxiliares.sumarAnyosFecha(new Date(), -Definiciones.AGNOS_OBSOLESCENCIA_SISTEMA), new Date());
		fechaMinimaAnalisisFaltante.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		fechaMinimaAnalisisFaltante.setBounds(123, 69, 142, 24);
		panelAnalisisFaltantes.add(fechaMinimaAnalisisFaltante);
		
		buscarAnalisisFaltanteBtn = new BotonAnimacion();
		buscarAnalisisFaltanteBtn.setEnabled(false);
		buscarAnalisisFaltanteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date fecha = fechaMinimaAnalisisFaltante.getDate();
				if(fecha==null || !Validaciones.validarRangoFecha(fecha, fechaMinimaAnalisisFaltante.getMinSelectableDate(), fechaMinimaAnalisisFaltante.getMaxSelectableDate())) {
					JOptionPane.showMessageDialog(panelAnalisisFaltantes, ErroresInterfazGrafica.ERROR_FECHA_ANALISIS_FALTANTES_REPORTE, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
					actualizarTablaAnalisisFaltantesReporte(fecha);
				}
			}
		});
		buscarAnalisisFaltanteBtn.setText("Buscar");
		buscarAnalisisFaltanteBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		buscarAnalisisFaltanteBtn.setEffectColor(new Color(51, 202, 223));
		buscarAnalisisFaltanteBtn.setBackground(new Color(47, 184, 176));
		buscarAnalisisFaltanteBtn.setBounds(730, 68, 113, 26);
		panelAnalisisFaltantes.add(buscarAnalisisFaltanteBtn);
		
		JLabel listadoAnalisisFaltanteLbl = new JLabel("Listado de An\u00E1lisis Faltantes\r\n");
		listadoAnalisisFaltanteLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 24));
		listadoAnalisisFaltanteLbl.setBorder(new MatteBorder(0, 0, 2, 0,DefinicionesInterfazGrafica.VERDE_AZULADO_1));
		listadoAnalisisFaltanteLbl.setBounds(10, 104, 869, 34);
		panelAnalisisFaltantes.add(listadoAnalisisFaltanteLbl);
		
		JScrollPane panelTablaAnalisisFaltantes = new JScrollPane();
		panelTablaAnalisisFaltantes.setBorder(null);
		panelTablaAnalisisFaltantes.setBackground(Color.LIGHT_GRAY);
		panelTablaAnalisisFaltantes.setBounds(10, 149, 869, 373);
		panelAnalisisFaltantes.add(panelTablaAnalisisFaltantes);
		
		modeloAnalisisFaltantesReporte = new PacienteAnalisisFaltantesTableModel();
		JTable tablaAnalisisFaltante = new JTable();
		tablaAnalisisFaltante.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		ordenamientoAnalisisFaltantesReporte = new TableRowSorter<PacienteAnalisisFaltantesTableModel>(modeloAnalisisFaltantesReporte);
		ordenamientoAnalisisFaltantesReporte.setComparator(0, Comparadores.comparadorFechaString());
		tablaAnalisisFaltante.setRowSorter(ordenamientoAnalisisFaltantesReporte);
		tablaAnalisisFaltante.setModel(modeloAnalisisFaltantesReporte);
		tablaAnalisisFaltante.setSelectionForeground(Color.BLACK);
		tablaAnalisisFaltante.setSelectionBackground(new Color(47, 184, 176));
		tablaAnalisisFaltante.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		panelTablaAnalisisFaltantes.setViewportView(tablaAnalisisFaltante);
		
		JLabel pacienteAnalisisFaltanteLbl = new JLabel("Paciente:");
		pacienteAnalisisFaltanteLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		pacienteAnalisisFaltanteLbl.setBorder(null);
		pacienteAnalisisFaltanteLbl.setBounds(10, 39, 70, 24);
		panelAnalisisFaltantes.add(pacienteAnalisisFaltanteLbl);
		
		modeloPacienteComboBoxModel = new PacienteComboBoxModel(CMF.getInstancia().getListadoCI());
		
		seleccionarPacienteAnalisisFaltante = new JComboBox<String>();
		seleccionarPacienteAnalisisFaltante.setModel(modeloPacienteComboBoxModel);
		seleccionarPacienteAnalisisFaltante.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				buscarAnalisisFaltanteBtn.setEnabled(seleccionarPacienteAnalisisFaltante.getSelectedIndex()>0);
			}
		});
		seleccionarPacienteAnalisisFaltante.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		seleccionarPacienteAnalisisFaltante.setBackground(Color.WHITE);
		seleccionarPacienteAnalisisFaltante.setBounds(84, 39, 256, 24);
		panelAnalisisFaltantes.add(seleccionarPacienteAnalisisFaltante);
		
		panelContenedor.add(panelContenedorOpciones);
		panelContenedorOpciones.setLayout(null);
		
		opcionInicio = new PanelOpcion();
		opcionInicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		opcionInicio.setOpaque(false);
		opcionInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				opcionInicio.setOpaque(true);
				opcionInicio.repaint();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				opcionInicio.setOpaque(false);
				opcionInicio.repaint();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				panelPrincipalTab.setSelectedIndex(0);
			}
		});
		opcionInicio.setBackground(DefinicionesInterfazGrafica.VERDE_AZULADO_2);
		opcionInicio.setSeleccionado(true);
		opcionInicio.setBounds(0, 11, 300, 75);
		panelContenedorOpciones.add(opcionInicio);
		opcionInicio.setLayout(null);
		
		logoInicio = new Imagen(new ImageIcon(AppPrincipal.class.getResource("/iconos/home0.png")));
		logoInicio.setBounds(67, 18, 38, 38);
		opcionInicio.add(logoInicio);
		
		textoInico = new JLabel("INICIO");
		textoInico.setHorizontalTextPosition(SwingConstants.CENTER);
		textoInico.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		textoInico.setBounds(126, 21, 82, 33);
		opcionInicio.add(textoInico);
		
		opcionPacientes = new PanelOpcion();
		opcionPacientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		opcionPacientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				opcionPacientes.setOpaque(true);
				opcionPacientes.repaint();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				opcionPacientes.setOpaque(false);
				opcionPacientes.repaint();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				panelPrincipalTab.setSelectedIndex(1);
			}
		});
		opcionPacientes.setBackground(DefinicionesInterfazGrafica.VERDE_AZULADO_2);
		opcionPacientes.setLayout(null);
		opcionPacientes.setSeleccionado(true);
		opcionPacientes.setOpaque(false);
		opcionPacientes.setBounds(0, 86, 300, 75);
		panelContenedorOpciones.add(opcionPacientes);
		
		logoPacientes = new Imagen(new ImageIcon(AppPrincipal.class.getResource("/iconos/paciente0.png")));
		logoPacientes.setBounds(65, 15, 45, 45);
		opcionPacientes.add(logoPacientes);
		
		textoPacientes = new JLabel("PACIENTES");
		textoPacientes.setHorizontalTextPosition(SwingConstants.CENTER);
		textoPacientes.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		textoPacientes.setBounds(126, 21, 104, 33);
		opcionPacientes.add(textoPacientes);
		
		opcionModable = new PanelOpcion();
		opcionModable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		opcionModable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				opcionModable.setOpaque(true);
				opcionModable.repaint();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				opcionModable.setOpaque(false);
				opcionModable.repaint();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(usuario==TipoCuentaUsuario.MEDICO)
					panelPrincipalTab.setSelectedIndex(2);
				else
					panelPrincipalTab.setSelectedIndex(3);
			}
		});
		opcionModable.setBackground(DefinicionesInterfazGrafica.VERDE_AZULADO_2);
		opcionModable.setLayout(null);
		opcionModable.setSeleccionado(true);
		opcionModable.setOpaque(false);
		opcionModable.setBounds(0, 236, 300, 75);
		panelContenedorOpciones.add(opcionModable);
		
		ImageIcon imagen=new ImageIcon(AppPrincipal.class.getResource("/iconos/analisis0.png"));
		if(usuario==TipoCuentaUsuario.MEDICO)
			imagen = new ImageIcon(AppPrincipal.class.getResource("/iconos/visita0.png"));
		
		logoModable = new Imagen(imagen);
		logoModable.setBounds(68, 18, 38, 38);
		opcionModable.add(logoModable);
		
		textoModable = new JLabel("ANÁLISIS");
		if(usuario==TipoCuentaUsuario.MEDICO)
			textoModable.setText("VISITAS");
		textoModable.setHorizontalTextPosition(SwingConstants.CENTER);
		textoModable.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		textoModable.setBounds(126, 21, 149, 33);
		opcionModable.add(textoModable);
		
		opcionReportes = new PanelOpcion();
		opcionReportes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		opcionReportes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				opcionReportes.setOpaque(true);
				opcionReportes.repaint();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				opcionReportes.setOpaque(false);
				opcionReportes.repaint();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				panelPrincipalTab.setSelectedIndex(4);
			}
		});
		opcionReportes.setBackground(DefinicionesInterfazGrafica.VERDE_AZULADO_2);
		opcionReportes.setLayout(null);
		opcionReportes.setSeleccionado(true);
		opcionReportes.setOpaque(false);
		opcionReportes.setBounds(0, 311, 300, 75);
		panelContenedorOpciones.add(opcionReportes);
		
		logoReportes = new Imagen(new ImageIcon(AppPrincipal.class.getResource("/iconos/reporte0.png")));
		logoReportes.setBounds(67, 18, 38, 38);
		opcionReportes.add(logoReportes);
		
		textoReportes = new JLabel("REPORTES");
		textoReportes.setHorizontalTextPosition(SwingConstants.CENTER);
		textoReportes.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		textoReportes.setBounds(126, 21, 114, 33);
		opcionReportes.add(textoReportes);
		
		opcionHojaCargos = new PanelOpcion();
		opcionHojaCargos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		opcionHojaCargos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				opcionHojaCargos.setOpaque(true);
				opcionHojaCargos.repaint();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				opcionHojaCargos.setOpaque(false);
				opcionHojaCargos.repaint();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				a();
			}
		});
		opcionHojaCargos.setLayout(null);
		opcionHojaCargos.setSeleccionado(true);
		opcionHojaCargos.setOpaque(false);
		opcionHojaCargos.setBackground(DefinicionesInterfazGrafica.VERDE_AZULADO_2);
		opcionHojaCargos.setBounds(0, 161, 300, 75);
		panelContenedorOpciones.add(opcionHojaCargos);
		
		logoHojaCargo = new Imagen(new ImageIcon(AppPrincipal.class.getResource("/iconos/hojaCargo.png")));
		logoHojaCargo.setBounds(72, 21, 32, 32);
		opcionHojaCargos.add(logoHojaCargo);
		
		JLabel hojaCargoLbl = new JLabel("HOJAS DE CARGO");
		hojaCargoLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		hojaCargoLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		hojaCargoLbl.setBounds(126, 21, 164, 33);
		opcionHojaCargos.add(hojaCargoLbl);
		
		salirBtn = new JButton("");
		salirBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(rootPane, DefinicionesInterfazGrafica.PREGUNTA_SALIR_PRINCIPAL, null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
					System.exit(0);
				else
					salirBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/exit0.png")));
			}
		});
		salirBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				salirBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/exit1.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				salirBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/exit0.png")));
			}
		});
		salirBtn.setContentAreaFilled(false);
		salirBtn.setBounds(1154, 4, 36, 36);
		salirBtn.setHorizontalAlignment(SwingConstants.CENTER);
		salirBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		salirBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		salirBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/exit0.png")));
		
		minimizarBtn = new JButton("");
		minimizarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(ICONIFIED);
			}
		});
		minimizarBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				minimizarBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/minimize1.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				minimizarBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/minimize0.png")));
			}
		});
		minimizarBtn.setContentAreaFilled(false);
		minimizarBtn.setBounds(1117, 4, 36, 36);
		panelBase.setLayout(null);
		minimizarBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		minimizarBtn.setHorizontalAlignment(SwingConstants.CENTER);
		minimizarBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		minimizarBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/minimize0.png")));
		panelSuperior.setLayout(null);
		panelSuperior.add(panelSuperiorLbl);
		panelSuperior.add(minimizarBtn);
		panelSuperior.add(salirBtn);
		panelBase.add(panelSuperior);
		panelBase.add(panelContenedor);
		
		panelPrincipalTab.setSelectedIndex(0);
		
		
		panelHojasCargo = new JPanel();
		panelHojasCargo.setBackground(Color.WHITE);
		panelPrincipalTab.addTab("New tab", null, panelHojasCargo, null);
		panelHojasCargo.setLayout(null);
		
		botonInfoHojaCargos = new JButton("");
		botonInfoHojaCargos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panelPrincipalTab,"Seleccione una fecha válida y presione el botón Aceptar. "
						+ "Tenga en cuenta que el rango válido es entre el día de hoy y los años de obsolescencia permitidos <"+Definiciones.AGNOS_OBSOLESCENCIA_SISTEMA+">. "
						+ "El sistema buscará y mostrará las hojas de cargos correspondientes a la fecha seleccionada.", null, JOptionPane.INFORMATION_MESSAGE);
			}
		});
		botonInfoHojaCargos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonInfoHojaCargos.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/infoo0.png")));
		botonInfoHojaCargos.setRolloverIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), AppPrincipal.class.getResource("/iconos/infoo1.png")));
		botonInfoHojaCargos.setContentAreaFilled(false);
		botonInfoHojaCargos.setBorder(null);
		botonInfoHojaCargos.setBounds(847, 90, 32, 32);
		panelHojasCargo.add(botonInfoHojaCargos);
		
		JLabel fechaVisitaHojaCargoLbl = new JLabel("Fecha:");
		fechaVisitaHojaCargoLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		fechaVisitaHojaCargoLbl.setBorder(null);
		fechaVisitaHojaCargoLbl.setBounds(10, 55, 56, 24);
		panelHojasCargo.add(fechaVisitaHojaCargoLbl);
		
		fechaVisitaHojaCargo = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		fechaVisitaHojaCargo.setSelectableDateRange(Auxiliares.sumarAnyosFecha(new Date(), -Definiciones.AGNOS_OBSOLESCENCIA_SISTEMA), new Date());
		fechaVisitaHojaCargo.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		fechaVisitaHojaCargo.setBounds(65, 55, 142, 24);
		panelHojasCargo.add(fechaVisitaHojaCargo);
		
		buscarHojaCargo = new BotonAnimacion();
		buscarHojaCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date fecha = fechaVisitaHojaCargo.getDate();
				if(fecha==null || !Validaciones.validarRangoFecha(fecha, fechaVisitaHojaCargo.getMinSelectableDate(), fechaVisitaHojaCargo.getMaxSelectableDate())) {
					JOptionPane.showMessageDialog(panelHojasCargo, ErroresInterfazGrafica.ERROR_FECHA_HOJA_CARGO, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
					actualizarTablaHojaDeCargos(fecha);
				}
			}
		});
		buscarHojaCargo.setText("Buscar");
		buscarHojaCargo.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		buscarHojaCargo.setEffectColor(new Color(51, 202, 223));
		buscarHojaCargo.setBackground(new Color(47, 184, 176));
		buscarHojaCargo.setBounds(730, 54, 113, 26);
		panelHojasCargo.add(buscarHojaCargo);
		
		JLabel listadoHojaCargoTxt = new JLabel("Listado de Hojas de Cargo");
		listadoHojaCargoTxt.setFont(new Font("Roboto Medium", Font.PLAIN, 24));
		listadoHojaCargoTxt.setBorder(new MatteBorder(0, 0, 2, 0,DefinicionesInterfazGrafica.VERDE_AZULADO_1));
		listadoHojaCargoTxt.setBounds(10, 90, 869, 34);
		panelHojasCargo.add(listadoHojaCargoTxt);
		
		JScrollPane panelTablaHojaCargo = new JScrollPane();
		panelTablaHojaCargo.setBorder(null);
		panelTablaHojaCargo.setBackground(Color.LIGHT_GRAY);
		panelTablaHojaCargo.setBounds(10, 135, 869, 409);
		panelHojasCargo.add(panelTablaHojaCargo);
		
		modeloHojasCargos = new HojaCargosTableModel();
		ordenamientoHojaCargo = new TableRowSorter<HojaCargosTableModel>(modeloHojasCargos);
		
		tablaHojasCargo = new JTable();
		tablaHojasCargo.setModel(modeloHojasCargos);
		tablaHojasCargo.setRowSorter(ordenamientoHojaCargo);
		tablaHojasCargo.setRowHeight(tablaHojasCargo.getRowHeight() * 3);
		tablaHojasCargo.getColumnModel().getColumn(3).setCellRenderer(new MultiLineaCellRendererEditor());
		tablaHojasCargo.getColumnModel().getColumn(3).setCellEditor(new MultiLineaCellRendererEditor());
		ordenamientoHojaCargo.setSortable(3, false);
		tablaHojasCargo.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		tablaHojasCargo.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		tablaHojasCargo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablaHojasCargo.setSelectionForeground(Color.BLACK);
		tablaHojasCargo.setSelectionBackground(DefinicionesInterfazGrafica.VERDE_AZULADO_1);
		panelTablaHojaCargo.setViewportView(tablaHojasCargo);
		
		actualizarSistemaDatos();
		this.setLocationRelativeTo(null);
	}
	
	private static void restaurarComponentesBusquedaPacientes() {
		nombreTxt.setText("");
		primerApellidoTxt.setText("");
		segundoApellidoTxt.setText("");
		carnetIdentidadTxt.setText("");
		callePrincipalComboBox.setSelectedIndex(0);
		hcTxt.setText("");
	}
	private static void restaurarTablaPacientes() {
		listadoPacientesPrincipalActual = cmf.getListadoPacientes();
		actualizarTablaPacientes();
	}
	private static void restaurarComponentesVacunas() {
		vacunaSeleccionada=null;
		seleccionarVacuna.setSelectedIndex(0);
	}
	private static void restaurarComponentesEnfermedad() {
		enfermedadSeleccionada=null;
		seleccionarEnfermedad.setSelectedIndex(0);
	}
	private static void restaurarComponentesSinVisitas() {
		fechaMinimaSinVisita.setDate(null);
		fechaMinimaSinVisita.setMaxSelectableDate(new Date());
	}
	private static void restaurarHojaCargos() {
		fechaVisitaHojaCargo.setDate(null);
		fechaVisitaHojaCargo.setMaxSelectableDate(new Date());
	}
	private static void restaurarComponentesAnalisisFaltantesReporte() {
		modeloPacienteComboBoxModel = new PacienteComboBoxModel(cmf.getListadoCI());
		seleccionarPacienteAnalisisFaltante.setModel(modeloPacienteComboBoxModel);
		seleccionarPacienteAnalisisFaltante.setSelectedIndex(0);
		fechaMinimaAnalisisFaltante.setDate(null);
		fechaMinimaAnalisisFaltante.setMaxSelectableDate(new Date());
	}
	private static void actualizarTablaVacunados(String vacuna) {
		modeloVacunados.actualizar(cmf.listadoPacientesVacuna(vacuna));
	}
	private static void actualizarTablaEnfermos(String enfermedad) {
		modeloEnfermos.actualizar(cmf.listadoPacientesEnfermedad(enfermedad));
	}
	private static void actualizarTablaSinVisitas(Date fecha) {
		modeloSinVisitas.actualizar(cmf.listadoPacientesSinVisita(fecha));
	}
	private static void actualizarTablaPacientes() {
		modeloPaciente.actualizar(listadoPacientesPrincipalActual);
	}
	private static void actualizarTablaEmbarazadas() {
		modeloEmbarazadas.actualizar(cmf.listadoEmbarazadas());
	}
	private static void actualizarTablaAnalisisFaltantesReporte(Date fecha) {
		if(fecha == null) {
			modeloAnalisisFaltantesReporte.actualizar(new ArrayList<Analisis>());
		}
		else {
			modeloAnalisisFaltantesReporte.actualizar(cmf.listadoAnalisisFaltantesPaciente(cmf.getPaciente((String)seleccionarPacienteAnalisisFaltante.getSelectedItem()), fecha).getListadoAnalisis());
		}
	}
	private static void actualizarTablaAnalisisFaltantesEnfermera() {
		modeloAnalisisEnfermera.actualizar(cmf.listadoAnalisisFaltantes(Auxiliares.fechaListadoAnalisisFaltantesEnfermera()));
	}
	private static void actualizarTablaHojaDeCargos(Date fecha) {
		if(fecha == null)
			modeloHojasCargos.actualizar(new ArrayList<VisitaHojaDeCargos>());
		else {
			try {
				Calendar c = Calendar.getInstance();
				c.setTime(fecha);
				modeloHojasCargos.actualizar(cmf.getHojaDeCargosDia(c).getListadoHojaCargos());
			}catch(Exception e1) {
				modeloHojasCargos.actualizar(new ArrayList<VisitaHojaDeCargos>());
			}
		}
	}
	private static void actualizarTablaEnRiesgo() {
		modeloEnRiesgo.actualizar(cmf.pacientesEnRiesgo());
	}
	private static void actualizarTablaHojaCargosDia() {
		try {
			modeloHojaCargosDia.actualizar(cmf.getHojaDeCargosDia(Calendar.getInstance()).getListadoHojaCargos());
		}catch(Exception e1) {
			modeloHojaCargosDia.actualizar(new ArrayList<VisitaHojaDeCargos>());
		}
	}
	private static void actualizarTarjetasInicio() {
		int numeroEmbarazadas = cmf.listadoEmbarazadas().size();
		int totalPacientesFemeninas = cmf.listadoPacientesFemeninas().size();
		int porcientoEmbarazadas = totalPacientesFemeninas==0 ? 0 : (int)((float)numeroEmbarazadas/totalPacientesFemeninas*100.00);
		numeroEmbarazadaLbl.setText(""+numeroEmbarazadas);
		barraProgresoEmbarazada.setValue(porcientoEmbarazadas);
		porcientoEmbarazadasLbl.setText(porcientoEmbarazadas+"%");
		
		int numeroEnRiesgo = cmf.pacientesEnRiesgo().size();
		int totalPacientes = cmf.getListadoPacientes().size();
		int porcientoEnRiesgo = totalPacientes==0 ? 0 : (int)((float)numeroEnRiesgo/totalPacientes*100.00);
		numeroEnRiesgoLbl.setText(""+numeroEnRiesgo);
		barraProgresoEnRiesgo.setValue(porcientoEnRiesgo);
		porcientoEnRiesgoLbl.setText(porcientoEnRiesgo+"%");
		
		int numeroVisitas;
		try {
			numeroVisitas = cmf.getHojaDeCargosDia(Calendar.getInstance()).getListadoHojaCargos().size();
		}catch(Exception e) {
			numeroVisitas = 0;
		}
		
		numeroVisitasHoyLbl.setText(numeroVisitas+"");
	}
	private static void ordenarTabla(TableRowSorter<?> t) {
		t.toggleSortOrder(0);
		if(t.getSortKeys().get(0).getSortOrder() != SortOrder.ASCENDING)
			t.toggleSortOrder(0);
	}
	private static void ordenarTablas() {
		ordenarTabla(ordenamientoPacientes);
		ordenarTabla(ordenamientoEmbarazadas);
		ordenarTabla(ordenamientoHojaCargosDia);
		ordenarTabla(ordenamientoAnalisisEnfermera);
		ordenarTabla(ordenamientoVacunados);
		ordenarTabla(ordenamientoEnRiesgo);
		ordenarTabla(ordenamientoEnfermos);
		ordenarTabla(ordenamientoSinVisitas);
		ordenarTabla(ordenamientoAnalisisFaltantesReporte);
		ordenarTabla(ordenamientoHojaCargo);
		
	}
	private static void actualizarSistemaDatos() {
		listadoPacientesPrincipalActual = cmf.getListadoPacientes();
		actualizarTarjetasInicio();
		actualizarTablaPacientes();
		actualizarTablaEmbarazadas();
		actualizarTablaEnRiesgo();
		restaurarComponentesBusquedaPacientes();
		restaurarComponentesVacunas();
		actualizarTablaVacunados(null);
		restaurarComponentesEnfermedad();
		actualizarTablaEnfermos(null);
		restaurarComponentesSinVisitas();
		actualizarTablaSinVisitas(null);
		actualizarTablaHojaCargosDia();
		restaurarHojaCargos();
		actualizarTablaHojaDeCargos(null);
		restaurarComponentesAnalisisFaltantesReporte();
		actualizarTablaAnalisisFaltantesReporte(null);
		actualizarTablaAnalisisFaltantesEnfermera();
		ordenarTablas();
	}
	public static void actualizarDatos() {
		if(instancia!=null)
			actualizarSistemaDatos();
	}
	private void buscadorPacientes() {
		String nombre = nombreTxt.getText();
		String pApellido = primerApellidoTxt.getText();
		String sApellido = segundoApellidoTxt.getText();
		String ci = carnetIdentidadTxt.getText();
		String calle = callePrincipalComboBox.getSelectedIndex()>0 ? (String)callePrincipalComboBox.getSelectedItem() : null;
		String numCasa = numeroCasaComboBox.getSelectedIndex()>0 ? (String)numeroCasaComboBox.getSelectedItem() : null;
		String hc = hcTxt.getText();
		
		listadoPacientesPrincipalActual = BuscadorPacientes.buscadorPacientes(cmf.getListadoPacientes(), nombre, pApellido, sApellido, ci, calle, numCasa, hc);
		actualizarTablaPacientes();
	}
	private void a() {
		panelPrincipalTab.setSelectedIndex(11);
	}
}
