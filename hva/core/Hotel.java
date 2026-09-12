package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**Name of the file associated with the hotel*/
  private String _fileName;

  /**List of habitats registered in the hotel*/
  private final List<Habitat> _habitats = new ArrayList<>();

  /**List of animals registered in the hotel*/
  private final List<Animal> _animals = new ArrayList<>();

  /**List of employees registered in the hotel*/
  private final List<Employee> _employees = new ArrayList<>();

  /**List of species registered in the hotel*/
  private final List<Species> _species = new ArrayList<>();

  /**List of vaccines registered in the hotel*/
  private final List<Vaccine> _vaccines = new ArrayList<>();

  /**List of trees registered in the hotel*/
  private final List<Tree> _trees = new LinkedList<>();

  /**List of vaccinations registered in the hotel*/
  private final List<Vaccination> _vaccinations = new ArrayList<>();

  /**Current season of the hotel*/
  private Season _currentSeason = Season.SPRING;

  /**Saved state of the hotel*/
  private Boolean _savedState = true;

  /**
   * Reads a text input file and creates corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO error while processing the text file
   **/
  void importFile(String filename) throws UnrecognizedEntryException, IOException {
    Parser parser = new Parser(this);
    parser.parseFile(filename);
  }
  
  /**
   * Creates a new animal and registers it.
   * 
   * @param animalId id of the animal to register
   * @param name name of the animal to register
   * @param speciesId id of the animal's species
   * @param habitatId id of the habitat where the animal is placed
   * @throws DuplicateAnimalKeyExceptionCore if the animal already exists in the program
   * @throws UnknownHabitatKeyExceptionCore if the habitat does not exist
   **/
  public void registerAnimal(String animalId, String name, String speciesId, String habitatId) throws DuplicateAnimalKeyExceptionCore, UnknownHabitatKeyExceptionCore {
      if (getAnimalById(animalId) != null) {throw new DuplicateAnimalKeyExceptionCore();}
      if (getHabitatById(habitatId) == null) {throw new UnknownHabitatKeyExceptionCore();}

      Animal newAnimal = new Animal(animalId, name, speciesId, habitatId);
      _animals.add(newAnimal);
      
      getHabitatById(habitatId).addAnimal(animalId);
      getSpeciesById(speciesId).addAnimal(animalId);
  }
 
  /**
   * Creates a new species and registers it.
   * 
   * @param speciesId id of the animal's species
   * @param name name of the species to register
   **/
  public void registerSpecies(String speciesId, String name) {
    Species newSpecies = new Species(speciesId, name);
    _species.add(newSpecies);
  }

  /**
   * Creates a new employee and registers it.
   * 
   * @param employeeId id of the employee to register
   * @param name name of the employee to register
   * @param empType type of employee to register
   * @throws DuplicateEmployeeKeyExceptionCore if the employee already existed
   **/
  public void registerEmployee(String employeeId, String name, String empType) throws DuplicateEmployeeKeyExceptionCore {
    Employee newEmployee;

    if (getEmployeeById(employeeId) != null) throw new DuplicateEmployeeKeyExceptionCore();

    if (empType.equals("VET")) {
        newEmployee = new Veterinarian(employeeId, name, this);
    } else {
        newEmployee = new Keeper(employeeId, name, this);
    }

    _employees.add(newEmployee);
  }

  /**
   * Adds a new responsibility to an employee.
   * 
   * @param employeeId id of the employee who will receive the responsibility
   * @param responsibility id of the responsibility the employee will receive
   * @throws UnknownEmployeeKeyExceptionCore if the employee does not exist
   * @throws NoResponsibilityExceptionCore if the responsibility does not exist
   **/
  public void addResponsibility(String employeeId, String responsibility) throws UnknownEmployeeKeyExceptionCore, NoResponsibilityExceptionCore {
    Employee employee = getEmployeeById(employeeId);
    if (employee == null){throw new UnknownEmployeeKeyExceptionCore();}
    
    boolean success = employee.addResponsibility(responsibility);
    if (!success){throw new NoResponsibilityExceptionCore();}
  }
  
  /**
   * Removes a responsibility from an employee.
   * 
   * @param employeeId id of the employee whose responsibility will be removed
   * @param responsibility id of the responsibility to be removed from the employee
   * @throws UnknownEmployeeKeyExceptionCore if the employee does not exist
   * @throws NoResponsibilityExceptionCore if the responsibility does not exist
   **/
  public void removeResponsibility(String employeeId, String responsibility) throws UnknownEmployeeKeyExceptionCore, NoResponsibilityExceptionCore{
    Employee employee = getEmployeeById(employeeId);
    if (employee == null){throw new UnknownEmployeeKeyExceptionCore();}
    if (!employee.getResponsibilities().contains(responsibility)){throw new NoResponsibilityExceptionCore();}
    employee.getResponsibilities().remove(responsibility);
  }

  /**
   * Creates a vaccine and registers it.
   * 
   * @param vaccineId id of the vaccine to register
   * @param name name of the vaccine to register
   * @param speciesIds ids of the species that the vaccine can correctly vaccinate
   * @throws DuplicateVaccineKeyExceptionCore if the vaccine already exists in the program
   * @throws UnknownSpeciesKeyExceptionCore if one or more species do not exist
   **/
  public void registerVaccine(String vaccineId, String name, String[] speciesIds) throws DuplicateVaccineKeyExceptionCore, UnknownSpeciesKeyExceptionCore {      
    if (getVaccineById(vaccineId) != null){throw new DuplicateVaccineKeyExceptionCore();}

    for(String speciesId: speciesIds)
      if (getSpeciesById(speciesId) == null){throw new UnknownSpeciesKeyExceptionCore();}
    
    Vaccine newVaccine = new Vaccine(vaccineId, name, speciesIds);
    _vaccines.add(newVaccine);
  }

  /**
   * Creates a new tree and stores it in the hotel.
   * 
   * @param treeId id of the tree to register
   * @param name name of the tree to register
   * @param type type of the tree to register
   * @param age age of the tree to register
   * @param diff base cleaning difficulty of the tree to register
   * @throws DuplicateTreeKeyExceptionCore if the tree already exists in the program
   **/ 
  public void createTree(String treeId, String name, String type, int age, int diff) throws DuplicateTreeKeyExceptionCore {
    Tree newTree;

    if (getTreeById(treeId) != null){throw new DuplicateTreeKeyExceptionCore();}

    if(type.equals("DECIDUOUS"))
      newTree = new DeciduousTree(treeId, name, age, diff, _currentSeason);
    else
      newTree = new EvergreenTree(treeId, name, age, diff, _currentSeason);

    _trees.add(newTree);
  }

  /**
   * Adds a tree to a habitat.
   * 
   * @param habitatId id of the habitat where the tree is placed
   * @param treeId id of the tree to add
   * @throws UnknownHabitatKeyExceptionCore if the habitat does not exist
   **/ 
  public void addTreeToHabitat(String habitatId, String treeId) throws UnknownHabitatKeyExceptionCore{
      if(getHabitatById(habitatId) == null){throw new UnknownHabitatKeyExceptionCore();}

      Habitat habitat = getHabitatById(habitatId);
      Tree newTree = getTreeById(treeId);
      habitat.addTree(newTree.getId());
  }

  /**
   * Creates a new habitat and registers it.
   * 
   * @param habitatId id of the habitat to register
   * @param name name of the habitat to register
   * @param area area of the habitat to register
   * @throws DuplicateHabitatKeyExceptionCore if the habitat already exists in the program
   **/
  public void registerHabitat(String habitatId, String name, int area) throws DuplicateHabitatKeyExceptionCore {
    if (getHabitatById(habitatId) != null) throw new DuplicateHabitatKeyExceptionCore();
    
    Habitat newHabitat = new Habitat(habitatId, name, area);
    _habitats.add(newHabitat);
  }

  /**
   * Calculates the satisfaction of an animal.
   * 
   * @param animalId id of the animal
   * @return animal satisfaction
   **/
  public float calculateAnimalSatisfaction(String animalId){
    Animal animal = getAnimalById(animalId);
    Habitat habitat = getHabitatById(animal.getHabitatId());
    String speciesId = getAnimalById(animalId).getSpeciesId();
    int sameSpecies = 0, differentSpecies = 0;
    for (String animalIdHabitat: habitat.getAnimals()){
      if(!animalIdHabitat.equals(animalId))
      {
        if(getAnimalById(animalIdHabitat).getSpeciesId().equals(speciesId)) sameSpecies++;
        else differentSpecies++;
      }
    }
    return animal.calculateSatisfaction(habitat, sameSpecies, differentSpecies);
  }

  /**
   * Calculates the satisfaction of an employee.
   * 
   * @param employeeId id of the employee
   * @return employee satisfaction
   **/
  public float calculateEmployeeSatisfaction(String employeeId){
    float satisfaction;
    Employee employee = getEmployeeById(employeeId);

    satisfaction = employee.calculateSatisfaction();
    return satisfaction;
  }

  /**
   * Returns the habitats registered in the hotel.
   * 
   * @return habitats of the hotel
   **/
  public List<Habitat> getHabitats(){return _habitats;}

  /**
   * Returns the animals registered in the hotel.
   * 
   * @return animals of the hotel
   **/
  public List<Animal> getAnimals(){return _animals;}

  /**
   * Returns the employees registered in the hotel.
   * 
   * @return employees of the hotel
   **/
  public List<Employee> getEmployees(){return _employees;}
  
  /**
   * Returns the species registered in the hotel.
   * 
   * @return species of the hotel
   **/
  public List<Species> getSpecies(){return _species;}
  
  /**
   * Returns the vaccines registered in the hotel.
   * 
   * @return vaccines of the hotel
   **/
  public List<Vaccine> getVaccines(){return _vaccines;}
  
  /**
   * Returns the trees registered in the hotel.
   * 
   * @return trees of the hotel
   **/
  public List<Tree> getTrees(){return _trees;}
  
  /**
   * Returns the vaccinations registered in the hotel.
   * 
   * @return vaccinations of the hotel
   **/
  public List<Vaccination> getVaccinations(){return _vaccinations;}
  
  /**
   * Returns the current season of the hotel.
   * 
   * @return season of the hotel
   **/
  public Season getCurrentSeason(){return _currentSeason;}
  
  /**
   * Changes the season in which the hotel currently is.
   * 
   * @param newSeason new season of the hotel
   **/
  public void setSeason(Season newSeason){_currentSeason = newSeason;}

  /**
   * Adds a vaccination to the list of vaccinations.
   * 
   * @param vaccination vaccination to be added to the list of vaccinations
   **/
  public void addVaccination(Vaccination vaccination){_vaccinations.add(vaccination);}

  /**
   * Returns the name of the file currently associated with the hotel.
   * 
   * @return name of the file associated with the hotel
   **/
  public String getFileName() {return _fileName;}
  
  /**
   * Changes the name of the file currently associated with the hotel.
   * 
   * @param filename name of the file to be associated with the hotel
   **/
  public void setFileName(String filename) {_fileName = filename;}

  /**
   * Returns the current saved state of the hotel.
   * 
   * @return current saved state
   **/
  public boolean getSavedState(){return _savedState;}

  /**
   * Changes the current saved state of the hotel to false.
   **/
  public void notSaved(){_savedState = false;}
  
  /**
   * Changes the current saved state of the hotel to true.
   **/
  public void saved(){_savedState = true;}

  /**
   * Searches for an animal in the hotel by its id and returns it.
   *
   * @param id id of the animal being searched for
   * @return animal corresponding to the id
   **/
  public Animal getAnimalById(String id){
    for (Animal animal: _animals)
      if(animal.getId().equals(id))
        return animal;
    return null;
  }

  /**
   * Searches for a habitat in the hotel by its id and returns it.
   * 
   * @param id id of the habitat being searched for
   * @return habitat corresponding to the id
   **/
  public Habitat getHabitatById(String id){
    for (Habitat habitat: _habitats)
      if(habitat.getId().equals(id))
        return habitat;
    return null;
  }

  /**
   * Searches for an employee in the hotel by its id and returns it.
   * 
   * @param id id of the employee being searched for
   * @return employee corresponding to the id
   **/
  public Employee getEmployeeById(String id){
    for (Employee employee: _employees)
      if(employee.getId().equals(id))
        return employee;
    return null;
  }

  /**
   * Searches for a veterinarian in the hotel by its id and returns it.
   *
   * @param id id of the veterinarian being searched for
   * @return veterinarian corresponding to the id
   **/
  public Veterinarian getVeterinarianById(String id) {
    for (Employee employee : _employees) {
        if (employee.getId().equals(id) && employee instanceof Veterinarian)
            return (Veterinarian) employee;
    }
    return null; 
  }

  /**
   * Searches for a species in the hotel by its id and returns it.
   * 
   * @param id id of the species being searched for
   * @return species corresponding to the id
   **/
  public Species getSpeciesById(String id){
    for (Species species: _species)
      if(species.getId().equals(id))
        return species;
    return null;
  }

  /**
   * Searches for a vaccine in the hotel by its id and returns it.
   * 
   * @param id id of the vaccine being searched for
   * @return vaccine corresponding to the id
   **/
  public Vaccine getVaccineById(String id){
    for(Vaccine vaccine: _vaccines)
      if(vaccine.getId().equalsIgnoreCase(id))
        return vaccine;
    return null;
  }
  
  /**
   * Searches for a tree in the hotel by its id and returns it.
   * 
   * @param id id of the tree being searched for
   * @return tree corresponding to the id
   **/
  public Tree getTreeById(String id){
    for (Tree tree: _trees)
      if(tree.getId().equals(id))
        return tree;
    return null;
  }
  
}