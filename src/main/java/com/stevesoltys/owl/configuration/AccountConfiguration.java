package com.stevesoltys.owl.configuration;

import com.stevesoltys.owl.exception.OwlConfigurationException;
import com.stevesoltys.owl.model.Account;
import com.stevesoltys.owl.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * A configuration class used to initialize {@link Account}s.
 *
 * @author Steve Soltys
 */
@Component
public class AccountConfiguration {

    /**
     * The configuration key for the list of {@link Account}s.
     */
    private static final String ACCOUNT_LIST_KEY = "accounts";

    /**
     * The configuration key for the username of an {@link Account}.
     */
    private static final String USERNAME_KEY = "username";

    /**
     * The configuration key for the password of an {@link Account}.
     */
    private static final String PASSWORD_KEY = "password";

    /**
     * The account repository.
     */
    private final AccountRepository accountRepository;

    @Autowired
    public AccountConfiguration(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Initializes the application using the given {@link Account} configuration map.
     *
     * @param configuration The configuration map.
     * @throws OwlConfigurationException If there is an error in the given configuration.
     */
    @SuppressWarnings("unchecked")
    public void initialize(Map<String, Object> configuration) throws OwlConfigurationException {
        try {
            List<Map<String, String>> accounts = (List<Map<String, String>>) configuration.get(ACCOUNT_LIST_KEY);

            for (Map<String, String> accountConfiguration : accounts) {

                if (!accountConfiguration.containsKey(USERNAME_KEY)) {
                    throw new OwlConfigurationException("An account in the configuration does not contain a username.");
                } else if (!accountConfiguration.containsKey(PASSWORD_KEY)) {
                    throw new OwlConfigurationException("An account in the configuration does not contain a password.");
                }

                String username = accountConfiguration.get(USERNAME_KEY);
                String password = accountConfiguration.get(PASSWORD_KEY);
                Account account = new Account(username, password);

                accountRepository.getAccounts().add(account);
            }
        } catch (NullPointerException | ClassCastException e) {
            throw new OwlConfigurationException("Invalid agent configuration.");
        }
    }

}
