package hva.app.habitat;

import hva.core.Habitat;
import hva.core.Hotel;
import hva.core.Tree;
import java.util.*;
import pt.tecnico.uilib.menus.Command;

/**
 * Show all habitats of this zoo hotel.
 **/
class DoShowAllHabitats extends Command<Hotel> {
  DoShowAllHabitats(Hotel receiver) {
    super(Label.SHOW_ALL_HABITATS, receiver);
  }
  
  @Override
  protected void execute() {
    List<Habitat> sortedHabitats = new ArrayList<>(_receiver.getHabitats());
    
    sortedHabitats.sort(Comparator.comparing(habitat -> habitat.getId().toLowerCase()));
    for (Habitat habitat: sortedHabitats){
      _display.addLine(habitat.addLine());
      for (Tree tree : _receiver.getTrees()) {
        if (habitat.getTreeIds().contains(tree.getId()))
          _display.addLine(tree.addLine());
      }
    }
    _display.display();
  }
}