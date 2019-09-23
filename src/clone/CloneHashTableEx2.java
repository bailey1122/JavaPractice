package clone;

// recursive clone method for class with complex mutable state
public class CloneHashTableEx2 implements Cloneable {
    private Entry[] buckets = new Entry[2];

    private static class Entry {
        final Object key;
        Object value;
        Entry next;

        Entry(Object key, Object value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        // recursively copy the linked list headed by this Entry. It works fine if the buckets aren't too long
        Entry deepCopy() {
            return new Entry(key, value,
                    next == null ? null : next.deepCopy());
        }

//        Entry deepCopy() {
//            Entry result = new Entry(key, value, next);
//            for (Entry p = result; p.next != null; p = p.next)
//                p.next = new Entry(p.next.key, p.next.value, p.next.next);
//            return result;
//        }
    }
    @Override public CloneHashTableEx2 clone() {
        try {
            CloneHashTableEx2 result = (CloneHashTableEx2) super.clone();
            result.buckets = new Entry[buckets.length];
            for (int i = 0; i < buckets.length; i++)
                if (buckets[i] != null)
                    result.buckets[i] = buckets[i].deepCopy();
                return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    // remainder omitted


//    // clone method for extendable class not supporting Cloneable
//    @Override
//    protected final Object clone() throws CloneNotSupportedException {
//        throw new CloneNotSupportedException();
//    }

    // a batter approach to object copying is to provide a copy constructor or copy factory

    // copy constructor
//    public Yum(Yum yum) { ... };

    // copy factory
//    public static Yum newInstance(Yum yum) { ... };
}
