package photoclient.lazada.com.photoclient.model;

import java.io.Serializable;

/**
 * Created by avi_chopra on 15/06/16.
 */
public class User implements Serializable{
    private String fullname;

    public void setFullname(String fullname){
        this.fullname = fullname;
    }

    public String getFullname(){
        return fullname;
    }
}
