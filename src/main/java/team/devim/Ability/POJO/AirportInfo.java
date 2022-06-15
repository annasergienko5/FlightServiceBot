package team.devim.Ability.POJO;

import java.util.Objects;

public class AirportInfo {
    public String departure_count;
    public String avg_amount;
    public String passenger_count;


    public AirportInfo(){}

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this == o) {
            result = true;
        } else if (o == null || this.getClass() != o.getClass()){
            result = false;
        }
        AirportInfo airportInfo = (AirportInfo) o;
        if (this.passenger_count.equals(airportInfo.passenger_count) &&
                this.departure_count.equals(airportInfo.departure_count) &&
                this.avg_amount.equals(airportInfo.avg_amount)) {
            result = true;
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this);
    }
}