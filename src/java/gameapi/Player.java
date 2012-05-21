/**
 * <copyright>
 *
 * Copyright (c) 2010 http://www.big.tuwien.ac.at
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * </copyright>
 */

package gameapi;

import java.util.Collections;
import java.util.List;

/**
 * Class representing a player playing in a {@link Game}.
 */
public class Player {

	/**
	 * The name of this user
	 */
	private String name;
        
        /**
         * The current position of the user's counter
         */
        private int position;
       
        /**
         * Sequence of fields in the game the player has to cover
         */
        private List<Integer> route;
        
	/**
	 * Initializes a {@link Player} with the specified <code>name</code>.
	 * 
	 * @param name to set
	 */
	public Player(String name) {
		super();
		this.name = name;
	}

	/**
	 * Returns the name of this player.
	 * 
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
        public void setPosition(int pos){
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
}
