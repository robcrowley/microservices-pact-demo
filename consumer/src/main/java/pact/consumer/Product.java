package pact.consumer;

import java.util.Objects;

public class Product {

  private String id, createdOn, name, description, logo;

  public Product(final String id, final String createdOn, final String name, final String description, String logo) {
    this.id = id;
    this.createdOn = createdOn;
    this.name = name;
    this.description = description;
    this.logo = logo;
  }

  public String getId() {
    return id;
  }
  
  public String getCreatedOn() {
    return createdOn;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }
  
  public String getLogo() {
    return logo;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (getClass() != other.getClass()) return false;

    final Product product = (Product) other;

    return Objects.equals(id, product.getId()) && Objects.equals(createdOn, product.getCreatedOn())
        && Objects.equals(name, product.getName())
        && Objects.equals(description, product.getDescription()) && Objects.equals(logo, product.getLogo());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdOn, name, description, logo);
  }
}
