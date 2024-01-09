package nucleo;

import definiciones.Definiciones;
import definiciones.Errores;
import utilidades.Validaciones;

public class VisitaHojaDeCargos {
	private String diagnostico;
	private Paciente paciente;

	public VisitaHojaDeCargos(String diagnostico, Paciente paciente) {
		setDiagnostico(diagnostico);
		setPaciente(paciente);
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	private void setDiagnostico(String diagnostico) {
		if(Validaciones.validarStringNoVacio(diagnostico) && Validaciones.validarStringNoTodoEspacio(diagnostico) && Validaciones.validarTamString(diagnostico, Definiciones.TAM_MIN_STRING_DIAGNOSTICO, Definiciones.TAM_MAX_STRING_DIAGNOSTICO))
			this.diagnostico = diagnostico;
		else 
			throw new IllegalArgumentException(Errores.ERROR_DIAGNOSTICO);
	}

	public Paciente getPaciente() {
		return paciente;
	}

	private void setPaciente(Paciente paciente) {   
		if(paciente!=null)
			this.paciente = paciente;
		else
			throw new IllegalArgumentException(Errores.ERROR_PACIENTE_VISITA_HOJA_CARGOS);
	}

}
