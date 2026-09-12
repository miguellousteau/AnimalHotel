package hva.core;

public class EvergreenTree extends Tree {
    private static final long serialVersionUID = 202407081733L;

    public EvergreenTree(String id, String name, int age, int difficulty, Season season){
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
                return "WITHLEAVES";
            case WINTER:
                return "SHEDDING";
            default: return null;
        }
    }
    
    /**
    * Returns the type of the tree instance.
    * 
    * @return tree type
    **/
    public String getType(){return "EVERGREEN";}

    /**
    * Calculates and returns the tree cleaning difficulty.
    * 
    * @return tree cleaning difficulty
    **/
    public float calculateCleaningEffort(){
        float seasonalEffort;
        switch(_season){
            case WINTER: seasonalEffort = 2; break;
            default: seasonalEffort = 1; break;
        }
        return _difficulty * seasonalEffort * (float)Math.log(_age + 1);
    }
}