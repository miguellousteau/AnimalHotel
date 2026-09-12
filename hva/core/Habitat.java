package hva.core;

import java.io.Serializable;
import java.util.*;

public class Habitat implements Serializable{
    private static final long serialVersionUID = 202407081733L;
    
    private String _id;
    private String _name;
    private int _area;
    private List<String> _animals = new ArrayList<>();
    private List<String> _treeIds = new ArrayList<>();
    private Set<String> _positive = new HashSet<>();
    private Set<String> _negative = new HashSet<>();

    /**
     * Habitat instance constructor.
     * 
     * @param id habitat id
     * @param name habitat name
     * @param area habitat area
     **/
    public Habitat(String id, String name, int area){
        _id = id;
        _name = name;
        _area = area;
    }
    
    /**
     * Builds a line containing information about the habitat.
     * @return line with information about the habitat
     **/
    public String addLine() {
        return "HABITAT|" + getId() + "|" + getName() + "|" + getArea() + "|" + getTreeIds().size();
    }

    public void addTree(String treeId){_treeIds.add(treeId);}
    public String getId(){return _id;}
    public String getName(){return _name;}
    public int getArea(){return _area;}
    public List<String> getAnimals(){return _animals;}
    public void addAnimal(String id){_animals.add(id);}
    public void removeAnimal(String id){_animals.remove(id);}
    public void setArea(int area){_area = area;}
    public List<String> getTreeIds(){return _treeIds;}
    public Set<String> getPositiveSpecies(){return _positive;}
    public Set<String> getNegativeSpecies(){return _negative;}
    public int getInfluence(String speciesId){
        for(String id: _positive)
            if (id.equals(speciesId)) return 20;
        for(String id: _negative)
            if (id.equals(speciesId)) return -20;
        return 0;
    }

    /**
    * Changes the influence of a habitat on a species to positive.
    * 
    * @param speciesId species id
    **/
    public void addPositiveSpecies(String speciesId){
        _negative.remove(speciesId);
        _positive.add(speciesId);
    }

    /**
    * Changes the influence of a habitat on a species to negative.
    * 
    * @param speciesId species id
    **/
    public void addNegativeSpecies(String speciesId){
        _positive.remove(speciesId);
        _negative.add(speciesId);
    }

    /**
    * Changes the influence of a habitat on a species to neutral.
    * 
    * @param speciesId species id
    **/
    public void addNeutralSpecies(String speciesId){
        _positive.remove(speciesId);
        _negative.remove(speciesId);
    }
}