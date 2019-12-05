package test.model.data;

import main.model.data.Species;
import main.model.roles.Soldier;
import main.model.roles.Worker;
import main.model.states.Egg;
import main.model.world.Pheromone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpeciesTest {

    Species species;

    @BeforeEach
    void setUp(){
        this.species = new Species("Test",2500,200,800);
    }

    @Test
    void getName() {
        assertEquals("Test", this.species.getName());
    }

    @Test
    void getWeight() {
        assertEquals(2500, this.species.getWeight());
    }

    @Test
    void getSetFoodConsumption() {
        species.getFoodConsumption();
        assertEquals(2500/4 , this.species.getFoodConsumption());
        species.setFoodConsumption(400);
        assertEquals(400, this.species.getFoodConsumption());
    }

    @Test
    void getTerritory() {
        assertTrue(200 == this.species.getTerritory());
    }

    @Test
    void getStamina() {
        assertTrue(800 == this.species.getStamina());
    }

    @Test
    void getSetGrowth() {
        Egg egg = new Egg();
        assertEquals(0, this.species.getGrowth(egg));
        this.species.setGrowth(egg, 3);
        assertEquals(3, this.species.getGrowth(egg));
    }

    @Test
    void setGrowthDays() {
        Egg egg = new Egg();
        assertEquals(0, this.species.getGrowth(egg));
        this.species.setGrowthDays(egg, 3);
        assertEquals(3*60*24, this.species.getGrowth(egg));
    }

    @Test
    void getSetRatio() {
        Worker worker = new Worker();
        assertEquals(0, this.species.getRatio(worker));
        this.species.setRatio(worker, 50);
        assertEquals(50, this.species.getRatio(worker));
    }

    @Test
    void getTotalRatio() {
        Worker worker = new Worker();
        Soldier soldier = new Soldier();
        this.species.setRatio(worker, 50);
        this.species.setRatio(soldier, 25);
        assertEquals(75, this.species.getTotalRatio());
    }

    @Test
    void getRealRatio() {
        Worker worker = new Worker();
        Soldier soldier = new Soldier();
        this.species.setRatio(worker, 30);
        this.species.setRatio(soldier, 70);
        assertEquals(30/(double) 100, this.species.getRealRatio(worker));
        assertEquals(70/(double) 100, this.species.getRealRatio(soldier));
    }
}