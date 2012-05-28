package input;

//import input.Avatar;
//import input.PicasaConnector;
import at.ac.big.tuwien.ewa.picasa.Avatar;
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

    ArrayList<Avatar> avatars = new ArrayList<Avatar>();

    private Avatar avatar; 
    
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
        URL feedUrl = new URL("https://picasaweb.google.com/data/feed/api/user/111420671758947023853/albumid/5708523142385535377"); 

        PicasawebService myService = new PicasawebService("Picasa");
        AlbumFeed feed = myService.getFeed(feedUrl, AlbumFeed.class);

        System.out.println("for-schleife"); 
         for (PhotoEntry photo : feed.getPhotoEntries()) {
            System.out.println("Photo "+photo); 
            avatar = new Avatar();
            avatar.setDescription(photo.getTitle().getPlainText());
            avatar.setUrl(photo.getMediaThumbnails().get(0).getUrl());
            avatars.add(avatar);
            System.out.println(avatar.getDescription());
            System.out.println(avatar.getUrl()); 
        }
        }catch(Exception x){
        System.out.println(x); 
        }
        
        return avatars; 
    }

    
}

