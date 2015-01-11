package net.rdyonline.theappbusinesstest.ui.employee;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.rdyonline.theappbusinesstest.R;
import net.rdyonline.theappbusinesstest.data.Employee;

import java.util.List;

/**
 * Created by rdy on 09/01/15.
 */
public class EmployeeListFragment extends Fragment {

    EmployeeListAdapter.OnEmployeeItemClickListener listener;
    List<Employee> employees;
    RecyclerView recyclerView;

    public static EmployeeListFragment newInstance(List<Employee> data,
                                                   EmployeeListAdapter
                                                           .OnEmployeeItemClickListener listener) {

        EmployeeListFragment fragment = new EmployeeListFragment();
        fragment.employees = data;
        fragment.listener = listener;

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_employee_list, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.employee_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new EmployeeListAdapter(employees, listener, getActivity()));

        return rootView;
    }

}
