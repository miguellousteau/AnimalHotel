package hva.core;

import java.io.Serializable;
import java.util.*;

public class Species implements Serializable{
    private static final long serialVersionUID = 202407081733L;
    private String _id;
    private String _name;
    private List<String> _animals = new ArrayList<>();

    /**
     * Species instance constructor.
     * 
     * @param id species instance id
     * @param name species instance name
     **/
    public Species(String id, String name){
        _id = id;
        _name = name;
    }

    public String getId(){return _id;}
    public String getName(){return _name;}
    public List<String> getAnimals(){return _animals;}
    public void addAnimal(String animal){_animals.add(animal);}
}