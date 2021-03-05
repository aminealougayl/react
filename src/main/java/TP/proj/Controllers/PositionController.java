package TP.proj.Controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TP.proj.entities.Amitie;
import TP.proj.entities.Position;
import TP.proj.entities.RelationKey;
import TP.proj.entities.User;
import TP.proj.respository.PositionRepository;
import TP.proj.respository.UserRepository;


@RestController
@RequestMapping("/api/positions")
public class PositionController {
	
	@Autowired
	private PositionRepository PosiRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@PostMapping("/add")
	public void save(@RequestBody Position u){
		PosiRepo.save(u);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable (required = true) String id){
		Position p = PosiRepo.findById(Integer.parseInt(id));
		PosiRepo.delete(p);
	}

	@GetMapping("/all")
	public List<Position>findAll(){
		return PosiRepo.findAll();
	}
	
	@GetMapping(value = "/byuser/{userID}")
	public  List<Position>findbyuser(@PathVariable (value = "userID") int userID) {
		return PosiRepo.findbyuser(userID); 
	}
	
}
