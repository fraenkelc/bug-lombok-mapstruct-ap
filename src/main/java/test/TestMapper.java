package test;

import org.mapstruct.Mapper;
import test.ap.WriteTarget;

@Mapper
@WriteTarget
public interface TestMapper {
    NoApTarget thisWorks(Source source);

    Target thisBreaks(Source source);

}
