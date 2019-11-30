import java.util.Date;

import model.data.Species;
import view.View;
import model.world.World;

public class App {

  public static void main(String[] args) {

    World world = new World(500,500);
    //model.world.addEntity(,5, 5);
    world.createWorker(100, 100);
    world.createWorker(50, 50);
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
