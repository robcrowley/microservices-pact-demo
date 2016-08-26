package pact.consumer;

import static com.theoryinpractise.halbuilder.api.RepresentationFactory.HAL_JSON;
import static java.util.Objects.requireNonNull;

import java.util.stream.Collectors;

import javax.ws.rs.client.ClientBuilder;

import org.springframework.stereotype.Component;

import com.theoryinpractise.halbuilder.api.ContentRepresentation;

@Component
public class ProductCatalogueServiceAdapter {

  private final String url;

  public ProductCatalogueServiceAdapter(String url) {
    this.url = requireNonNull(url);
  }

  public Iterable<Product> getProducts() {
    final ContentRepresentation representation = ClientBuilder.newClient(new ClientConfiguration())
        .target(url)
        .path("products")
        .request(HAL_JSON)
        .get(ContentRepresentation.class);

    return representation.getResourcesByRel("items")
        .stream()
        .map(p -> new Product(String.class.cast(p.getValue("id")),
            String.class.cast(p.getValue("createdOn")),
            String.class.cast(p.getValue("name")),
            String.class.cast(p.getValue("description")),
            String.class.cast(p.getValue("logo"))))
        .collect(Collectors.toList());
  }
}
