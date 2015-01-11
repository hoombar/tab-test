package net.rdyonline.theappbusinesstest.data;

import net.rdyonline.theappbusinesstest.data.web.EmployeeWebApi;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by rdy on 09/01/15.
 */
public class EmployeeModelTest {

    EmployeeWebApi webApi = mock(EmployeeWebApi.class);
    EmployeeModel sut = new EmployeeModel(webApi);

    List<Employee> employees = new ArrayList<Employee>();
    Employee EMPLOYEE_1 = mock(Employee.class);
    Employee EMPLOYEE_2 = mock(Employee.class);
    Employee EMPLOYEE_3 = mock(Employee.class);
    Employee EMPLOYEE_4 = mock(Employee.class);

    @Before
    public void setup() {
        employees.add(EMPLOYEE_1);
        employees.add(EMPLOYEE_2);
        employees.add(EMPLOYEE_3);
        employees.add(EMPLOYEE_4);

        when(webApi.list()).thenReturn(employees);
    }

    @Test
    public void shouldListMultipleEmployees() {
        List<Employee> sutEmployees = sut.listAll();
        assertThat(sutEmployees).contains(EMPLOYEE_1, EMPLOYEE_2, EMPLOYEE_3, EMPLOYEE_4);
    }

    @Test
    public void shouldReturnNullIfNoEmployeeAvailable() {
        when(webApi.list()).thenReturn(null);
        assertThat(sut.listAll()).isNull();
    }



}
