package ee.hm.dop.guice.module;

import com.google.inject.servlet.ServletModule;

import ee.hm.dop.guice.GuiceInjector.Module;
import ee.hm.dop.service.MaterialService;
import ee.hm.dop.service.TranslationService;

@Module
public class RestModule extends ServletModule {

    @Override
    protected void configureServlets() {
        bind(MaterialService.class);
        bind(TranslationService.class);
    }
}
