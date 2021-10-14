package co.edu.usbbog.sd.model;

public class CredencialesBasic {
	
	private String usern, contra;

	public CredencialesBasic(String usern, String contra) {
		super();
		this.usern = usern;
		this.contra = contra;
	}

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
