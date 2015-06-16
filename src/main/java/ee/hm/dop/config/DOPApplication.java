package ee.hm.dop.config;

import javax.inject.Inject;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import ee.hm.dop.guice.GuiceInjector;
import ee.hm.dop.guice.provider.ObjectMapperProvider;

public class DOPApplication extends ResourceConfig {

    @Inject
    public DOPApplication(ServiceLocator serviceLocator) {
        // Set package to look for resources in
        packages("ee.hm.dop");

        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);

        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(GuiceInjector.getInjector());

        register(JacksonFeature.class);
        register(ObjectMapperProvider.class);
    }
}