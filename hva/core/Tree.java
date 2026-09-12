package hva.core;

import java.io.Serializable;

public abstract class Tree implements Serializable{
    private static final long serialVersionUID = 202407081733L;

    protected String _id;
    protected String _name;
    protected int _age;
    protected int _difficulty;
    protected Season _season;

    /**
    * Animal instance constructor.
    * 
    * @param id tree id
    * @param name tree name
    * @param age tree age
    * @param difficulty base tree cleaning difficulty
    * @param season season in which the tree will be created
    **/
    public Tree(String id, String name, int age, int difficulty, Season season){
        _id = id;
        _name = name;
        _age = age;
        _difficulty = difficulty;
        _season = season;
    }
    
    /**
    * Calculates and returns the current biological cycle of the tree.
    * 
    * @return current biological cycle of the tree
    **/
    public abstract String getBiologicalCycle();

    /**
    * Builds a line containing information about the tree.
    * @return line with information about the tree
    **/
    public String addLine() {
        return "TREE|" + _id + "|" + _name + "|" + _age + "|" + _difficulty + "|" + getType() + "|" + getBiologicalCycle();
    }

    public String getId(){return _id;}
    public String getName(){return _name;}
    public int getAge(){return _age;}
    public int getDifficulty(){return _difficulty;}
    public Season getSeason(){return _season;}
    public void setSeason(Season newSeason){_season = newSeason;}

    /**
    * Calculates and returns the tree cleaning difficulty.
    * 
    * @return tree cleaning difficulty
    **/
    public abstract float calculateCleaningEffort();

    /**
    * Returns the type of the tree instance.
    * 
    * @return tree type
    **/
    public abstract String getType();
}