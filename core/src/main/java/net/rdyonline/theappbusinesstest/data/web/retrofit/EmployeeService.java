package net.rdyonline.theappbusinesstest.data.web.retrofit;

import net.rdyonline.theappbusinesstest.data.Employee;

import java.util.ArrayList;
import java.util.List;

import retrofit.http.GET;

/**
 * Created by rdy on 09/01/15.
 */
public interface EmployeeService {

    @GET("/our-team")
    public EmployeeWebPageWrapper getEmployeesFromWebsite();

    public class EmployeeWebPageWrapper {
        public List<Employee> employees = new ArrayList<Employee>();
    }

}
