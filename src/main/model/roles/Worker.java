package main.model.roles;

import main.model.abstracts.AntRole;
import main.model.data.Species;
import main.model.entities.Ant;
import main.model.enums.EType;
import main.model.world.Pheromone;
import main.model.world.Tile;

import java.util.ArrayList;
import java.util.List;

public class Worker extends AntRole {

    private Tile fromTile;
    private List<Tile> fromTiles;
    private int minutesSinceIsInPrey;


    public Worker() {
        fromTiles = new ArrayList<>();
        minutesSinceIsInPrey = 0;
    }

    //TODO doc this.
    //TODO factoriser.
    @Override
    public void action(Ant ant) {
        Species antSpecies = ant.getSpecies();
        int antStamina = ant.getStamina();
        int speciesStamina = antSpecies.getStamina();
        Pheromone speciesPheromone = antSpecies.getHomePheromone();
        Pheromone foodPheromone = antSpecies.getFoodPheromone();
        boolean useExploreForGoHome = false;
        if (Math.random() < 0.001) { // AFK rate
            return;
        }
        if (ant.getTile().contains(EType.PREY)) {
            minutesSinceIsInPrey++;
            ant.getTile().addPheromone(foodPheromone, antSpecies.getPheromoneDropRate() * 200);
            if (minutesSinceIsInPrey > 160) {
                this.moveTowardHome(ant);
                minutesSinceIsInPrey = 0;
            }
        } else if (ant.getTile().equals(ant.getHome())) {
            this.house(ant);
        } else if (ant.getTile().getFood() > 0 && ant.getCarriedFood() < ant.getSpecies().getMaxCarry()) {
            this.carry(ant);
        } else if (ant.getCarriedFood() > 0) {
            if (useExploreForGoHome) bringFood(ant);
            else this.moveTowardHome(ant);
            //ant.getTile().addPheromone(speciesPheromone, antSpecies.getPheromoneDropRate());
            ant.getTile().addPheromone(foodPheromone, antSpecies.getPheromoneDropRate() * 200);
        } else if (antStamina < speciesStamina / 2) {
            if (useExploreForGoHome) bringFood(ant);
            else this.moveTowardHome(ant);
            ant.getTile().addPheromone(speciesPheromone, antSpecies.getPheromoneDropRate());
        } else {
            ant.getTile().addPheromone(speciesPheromone, (int) (antSpecies.getPheromoneDropRate() * (ant.getStamina() / (double) ant.getSpecies().getStamina())));
            this.explore(ant);
        }

    }

    @Override
    public EType getType() {
        return EType.WORKER;
    }

    private void carry(Ant ant) {
        Tile antTile = ant.getTile();
        Species antSpecies = ant.getSpecies();
        int amountOfFood = antTile.getFood();

        antTile.removeFood(amountOfFood);
        ant.addCarriedFood(amountOfFood);

        if (ant.getCarriedFood() > antSpecies.getMaxCarry()) {
            int foodToRemove = ant.getCarriedFood() - antSpecies.getMaxCarry();
            ant.getTile().setFood(foodToRemove);
            ant.setCarriedFood(antSpecies.getMaxCarry());
        }
    }

    private void house2(Ant ant) {

    }

    private void house(Ant ant) {
        if (ant.getCarriedFood() > 0) {
            ant.getTile().addFood(ant.getCarriedFood());
            ant.setCarriedFood(0);
        }
        int speciesStamina = ant.getSpecies().getStamina();
        this.fromTiles = new ArrayList<>();
        ant.setStamina(speciesStamina);
        float chanceToGoOutside = 1 / (float) (speciesStamina / 2);
        if (Math.random() < chanceToGoOutside) {
            ant.consume(ant.getSpecies().getFoodConsumption() - ant.getFood());
            explore(ant);
        }
    }

    private void explore(Ant ant) {
        this.explore(ant, false);
    }

