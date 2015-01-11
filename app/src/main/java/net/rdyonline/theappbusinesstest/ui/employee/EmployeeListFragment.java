package net.rdyonline.theappbusinesstest.ui.employee;

import android.app.Fragment;

import net.rdyonline.theappbusinesstest.data.Employee;

import java.util.List;

/**
 * Created by rdy on 09/01/15.
 */
public class EmployeeListFragment extends Fragment {

    List<Employee> employees;

    public static EmployeeListFragment newInstance(List<Employee> data) {
        EmployeeListFragment fragment = new EmployeeListFragment();
        fragment.employees = data;

        return fragment;
    }

}
