package pact.entities;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public class Product {

  private String id, createdOn, name, description, logo;

  public Product(final String id, final String createdOn, final String name, final String description, String logo) {
    this.id = requireNonNull(id);
    this.createdOn = requireNonNull(createdOn);
    this.name = requireNonNull(name);
    this.description = requireNonNull(description);
    this.logo = requireNonNull(logo);
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

  public void setName(String name) {
    this.name = requireNonNull(name);
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = requireNonNull(description);
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = requireNonNull(logo);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (getClass() != other.getClass()) return false;

    final Product product = (Product) other;

    return Objects.equals(id, product.getId()) && Objects.equals(createdOn, product.getCreatedOn())
        && Objects.equals(name, product.getName())
        && Objects.equals(description, product.getDescription())
        && Objects.equals(logo,  product.getLogo());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdOn, name, description, logo);
  }
}
