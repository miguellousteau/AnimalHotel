package hva.app.search;

import hva.core.Hotel;
import hva.core.Animal;
import hva.core.Habitat;
import java.util.*;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all animals of a given habitat.
 **/
class DoShowAnimalsInHabitat extends Command<Hotel> {

  DoShowAnimalsInHabitat(Hotel receiver) {
    super(Label.ANIMALS_IN_HABITAT, receiver);
    addStringField("habitatId",hva.app.habitat.Prompt.habitatKey());
  }

  @Override
  protected void execute() throws CommandException {
    Habitat habitat = _receiver.getHabitatById(stringField("habitatId"));
    if(habitat == null){throw new UnknownHabitatKeyException(stringField("habitatId"));}

    List<Animal> animals = _receiver.getAnimals();
    List<Animal> sortedAnimals = new ArrayList<>(animals);
    sortedAnimals.sort(Comparator.comparing(Animal::getId, String.CASE_INSENSITIVE_ORDER));

    for(Animal animal: sortedAnimals)
      if (animal.getHabitatId().equals(stringField("habitatId")))
        _display.addLine(animal.addLine());
    _display.display();
  }
}