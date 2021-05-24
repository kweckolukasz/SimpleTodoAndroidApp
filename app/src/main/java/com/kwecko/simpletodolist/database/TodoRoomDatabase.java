package com.kwecko.simpletodolist.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.kwecko.simpletodolist.DAO.TodoDAO;
import com.kwecko.simpletodolist.model.Todo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

@Database(entities = Todo.class, version = 1, exportSchema = false)
public abstract class TodoRoomDatabase extends RoomDatabase {

    static Logger logger = Logger.getLogger(TodoRoomDatabase.class.getName());

    public abstract TodoDAO todoDAO();

    private static volatile TodoRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static TodoRoomDatabase getDatabase(final Context context) {
        logger.log(Level.INFO, "inside builder");
        if (INSTANCE == null) {
            synchronized (TodoRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodoRoomDatabase.class, "todo_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(()->{
                logger.log(Level.INFO, "inside DB Callback");
                TodoDAO todoDAO = INSTANCE.todoDAO();
                todoDAO.deleteAll();

                List<Todo> todos = Arrays.asList(
                        new Todo(0, "posprzątaj pokój", 123456789),
                        new Todo(1, "nakarm psa", 12345678),
                        new Todo(2, "zrób zakupy", 1234567),
                        new Todo(3, "odbierz oliwkę :)", 123456),
                        new Todo(4, "Pojedź do Karpacza i wyszlifuj wszystkie balkony", 123456),
                        new Todo(5, "12345678911234567892345678931234567894123456789512345678961234657897123456789", 123456)
                );

                for (Todo todo :
                        todos) {
                    todoDAO.insertTodo(todo);
                    logger.log(Level.INFO, "inserted: "+todo.getText());

                }


            });
        }
    };

}
