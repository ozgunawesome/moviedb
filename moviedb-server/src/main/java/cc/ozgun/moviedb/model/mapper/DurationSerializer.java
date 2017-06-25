package cc.ozgun.moviedb.model.mapper;

import cc.ozgun.moviedb.exception.InvalidDurationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Jackson custom serializer to convert duration in seconds to hh:mm:ss format
 * <p>
 * Created by Özgün Ayaz on 6/25/2017. License: CC BY-SA 4.0
 */
public class DurationSerializer extends JsonSerializer<Long> {

    @Override
    public void serialize(Long aLong, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (aLong < 0) {
            throw new InvalidDurationException("Cannot serialize negative values");
        }

        jsonGenerator.writeObject(String.format("%02d:%02d:%02d", aLong / 3600, (aLong % 3600) / 60, aLong % 60));
    }
}
