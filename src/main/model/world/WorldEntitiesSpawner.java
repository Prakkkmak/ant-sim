package main.model.world;

import main.model.entities.Prey;

public class WorldEntitiesSpawner {

    public void randomPrey(World world, double chance) {
        double r = Math.random();
        if (r < chance) {
            int posX = (int) (Math.random() * world.getSizeX());
            int posY = (int) (Math.random() * world.getSizeY());
            Tile tileToSpawn = world.getTile(posX, posY);
            tileToSpawn.addEntity(new Prey(tileToSpawn));
        }
    }

    public void randomFeeder(World world, double chance) {
        double r = Math.random();
        if (r < chance) {
            int posX = (int) (Math.random() * world.getSizeX());
            int posY = (int) (Math.random() * world.getSizeY());
            //Tile tileToSpawn = world.getTile(posX,posY);
            world.createFood(posX, posY, (int) (Math.random() * 5000));
        }
    }
}
