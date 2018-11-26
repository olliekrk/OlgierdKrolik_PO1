package entries.general;

import values.IBibtexValue;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static entries.general.BibtexFieldConstraint.required;
import static entries.general.BibtexFieldConstraint.requiredMultiple;

public abstract class BibtexEntry implements BibtexVisitableElement {

    private final String id;

    //static map, where keys are classes which extend BibtexEntry and values are maps with constraints of every field
    protected static Map<Class<? extends BibtexEntry>, Map<String, BibtexFieldConstraint>> classConstraints = new HashMap<>();

    public BibtexEntry(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Map<String, BibtexFieldConstraint> getConstraintMap() {
        return classConstraints.get(this.getClass());
    }

    public boolean validateEntry() {
        Class entryClass = this.getClass();
        Map<String, BibtexFieldConstraint> constraintMap = this.getConstraintMap();

        String fieldName;
        BibtexFieldConstraint constraint;
        IBibtexValue value;

        for (Field f : entryClass.getDeclaredFields()) {
            if (f.getType().equals(IBibtexValue.class)) {

                fieldName = f.getName();
                constraint = constraintMap.get(fieldName);
                try {
                    value = (IBibtexValue) f.get(this);
                } catch (IllegalAccessException e) {
                    //exception, unlikely to happen as every field is public
                    e.printStackTrace();
                    return false;
                }

                if ((value == null) && (constraint.equals(required) || constraint.equals(requiredMultiple))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void accept(BibtexVisitor visitor) {
        visitor.visit(this);
    }
}
