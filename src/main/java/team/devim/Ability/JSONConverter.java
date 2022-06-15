package team.devim.Ability;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import team.devim.Ability.POJO.AirportCodes;
import team.devim.Ability.POJO.AirportCodesInfo;
import team.devim.Ability.POJO.AirportInfo;

public class JSONConverter {
    private GsonBuilder builder = new GsonBuilder();
    private Gson gson = builder.create();

    public AirportInfo airportInfoFromJSON(String json) {
        AirportInfo airportInfo = gson.fromJson(json, AirportInfo.class);
        return airportInfo;
    }

    public String airportCodesToJSON(AirportCodes airportCodes) {
        String json = gson.toJson(airportCodes);
        return json;
    }

    public AirportCodesInfo airportCodesInfoFromJSON(String json) {
        AirportCodesInfo airportCodesInfo = gson.fromJson(json, AirportCodesInfo.class);
        return airportCodesInfo;
    }
}