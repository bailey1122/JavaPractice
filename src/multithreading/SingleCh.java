//package multithreading;
//
//public class SingleCh {
//
//    // single-check idiom - can cause repeated initialization
//    private volatile FieldType field;
//
//    private FieldType getField() {
//        FieldType result = field;
//        if (result == null)
//            field = result = computeFieldValue();
//            return result;
//    }
//}
