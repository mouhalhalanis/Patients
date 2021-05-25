package com.emsi.demo2;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.emsi.demo2.dao.PatientRepos;
import com.emsi.demo2.entities.Patient;

@SpringBootApplication



public class Demo2Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}
	@Autowired
	private PatientRepos pr ; 
	@Override
	public void run(String... args) throws Exception {
	

		
		pr.findAll().forEach(p->{System.out.println(p.getNom());});
	}

}
