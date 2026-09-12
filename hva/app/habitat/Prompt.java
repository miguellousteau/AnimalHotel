package hva.app.habitat;

public interface Prompt {

  static String habitatKey() {
    return "Habitat unique identifier: ";
  }

  static String habitatName() {
    return "Habitat name: ";
  }

  static String habitatArea() {
    return "Habitat area: ";
  }

  static String habitatInfluence() {
    return "Influence (positive, negative, neutral: POS, NEG, NEU): ";
  }

  static String treeKey() {
    return "Tree unique identifier: ";
  }

  static String treeName() {
    return "Tree name: ";
  }

  static String treeAge() {
    return "Tree age: ";
  }

  static String treeDifficulty() {
    return "Tree cleaning difficulty: ";
  }

  static String treeType() {
    return "Tree type: (DECIDUOUS or EVERGREEN) ";
  }

  static String treeState() {
    return "New biological state: ";
  }

}