
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 */

/**
 *
 * @author Barbara
 */
public class Picasa implements PicasaConnector{
    
    ArrayList<Avatar> avatars = new ArrayList<Avatar>();

    @Override
    public List<Avatar> getPhotoURLs() throws Exception {
        
        URL feedUrl = new URL("https://picasaweb.google.com/111420671758947023853/EWA2012"); 

        PicasawebService myService = new PicasawebService("Picasa");
        AlbumFeed feed = myService.getFeed(feedUrl, AlbumFeed.class);

        
         for (PhotoEntry photo : feed.getPhotoEntries()) {
            Avatar a1 = new Avatar();
            a1.setDescription(photo.getTitle().getPlainText());
            a1.setUrl(photo.getMediaThumbnails().get(0).getUrl());
            avatars.add(a1);
        }
        
        return avatars; 
    }
    
}

