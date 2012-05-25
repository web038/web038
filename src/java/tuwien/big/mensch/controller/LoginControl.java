package tuwien.big.mensch.controller;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import tuwien.big.mensch.entities.Player;
import tuwien.big.mensch.controller.GameControl;
import tuwien.big.mensch.entities.RegisteredPlayerPool;

@ManagedBean(name = "lc")
@SessionScoped
public class LoginControl {

    @ManagedProperty(value = "#{player}")
    private Player player;
    
    @ManagedProperty(value = "#{rpp}")
    private RegisteredPlayerPool rpp;
    
    @ManagedProperty(value = "#{gc}")
    private GameControl gc;
        
    @ManagedProperty(value = "false")
    private boolean showloginfailed;
    
    private String name;
    
    private String password;
    
    // index in the game
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    /** Creates a new instance of LoginControl */
    public LoginControl() {
        System.out.println("LoginControl constructor"); 
    }

    public String login() {

        System.out.println("Login Data:");
        System.out.println(name);
        System.out.println(password);

        player = getRpp().getRegisteredPlayer(name, password);
      
        if (player != null) {
            setShowloginfailed(false);
            gc = new GameControl(player.getName());
            return "game";
        } else {
            setShowloginfailed(true);
            return "index";
        }
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the rpp
     */
    public RegisteredPlayerPool getRpp() {
        return rpp;
    }

    /**
     * @param rpp the rpp to set
     */
    public void setRpp(RegisteredPlayerPool rpp) {
        this.rpp = rpp;
    }

    /**
     * @return the showloginfailed
     */
    public boolean isShowloginfailed() {
        return showloginfailed;
    }

    /**
     * @param showloginfailed the showloginfailed to set
     */
    public void setShowloginfailed(boolean showloginfailed) {
        this.showloginfailed = showloginfailed;
    }

    /**
     * @return the mc
     */
    public GameControl getGc() {
        return gc;
    }
  
    /**
     * @param mc the mc to set
     */
    public void setGc(GameControl gc) {
        this.gc = gc;
        this.index=this.gc.getNextPlayerIndex();
    }
}
