
import at.ac.big.tuwien.ewa.picasa.Avatar;
import at.ac.big.tuwien.ewa.picasa.PicasaConnector;
import java.util.List;
import java.net.URL;

import com.google.gdata.client.*;
import com.google.gdata.client.photos.*;
import com.google.gdata.data.*;
import com.google.gdata.data.media.*;
import com.google.gdata.data.photos.*;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;

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
public class Picasa implements PicasaConnector{
    
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
        System.out.println("get avatars"); 
        
        try{
        URL feedUrl = new URL("https://picasaweb.google.com/111420671758947023853/EWA2012"); 

        PicasawebService myService = new PicasawebService("Picasa");
        AlbumFeed feed = myService.getFeed(feedUrl, AlbumFeed.class);

        
         for (PhotoEntry photo : feed.getPhotoEntries()) {
            Avatar a1 = new Avatar();
            a1.setDescription(photo.getTitle().getPlainText());
            a1.setUrl(photo.getMediaThumbnails().get(0).getUrl());
            avatars.add(a1);
        }
        }catch(Exception x){
        System.out.println(x.getStackTrace()); 
        }
        
        return avatars; 
    }   
}

