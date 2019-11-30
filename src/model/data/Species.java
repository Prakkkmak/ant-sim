package model.data;

import model.abstracts.AntRole;
import model.abstracts.State;

import java.util.HashMap;
import java.util.Map;

/**
 * A species contains all the data of a an ant evolution. Each species have a specific behavior.
 */
public class Species {
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
     * Default species constructor.
     */
    public Species(String name, int weight, int territory, int stamina){
        this.name = name;
        this.weight = weight;
        this.territory = territory;
        this.stamina = stamina;
        this.growths = new HashMap<>();
        this.ratios = new HashMap<>();
    }

    public String getName(){
        return this.name;
    }

    public int getWeight(){
        return this.weight;
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
     * @param growthDay Growth time in days.
     */
    public void setGrowth(State state , int growthDay){
        this.growths.put(state, growthDay * 24);
    }

    /**
     * Get the growth time of a specific state.
     * @param state The state.
     * @return The time in days.
     */
    public int getGrowth(State state){
        return this.growths.get(state) / 24;
    }


    /**
     * Set the ratio of specific role for an ant. The sum of ratio is reported to 1 for get the real ratio.
     * @param role The role.
     * @param ratio The ratio.
     */
    public void setRatio(AntRole role, int ratio){
        this.ratios.put(role, ratio);
    }

    /**
     * Get the ratio of specific role for an ant.
     * @param role The role.
     * @return Returns the current value of the ratio. Use {@link #getRealRatio(AntRole role) getRealRatio}
     * for get the real ratio.
     */
    public int getRatio(AntRole role){
        return this.ratios.get(role);
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

    /**
     * Get the ratio reported to ratio/1 for the specific role.
     * @param role The role to find the ratio.
     * @return The resulted ratio 0 < ratio < 1
     */
    public double getRealRatio(AntRole role){
        return ratios.get(role) / (double) getTotalRatio();
    }
}
