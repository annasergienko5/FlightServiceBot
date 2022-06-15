package team.devim.Ability;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import team.devim.Ability.POJO.AirportInfo;

public class UsersServiceTest {

    @Test
    public void ConverterFromJSONPositive() {
        String json = "{\"departure_count\":\"3921\",\"avg_amount\":\"24238.168506234345\",\"passenger_count\":\"326979\"}";
        JSONConverter JSONConverter = new JSONConverter();
        AirportInfo expected = JSONConverter.airportInfoFromJSON(json);

        AirportInfo actual = new AirportInfo();
        actual.departure_count = "3921";
        actual.avg_amount = "24238.168506234345";
        actual.passenger_count = "326979";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void ConverterFromJSONNegative() {
        String json = "{\"departure_count\":\"3921\",\"avg_amount\":\"24238.168506234345\",\"passenger_count\":\"326979\"}";
        JSONConverter JSONConverter = new JSONConverter();
        AirportInfo expected = JSONConverter.airportInfoFromJSON(json);

        AirportInfo actual = new AirportInfo();
        actual.departure_count = "555";
        actual.avg_amount = "24238.168506234345";
        actual.passenger_count = "326979";

        Assertions.assertNotEquals(expected, actual);
    }

    @Test
    public void ConverterFromJSONNull() {
        String json = "null";
        JSONConverter JSONConverter = new JSONConverter();
        AirportInfo expected = JSONConverter.airportInfoFromJSON(json);

        Assertions.assertNull(expected);
    }
}