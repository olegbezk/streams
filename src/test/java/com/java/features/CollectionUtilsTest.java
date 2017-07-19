/*
 * Copyright 2016-2017 Sophos Limited. All rights reserved.
 *
 * 'Sophos' and 'Sophos Anti-Virus' are registered trademarks of Sophos Limited
 * and Sophos Group.  All other product and company names mentioned are
 * trademarks or registered trademarks of their respective owners.
 */
package com.java.features;

import org.junit.Test;

import java.util.HashSet;
import java.util.function.Predicate;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CollectionUtilsTest {

    @Test
    public void testIsCollectionsEquals() {
        final User userOne = new User("John", "Dow");
        final User userTwo = new User("William", "Smith");

        final HashSet<User> onlyFirstUsers = new HashSet<>(asList(userOne, userOne));
        final HashSet<User> withFirstAndSecondUsers = new HashSet<>(asList(userOne, userTwo));

        final boolean onlyWithFirstUsersEquals = CollectionsUtil
                .isCollectionsEquals(onlyFirstUsers, onlyFirstUsers, (expected, actual) -> expected.stream()
                        .anyMatch(e -> CollectionsUtil.exists(actual, a -> getUserPredicate(a).test(e))));

        assertTrue(onlyWithFirstUsersEquals);

        final boolean withFirstAndSecondUsersEquals = CollectionsUtil
                .isCollectionsEquals(onlyFirstUsers, withFirstAndSecondUsers, (expected, actual) -> expected.stream()
                        .anyMatch(e -> CollectionsUtil.exists(actual, a -> getUserPredicate(a).test(e))));

        assertFalse(withFirstAndSecondUsersEquals);
    }

    private Predicate<User> getUserPredicate(final User user) {
        return u -> u.getFirstName().equals(user.getFirstName()) && u.getSecondName().equals(user.getSecondName());
    }

    private class User {

        private String firstName;

        private String secondName;

        User(String firstName, String secondName) {
            this.firstName = firstName;
            this.secondName = secondName;
        }

        String getFirstName() {
            return firstName;
        }

        String getSecondName() {
            return secondName;
        }
    }
}
