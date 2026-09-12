package hva.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Animal implements Serializable{
    private static final long serialVersionUID = 202407081733L;

    private String _id;
    private String _name;
    private String _speciesId;
    private String _habitatId;
    private List<Vaccination> _vaccinations = new ArrayList<>();
    private List<VaccinationResult> _healthHistory = new ArrayList<>();
    
    /**
     * Animal instance constructor.
     * 
     * @param id animal id
     * @param name animal name
     * @param speciesId animal species id
     * @param habitatId animal habitat id
     **/
    public Animal(String id, String name, String speciesId, String habitatId){
        _id = id;
        _name = name;
        _speciesId = speciesId;
        _habitatId = habitatId;
    }

    /**
     * Calculates the Animal's satisfaction.
     * 
     * @param habitat habitat where the animal is located
     * @param sameSpecies number of animals within the habitat that have the same species as this instance
     * @param differentSpecies number of animals within the habitat that have a different species from this instance
     **/
    public float calculateSatisfaction(Habitat habitat, int sameSpecies, int differentSpecies){
        float totalSatisfaction;
        int population, influence, area;

        population = habitat.getAnimals().size();
        area = habitat.getArea();
        influence = habitat.getInfluence(_speciesId);

        totalSatisfaction = 20 + 3*sameSpecies - 2*differentSpecies + area/population + influence;
        return totalSatisfaction;
    }

    /**
    * Builds a line containing information about the animal.
    * @return line with information about the animal
    **/
    public String addLine() {
        if (_healthHistory.size() == 0) {
            return "ANIMAL|" + getId() + "|" + getName() + "|" + getSpeciesId() + "|VOID" + "|" + getHabitatId();
        } else {
            List<String> healthHistoryString = _healthHistory.stream()
                .map(VaccinationResult::name)
                .collect(Collectors.toList());
            Collections.sort(healthHistoryString, String.CASE_INSENSITIVE_ORDER);
            return "ANIMAL|" + getId() + "|" + getName() + "|" + getSpeciesId() + "|" + String.join(",", healthHistoryString) + "|" + getHabitatId();
        }
    }
    
    
    public String getId(){return _id;}
    public String getName(){return _name;}
    public String getSpeciesId(){ return _speciesId;}
    public String getHabitatId(){return _habitatId;}
    public List<Vaccination> getVaccinations(){return _vaccinations;}
    public List<VaccinationResult> getHealthHistory(){return _healthHistory;}
    public void setHabitat(String habitatId){_habitatId = habitatId;}
    public void addVaccination(Vaccination vaccination){_vaccinations.add(vaccination);}
    public void addHealthHistory(VaccinationResult result){_healthHistory.add(result);}
}