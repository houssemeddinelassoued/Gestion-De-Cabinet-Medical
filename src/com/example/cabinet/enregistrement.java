package com.example.cabinet;

import java.io.Serializable;







public class enregistrement implements Serializable  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String val_nom, val_prenom,val_sexe, val_phone, val_observation;
	 int  jj,mm ,yy ;
	   public  enregistrement(){} ;
	
	public String getVal_nom( ) {
		return val_nom;
	}
	public void setVal_nom(String val_nom) {
		this.val_nom = val_nom;
	}
	public String getVal_prenom( ) {
		return val_prenom;
	}
	public void setVal_prenom(String val_prenom) {
		this.val_prenom = val_prenom;
	}
	public String getVal_sexe() {
		return val_sexe;
	}
	public void setVal_sexe(String val_sexe) {
		this.val_sexe = val_sexe;
	}
	public String getVal_phone() {
		return val_phone;
	}
	public void setVal_phone(String val_phone) {
		this.val_phone = val_phone;
	}
	public String getVal_observation() {
		return val_observation;
	}
	public void setVal_observation(String val_observation) {
		this.val_observation = val_observation;
	}
	
	public enregistrement(String val_nom, String val_prenom, String val_sexe,
			String val_phone, String val_observation,int jj , int mm, int yy) {
		super();
		this.val_nom = val_nom;
		this.val_prenom = val_prenom;
		this.val_sexe = val_sexe;
		this.val_phone = val_phone;
		this.val_observation = val_observation;
		this.jj=jj;
		this.mm=mm;
		this.yy=yy;
		
	}

	public int getJj() {
		return jj;
	}

	public void setJj(int jj) {
		this.jj = jj;
	}

	public int getMm() {
		return mm;
	}

	public void setMm(int mm) {
		this.mm = mm;
	}

	public int getYy() {
		return yy;
	}

	public void setYy(int yy) {
		this.yy = yy;
	}
	
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
