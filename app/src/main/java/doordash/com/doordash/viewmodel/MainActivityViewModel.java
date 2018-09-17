package doordash.com.doordash.viewmodel;

import android.arch.lifecycle.ViewModel;

import java.util.List;

import doordash.com.doordash.utils.Constants;
import doordash.com.doordash.service.repository.RetroClientCall;
import doordash.com.doordash.service.model.RestaurantModel;
import doordash.com.doordash.service.repository.GetRestaurantData;
import io.reactivex.Observable;

/**
 * Created by ankit on 9/15/2018.
 */

public class MainActivityViewModel extends ViewModel {

    private GetRestaurantData service = RetroClientCall.getRetrofitInstance().create(GetRestaurantData.class);

    public Observable<List<RestaurantModel>> getRestaurants() {
        if (service != null) {
            return service.getAllRestaurants(Constants.latitude, Constants.lon, Constants.offset, Constants.limit);
        }
        return null;
    }
}


