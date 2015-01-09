package net.rdyonline.theappbusinesstest.data.web;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.converter.Converter;

/**
 * Created by rdy on 09/01/15.
 */
public abstract class ConcreteWebApi<T> implements WebApi<T> {


    private List<T> data;

    public abstract List<T> fetch();
    public abstract void saveData(List<T> data);

    @Override
    public List<T> list() {
        List<T> data = fetch();
        if (data != null) {
            saveData(data);
        };

        return null;
    }

    /***
     * Note to the reviewer. The employees should really be compared by some sort of identifier
     * (that comes from the server), but because there's no API, the only real matcher that can
     * be used is name+role+description - this could well be unique,
     * but I would prefer to have something definitive from the server that I can compare
     * employee objects on. The other thing I could do is give them a GUID when I import them as
     * they will effectively be flushed each time a new list is grabbed from the server,
     * but this is all outside the scope of the test
     */
    @Override
    public boolean contains(T item) {
        // TODO(benp): finish this? could throw specific ex telling dev that data hasn't been
        // fetched, etc

        return false;
    }
}
