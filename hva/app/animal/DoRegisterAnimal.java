package hva.app.animal;

import hva.core.Hotel;
import hva.app.exception.DuplicateAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.exception.DuplicateAnimalKeyExceptionCore;
import hva.core.exception.UnknownHabitatKeyExceptionCore;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Register a new animal in this zoo hotel.
 */
class DoRegisterAnimal extends Command<Hotel> {

  DoRegisterAnimal(Hotel receiver) {
    super(Label.REGISTER_ANIMAL, receiver);
    addStringField("animalId", Prompt.animalKey());
    addStringField("animalName", Prompt.animalName());
    addStringField("speciesId", Prompt.speciesKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    if (_receiver.getSpeciesById(stringField("speciesId")) == null){
      String speciesName = Form.requestString(Prompt.speciesName());
      _receiver.registerSpecies(stringField("speciesId"), speciesName);
    }
    String habitatId = Form.requestString(hva.app.habitat.Prompt.habitatKey());
    try{
      _receiver.registerAnimal(stringField("animalId"), stringField("animalName"), stringField("speciesId"), habitatId);
    }catch(DuplicateAnimalKeyExceptionCore e){throw new DuplicateAnimalKeyException(stringField("animalId"));}
    catch(UnknownHabitatKeyExceptionCore e){throw new UnknownHabitatKeyException(habitatId);}
    _receiver.notSaved();
  }
}
