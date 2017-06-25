package cc.ozgun.moviedb.model.mapper;

import cc.ozgun.moviedb.exception.InvalidDurationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Jackson custom deserializer to convert duration in seconds from hh:mm:ss format
 * <p>
 * Created by Özgün Ayaz on 6/25/2017. License: CC BY-SA 4.0
 */
public class DurationDeserializer extends JsonDeserializer<Long> {

    @Override
    public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        String input = jsonParser.getValueAsString();

        if (Pattern.compile("^\\d+:[0-5][0-9]:[0-5][0-9]$").matcher(input).matches()) {
            String[] e = input.split(":");
            return (Long.valueOf(e[0]) * 3600) + (Long.valueOf(e[1]) * 60) + Long.valueOf(e[2]);

        } else {
            throw new InvalidDurationException("Duration must be in format hh:mm:ss");
        }

    }
}
