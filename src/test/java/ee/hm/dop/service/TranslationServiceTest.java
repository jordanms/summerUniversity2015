package ee.hm.dop.service;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.Map;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import ee.hm.dop.dao.LanguageDAO;
import ee.hm.dop.dao.TranslationDAO;
import ee.hm.dop.model.Language;
import ee.hm.dop.model.TranslationGroup;

@RunWith(EasyMockRunner.class)
public class TranslationServiceTest {

    @TestSubject
    private TranslationService translationService = new TranslationService();

    @Mock
    private LanguageDAO languageDAO;

    @Mock
    private TranslationDAO translationDAO;

    @Test
    public void getTranslationsForNull() {

        replayAll();

        assertNull(translationService.getTranslationsFor(null));

        verifyAll();
    }

    @Test
    public void getTranslationsForNotSupportedLanguage() {
        String languageCode = "notSupportedLanguageCode";

        expect(languageDAO.findByCode(languageCode)).andReturn(null);

        replayAll();

        assertNull(translationService.getTranslationsFor(languageCode));

        verifyAll();
    }

    @Test
    public void getTranslationsForSupportedLanguageButNoTranslation() {
        String languageCode = "supportedLanguageCode";
        Language language = createMock(Language.class);

        expect(languageDAO.findByCode(languageCode)).andReturn(language);
        expect(translationDAO.findTranslationGroupFor(language)).andReturn(null);

        replayAll(language);

        assertNull(translationService.getTranslationsFor(languageCode));

        verifyAll(language);
    }

    @Test
    public void getTranslationsForSupportedLanguageWithTranslation() {
        String languageCode = "supportedLanguageCode";
        Language language = createMock(Language.class);
        TranslationGroup translationGroup = createMock(TranslationGroup.class);
        @SuppressWarnings("unchecked")
        Map<String, String> translations = createMock(Map.class);

        expect(languageDAO.findByCode(languageCode)).andReturn(language);
        expect(translationDAO.findTranslationGroupFor(language)).andReturn(translationGroup);
        expect(translationGroup.getTranslations()).andReturn(translations);

        replayAll(language, translationGroup, translations);

        assertSame(translations, translationService.getTranslationsFor(languageCode));

        verifyAll(language, translationGroup, translations);
    }

    private void replayAll(Object... mocks) {
        replay(languageDAO, translationDAO);

        if (mocks != null) {
            for (Object object : mocks) {
                replay(object);
            }
        }
    }

    private void verifyAll(Object... mocks) {
        verify(languageDAO, translationDAO);

        if (mocks != null) {
            for (Object object : mocks) {
                verify(object);
            }
        }
    }
}
