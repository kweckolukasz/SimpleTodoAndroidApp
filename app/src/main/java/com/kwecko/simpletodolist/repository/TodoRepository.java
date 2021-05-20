package com.kwecko.simpletodolist.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.kwecko.simpletodolist.DAO.TodoDAO;
import com.kwecko.simpletodolist.database.TodoRoomDatabase;
import com.kwecko.simpletodolist.model.Todo;

import java.util.List;

public class TodoRepository {

    private TodoDAO todoDAO;
    private LiveData<List<Todo>> getAllTodos;

    public TodoRepository(Application application){
        TodoRoomDatabase todoRoomDatabase = TodoRoomDatabase.getDatabase(application);
        todoDAO = todoRoomDatabase.todoDAO();
        getAllTodos = todoDAO.allTodosByDate();
    }

    public LiveData<List<Todo>> getGetAllTodosByDate() {
        return getAllTodos;
    }

    public void insert(Todo todo){
        TodoRoomDatabase.databaseWriteExecutor.execute(() -> todoDAO.insertTodo(todo));
    }
}
