package net.rdyonline.theappbusinesstest.data.web;

import net.rdyonline.theappbusinesstest.data.DataPersister;
import net.rdyonline.theappbusinesstest.data.DataProvider;

import java.util.List;

/**
 * Created by rdy on 09/01/15.
 */
public abstract class WebApi<T> implements DataProvider<T> {

    private DataPersister<T> persister;
    private List<T> data;

    public abstract List<T> fetch();

    public WebApi(DataPersister<T> persister) {
        this.persister = persister;
    }

    private void saveData(List<T> data) {
        persister.saveData(data);
    }

    @Override
    public List<T> list() {
        List<T> data = fetch();
        if (data != null && data.size() > 0) {
            saveData(data);
        };

        return data;
    }

}
