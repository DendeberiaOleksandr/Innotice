package org.innotice.discord.server.resource;

import lombok.SneakyThrows;
import org.innotice.test.util.AbstractTestContainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
public class AuthorizationFlowIT extends AbstractTestContainer {

    @Autowired
    private MockMvc mvc;

    @SneakyThrows
    @Test
    void requestAuthenticatedEndpoint_whenAuthorizationNotProvided_thenShouldDenyAccess() {
        // given
        mvc.perform(
                get("/")
        );

        // when

        // then
    }

}
