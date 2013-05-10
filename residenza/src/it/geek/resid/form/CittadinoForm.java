package it.geek.resid.form;

import it.geek.resid.util.Collezioni;

import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.util.LabelValueBean;

public class CittadinoForm extends ActionForm {

	private String method;
	private String codFis;
	private Integer codRegione;
	private Integer codProvincia;
	private Integer codCitta;
	private List<LabelValueBean> listaRegioni;
	private List<LabelValueBean> listaProvince;
	private List<LabelValueBean> listaCitta;
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	public List<LabelValueBean> getListaRegioni() {
		listaRegioni = Collezioni.getOptions("regioni");
		return listaRegioni;
	}
	public List<LabelValueBean> getListaProvince() {
		if(codRegione==null){
			listaProvince = Collezioni.getOptions("province");
		}else{
			listaProvince = Collezioni.getOptionsFiltered("province","cod_regione",codRegione);
		}
		return listaProvince;
	}
	public List<LabelValueBean> getListaCitta() {
		if(codProvincia==null){
			listaProvince = Collezioni.getOptions("citta");
		}else{
			listaCitta = Collezioni.getOptionsFiltered("citta","cod_provincia",codProvincia);
		}
		return listaCitta;
	}

	public Integer getCodRegione() {
		return codRegione;
	}

	public void setCodRegione(Integer codRegione) {
		this.codRegione = codRegione;
	}
	public Integer getCodProvincia() {
		return codProvincia;
	}
	public void setCodProvincia(Integer codProvincia) {
		this.codProvincia = codProvincia;
	}
	public Integer getCodCitta() {
		return codCitta;
	}
	public void setCodCitta(Integer codCitta) {
		this.codCitta = codCitta;
	}
	public String getCodFis() {
		return codFis;
	}
	public void setCodFis(String codFis) {
		this.codFis = codFis;
	}
}
