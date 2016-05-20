package com.stevesoltys.owl.controller.component;

import com.stevesoltys.owl.model.component.CPULoadComponent;
import com.stevesoltys.owl.repository.OwlComponentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Test the {@link OwlComponentRestController}.
 *
 * @author Steve Soltys
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OwlComponentRestControllerTestsContext.class)
@WebAppConfiguration
public class OwlComponentRestControllerTests {

    /**
     * The content type that is used for the REST service.
     */
    private static final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    /**
     * The mock mvc instance, used for testing the REST service.
     */
    private MockMvc mockMvc;

    /**
     * The web application context.
     */
    @Autowired
    private WebApplicationContext webApplicationContext;

    /**
     * The component repository.
     */
    @Autowired
    private OwlComponentRepository componentRepository;

    /**
     * Sets up the mock MVC and mocks a data set for the component repository.
     *
     * @throws Exception If there is an error setting up the test environment.
     */
    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        componentRepository.getComponents().clear();
        componentRepository.getComponents().add(new CPULoadComponent());
    }

    /**
     * Tests serving components to a remote host.
     *
     * @throws Exception If there is an error obtaining the response, the content type is incorrect, or the result set
     *                   is invalid.
     */
    @Test
    public void testServeComponents() throws Exception {

        mockMvc.perform(get(OwlComponentRestController.COMPONENTS_MAPPING)).
                andExpect(status().isOk()).
                andExpect(content().contentType(contentType)).
                andExpect(jsonPath("$['components']", hasSize(componentRepository.getComponents().size()))).
                andExpect(jsonPath("$['components'][0].lastUpdate", notNullValue())).
                andExpect(jsonPath("$['components'][0].state", notNullValue())).
                andExpect(jsonPath("$['components'][0].updateInterval", notNullValue()));
    }
}
