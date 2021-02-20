package others;

// difference between String, StringBuilder and StringBuffer
public class StrBuffStrBuild {

    // concatenates to String
    public static void concat1(String s1) {
        s1 = s1 + "text";
    }

    // concatenates to StringBuilder
    public static void concat2(StringBuilder s2) {
        s2.append("text");
    }

    public static void concat3(StringBuffer s3) {
        s3.append("text");
    }

    public static void main(String[] args) {
        String s1 = "Some"; // immutable
        concat1(s1); // s1 is not changed
        System.out.println("String: " + s1);

        StringBuilder s2 = new StringBuilder("Some"); // mutable
        concat2(s2); // s2 is changed
        System.out.println("StringBuilder: " + s2);

        StringBuffer s3 = new StringBuffer("Some"); // mutable and thread safe
        concat3(s3); // s3 is changed
        System.out.println("StringBuffer: " + s3);


        System.out.println();
        String str = "Hello";

        // conversion from String object to StringBuffer
        StringBuffer sbr = new StringBuffer(str);
        sbr.reverse();
        System.out.println(sbr);

        // conversion from String object to StringBuilder
        StringBuilder sbl = new StringBuilder(str);
        sbl.append(" , how are you?");
        System.out.println(sbl);
    }
}
