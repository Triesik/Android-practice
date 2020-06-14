package organizer.app.clientAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("http://192.168.1.184:53026/")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static PersonApi personApi = retrofit.create(PersonApi.class);

    public static PersonApi getPersonApi() {
        return personApi;
    }
}