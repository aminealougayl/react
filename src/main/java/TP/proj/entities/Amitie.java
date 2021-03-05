package TP.proj.entities;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;



@Entity
public class Amitie {
	 @EmbeddedId
	 private RelationKey id;
	 private Date dateAmitie;
	 private int etat;
	 
	   @JoinColumn(name = "idUser1", referencedColumnName = "id", insertable = false, updatable = false)
	    @ManyToOne
	    private User User1;
	    @JoinColumn(name = "idUser2", referencedColumnName = "id", insertable = false, updatable = false)
	    @ManyToOne
	    private User User2;
	
	    



	public Amitie() {
		super();
	}
	

	public Amitie(RelationKey id, Date dateAmitie, int etat, User user1, User user2) {
		super();
		this.id = id;
		this.dateAmitie = dateAmitie;
		this.etat = etat;
		User1 = user1;
		User2 = user2;
	}


	public Amitie(Date dateAmitie, int etat, User user1, User user2) {
		super();
		this.dateAmitie = dateAmitie;
		this.etat = etat;
		User1 = user1;
		User2 = user2;
	}


	public Date getDateAmitie() {
		return dateAmitie;
	}


	public void setDateAmitie(Date dateAmitie) {
		this.dateAmitie = dateAmitie;
	}


	public int getEtat() {
		return etat;
	}


	public void setEtat(int etat) {
		this.etat = etat;
	}


	public Amitie(RelationKey id, User user1, User user2) {
		super();
		this.id = id;
		User1 = user1;
		User2 = user2;
	}


	public Amitie(User user1, User user2) {
		super();
		User1 = user1;
		User2 = user2;
	}


	public RelationKey getId() {
		return id;
	}


	public void setId(RelationKey id) {
		this.id = id;
	}


	public User getUser1() {
		return User1;
	}


	public void setUser1(User user1) {
		User1 = user1;
	}


	public User getUser2() {
		return User2;
	}


	public void setUser2(User user2) {
		User2 = user2;
	}


	


	


	


	
	 
	 
}
