package hva.core;

import java.io.Serializable;
import java.util.*;

public class Vaccine implements Serializable {
    private static final long serialVersionUID = 202407081733L;

    private String _id;
    private String _name;
    private String[] _speciesIds;
    private List<Vaccination> _vaccinations = new ArrayList<>();
    
    /**
     * Vaccine instance constructor.
     * 
     * @param id vaccine id
     * @param name vaccine name
     * @param speciesIds ids of the species the vaccine is intended for
     **/
    public Vaccine(String id, String name, String[] speciesIds){
        _id = id;
        _name = name;
        _speciesIds = speciesIds;
    }

    /**
    * Builds a line containing information about the vaccine.
    * @return line with information about the vaccine
    **/
    public String addLine() {
        if (getSortedSpeciesIds().isEmpty())
            return "VACCINE|" + _id + "|" + _name + "|" + _vaccinations.size();
        else
            return "VACCINE|" + _id + "|" + _name + "|" + _vaccinations.size() + "|" + String.join(",", getSortedSpeciesIds());
    }

    public String getId(){return _id;}
    public String getName(){return _name;}
    public String[] getSpeciesIds(){return _speciesIds;}
    public List<Vaccination> getVaccinations(){return _vaccinations;}
    public void addVaccination(Vaccination vaccination){_vaccinations.add(vaccination);}

    /**
    * Applies the vaccine according to the vaccination result.
    * 
    * @param animal animal that will receive the vaccination
    * @param damage amount of damage the animal will receive
    * @param correct represents whether the animal was vaccinated correctly
    **/
    public void apply(Animal animal, int damage, Boolean correct){
        switch(damage){
            case 0:
                if (correct) {
                    animal.addHealthHistory(VaccinationResult.NORMAL);
                    break;
                }
                else{animal.addHealthHistory(VaccinationResult.CONFUSION);}
                    break;
            case 1:
            case 2:
            case 3:
            case 4:
                animal.addHealthHistory(VaccinationResult.ACCIDENT);
                break;
            default:
                animal.addHealthHistory(VaccinationResult.ERROR);
        }
    }

    /**
    * Sorts the vaccine's species ids alphabetically, ignoring capitalization.
    * 
    * @return sorted species ids separated by commas
    **/
    public String getSortedSpeciesIds(){
        String[] sortedIds = _speciesIds.clone();
        Arrays.sort(sortedIds, String.CASE_INSENSITIVE_ORDER);
        return String.join(", ", sortedIds);
    }
}