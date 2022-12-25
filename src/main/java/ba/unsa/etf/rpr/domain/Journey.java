package ba.unsa.etf.rpr.domain;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;
/**
 * Bean for journey
 * @autor Hana Catic
 * */
public class Journey implements Idable {
    private int id;
    private Train train;
    private RailwayStation departureStationId;
    private RailwayStation arrivalStationId;
    private Date departureDate;
    private Date arrivalDate;
    private Time departureTime;
    private Time arrivalTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public RailwayStation getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(RailwayStation departureStationId) {
        this.departureStationId = departureStationId;
    }

    public RailwayStation getArrivalStationId() {
        return arrivalStationId;
    }

    public void setArrivalStationId(RailwayStation arrivalStationId) {
        this.arrivalStationId = arrivalStationId;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "Journey{" +
                "id=" + id +
                ", train=" + train +
                ", departureStationId=" + departureStationId +
                ", arrivalStationId=" + arrivalStationId +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journey journey = (Journey) o;
        return id == journey.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, train, departureStationId, arrivalStationId, departureDate, arrivalDate, departureTime, arrivalTime);
    }
}
