package model.data;

import model.abstracts.AntRole;
import model.abstracts.State;
import model.world.Pheromone;

import java.util.HashMap;
import java.util.Map;

/**
 * A species contains all the data of a an ant evolution. Each species have a specific behavior.
 */
public class Species {
    //TODO Add all states and role to the specie.
    /**
     * Name of the species.
     */
    private String name;
    /**
     * Weight in g of the ants.
     */
    private int weight;
    /**
     * Size of the territory?
     */
    private int territory;
    /**
     * Time she can stay outside.
     */
    private int stamina;
    
    /**
     * Growth times in hours.
     */
    private Map<State, Integer> growths;
    /**
     * Ratio of ant in this specie. The queen try to have the good amount of ant/total ratios
     */
    private Map<AntRole, Integer> ratios;
    
    /**
     * Pheromone of a ant.
     */
    private Pheromone pheromone;
    
    /**
     * Default species constructor.
     */
    public Species(String name, int weight, int territory, int stamina){
        this.name = name;
        this.weight = weight;
        this.territory = territory;
        this.stamina = stamina;
        this.growths = new HashMap<>();
        this.ratios = new HashMap<>();
        this.pheromone = new Pheromone();
    }

    public String getName(){
        return this.name;
    }
    
    public int getWeight(){
        return this.weight;
    }
    
    public int getFoodConsumption(){
        return (this.getWeight() / 3);
    }

    public int getTerritory(){
        return this.territory;
    }

    public int getStamina(){
        return this.stamina;
    }

    /**
     * Set the growth of a specific state.
     * @param state The state.
     * @param growth Growth time in minutes.
     */
    public void setGrowth(State state , int growth){
        for (Map.Entry<State, Integer > e : this.growths.entrySet()){
            if(e.getKey().equals(state)) {
                e.setValue(growth);
                return;
            }
        }
        this.growths.put(state, growth);
    }
    /**
     * Set the growth of a specific state.
     * @param state The state.
     * @param growthDays Growth time in days.
     */
    public void setGrowthDays(State state , int growthDays){
        this.setGrowth(state, growthDays * 60 * 24);
    }

    /**
     * Get the growth time of a specific state.
     * @param state The state.
     * @return The time in minutes.
     */
    public int getGrowth(State state){
        for (Map.Entry<State, Integer > e : this.growths.entrySet()){
            if(e.getKey().equals(state)) return e.getValue();
        }
        return 0;
    }


    /**
     * Set the ratio of specific role for an ant. The sum of ratio is reported to 1 for get the real ratio.
     * @param role The role.
     * @param ratio The ratio.
     */
    public void setRatio(AntRole role, int ratio){
        for (Map.Entry<AntRole, Integer > e : this.ratios.entrySet()){
            if(e.getKey().equals(role)) {
                e.setValue(ratio);
                return;
            }
        }
        this.ratios.put(role, ratio);
    }

    /**
     * Get the ratio of specific role for an ant.
     * @param role The role.
     * @return Returns the current value of the ratio. Use {@link #getRealRatio(AntRole role) getRealRatio}
     * for get the real ratio.
     */
    public int getRatio(AntRole role){
        for (Map.Entry<AntRole, Integer > e : this.ratios.entrySet()){
            if(e.getKey().equals(role)) return e.getValue();
        }
        return 0;
    }

    /**
     * Get the total of ratios for all roles combined.
     * @return The total of ratios.
     */
    public int getTotalRatio(){
        int total = 0;
        for (Integer v : ratios.values()) {
            total += v;
        }
        return total;
    }

    public Pheromone getPheromone(){
        return this.pheromone;
    }
    /**
     * Get the ratio reported to ratio/1 for the specific role.
     * @param role The role to find the ratio.
     * @return The resulted ratio 0 to ratio to 1
     */
    public double getRealRatio(AntRole role){
        return this.getRatio(role) / (double) getTotalRatio();
    }
}
