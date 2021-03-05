package TP.proj.Controllers;


import java.util.ArrayList;
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
import TP.proj.respository.AmitieRepository;
import TP.proj.respository.PositionRepository;
import TP.proj.respository.UserRepository;

@RestController
@RequestMapping("api/amis")
public class AmitieController {
	@Autowired
	private AmitieRepository AmisRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PositionRepository PosiRepo;
	
	@PostMapping("/add")
	public void save(@RequestBody Amitie u){
		AmisRepo.save(u);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable (required = true) String id){
		Amitie p = AmisRepo.findById(Integer.parseInt(id));
		AmisRepo.delete(p);
	}

	@GetMapping("/all")
	public List<Amitie>findAll(){
		List<Amitie> al= AmisRepo.findAll();
		 return al;
	}
	
	
	@GetMapping("/addfriend/{currentUser}/{friendName}")
	    public void addfriend(@PathVariable(value = "currentUser") String currentUser ,@PathVariable(value = "friendName") String friendName) {
		   User u1 = userRepo.findByUsername(currentUser);
		   User u2 = userRepo.findByUsername(friendName);
		   RelationKey key= new RelationKey(u1.getId(),u2.getId());
		   Amitie am =new  Amitie(key,new Date(),1,u1,u2);

		   AmisRepo.save(am);
	      
	    }
	@GetMapping("/accept/{currentUser}/{PotentielFriend}")
    public void acceptfriend(@PathVariable(value = "currentUser") String currentUser ,@PathVariable(value = "PotentielFriend") String PotentielFriend) {
		
		
	   User u1 = userRepo.findByUsername(currentUser);
	   User u2 = userRepo.findByUsername(PotentielFriend);
	   List<Amitie> fr2= AmisRepo.loadfriendship(u2.getId(),u1.getId());
	   
	  if(fr2 != null ) {
		   RelationKey newkey= new RelationKey(u2.getId(),u1.getId());
		   Amitie amNEW =new  Amitie(newkey,new Date(),3,u2,u1);
		   AmisRepo.save(amNEW);
	   }
	 

      
    }
	@GetMapping("/block/{currentUser}/{blockedFriend}")
    public void blockfriend(@PathVariable(value = "currentUser") String currentUser ,@PathVariable(value = "blockedFriend") String blockedFriend) {
	   User u1 = userRepo.findByUsername(currentUser);
	   User u2 = userRepo.findByUsername(blockedFriend);
	   
	   List<Amitie> fr2= AmisRepo.loadfriendship(u2.getId(),u1.getId());
	   
	   if(fr2 != null ) {
		   RelationKey newkey= new RelationKey(u2.getId(),u1.getId());
		   Amitie amNEW =new  Amitie(newkey,new Date(),2,u2,u1);
		   AmisRepo.save(amNEW);
	   }
	   
      
    }

	@GetMapping("/getMyfriends/{currentuser}")
    public List<Amitie> findMyFriends(@PathVariable(value = "currentuser") String currentuser) {
		User u = userRepo.findByUsername(currentuser);   
	   List<Amitie> fr = AmisRepo.findMyfriends(u.getId());
	   ArrayList<Amitie> l = new ArrayList<Amitie>();
	   for(Amitie f:fr) {
		   if(f.getEtat()==3) {
			   l.add(f);
		   }
	   }
	   return l;
   
    }
	@GetMapping("/getMyfriendByName/{currentuser}/{myfriendName}")
    public User findMyFriendsbyName(@PathVariable(value = "currentuser") String currentuser,@PathVariable(value = "myfriendName") String myfriendName) {
		User u = userRepo.findByUsername(currentuser);   
		User u2 = userRepo.findByUsername(myfriendName); 
		User u1= null;
	   List<Amitie> fr = AmisRepo.findMyfriends(u.getId());
	   ArrayList<Amitie> l = new ArrayList<Amitie>();
	   for(Amitie f:fr) {
		   if(f.getEtat()==3) {
			   l.add(f);
		   }
	   }
	   for(Amitie a:l) {
		   if(a.getUser1().getId()==u2.getId() || a.getUser2().getId()==u2.getId()) {
			  u1=u2;
		   }
	   }
	  return u1;
   
    }
	@GetMapping("/getMyfriendByCity/{currentuser}/{city}")
    public List<User> findMyFriendsbyCity(@PathVariable(value = "currentuser") String currentuser,@PathVariable(value = "city") String city) {
		User u = userRepo.findByUsername(currentuser);  
		List<User> ll=userRepo.findbycity(city);
		ArrayList<User> l3 =new ArrayList<>();
	   List<Amitie> fr = AmisRepo.findMyfriends(u.getId());
	   ArrayList<Amitie> l = new ArrayList<Amitie>();
	   for(Amitie f:fr) {
		   if(f.getEtat()==3 ) {
			   l.add(f);
		   }
	   }
	   for(Amitie a:l) {
		 for(User us:ll) {
			 if((a.getUser1().getId()==us.getId()) &&(a.getUser1().getId()!= u.getId())) {
				 l3.add(a.getUser1());
			 }else if((a.getUser2().getId()==us.getId()) &&(a.getUser2().getId()!= u.getId())) {
				 l3.add(a.getUser2());
			 }
		 }
	   }
	   
	  return l3;
   
    }
	@GetMapping("/getInvitations/{currentuser}")
    public List<Amitie> getInvitations(@PathVariable(value = "currentuser") String currentuser) {
		User u = userRepo.findByUsername(currentuser);   
	   return AmisRepo.GetUsers_state(u.getId(),1);
   
    }
	@GetMapping("/getBlockedUsers/{currentuser}")
    public List<Amitie> getBlockedUsers(@PathVariable(value = "currentuser") String currentuser) {
		User u = userRepo.findByUsername(currentuser);  
	   return AmisRepo.GetUsers_state(u.getId(),2);
   
    }
	
	@GetMapping("/PositionsOfFriend/{currentUser}/{friend}")
    public List<Position> findpositionsOfFriend(@PathVariable(value = "currentUser") String currentUser ,@PathVariable(value = "friend") String friend) {
		User u1 = userRepo.findByUsername(currentUser);   
		User u2 = userRepo.findByUsername(friend); 
		 List<Amitie> fd= findMyFriends(currentUser);
		 List<Position> lp= null;
		 for(Amitie re :fd) {
			 if((re.getUser1().getId()==u2.getId()) || (re.getUser2().getId()==u2.getId())) {
				 lp= PosiRepo.findbyuser(u2.getId());
				  break;
			 }
		 }
	   
      return lp;
    }
	
	 

}
