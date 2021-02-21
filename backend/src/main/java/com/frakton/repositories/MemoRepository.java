package com.frakton.repositories;

import com.frakton.entities.Memo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MemoRepository extends CrudRepository<Memo, Long> {

    @Transactional
    @Modifying
    @Query("update Memo m set m.text = :text where m.id = :id")
    void updateMemo(@Param(value = "id") long id, @Param(value = "text") String text);
}