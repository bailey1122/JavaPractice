package eqandhash;

// equals
public final class EqualsPhone {
    private final short areaCode, prefix, lineNum;

    public EqualsPhone(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "area code");
        this.prefix = rangeCheck(prefix, 999, "prefix");
        this.lineNum = rangeCheck(lineNum, 9999, "line num");
    }

    private static short rangeCheck(int val, int max, String arg) {
        if (val < 0 || val > max)
            throw new IllegalArgumentException(arg + ": " + val);
        return (short) val;
    }

    // equals
    @Override public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EqualsPhone))
            return false;
        EqualsPhone ep = (EqualsPhone)o;
        return ep.lineNum == lineNum && ep.prefix == prefix && ep.areaCode == areaCode;
    }

    // hashCode
    @Override public int hashCode() {
        int result = Short.hashCode(areaCode);
        result = 31 * result + Short.hashCode(prefix);
        result = 31 * result + Short.hashCode(lineNum);
        return result;
    }


///////////////////////////////////////////////////////////////////////////////////////////
    // toString() overriding

    // returns the string representation of this EqualsPhone,
    // format is: "XXX-YYY-ZZZZ" where XXX is the area code, YYY is the
    // prefix, and ZZZZ is the line number. Each of the capital letters
    // represents a single decimal digit.  If any of the three parts of
    // this phone number is too small to fill up its field, the field is
    // padded with leading zeros.
    // For example, if the value of the line number is 123, the last
    // four characters of the string representation will be "0123".
    @Override public String toString() {
        return String.format("%03d-%03d-%04d", areaCode, prefix, lineNum);
    }

    // Clone method for class with no reference to mutable state
    @Override public EqualsPhone clone() {
        try {
            return (EqualsPhone) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // can't happen
        }
    }
}
