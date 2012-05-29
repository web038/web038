package tuwien.big.mensch.controller;

/*
 * controls login operations
 */
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import tuwien.big.mensch.entities.Player;
import tuwien.big.mensch.entities.RegisteredPlayerPool;
import org.icefaces.application.PushRenderer;

@ManagedBean(name = "lc")
@SessionScoped
public class LoginControl implements Serializable{

    @ManagedProperty(value = "#{player}")
    private Player player=null;
    @ManagedProperty(value = "#{rpp}")
    private RegisteredPlayerPool rpp;
    @ManagedProperty(value = "#{gc}")
    private GameControl gc;
    @ManagedProperty(value = "false")
    private boolean showloginfailed;
    @ManagedProperty(value = "false")   
    private boolean showwaiting;
    private String name;
    private String password;

    /**
     * Creates a new instance of LoginControl
     */
    public LoginControl() {
    }

    public String login() {
        /*
         * System.out.println("Login Data:"); System.out.println(name);
         * System.out.println(password);
         *
         */

        player = getRpp().getRegisteredPlayer(name, password);
        if (player != null) {
            setShowloginfailed(false);
            if (gc.getGamestate() == GameState.NEW ) {
                gc.init(player);
                return "wait";
            } else if (gc.getGamestate() == GameState.WAITING) {
                System.out.println("second player wants to start the game"); 
                gc.startGame(player); // the second player starts the game
                return "game";
            }

            //gc.addPlayer(player); 


            //gc = new GameControl(player.getName());


        } else {
            setShowloginfailed(true);
        }
        return "index";
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
 * is game waiting for more players?
 * @return 
 */
    public boolean isShowwaiting() {
        return showwaiting;
    }

    public void setShowwaiting(boolean showwaiting) {
        this.showwaiting = showwaiting;
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
    }

    /**
     * is player 1 already waiting for secnd player?
     */
    public String getPlayerWaiting() {
        System.out.println("getPlayerWaiting, GameState="+this.gc.getGamestate());
        if (this.gc.getGamestate() == GameState.NEW) {
            return "Sie sind der 1.Spieler";
        }
        if (this.gc.getGamestate() == GameState.WAITING) {
            return "Spieler " + this.gc.getPlayers().get(0) + " wartet schon auf Sie!";
        }
        if(this.gc.getPlayers().contains(player)) { // funktioniert nicht.
            return "Das Spiel läuft bereits.";
        }
        return "Das Spiel läuft bereits. Warten Sie bis es beendet wurde.";
    }
    /**
     * 
     */
    public boolean isPlayerLoggedIn() {
        return player!=null &&  player.getName()!=null;
    }
    /**
     * should the login screen be rendered
     * @returns boolean true, if the game hasnot started yet 
     */
    public boolean showLogin() {
        return !this.gc.isGameStarted() ;
    }
    
    /**
     * should the "go to game" button be rendered
     * @returns boolean true, if the user is logged in and 
     *                  part of the game 
     */
    public boolean showGameButton() {
        return this.gc.isGameStarted() && isPlayerLoggedIn();
    }
}
