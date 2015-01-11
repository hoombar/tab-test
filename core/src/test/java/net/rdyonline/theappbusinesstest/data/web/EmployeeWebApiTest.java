package net.rdyonline.theappbusinesstest.data.web;

import net.rdyonline.theappbusinesstest.data.DataPersister;
import net.rdyonline.theappbusinesstest.data.Employee;
import net.rdyonline.theappbusinesstest.data.web.retrofit.ApiAdapter;
import net.rdyonline.theappbusinesstest.data.web.retrofit.EmployeeService;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Please note that this isn't 100 percent test coverage, I'm just demonstrating a few techniques
 * and ways that I am familiar with while writing unit tests. Because I didn't intent to have
 * 100% coverage (I preferred to get all functionality done), I didn't strictly follow the
 * given/when/then approach of TDD
 *
 * Created by rdy on 09/01/15.
 */
public class EmployeeWebApiTest {

    DataPersister<Employee> persister = mock(DataPersister.class);
    EmployeeService employeeService = mock(EmployeeService.class);

    EmployeeWebApi sut;

    Employee EMPLOYEE_ONE = mock(Employee.class);
    Employee EMPLOYEE_TWO = mock(Employee.class);

    List<Employee> employees;

    @Before
    public void setup() {
        sut = spy(new EmployeeWebApi(employeeService, persister));

        employees = new ArrayList<Employee>();
        employees.add(EMPLOYEE_ONE);
        employees.add(EMPLOYEE_TWO);

        doReturn(employees).when(sut).fetch();
    }

    @Test
    public void shouldFetchWhenListingEmployees() {
        sut.list();
        verify(sut).fetch();
    }

    @Test
    public void shouldReturnEmployees() {
        assertThat(sut.list()).contains(EMPLOYEE_ONE, EMPLOYEE_TWO);
    }

    @Test
    public void shouldSaveDataWhenDataListedAndValidDataReturned() {
        List<Employee> data = sut.list();
        verify(persister).saveData(data);
    }

    @Test
    public void shouldNotSaveDataWhenDataListedAndNoneReturned() {
        doReturn(new ArrayList<Employee>()).when(sut).fetch();
        sut.list();
        verify(persister, never()).saveData(anyList());
    }

    @Test
    public void shouldNotSaveDataWhenDataListedAndNullReturned() {
        doReturn(null).when(sut).fetch();
        sut.list();
        verify(persister, never()).saveData(anyList());
    }
}
