package TP.proj.payload;


import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import TP.proj.entities.Position;

public class UserProfile {
    private int id;
    private String username;
    private String name;
	private String email;
	private String telephone;
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	private String ville;


	
	public int getId() {
		return id;
	}

	public UserProfile(int id, String username, String name, String email,String telephone,
			Date dateNaissance, String ville) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.dateNaissance = dateNaissance;
		this.ville = ville;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

 

   
}

