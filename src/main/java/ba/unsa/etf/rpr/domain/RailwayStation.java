package ba.unsa.etf.rpr.domain;

import java.util.Objects;
/**
 * Bean for railway station
 *@author Hana Catic*/
public class RailwayStation implements Idable {
    private int id;
    private String name;

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

    @Override
    public String toString() {
        return "RailwayStation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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
        return Objects.hash(id, name);
    }
}
