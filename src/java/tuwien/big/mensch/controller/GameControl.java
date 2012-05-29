/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tuwien.big.mensch.controller;

import java.io.Serializable;
import tuwien.big.mensch.entities.Field;
import javax.faces.bean.ManagedBean;
import tuwien.big.mensch.entities.Game;
import tuwien.big.mensch.entities.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.faces.bean.ApplicationScoped;
import org.icefaces.application.PushRenderer;

@ManagedBean(name = "gc")
@ApplicationScoped
public class GameControl {
    private GameState gamestate=GameState.NEW;
    private Game game;
    private int score = 0;
    
    public static final String LOGIN_RENDERER_NAME = "login";
    public static final String DICE_RENDERER_NAME = "dice";
    
    private List<Player> players;
    // has game started (true) or is it waiting for more players (false)

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
  
    public Player getCurrentPlayer() {
        return game.getCurrentPlayer();
    }

    public GameState getGamestate() {
        return gamestate;
    }

    public void setGameState(GameState gamestate) {
        this.gamestate = gamestate;
    }
    
    /** Creates a new instance of MemoryControl */
    public GameControl() {
    }

    /**
     * starts the game
     */
    public void startGame(Player player) {
        this.addPlayer(player);
        this.game.start(this.players);
       
        this.setGameState(GameState.STARTED);
   
    }
    
    /**
     * restart the game
     */
    public void restartGame() {
        this.game.start(this.players);
        this.setGameState(GameState.STARTED);
    }
    
/**
 * has the game started?
 */
    public boolean isGameStarted() {
        return (this.gamestate==GameState.STARTED);
    }
    
    /**
     * Initializes a new game
     */
    void init(Player player) {
        this.game = new Game();
        this.players=new ArrayList<Player>();
        this.addPlayer(player);
        score = 0;
        this.setGameState(GameState.WAITING);
    }
    
    /**
     * adds a player to the game
     */
    public void addPlayer(Player player) {
        this.players.add(player);
        PushRenderer.addCurrentSession(GameControl.LOGIN_RENDERER_NAME);
        PushRenderer.render(GameControl.LOGIN_RENDERER_NAME);
    }
    
    /**
     * Returns the time already spent on this game
     * 
     * @return the time already spent on this game
     */
    public String getTime(){
       long milliseconds = game.getSpentTime();
       return String.format("%d min, %d sec",
               TimeUnit.MILLISECONDS.toMinutes(milliseconds),
               (TimeUnit.MILLISECONDS.toSeconds(milliseconds) - 
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds)))
       );
    }

    /**
     * Specifies whether this game is over or not
     * 
     * @return <code>true</code> if this game is over, <code>false</code> 
     *         otherwise.
     */
    public boolean isGameOver(){
        return game.isGameOver();
    }
    
    /**
     * Returns the rounds already played in this game
     * 
     * @return the rounds already played in this game
     */
    public int getRound(){
        return game.getRound();
    }
    
    /**
     * Return an unmodifiable list of the players participating in this game
     * 
     * @return an unmodifiable list of the players participating in this game
     */
    public List<Player> getPlayers(){
        return this.players;
    }
    
    /**
     * Returns the currently leading player
     * 
     * @return the currently leading player
     */
    public Player getLeader(){
        return game.getLeader();
    }
    
    /**
     * Specifies if a counter of a player occupies the field identified by 
     * <code>index</code> and when yes which player's counter occupies it
     * 
     * @param index Index of the field for which should be checked if and which
     *        player's counter occupies it
     * @return number of the player whose counter occupies the field identified
     *         by the given <code>intex</code>, i.e., 1, 2, 3 or 4; or -1
     *         if no counter occupies this field
     */
    public int isFieldOccupied(int index){
        if(this.game.getPositionplayer1() == index)
            return 1;
        else if(this.game.getPositionplayer2() == index)
            return 2;
        else if(this.game.getPositionplayer3() == index)
            return 3;
        else if(this.game.getPositionplayer4() == index)
            return 4;
        return -1;
    }
    
    /**
     * Rolls the dice for the player
     */
    public void rollDice(){
        if(isGameOver())
            return;
        this.score = this.game.rollthedice();
        PushRenderer.addCurrentSession(GameControl.DICE_RENDERER_NAME);
        PushRenderer.render(GameControl.DICE_RENDERER_NAME);

    }
    
    /**
     * Returns the score thrown by the player
     * 
     * @return the score thrown by the player
     */
    public int getScore(){
        return this.score;
    }
    
    /**
     * Returns the score of the computer opponent
     * 
     * @return the score of the computer-controlled opponent
     */
    public List<Integer> getOpponentScore(){
        return game.getOpponentScore();
    }
    
    /**
     * Returns the fields of the board
     * 
     * @return fields of the board
     */
    public List<Field> getBoardFields(){
        return game.getBoardFields();
    }
    
    /**
     * Returns the player
     * 
     * @return player
     */
    public Player getPlayer(int index) {
        return this.game.getPlayers().get(index);
    }
    
    public String getPlayerName(int i){
        return getPlayer(i).getName();
    }
}

    