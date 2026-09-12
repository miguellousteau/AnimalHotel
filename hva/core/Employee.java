package hva.core;

import java.io.Serializable;
import java.util.*;

public abstract class Employee implements Serializable{
    private static final long serialVersionUID = 202407081733L;

    protected String _id;
    protected String _name;
    protected Hotel _hotel;
    protected Set<String> _responsibilities = new HashSet<>();
    
    /**
   * Employee instance constructor.
   * 
   * @param id employee id
   * @param name employee name
   * @param hotel hotel where the employee was created
   **/
    public Employee(String id, String name, Hotel hotel){
        _id = id;
        _name = name; 
        _hotel = hotel;
    }

    /**
    * Builds a line containing information about the employee.
    * @return line with information about the employee
    **/
    public abstract String addLine();

    /**
     * Calculates and returns the employee's satisfaction.
     * 
     * @return employee satisfaction
     **/
    public abstract float calculateSatisfaction();

    /**
    * Returns the type of the employee instance.
    * 
    * @return employee type
    **/
    public abstract String getType();
    public String getId(){return _id;}
    public String getName(){return _name;}
    public Set<String> getResponsibilities(){return _responsibilities;}

    /**
    * Adds a responsibility to the employee and returns whether it succeeded.
    * 
    * @return success of the operation
    **/
    public abstract boolean addResponsibility(String responsibility);
}