    private void explore(Ant ant, boolean home) {
        if (this.fromTiles.size() == 0) {
            this.fromTiles.add(ant.getHome());
        }
        Species species = ant.getSpecies();
        Pheromone homePheromone = species.getHomePheromone();
        Pheromone foodPheromone = species.getFoodPheromone();
        Tile northTile = ant.getTile().getNorthTile();
        Tile eastTile = ant.getTile().getEastTile();
        Tile southTile = ant.getTile().getSouthTile();
        Tile westTile = ant.getTile().getWestTile();
        if (fromTile == null) {
            fromTile = ant.getTile();
        }
        int chanceToGoNorth = 0;
        int chanceToGoEast = 0;
        int chanceToGoSouth = 0;
        int chanceToGoWest = 0;
        /* Add random behaviour */
        int randomMovementRate = species.getRandomMovementRate();
        if (home) randomMovementRate = 0;
        chanceToGoNorth += randomMovementRate;
        chanceToGoEast += randomMovementRate;
        chanceToGoSouth += randomMovementRate;
        chanceToGoWest += randomMovementRate;
        /* Add linear behaviour to chances. */
        int linearMovementRate = species.getLinearMovementRate();
        if (home) linearMovementRate = 0;
        if (fromTile.equals(northTile)) {
            chanceToGoSouth += linearMovementRate;
        }
        if (fromTile.equals(eastTile)) {
            chanceToGoWest += linearMovementRate;
        }
        if (fromTile.equals(southTile)) {
            chanceToGoNorth += linearMovementRate;
        }
        if (fromTile.equals(westTile)) {
            chanceToGoEast += linearMovementRate;
        }
        /* Add pheromones if tile exist, chance = 0 otherwise */
        int foodFactor = 4;
        int goHomeFactor = 20;
        if (this.fromTiles.contains(northTile) || northTile == null) {
            chanceToGoNorth = 0;
        } else {
            if (!home) {
                chanceToGoNorth += northTile.getPheromoneRate(homePheromone);
                chanceToGoNorth += northTile.getPheromoneRate(foodPheromone) * foodFactor;
                if (northTile.getFood() > 0 && !northTile.contains(EType.ANTHILL))
                    chanceToGoNorth += 100000000;
            } else {
                chanceToGoNorth += northTile.getPheromoneRate(homePheromone) * goHomeFactor;
            }
        }
        if (this.fromTiles.contains(eastTile) || eastTile == null) {
            chanceToGoEast = 0;
        } else {
            if (!home) {
                chanceToGoEast += eastTile.getPheromoneRate(homePheromone);
                chanceToGoEast += eastTile.getPheromoneRate(foodPheromone) * foodFactor;
                if (eastTile.getFood() > 0 && !eastTile.contains(EType.ANTHILL))
                    chanceToGoEast += 100000000;
            } else {
                chanceToGoEast += eastTile.getPheromoneRate(homePheromone) * goHomeFactor;
            }
        }
        if (this.fromTiles.contains(southTile) || southTile == null) {
            chanceToGoSouth = 0;
        } else {
            if (!home) {
                chanceToGoSouth += southTile.getPheromoneRate(homePheromone);
                chanceToGoSouth += southTile.getPheromoneRate(foodPheromone) * foodFactor;
                if (southTile.getFood() > 0 && !southTile.contains(EType.ANTHILL))
                    chanceToGoSouth += 100000000;
            } else {
                chanceToGoSouth += southTile.getPheromoneRate(homePheromone) * goHomeFactor;
            }
        }
        if (this.fromTiles.contains(westTile) || westTile == null) {
            chanceToGoWest = 0;
        } else {
            if (!home) {
                chanceToGoWest += westTile.getPheromoneRate(homePheromone);
                chanceToGoWest += westTile.getPheromoneRate(foodPheromone) * foodFactor;
                if (westTile.getFood() > 0 && !westTile.contains(EType.ANTHILL))
                    chanceToGoWest += 100000000;
            } else {
                chanceToGoWest += westTile.getPheromoneRate(homePheromone) * goHomeFactor;
            }
        }
        /* Calculate chances */
        int totalChance = chanceToGoNorth + chanceToGoEast + chanceToGoSouth + chanceToGoWest;
        if (totalChance == 0) {
            if (northTile != null) chanceToGoNorth = 10;
            if (eastTile != null) chanceToGoEast = 10;
            if (southTile != null) chanceToGoSouth = 10;
            if (westTile != null) chanceToGoWest = 10;
            totalChance = chanceToGoNorth + chanceToGoEast + chanceToGoSouth + chanceToGoWest;
        }
        if (home) {
            if (northTile != null && northTile.equals(ant.getHome())) {
                ant.getTile().moveTo(ant, northTile);
                return;
            } else if (eastTile != null && eastTile.equals(ant.getHome())) {
                ant.getTile().moveTo(ant, eastTile);
                return;
            } else if (southTile != null && southTile.equals(ant.getHome())) {
                ant.getTile().moveTo(ant, southTile);
                return;
            } else if (westTile != null && westTile.equals(ant.getHome())) {
                ant.getTile().moveTo(ant, westTile);
                return;
            }

        }
        this.fromTile = ant.getTile();
        moveToTileByChance(ant, chanceToGoNorth, chanceToGoEast, chanceToGoSouth, chanceToGoWest);
        if (!home && this.fromTiles.size() > 10) {
            this.fromTiles.remove(1);
        }
    }

    public void bringFood(Ant ant) {
        Tile northTile = ant.getTile().getNorthTile();
        Tile eastTile = ant.getTile().getEastTile();
        Tile southTile = ant.getTile().getSouthTile();
        Tile westTile = ant.getTile().getWestTile();

        Pheromone homePheromone = ant.getSpecies().getHomePheromone();

        Tile max = null;
        max = morePheromoneTile(max, westTile, homePheromone);
        max = morePheromoneTile(max, northTile, homePheromone);
        max = morePheromoneTile(max, eastTile, homePheromone);
        max = morePheromoneTile(max, southTile, homePheromone);
        if (max != null) {
            ant.getTile().moveTo(ant, max);
        } else {
            moveToTileByChance(ant, 1, 1, 1, 1);
        }
    }

    public Tile morePheromoneTile(Tile tileA, Tile tileB, Pheromone p) {
        if (tileA == null) {
            return tileB;
        }
        if (tileB == null) {
            return tileA;
        }
        if (tileA.getPheromoneRate(p) <= tileB.getPheromoneRate(p)) return tileB;
        else return tileA;
    }

    public void moveToTileByChance(Ant ant, int north, int east, int south, int west) {
        Tile northTile = ant.getTile().getNorthTile();
        Tile eastTile = ant.getTile().getEastTile();
        Tile southTile = ant.getTile().getSouthTile();
        Tile westTile = ant.getTile().getWestTile();
        double total = north + east + south + west;
        double northRate = north / total;
        double eastRate = east / total;
        double southRate = south / total;
        double westRate = west / total;
        double rng = Math.random();
        if (rng < northRate) {
            ant.getTile().moveTo(ant, northTile);
            this.fromTiles.add(northTile);
        } else if (rng < eastRate + northRate) {
            ant.getTile().moveTo(ant, eastTile);
            this.fromTiles.add(eastTile);
        } else if (rng < southRate + eastRate + northRate) {
            ant.getTile().moveTo(ant, southTile);
            this.fromTiles.add(southTile);
        } else {
            ant.getTile().moveTo(ant, westTile);
            this.fromTiles.add(westTile);
        }
    }

}
