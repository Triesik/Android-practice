package organizer.app.clientAPI;

import java.util.List;

import organizer.app.data.data.Note;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("/photos")
    Call<List<Note>> getAllNotes();
}
