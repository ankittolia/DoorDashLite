package doordash.com.doordash.service.repository;

import java.util.List;

import doordash.com.doordash.service.model.RestaurantModel;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by ankit on 9/15/2018.
 */

public interface GetRestaurantData {
    @GET("/v2/restaurant")
    Observable<List<RestaurantModel>> getAllRestaurants(@Query("lat") double lat, @Query("lng") double lng,
                                                        @Query("offset") int offset, @Query("limit") int limit);
}
