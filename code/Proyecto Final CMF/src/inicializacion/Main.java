package inicializacion;

import java.awt.EventQueue;
import java.util.Locale;

import javax.swing.JOptionPane;

import interfaz_grafica.PantallaCarga;
import utilidades.ValidacionesDefiniciones;

/**
 * 
 * @author Eva Vazquez
 * @author Eduardo Gonzalez
 *
 */
public class Main {
	public static void main(String[] args) {
		Locale.setDefault(new Locale("es"));
		try {
			ValidacionesDefiniciones.validarDefiniciones();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					PantallaCarga s = new PantallaCarga();
					s.setVisible(true);
					inicializarHilo();
				}
			});
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error Fatal", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	private static void inicializarHilo() { //Permite inicializar el sistema mientras se carga.
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Inicializadora.inicializarCMF();
				}catch(Exception e) {
					e.printStackTrace();
				}

			}}).start();
	}
}

