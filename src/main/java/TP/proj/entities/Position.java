package TP.proj.entities;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Position {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPosition;
	private long latitude;
	private long longitude;
	@Temporal(TemporalType.DATE)
	private Date date ;

	

	 @ManyToOne( cascade = CascadeType.ALL )
	    @JoinColumn( name="userID" )
	    private User user;
	
	
	public Position() {
		super();
	}
	



	


	public Position(long latitude, long longitude, Date date, User user) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.date = date;
		this.user = user;
	}







	public Position(int idPosition, long latitude, long longitude, Date date, User user) {
		super();
		this.idPosition = idPosition;
		this.latitude = latitude;
		this.longitude = longitude;
		this.date = date;
		this.user = user;
	}







	public int getIdPosition() {
		return idPosition;
	}

	public void setIdPosition(int idPosition) {
		this.idPosition = idPosition;
	}

	public long getLatitude() {
		return latitude;
	}

	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}

	public long getLongitude() {
		return longitude;
	}

	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}  
	
	
}
