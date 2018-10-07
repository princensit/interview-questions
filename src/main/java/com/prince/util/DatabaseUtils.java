package com.prince.util;

import javax.annotation.Nonnull;

public class DatabaseUtils {

    public static final String VALIDATION_QUERY_SQLSERVER = "SELECT 1";

    public static final String VALIDATION_QUERY_MYSQL = "SELECT 1";

    public enum Database {
        MYSQL(false), SQLSERVER(true);

        private final boolean useOutputForGeneratedKeys;

        Database(boolean useOutputForGeneratedKeys) {
            this.useOutputForGeneratedKeys = useOutputForGeneratedKeys;
        }

        public static Database getDatabase(@Nonnull String url) {
            for (Database database : Database.values()) {
                if (url.contains(database.toString().toLowerCase())) {
                    return database;
                }
            }
            throw new IllegalArgumentException(url);
        }

        public boolean isUseOutputForGeneratedKeys() {
            return useOutputForGeneratedKeys;
        }
    }

    private DatabaseUtils() {}
}
