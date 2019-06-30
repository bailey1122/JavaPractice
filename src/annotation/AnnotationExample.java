package annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;


// this is a simple example of how the annotation mechanism works
public class AnnotationExample {
    public static void main(String[] args) throws IllegalAccessException {
        AnnotationExample annotationExample = new AnnotationExample();
        Auto auto = new Auto();
        annotationExample.print(auto, auto.getClass());
    }

    void print(Object o, Class c) throws IllegalAccessError, IllegalAccessException {
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(Search.class)) {
                    System.out.println(field.get(o));
                }
            }
        }
    }
}


@Retention(RetentionPolicy.RUNTIME)
@interface Search {
    boolean value() default true;
}


class Auto {
    @Search //output only fields that are marked "Search"
    String tesla = "Tesla";
    @Search //output only fields that are marked "Search"
    String mercedes = "Mercedes-Benz";
    @Search //output only fields that are marked "Search"
    String volvo = "Volvo";

    int wheels = 4;
}
