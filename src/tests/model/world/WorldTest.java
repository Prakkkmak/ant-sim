package tests.model.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.world.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WorldTest {

  private World world;

  @BeforeEach
  void setUp() {
    world = new World(10, 10);
  }

  @Test
  void testGetSize() {
    assertEquals(10, world.getSizeX());
    assertEquals(10, world.getSizeX());
  }

  @Test
  void testGetTileContent() {
    assertEquals(0, world.getTileContent(0, 0));
    world.createSoldier(0, 0);
    assertEquals(1, world.getTileContent(0, 0));
  }
}