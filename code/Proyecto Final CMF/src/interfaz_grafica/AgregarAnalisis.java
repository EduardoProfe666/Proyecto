package interfaz_grafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import nucleo.Analisis;
import nucleo.Paciente;
import utilidades.Validaciones;
import componentes.BotonAnimacion;
import definiciones.Definiciones;
import definiciones.DefinicionesInterfazGrafica;
import javax.swing.border.MatteBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AgregarAnalisis extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panelBase = new JPanel();
	private int yMouseDrag;
	private int xMouseDrag;
	private JPanel panelSuperior;
	private BotonAnimacion botonSalir;
	private JLabel agregarAnalisisLbl;
	private JLabel pacienteLbl;
	private JLabel tipoAnalisisLbl;
	private JLabel fechaIndicacionLbl;
	private JLabel fechaIndicacionTxt;
	private JLabel resultadoLbl;
	private JScrollPane panelResultado;
	private JTextArea resultadoTexto;
	private BotonAnimacion aceptarBtn;
	private BotonAnimacion cancelarBtn;
	private Analisis analisis;
	private Paciente paciente;
	private JLabel pacienteTxt;
	private JLabel ciTxt;
	private JLabel hcTxt;
	private JLabel tipoAnalisisTxt;

	public AgregarAnalisis(JFrame parent, final Analisis a, final Paciente p) {
		super(parent,true);
		this.setUndecorated(true);
		analisis = a;
		paciente = p;
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
				if(JOptionPane.showConfirmDialog(rootPane, DefinicionesInterfazGrafica.PREGUNTA_SALIR_EDITAR, null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
					dispose();
			}
		});
		botonSalir.setText("X");
		botonSalir.setForeground(Color.BLACK);
		botonSalir.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		botonSalir.setEffectColor(new Color(178, 34, 34));
		botonSalir.setBackground(Color.LIGHT_GRAY);
		botonSalir.setBounds(556, 4, 32, 32);
		panelSuperior.add(botonSalir);
		
		agregarAnalisisLbl = new JLabel("Agregar Resultados de An\u00E1lisis");
		agregarAnalisisLbl.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		agregarAnalisisLbl.setBounds(10, 5, 329, 29);
		panelSuperior.add(agregarAnalisisLbl);
		
		pacienteLbl = new JLabel("Paciente:");
		pacienteLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		pacienteLbl.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		pacienteLbl.setBounds(13, 54, 71, 24);
		panelBase.add(pacienteLbl);
		
		pacienteTxt = new JLabel();
		pacienteTxt.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		pacienteTxt.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		pacienteTxt.setBounds(84, 54, 193, 24);
		panelBase.add(pacienteTxt);
		
		tipoAnalisisTxt = new JLabel();
		tipoAnalisisTxt.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		tipoAnalisisTxt.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		tipoAnalisisTxt.setBounds(449, 54, 141, 24);
		panelBase.add(tipoAnalisisTxt);
		
		tipoAnalisisLbl = new JLabel("Tipo de An\u00E1lisis:");
		tipoAnalisisLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		tipoAnalisisLbl.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		tipoAnalisisLbl.setBounds(326, 54, 129, 24);
		panelBase.add(tipoAnalisisLbl);
		
		fechaIndicacionLbl = new JLabel("Fecha de Indicaci\u00F3n:");
		fechaIndicacionLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		fechaIndicacionLbl.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		fechaIndicacionLbl.setBounds(326, 89, 156, 24);
		panelBase.add(fechaIndicacionLbl);
		
		fechaIndicacionTxt = new JLabel();
		fechaIndicacionTxt.setToolTipText("");
		fechaIndicacionTxt.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		fechaIndicacionTxt.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		fechaIndicacionTxt.setBounds(479, 89, 111, 24);
		panelBase.add(fechaIndicacionTxt);
		
		resultadoLbl = new JLabel("Resultados de An\u00E1lisis");
		resultadoLbl.setToolTipText("Debe introducir un texto con tama\u00F1o m\u00EDnimo de <"+Definiciones.TAM_MIN_STRING_RESULTADO_ANALISIS+">\r\ncaracteres y tama\u00F1o m\u00E1ximo "
				+ "de <"+Definiciones.TAM_MAX_STRING_RESULTADO_ANALISIS+"> caracteres.");
		resultadoLbl.setForeground(Color.BLACK);
		resultadoLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		resultadoLbl.setBorder(null);
		resultadoLbl.setBounds(13, 180, 264, 24);
		panelBase.add(resultadoLbl);
		
		panelResultado = new JScrollPane();
		panelResultado.setBorder(new LineBorder(Color.BLACK, 2));
		panelResultado.setBounds(13, 204, 577, 336);
		panelBase.add(panelResultado);
		
		resultadoTexto = new JTextArea();
		resultadoTexto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(resultadoTexto.getText().length()==Definiciones.TAM_MAX_STRING_RESULTADO_ANALISIS)
					e.consume();
			}
		});
		resultadoTexto.setToolTipText("Debe introducir un texto con tama\u00F1o m\u00EDnimo de <"+Definiciones.TAM_MIN_STRING_RESULTADO_ANALISIS+">\r\ncaracteres y tama\u00F1o m\u00E1ximo "
				+ "de <"+Definiciones.TAM_MAX_STRING_RESULTADO_ANALISIS+"> caracteres.");
		resultadoTexto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				resultadoLbl.setForeground(Color.BLACK);
			}
		});
		resultadoTexto.setWrapStyleWord(true);
		resultadoTexto.setLineWrap(true);
		resultadoTexto.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		resultadoTexto.setBorder(null);
		panelResultado.setViewportView(resultadoTexto);
		
		aceptarBtn = new BotonAnimacion();
		aceptarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!validarComponentes())
					JOptionPane.showMessageDialog(rootPane, "Compruebe los datos de los campos señalados","ERROR",JOptionPane.ERROR_MESSAGE);
				
				else if(JOptionPane.showConfirmDialog(rootPane, "¿Desea agregar el resultado de análisis?", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					analisis.setResultadoAnalisis(resultadoTexto.getText().trim(), new Date());
					JOptionPane.showMessageDialog(rootPane, "El resultado de análisis fue agregado correctamente", null, JOptionPane.INFORMATION_MESSAGE);
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
				if(JOptionPane.showConfirmDialog(rootPane, DefinicionesInterfazGrafica.PREGUNTA_SALIR_EDITAR, null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
					dispose();
			}
		});
		cancelarBtn.setText("Cancelar");
		cancelarBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		cancelarBtn.setEffectColor(new Color(178, 34, 34));
		cancelarBtn.setBackground(Color.RED);
		cancelarBtn.setBounds(483, 551, 107, 27);
		panelBase.add(cancelarBtn);
		
		JLabel ciLbl = new JLabel("CI:");
		ciLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		ciLbl.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		ciLbl.setBounds(13, 89, 26, 24);
		panelBase.add(ciLbl);
		
		ciTxt = new JLabel();
		ciTxt.setToolTipText("");
		ciTxt.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		ciTxt.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		ciTxt.setBounds(37, 89, 240, 24);
		panelBase.add(ciTxt);
		
		JLabel hcLbl = new JLabel("No. de HC:");
		hcLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		hcLbl.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		hcLbl.setBounds(13, 124, 82, 24);
		panelBase.add(hcLbl);
		
		hcTxt = new JLabel();
		hcTxt.setToolTipText("");
		hcTxt.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		hcTxt.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		hcTxt.setBounds(94, 124, 183, 24);
		panelBase.add(hcTxt);
		
		llenarDatos();
		this.setLocationRelativeTo(null);
	}
	
	private void llenarDatos() {
		pacienteTxt.setText(paciente.getNombreSimple());
		ciTxt.setText(paciente.getCarnetIdentidad());
		hcTxt.setText(paciente.getHistoriaClinica().getNumeroHistoriaClinica());
		tipoAnalisisTxt.setText(analisis.getTipoAnalisis());
		fechaIndicacionTxt.setText(DefinicionesInterfazGrafica.FORMATO_BASE_FECHA.format(analisis.getFechaIndicacion()));
	}
	
	private boolean validarComponentes() {
		boolean esValido = true;
		
		if(!Validaciones.validarTamString(resultadoTexto.getText().trim(), Definiciones.TAM_MIN_STRING_RESULTADO_ANALISIS, Definiciones.TAM_MAX_STRING_RESULTADO_ANALISIS)) {
			esValido=false;
			resultadoLbl.setForeground(Color.RED);
		}
		
		return esValido;
	}
	
}
