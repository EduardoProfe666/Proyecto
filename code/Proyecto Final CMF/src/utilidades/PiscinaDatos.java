package utilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import definiciones.Definiciones;
import definiciones.Errores;
import nucleo.Direccion;
import nucleo.Vacuna;
import clases_auxiliares.VacunacionPiscinaDatos;
import clases_auxiliares.DireccionPiscinaDatos;

public final class PiscinaDatos {
	
	
	private static PiscinaDatos instancia;
	private static final Random rnd = new Random();
	private static final ArrayList<String> piscinaNombresMasculinos = new ArrayList<String>(Arrays.asList("Eduardo","Alberto","Juan","Felipe","Marco",
			"Hugo","Lucas","Leo","Alejandro","Milos",
			"Pedro","Gerard","Cristian","David","Mario",
			"Bruno","Oliverio","Thiago","Sergio","Iván",
			"Eric","Guillermo","Joel","Ismael","José","Adrián"
			));
	private static final ArrayList<String> piscinaNombresFemeninos = new ArrayList<String>(Arrays.asList("Julia","Eva","Zoe","Ariadna","Yami",
			"Vilma","Celia","Sonia","Clara","India","Victoria",
			"Marina","Carlota","Luna","Elsa","Isabel",
			"Paula","Karla","María","Alma","Elena",
			"Manuela","Adriana","Alejandra","Alicia","Guadalupe","Jacqueline"
			));
	private static final ArrayList<String> piscinaApellidos = new ArrayList<String>(Arrays.asList("Castro","Martell","Morales","Rivera","Reyes",
			"Romero","Escobar","Cruz","Rivas","Orellana",
			"Aguilar","Alvarado","Zambrano","Torres","Zabala",
			"Vera","Flores","Espinosa","Jaramillo","Mendoza",
			"Ayala","Morillo","Acosta","Rojas","Ortiz",
			"Galeano","Ferreira","Cabrera","Sosa","Franco",
			"Navarro","Ramos","Serrano","Molina","Gil",
			"Blanco","Ortega","Delgado","Rubio","Medina",
			"Iglesias","Castillo","Santos","Guerrero","Lozano",
			"Cano","Herrera","Gallego","Calvo","Vega","San Pedro"
			));
	private static final ArrayList<String> piscinaTipoAnalisis = new ArrayList<String>(Arrays.asList("Hemograma","Orina","Heces Fecales","Serología","VIH","Rayos-X"
																									));
	private static final ArrayList<String> piscinaEspecialidadesRemitidas = new ArrayList<String>(Arrays.asList("Cardiología","Dermatología","Gastroentereología",
																												"Prótesis","Oftalmología","Toxicología"));
	private static final ArrayList<String> piscinaEnfermedadesCronicas = new ArrayList<String>(Arrays.asList("Artritis","Asma","Cáncer","Diabetes",
																											 "VIH/SIDA","Epilepsia","Esclerosis Múltiple"));
	private static ArrayList<VacunacionPiscinaDatos> piscinaVacunas = inicializarPiscinaVacunas();
	private static ArrayList<DireccionPiscinaDatos> piscinaDirecciones = inicializarPiscinaDirecciones();

	private final ArrayList<String> listadoCIGenerados;
	private String ciMedico;
	private String ciEnfermera;
	private int contadorCI;

	private PiscinaDatos() {
		listadoCIGenerados = new ArrayList<String>();
		generarListadoCi(generarEntero(Definiciones.CANT_MIN_PACIENTES, Definiciones.CANT_MAX_PACIENTES));
		contadorCI = 0;
		ciMedico = generarCiMedicoAleatorio();
		ciEnfermera = generarCiEnfermeraAleatorio();
	}

	public static PiscinaDatos getInstancia() {
		if(instancia==null)
			instancia = new PiscinaDatos();
		return instancia;
	}

	public static char generarSexo() {
		char sexo;
		if(rnd.nextBoolean())
			sexo = 'F';
		else
			sexo = 'M';

		return sexo;
	}

