package net.rdyonline.theappbusinesstest.data;

import java.util.List;

/**
 * Created by rdy on 11/01/15.
 */
public interface DataPersister<T> {

    public void saveData(List<T> data);

}
