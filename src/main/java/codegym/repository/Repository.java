package codegym.repository;

import java.util.List;

public interface Repository<T> {
    List<T> findAll();

    T findById(Long id);

    T save(T model);

    void delete(Long id);

}
