package pact.provider;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.theoryinpractise.halbuilder.jaxrs.JaxRsHalBuilderReaderSupport;
import com.theoryinpractise.halbuilder.jaxrs.JaxRsHalBuilderSupport;

import pact.provider.jersey.ProductDaoBinder;
import pact.provider.state.ProviderStateService;

@Component
public class Configuration extends ResourceConfig {
  public Configuration() {
    register(ProductDaoBinder.class);
    register(JaxRsHalBuilderSupport.class);
    register(JaxRsHalBuilderReaderSupport.class);

    registerClasses(ProductCatalogueService.class, ProviderStateService.class);
  }
}
