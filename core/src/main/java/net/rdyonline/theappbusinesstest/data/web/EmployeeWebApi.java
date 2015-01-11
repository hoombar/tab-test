package net.rdyonline.theappbusinesstest.data.web;

import net.rdyonline.theappbusinesstest.data.DataPersister;
import net.rdyonline.theappbusinesstest.data.Employee;
import net.rdyonline.theappbusinesstest.data.web.retrofit.EmployeeService;

import java.util.List;

/**
 * Created by rdy on 09/01/15.
 */
public class EmployeeWebApi<T extends Employee> extends WebApi<Employee> {

    EmployeeService service;

    public EmployeeWebApi(EmployeeService service, DataPersister<Employee> persister) {
        super(persister);
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

}
