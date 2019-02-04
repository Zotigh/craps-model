package edu.cnm.deepdive.craps.model;

/**
 * makes a basic state machine for the main play of a single game of craps starting with the come out roll and ending with either a win or loss based on the main bet.
 * @author Lance Zotigh &amp; deep dive coding java + android Android cohort 6
 * @version 1.0
 */
public enum State {
  COME_OUT {
    @Override
    public State change(int roll, int pointValue) {
      switch (roll) {
        case 2:
        case 3:
        case 12:
          return LOSS;
        case 7:
        case 11 :
          return WIN;
          default:
            return POINT;
      }
    }
  },
  POINT {
    @Override
    public State change(int roll, int pointValue) {
      if (roll == 7) {
        return LOSS;
      }
      if (roll == pointValue) {
        return WIN;
      }
      return this;
    }
  },
  WIN,
  LOSS;

  /**
   * Applies the specific roll sum to this state, giving the state from the transition  rep by the roll. For the terminal states({@link #WIN}) and ({@link #LOSS}) no change of state will result from any roll sum value.
   * @param roll
   * @param pointValue
   * @return
   */
  public State change(int roll, int pointValue) {
    return this;
  }

}
