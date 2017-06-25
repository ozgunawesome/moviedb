package cc.ozgun.moviedb.model.mapper;

import cc.ozgun.moviedb.exception.InvalidDurationException;
import com.fasterxml.jackson.core.JsonParser;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit test for duration deserializer, testing the regex et cetera
 * <p>
 * Created by Özgün Ayaz on 6/25/2017. License: CC BY-SA 4.0
 */
public class DurationDeserializerTest {

    private DurationDeserializer durationDeserializer = new DurationDeserializer();
    private JsonParser jsonParser = mock(JsonParser.class);

    @Test
    public void testCorrectString() throws IOException {
        when(jsonParser.getValueAsString()).thenReturn("01:28:00"); //5280 seconds
        assertEquals(Long.valueOf(5280), durationDeserializer.deserialize(jsonParser, null));
    }

    @Test
    public void testLongButStillOkayString() throws IOException {
        when(jsonParser.getValueAsString()).thenReturn("133:01:20"); //478880 seconds
        assertEquals(Long.valueOf(478880), durationDeserializer.deserialize(jsonParser, null));
    }

    @Test(expected = InvalidDurationException.class)
    public void testBotchedInputString() throws IOException {
        when(jsonParser.getValueAsString()).thenReturn("lelelelel"); //what the hell?
        durationDeserializer.deserialize(jsonParser, null);
    }
}
