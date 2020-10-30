package clase_mvc;

public class ClaseBean {
	private int idcla,idcur,iddoc;
	private String horini,horfin,nomcur;
	
	public int getIdcla() {
		return idcla;
	}
	public int getIddoc() {
		return iddoc;
	}
	public void setIddoc(int iddoc) {
		this.iddoc = iddoc;
	}
	public void setIdcla(int idcla) {
		this.idcla = idcla;
	}
	public int getIdcur() {
		return idcur;
	}
	public String getNomcur() {
		return nomcur;
	}
	public void setNomcur(String nomcur) {
		this.nomcur = nomcur;
	}
	public void setIdcur(int idcur) {
		this.idcur = idcur;
	}
	public String getHorini() {
		return horini;
	}
	public void setHorini(String horini) {
		this.horini = horini;
	}
	public String getHorfin() {
		return horfin;
	}
	public void setHorfin(String horfin) {
		this.horfin = horfin;
	}
}
