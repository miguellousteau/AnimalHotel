package hva.app.habitat;

import hva.core.Hotel;
import hva.core.Tree;

import java.util.*;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all trees in a given habitat.
 **/
class DoShowAllTreesInHabitat extends Command<Hotel> {

  DoShowAllTreesInHabitat(Hotel receiver) {
    super(Label.SHOW_TREES_IN_HABITAT, receiver);
    addStringField("habitatId", Prompt.habitatKey());
  }
  
  @Override
  protected void execute() throws CommandException {

    if (_receiver.getHabitatById(stringField("habitatId")) == null) {throw new UnknownHabitatKeyException(stringField("habitatId"));}

    List<String> treeIds = _receiver.getHabitatById(stringField("habitatId")).getTreeIds();
    List<Tree> trees = _receiver.getTrees();
  
    List<Tree> sortedTrees = new ArrayList<>(trees);
    
    sortedTrees.sort(Comparator.comparing(Tree::getId, String.CASE_INSENSITIVE_ORDER));
    
    for (Tree tree : sortedTrees) {
      if (treeIds.contains(tree.getId())) {
          _display.addLine(tree.addLine());
      }
    }
    _display.display();
  }
}