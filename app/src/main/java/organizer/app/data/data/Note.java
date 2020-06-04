package organizer.app.data.data;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String noteName;
    public String content;
    public int ownerId;

    @Ignore
    public Note(int ownerId, String content, String noteName) {
        this.content = content;
        this.ownerId = ownerId;
        this.noteName = noteName;
    }

    public Note() {

    }

    public void setContent(String content) {

        this.content = content;
    }


    public String getNoteContent() {

        return content;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setOwnerId(int id) {

        this.id = id;
    }

    public int getOwnerId() {

        return id;
    }

    public void setNoteName(String name) {

        this.noteName = name;
    }

    public String getNoteName() {
        return noteName;
    }

}
