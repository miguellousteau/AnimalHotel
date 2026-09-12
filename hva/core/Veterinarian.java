package hva.core;

import static java.lang.Math.max;
import java.util.*;

public class Veterinarian extends Employee {
    private static final long serialVersionUID = 202407081733L;

    private List<Vaccination> _vaccinations = new ArrayList<>();

    /**
    * Veterinarian instance constructor.
    * 
    * @param id veterinarian id
    * @param name veterinarian name
    * @param hotel hotel where the veterinarian was created
    **/
    public Veterinarian(String id, String name, Hotel hotel){super(id, name, hotel);}

    @Override
    /**
    * Builds a line containing information about the veterinarian.
    * @return line with information about the veterinarian
    **/
    public String addLine() {
        if (_responsibilities.isEmpty()) {
            return "VET|" + getId() + "|" + getName();
        } else {
            List<String> sortedResponsibilities = new ArrayList<>(_responsibilities);
            sortedResponsibilities.sort(String.CASE_INSENSITIVE_ORDER);
            return "VET|" + getId() + "|" + getName() + "|" + String.join(",", sortedResponsibilities);
        }
    }
 
    /**
     * Calculates and returns the veterinarian's satisfaction.
     * 
     * @return veterinarian satisfaction
     **/
    public float calculateSatisfaction(){
        float veterinarians, population;
        float satisfaction = 20;
        HashMap<Float, Float> map = new HashMap<>();

        for(String responsibility: _responsibilities){
          veterinarians = 0;
          for (Employee employee: _hotel.getEmployees())
            if(employee.getResponsibilities().contains(responsibility))
              veterinarians++;
          population = _hotel.getSpeciesById(responsibility).getAnimals().size();

          map.put(population, veterinarians);
        }

        for (Map.Entry<Float, Float> entry: map.entrySet()){
            population = entry.getKey();
            veterinarians = entry.getValue();
            satisfaction -= population/veterinarians;
        }
        return satisfaction;
    }
  
    /**
    * Returns the type of the veterinarian instance.
    * 
    * @return String "VET"
    **/
    public String getType(){return "VET";}

    /**
    * Adds a responsibility to the veterinarian and returns whether it succeeded.
    * 
    * @return success of the operation
    **/
    public boolean addResponsibility(String responsibility){
        if(_hotel.getSpeciesById(responsibility) == null) return false;

        _responsibilities.add(responsibility);
        return true;
    }

    public List<Vaccination> getVaccinations(){return _vaccinations;}

    public void addVaccination(Vaccination vaccination){_vaccinations.add(vaccination);}

    /**
    * Vaccinates an animal.
    * 
    * @param animal animal to be vaccinated
    * @param vaccine vaccine to be administered to the animal
    * @param species species of the animal to be vaccinated
    * @param speciesNames list of names of the species the vaccine can correctly vaccinate
    * @param correct represents whether the animal is being vaccinated with the correct vaccine or not
    **/
    public void vaccinate(Animal animal, Vaccine vaccine, Species species, List<String> speciesNames, Boolean correct){
        int damage;
        if(correct){damage = 0;}
        else {damage = damage(vaccine, animal, species, speciesNames);}
        vaccine.apply(animal, damage, correct);
    }

    /**
    * Calculates and returns the damage an animal will receive from the vaccination.
    * 
    * @param vaccine vaccine used for the animal's vaccination
    * @param animal animal to be vaccinated
    * @param species species of the animal to be vaccinated
    * @param speciesNames list of names of the species the vaccine can correctly vaccinate
    * @return damage the animal will receive
    **/
    public int damage(Vaccine vaccine, Animal animal, Species species, List<String> speciesNames){
        int nameLength, commonCharacters;
        List<Integer> values = new ArrayList<>();
        for (String speciesName: speciesNames){
            nameLength = nameLength(species.getName(), speciesName);
            commonCharacters = commonCharacters(species.getName(), speciesName);
            values.add(nameLength - commonCharacters);
        }
        return Collections.max(values);
    }

    /**
    * Determines the maximum length between the names of two species.
    * 
    * @param speciesName1 name of the first species
    * @param speciesName2 name of the second species
    * @return maximum between two species names
    **/
    public int nameLength(String speciesName1, String speciesName2){
        return max(speciesName1.length(), speciesName2.length());
    }

    /**
    * Determines the number of characters that the names of two species have in common.
    * 
    * @param speciesName1 name of the first species
    * @param speciesName2 name of the second species
    * @return number of common characters
    **/
    public int commonCharacters(String speciesName1, String speciesName2){
        int num = 0;
        Set<Character> set1 = new HashSet<>();
        for (char c : speciesName1.toCharArray()) {
            set1.add(c);
        }
        for (char c : speciesName2.toCharArray()) {
            if (set1.contains(c)) {
                num++;
                set1.remove(c); 
            }
        }
        return num;
    }
}