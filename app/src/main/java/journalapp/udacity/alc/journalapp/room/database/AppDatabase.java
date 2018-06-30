package journalapp.udacity.alc.journalapp.room.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import journalapp.udacity.alc.journalapp.room.dao.DiaryDao;
import journalapp.udacity.alc.journalapp.room.entity.Diary;

@Database(entities = {Diary.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

//    private static AppDatabase INSTANCE;

    public abstract DiaryDao diaryDao();

//    public static AppDatabase getAppDatabase(Context context) {
//        if (INSTANCE == null) {
//            INSTANCE =
//                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "diary-database")
//                            // allow queries on the main thread.
//                            // Don't do this on a real app! See PersistenceBasicSample for an example.
//                            .allowMainThreadQueries()
//                            .build();
//        }
//        return INSTANCE;
//    }
//
//    public static void destroyInstance() {
//        INSTANCE = null;
//    }
}
