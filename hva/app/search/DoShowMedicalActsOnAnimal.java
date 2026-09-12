package hva.app.search;

import hva.core.Hotel;
import hva.core.Vaccination;
import hva.core.Animal;
import hva.app.exception.UnknownAnimalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all medical acts applied to a given animal.
 **/
class DoShowMedicalActsOnAnimal extends Command<Hotel> {

  DoShowMedicalActsOnAnimal(Hotel receiver) {
    super(Label.MEDICAL_ACTS_ON_ANIMAL, receiver);
    addStringField("animalId", hva.app.animal.Prompt.animalKey());
  }

  @Override
  protected void execute() throws CommandException {
    if (_receiver.getAnimalById(stringField("animalId")) == null){throw new UnknownAnimalKeyException(stringField("animalId"));}
    Animal animal = _receiver.getAnimalById(stringField("animalId"));
    for(Vaccination vaccination: animal.getVaccinations()){
      _display.addLine(vaccination.addLine());
    }
    _display.display();
  }
}