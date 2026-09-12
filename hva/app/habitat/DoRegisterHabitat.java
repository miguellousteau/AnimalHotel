package hva.app.habitat;

import hva.core.Hotel;
import hva.core.exception.DuplicateHabitatKeyExceptionCore;
import hva.app.exception.DuplicateHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Add a new habitat to this zoo hotel.
 **/
class DoRegisterHabitat extends Command<Hotel> {

  DoRegisterHabitat(Hotel receiver) {
    super(Label.REGISTER_HABITAT, receiver);
    addStringField("habitatId", Prompt.habitatKey());
    addStringField("name", Prompt.habitatName());
    addIntegerField("area", Prompt.habitatArea());
  }
  
  @Override
  protected void execute() throws CommandException {
    try{
    _receiver.registerHabitat(stringField("habitatId"), stringField("name"), integerField("area"));
    }catch(DuplicateHabitatKeyExceptionCore e){throw new DuplicateHabitatKeyException(stringField("habitatId"));}
    _receiver.notSaved();
  }
}