package net.rdyonline.theappbusinesstest.data;

import java.util.List;

/**
 * Created by rdy on 09/01/15.
 */
public interface DataProvider<T> {

    List<T> list();
}
