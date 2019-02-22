package ca.mcgill.ecse321.cooperator;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class CooperatorApplication {
	
	public static SessionFactory factory;
	
	public static void main(String[] args) {
		SpringApplication.run(CooperatorApplication.class, args);
	}

  @RequestMapping("/")
  public String greeting(){
    return "Deployed to Heroku";
  }
}
