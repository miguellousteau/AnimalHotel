package hva.app.employee;

import java.util.*;

import hva.core.Employee;
import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;

/**
 * Show all employees of this zoo hotel.
 **/
class DoShowAllEmployees extends Command<Hotel> {

  DoShowAllEmployees(Hotel receiver) {
    super(Label.SHOW_ALL_EMPLOYEES, receiver);
  }
  
  @Override
  protected void execute() {
      List<Employee> sortedEmployees = new ArrayList<>(_receiver.getEmployees());
      sortedEmployees.sort(Comparator.comparing(employee -> employee.getId().toLowerCase()));
  
      for (Employee employee : sortedEmployees) {
          _display.addLine(employee.addLine());
      }
      _display.display();
  }
}