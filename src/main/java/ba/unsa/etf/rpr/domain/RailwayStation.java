package ba.unsa.etf.rpr.domain;

import java.util.Objects;
/**
 * Bean for railway station
 * @author Hana Catic*/
public class RailwayStation implements Idable {
    private int id;
    private String name;
    private String address;
    private String city;
    private String country;

    public RailwayStation() {
    }

    public RailwayStation(String name, String address, String city, String country) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.city +", " + this.country;
        /*return "RailwayStation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adress='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';*/
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RailwayStation that = (RailwayStation) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, city, country);
    }
}
