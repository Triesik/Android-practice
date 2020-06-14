package organizer.app.Repositories;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import organizer.app.clientAPI.PersonApi;
import organizer.app.clientAPI.RetrofitClient;
import organizer.app.data.data.Person;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonRepository {

    MutableLiveData<List<Person>> allPeople;

    private LiveData<Person> note;
    public PersonRepository() {
        allPeople = new MutableLiveData<>();
        getAll();

    }


    public LiveData<List<Person>> getAllPeople() {
        //getAll();
        return allPeople;
    }

    private void getAll() {
        PersonApi personApi = RetrofitClient.getPersonApi();
        Call<List<Person>> call = personApi.getPeople();
        List<Person> peoples = new ArrayList<Person>();
        Person person1 = new Person("99", "Loading data", "", "");
        peoples.add(person1);
        allPeople.setValue(peoples);
        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                if (response.code() == 200) {
                    allPeople.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }

}
