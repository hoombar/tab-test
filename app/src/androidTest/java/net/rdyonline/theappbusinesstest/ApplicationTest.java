package net.rdyonline.theappbusinesstest;

import android.app.Application;
import android.test.ApplicationTestCase;

/***
 * Hello!
 *
 * You'll notice that there aren't any tests here - all of the tests are in the 'core' project.
 * I would normally add funcational and end to end tests as well, but I've only provided unit
 * tests in the core library (pure Java, much faster to run than pulling in Robolectric).
 *
 * I've used Robolectric to run unit tests at this layer as well, but please take a look at the
 * core Java+Mockito tests in the core project
 *
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
}