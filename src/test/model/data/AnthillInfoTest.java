package test.model.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import main.model.data.AnthillInfo;
import main.model.states.Egg;
import main.model.entities.Ant;
import main.model.enums.EType;
import main.model.factories.AntFactory;
import main.model.factories.SpeciesFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnthillInfoTest {
    AnthillInfo anthillInfo;
    @BeforeEach
    void setUp(){
        this.anthillInfo = new AnthillInfo();
    }
    @Test
    void addRemoveAnt() {
        assertNull(this.anthillInfo.getAnt(EType.EGG));
        AntFactory antFactory = new AntFactory(SpeciesFactory.CreateDefaultSpecies());
        Ant ant = antFactory.createAnt(new Egg());
        this.anthillInfo.addAnt(ant);
        assertEquals(1, this.anthillInfo.getAnt(EType.EGG).size());
        this.anthillInfo.removeAnt(ant);
        assertEquals(0, this.anthillInfo.getAnt(EType.EGG).size());
    }
}