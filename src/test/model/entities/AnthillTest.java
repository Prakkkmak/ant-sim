package test.model.entities;

import main.model.data.Species;
import main.model.entities.Anthill;
import main.model.world.Tile;
import main.model.world.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnthillTest {

    Anthill theQueen;

    @BeforeEach
    void setUp(){
        this.theQueen = new Anthill(new Tile(new World(10,10),5,5), new Species("testSpecies",2500,300,800));
    }

    @Test
    void createEgg() {

    }

    @Test
    void getLastEggingDay() {
    }

    @Test
    void setLastEggingDay() {
    }

    @Test
    void getSpecies() {
    }

    @Test
    void setSpecies() {
    }

    @Test
    void getInfos() {
    }

    @Test
    void addAnt() {
    }

    @Test
    void getType() {
    }

    @Test
    void update() {
    }

    @Test
    void accept() {
    }
}