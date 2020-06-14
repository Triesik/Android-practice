package organizer.app.fragments;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import organizer.app.Repositories.NoteRepository;
import organizer.app.data.data.Note;


public class NoteViewModel extends ViewModel {

    private NoteRepository nRepository;

    private LiveData<List<Note>> allNotes;
    Note note;

    public NoteViewModel (Context context) {
        nRepository = new NoteRepository(context);
        allNotes = nRepository.getAllNotes();

    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void insert(Note note) {
        nRepository.insert(note);
    }
    public void update(Note note) { nRepository.update(note); }
    public Note getNote (int id) {return nRepository.getNote(id); }
}
