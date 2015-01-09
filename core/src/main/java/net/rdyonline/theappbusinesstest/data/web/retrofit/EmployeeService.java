package net.rdyonline.theappbusinesstest.data.web.retrofit;

import retrofit.http.GET;

/**
 * Created by rdy on 09/01/15.
 */
public interface EmployeeService {

    @GET("/our-team")
    public EmployeeWebPageWrapper getEmployeesFromWebsite();

    public class EmployeeWebPageWrapper {

    }

}
