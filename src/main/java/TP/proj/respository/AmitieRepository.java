package TP.proj.respository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import TP.proj.entities.Amitie;
import TP.proj.entities.User;


public interface AmitieRepository extends JpaRepository<Amitie , Integer> {
	Amitie findById(int id);
	
	
	@Query("select a from Amitie a where a.id.idUser1=?1 or a.id.idUser2=?1")
	List<Amitie> findfriendsOFuser(int idUser);

	
	@Query("select a from Amitie a where  a.id.idUser2=?1 and a.etat=?2")
	List<Amitie> GetUsers_state(int idUser,int etat);
	@Query("select a from Amitie a where a.id.idUser1=?1 or a.id.idUser2=?2")
	List<Amitie> loadfriendship(int idUser1,int idUser2);
	
	@Query("select a from Amitie a where a.id.idUser1=?1 or a.id.idUser2=?1")
	List<Amitie> findMyfriends(int idUser);
}
