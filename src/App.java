import java.util.Date;

import model.data.Species;
import view.View;
import model.world.World;

public class App {
  public static void main(String[] args) {
    //TODO parameters main
    World world = new World(500, 500);
    //model.world.addEntity(,5, 5);
    for(int i = 0; i < 10000; i++){
      world.createWorker(200, 200);
    }

    //world.createWorker(10, 10);
    //world.createEgg(50, 50);
    world.createQueen(200,200);
    timer(world, 1);
  }

  public static void timer(World world, int minutePerTick){
    View view = new View(world);
    view.update();
    Date date = new Date();
    long time = date.getTime();
    int tick = 0;
    int viewUpdateRate = 0;
    int currentViewUpdate = 0;
    while(true) {
      date = new Date();
      long dateMs = date.getTime();

      if(dateMs - time > 50) {

        //onMinutePass.run();
        if(++currentViewUpdate > viewUpdateRate){
          view.update();
          currentViewUpdate = 0;
        }
        for(int i = 0; i < minutePerTick; i++){
          world.onMinutePass(tick++);
        }
        time = date.getTime();
      }


  }
  }

}
