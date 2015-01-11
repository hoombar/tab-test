package net.rdyonline.theappbusinesstest.ui.employee;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.rdyonline.theappbusinesstest.R;
import net.rdyonline.theappbusinesstest.data.web.EmployeeWebApi;
import net.rdyonline.theappbusinesstest.data.web.retrofit.ApiAdapter;
import net.rdyonline.theappbusinesstest.data.web.retrofit.EmployeeService;
import net.rdyonline.theappbusinesstest.data.web.retrofit.converter.TabEmployeePageConverter;

/**
 * Created by rdy on 09/01/15.
 */
public class EmployeeDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_employee_details, container, false);

        rootView.findViewById(R.id.tmp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TabEmployeePageConverter converter = new TabEmployeePageConverter();
                ApiAdapter apiAdapter = new ApiAdapter(converter);
                EmployeeService service = apiAdapter.getAdapter().create(EmployeeService.class);

                final EmployeeWebApi api = new EmployeeWebApi(service);

                new AsyncTask<Void, Void, Void>() {


                    @Override
                    protected Void doInBackground(Void... params) {
                        api.list();

                        return null;
                    }

                }.execute();

            }
        });

        return rootView;
    }
}
