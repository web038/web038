
import at.ac.big.tuwien.ewa.picasa.Avatar;
import at.ac.big.tuwien.ewa.picasa.PicasaConnector;
import java.util.List;
import java.net.URL;

import com.google.gdata.client.photos.*;
import com.google.gdata.data.photos.*;
import java.util.ArrayList;
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
    
        @ManagedProperty(value = "#{avatar}")
    private Avatar avatar;
    
    ArrayList<Avatar> avatars = new ArrayList<Avatar>();

    public ArrayList<Avatar> getAvatars() {
        return avatars;
    }

    public void setAvatars(ArrayList<Avatar> avatars) {
        this.avatars = avatars;
    }

    public Picasa() {
    }

    
    
    @Override
    public List<Avatar> getPhotoURLs() throws Exception {
        System.out.println("Getting the avatars"); 
        
        try{
        URL feedUrl = new URL("https://picasaweb.google.com/111420671758947023853/EWA2012"); 

        PicasawebService myService = new PicasawebService("Picasa");
        AlbumFeed feed = myService.getFeed(feedUrl, AlbumFeed.class);

        
         for (PhotoEntry photo : feed.getPhotoEntries()) {
            avatar = new Avatar();
            avatar.setDescription(photo.getTitle().getPlainText());
            avatar.setUrl(photo.getMediaThumbnails().get(0).getUrl());
            avatars.add(avatar);
        }
        }catch(Exception x){
        System.out.println(x.getStackTrace()); 
        }
        
        return avatars; 
    }   
}

