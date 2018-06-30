package journalapp.udacity.alc.journalapp.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "diary")
public class Diary {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "user")
    private String user;

    @ColumnInfo(name = "title")
    private String title;

//    @ColumnInfo(name = "feeling")
//    private String feeling;

    @ColumnInfo(name = "content")
    private String content;

    @ColumnInfo(name = "date")
    private long date;

    public Diary(String user, String title, String content, long date) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.date = date;
    }

//    public Diary(String user, String title, String feeling, String content, String date) {
//        this.user = user;
//        this.title = title;
//        this.feeling = feeling;
//        this.content = content;
//        this.date = date;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

//    public String getFeeling() {
//        return feeling;
//    }
//
//    public void setFeeling(String feeling) {
//        this.feeling = feeling;
//    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
