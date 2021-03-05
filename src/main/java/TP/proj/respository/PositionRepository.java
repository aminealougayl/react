package TP.proj.respository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import TP.proj.entities.Position;
import TP.proj.entities.User;

public interface PositionRepository extends JpaRepository<Position , Integer> {

	Position findById(int id);
	@Query("select p from Position p where p.user.id =?1 ")
	List<Position> findbyuser(int userID);
	
	@Query("select p from Position p where p.user.id =?1 and p.date between	?2 and ?3")
	List<Position> findbyUsersbtnsDates(int id,Date d1,Date d2);
}
