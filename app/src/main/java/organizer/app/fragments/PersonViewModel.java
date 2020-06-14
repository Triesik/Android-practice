package organizer.app.fragments;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.util.List;

import organizer.app.Repositories.PersonRepository;
import organizer.app.clientAPI.PersonApi;
import organizer.app.data.data.Person;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PersonViewModel extends ViewModel {

    private PersonRepository pRepository;

    private LiveData<List<Person>> allNotes;
    Person person;

    public PersonViewModel () {
        pRepository = new PersonRepository();
        allNotes = pRepository.getAllPeople();

    }

    public LiveData<List<Person>> getAllPeople() {
        return allNotes;
    }


}
