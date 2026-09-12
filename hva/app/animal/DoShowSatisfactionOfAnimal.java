package hva.app.animal;

import hva.core.Hotel;
import hva.app.exception.UnknownAnimalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import static java.lang.Math.round;

/**
 * Shows the satisfaction of a given animal.
 */
class DoShowSatisfactionOfAnimal extends Command<Hotel> {

  DoShowSatisfactionOfAnimal(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_ANIMAL, receiver);
    addStringField("animalId", Prompt.animalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    int satisfaction;

    if(_receiver.getAnimalById(stringField("animalId")) == null){throw new UnknownAnimalKeyException(stringField("animalId"));}

    satisfaction = round(_receiver.calculateAnimalSatisfaction(stringField("animalId")));

    _display.addLine(satisfaction);
    _display.display();
  }
}