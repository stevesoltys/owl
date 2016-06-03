package com.stevesoltys.owl.config;

import com.stevesoltys.owl.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The context configuration for the {@link OwlConfigurationTests}.
 *
 * @author Steve Soltys
 */
@Configuration
public class OwlConfigurationTestsContext {

    @Bean
    public OwlConfiguration owlConfiguration() {
        return new OwlConfiguration(owlComponentTypeConfiguration(), componentConfiguration(), agentConfiguration(),
                accountConfiguration());
    }

    @Bean
    public OwlComponentControllerRepository componentControllerRepository() {
        return new OwlComponentControllerRepository();
    }

    @Bean
    public OwlComponentTypeConfiguration owlComponentTypeConfiguration() {
        return new OwlComponentTypeConfiguration(componentTypeRepository(), componentControllerRepository());
    }


    @Bean
    public OwlComponentTypeRepository componentTypeRepository() {
        return new OwlComponentTypeRepository();
    }

    @Bean
    public OwlComponentRepository componentRepository() {
        return new OwlComponentRepository(componentTypeRepository());
    }

    @Bean
    public OwlComponentConfiguration componentConfiguration() {
        return new OwlComponentConfiguration(componentRepository());
    }

    @Bean
    public AgentRepository agentRepository() {
        return new AgentRepository();
    }

    @Bean
    public AgentConfiguration agentConfiguration() {
        return new AgentConfiguration(agentRepository());
    }

    @Bean
    public AccountRepository accountRepository() {
        return new AccountRepository();
    }

    @Bean
    public AccountConfiguration accountConfiguration() {
        return new AccountConfiguration(accountRepository());
    }

}