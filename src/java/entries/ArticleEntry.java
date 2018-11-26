package entries;

import entries.general.BibtexEntry;
import entries.general.BibtexFieldConstraint;
import values.IBibtexValue;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static entries.general.BibtexFieldConstraint.*;

public class ArticleEntry extends BibtexEntry {

    public IBibtexValue author, title, journal, year, volume, number, pages, month, note, key;

    public ArticleEntry(String id) {
        super(id);
    }

    static {
        Map<String, BibtexFieldConstraint> constraintMap = new HashMap<>();
        for (Field f : ArticleEntry.class.getDeclaredFields()) {
            constraintMap.put(f.getName(), none); //by default there are no constraints of a field
        }
        constraintMap.put("author", requiredMultiple);
        constraintMap.put("title", required);
        constraintMap.put("journal", required);
        constraintMap.put("year", required);
        classConstraints.put(ArticleEntry.class, constraintMap);
    }

}
