package ru.pavlenty.roomexample;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    Flowable<List<Task>> getAll();

    @Insert
    Completable insert(Task task);

    @Delete
    Completable delete(Task task);

    @Update
    Completable update(Task task);

}