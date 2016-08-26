package pact.provider.jersey;

import javax.inject.Singleton;
import javax.ws.rs.ext.Provider;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import pact.provider.dao.ProductDao;

@Provider
public class ProductDaoBinder extends AbstractBinder {
  @Override
  protected void configure() {
      bind(ProductDao.class).to(ProductDao.class).in(Singleton.class);
  }
}