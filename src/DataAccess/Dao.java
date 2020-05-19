package DataAccess;
import java.sql.SQLException;
import java.util.*;

public interface Dao <T> {

    LinkedList<T> get(List<T> keys);

    List<T> getAll(T collName, T filter);

    void save(List<T> strings) throws SQLException;

    void update(T t, List<T> string);

    void delete(List<T> strings);
}

