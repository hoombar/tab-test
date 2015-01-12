package net.rdyonline.theappbusinesstest.ui.employee;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;

import net.rdyonline.theappbusinesstest.R;
import net.rdyonline.theappbusinesstest.data.DataProvider;
import net.rdyonline.theappbusinesstest.data.Employee;
import net.rdyonline.theappbusinesstest.data.EmployeeDataPersister;
import net.rdyonline.theappbusinesstest.data.employee.DefaultEmployee;
import net.rdyonline.theappbusinesstest.data.employee.EmployeeDataLocal;
import net.rdyonline.theappbusinesstest.data.web.EmployeeWebApi;
import net.rdyonline.theappbusinesstest.data.web.retrofit.ApiAdapter;
import net.rdyonline.theappbusinesstest.data.web.retrofit.EmployeeService;
import net.rdyonline.theappbusinesstest.data.web.retrofit.converter.TabEmployeePageConverter;

import java.util.List;

/***
 * The {@link EmployeeActivity} is intended to display a list of employees and show the details
 * of any employee selected
 */
public class EmployeeActivity extends Activity implements
        EmployeeListAdapter.OnEmployeeItemClickListener {

    // I would prefer to use Dagger to inject this - I could have also done it manually,
    // but inversion of control would have been overkill for an example test
    EmployeeDataPersister employeeDataPersister;
    // the dataProvider is swapped out based on the network state and local storage state
    DataProvider<Employee> dataProvider;
    List<Employee> employees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        employeeDataPersister = new EmployeeDataPersister(sharedPreferences);

        setContentView(R.layout.activity_employee);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // work out which data provider
        dataProvider = getDataProvider();
        loadData();
    }

    /*
     * Using the strategy pattern here. DataProvider can be swapped out for any type of data
     * provider. For this particular case, I'm using different data providers depending on the
     * state of the network connection
     */
    private DataProvider<Employee> getDataProvider() {
        if (isConnected()) {
            return getWebDataProvider();
        } else {
            return getLocalDataProvider();
        }
    }

    /****
     * If there's no network connection, a local data provider should be used.
     * There's an edge case here where they might not have downloaded any data yet - if this is
     * the case then it should default to showing someting that comes bundled with the app.
     *
     * @return A valid data provider for getting employees
     */
    private DataProvider<Employee> getLocalDataProvider() {
        // not connected - is there cached data?
        EmployeeDataLocal secondaryProvider = new EmployeeDataLocal(employeeDataPersister);

        if (secondaryProvider.list() != null && secondaryProvider.list().size() > 0) {
            return secondaryProvider;
        } else {
            return new DefaultEmployee(getResources());
        }
    }

    private DataProvider<Employee> getWebDataProvider() {
        TabEmployeePageConverter converter = new TabEmployeePageConverter();
        ApiAdapter apiAdapter = new ApiAdapter(converter);
        EmployeeService service = apiAdapter.getAdapter().create(EmployeeService.class);

        return new EmployeeWebApi(service, employeeDataPersister);
    }

    /***
     * At the moment, the data loading is done with a simple ASyncTask. For this app,
     * it seemed a sensible mechanism to use - I have used Publish/Subscribe (Observer pattern)
     * in the form of an event bus, but that seemed overkill for this.
     */
    private void loadData() {
        new AsyncTask<Void, Void, List<Employee>>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                setProgressBarIndeterminateVisibility(true);
            }

            @Override
            protected List<Employee> doInBackground(Void... params) {
                return dataProvider.list();
            }

            @Override
            protected void onPostExecute(List<Employee> data) {
                if (isFinishing()) {
                    // if the task runs for a long time, the activity might have been closed.
                    // This would potentially cause NullPointerException later on when trying to
                    // do things with context
                    return;
                }

                employees = data;
                updateViews();
                setProgressBarIndeterminateVisibility(false);
            }

        }.execute();

    }

    /**
     * There's two modes - you can either be viewing a list of employees or the details of an
     * employee
     */
    private void updateViews() {
        if (employees.size() == 1) {
            loadEmployeeDetails(employees.get(0), false);

        } else if (employees.size() > 1) {
            // load list view
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, EmployeeListFragment.newInstance(employees, this))
                    .commit();

        }
    }

    /***
     * Looks like we got an employee from somewhere, so need to update the UI by swapping out the
     * current fragment
     *
     * @param employee
     * @param addToBackStack true if you want back to take pop off the stack
     */
    private void loadEmployeeDetails(Employee employee, boolean addToBackStack) {
        // load detail view
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, EmployeeDetailsFragment.newInstance(employee));
        if (addToBackStack) {
            transaction.addToBackStack("detail");
        }

        transaction.commit();
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

    /**
     * Listen out for any presses on an employee item and show the pertinent team member
     *
     * @param employee
     */
    @Override
    public void employeeSelected(Employee employee) {
        loadEmployeeDetails(employee, true);
    }
}
