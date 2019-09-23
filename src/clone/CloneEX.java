package clone;

import eqandhash.EqualsPhone;

// Clone method for class with no reference to mutable state
public class CloneEX {
    @Override public EqualsPhone clone() {
        try {
            return (EqualsPhone) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // can't happen
        }
    }
}
