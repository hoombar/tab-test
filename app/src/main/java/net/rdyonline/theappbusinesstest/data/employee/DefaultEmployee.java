package net.rdyonline.theappbusinesstest.data.employee;

import android.content.res.Resources;

import net.rdyonline.theappbusinesstest.R;
import net.rdyonline.theappbusinesstest.data.DataProvider;
import net.rdyonline.theappbusinesstest.data.Employee;
import net.rdyonline.theappbusinesstest.data.TabEmployee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rdy on 11/01/15.
 */
public class DefaultEmployee implements DataProvider<Employee> {

    private Resources resources;

    public DefaultEmployee(Resources res) {
        this.resources = res;
    }

    @Override
    public List<Employee> list() {
        List<Employee> defaults = new ArrayList<Employee>();

        String name = resources.getString(R.string.default_employee_name);
        String title = resources.getString(R.string.default_employee_role);
        String image = resources.getString(R.string.default_employee_image);
        String description = resources.getString(R.string.default_employee_description);


        defaults.add(new TabEmployee(name, title, image, description));

        return defaults;
    }
}
