package organizer.app.Repositories;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import localdatabase.MyDatabase;
import organizer.app.DAO.NoteDAO;
import organizer.app.data.data.Note;

import android.os.AsyncTask;

public class NoteRepository {
    private NoteDAO noteDao;
    private LiveData<List<Note>> allNotes;
    private LiveData<Note> note;
    public NoteRepository(Context context) {
        MyDatabase database = MyDatabase.getInstance(context);
        noteDao = database.noteDao();
        allNotes = noteDao.getAll();

    }
    public void insert(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }
    public void update(Note note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }
    public void delete(Note note) {
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public Note getNote(int id)
    {
        return noteDao.getNote(id);
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDAO noteDao;
        private InsertNoteAsyncTask(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }
    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDAO noteDao;
        private UpdateNoteAsyncTask(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }
    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDAO noteDao;
        private DeleteNoteAsyncTask(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class GetNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDAO noteDao;
        private GetNoteAsyncTask(NoteDAO noteDao) {this.noteDao = noteDao;}

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.getNote(notes[0].getId());
            return null;
        }
    }

}



