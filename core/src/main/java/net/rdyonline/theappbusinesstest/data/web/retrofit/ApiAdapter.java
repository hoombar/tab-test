package net.rdyonline.theappbusinesstest.data.web.retrofit;

import retrofit.RestAdapter;
import retrofit.converter.Converter;

/**
 * Created by rdy on 09/01/15.
 */
public class ApiAdapter {

    // Note to the reviewer: I didn't create build variants, etc, as at this point I have already
    // spent 2 hours - happy to go in to details at interview
    // TODO(benp) this should really go in a config file, or gradle build variant..
    private static final String ENDPOINT = "http://www.theappbusiness.com/";

    private RestAdapter restAdapter;

    public ApiAdapter(Converter converter) {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setConverter(converter)
                .build();
    }

    public RestAdapter getAdapter() {
        return restAdapter;
    }

}
