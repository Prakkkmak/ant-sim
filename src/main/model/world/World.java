package main.model.world;

import main.model.data.Species;
import main.model.developments.Egg;
import main.model.entities.Ant;
import main.model.entities.Anthill;
import main.model.enums.EType;
import main.model.factories.AntFactory;
import main.model.factories.SpeciesFactory;
import main.model.interfaces.IEntityTile;
import main.model.interfaces.ITickable;
import main.model.roles.Soldier;
import main.model.roles.Worker;
import main.model.visitors.AntCounterVisitor;
import main.model.visitors.FoodCounterVisitor;

import java.util.Map;

/**
 * The main.model.world is where the sim append. It's a list of tiles.
 *
 * @author lostanth
 */
public class World implements ITickable {
    /**
     * Date of creation of the world.
     */
    private Time date;
    /**
     * Tiles composes the main.model.world.
     */
    private Tile[][] tiles;
    /**
     * The ant factory create ants with a specific species.
     */
    private AntFactory antFactory;
    /**
     * Word entities spawner is used for create preys and random food.
     */
    private WorldEntitiesSpawner worldEntitiesSpawner;

    /**
     * Creation of the main.model.world by its size.
     *
     * @param sizeX Number of X tiles.
     * @param sizeY Number of Y tiles.
     */
    public World(int sizeX, int sizeY) {
        Species species = SpeciesFactory.CreateDefaultSpecies();
        this.antFactory = new AntFactory(species);
        this.worldEntitiesSpawner = new WorldEntitiesSpawner();
        this.date = new Time();
        this.tiles = new Tile[sizeX][sizeY];
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[i].length; j++) {
                this.tiles[i][j] = new Tile(this, i, j);
            }
        }
    }

    public World(int size) {
        this(size, size);
    }

    /* === Getters Setters === */

    public int getSizeX() {
        return this.tiles.length;
    }

    public int getSizeY() {
        return this.tiles[0].length;
    }


    public WorldEntitiesSpawner getWorldEntitiesSpawner() {
        return this.worldEntitiesSpawner;
    }

    public Time getDate() {
        return date;
    }

    /**
     * Get a tile at specific location. TODO Ne pas retourner de tile
     *
     * @param x Position X.
     * @param y Position Y.
     * @return The tile at this position.
     */
    public Tile getTile(int x, int y) {
        return this.tiles[x][y];
    }

    /**
     * Get the content of the tile. The content is an id and the greatest is the one the function returns.
     *
     * @param x X.
     * @param y Y.
     * @return The content in id format
     */
    public int getTileContent(int x, int y) {
        return this.tiles[x][y].getContent();
    }

    /**
     * Get the pheromone concentration of a tile.
     * @param x X.
     * @param y Y.
     * @return The total of pheromone in the tile.
     */
    public int getPheromoneConcentration(int x, int y) {
        return this.tiles[x][y].getPheromoneConcentration();
    }

    /**
     * Get the concentration of a specific pheromone in a tile.
     * @param x X.
     * @param y Y.
     * @param i The id of the pheromone. All pheromone have a specific id.
     * @return The concentration of pheromone.
     */
    public int getPheromoneConcentration(int x, int y, int i) {
        return this.tiles[x][y].getPheromoneRate(i);
    }

    /**
     * Get the number of food in a specific tile.
     * @param x X.
     * @param y Y.
     * @return The number of food in the tile.
     */
    public int getFoodConcentration(int x, int y) {
        return this.tiles[x][y].getFood();
    }

    /**
     * Get mark in a specific tile.
     * @param x X.
     * @param y Y.
     * @return Returns true if the tile contains marked entity.
     */
    public boolean getMarked(int x, int y) {
        return this.tiles[x][y].isMarked();
    }

    /**
     * Create food at specific tile.
     * @param x X.
     * @param y Y.
     * @param amount The amount of food to create.
     */
    public void createFood(int x, int y, int amount) {
        this.tiles[x][y].addFood(amount);
    }

    /**
     * Add entity at specific tile.
     *
     * @param entity The entity to add.
     * @param x      Pos X.
     * @param y      Pos Y.
     */
    void addEntity(IEntityTile entity, int x, int y) {
        if (x >= this.tiles.length) {
            x = this.tiles.length - 1;
        }
        if (x < 0) {
            x = 0;
        }
        if (y >= this.tiles.length) {
            y = this.tiles[0].length - 1;
        }
        if (y < 0) {
            y = 0;
        }
        this.tiles[x][y].addEntity(entity);
    }
    /* === Visitors ===*/

    /**
     * Get all ants outside any anthill.
     * @return A map with a value for each type present outside.
     */
    public Map<EType, Integer> getOutsides() {
        AntCounterVisitor visitor = new AntCounterVisitor();
        for (Tile[] tiles : this.tiles) {
            for (Tile tile : tiles) {
                tile.accept(visitor);
            }
        }
        return visitor.getOutside();
    }

    /**
     * Get all ants inside anthills.
     * @return Contains the amount of each types.
     */
    public Map<EType, Integer> getInsides() {
        AntCounterVisitor visitor = new AntCounterVisitor();
        for (Tile[] tiles : this.tiles) {
            for (Tile tile : tiles) {
                tile.accept(visitor);
            }
        }
        return visitor.getInside();
    }

    /**
     * Get ant count. Count all ant in all states.
     *
     * @return The ant count.
     */
    public int getAntCount() {
        AntCounterVisitor visitor = new AntCounterVisitor();
        for (Tile[] tiles : this.tiles) {
            for (Tile tile : tiles) {
                tile.accept(visitor);
            }
        }
        return 0;
    }

    /**
     * Get the number of food inside anthills.
     * @return The total number of food.
     */
    public int getFoodInsides() {
        FoodCounterVisitor visitor = new FoodCounterVisitor();
        for (Tile[] tiles : this.tiles) {
            for (Tile tile : tiles) {
                tile.accept(visitor);
            }
        }
        return visitor.getInsideFood();
    }

    /**
     * Get food outside anthills.
     * @return The total number of food.
     */
    public int getFoodOutsides() {
        FoodCounterVisitor visitor = new FoodCounterVisitor();
        for (Tile[] tiles : this.tiles) {
            for (Tile tile : tiles) {
                tile.accept(visitor);
            }
        }
        return visitor.getOutsideFood();
    }

    /* === Ants creators === */
    /**
     * Create a soldier at specific coords.
     *
     * @param x Position X.
     * @param y Position Y.
     */
    public void createEgg(int x, int y) {
        Ant ant = antFactory.createAnt(new Egg());
        this.addEntity(ant, x, y);
    }

    /**
     * Create a soldier at specific coords.
     *
     * @param x Position X.
     * @param y Position Y.
     */
    public void createSoldier(int x, int y) {
        Ant ant = antFactory.createAnt(new Soldier());
        this.addEntity(ant, x, y);
    }

    /**
     * Create a worker at specific coords.
     *
     * @param x Position X.
     * @param y Position Y.
     */
    public void createWorker(int x, int y) {
        createWorker(x, y, false);
    }

    /**
     * Create a worker at specific coords with a mark.
     *
     * @param x      Position X.
     * @param y      Position Y.
     * @param marked True if it's marked
     */
    public void createWorker(int x, int y, boolean marked) {
        Ant ant = antFactory.createAnt(this.tiles[x][y], new Worker());
        ant.setMarked(marked);
        this.addEntity(ant, x, y);
    }

    /**
     * Create an anthill at specific coords.
     *
     * @param x Position X.
     * @param y Position Y.
     */
    public void createAnthill(int x, int y) {
        Anthill anthill = this.antFactory.createAnthill(this.tiles[x][y]);
        this.addEntity(anthill, x, y);
        this.tiles[x][y].addFood(100000);
        this.antFactory.setAnthill(anthill);
    }

    /**
     * Called when an minute is passed It pass the minute for all tiles then create entites with the auto spawner.
     */
    @Override
    public void onMinutePass(int tick) {
        date.onMinutePass(tick);
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[i].length; j++) {
                this.tiles[i][j].onMinutePass(tick);
            }
        }
        //TODO create spawner class
        this.getWorldEntitiesSpawner().randomFeeder(this, 1 / 200.0);
        this.getWorldEntitiesSpawner().randomPrey(this, 1 / 60.0);
    }
}
