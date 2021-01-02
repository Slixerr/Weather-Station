package application;

import java.time.Duration;
import javafx.util.StringConverter;

public class IntegerSecondsConverter extends StringConverter<Integer> {

    @Override
    public String toString(Integer object) {
        return object/60 + " minutos";
    }

    @Override
    public Integer fromString(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