	private static boolean nombreCompuesto() {
		return rnd.nextBoolean();
	}
	private static String nombreMasculinoAleatorio() {
		rnd.setSeed(rnd.nextLong());
		rnd.setSeed(rnd.nextLong());
		return piscinaNombresMasculinos.get(rnd.nextInt(piscinaNombresMasculinos.size()));
	}
	private static String nombreFemeninoAleatorio() {
		rnd.setSeed(rnd.nextLong());
		rnd.setSeed(rnd.nextLong());
		return piscinaNombresFemeninos.get(rnd.nextInt(piscinaNombresFemeninos.size()));
	}

	public static String generarNombreMasculino() {
		String nombreM = nombreMasculinoAleatorio();
		if(nombreCompuesto())
			nombreM = nombreM + " " + nombreMasculinoAleatorio();
		return nombreM;
	}
	public static String generarNombreFemenino() {
		String nombreF = nombreFemeninoAleatorio();
		if(nombreCompuesto())
			nombreF = nombreF + " " + nombreFemeninoAleatorio();
		return nombreF;
	}
	public static String generarNombre(char sexo) {
		String nombre;
		if(sexo=='F')
			nombre = generarNombreFemenino();
		else
			nombre = generarNombreMasculino();
		
		return nombre;
	}
	public static String generarNombreSimple(char sexo) {
		String nombre;
		if(sexo=='F')
			nombre = nombreFemeninoAleatorio();
		else
			nombre = nombreMasculinoAleatorio();
		
		return nombre;
	}
	public static String generarApellido() {
		return piscinaApellidos.get(rnd.nextInt(piscinaApellidos.size()));
	}

	private static Calendar generarFechaCalendar(Date fechaMinima, Date fechaMaxima) {
		long max = fechaMaxima.getTime(); 
		long min = fechaMinima.getTime();
		long fechaLong = min + (long)(Math.random() * (max-min));

		Calendar c = new GregorianCalendar();
		c.setTimeInMillis(fechaLong);
		return c;
	}
	private static Date generarFechaDate(Date fechaMinima, Date fechaMaxima) {
		return generarFechaCalendar(fechaMinima,fechaMaxima).getTime();
	}

	private static int generarEntero(int min, int max) {
		return rnd.nextInt(max-min+1)+min;
	}
	private int generarDigitoSexo(char sexo) {
		int digito=rnd.nextInt(10);
		if(sexo == 'F' || sexo == 'f') {
			if(digito%2==0) {
				digito++;
			}

		}
		else if(digito%2==1){
			digito--;
		}
		return digito;
	}
	private int generarDigitoSiglo(int sigloDisminuido) {
		int digito=9;

		if(sigloDisminuido==19)
			digito = rnd.nextInt(6);
		else if(sigloDisminuido==20)
			digito = rnd.nextInt(3)+6;

		return digito;
	}
	private String generarCiAleatorio(char sexo, boolean trabajadorMedico) {
		if(sexo!='F' && sexo!='f' && sexo!='M' && sexo!='m')
			throw new IllegalArgumentException("El sexo debe ser F(f) o M(m)");

		Calendar fechaNacimiento;
		if(trabajadorMedico) 
			fechaNacimiento = generarFechaCalendar(Definiciones.FECHA_NACIMIENTO_MIN_TRABAJADOR_MEDICO.getTime(), Definiciones.FECHA_NACIMIENTO_MAX_TRABAJADOR_MEDICO.getTime());
		else
			fechaNacimiento = generarFechaCalendar(Definiciones.FECHA_MIN_PISCINA_DATOS_CALENDAR.getTime(), Definiciones.FECHA_MAX_PISCINA_DATOS_CALENDAR.getTime());

		int anyo = fechaNacimiento.get(Calendar.YEAR);
		int mes = fechaNacimiento.get(Calendar.MONTH)+1;
		int dia = fechaNacimiento.get(Calendar.DAY_OF_MONTH);
		int sigloDisminuido = anyo/100;
		int anyoCi = anyo-(sigloDisminuido*100);

		String ci = ""+convertirEnteroDobleString(anyoCi)+convertirEnteroDobleString(mes)+convertirEnteroDobleString(dia)+generarDigitoSiglo(sigloDisminuido)+convertirEnteroDobleString(generarEntero(0,99))+generarDigitoSexo(sexo)+generarEntero(0,9);
		if(comprobarCiExistente(ci))
			ci = generarCiAleatorio(sexo,trabajadorMedico);

		return ci;
	}

