import java.awt.Dimension;
import java.util.Date;
import java.util.Timer;
import graphicLayer.GSpace;
import view.View;
import world.World;

public class App {

  public static void main(String[] args) {
    World world = new World(100,100);
    //world.addEntity(,5, 5);
    world.createSoldier(2, 2);
    View view = new View(world);
    view.update();
    
    Date date= new Date();
    long time = date.getTime();
    int tick = 0;
    while(true) {
       date = new Date();
       if(date.getTime() - time > 100) {
         //onMinutePass.run();
         world.onMinutePass(tick++);
         view.update();
         time = date.getTime();
       }
       
    }
   
    //time.schedule(onMinutePass, 0, 5000); // Create Repetitively task for every 1 secs
  }

}
