package model.interfaces;
/**
 * Interface for timed sensitive elements.
 * @author lostanth
 *
 */
public interface ITickable {
  /**
   * Have to be casted when an simulated hour is passed.
   */
  void onMinutePass(int tick);
}
