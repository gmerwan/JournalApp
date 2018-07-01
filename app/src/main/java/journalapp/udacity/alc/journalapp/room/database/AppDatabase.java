package journalapp.udacity.alc.journalapp.room.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import journalapp.udacity.alc.journalapp.room.dao.DiaryDao;
import journalapp.udacity.alc.journalapp.room.entity.Diary;

@Database(entities = {Diary.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DiaryDao diaryDao();

}
