/*
 * Copyright 2016-2017 Sophos Limited. All rights reserved.
 *
 * 'Sophos' and 'Sophos Anti-Virus' are registered trademarks of Sophos Limited
 * and Sophos Group.  All other product and company names mentioned are
 * trademarks or registered trademarks of their respective owners.
 */
package com.java.features;

import java.util.Collection;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

final class CollectionsUtil {

    private CollectionsUtil() {
    }

    private static boolean isBlank(final Collection source) {
        return source == null || source.isEmpty();
    }

    static <T> boolean exists(final Collection<T> source, final Predicate<T> predicate) {
        return !(isBlank(source) || predicate == null) && source.stream().anyMatch(predicate);

    }

    static <T, U> boolean isCollectionsEquals(final Collection<T> col1, final Collection<U> col2,
                                              final BiPredicate<Collection<T>, Collection<U>> collectionsTester) {
        final boolean col1Empty = isBlank(col1);
        return col1Empty == isBlank(col2)
                && (col1Empty || col1.size() == col2.size() && collectionsTester.test(col1, col2));
    }
}
