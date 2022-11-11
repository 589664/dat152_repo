package u589664.dat152.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import u589664.dat152.model.Item;
import u589664.dat152.service.ItemService;

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
	  item1.setName("taste");
	  
	  Item item2 = new Item();
	  item2.setPrice(20.00);
	  item2.setDescription("mongusextended");
	  item2.setName("grace");
	  
	  Item item3 = new Item();
	  item3.setPrice(3900.00);
	  item3.setDescription("mongusextended");
	  item3.setName("pace");
      
      log.info("Preloading " + (service.saveOrUpdate(item1)).toString());
      log.info("Preloading " + (service.saveOrUpdate(item2)).toString());
      log.info("Preloading " + (service.saveOrUpdate(item3)).toString());
  }

}
