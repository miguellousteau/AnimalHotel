package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.exception.DuplicateVaccineKeyExceptionCore;
import hva.core.exception.UnknownSpeciesKeyExceptionCore;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.app.exception.DuplicateVaccineKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Apply a vaccine to a given animal.
 **/
class DoRegisterVaccine extends Command<Hotel> {

  DoRegisterVaccine(Hotel receiver) {
      super(Label.REGISTER_VACCINE, receiver);
      addStringField("vaccineId", Prompt.vaccineKey());
      addStringField("vaccineName", Prompt.vaccineName());
      addStringField("speciesIds", Prompt.listOfSpeciesKeys());
  }

  @Override
  protected final void execute() throws CommandException {
      String[] speciesIdsArray = stringField("speciesIds").split(",");

      try{
        _receiver.registerVaccine(stringField("vaccineId"), stringField("vaccineName"), speciesIdsArray);
      }catch(DuplicateVaccineKeyExceptionCore e){throw new DuplicateVaccineKeyException(stringField("vaccineId"));}
      catch(UnknownSpeciesKeyExceptionCore e){throw new UnknownSpeciesKeyException(stringField("speciesIds"));}
      _receiver.notSaved();
  } 
}