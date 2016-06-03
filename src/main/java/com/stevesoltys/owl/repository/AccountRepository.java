package com.stevesoltys.owl.repository;

import com.stevesoltys.owl.model.Account;
import com.stevesoltys.owl.model.Agent;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * A repository which contains {@link Agent}s.
 *
 * @author Steve Soltys
 */
@Repository
public class AccountRepository {

    /**
     * The set of {@link Account}s that this repository contains.
     */
    private final Set<Account> accounts = new HashSet<>();

    /**
     * Attempts to find an {@link Account} in the repository, given the username.
     *
     * @param username The username.
     * @return An optional, possibly containing the account. If it is empty, the account was not found.
     */
    public Optional<Account> findByUsername(String username) {

        for (Account account : accounts) {

            if (account.getUsername().equals(username)) {
                return Optional.of(account);
            }
        }

        return Optional.empty();
    }

    /**
     * Gets the set of {@link Account}s that are in this repository.
     *
     * @return The accounts.
     */
    public Set<Account> getAccounts() {
        return accounts;
    }
}
