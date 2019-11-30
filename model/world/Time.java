package world;

import interfaces.ITickable;
/**
 * Time is a custom class for manage the time in the simulation.
 * @author lostanth
 *
 */
public class Time implements ITickable {
  /**
   * Number of hours passed.
   */
  private int minutes;
  /**
   * Default constructor, set the hour to 0.
   */
  public Time() {
    this.minutes = 0;
  }
  /**
   * Add minutes to the minutes.
   * @param minutes to add.
   */
  public void addMinutes(int minutes) {
    this.minutes += minutes;
  }
  /**
   * Add 1 minute to the hours.
   */
  public void addHours() {
    this.addMinutes(1);
  }
  /**
   * Get hours passed since the creation of the timer.
   * @return Minutes passed.
   */
  public int getMinutes() {
    return this.minutes;
  }
  /**
   * Get hours passed since the creation of the timer.
   * @return Hours passed.
   */
  public int getHours() {
    return (int) Math.floor(this.minutes/60);
  }
  /**
   * Get days passed since the creation of the timer. A day is 24 hours.
   * @return Day passed.
   */
  public int getDay() {
    return (int) Math.floor(this.getHours() / 24);
  }
  /**
   * Get month passed since the creation of the timer. A month is 30 day.
   * @return Month passed.
   */
  public int getMonth() {
    return (int) Math.floor(this.getDay() / 30);
  }
  /**
   * Get seasons passed since the creation of the timer. A season is 3 month.
   * @return Season passed.
   */
  public int getSeason() {
    return (int) Math.floor(this.getMonth() / 3);
  }
  /**
   * Get the year passed since the creation of the timer. A year is 4 seasons.
   * @return Year passed.
   */
  public int getYear() {
    return (int) Math.floor(this.getSeason() / 4);
  }
  /**
   * Called when an hour is passed.
   */
  @Override
  public void onMinutePass(int tick) {
    this.addHours();
  }
  
}
