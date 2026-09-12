package hva.app.habitat;

import hva.core.Hotel;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Change the area of a given habitat.
 **/
class DoChangeHabitatArea extends Command<Hotel> {

  DoChangeHabitatArea(Hotel receiver) {
    super(Label.CHANGE_HABITAT_AREA, receiver);
    addStringField("habitatId", Prompt.habitatKey());
    addIntegerField("area", Prompt.habitatArea());
  }
  
  @Override
  protected void execute() throws CommandException {
    if (_receiver.getHabitatById(stringField("habitatId")) == null){throw new UnknownHabitatKeyException(stringField("habitatId"));}
    _receiver.getHabitatById(stringField("habitatId")).setArea(integerField("area"));

    _receiver.notSaved();
  }
}
