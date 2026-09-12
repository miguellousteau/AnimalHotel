package hva.app.employee;

import hva.app.exception.NoResponsibilityException;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.core.Hotel;
import hva.core.exception.NoResponsibilityExceptionCore;
import hva.core.exception.UnknownEmployeeKeyExceptionCore;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a new responsibility to an employee: species to veterinarians and
 * habitats to zookeepers.
 **/
class DoAddResponsibility extends Command<Hotel> {

  DoAddResponsibility(Hotel receiver) {
    super(Label.ADD_RESPONSIBILITY, receiver);
    addStringField("employeeId", Prompt.employeeKey());
    addStringField("responsibilityId", Prompt.responsibilityKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    try{
      _receiver.addResponsibility(stringField("employeeId"), stringField("responsibilityId"));
    }catch(UnknownEmployeeKeyExceptionCore e){throw new UnknownEmployeeKeyException(stringField("employeeId"));}
    catch(NoResponsibilityExceptionCore e){throw new NoResponsibilityException(stringField("employeeId"), stringField("responsibilityId"));}
      _receiver.notSaved();
  }
}
