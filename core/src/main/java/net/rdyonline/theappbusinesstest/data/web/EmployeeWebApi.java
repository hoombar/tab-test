package net.rdyonline.theappbusinesstest.data.web;

import net.rdyonline.theappbusinesstest.data.Employee;
import net.rdyonline.theappbusinesstest.data.web.retrofit.ApiAdapter;
import net.rdyonline.theappbusinesstest.data.web.retrofit.EmployeeService;

import java.util.List;

import retrofit.converter.Converter;

/**
 * Created by rdy on 09/01/15.
 */
public class EmployeeWebApi extends ConcreteWebApi<Employee> {

    EmployeeService service;

    public EmployeeWebApi(EmployeeService service) {
        this.service = service;
    }

    @Override
    public List<Employee> fetch() {
        EmployeeService.EmployeeWebPageWrapper wrapper = service.getEmployeesFromWebsite();

        if (wrapper.employees != null && wrapper.employees.size() > 0) {
            return wrapper.employees;
        }

        return null;
    }

    @Override
    public void saveData(List<Employee> data) {

    }
}
