package no.hvl.dat152.h2databaseexample.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import no.hvl.dat152.h2databaseexample.model.Student;
import no.hvl.dat152.h2databaseexample.service.StudentService;

@Component
class BootstrapCommandLineRunner implements CommandLineRunner  {

  private static final Logger log = LoggerFactory.getLogger(BootstrapCommandLineRunner.class);

  @Autowired
  StudentService service;

  @Override
  public void run(String... args) throws Exception {

	  Student stud1 = new Student();
	  stud1.setAge(10);
	  stud1.setEmail("mongusextended");
	  stud1.setName("grace");
      
      log.info("Preloading " + (service.saveOrUpdate(stud1)).toString());
   
  }

}
