package net.rdyonline.theappbusinesstest.data;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * To persist the data, I thought it would be a nice solution to exploit SharedPreferences and
 * serialization/deserialization of POJO to JSON and visa-versa.
 *
 * For the purposes of demonstration, I think it's quite a nice solution, however,
 * I'm aware that if you were to implement this, you would want to ensure that the objects aren't
 * going to change as deserialization across different versions of objects can be problematic
 *
 * Created by rdy on 11/01/15.
 */
public class EmployeeDataPersister implements DataPersister<Employee> {

    private SharedPreferences sharedPreferences;
    private final String PREF_KEY = "saved_employees";

    public EmployeeDataPersister(SharedPreferences preferences) {
        this.sharedPreferences = preferences;
    }

    @Override
    public void saveData(List<Employee> data) {
        String serialized = serializeData(data);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_KEY, serialized);
        editor.commit();
    }

    private String serializeData(List<Employee> data) {
        Gson gson = new Gson();

        return gson.toJson(data);
    }

    public List<Employee> load() {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<ArrayList<Employee>>(){}.getType();
        return gson.fromJson(sharedPreferences.getString(PREF_KEY, null), collectionType);
    }
}
