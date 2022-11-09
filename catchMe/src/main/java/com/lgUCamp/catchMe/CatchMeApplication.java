package com.lgUCamp.catchMe;

import com.lgUCamp.catchMe.Service.CarboninfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatchMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatchMeApplication.class, args);

		System.out.println("CATCH ME Project Start!! ---------------------- >>> ");

	}

}
