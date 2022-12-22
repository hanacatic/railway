package ba.unsa.etf.rpr.domain;
/**
 * Interface - forces all POJO beans to have an ID field.
 * */
public interface Idable {
    void setId(int id);
    int getId();
}
