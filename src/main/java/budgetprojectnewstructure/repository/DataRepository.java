package budgetprojectnewstructure.repository;


import budgetprojectnewstructure.domain.DataEntity;

import java.util.List;

public interface DataRepository<T extends DataEntity> {
    List<T> findAll();
    T findById(String id);
    void save(T entity);
    void delete(T entity);
}
