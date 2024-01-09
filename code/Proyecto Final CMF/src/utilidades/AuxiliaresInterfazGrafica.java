package utilidades;

import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import clases_auxiliares.TipoCuentaUsuario;
import clases_auxiliares.Usuario;
import definiciones.Definiciones;
import definiciones.DefinicionesInterfazGrafica;
import definiciones.ErroresInterfazGrafica;

public final class AuxiliaresInterfazGrafica {
	
	private AuxiliaresInterfazGrafica() {}
	
	public static Icon ajustarImagen(Dimension dimCmp, URL urlImg) {
		ImageIcon imagen = new ImageIcon(urlImg);
		
		return new ImageIcon(imagen.getImage().getScaledInstance(dimCmp.width, dimCmp.height, Image.SCALE_SMOOTH));
	}
	public static TipoCuentaUsuario seguridad(String usuario,String contrasenya) {
		TipoCuentaUsuario cuenta = null;
		
		boolean usuarioNoVacio = Validaciones.validarStringNoVacio(usuario) && !usuario.equals(DefinicionesInterfazGrafica.CAMPO_USUARIO_TEXTO_BASE);
		boolean contrasenyaNoVacia = Validaciones.validarStringNoVacio(contrasenya) && !contrasenya.equals(DefinicionesInterfazGrafica.CAMPO_CONTRASENYA_TEXTO_BASE);
		
		if(!usuarioNoVacio) {
			if(!contrasenyaNoVacia)
				throw new IllegalArgumentException(ErroresInterfazGrafica.ERROR_CAMPO_VACIO);
			else
				throw new IllegalArgumentException(ErroresInterfazGrafica.ERROR_CAMPO_VACIO_USUARIO);
		}
		else if(!contrasenyaNoVacia)
			throw new IllegalArgumentException(ErroresInterfazGrafica.ERROR_CAMPO_VACIO_CONTRASENYA);
			
		Usuario u = null;
		if(Definiciones.MEDICO.getNombreUsuario().equals(usuario))
			u = Definiciones.MEDICO;
		else if(Definiciones.ENFERMERA.getNombreUsuario().equals(usuario))
			u = Definiciones.ENFERMERA;
		
		if(u==null)
			throw new IllegalArgumentException(ErroresInterfazGrafica.ERROR_USUARIO_NO_VALIDO);
		
		if(u.getContrasenya().equals(contrasenya))
			cuenta = u.getTipoCuenta();
		else
			throw new IllegalArgumentException(ErroresInterfazGrafica.ERROR_CONTRASENYA_NO_VALIDA);

		return cuenta;
	}

}
