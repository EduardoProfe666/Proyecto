package interfaz_grafica;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import componentes.PanelAnimacionCurvas;
import definiciones.DefinicionesInterfazGrafica;
import nucleo.CMF;
import utilidades.Auxiliares;

import javax.swing.border.LineBorder;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDeepOceanIJTheme;

import java.awt.Color;
import java.awt.Font;

import componentes.Imagen;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class PantallaCarga extends JFrame {

	private static final long serialVersionUID = 1L;
	private PanelAnimacionCurvas panelBase;
	private Imagen logo;
	private JProgressBar barraProgreso;
	private JLabel estado;


	public PantallaCarga() {
		FlatMaterialDeepOceanIJTheme.setup();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaCarga.class.getResource("/iconos/iconoApp.png")));
		this.setBounds(100, 100, DefinicionesInterfazGrafica.DIMENSION_PANTALLA_CARGA.width, DefinicionesInterfazGrafica.DIMENSION_PANTALLA_CARGA.height);
		this.setUndecorated(true);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent evt) {
				programaEstado();
			}
		});
		
		panelBase = new PanelAnimacionCurvas(DefinicionesInterfazGrafica.COLOR_GRADIENTE_A_PANTALLA_CARGA, DefinicionesInterfazGrafica.COLOR_GRADIENTE_B_PANTALLA_CARGA,DefinicionesInterfazGrafica.COLOR_CURVA_INICIO_PANTALLA_CARGA,DefinicionesInterfazGrafica.COLOR_CURVA_FINAL_PANTALLA_CARGA);
		panelBase.iniciarAnimacion();
		panelBase.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(panelBase);
		
		logo = new Imagen(new ImageIcon(PantallaCarga.class.getResource("/imagenes/logoMinsap.png")));
		logo.setBounds(149, 30, 223, 249);
		
	    barraProgreso = new JProgressBar();
	    barraProgreso.setBounds(149, 285, 223, 17);
		
		estado = new JLabel("Cargando ...");
		estado.setBounds(113, 313, 295, 36);
		estado.setHorizontalAlignment(SwingConstants.CENTER);
		estado.setForeground(Color.BLACK);
		estado.setFont(new Font("Roboto Medium", Font.PLAIN, 19));
		panelBase.setLayout(null);
		panelBase.add(logo);
		panelBase.add(barraProgreso);
		panelBase.add(estado);
		
		this.setLocationRelativeTo(null);
		
	}
	
	private void actualizarEstado(int n) {
		this.estado.setText(estadoImpresion(n));
		this.barraProgreso.setValue(n);
	}
	private void programaEstado() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for(int n=0;n<=100;n++) {
						Thread.sleep(Auxiliares.r.nextInt(11)+(long)20);
						if(Auxiliares.r.nextBoolean())
							Thread.sleep(DefinicionesInterfazGrafica.DEMORA_PROGRESSBAR_PANTALLA_CARGA);
						actualizarEstado(n);
					}
					Thread.sleep(450);
					terminarVentanaSplashScreen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	private String estadoImpresion(int n) {
		String r = "";
		
		if(n<=20) 
			r = "Cargando Núcleo ...";
		else if(n<=45) 
			r = "Conectando ...";
		else if(n<=68) 
			r = "Sincronizando con la Nube ...";
		else if(n<=90) 
			r = "Inicializando Interfaz ...";
		else 
			r = "Lanzando sistema";
			
		return r;
	}
	private void terminarVentanaSplashScreen() {
		try {
			CMF.getInstancia();
			panelBase.detenerAnimacion();
			this.dispose();
			Autenticacion login = new Autenticacion();
			login.setVisible(true);
		}catch(Exception e) {
			programaEstado();
		}
	}
}
