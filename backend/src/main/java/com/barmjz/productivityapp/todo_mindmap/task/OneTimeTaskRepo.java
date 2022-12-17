package com.barmjz.productivityapp.todo_mindmap.task;

import com.barmjz.productivityapp.todo_mindmap.category.Category;
import com.barmjz.productivityapp.todo_mindmap.task.OneTimeTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OneTimeTaskRepo extends JpaRepository<OneTimeTask, Long> {
    Optional<List<OneTimeTask>> getAllByUserId(Long userId);
    Optional<List<OneTimeTask>> getAllByCategory(Category category);
    Optional<List<OneTimeTask>> getAllByUserIdAndTodoEquals(Long userId, boolean todo);

    Optional<List<OneTimeTask>> getAllByUserIdAndTodoEqualsOrderByDueDate(Long userId, boolean todo);


    Optional<List<OneTimeTask>> getTop10ByUserIdAndCompletionDateNotNullOrderByCompletionDateDesc(Long userId);

    @Modifying
    @Query("UPDATE OneTimeTask t SET t.completionDate = ?2 WHERE t.id = ?1")
    void markTaskAsDone(Long taskId, Date currentDate);

    @Modifying
    @Query("UPDATE OneTimeTask t SET t.completionDate = null WHERE t.id = ?1")
    void unMarkTaskAsDone(Long taskId);

    @Modifying
    @Query("UPDATE OneTimeTask t SET t.todo = true WHERE t.id = ?1")
    void addOnetimeTask(Long taskId);

    Optional<OneTimeTask> getByCreationDate(Date creationDate);
}
