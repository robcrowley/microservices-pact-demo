package pact.consumer;

import static com.theoryinpractise.halbuilder.api.RepresentationFactory.HAL_JSON;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static junit.framework.TestCase.assertEquals;

import org.junit.Rule;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import au.com.dius.pact.model.PactSpecVersion;
import jersey.repackaged.com.google.common.collect.ImmutableList;

public class ProductCatalogueServiceAdapterTests {

  @Rule
  public PactProviderRule mockProvider = new PactProviderRule("Product_Catalogue_Provider", "localhost", 8080, PactSpecVersion.V3, this);

  @Pact(provider = "Product_Catalogue_Provider", consumer = "Product_Catalogue_Consumer")
  public PactFragment createFragment(PactDslWithProvider builder) {
    return builder.given("products exist")
        .uponReceiving("a request for the product catalogue")
        .path("/products")
        .method("GET")
        .headers(ImmutableMap.of(ACCEPT, HAL_JSON))
        .willRespondWith()
        .headers(ImmutableMap.of(CONTENT_TYPE, HAL_JSON))
        .status(200)
        .body(new PactDslJsonBody()
            .integerType("page", 1)
            .integerType("perPage", 10)
            .integerType("totalCount", 1)
            .object("_links")
              .object("self")
                .stringType("href", "/products")
              .closeObject()
            .closeObject()
            .object("_embedded")
              .eachLike("items")
                .stringType("id", "LRPL")
                .stringMatcher("createdOn", "\\d{4}-\\d{1,2}-\\d{1,2}", "2016-2-28")
                .stringType("name", "Personal Loan")
                .stringType("description", "Low Rate Personal Loan")
                .stringType("logo", "/cdn/logos/lrpl.webp")
                .object("_links")
                  .object("self")
                    .stringType("href", "/products/LRPL")
                    .stringType("title", "Personal Loan")
                  .closeObject()
                .closeObject()
              .closeObject()
            .closeArray()
          .closeObject())
        .toFragment();
  }

  @Test
  @PactVerification("Product_Catalogue_Provider")
  public void runTest() {
    assertEquals(new ProductCatalogueServiceAdapter("http://localhost:8080").getProducts(), 
        ImmutableList.of(new Product("LRPL", "2016-2-28", "Personal Loan", "Low Rate Personal Loan",  "/cdn/logos/lrpl.webp")));
  }
}
