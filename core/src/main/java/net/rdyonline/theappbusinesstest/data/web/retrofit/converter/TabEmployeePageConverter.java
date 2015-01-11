package net.rdyonline.theappbusinesstest.data.web.retrofit.converter;

import net.rdyonline.theappbusinesstest.data.Employee;
import net.rdyonline.theappbusinesstest.data.TabEmployee;
import net.rdyonline.theappbusinesstest.data.web.retrofit.EmployeeService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Type;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

/**
 * When an API is built (that hopefully returns JSON), we can ditch this adapter and swap it out
 * for an adapter that parses JSON. We can keep the design of the classes though,
 * because the adapter should be able to bind to the {@link EmployeeService.EmployeeWebPageWrapper}
 *
 * Created by rdy on 11/01/15.
 */
public class TabEmployeePageConverter implements Converter {

    private final String ROOT_EMPLOYEE_SELECTOR = "#users .wrapper .row .col";
    private final String NAME_SELECTOR = "h3";
    private final String ROLE_SELETOR = "p:eq(2)";
    private final String IMAGE_SELECTOR = ".title img";
    private final String DESCRIPTION_SELECTOR = "p.user-description";

    @Override
    public Object fromBody(TypedInput body, Type type) throws ConversionException {
        EmployeeService.EmployeeWebPageWrapper result = new EmployeeService.EmployeeWebPageWrapper();

        try {
            Document doc = Jsoup.parse(convertStreamToString(body.in()));

            Elements users = doc.select(ROOT_EMPLOYEE_SELECTOR);
            for (Element user : users) {

                String name = user.select(NAME_SELECTOR).text();
                String role = user.select(ROLE_SELETOR).text();
                String image = user.select(IMAGE_SELECTOR).attr("src");
                String description = user.select(DESCRIPTION_SELECTOR).text();

                Employee tabEmployee = new TabEmployee(name, role, image, description);

                result.employees.add(tabEmployee);
            }

            return result;

        } catch (IOException io) {
            throw new ConversionException(io);
        }
    }

    @Override
    public TypedOutput toBody(Object object) {
        return null;
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
