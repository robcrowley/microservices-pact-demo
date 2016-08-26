package pact.provider;

import static com.theoryinpractise.halbuilder.api.RepresentationFactory.HAL_JSON;
import static com.theoryinpractise.halbuilder.api.RepresentationFactory.HAL_XML;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.theoryinpractise.halbuilder.api.Representation;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import com.theoryinpractise.halbuilder.standard.StandardRepresentationFactory;

import pact.provider.dao.ProductDao;

@Path("/products")
@Produces({HAL_JSON, HAL_XML})
public class ProductCatalogueService {
  private final ProductDao dao;
  private final RepresentationFactory factory = new StandardRepresentationFactory();

  @Inject
  public ProductCatalogueService(final ProductDao dao) {
    this.dao = dao;
  }

  @GET
  public Response getProducts() {
    final List<Representation> representations = dao.getAll()
        .map(p -> factory.newRepresentation()
            .withLink("self", "/products/" + p.getId(), null, p.getName(), null, null)
            .withBean(p))
        .collect(Collectors.toList());

    final Representation envelope = factory.newRepresentation("/products")
        .withProperty("page", 1)
        .withProperty("perPage", 10)
        .withProperty("totalCount", representations.size());

    representations.forEach(r -> envelope.withRepresentation("items", r));

    return Response.ok(envelope).build();
  }
}
