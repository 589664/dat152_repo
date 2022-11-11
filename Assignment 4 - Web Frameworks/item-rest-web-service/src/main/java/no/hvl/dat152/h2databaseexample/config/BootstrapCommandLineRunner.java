package no.hvl.dat152.h2databaseexample.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import no.hvl.dat152.h2databaseexample.model.Item;
import no.hvl.dat152.h2databaseexample.service.ItemService;

@Component
class BootstrapCommandLineRunner implements CommandLineRunner  {

  private static final Logger log = LoggerFactory.getLogger(BootstrapCommandLineRunner.class);

  @Autowired
  ItemService service;

  @Override
  public void run(String... args) throws Exception {

	  Item item1 = new Item();
	  item1.setPrice(10.00);
	  item1.setDescription("mongusextended");
	  item1.setName("grace");
      
      log.info("Preloading " + (service.saveOrUpdate(item1)).toString());
   
  }

}
