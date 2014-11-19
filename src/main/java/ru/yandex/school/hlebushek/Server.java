package ru.yandex.school.hlebushek;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

public class Server extends ResourceConfig {

    public Server() {
        register(new DynamicFeature() {
            @Override
            public void configure(ResourceInfo resourceInfo, FeatureContext context) {
                //context.register(DatabaseProvider.class); todo: add db provider class
            }
        });
        packages(Server.class.getPackage().getName());
    }
}
