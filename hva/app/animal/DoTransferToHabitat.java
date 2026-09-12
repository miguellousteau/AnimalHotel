package hva.app.animal;

import hva.core.Hotel;
import hva.core.Habitat;
import hva.core.Animal;
import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Transfers a given animal to a new habitat of this zoo hotel.
 */
class DoTransferToHabitat extends Command<Hotel> {

  DoTransferToHabitat(Hotel hotel) {
    super(Label.TRANSFER_ANIMAL_TO_HABITAT, hotel);
    addStringField("animalId", Prompt.animalKey());
    addStringField("habitatId", hva.app.habitat.Prompt.habitatKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    
    Animal animal = _receiver.getAnimalById(stringField("animalId"));
    Habitat newHabitat = _receiver.getHabitatById(stringField("habitatId"));

    if(animal == null){throw new UnknownAnimalKeyException(stringField("animalId"));}
    if(newHabitat == null){throw new UnknownHabitatKeyException(stringField("habitatId"));}

    animal.setHabitat(stringField("habitatId"));
    Habitat oldHabitat = _receiver.getHabitatById(animal.getHabitatId());
    oldHabitat.removeAnimal(stringField("animalId"));
    newHabitat.addAnimal(stringField("habitatId"));

    _receiver.notSaved();
  }
}