package edu.cnm.deepdive.craps.model;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 */

public class Game {

  private int pointValue;
  private State state;
  private Random rng;
  private List<int[]> rolls;
  private long wins;
  private long losses;

  /**
   *
   * @param rng
   *
   */

  public Game(Random rng) {
    this.rng = rng;
    rolls = new LinkedList<>();
  }

  /**
   * prepairs this instane to play a round&mdash; i.e. returns game state to {@link State#COME_OUT}, clears the established point, and clears the list of rollls. This method <strong>must</strong> be invoked prior to {@link #play()}, even immediatly after creating and intilizing the indtance via {@link #Game(Random)}
   * Game implements  a single play engine for the main roll/ bet of a game of Craps. Additionaly, it can be used to keep tallies across several rounds of play. Side bets are not supported.
   */

  public void reset() {
    state = State.COME_OUT;
    pointValue = 0;
    rolls.clear();
  }

  private void roll() {
    int die0 = rng.nextInt(6) + 1;
    int die1 = rng.nextInt(6) + 1;
    int sum = die0 + die1;
    State newState = state.change(sum, pointValue);
    if (state == State.COME_OUT && newState == State.POINT) {
      pointValue = sum;
    }
    state = newState;
    int[] diceRoll = {die0, die1};
    rolls.add(diceRoll);
  }

  /**
   * Plays one round of Craps, from the come-out roll to win or loss. If this is invoked on an instance already in a terminal state ({@link State#WIN} or {@link State#LOSS}), no exception is thrown, but the state of this Game instance doesent change.
   *
   * @return
   */
  public State play() {
    while (state != state.WIN && state != state.LOSS) {
      roll();
      if (state == State.WIN) {
        wins++;
      } else if (state == State.LOSS) {
        losses++;
      }
    }
    return state;
  }

  /**
   * returns state of game. This will  be the {@link State#COME_OUT} state after {@link State#LOSS} is invoked, and a terminal state ({@link State#WIN} or {@link State#LOSS}) after {@link #play()} is invoked
   *
   * @return
   */
  public int getPointValue() {
    return pointValue;
  }

  public State getState() {
    return state;
  }

  /**
   * returns the tally of losses recorded since this instance was created and initilized.
   * @return
   */

  public List<int[]> getRolls() {
    List<int[]> copy = new LinkedList<>();
    for (int[] roll : rolls) {
      copy.add(Arrays.copyOf(roll, roll.length));
    }
    return copy;
  }

  public long getWins() {
    return wins;
  }

  public long getLosses() {
    return losses;
  }

}
