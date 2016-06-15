package photoclient.lazada.com.photoclient.net;

import photoclient.lazada.com.photoclient.model.DetailPhoto;
import photoclient.lazada.com.photoclient.model.DetailPhotoObject;
import photoclient.lazada.com.photoclient.model.Photos;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by avi_chopra on 15/06/16.
 */
public interface Routes {

    /**
     * Get Photos API
     * @param response
     */
    @GET("/v1/photos")
    public Call<Photos> getPhotos(@Query("only") String category);


    /**
     * Get Best Quality Photos API
     * @param response
     */
    @GET("/v1/photos/{id}")
    public Call<DetailPhotoObject> getPhotos(@Path("id") int photoId);
}
