package com.stevesoltys.owl.controller.component;

import com.stevesoltys.owl.controller.OwlComponentRestController;
import com.stevesoltys.owl.repository.OwlComponentRepository;
import com.stevesoltys.owl.repository.OwlComponentAttributeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * The context configuration for the {@link OwlComponentRestControllerTests}.
 *
 * @author Steve Soltys
 */
@Configuration
@EnableWebMvc
public class OwlComponentRestControllerTestsContext {

    @Bean
    public OwlComponentAttributeRepository componentTypeRepository() {
        return new OwlComponentAttributeRepository();
    }

    @Bean
    public OwlComponentRepository componentRepository() {
        return new OwlComponentRepository(componentTypeRepository());
    }

    @Bean
    public OwlComponentRestController owlComponentRestController() {
        return new OwlComponentRestController(componentRepository());
    }
}
