package ee.hm.dop.guice.module;

import javax.persistence.EntityManagerFactory;

import org.apache.commons.configuration.Configuration;

import com.google.inject.AbstractModule;

import ee.hm.dop.guice.GuiceInjector.Module;
import ee.hm.dop.guice.provider.ConfigurationTestProvider;
import ee.hm.dop.guice.provider.EntityManagerFactoryTestProvider;

@Module(override = ProviderModule.class)
public class ProviderTestModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(EntityManagerFactory.class).toProvider(EntityManagerFactoryTestProvider.class);
        bind(Configuration.class).toProvider(ConfigurationTestProvider.class);
    }
}
