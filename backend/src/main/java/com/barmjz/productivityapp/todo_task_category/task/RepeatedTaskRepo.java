package com.barmjz.productivityapp.todo_task_category.task;
import com.barmjz.productivityapp.todo_task_category.category.Category;
import com.barmjz.productivityapp.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface RepeatedTaskRepo extends JpaRepository<RepeatedTask, Long> {
    Optional<List<RepeatedTask>> getAllByUserId(Long userId);

    Optional<List<RepeatedTask>> getAllByCategory(Category category);

    Optional<List<RepeatedTask>> getAllByUserIdAndSundayEquals(Long user_id, boolean sunday);

    @Query("SELECT r FROM RepeatedTask r WHERE r.user = ?1 AND r.sunday = ?2 AND (r.lastRemovalDate IS NUll OR (r.lastRemovalDate <> ?3))")
    Optional<List<RepeatedTask>> getAllByUserAndSundayEqualsAndLastRemovalDateNot(User user, boolean sunday, Date date);

    @Query("SELECT r FROM RepeatedTask r WHERE r.user = ?1 AND r.monday = ?2 AND (r.lastRemovalDate IS NUll OR (r.lastRemovalDate <> ?3))")
    Optional<List<RepeatedTask>> getAllByUserAndMondayEqualsAndLastRemovalDateNot(User user, boolean monday, Date date);
    @Query("SELECT r FROM RepeatedTask r WHERE r.user = ?1 AND r.tuesday = ?2 AND (r.lastRemovalDate IS NUll OR (r.lastRemovalDate <> ?3))")

    Optional<List<RepeatedTask>> getAllByUserAndTuesdayEqualsAndLastRemovalDateNot(User user, boolean tuesday, Date date);
    @Query("SELECT r FROM RepeatedTask r WHERE r.user = ?1 AND r.wednesday = ?2 AND (r.lastRemovalDate IS NUll OR (r.lastRemovalDate <> ?3))")

    Optional<List<RepeatedTask>> getAllByUserAndWednesdayEqualsAndLastRemovalDateNot(User user, boolean wednesday, Date date);

    @Query("SELECT r FROM RepeatedTask r WHERE r.user = ?1 AND r.thursday = ?2 AND (r.lastRemovalDate IS NUll OR (r.lastRemovalDate <> ?3))")

    Optional<List<RepeatedTask>> getAllByUserAndThursdayEqualsAndLastRemovalDateNot(User user, boolean thursday, Date date);

    @Query("SELECT r FROM RepeatedTask r WHERE r.user = ?1 AND r.friday = ?2 AND (r.lastRemovalDate IS NUll OR (r.lastRemovalDate <> ?3))")
    Optional<List<RepeatedTask>> getAllByUserAndFridayEqualsAndLastRemovalDateNot(User user, boolean friday, Date date);

    @Query("SELECT r FROM RepeatedTask r WHERE r.user = ?1 AND r.saturday = ?2 AND (r.lastRemovalDate IS NUll OR (r.lastRemovalDate <> ?3))")

    Optional<List<RepeatedTask>> getAllByUserAndSaturdayEqualsAndLastRemovalDateNot(User user, boolean saturday, Date date);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE RepeatedTask r SET r.lastRemovalDate = ?2 WHERE r.id = ?1")
    void changeRemovalDate(Long taskId, Date date);

    Optional<RepeatedTask> getByCreationDate(Date creationDate);

}
