package com.kwecko.simpletodolist.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.kwecko.simpletodolist.model.Todo;

import java.util.List;

@Dao
public interface TodoDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTodo(Todo todo);

    @Update
    void updateTodo(Todo todo);

    @Delete
    void deleteTodo(Todo todo);

    @Query("SELECT * FROM todo ORDER BY date")
    LiveData<List<Todo>> allTodosByDate();

    @Query("DELETE FROM todo")
    void deleteAll();
}
