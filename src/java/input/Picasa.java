package input;

//import input.Avatar;
//import input.PicasaConnector;
import java.util.List;
import java.net.URL;
import java.io.File;

import com.google.gdata.client.*;
import com.google.gdata.client.photos.*;
import com.google.gdata.data.*;
import com.google.gdata.data.media.*;
import com.google.gdata.data.photos.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 */

/**
 *
 * @author Barbara
 */
@ManagedBean(name = "pic")
@SessionScoped
public class Picasa implements PicasaConnector{

    private List options;  
    private String choice; 
    
    @ManagedProperty(value = "#{av}")
    private Avatar avatar = null;

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public List getOptions() {
        return options;
    }

    public void setOptions(List options) {
        this.options = options;
    }

    

    public Picasa() {
        try {
            options = new ArrayList<Avatar>();
            this.getPhotoURLs();
        } catch (Exception ex) {
            Logger.getLogger(Picasa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    @Override
    public List<Avatar> getPhotoURLs() throws Exception {
        System.out.println("Getting the avatars");


        try {
            URL feedUrl = new URL("https://picasaweb.google.com/data/feed/api/user/111420671758947023853/albumid/5708523142385535377");

            PicasawebService myService = new PicasawebService("Picasa");
            AlbumFeed feed = myService.getFeed(feedUrl, AlbumFeed.class);

            System.out.println("for-schleife");
            for (PhotoEntry photo : feed.getPhotoEntries()) {
                System.out.println("Photo " + photo);
                avatar = new Avatar();
                avatar.setDescription(photo.getTitle().getPlainText());
                avatar.setUrl(photo.getMediaThumbnails().get(0).getUrl());
                options.add(avatar);
                System.out.println(avatar.getDescription());
            }
        } catch (Exception x) {
            System.out.println(x);
        }

        return options;
    }
}
