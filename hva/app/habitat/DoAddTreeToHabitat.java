package hva.app.habitat;

import hva.app.exception.DuplicateTreeKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Hotel;
import hva.core.exception.DuplicateTreeKeyExceptionCore;
import hva.core.exception.UnknownHabitatKeyExceptionCore;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a new tree to a given habitat of the current zoo hotel.
 **/
class DoAddTreeToHabitat extends Command<Hotel> {

  DoAddTreeToHabitat(Hotel receiver) {
    super(Label.ADD_TREE_TO_HABITAT, receiver);
    addStringField("habitatId", Prompt.habitatKey());
    addStringField("treeId", Prompt.treeKey());
    addStringField("name", Prompt.treeName());
    addIntegerField("age", Prompt.treeAge());
    addIntegerField("difficulty", Prompt.treeDifficulty());
  }
  
  @Override
  protected void execute() throws CommandException {
    
    String type = "";
    while (!(type.equals("DECIDUOUS") || type.equals("EVERGREEN")))
      type = Form.requestString(Prompt.treeType());

    try{
      _receiver.createTree(stringField("treeId"), stringField("name"), type, integerField("age"), integerField("difficulty"));
    }catch(DuplicateTreeKeyExceptionCore e){throw new DuplicateTreeKeyException(stringField("treeId"));}

    try{
      _receiver.addTreeToHabitat(stringField("habitatId"), stringField("treeId"));
    }catch(UnknownHabitatKeyExceptionCore e){
      _receiver.getTrees().remove(_receiver.getTreeById(stringField("treeId")));
      throw new UnknownHabitatKeyException(stringField("habitatId"));
    }

    _display.addLine(_receiver.getTreeById(stringField("treeId")).addLine());
    _display.display();

    _receiver.notSaved();

  }
}
