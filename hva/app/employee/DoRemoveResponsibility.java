package hva.app.employee;

import hva.app.exception.NoResponsibilityException;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.core.Hotel;
import hva.core.exception.NoResponsibilityExceptionCore;
import hva.core.exception.UnknownEmployeeKeyExceptionCore;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Remove a given responsibility from a given employee of this zoo hotel.
 **/
class DoRemoveResponsibility extends Command<Hotel> {

  DoRemoveResponsibility(Hotel receiver) {
    super(Label.REMOVE_RESPONSIBILITY, receiver);
    addStringField("employeeId", Prompt.employeeKey());
    addStringField("responsibilityId", Prompt.responsibilityKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    try{
      _receiver.removeResponsibility(stringField("employeeId"), stringField("responsibilityId"));
    }catch(UnknownEmployeeKeyExceptionCore e){throw new UnknownEmployeeKeyException(stringField("employeeId"));}
    catch(NoResponsibilityExceptionCore e){throw new NoResponsibilityException(stringField("employeeId"), stringField("responsibilityId"));}
    _receiver.notSaved();
  }
}
