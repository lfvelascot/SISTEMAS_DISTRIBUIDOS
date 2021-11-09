package co.edu.usbbog.sd.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CredencialesBasic {
	
	private String usern, contra;
	
	public CredencialesBasic(String usern, String contra) {
		this.usern = usern;
		this.contra = contra;
	}

	
	public CredencialesBasic() {}

	public String getUsern() {
		return usern;
	}

	public void setUsern(String usern) {
		this.usern = usern;
	}

	public String getContra() {
		return contra;
	}

	public void setContra(String contra) {
		this.contra = contra;
	}
	
	

}
