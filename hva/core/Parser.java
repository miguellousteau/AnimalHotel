package hva.core;

import hva.core.exception.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
 private Hotel _hotel;

  Parser(Hotel h) {
    _hotel = h;
  }

  public void parseFile(String filename) throws IOException, UnrecognizedEntryException {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;

      while ((line = reader.readLine()) != null)
        parseLine(line);
      _hotel.notSaved();
    }
  }

  private void parseLine(String line) throws UnrecognizedEntryException {
    String[] components = line.split("\\|");
    switch(components[0]) {
    case "ANIMAL" -> parseAnimal(components);
    case "SPECIES" -> parseSpecies(components);
    case "TREE" -> parseTree(components);
    case "HABITAT" -> parseHabitat(components);
    case "TREATMENT" -> parseEmployee(components, "TRT");
    case "VETERINARIAN" -> parseEmployee(components, "VET");
    case "VACCINE" -> parseVaccine(components);
    default -> throw new UnrecognizedEntryException("invalid entry type: " + components[0]);
    }
  }

  // Parse a line with format ANIMAL|id|name|speciesId|habitatId
  private void parseAnimal(String[] components) throws UnrecognizedEntryException {
    try {
      String id = components[1];
      String name = components[2];
      String habitatId = components[4];
      String speciesId = components[3];

      _hotel.registerAnimal(id, name, speciesId, habitatId);
    } catch (DuplicateAnimalKeyExceptionCore|UnknownHabitatKeyExceptionCore e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }

  // Parse a line with format SPECIES|id|name
  private void parseSpecies(String[] components) throws UnrecognizedEntryException {
      String id = components[1];
      String name = components[2];

      _hotel.registerSpecies(id, name);
  }
  
  // Parse a line with format TREATMENT|id|name|habitatId1,...,habitatIdN or
  // VETERINARIAN|id|name|speciesId1,...,speciesIdN
  private void parseEmployee(String[] components, String empType) throws UnrecognizedEntryException {
    try {
      String id = components[1];
      String name = components[2];

      _hotel.registerEmployee(id, name, empType);

      if (components.length == 4) {
        for(String responsibility : components[3].split(","))
          _hotel.addResponsibility(components[1], responsibility);
      }
    } catch (NoResponsibilityExceptionCore|UnknownEmployeeKeyExceptionCore|DuplicateEmployeeKeyExceptionCore e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }

  // Parse a line with format VACCINE|id|name|speciesId1,...,speciesIdN
  private void parseVaccine(String[] components) throws UnrecognizedEntryException {
    try {
      String id = components[1];
      String name = components[2];
      String[] speciesIds = components.length == 4 ? components[3].split(",") : new String[0];
      _hotel.registerVaccine(id, name, speciesIds);
    } catch (DuplicateVaccineKeyExceptionCore|UnknownSpeciesKeyExceptionCore e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }

  // Parse a line with format TREE|id|name|age|difficulty|type
  private void parseTree(String[] components) throws UnrecognizedEntryException {
    try {
      String id = components[1];
      String name = components[2];
      int age = Integer.parseInt(components[3]);
      int diff = Integer.parseInt(components[4]);
      String type = components[5];

      _hotel.createTree(id, name, type, age, diff);
    } catch (DuplicateTreeKeyExceptionCore e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }

  // Parse a line with format HABITAT|id|name|area|treeId1,...,treeIdN
  private void parseHabitat(String[] components) throws UnrecognizedEntryException {
    try {
      String id = components[1];
      String name = components[2];
      int area = Integer.parseInt(components[3]);

      _hotel.registerHabitat(id, name, area);

      if (components.length == 5) {
        String[] listOfTree = components[4].split(",");
        for (String treeKey : listOfTree)
          _hotel.addTreeToHabitat(id, treeKey);
      }
      
    } catch (DuplicateHabitatKeyExceptionCore|UnknownHabitatKeyExceptionCore e) {
      throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
    }
  }
}