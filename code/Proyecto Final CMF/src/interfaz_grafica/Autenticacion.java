package interfaz_grafica;

import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import definiciones.ErroresInterfazGrafica;
import nucleo.CMF;
import clases_auxiliares.TipoCuentaUsuario;
import definiciones.Definiciones;
import definiciones.DefinicionesInterfazGrafica;
import utilidades.AuxiliaresInterfazGrafica;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import componentes.PanelBordeOval;
import componentes.Imagen;
import componentes.JPasswordFieldModificado;

import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import componentes.AvatarCircular;
import componentes.BotonAnimacion;
import componentes.JTextFieldModificado;

import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Autenticacion extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panelBase;
	private PanelBordeOval panelSuperior;
	private Imagen imagen;
	private JPanel panelLogin;
	private AvatarCircular avatar;
	private JLabel loginPanelSuperiorLbl;
	private JButton salirBtn;
	private JButton minimizarBtn;
	private JTextFieldModificado campoUsuario;
	private JPasswordFieldModificado campoContrasenya;
	private JLabel usuarioLabel;
	private JLabel contrasenyaLabel;
	private BotonAnimacion ingresarBtn;
	private JButton contrasenyaBtn;

	private int xMouseDrag;
	private int yMouseDrag;
	private char echoCharContrasenya;
	private boolean contrasenyaVisible;

	public Autenticacion() {
		FlatLightLaf.setup();
		this.setUndecorated(true);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(Autenticacion.class.getResource("/iconos/iconoApp.png")));
		this.setBounds(100, 100, DefinicionesInterfazGrafica.DIMENSION_AUTENTICACION.width, DefinicionesInterfazGrafica.DIMENSION_AUTENTICACION.height);
		this.setBackground(new Color(0,0,0,0));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				minimizarBtn.requestFocus();
			}
		});

		panelBase = new JPanel();
		panelBase.setOpaque(false);
		setContentPane(panelBase);

		panelSuperior = new PanelBordeOval(DefinicionesInterfazGrafica.VALOR_ESQUINA_OVAL,DefinicionesInterfazGrafica.VALOR_ESQUINA_OVAL,0,0);
		panelSuperior.setBounds(0, 0, 800, 45);
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
		panelSuperior.setBackground(new Color(DefinicionesInterfazGrafica.TURQUESA_CLARO.getRed(),DefinicionesInterfazGrafica.TURQUESA_CLARO.getGreen(),DefinicionesInterfazGrafica.TURQUESA_CLARO.getBlue(),150));

		loginPanelSuperiorLbl = new JLabel("Autenticación CMF");
		loginPanelSuperiorLbl.setBounds(21, 6, 408, 38);
		loginPanelSuperiorLbl.setFont(new Font("Roboto Black", Font.PLAIN, 21));

		salirBtn = new JButton("");
		salirBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(rootPane, DefinicionesInterfazGrafica.PREGUNTA_SALIR, null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
					System.exit(0);
				else
					salirBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), Autenticacion.class.getResource("/iconos/exit0.png")));
			}
		});
		salirBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				salirBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), Autenticacion.class.getResource("/iconos/exit1.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				salirBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), Autenticacion.class.getResource("/iconos/exit0.png")));
			}
		});
		salirBtn.setContentAreaFilled(false);
		salirBtn.setBounds(759, 4, 36, 36);
		salirBtn.setHorizontalAlignment(SwingConstants.CENTER);
		salirBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		salirBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		salirBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), Autenticacion.class.getResource("/iconos/exit0.png")));

		minimizarBtn = new JButton("");
		minimizarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(ICONIFIED);
			}
		});
		minimizarBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				minimizarBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), Autenticacion.class.getResource("/iconos/minimize1.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				minimizarBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), Autenticacion.class.getResource("/iconos/minimize0.png")));
			}
		});
		minimizarBtn.setContentAreaFilled(false);
		minimizarBtn.setBounds(722, 4, 36, 36);
		minimizarBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		minimizarBtn.setHorizontalAlignment(SwingConstants.CENTER);
		minimizarBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		minimizarBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), Autenticacion.class.getResource("/iconos/minimize0.png")));

		imagen = new Imagen(new ImageIcon(Autenticacion.class.getResource("/imagenes/imgLogin.jpg")));
		imagen.setBounds(0, 45, 507, 467);
		imagen.setBorder(new MatteBorder(0, 2, 2, 2, (Color) new Color(0, 0, 0)));

		panelLogin = new JPanel();
		panelLogin.setBackground(Color.WHITE);
		panelLogin.setBounds(507, 45, 293, 467);
		panelLogin.setBorder(new MatteBorder(0, 0, 2, 2, (Color) new Color(0, 0, 0)));

		avatar = new AvatarCircular(new ImageIcon(Autenticacion.class.getResource("/imagenes/avatarDoctorLogin.png")), DefinicionesInterfazGrafica.TAM_BORDE_AVATAR);
		avatar.setBounds(70, 45, 150, 150);
		avatar.setForeground(DefinicionesInterfazGrafica.TURQUESA_CLARO);

		usuarioLabel = new JLabel("Usuario");
		usuarioLabel.setBounds(44, 217, 203, 32);
		usuarioLabel.setForeground(Color.BLACK);
		usuarioLabel.setFont(new Font("Roboto Black", Font.PLAIN, 20));

		campoUsuario = new JTextFieldModificado();
		campoUsuario.setLimite(DefinicionesInterfazGrafica.CANT_MAX_CARACTERES_LOGIN);
		campoUsuario.setBounds(44, 249, 203, 32);
		campoUsuario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(campoUsuario.getText().equals(DefinicionesInterfazGrafica.CAMPO_USUARIO_TEXTO_BASE)) {
					campoUsuario.setText("");
					campoUsuario.setForeground(Color.BLACK);
					campoUsuario.setBorder(new MatteBorder(0, 0, 2, 0, DefinicionesInterfazGrafica.TURQUESA_CLARO));
				}
				else
					campoUsuario.setBorder(new MatteBorder(0, 0, 2, 0, DefinicionesInterfazGrafica.TURQUESA_CLARO));
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(campoUsuario.getText().isEmpty()) {
					campoUsuario.setText(DefinicionesInterfazGrafica.CAMPO_USUARIO_TEXTO_BASE);
					campoUsuario.setForeground(Color.GRAY);
					campoUsuario.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
				}
			}
		});
		campoUsuario.setText(DefinicionesInterfazGrafica.CAMPO_USUARIO_TEXTO_BASE);
		campoUsuario.setOpaque(false);
		campoUsuario.setForeground(Color.GRAY);
		campoUsuario.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		campoUsuario.setColumns(10);
		campoUsuario.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 0, 0)));

		contrasenyaLabel = new JLabel("Contrase\u00F1a");
		contrasenyaLabel.setBounds(44, 292, 203, 32);
		contrasenyaLabel.setForeground(Color.BLACK);
		contrasenyaLabel.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		
		campoContrasenya = new JPasswordFieldModificado();
		campoContrasenya.setLimite(DefinicionesInterfazGrafica.CANT_MAX_CARACTERES_LOGIN);
		campoContrasenya.setBounds(44, 324, 171, 32);
		campoContrasenya.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(campoContrasenya.getPassword().length==DefinicionesInterfazGrafica.CANT_MAX_CARACTERES_LOGIN)
					e.consume();
			}
		});
		campoContrasenya.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(String.valueOf(campoContrasenya.getPassword()).equals(DefinicionesInterfazGrafica.CAMPO_CONTRASENYA_TEXTO_BASE)) {
					campoContrasenya.setText("");
					campoContrasenya.setForeground(Color.BLACK);
					campoContrasenya.setBorder(new MatteBorder(0, 0, 2, 0, DefinicionesInterfazGrafica.VERDE_AZULADO_1));
					contrasenyaBtn.setBorder(new MatteBorder(0, 0, 2, 0, DefinicionesInterfazGrafica.VERDE_AZULADO_1));
					if(contrasenyaVisible)
						campoContrasenya.setEchoChar((char)0);
					else
						campoContrasenya.setEchoChar(echoCharContrasenya);
				}
				else {
					campoContrasenya.setBorder(new MatteBorder(0, 0, 2, 0, DefinicionesInterfazGrafica.VERDE_AZULADO_1));
					contrasenyaBtn.setBorder(new MatteBorder(0, 0, 2, 0, DefinicionesInterfazGrafica.VERDE_AZULADO_1));
				}

			}
			@Override
			public void focusLost(FocusEvent e) {
				if(String.valueOf(campoContrasenya.getPassword()).isEmpty()) {
					campoContrasenya.setEchoChar(echoCharContrasenya);
					campoContrasenya.setText(DefinicionesInterfazGrafica.CAMPO_CONTRASENYA_TEXTO_BASE);
					campoContrasenya.setForeground(Color.GRAY);
					campoContrasenya.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
					contrasenyaBtn.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
				}
			}
		});
		campoContrasenya.setText(DefinicionesInterfazGrafica.CAMPO_CONTRASENYA_TEXTO_BASE);
		campoContrasenya.setOpaque(false);
		campoContrasenya.setForeground(Color.GRAY);
		campoContrasenya.setFont(new Font("Tahoma", Font.BOLD, 14));
		campoContrasenya.setColumns(10);
		campoContrasenya.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 0, 0)));

		contrasenyaBtn = new JButton("");
		contrasenyaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contrasenyaVisible = !contrasenyaVisible;

				if(!String.valueOf(campoContrasenya.getPassword()).equals(DefinicionesInterfazGrafica.CAMPO_CONTRASENYA_TEXTO_BASE)) {
					if(contrasenyaVisible)
						campoContrasenya.setEchoChar((char)0);
					else
						campoContrasenya.setEchoChar(echoCharContrasenya);
				}

				if(contrasenyaVisible) 
					contrasenyaBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), Autenticacion.class.getResource("/iconos/hideP1.png")));
				else 
					contrasenyaBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), Autenticacion.class.getResource("/iconos/showP1.png")));
			}
		});
		echoCharContrasenya = campoContrasenya.getEchoChar();
		contrasenyaVisible = false;
		contrasenyaBtn.setContentAreaFilled(false);
		contrasenyaBtn.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 0, 0)));
		contrasenyaBtn.setBounds(215, 324, 32, 32);
		contrasenyaBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(contrasenyaVisible)
					contrasenyaBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), Autenticacion.class.getResource("/iconos/hideP1.png")));
				else
					contrasenyaBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), Autenticacion.class.getResource("/iconos/showP1.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(contrasenyaVisible)
					contrasenyaBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), Autenticacion.class.getResource("/iconos/hideP0.png")));
				else
					contrasenyaBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), Autenticacion.class.getResource("/iconos/showP0.png")));
			}

		});
		contrasenyaBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contrasenyaBtn.setIcon(AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(32,32), Autenticacion.class.getResource("/iconos/showP0.png")));

		ingresarBtn = new BotonAnimacion();
		ingresarBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean autenticado = true;
				TipoCuentaUsuario usuario = null;
				try {
					usuario = AuxiliaresInterfazGrafica.seguridad(campoUsuario.getText(),String.valueOf(campoContrasenya.getPassword()));
				}
				catch(Exception ex) {
					autenticado = false;
					String mensaje = ex.getMessage(); 
					if(mensaje.equals(ErroresInterfazGrafica.ERROR_CAMPO_VACIO)) {
						campoUsuario.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
						campoContrasenya.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
						contrasenyaBtn.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
					}
					else if(mensaje.equals(ErroresInterfazGrafica.ERROR_USUARIO_NO_VALIDO) || mensaje.equals(ErroresInterfazGrafica.ERROR_CAMPO_VACIO_USUARIO))
						campoUsuario.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
					else if(mensaje.equals(ErroresInterfazGrafica.ERROR_CONTRASENYA_NO_VALIDA) || mensaje.equals(ErroresInterfazGrafica.ERROR_CAMPO_VACIO_CONTRASENYA)) {
						campoContrasenya.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
						contrasenyaBtn.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
					}

					JOptionPane.showMessageDialog(rootPane, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				finally{
					if(autenticado) {
						String nombre = usuario==TipoCuentaUsuario.MEDICO ? CMF.getInstancia().getDoctor().getNombreSimple() : CMF.getInstancia().getEnfermera().getNombreSimple();
						JOptionPane.showMessageDialog(rootPane, "Bienvenido/a de nuevo "+nombre, null, JOptionPane.INFORMATION_MESSAGE);
						terminarVentanaLogin(usuario);
					}
				}
			}
		});
		ingresarBtn.setBounds(44, 388, 203, 32);
		ingresarBtn.setEffectColor(DefinicionesInterfazGrafica.AQUAMARINA);
		ingresarBtn.setText("Ingresar");
		ingresarBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ingresarBtn.setBackground(DefinicionesInterfazGrafica.VERDE_AZULADO_2);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ingresarBtn.setBackground(DefinicionesInterfazGrafica.VERDE_AZULADO_1);
			}
			
		});
		ingresarBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ingresarBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		ingresarBtn.setBorder(null);
		ingresarBtn.setBackground(DefinicionesInterfazGrafica.VERDE_AZULADO_1);
		panelBase.setLayout(null);
		panelBase.add(panelSuperior);
		panelSuperior.setLayout(null);
		panelSuperior.add(loginPanelSuperiorLbl);
		panelSuperior.add(minimizarBtn);
		panelSuperior.add(salirBtn);
		panelBase.add(imagen);
		panelBase.add(panelLogin);
		panelLogin.setLayout(null);
		panelLogin.add(avatar);
		panelLogin.add(contrasenyaLabel);
		panelLogin.add(campoUsuario);
		panelLogin.add(usuarioLabel);
		panelLogin.add(ingresarBtn);
		panelLogin.add(campoContrasenya);
		panelLogin.add(contrasenyaBtn);

		this.setLocationRelativeTo(null);
		this.setVisible(true);
		JOptionPane.showMessageDialog(rootPane, "Para probar el programa use las siguientes cuentas:\r\n\n"
				  + "Cuenta Médico: \r\n      Usuario: " +Definiciones.MEDICO.getNombreUsuario()+"\r\n      Contraseña: "+Definiciones.MEDICO.getContrasenya()+"\r\n\n"
				  + "Cuenta Enfermera: \r\n      Usuario: " +Definiciones.ENFERMERA.getNombreUsuario()+"\r\n      Contraseña: "+Definiciones.ENFERMERA.getContrasenya(),
				  null, JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void terminarVentanaLogin(TipoCuentaUsuario usuario) {
		AppPrincipal app = AppPrincipal.getInstancia(usuario); 
		app.setVisible(true);
		this.dispose();
	}
}
