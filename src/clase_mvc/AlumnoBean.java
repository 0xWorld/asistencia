package clase_mvc;

public class AlumnoBean {
	private String codalu,nomalu;
	private Boolean asistio;
	public AlumnoBean(String codalu, String nomalu,Boolean asistio) {
		super();
		this.codalu = codalu;
		this.nomalu = nomalu;
		this.asistio = asistio;
	}

	public Boolean getAsistio() {
		return asistio;
	}

	public void setAsistio(Boolean asistio) {
		this.asistio = asistio;
	}

	public String getCodalu() {
		return codalu;
	}

	public void setCodalu(String codalu) {
		this.codalu = codalu;
	}

	public String getNomalu() {
		return nomalu;
	}

	public void setNomalu(String nomalu) {
		this.nomalu = nomalu;
	}
}
