package functions.helper;

import classes.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.converter.TypedArgumentConverter;
import org.mockito.Mockito;

import java.util.Optional;

public class ToAccountMockArgumentConverter extends SimpleArgumentConverter{
    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        Assertions.assertEquals(Optional.class, targetType, "Can only convert to Account.class");
        if (source instanceof String){
            String s = (String) source;
            if (s.equalsIgnoreCase("mock"))
                return Optional.of(Mockito.mock(Account.class));
        }
        return Optional.empty();
    }
}
