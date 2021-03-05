package TP.proj;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import TP.proj.entities.Amitie;
import TP.proj.entities.Position;
import TP.proj.entities.Role;
import TP.proj.entities.RoleName;
import TP.proj.entities.User;
import TP.proj.respository.RoleRepository;
import TP.proj.entities.RoleName;

@SpringBootApplication
public class ProjApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjApplication.class, args);
	}

	@Autowired
	private RoleRepository roleRep;
	
	@Override
	public void run(String... args) throws Exception{

		
		Role r1=new Role(RoleName.ROLE_USER);
		Role r2=new Role(RoleName.ROLE_ADMIN);
		this.roleRep.save(r1);
		this.roleRep.save(r2);
		
	}


}
