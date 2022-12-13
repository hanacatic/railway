package ba.unsa.etf.rpr.dao;

import java.util.List;

public interface Dao <Type>{
    Type add(Type item);
    Type update(Type item);
    Type getById(int id);
    void delete(int id);
    List<Type> getAll();
}
