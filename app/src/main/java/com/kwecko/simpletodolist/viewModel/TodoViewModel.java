package com.kwecko.simpletodolist.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kwecko.simpletodolist.model.Todo;
import com.kwecko.simpletodolist.repository.TodoRepository;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {

    private TodoRepository repository;
    private LiveData<List<Todo>> allTodosByDate;

    public TodoViewModel(@NonNull Application application) {
        super(application);
        repository = new TodoRepository(application);
        allTodosByDate = repository.getGetAllTodosByDate();
    }

    public LiveData<List<Todo>> getAllTodosByDate() {
        return allTodosByDate;
    }

    public void insertTodo(Todo todo){
        repository.insert(todo);
    }
}
