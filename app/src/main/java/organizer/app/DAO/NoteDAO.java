package organizer.app.DAO;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import organizer.app.data.data.Note;

import java.util.List;

import organizer.app.data.data.Note;

@Dao
public interface NoteDAO {
    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getAll();

    @Query("SELECT * FROM notes WHERE id =:noteId")
    Note getNote(int noteId);

    @Insert
    public void insert(Note note);


    @Update
    public void update(Note note);

    @Delete
    public void delete(Note note);
}
