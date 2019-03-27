package com.prince.design.connection_pool;

import lombok.Data;

@Data
public class DataSource {

    private String url;

    private String username;

    private String password;

    private String driverClassName;

    private int initialPoolSize;

    private int maxPoolSize;
}
