package homework4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KommivoyajerTests {
    @Test
    public void invertorTest() {
        Double[] array = {1.0, 2.0, 3.0, 4.0};
        Double[] array2 = {1.0, 4.0, 3.0, 2.0};
        Kommivoyajer.subsequenceInvertor(array, 1, 3);
        assertEquals(array2, array);
    }

}
