package net.rdyonline.theappbusinesstest.ui.employee;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.rdyonline.theappbusinesstest.R;
import net.rdyonline.theappbusinesstest.data.DataPersister;
import net.rdyonline.theappbusinesstest.data.Employee;
import net.rdyonline.theappbusinesstest.data.web.EmployeeWebApi;
import net.rdyonline.theappbusinesstest.data.web.retrofit.ApiAdapter;
import net.rdyonline.theappbusinesstest.data.web.retrofit.EmployeeService;
import net.rdyonline.theappbusinesstest.data.web.retrofit.converter.TabEmployeePageConverter;

import java.util.List;

/**
 * Created by rdy on 09/01/15.
 */
public class EmployeeDetailsFragment extends Fragment {

    Employee data;
    TextView name;
    TextView role;
    TextView description;
    ImageView avatar;

    public static EmployeeDetailsFragment newInstance(Employee data) {
        EmployeeDetailsFragment fragment = new EmployeeDetailsFragment();
        fragment.data = data;

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_employee_details, container, false);


        name = (TextView) rootView.findViewById(R.id.txt_name);
        role = (TextView) rootView.findViewById(R.id.txt_role);
        description = (TextView) rootView.findViewById(R.id.txt_description);
        avatar = (ImageView) rootView.findViewById(R.id.img_avatar);

        bindEmployeeData();

        return rootView;
    }

    private void bindEmployeeData() {
        name.setText(data.getName());
        role.setText(data.getRole());
        description.setText(data.getDescription());
        Picasso.with(getActivity()).load(data.getImage()).error(R.drawable.ic_launcher).into(avatar);
    }
}
