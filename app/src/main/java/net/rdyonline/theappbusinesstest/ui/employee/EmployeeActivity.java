package net.rdyonline.theappbusinesstest.ui.employee;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import net.rdyonline.theappbusinesstest.R;
import net.rdyonline.theappbusinesstest.data.DataPersister;
import net.rdyonline.theappbusinesstest.data.DataProvider;
import net.rdyonline.theappbusinesstest.data.Employee;
import net.rdyonline.theappbusinesstest.data.employee.DefaultEmployee;
import net.rdyonline.theappbusinesstest.data.employee.EmployeeDataLocal;
import net.rdyonline.theappbusinesstest.data.web.EmployeeWebApi;
import net.rdyonline.theappbusinesstest.data.web.retrofit.ApiAdapter;
import net.rdyonline.theappbusinesstest.data.web.retrofit.EmployeeService;
import net.rdyonline.theappbusinesstest.data.web.retrofit.converter.TabEmployeePageConverter;

import java.util.List;


public class EmployeeActivity extends Activity {

    DataProvider<Employee> dataProvider;
    List<Employee> employees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataProvider = new DefaultEmployee(getResources());

        setContentView(R.layout.activity_employee);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // work out which data provider
        dataProvider = getDataProvider();
        loadData();
    }

    private DataProvider<Employee> getDataProvider() {
        if (isConnected()) {
            return getWebDataProvider();
        } else {
            return getLocalDataProvider();
        }
    }

    private DataProvider<Employee> getLocalDataProvider() {
        // not connected - is there cached data?
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        EmployeeDataLocal secondaryProvider = new EmployeeDataLocal(prefs);

        if (secondaryProvider.list() != null && secondaryProvider.list().size() > 0) {
            return secondaryProvider;
        } else {
            return new DefaultEmployee(getResources());
        }
    }

    private DataProvider<Employee> getWebDataProvider() {
        DataPersister<Employee> persister = new DataPersister<Employee>() {

            @Override
            public void saveData(List<Employee> data) {
                // when the web data has been retrieved, it should be saved for use later,
                // if there's no network connection when next checking
            }
        };

        TabEmployeePageConverter converter = new TabEmployeePageConverter();
        ApiAdapter apiAdapter = new ApiAdapter(converter);
        EmployeeService service = apiAdapter.getAdapter().create(EmployeeService.class);

        return new EmployeeWebApi(service, persister);
    }

    private void loadData() {
        new AsyncTask<Void, Void, List<Employee>>() {

            @Override
            protected List<Employee> doInBackground(Void... params) {
                return dataProvider.list();
            }

            @Override
            protected void onPostExecute(List<Employee> data) {
                employees = data;
                updateViews();
            }

        }.execute();

    }

    private void updateViews() {
        if (employees.size() == 1) {
            // load detail view
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, EmployeeDetailsFragment.newInstance(employees.get(0)))
                    .commit();

        } else if (employees.size() > 1) {
            // load list view
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, EmployeeListFragment.newInstance(employees))
                    .commit();

        }
    }

    /**
     * Without a network connection the app can't grab a new list of employees,
     * so it needs to know whether it needs to either use "cached" data,
     * or to load the default one if the network isn't available on first load
     *
     * @return true if either WiFi or mobile data is available
     */
    // TODO(benp) move to utils class
    private boolean isConnected() {
        ConnectivityManager connManager = (ConnectivityManager)
                getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return (null != wifi && wifi.isConnected())
                || (null != mobile && mobile.isConnected());
    }
}
