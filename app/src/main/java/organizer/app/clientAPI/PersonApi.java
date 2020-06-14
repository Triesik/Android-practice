package organizer.app.clientAPI;

import java.util.List;

import organizer.app.data.data.Note;
import organizer.app.data.data.Person;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PersonApi {

    @GET("api/Person/")
    Call<List<Person>> getPeople();

}
