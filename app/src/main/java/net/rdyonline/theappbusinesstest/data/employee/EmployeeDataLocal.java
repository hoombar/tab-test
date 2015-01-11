package net.rdyonline.theappbusinesstest.data.employee;

import net.rdyonline.theappbusinesstest.data.DataProvider;
import net.rdyonline.theappbusinesstest.data.Employee;
import net.rdyonline.theappbusinesstest.data.EmployeeDataPersister;

import java.util.List;

/**
 * The local data provider uses the data that has been persisted to disk
 *
 * Created by rdy on 11/01/15.
 */
public class EmployeeDataLocal implements DataProvider<Employee> {

    EmployeeDataPersister employeeDataPersister;

    public EmployeeDataLocal(EmployeeDataPersister dataPersister) {
        this.employeeDataPersister = dataPersister;
    }

    @Override
    public List<Employee> list() {
        return employeeDataPersister.load();
    }
}
