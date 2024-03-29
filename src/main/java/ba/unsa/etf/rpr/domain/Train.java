package ba.unsa.etf.rpr.domain;

import java.sql.Date;
import java.util.Objects;
/**
 * Bean for train
 * @author Hana Catic
 * */
public class Train implements Idable {
    private int id;
    private String name;
    private Date dateBought;

    public Train() {
    }
    public Train(String name, Date dateBought) {
        this.name = name;
        this.dateBought = dateBought;
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

    public Date getDateBought() {
        return dateBought;
    }

    public void setDateBought(Date dateBought) {
        this.dateBought = dateBought;
    }

    @Override
    public String toString() {
        return this.name;
        /*return "Train{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateBought=" + dateBought +
                '}';*/
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return id == train.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateBought);
    }
}