	private boolean comprobarCiExistente(String ci) {
		boolean esValido = ci!=null;
		
		if(esValido)
			esValido = Collections.binarySearch(listadoCIGenerados, ci)>=0;
		return esValido;
	}

	public static boolean generarTieneLicenciaturaEnfermera() {
		return rnd.nextBoolean();
	}

	public static Date generarComienzoLaboral(Date fechaNacimiento) {
		Calendar fechaMin = new GregorianCalendar();
		fechaMin.setTime(fechaNacimiento);
		fechaMin.set(Calendar.YEAR, fechaMin.get(Calendar.YEAR)+Definiciones.EDAD_MIN_TRABAJADOR_MEDICO);
		return generarFechaDate(fechaMin.getTime(),new Date());
	}

	public static Date generarFechaInscripcionCMF(Date comienzoLaboral) {
		return generarFechaDate(comienzoLaboral,new Date());
	}

	public static String generarNumeroRegistroMedico() {
		return "M"+generarEntero(Definiciones.NUMERO_REGISTRO_MEDICO_MIN, Definiciones.NUMERO_REGISTRO_MEDICO_MAX);
	}

	private void generarListadoCi(int cantidadCi) {
		for(int i=0;i<cantidadCi;i++) 
			Auxiliares.introducirStringListado(generarCiAleatorio(generarSexo(), false), listadoCIGenerados);
	}

	public int cantidadCiGenerados() {
		return listadoCIGenerados.size();
	}
	public String generarCi() {
		if(listadoCIGenerados.size()>contadorCI)
			return listadoCIGenerados.get(contadorCI++);
		else
			throw new ArrayIndexOutOfBoundsException(Errores.ERROR_MAX_CI_GENERADOS);
	}
	public String generarCiMedico() {
		return this.ciMedico;
	}
	public String generarCiEnfermera() {
		return this.ciEnfermera;
	}
	private static String convertirEnteroDobleString(int entero) {
		String s = String.valueOf(entero);
		if(entero>=0 && entero<10) {
			s = "0"+s;
		}
		return s;
	}

	private String generarCiMedicoAleatorio() {
		String ciMedico = generarCiAleatorio(generarSexo(), true);
		if(ciEnfermera!=null && ciMedico.equals(ciEnfermera))
			ciMedico = generarCiMedicoAleatorio();
		return ciMedico;
	}
	private String generarCiEnfermeraAleatorio() {
		String ciEnfermera = generarCiAleatorio('F',true);
		if(ciMedico!=null && ciMedico.equals(ciEnfermera))
			ciMedico = generarCiEnfermeraAleatorio();
		return ciEnfermera;
	}
	public static String generarNombreApellido() { //Para la generación del nombre del policlínico, del director del policlínico y las calles
		return generarNombreSimple(generarSexo()) + " " + generarApellido();
	}

	public static boolean generarEstaEmbarazada(Date fechaNacimiento) {
		boolean estaEmbarazada;
		if(validarEdadPruebasMujer(fechaNacimiento))
			estaEmbarazada=rnd.nextBoolean();
		else
			estaEmbarazada=false;
		return estaEmbarazada;
	}

	public static Date generarFechaUltimaPruebaCitologica(Date fechaNacimiento) {
		Date fecha = null;
		if(validarEdadPruebasMujer(fechaNacimiento))
			fecha = generarFechaDate(Auxiliares.sumarAnyosFecha(fechaNacimiento, Definiciones.EDAD_MIN_PRUEBAS_EMBARAZO_MUJER), new Date());

		return fecha;
	}

	private static boolean validarEdadPruebasMujer(Date fechaNacimiento) {
		int edad = Auxiliares.determinarEdad(fechaNacimiento);

		return (edad>=Definiciones.EDAD_MIN_PRUEBAS_EMBARAZO_MUJER && edad<=Definiciones.EDAD_MAX_PRUEBAS_EMBARAZO_MUJER);
	}

	public static ArrayList<VacunacionPiscinaDatos> getPiscinaVacunas(){
		return new ArrayList<VacunacionPiscinaDatos>(piscinaVacunas);
	}

