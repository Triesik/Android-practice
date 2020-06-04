package organizer.app.fragments;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import localdatabase.DatabaseHandler;
import organizer.app.Repositories.NoteRepository;
import organizer.app.data.data.Note;


public class NoteViewModel extends ViewModel {

    private NoteRepository nRepository;

    private LiveData<List<Note>> allNotes;

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
}
