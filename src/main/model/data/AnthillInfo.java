package main.model.data;

import main.model.entities.Ant;
import main.model.enums.EType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Anthill infos are here for get the info of the current antHill. This includes all ants
 * borned in this anthill.
 */
public class AnthillInfo {

    /**
     * The map of ants. Each type have their own list.
     */
    private Map<EType, List<Ant>> ants;

    public AnthillInfo() {
        this.ants = new HashMap<>();
    }

    /**
     * Add an ant to the map. The methods search for the ant type and add to the
     * corresponded list.
     * @param ant The ant to add.
     */
    public void addAnt(Ant ant) {
        EType type = ant.getType();
        ants.computeIfAbsent(type, k -> new ArrayList<>());
        ants.get(type).add(ant);
    }

    /**
     * Remove the ant to the map. The methods remove a specific ant. Be careful, the ant have
     * to be to the same type of when she's add.
     * @param ant The ant to remove.
     */
    public void removeAnt(Ant ant) {
        EType type = ant.getType();
        if (ants.get(type) == null) return;
        ants.get(type).remove(ant);
    }

    /**
     * Get ants of a specific type ( when it's registered )/
     * @param type The type of the ant.
     * @return The list of the ant.
     */
    public  List<Ant> getAnt(EType type){
        return this.ants.get(type);
    }
}
