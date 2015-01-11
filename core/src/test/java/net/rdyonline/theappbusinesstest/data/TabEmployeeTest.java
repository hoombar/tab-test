package net.rdyonline.theappbusinesstest.data;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class TabEmployeeTest {

    Employee employeeSut;

    String NAME = "Ben Pearson";
    String ROLE = "Android";
    String IMAGE = "http://image";
    String DESCRIPTION = "description";

    @Before
    public void setup() {
        employeeSut = new Employee(NAME, ROLE, IMAGE, DESCRIPTION);
    }

    @Test
    public void nameCorrectlySet() {
        assertThat(employeeSut.getName()).isEqualTo(NAME);
    }

    @Test
    public void roleCorrectlySet() {
        assertThat(employeeSut.getRole()).isEqualTo(ROLE);
    }

    @Test
    public void imageCorrectlySet() {
        assertThat(employeeSut.getImage()).isEqualTo(IMAGE);
    }

    @Test
    public void descriptionCorrectlySet() {
        assertThat(employeeSut.getDescription()).isEqualTo(DESCRIPTION);
    }

}