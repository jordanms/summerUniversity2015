package ee.hm.dop.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import javax.inject.Inject;

import org.junit.Test;

import ee.hm.dop.common.test.DatabaseTestBase;
import ee.hm.dop.model.Language;
import ee.hm.dop.model.TranslationGroup;

public class TranslationDAOTest extends DatabaseTestBase {

    @Inject
    private TranslationDAO translationDAO;

    @Test
    public void getTranslationGroupForEstonian() {
        Language language = new Language();
        language.setId((long) 1);
        language.setCode("est");

        TranslationGroup translationGroup = translationDAO.findTranslationGroupFor(language);

        assertNotNull(translationGroup);
        assertEquals(language.getCode(), translationGroup.getLanguage().getCode());
        assertEquals(language.getId(), translationGroup.getLanguage().getId());
        Map<String, String> translations = translationGroup.getTranslations();
        assertEquals(3, translations.size());
        assertEquals("FOO sõnum", translations.get("FOO"));
        assertEquals("Eesti keeles", translations.get("Estonian"));
        assertEquals("Vene keel", translations.get("Russian"));
    }

    @Test
    public void getTranslationGroupForRussian() {
        Language language = new Language();
        language.setId((long) 2);
        language.setCode("rus");

        TranslationGroup translationGroup = translationDAO.findTranslationGroupFor(language);

        assertNotNull(translationGroup);
        assertEquals(language.getCode(), translationGroup.getLanguage().getCode());
        assertEquals(language.getId(), translationGroup.getLanguage().getId());
        Map<String, String> translations = translationGroup.getTranslations();
        assertEquals(3, translations.size());
        assertEquals("FOO сообщение", translations.get("FOO"));
        assertEquals("Эстонский язык", translations.get("Estonian"));
        assertEquals("русский язык", translations.get("Russian"));
    }
}
