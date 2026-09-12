package hva.app.habitat;

import hva.core.Hotel;
import hva.core.Habitat;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Associate (positive or negatively) a species to a given habitat.
 **/
class DoChangeHabitatInfluence extends Command<Hotel> {

  DoChangeHabitatInfluence(Hotel receiver) {
    super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
    addStringField("habitatId", Prompt.habitatKey());
    addStringField("speciesId", hva.app.animal.Prompt.speciesKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    String influence = "";
    while(!(influence.equals("POS")||influence.equals("NEU")||influence.equals("NEG"))){
      influence = Form.requestString(Prompt.habitatInfluence());
    }

    Habitat habitat = _receiver.getHabitatById(stringField("habitatId"));

    if(habitat == null){throw new UnknownHabitatKeyException(stringField("habitatId"));}
    if (_receiver.getSpeciesById(stringField("speciesId")) == null){throw new UnknownSpeciesKeyException(stringField("speciesId"));}
    
    if(influence.equals("POS")) habitat.addPositiveSpecies(stringField("speciesId"));
    else if (influence.equals("NEG")) habitat.addNegativeSpecies(stringField("speciesId"));
    else habitat.addNeutralSpecies(stringField("speciesId"));

    _receiver.notSaved();
  }
}
