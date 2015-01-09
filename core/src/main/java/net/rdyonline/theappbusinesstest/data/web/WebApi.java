package net.rdyonline.theappbusinesstest.data.web;

import java.util.List;

/**
 * Created by rdy on 09/01/15.
 */
public interface WebApi<T> {

    List<T> list();
    boolean contains(T item);

}
