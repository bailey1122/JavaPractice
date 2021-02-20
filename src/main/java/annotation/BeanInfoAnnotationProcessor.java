package annotation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.beans.Introspector;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

// This is a class which implement handler of annotations
// and analyzes annotations property @Property
@SupportedAnnotationTypes("sourceAnnotations.Property")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class BeanInfoAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement t : annotations) {
            Map<String, Property> props = new LinkedHashMap<>();
            String beanClassName = null;
            for (Element e : roundEnv.getElementsAnnotatedWith(t)) {
                String mname = e.getSimpleName().toString();
                String[] prefixes = { "get", "set", "is" };
                boolean found = false;
                for (int i = 0; !found && i <prefixes.length ; i++)
                    if (mname.startsWith(prefixes[i])) {
                        found = true;
                        int start = prefixes[i].length();
                        String name = Introspector.decapitalize(mname.substring(start));
                        props.put(name, e.getAnnotation(Property.class));
                    }

                    if (!found) processingEnv.getMessager().printMessage(
                            Diagnostic.Kind.ERROR, "@Property must be applied to getXxx," +
                            " setXxx, or Xxx method", e);
                    else if (beanClassName == null)
                        beanClassName = ((TypeElement) e.getEnclosingElement())
                                .getQualifiedName().toString();
                }
                try {
                    if (beanClassName != null) writeBeanInfoFile(
                            beanClassName, props);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return true;
        }
        // Write initial file's class of information about component JavaBeans
        // @param beanClassName Name's class of information about component JavaBeans
        // @param props Display of name's properties and their annotations

        private void writeBeanInfoFile(String beanClassName, Map<String,
                Property> props ) throws IOException {
            JavaFileObject sourceFile = processingEnv.getFiler().createSourceFile(
                    beanClassName + "BeanInfo");
            PrintWriter out = new PrintWriter(sourceFile.openWriter());
            int i = beanClassName.lastIndexOf(".");
            if (i > 0) {
                out.print("package ");
                out.print(beanClassName.substring(0, i));
                out.println(";");
            }
            out.print("public class");
            out.print(beanClassName.substring(i + 1));
            out.println("BeanInfo extends java.beans.SimpleBeanInfo");
            out.println("{");
            out.println(" public java.beans.PropertyDescriptor[]" +
                    "getPropertyDescriptors()");
            out.println("{");
            out.println(" try");
            out.println(" {");
            for (Map.Entry<String, Property> e : props.entrySet()) {
                out.print(" java.beans.PropertyDescriptor ");
                out.print(e.getKey());
                out.println("Descriptor");
                out.print(" = new java.beans.PropertyDescriptor(\"");
                out.print(e.getKey());
                out.print("\", ");
                out.print(beanClassName);
                out.print(".class);");
                String ed = e.getValue().editor().toString();
                if (!ed.equals("")) {
                    out.print(" ");
                    out.print(e.getKey());
                    out.print("Descriptor.setPropertyEditorClass(");
                    out.print(ed);
                    out.println(".class);");
                }
            }
            out.println(" return new java.beans.PropertyDescriptor[]");
            out.print(" {");
            boolean first = true;
            for (String p : props.keySet()) {
                if (first) first = false;
                else out.print(",");
                out.println();
                out.print(" ");
                out.print(p);
                out.print("Descriptor");
            }
            out.println();
            out.println(" };");
            out.println(" }");
            out.println(" catch (java.beans.IntrospectionException e");
            out.println(" {");
            out.println(" e.printStackTrace();");
            out.println("return null;");
            out.println(" }");
            out.println(" }");
            out.println("}");
            out.close();
    }
}
