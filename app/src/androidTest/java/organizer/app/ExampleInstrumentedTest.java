package organizer.app;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import localdatabase.MyDatabase;
import organizer.app.DAO.NoteDAO;
import organizer.app.Repositories.NoteRepository;
import organizer.app.data.data.Note;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        NoteDAO noteDao;
        LiveData<List<Note>> noteList;
        List<Note> list;
        List<Note> listrepo;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyDatabase db = MyDatabase.getInstance(appContext);
        LiveData<List<Note>> notes;
        //Note note3 = new Note(5, "aaaccxxx", "eeeeeeeeeeeeeeeeeeeeeee");
        Note note = new Note();
        //Note note2 = new Note(1, "start your note", "aecxcsdv");
        //db.noteDao().insert(note2);
        //db.noteDao().insert(note3);
        note = db.noteDao().getNote(5);
        NoteRepository nRepository;
        nRepository = new NoteRepository(appContext);
        //NoteViewModel noteViewModel;

        //listrepo = nRepository.getAllAsList();
        //list = db.noteDao().getAllAsList();

        assertNotNull(note.id);

    }

    @Test
    public void testRetrofit() {

    }
}
