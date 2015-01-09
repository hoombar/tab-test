package net.rdyonline.theappbusinesstest.data.web;

import net.rdyonline.theappbusinesstest.data.web.retrofit.ApiAdapter;
import net.rdyonline.theappbusinesstest.data.web.retrofit.EmployeeService;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by rdy on 09/01/15.
 */
public class EmployeeWebApiTest {

    EmployeeService employeeService = mock(EmployeeService.class);
    EmployeeService.EmployeeWebPageWrapper employeeWebPageWrapper = mock(EmployeeService
            .EmployeeWebPageWrapper.class);
    EmployeeWebApi sut = new EmployeeWebApi(employeeService);

    @Before
    public void setup() {
        // TODO(benp): should add some wrapper objects that will parse employees
    }

    @Test
    public void shouldReturnEmployeeList() {
        when(employeeService.getEmployeesFromWebsite()).thenReturn(employeeWebPageWrapper);


        sut.list();
    }

}
