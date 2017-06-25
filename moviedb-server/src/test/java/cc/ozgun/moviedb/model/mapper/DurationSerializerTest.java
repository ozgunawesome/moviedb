package cc.ozgun.moviedb.model.mapper;

import cc.ozgun.moviedb.exception.InvalidDurationException;
import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Unit test for duration (long to hh:mm:ss)
 * <p>
 * Created by Özgün Ayaz on 6/25/2017. License: CC BY-SA 4.0
 */
public class DurationSerializerTest {

    private DurationSerializer durationSerializer = new DurationSerializer();
    private JsonGenerator jsonGenerator = mock(JsonGenerator.class);

    @Test
    public void testCorrectDuration() throws IOException {
        durationSerializer.serialize(5280L, jsonGenerator, null);
        verify(jsonGenerator).writeObject(eq("01:28:00"));
    }

    @Test(expected = InvalidDurationException.class)
    public void testBadInputString() throws IOException {
        durationSerializer.serialize(-1L, jsonGenerator, null);
    }

}
