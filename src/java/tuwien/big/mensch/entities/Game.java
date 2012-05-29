/**
 * <copyright>
 *
 * Copyright (c) 2010 http://www.big.tuwien.ac.at All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * </copyright>
 */
package tuwien.big.mensch.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.print.attribute.Size2DSyntax;


/**
 * Class representing a Mensch aergere Dich nicht game
 */
public class Game implements Serializable{

        

    /**
     * Current round of the game
     */
    private int round = 1;
    /**
     * List of player's participating in this game
     */
    private List<Player> players;
    /**
     * Current player
     */
    private Player currentplayer = null;
    /**
     * Sequence of fields in the game the player 1 has to cover
     */
    private List<Integer> routeplayer1 = Arrays.asList(43, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 57, 58, 59, 60);
    /**
     * Sequence of fields in the game the player 2 has to cover
     */
    private List<Integer> routeplayer2 = Arrays.asList(45, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 61, 62, 63, 64);
    /**
     * Sequence of fields in the game the player 3 has to cover
     */
    private List<Integer> routeplayer3 = Arrays.asList(50, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 65, 66, 67, 68);
    /**
     * Sequence of fields in the game the player 4 has to cover
     */
    private List<Integer> routeplayer4 = Arrays.asList(56, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 69, 70, 71, 72);
    /**
     * Sequence of fields in the game the players have to cover
     */
    private List<List<Integer>> routes = Arrays.asList(routeplayer1, routeplayer2, routeplayer3, routeplayer4);
    /**
     * Dice that is used in this game
     */
    private Dice dice = new Dice();
    /**
     * Specifies if the game is over (
     * <code>true</code>) or not (
     * <code>false</code)
     */
    private boolean gameOver = false;
    /**
     * Starting time of the game
     */
    private long gamestarttime ;
    /**
     * Time already spent in this game
     */
    private long spenttime;
    /**
     * Player currently leading the game
     */
    private Player leader = null;
    /**
     * Current score of the computer-controlled opponent
     */
    private List<Integer> computeropponentscore = null;
    /**
     * real opponent or computer opponent
     */
    private Boolean computeropponent = null;
    /**
     * Fields of the board
     */
    private List<Field> boardFields = null;

    public Game() {
    }
    
    /**
     * start the game
     * @param   players the players that want to play the game
     */
    public void start(List<Player> players) {
       this.players=players;
       
        if (players.size() < 1) {
            throw new IllegalArgumentException("At least one player is necessary to play.");
        }

        for (int i = 0; i < players.size(); ++i) {
            players.get(i).setRoute(routes.get(i));
            players.get(i).setPosition(routes.get(i).get(0));
        }
        this.currentplayer = players.get(0);

        boardFields = new ArrayList<Field>();
        for (int i = 0; i < 72; ++i) {
            Field f = new Field();
            boardFields.add(f);
        }
        boardFields.get(routeplayer1.get(0) - 1).setOccupyingPlayer(1);
        boardFields.get(routeplayer2.get(0) - 1).setOccupyingPlayer(2);
        gamestarttime= System.currentTimeMillis();
    }

    /**
     * Specifies whether this game is over or not
     *
     * @return
     * <code>true</code> if this game is over,
     * <code>false</code> otherwise
     */
    public boolean isGameOver() {
        return this.gameOver;
    }

    

    /**
     * Returns the time already spent on this game
     *
     * @return the time already spent on this game
     */
    public long getSpentTime() {
        if (!gameOver) {
            spenttime = System.currentTimeMillis() - this.gamestarttime;
        }
        return spenttime;
    }

    /**
     * Returns the rounds already played in this game
     *
     * @return the rounds already played in this game
     */
    public int getRound() {
        return this.round;
    }

    /**
     * Return an unmodifiable list of the players particiapting in this game
     *
     * @return an unmodifiable list of the players participating in this game
     */
    public List<Player> getPlayers() {
        //return Collections.unmodifiableList(this.players);
        return this.players;
    }

    /**
     * Returns the currently leading player
     *
     * @return the currently leading player
     */
    public Player getLeader() {
        return this.leader;
    }

