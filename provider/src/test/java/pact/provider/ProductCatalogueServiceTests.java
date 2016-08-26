package pact.provider;

import static com.theoryinpractise.halbuilder.api.RepresentationFactory.HAL_JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.theoryinpractise.halbuilder.api.ContentRepresentation;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductCatalogueServiceTests {

  @LocalServerPort
  private int port;

  @Test
  public void test_getproducts_success() {
    final Response response = ClientBuilder.newClient()
        .target("http://localhost:" + this.port)
        .path("products")
        .request(HAL_JSON)
        .get();

    assertThat(response.getStatusInfo()).isEqualTo(Status.OK);
  }


  @Test
  public void test_getproductsrepresentation_success() {
    final ContentRepresentation representation = ClientBuilder.newClient(new ClientConfiguration())
        .target("http://localhost:" + this.port)
        .path("products")
        .request(HAL_JSON)
        .get(ContentRepresentation.class);

    assertNotNull(representation);
  }
}
