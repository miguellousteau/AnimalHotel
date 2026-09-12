package hva.core;

import java.lang.Math;

public class DeciduousTree extends Tree {
    private static final long serialVersionUID = 202407081733L;
    
    public DeciduousTree(String id, String name, int age, int difficulty, Season season){
        super(id, name, age, difficulty, season);
    }

    /**
    * Calculates and returns the current biological cycle of the tree.
    * 
    * @return current biological cycle of the tree
    **/
    public String getBiologicalCycle(){
        switch(_season){
            case SPRING:
                return "LEAVING";
            case SUMMER:
                return "WITHLEAVES";
            case AUTUMN:
                return "SHEDDING";
            case WINTER:
                return "WITHOUTLEAVES";
            default: return null;
        }
    }

    /**
    * Returns the type of the tree instance.
    * 
    * @return tree type
    **/
    public String getType(){return "DECIDUOUS";}

    /**
    * Calculates and returns the tree cleaning difficulty.
    * 
    * @return tree cleaning difficulty
    **/    
    public float calculateCleaningEffort(){
        float seasonalEffort;
        switch(_season){
            case SPRING: seasonalEffort = 1; break;
            case SUMMER: seasonalEffort = 2; break;
            case AUTUMN: seasonalEffort = 5; break;
            default: seasonalEffort = 0; break;
        }
        return _difficulty * seasonalEffort * (float)Math.log(_age + 1);
    }

}