	public static ArrayList<Vacuna> generarVacunaciones(Date fechaNacimiento) {
		ArrayList<Vacuna> listadoVacunaciones = new ArrayList<Vacuna>();
		int edad = Auxiliares.determinarEdad(fechaNacimiento);

		for(VacunacionPiscinaDatos v : piscinaVacunas) {
			if(edad>=v.getEdadMin())
				listadoVacunaciones.add(new Vacuna(Auxiliares.sumarAnyosFecha(fechaNacimiento, generarEntero(v.getEdadMin(), v.getEdadMax())),v.getNombreVacuna()));
		}

		return listadoVacunaciones;
	}
	
	public static ArrayList<String> listadoVacunasPosibles(Date fechaNacimiento){
		ArrayList<String> listado = new ArrayList<String>();
		int edad = Auxiliares.determinarEdad(fechaNacimiento);
		
		for(VacunacionPiscinaDatos v : piscinaVacunas) {
			if(edad>=v.getEdadMin())
				listado.add(v.getNombreVacuna());
		}
		
	    return listado;
	}

	private static ArrayList<VacunacionPiscinaDatos> inicializarPiscinaVacunas(){ 
		ArrayList<VacunacionPiscinaDatos> piscinaVacunas = new ArrayList<VacunacionPiscinaDatos>();
		piscinaVacunas.add(new VacunacionPiscinaDatos("DTPe + Hib + HB ",0,0));
		piscinaVacunas.add(new VacunacionPiscinaDatos("DTP",1,5));
		piscinaVacunas.add(new VacunacionPiscinaDatos("SRP",6,10));
		piscinaVacunas.add(new VacunacionPiscinaDatos("AT 1",11,15));
		piscinaVacunas.add(new VacunacionPiscinaDatos("T R1",16,19));
		piscinaVacunas.add(new VacunacionPiscinaDatos("A/Gripal",65,75));

		return piscinaVacunas;
	}

	private static String generarCalle(ArrayList<String> calles) {
		String calle = generarNombreApellido();
		if(Validaciones.validarRepeticionStringLista(calles, calle))
			calle = generarCalle(calles);

		return calle;
	}

	private static ArrayList<String> generarCallesPrincipales() {
		ArrayList<String> calles = new ArrayList<String>();

		for(int i=0;i<Definiciones.CANTIDAD_CALLES_PRINCIPALES;i++)
			Auxiliares.introducirStringListado(generarCalle(calles), calles);

		return calles;
	}
	private static ArrayList<String> generarNumerosCasas(){
		ArrayList<String> numeros = new ArrayList<String>();
		int cantNumeros = generarEntero(Definiciones.CANT_MIN_VIVIENDAS_CALLE, Definiciones.CANT_MAX_VIVIENDAS_CALLE)-1;
		numeros.add(String.valueOf(generarEntero(5,100)));

		for(int i=0;i<cantNumeros;i++)
			numeros.add(String.valueOf(generarEntero(5,10)+Integer.valueOf(numeros.get(i))));

		return numeros;
	}
	private static ArrayList<Direccion> generarDirecciones(){
		ArrayList<Direccion> direcciones = new ArrayList<Direccion>();
		ArrayList<String> callesPrincipales = generarCallesPrincipales();

		for(String c : callesPrincipales) {
			ArrayList<String> numerosCasas = generarNumerosCasas();
			for(String n : numerosCasas)
				direcciones.add(new Direccion(c,n));
		}
		return direcciones;
	}
	private static ArrayList<DireccionPiscinaDatos> inicializarPiscinaDirecciones() {
		piscinaDirecciones = new ArrayList<DireccionPiscinaDatos>();
		ArrayList<DireccionPiscinaDatos> listado = new ArrayList<DireccionPiscinaDatos>();
		ArrayList<Direccion> direcciones = generarDirecciones();

		for(int i=0;i<direcciones.size();i++)
			listado.add(new DireccionPiscinaDatos(direcciones.get(i),i+1));

		return listado;
	}

	public static Direccion generarDireccion() {
		return piscinaDirecciones.get(generarEntero(0,piscinaDirecciones.size()-1)).getDireccion();
	}

