package u589664.dat152.test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import u589664.dat152.model.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationTests {

	private Logger LOG = LoggerFactory.getLogger(ApplicationTests.class);
	
	@Autowired
	TestRestTemplate template;
	
	final String baseUri = "/items";
	
	HttpHeaders headers = new HttpHeaders();
	
	@Test
	@Order(0)
	//@Disabled
	void contextLoads() {
		headers.setContentType(MediaType.APPLICATION_JSON);
	}
	
	@Test
	@Order(2)
	@DisplayName("Check Add Course")
	//@Disabled("Not ready")
	public void checkCreateItem() throws Exception {
		Item item1 = new Item();
		item1.setPrice(10.00);
		item1.setDescription("mongusextended");
		item1.setName("taste");
		
		ResponseEntity<Item> response = template.postForEntity(baseUri, item1, null);
				
		assertTrue(response.getStatusCode().is2xxSuccessful());
	}
	
	@Test
	@Order(1)
	@DisplayName("Check 3 items")
	//@Disabled("Not ready")
	public void checkthreeItems() throws Exception {
		
		ResponseEntity<Item[]> response = template.getForEntity(baseUri, Item[].class);
				
		assertThat(response.getBody()).hasSize(3);
	}
	
	@Test
	@Order(3)
	@DisplayName("Check 10100 returns NOT FOUND")
	//@Disabled("Not ready")
	public void checkNotFound() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String url = baseUri + "/10100";
		
		//log in the console (optional)
		LOG.info("Testing for uri: " + baseUri);
		
		HttpStatus expectedStatus = HttpStatus.NOT_FOUND;
		
		ResponseEntity<Item> response = template.getForEntity(url, Item.class);
		
		assertEquals(expectedStatus, response.getStatusCode());
	}

}
