package test.ap;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@AutoService(Processor.class)
@SupportedAnnotationTypes("test.ap.WriteTarget")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class Ap extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {

            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);

            try {
                JavaFileObject file = processingEnv.getFiler().createSourceFile("test.Target", annotatedElements.toArray(new Element[0]));
                try (PrintWriter w = new PrintWriter(file.openWriter())) {
                    w.println("package test; @lombok.experimental.SuperBuilder @lombok.Value public class Target {String test;}");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return true;
    }
}
