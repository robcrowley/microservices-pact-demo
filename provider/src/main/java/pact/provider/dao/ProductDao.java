package pact.provider.dao;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import pact.entities.Product;

@Component
public class ProductDao {
  private final Map<String, Product> products = Stream
      .of(new Product("LRPL", "2016-2-28", "Personal Loan", "Low Rate Personal Loan", "/cdn/logos/lrpl.webp"))
      .collect(Collectors.toMap(k -> k.getId(), v -> v));

  public Stream<Product> getAll() {
    return this.products.values()
        .stream()
        .sorted((first, second) -> first.getId().compareTo(second.getId()));
  }

  public void add(final Product product) {
    requireNonNull(product);

    products.put(product.getId(), product);
  }
}
