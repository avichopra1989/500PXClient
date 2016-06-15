package photoclient.lazada.com.photoclient.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by avi_chopra on 15/06/16.
 */
public class Photos implements Serializable {
    private List<Photo> photos;

    public List<Photo> getPhotos(){
        return photos;
    }

    public void setPhotos(List<Photo> photos){
        this.photos = photos;
    }
}
