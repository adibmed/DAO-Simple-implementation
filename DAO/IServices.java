package tps.tp7.DAO;

import java.util.Optional;
import java.util.Vector;

public interface IServices<T> {

    public void add(T t);

    public void remove(String email);

    public Vector<T> getAll();

    public Optional<T> find(String email);

    public void update(T t, String email);
}

