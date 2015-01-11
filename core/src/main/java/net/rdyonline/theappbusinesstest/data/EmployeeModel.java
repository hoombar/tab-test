package net.rdyonline.theappbusinesstest.data;

import net.rdyonline.theappbusinesstest.data.web.EmployeeWebApi;

import java.util.List;

/**
 * Created by rdy on 09/01/15.
 */
public class EmployeeModel implements Model<Employee> {

    EmployeeWebApi webApi;

    public EmployeeModel(EmployeeWebApi webApi) {
        this.webApi = webApi;
    }

    public List<Employee> listAll() {
        if (webApi == null) return null;

        return webApi.list();
    }
}
