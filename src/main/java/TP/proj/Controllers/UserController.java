package TP.proj.Controllers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import TP.proj.entities.Amitie;
import TP.proj.entities.Position;
import TP.proj.entities.RoleName;
import TP.proj.entities.User;
import TP.proj.payload.ApiResponse;
import TP.proj.payload.UserIdentityAvailability;
import TP.proj.payload.UserProfile;
import TP.proj.payload.UserSummary;
import TP.proj.respository.*;
import TP.proj.security.CurrentUser;
import TP.proj.security.UserPrincipal;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
    private  PositionRepository pr;
	
	 @GetMapping("/user/currentuser")
	    @PreAuthorize("hasRole('USER')")
	    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
	        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
	        return userSummary;
	    }
	
	 @GetMapping("/checkUsernameAvailability")
	    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
	        Boolean isAvailable = !userRepo.existsByUsername(username);
	        return new UserIdentityAvailability(isAvailable);
	    }

	    @GetMapping("/checkEmailAvailability")
	    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
	        Boolean isAvailable = !userRepo.existsByEmail(email);
	        return new UserIdentityAvailability(isAvailable);
	    }
	    
	    @GetMapping("/{username}")
	    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
	        User user = userRepo.findByUsername(username);
	        UserProfile userProfile = new UserProfile(user.getId(),user.getUsername(),user.getNom_complet(),user.getEmail(),user.getTelephone(),user.getDateNaissance(),user.getVille());

	        return userProfile;
	    }
//	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@PostMapping("/add")
	public void save(@RequestBody User u){
		userRepo.save(u);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable (required = true) String id){
		userRepo.deleteById(Integer.parseInt(id));
	}

	

  
    @GetMapping("/allUsers")
    public List<User> findAll() {
	   List<User> users = userRepo.findAll();
	   ArrayList<User> l= new ArrayList<User>();
	   for(User u:users) {
		   if(userRepo.getroleName(u.getId()).getName() == RoleName.ROLE_USER) {
			   l.add(u);
		   }
	   }
	   return l;
    }

    @GetMapping("/getUser/{username}")
    public User getUser(@PathVariable(value = "username") String username) {
	   User user = userRepo.findByNom(username);
	  
	   return user;
    }
    
	
	   @GetMapping("/byCity/{city}")
	    public List<User> getUserCity(@PathVariable(value = "city") String city) {
		   List<User> users = userRepo.findbycity(city);
		  
		   return users;
	    }
	   
		@GetMapping("/MyPosition/{currentUser}")
	    public List<Position> getMyPosition(@PathVariable(value = "currentUser") String currentUser ) {
			User u = userRepo.findByUsername(currentUser);  
			   
				List<Position> lp= pr.findbyuser(u.getId());
				return lp;
			
	    }
		@GetMapping("/getRole/{currentUser}")
	    public RoleName getRoleByUsername(@PathVariable(value = "currentUser") String currentUser ) {
			User u = userRepo.findByUsername(currentUser); 
		
				return userRepo.getroleName(u.getId()).getName();
			
			
	    }
		
		  @GetMapping("/allusersExceptMe/{currentUser}")
	    public List<User> findAllExceptMe(@PathVariable(value = "currentUser") String currentUser) {
			  User u = userRepo.findByUsername(currentUser); 
		   List<User> users = userRepo.findUsersExceptMe(u.getId());
		   return users;
	    }

			@PostMapping("/edit/{currentUserID}")
			   public ResponseEntity<?> EditUser(@PathVariable(value = "currentUserID") int currentUserID,@RequestBody User u1) {
//				User u2= userRepo.getbyid(currentUserID);
				if(u1.getId()== currentUserID ) {
					if(u1.getNom_complet() != null &&  u1.getUsername() != null && u1.getDateNaissance() != null
							&& u1.getEmail() != null && u1.getTelephone() != null && u1.getVille() != null ) {
						userRepo.editUserInfos(u1.getNom_complet(), u1.getUsername(), u1.getEmail(), u1.getTelephone(), u1.getDateNaissance(), u1.getVille(), currentUserID);
					}else if(u1.getNom_complet() != null &&  u1.getUsername() != null && u1.getDateNaissance() != null
							&& u1.getEmail() != null && u1.getTelephone() != null) {

						userRepo.editUser1(u1.getNom_complet(), u1.getUsername(), u1.getEmail(), u1.getTelephone(), u1.getDateNaissance(), currentUserID);
						
					}else if(u1.getNom_complet() != null &&  u1.getUsername() != null && u1.getDateNaissance() != null
							&& u1.getEmail() != null) {

						userRepo.editUser2(u1.getNom_complet(), u1.getUsername(), u1.getEmail(), u1.getTelephone(), currentUserID);
					}else if(u1.getNom_complet() != null &&  u1.getUsername() != null && u1.getDateNaissance() != null) {

						userRepo.editUser3(u1.getNom_complet(), u1.getUsername(), u1.getEmail(), currentUserID);
					}else if(u1.getNom_complet() != null &&  u1.getUsername() != null) {

						userRepo.editUser4(u1.getNom_complet(), u1.getUsername(), currentUserID);
					}else if(u1.getNom_complet() != null) {
						userRepo.editUserName(u1.getNom_complet(), currentUserID);

					}
					 return new ResponseEntity(new ApiResponse(true, "The edit operation  is succeeded !"),
			                    HttpStatus.CREATED);
				}
				else {
					 return new ResponseEntity(new ApiResponse(false, "User not found !"),
			                    HttpStatus.BAD_REQUEST);
				}
			
			}
			
			
			@GetMapping("/PositionsOfUser/{User}/{date1}/{date2}")
		    public List<Position> findpositionsbtnDates(@PathVariable(value = "User") String User ,@PathVariable(value = "date1") Date date1,@PathVariable(value = "date2") Date date2) {
				 List<User> users = userRepo.findAll();
				   ArrayList<User> l= new ArrayList<User>();
				   for(User u:users) {
					   if(userRepo.getroleName(u.getId()).getName() == RoleName.ROLE_USER) {
						   l.add(u);
					   }
				   }
				
				
				User u1 = userRepo.findByUsername(User);   
				List<Position> l1= pr.findbyUsersbtnsDates(u1.getId(), date1, date2);
				 List<Position> lp= null;
				 for(User re :l) {
					 if(re.getId()==u1.getId()) {
						 lp=l1;
					 }
					
				 }
			   
		      return lp;
		    }
}