	public static int getNumeroVivienda(Direccion d) {
		return piscinaDirecciones.get(indiceDireccion(d)).getNumeroVivienda();
	}
	private static int indiceDireccion(Direccion d) { 
		int indice = -1;
		if(d!=null) {
			Comparator<DireccionPiscinaDatos> c1 = new Comparator<DireccionPiscinaDatos>() {
				@Override
				public int compare(DireccionPiscinaDatos d1, DireccionPiscinaDatos d2) {
					int r = d1.getDireccion().getCallePrincipal().compareTo(d2.getDireccion().getCallePrincipal());
					if(r==0) 
						r = d1.getDireccion().getNumeroCasa().compareTo(d2.getDireccion().getNumeroCasa());
					
					return r;
				}};
			Comparator<Direccion> c2 = new Comparator<Direccion>() {
				@Override
				public int compare(Direccion d1, Direccion d2) {
					int r = d1.getCallePrincipal().compareTo(d2.getCallePrincipal());
					if(r==0)
						r= d1.getNumeroCasa().compareTo(d2.getNumeroCasa());
					return r;
				}};
			Collections.sort(piscinaDirecciones, c1);
			indice = Collections.binarySearch(listadoDirecciones(), d, c2);
		}
		return indice;
	}
	private static ArrayList<Direccion> listadoDirecciones(){
		ArrayList<Direccion> listado = new ArrayList<Direccion>();

		for(DireccionPiscinaDatos d : piscinaDirecciones)
			listado.add(d.getDireccion());

		return listado;
	}
	public static int generarNumeroHabitanteVivienda(int numeroVivienda) {
		return piscinaDirecciones.get(numeroVivienda-1).generarIdentificadorHabitante();
	}


	public static ArrayList<String> getListadoCallesPrincipales() {
		ArrayList<String> calles = new ArrayList<String>();
		
		String calle = "";
		//Se puede realizar de esta forma pues se genera de forma ordenada
		for(DireccionPiscinaDatos d : piscinaDirecciones) {
			String aux = d.getDireccion().getCallePrincipal();
			if(!calle.equals(aux)) {
				calle=aux;
				calles.add(calle);
			}
			
		}

		return calles;
	}

	public static ArrayList<String> getListadoNumeroCasaCalle(String calle){
		ArrayList<String> numeroCasas = new ArrayList<String>();
		ArrayList<Direccion> direcciones = listadoDirecciones();

		for(Direccion d : direcciones)
			if(d.getCallePrincipal().equals(calle)) 
				numeroCasas.add(d.getNumeroCasa());

		return numeroCasas;
	}

	public static ArrayList<String> getListadoTipoAnalisis(){
		return new ArrayList<String>(piscinaTipoAnalisis);
	}

	public static ArrayList<String> getListadoEspecialidadesRemitidas(){
		return new ArrayList<String>(piscinaEspecialidadesRemitidas);
	}

	public static ArrayList<String> getListadoEnfermedadesCronicas() {
		return new ArrayList<String>(piscinaEnfermedadesCronicas);
	}

	public static ArrayList<String> getListadoNombresVacunas(){
		ArrayList<String> listado = new ArrayList<String>();

		for(VacunacionPiscinaDatos v : piscinaVacunas)
			listado.add(v.getNombreVacuna());

		return listado;
	}

	public static VacunacionPiscinaDatos getDatosVacunacion(String nombreVacuna) {
		return piscinaVacunas.get(getListadoNombresVacunas().indexOf(nombreVacuna));
	}
	
	public static ArrayList<String> generarEnfermedadesCronicas(){
		ArrayList<String> enfermedades = new ArrayList<String>();
		
		
		if(rnd.nextBoolean()) { //Para disminuir la probabilidad
			int cantEnfermedades = generarEntero(0,piscinaEnfermedadesCronicas.size());
			Collections.shuffle(piscinaEnfermedadesCronicas);
			for(int i=0;i<cantEnfermedades;i++) 
				enfermedades.add(piscinaEnfermedadesCronicas.get(i));
		}
		
		return enfermedades;
	}
	
	protected ArrayList<String> listadoCIGenerados(){
		return new ArrayList<String>(this.listadoCIGenerados);
	}
	
}
