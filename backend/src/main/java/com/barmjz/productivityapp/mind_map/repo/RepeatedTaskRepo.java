package com.barmjz.productivityapp.mind_map.repo;

import com.barmjz.productivityapp.mind_map.model.RepeatedTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface RepeatedTaskRepo extends JpaRepository<RepeatedTask, Long> {
    Optional<List<RepeatedTask>> getAllByUserId(Long userId);
    Optional<List<RepeatedTask>> getAllByUserIdAndSundayEqualsAndLastRemovalDateNot(Long user_id, boolean sunday, Date date);
    Optional<List<RepeatedTask>> getAllByUserIdAndMondayEqualsAndLastRemovalDateNot(Long user_id, boolean monday, Date date);
    Optional<List<RepeatedTask>> getAllByUserIdAndTuesdayEqualsAndLastRemovalDateNot(Long user_id, boolean tuesday, Date date);
    Optional<List<RepeatedTask>> getAllByUserIdAndWednesdayEqualsAndLastRemovalDateNot(Long user_id, boolean wednesday, Date date);
    Optional<List<RepeatedTask>> getAllByUserIdAndThursdayEqualsAndLastRemovalDateNot(Long user_id, boolean thursday, Date date);
    Optional<List<RepeatedTask>> getAllByUserIdAndFridayEqualsAndLastRemovalDateNot(Long user_id, boolean friday, Date date);
    Optional<List<RepeatedTask>> getAllByUserIdAndSaturdayEqualsAndLastRemovalDateNot(Long user_id, boolean saturday, Date date);

    @Modifying
    @Query("UPDATE RepeatedTask r SET r.lastRemovalDate = ?2 WHERE r.id = ?1")
    void changeRemovalDate(Long taskId, Date date);

}
