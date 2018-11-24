package entries;

import entries.general.BibtexEntry;
import parser.BibtexConstraints;
import values.IBibtexValue;

import java.lang.reflect.Field;

import static entries.general.BibtexFieldConstraint.*;

public class BookEntry extends BibtexEntry {

    @BibtexConstraints(required = true, multiple = true)
    public IBibtexValue author;
    @BibtexConstraints(required = true, multiple = true)
    public IBibtexValue editor;
    @BibtexConstraints(required = true)
    public IBibtexValue title;
    @BibtexConstraints(required = true)
    public IBibtexValue publisher;
    @BibtexConstraints(required = true)
    public IBibtexValue year;
    public IBibtexValue volume;
    public IBibtexValue series;
    public IBibtexValue address;
    public IBibtexValue edition;
    public IBibtexValue month;
    public IBibtexValue note;
    public IBibtexValue key;

    //public IBibtexValue author, editor, title, publisher, year, volume, number, series, address, edition;

    public BookEntry(String id) {
        super(id);
    }

    static {
        for (Field f : IncollectionEntry.class.getDeclaredFields()) {
            constraintMap.put(f.getName(), none); //by default there are no constraints of a field
        }
        constraintMap.put("author", requiredMultiple);
        constraintMap.put("editor", requiredMultiple);
        constraintMap.put("title", required);
        constraintMap.put("publisher", required);
        constraintMap.put("year", required);
    }
}
