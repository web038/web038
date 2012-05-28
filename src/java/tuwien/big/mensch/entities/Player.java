/*
 * a player of the game
 */
package tuwien.big.mensch.entities;

import at.ac.big.tuwien.ewa.picasa.Avatar;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;

@ManagedBean(name = "player")
@NoneScoped
public class Player {

    @Override
    public String toString() {
        return firstname + " " + lastname + "(" + name + ')';
    }
    /**
     * The current position of the user's counter
     */
    private int position;
    private String firstname = null;
    private String lastname = null;
    private String name = null;
    private String password = null;
    private String birthday = null;
    private String sex = null;
    private Avatar avatar = null;


        
    /**
     * Sequence of fields in the game the player has to cover
     */
    private List<Integer> route; 

    /**
     * Creates a new instance of Player
     */
    public Player() {
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Return the actual position of this player's counter
     *
     * @return the actual position of this player's counter
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Sets the actual position of this player's counter
     *
     * @param pos actual position of this player's counter
     */
    public void setPosition(int pos) {
        this.position = pos;
    }

    /**
     * Get the sequence of fields the player has to cover
     *
     * @return the sequence of fields the player has to cover
     */
    public List<Integer> getRoute() {
        return Collections.unmodifiableList(this.route);
    }

    /**
     * Set the sequence of fields the player has to cover
     *
     * @param route sequence of fields the player has to cover
     */
    public void setRoute(List<Integer> route) {
        this.route = route;
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
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
    
        public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public Avatar getAvatar() {
        return avatar;
    }
}
