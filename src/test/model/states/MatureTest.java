package test.model.states;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import main.model.abstracts.State;
import main.model.entities.Ant;
import main.model.enums.EType;
import main.model.factories.SpeciesFactory;
import main.model.roles.Worker;
import main.model.states.Garbage;
import main.model.states.Mature;
import main.model.world.Tile;
import main.model.world.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MatureTest {

    private Mature mature;
    private World world;
    private Ant ant;
    private Tile tile;
    @BeforeEach
    void setUp(){
        this.mature = new Mature();
        this.world = new World(10, 10);
        this.tile = this.world.getTile(5,5);
        this.ant = new Ant(tile, SpeciesFactory.CreateDefaultSpecies());
    }

    @Test
    void evolve() {
        State state = this.mature.evolve();
        assertTrue(state instanceof Garbage);
    }

    @Test
    void getSetAntRole() {
        this.mature.setAntRole(new Worker());
        assertTrue(this.mature.getAntRole() instanceof Worker);
    }

    @Test
    void actionFoodWhenAntHaveEnoughFood() {
        this.tile.setFood(2000);
        this.ant.setFood(this.ant.getSpecies().getFoodConsumption());
        int currentFood = tile.getFood();
        this.mature.action(ant);
        assertEquals(currentFood, tile.getFood());
        assertEquals(this.ant.getSpecies().getFoodConsumption(), this.ant.getFood());
    }
    @Test
    void actionFoodWhenAntDontHaveAnyFood() {
        tile.setFood(10000);
        int currentFood = tile.getFood();
        ant.setFood(0);
        this.mature.action(ant);
        int excepted = currentFood - ant.getSpecies().getFoodConsumption();
        assertEquals(excepted, tile.getFood());
        assertEquals(ant.getSpecies().getFoodConsumption(), ant.getFood());

    }
    @Test
    void actionFoodWhenAntDontHaveAnyFoodByOne() {
        ant.setFood(ant.getSpecies().getFoodConsumption() / 4 - 1);
        tile.setFood(10000);
        int currentFood = tile.getFood();
        this.mature.action(ant);
        int excepted = currentFood - ant.getSpecies().getFoodConsumption();
        assertEquals(excepted, tile.getFood());
        assertEquals(ant.getSpecies().getFoodConsumption()
            + (ant.getSpecies().getFoodConsumption() / 4 - 1), ant.getFood());
    }

    @Test
    void actionFoodWhenTileDontHaveAnyFood() {
        tile.setFood(0);
        int currentFood = tile.getFood();
        ant.setFood(0);
        this.mature.action(ant);
        assertEquals(0, tile.getFood());
        assertEquals(0, ant.getFood());

    }

    @Test
    void getType() {
        this.mature.setAntRole(new Worker());
        EType type = this.mature.getType();
        assertEquals(EType.WORKER, type);
    }
}