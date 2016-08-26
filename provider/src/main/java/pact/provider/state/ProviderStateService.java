package pact.provider.state;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@Path("/providerstates")
public class ProviderStateService {
  private final Logger LOGGER = LoggerFactory.getLogger(ProviderStateService.class);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response changeState(ProviderState state) {
    LOGGER.info("provider state called: state: '{}', action = '{}'",
        state.getName(),
        state.getAction());

    return Response.noContent().build();
  }
}
