# Reproducer for lombok+mapstruct issue

This repro reproduces an issue that occurs if lombok runs in multiple rounds (e.g. when another AP writes a file that
contains lombok annotations). In such cases lombok will tell mapstruct too early that its done with its work.

Here's the generated mapper. the mapper `thisBreaks` should look like the `thisWorks`

```java 
package test;

import javax.annotation.processing.Generated;
import test.NoApTarget.NoApTargetBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-18T22:42:33+0100",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 11.0.14.1 (Eclipse Adoptium)"
)
public class TestMapperImpl implements TestMapper {

    @Override
    public NoApTarget thisWorks(Source source) {
        if ( source == null ) {
            return null;
        }

        NoApTargetBuilder<?, ?> noApTarget = NoApTarget.builder();

        noApTarget.test( source.getTest() );

        return noApTarget.build();
    }

    @Override
    public Target thisBreaks(Source source) {
        if ( source == null ) {
            return null;
        }

        Target target = new Target();

        return target;
    }
}

```