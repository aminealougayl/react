package TP.proj.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class RelationKey implements Serializable{
	private int idUser1;
	private int idUser2;
	
	
	
	public RelationKey() {
		super();
	}

	

	public RelationKey(int idUser1, int idUser2) {
		super();
		this.idUser1 = idUser1;
		this.idUser2 = idUser2;
		
	}






	public int getIdUser1() {
		return idUser1;
	}




	public void setIdUser1(int idUser1) {
		this.idUser1 = idUser1;
	}




	public int getIdUser2() {
		return idUser2;
	}




	public void setIdUser2(int idUser2) {
		this.idUser2 = idUser2;
	}








}
