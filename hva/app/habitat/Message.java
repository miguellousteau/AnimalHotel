package hva.app.habitat;

public interface Message {

  static String noAssociation(String habitatId, String speciesId) {
    return "There is no association between habitat '" + habitatId + "' and species '" + speciesId + "'";
  }

}