    /**
     * Rolls the dice for the given player and updates the position of the
     * player's counter according to the score
     *
     * @param player Player who rolls the dice
     * @return score
     */
    public int rollthedice() {
        
        if (gameOver) {
            throw new IllegalArgumentException(
                    "Game is over. Rolling the dice is not allowed.");
        }

        

        int score = dice.roll();

        List<Integer> route = currentplayer.getRoute();
        int position = currentplayer.getPosition();

        if (route.indexOf(position) + score >= route.size()) {
            /*
             * The counter of the player is standing in front of the HOME Base
             * but threw a too high score --> Do nothing
             */
        } else if (position == route.get(0) && score != 6) {
            /*
             * The counter of the player is standing in the Starting Square and
             * the player didn't threw a six. --> Do nothing
             */
        } else if (position == route.get(0) && score == 6) {
            /**
             * Player starts
             */
            currentplayer.setPosition(route.get(1));
            updateBoardFields(route.get(1), position, currentplayer);

            /**
             * Test if one of the opponent's counters is knocked off because the
             * player's counter moves to the new position
             */
            testKnockOff(currentplayer, route.get(1));

            /**
             * Identify the leading player
             */
            identifyLeader();
        } else {
            /**
             * Move on field
             */
            int newposition = route.get(route.indexOf(position) + score);
            currentplayer.setPosition(newposition);
            updateBoardFields(newposition, position, currentplayer);

            /**
             * Test if one of the opponent's counters is knocked off because the
             * player's counter moves to the new position
             */
            testKnockOff(currentplayer, newposition);

            /**
             * Test if the figure of the player reached the HOME Base and the
             * game is over
             */
            if (newposition == route.get(route.size() - 1)
                    || newposition == route.get(route.size() - 2)
                    || newposition == route.get(route.size() - 3)
                    || newposition == route.get(route.size() - 4)) {
                // Spieler ist im Ziel
                gameOver = true;
            }

            /**
             * Identify the leading player
             */
            identifyLeader();
        }
        System.out.println("gewuerfelt wurde:"+score);
            if (score != 6 && !gameOver) {
                // It is the turn of the next player
                int index=players.indexOf(currentplayer);
                currentplayer = players.get((index + 1) % players.size());
                System.out.println("next player is "+currentplayer);
                
            } 
       
        return score;
    }

    private void updateBoardFields(int posnew, int posold, Player player) {
        boardFields.get(posnew - 1).setOccupyingPlayer(players.indexOf(player) + 1);
        if (posold != -1) {
            boardFields.get(posold - 1).setOccupyingPlayer(-1);
        }
    }

    /**
     * Test if one of the opponent's counters is knocked off because the
     * player's counter moves to the new position
     *
     * @param player Player whose counter moved to a new position
     * @param newposition New Position of the player's counter
     */
    private void testKnockOff(Player player, int newposition) {
        Player otherplayer = players.get((players.indexOf(player) + 1) % 2);
        if (newposition == otherplayer.getPosition()) {
            otherplayer.setPosition(otherplayer.getRoute().get(0));
            updateBoardFields(otherplayer.getRoute().get(0), -1, otherplayer);
        }
    }

    /**
     * Identify the leading player
     */
    private void identifyLeader() {
        List<Integer> positionen = new ArrayList<Integer>();
        for (int i = 0; i < players.size(); ++i) {
            positionen.add(players.get(i).getRoute().indexOf(players.get(i).getPosition()));
        }
        List<Integer> positionensorted = new ArrayList<Integer>();
        positionensorted.addAll(positionen);
        Collections.sort(positionensorted);
        int weitesteposition = positionensorted.get(positionensorted.size() - 1);
        if (weitesteposition > positionensorted.get(positionensorted.size() - 2)) {
            this.leader = players.get(positionen.indexOf(weitesteposition));
        } else {
            this.leader = null;
        }
    }

    /**
     * Returns the current position of player 1
     *
     * @return the current position of player 1
     */
    public int getPositionplayer1() {
        return players.get(0).getPosition();
    }

    /**
     * Returns the current position of player 2
     *
     * @return the current position of player 2
     */
    public int getPositionplayer2() {
        return players.get(1).getPosition();
    }

    /**
     * Returns the current position of player 3
     *
     * @return the current position of player 3
     */
    public int getPositionplayer3() {
        if (players.size() < 3) {
            return -1;
        }
        return players.get(2).getPosition();
    }

    /**
     * Returns the current position of player 4
     *
     * @return the current position of player 4
     */
    public int getPositionplayer4() {
        if (players.size() < 4) {
            return -1;
        }
        return players.get(3).getPosition();
    }

    /**
     * Returns the current player
     *
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return this.currentplayer;
    }

    /**
     * Returns the fields of the board
     *
     * @return fields of the board
     */
    public List<Field> getBoardFields() {
        return this.boardFields;
    }

    /**
     * Returns the score of the computer-controlled opponent
     *
     * @return the score of the computer-controlled opponent
     */
    public List<Integer> getOpponentScore() {
        return computeropponentscore;
    }
    
    
    
}