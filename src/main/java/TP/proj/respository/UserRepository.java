package TP.proj.respository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import TP.proj.entities.Amitie;
import TP.proj.entities.Role;
import TP.proj.entities.User;
import java.util.Optional;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository< User, Integer> {
	Optional<User> findById(int id);

	@Query("select u from User u where u.username=?1 ")
	User findByUsername(String username);
	
	@Query("select u from User u where u.ville=?1")
	List<User> findbycity(String ville);
	
	@Query("select u from User u where u.nom_complet=?1")
	User findByNom(String nom_complet);
	
	@Query("select u from User u where u.id!=?1 ")
	List<User> findUsersExceptMe(int id);
	
	@Query("select u from User u where u.id=?1 ")
	User getbyid(int id);
	
	
	@Query("select u.roles from User u where u.id=?1 ")
	Role getroleName(int id);
	
	
	 Optional<User> findByUsernameOrEmail(String username, String email);
	
	  Optional<User> findByEmail(String email);


	    List<User> findByIdIn(List<Long> userIds);


	    Boolean existsByUsername(String username);

	    Boolean existsByEmail(String email);
	    
	    @Transactional
	    @Modifying
		@Query("update User u set u.nom_complet=?1 , u.username=?2,u.email=?3,u.telephone=?4,u.dateNaissance=?5,"
				+ "u.ville=?6 where u.id=?7")
		void editUserInfos(String nom_complet,String username, String email,String telephone,
				Date dateNaissance, String ville,int idUser);
	    
	    @Transactional
	    @Modifying
		@Query("update User u set u.nom_complet=?1 , u.username=?2,u.email=?3,u.telephone=?4,u.dateNaissance=?5"
				+ " where u.id=?6")
		void editUser1(String nom_complet,String username, String email,String telephone,
				Date dateNaissance,int idUser);
	    
	    
	    @Transactional
	    @Modifying
		@Query("update User u set u.nom_complet=?1 , u.username=?2,u.email=?3,u.telephone=?4"
				+ " where u.id=?5")
		void editUser2(String nom_complet,String username, String email,String telephone,int idUser);
	    
	    @Transactional
	    @Modifying
		@Query("update User u set u.nom_complet=?1 , u.username=?2,u.email=?3"
				+ " where u.id=?4")
		void editUser3(String nom_complet,String username, String email,int idUser);
	    
	    @Transactional
	    @Modifying
		@Query("update User u set u.nom_complet=?1 , u.username=?2"
				+ " where u.id=?3")
		void editUser4(String nom_complet,String username,int idUser);
	    
	    
	    
	    @Transactional
	    @Modifying
		@Query("update User u set u.nom_complet=?1 where u.id=?2")
		void editUserName(String nom_complet,int idUser);
	
	 
}
