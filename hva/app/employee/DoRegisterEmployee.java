package hva.app.employee;

import hva.core.Hotel;
import hva.core.exception.DuplicateEmployeeKeyExceptionCore;
import hva.app.exception.DuplicateEmployeeKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Adds a new employee to this zoo hotel.
 **/
class DoRegisterEmployee extends Command<Hotel> {

  DoRegisterEmployee(Hotel receiver) {
    super(Label.REGISTER_EMPLOYEE, receiver);
    addStringField("employeeId", Prompt.employeeKey());
    addStringField("employeeName", Prompt.employeeName());
  }
  
  @Override
  protected void execute() throws CommandException {
    String type = "";
    while(!(type.equals("VET") || type.equals("TRT"))){
      type = Form.requestString(Prompt.employeeType());
    }
        
    try{
      _receiver.registerEmployee(stringField("employeeId"), stringField("employeeName"), type);
    } catch(DuplicateEmployeeKeyExceptionCore e){throw new DuplicateEmployeeKeyException(stringField("employeeId"));}
      _receiver.notSaved();
  }
}