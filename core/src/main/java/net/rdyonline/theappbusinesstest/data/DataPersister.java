package net.rdyonline.theappbusinesstest.data;

import java.util.List;

/**
 * Any concrete implementation of this interface should understand how it persists the data to X
 * and how it then retrieves the same data
 *
 * Created by rdy on 11/01/15.
 */
public interface DataPersister<T> {

    public void saveData(List<T> data);

    public List<T> load();

}
