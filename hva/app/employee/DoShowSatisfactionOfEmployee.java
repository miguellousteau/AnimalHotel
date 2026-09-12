package hva.app.employee;

import hva.core.Hotel;
import hva.app.exception.UnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import static java.lang.Math.round;

/**
 * Show the satisfaction of a given employee.
 **/
class DoShowSatisfactionOfEmployee extends Command<Hotel> {

  DoShowSatisfactionOfEmployee(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_EMPLOYEE, receiver);
    addStringField("employeeId",Prompt.employeeKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    if (_receiver.getEmployeeById(stringField("employeeId")) == null){throw new UnknownEmployeeKeyException(stringField("employeeId"));}

    int satisfaction = round(_receiver.calculateEmployeeSatisfaction(stringField("employeeId")));
    _display.addLine(satisfaction);
    _display.display();
  }
}
