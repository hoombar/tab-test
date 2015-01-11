package net.rdyonline.theappbusinesstest.data.employee;

import android.content.SharedPreferences;

import net.rdyonline.theappbusinesstest.data.DataPersister;
import net.rdyonline.theappbusinesstest.data.DataProvider;
import net.rdyonline.theappbusinesstest.data.Employee;
import net.rdyonline.theappbusinesstest.data.web.EmployeeWebApi;

import java.util.List;

/**
 * Created by rdy on 11/01/15.
 */
public class EmployeeDataLocal implements DataProvider<Employee> {

    private SharedPreferences preferences;

    public EmployeeDataLocal(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public List<Employee> list() {
        return null;
    }
}
