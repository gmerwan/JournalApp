package journalapp.udacity.alc.journalapp.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import journalapp.udacity.alc.journalapp.room.entity.Diary;

@Dao
public interface DiaryDao {

    @Query("SELECT * FROM diary where user = :user")
    List<Diary> getAll(String user);

    @Query("SELECT * FROM diary where user = :user AND title LIKE :title")
    List<Diary> findByTitle(String user, String title);

    @Query("SELECT * FROM diary where user = :user AND id = :id")
    Diary findById(String user, int id);

    @Query("SELECT COUNT(*) FROM diary where user = :user")
    int countDiaries(String user);

    @Insert
    void insertAll(Diary... diaries);

    @Insert
    void insert(Diary diary);

    @Delete
    void delete(Diary diary);
}
