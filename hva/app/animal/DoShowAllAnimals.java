package hva.app.animal;

import hva.core.Animal;
import hva.core.Hotel;
import java.util.*;
import pt.tecnico.uilib.menus.Command;

/**
 * Show all animals registered in this zoo hotel.
 */
class DoShowAllAnimals extends Command<Hotel> {

  DoShowAllAnimals(Hotel receiver) {
    super(Label.SHOW_ALL_ANIMALS, receiver);
  }
  
  @Override
  protected final void execute() {
    List<Animal> sortedAnimals = new ArrayList<>(_receiver.getAnimals());
    
    sortedAnimals.sort(Comparator.comparing(animal -> animal.getId().toLowerCase()));

    for(Animal animal: sortedAnimals) _display.addLine(animal.addLine());
    _display.display();
  }
}
