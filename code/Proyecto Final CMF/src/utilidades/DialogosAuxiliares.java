package utilidades;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import clases_auxiliares.VacunacionPiscinaDatos;
import modelos.AnalisisIndicadosComboBoxModel;
import modelos.EnfermedadesCronicasComboBoxModel;
import modelos.EspecialidadesRemitidasComboBoxModel;
import modelos.VacunaComboBoxModel;
import nucleo.CMF;
import nucleo.Vacuna;
import nucleo.Visita;

public final class DialogosAuxiliares {
	private DialogosAuxiliares() {}
	
	public static String agregarEnfermedad(List<String> enfermedadesSeleccionadas) {
		String enfermedadSeleccionada = null;
		
		ArrayList<String> enfermedades = Auxiliares.listadoStringsNoComunes(PiscinaDatos.getListadoEnfermedadesCronicas(), enfermedadesSeleccionadas);
		
		if(enfermedades.isEmpty())
			JOptionPane.showMessageDialog(null, "No se pueden seleccionar más enfermedades crónicas", "ERROR", JOptionPane.ERROR_MESSAGE);
		else {
			JComboBox<String> c = new JComboBox<String>();
			c.setModel(new EnfermedadesCronicasComboBoxModel(enfermedades));
			c.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
			c.setBackground(Color.WHITE);
			
			int resultado = JOptionPane.showConfirmDialog(null, c, "Seleccionar Enfermedad Crónica", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(42,42),DialogosAuxiliares.class.getResource("/iconos/enfermedadCronica.png")));
			
			if(resultado==JOptionPane.OK_OPTION) {
				if(c.getSelectedIndex()>0)
					enfermedadSeleccionada = (String)c.getSelectedItem();
				else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una enfermedad crónica válida", "ERROR", JOptionPane.ERROR_MESSAGE);
					enfermedadSeleccionada = agregarEnfermedad(enfermedadesSeleccionadas);
				}
			}
		}
		
		
		return enfermedadSeleccionada;
	}
	
	public static Vacuna agregarVacunacion(List<String> vacunasSeleccionadas,Date fechaNacimiento) {
		Vacuna vacunaCreada = null;
		
		ArrayList<String> vacunas = Auxiliares.listadoStringsNoComunes(PiscinaDatos.listadoVacunasPosibles(fechaNacimiento), vacunasSeleccionadas);
		if(vacunas.isEmpty())
			JOptionPane.showMessageDialog(null, "No se pueden seleccionar más vacunas", "ERROR", JOptionPane.ERROR_MESSAGE);
		else {
			JComboBox<String> c = new JComboBox<String>();
			c.setModel(new VacunaComboBoxModel(vacunas));
			c.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
			c.setBackground(Color.WHITE);
			
			int resultado = JOptionPane.showConfirmDialog(null, c, "Seleccionar Vacuna", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(42,42),DialogosAuxiliares.class.getResource("/iconos/vacunacion.png")));
			
			if(resultado==JOptionPane.OK_OPTION) {
				
				if(c.getSelectedIndex()>0) {
					vacunaCreada = determinarFechaVacuna(fechaNacimiento,(String)c.getSelectedItem());
				}
				else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una vacuna válida", "ERROR", JOptionPane.ERROR_MESSAGE);
					vacunaCreada = agregarVacunacion(vacunasSeleccionadas,fechaNacimiento);
				}
				
			}
		}
		
		return vacunaCreada;
		
	}
	
	private static Vacuna determinarFechaVacuna(Date fechaNacimiento, String vacuna) {
		Vacuna vacunaCreada = null;
		
		JPanel p = new JPanel();
		
		JLabel l = new JLabel("Fecha: ");
		l.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		p.add(l);
		
		JDateChooser d = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		VacunacionPiscinaDatos vpd = PiscinaDatos.getDatosVacunacion(vacuna);
		Date fMin = Auxiliares.sumarAnyosFecha(fechaNacimiento, vpd.getEdadMin());
		Date fMax = Auxiliares.sumarAnyosFecha(fechaNacimiento, vpd.getEdadMax());
		d.setSelectableDateRange(fMin, fMax);
		d.setDate(fMin);
		p.add(d);
		
		int resultado = JOptionPane.showConfirmDialog(null, p, "Seleccionar Fecha de Vacunación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, AuxiliaresInterfazGrafica.ajustarImagen(new Dimension(42,42),DialogosAuxiliares.class.getResource("/iconos/vacunacion.png")));
		
		if(resultado==JOptionPane.OK_OPTION) {
			if(d.getDate()!=null && Validaciones.validarRangoFecha(d.getDate(), d.getMinSelectableDate(), d.getMaxSelectableDate())) {
				vacunaCreada = new Vacuna(d.getDate(),vacuna);
			}
			else {
				JOptionPane.showMessageDialog(null, "Debe seleccionar una fecha válida", "ERROR", JOptionPane.ERROR_MESSAGE);
				vacunaCreada = determinarFechaVacuna(fechaNacimiento,vacuna);
			}
		}
		return vacunaCreada;
	}
	
	public static String agregarAnalisisIndicado(List<String> analisisIndicadosSeleccionados, String idPaciente) {
		String analisis = null;
		
		ArrayList<Visita> visitas = CMF.getInstancia().getPaciente(idPaciente).getHistoriaClinica().visitasFechaDada(new Date());
		ArrayList<String> analisisHechosDia = new ArrayList<String>();
		for(Visita v : visitas)
			for(String a : v.getAnalisisIndicados())
				analisisHechosDia.add(a);
		
		ArrayList<String> analisisIndicados = Auxiliares.listadoStringsNoComunes(PiscinaDatos.getListadoTipoAnalisis(),analisisHechosDia);
		analisisIndicados = Auxiliares.listadoStringsNoComunes(analisisIndicados, analisisIndicadosSeleccionados);
		
		if(analisisIndicados.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por hoy no se pueden indicar más análisis al paciente seleccionado", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		else {
			JComboBox<String> c = new JComboBox<String>();
			c.setModel(new AnalisisIndicadosComboBoxModel(analisisIndicados));
			c.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
			c.setBackground(Color.WHITE);
			
			int resultado = JOptionPane.showConfirmDialog(null, c, "Seleccionar Análisis Indicado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(resultado==JOptionPane.OK_OPTION) {
				if(c.getSelectedIndex()>0)
					analisis = (String)c.getSelectedItem();
				else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un análisis indicado", "ERROR", JOptionPane.ERROR_MESSAGE);
					analisis = agregarAnalisisIndicado(analisisIndicadosSeleccionados,idPaciente);
				}
			}
		}
		
		return analisis;
	}
	
	public static String agregarEspecialidadRemitida(List<String> especialidadesSeleccionadas) {
		String especialidad = null;
		
		ArrayList<String> especialidades = Auxiliares.listadoStringsNoComunes(PiscinaDatos.getListadoEspecialidadesRemitidas(),especialidadesSeleccionadas);
		
		if(especialidades.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No se pueden seleccionar más especialidades", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		else {
			JComboBox<String> c = new JComboBox<String>();
			c.setModel(new EspecialidadesRemitidasComboBoxModel(especialidades));
			c.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
			c.setBackground(Color.WHITE);
			
			int resultado = JOptionPane.showConfirmDialog(null, c, "Seleccionar Especialidad Remitida", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(resultado==JOptionPane.OK_OPTION) {
				if(c.getSelectedIndex()>0)
					especialidad = (String)c.getSelectedItem();
				else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una especialidad", "ERROR", JOptionPane.ERROR_MESSAGE);
					especialidad = agregarEspecialidadRemitida(especialidadesSeleccionadas);
				}
			}
		}
		
		return especialidad;
	}
	
}
