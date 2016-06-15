package photoclient.lazada.com.photoclient.model;

import java.io.Serializable;

/**
 * Photo Model with all the photo information
 * Created by avi_chopra on 15/06/16.
 */
public class Photo implements Serializable{
    private String name,id;
    private String image_url;
    private User user;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public User getUser() {
        return user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
