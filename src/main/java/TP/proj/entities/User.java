package TP.proj.entities;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nom_complet;
	private String username;
	private String email;
	private String password;
	private String telephone;
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	private String ville;
	
	@ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
	@Fetch(FetchMode.JOIN)
    private Set<Role> roles = new HashSet<>();
	


	
	public User() {
		super();
	}
	
	
	

	
	public User(int id) {
		super();
		this.id = id;
	}





	public User(String nom_complet, String username, String email, String password, String telephone,
			Date dateNaissance, String ville) {
		super();
		this.nom_complet = nom_complet;
		this.username = username;
		this.email = email;
		this.password = password;
		this.telephone = telephone;
		this.dateNaissance = dateNaissance;
		this.ville = ville;
	}





	public User(int id, String nom_complet, String username, String email, String password, String telephone,
			Date dateNaissance, String ville) {
		super();
		this.id = id;
		this.nom_complet = nom_complet;
		this.username = username;
		this.email = email;
		this.password = password;
		this.telephone = telephone;
		this.dateNaissance = dateNaissance;
		this.ville = ville;
	}









	public User(int id, String nom_complet, String username, String email, String password, String telephone,
			Date dateNaissance, String ville, Set<Role> roles) {
		super();
		this.id = id;
		this.nom_complet = nom_complet;
		this.username = username;
		this.email = email;
		this.password = password;
		this.telephone = telephone;
		this.dateNaissance = dateNaissance;
		this.ville = ville;
		this.roles = roles;
	}





	public Set<Role> getRoles() {
		return roles;
	}





	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}





	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}






	public String getNom_complet() {
		return nom_complet;
	}





	public void setNom_complet(String nom_complet) {
		this.nom_complet = nom_complet;
	}





	public String getUsername() {
		return username;
	}





	public void setUsername(String username) {
		this.username = username;
	}





	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
	
	
	
	
	

}
