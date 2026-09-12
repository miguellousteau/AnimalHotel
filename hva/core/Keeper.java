package hva.core;

import java.util.*;

public class Keeper extends Employee {
    private static final long serialVersionUID = 202407081733L;
    
    /**
    * Keeper instance constructor.
    * 
    * @param id keeper id
    * @param name keeper name
    * @param hotel hotel where the keeper was created
    **/
    public Keeper(String id, String name, Hotel hotel){super(id, name, hotel);}

    @Override
    /**
    * Builds a line containing information about the keeper.
    * @return line with information about the keeper
    **/
    public String addLine() {
        if (_responsibilities.isEmpty()) {
            return "TRT|" + getId() + "|" + getName();
        } else {
            List<String> sortedResponsibilities = new ArrayList<>(_responsibilities);
            sortedResponsibilities.sort(String.CASE_INSENSITIVE_ORDER);
            return "TRT|" + getId() + "|" + getName() + "|" + String.join(",", sortedResponsibilities);
        }
    }

    /**
     * Calculates and returns the keeper's satisfaction.
     * 
     * @return keeper satisfaction
     **/
    public float calculateSatisfaction(){
        float keepers;
        float cleaningEffort;
        float work;
        float satisfaction = 300;
        HashMap<Float, Float> map = new HashMap<>();

        for (String responsibility: _responsibilities){
          keepers = 0;
          for (Employee employee: _hotel.getEmployees())
            if(employee.getResponsibilities().contains(responsibility))
              keepers++;

          Habitat habitat = _hotel.getHabitatById(responsibility);
          cleaningEffort = 0;

          for (String treeId: habitat.getTreeIds()){
            cleaningEffort += _hotel.getTreeById(treeId).calculateCleaningEffort();
          }
          work = habitat.getArea() + 3*habitat.getAnimals().size() + cleaningEffort;

          map.put(work, keepers);
        }

        for (Map.Entry<Float, Float> entry: map.entrySet()){
            work = entry.getKey();
            keepers = entry.getValue();
            satisfaction -= work/keepers;
        }
        return satisfaction;
    }
        
    /**
    * Returns the type of the keeper instance.
    * 
    * @return String "TRT"
    **/
    public String getType(){return "TRT";}
    
    /**
    * Adds a responsibility to the keeper and returns whether it succeeded.
    * 
    * @return success of the operation
    **/
    public boolean addResponsibility(String responsibility){
        if(_hotel.getHabitatById(responsibility) == null) return false;

        _responsibilities.add(responsibility);
        return true;
    }
}