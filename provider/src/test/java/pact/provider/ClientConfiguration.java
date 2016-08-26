package pact.provider;

import org.glassfish.jersey.server.ResourceConfig;

import com.theoryinpractise.halbuilder.jaxrs.JaxRsHalBuilderReaderSupport;
import com.theoryinpractise.halbuilder.jaxrs.JaxRsHalBuilderSupport;

public class ClientConfiguration extends ResourceConfig {
  public ClientConfiguration() {
    register(JaxRsHalBuilderSupport.class);
    register(JaxRsHalBuilderReaderSupport.class);
  }
}
