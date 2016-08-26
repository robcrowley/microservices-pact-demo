package pact.provider.state;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class ProviderState {
  private final Logger LOGGER = LoggerFactory.getLogger(ProviderState.class);
  
  private Object name, action;

  public Object getName() {
    return this.name;
  }

  public Object getAction() {
    return this.action;
  }

  @JsonProperty("action")
  public void setAction(Object action) {
    this.action = action;
  }

  @JsonAnySetter
  public void setProviderState(String key, Object value) {
    LOGGER.info("setting provider state. key={}, value={}", key, value);
    
    this.name = value;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (getClass() != other.getClass()) return false;

    final ProviderState product = (ProviderState) other;

    return Objects.equals(name, product.getName()) && Objects.equals(action, product.getAction());
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, action);
  }
}
