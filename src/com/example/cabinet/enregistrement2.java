package com.example.cabinet;

import java.io.Serializable;

public class enregistrement2 implements Serializable {
	
	private static final long serialVersionUID = 1L;
	String val_nom, val_prenom;
	 int  jj,mm ,yy ,hr,mn,pm_am;
	 
	 public  enregistrement2(){} ;
	 
		public String getVal_nom() {
			return val_nom;
		}
		public void setVal_nom(String val_nom) {
			this.val_nom = val_nom;
		}
		
		public String getVal_prenom() {
			return val_prenom;
		}
		public void setVal_prenom(String val_prenom) {
			this.val_prenom = val_prenom;
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
		
		//////////////////
		public int getHr() {
			return hr;
		}

		public void setHr(int hr) {
			this.hr = hr;
		}
		/////////////////////////
		public int getMn() {
			return mn;
		}

		public void setMn(int mn) {
			this.mn = mn;
		}
		/////////////////////
		public int getPm_am() {
			return pm_am;
		}

		public void setPm_am(int pm_am) {
			this.pm_am = pm_am;
		}
		
		public enregistrement2(String val_nom, String val_prenom, 
				int jj , int mm, int yy,
				int hr , int mn) {
			super();
			this.val_nom = val_nom;
			this.val_prenom = val_prenom;
			this.jj=jj;
			this.mm=mm;
			this.yy=yy;
			
			this.hr=hr;
			this.mn=mn;
				
		}

}
