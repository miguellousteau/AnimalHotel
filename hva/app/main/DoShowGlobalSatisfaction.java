package hva.app.main;

import hva.core.Employee;
import hva.core.Animal;
import hva.core.HotelManager;
import pt.tecnico.uilib.menus.Command;
import static java.lang.Math.round;

/**
 * Command for showing the global satisfaction of the current zoo hotel.
 **/
class DoShowGlobalSatisfaction extends Command<HotelManager> {
  DoShowGlobalSatisfaction(HotelManager receiver) {
    super(hva.app.main.Label.SHOW_GLOBAL_SATISFACTION, receiver);
  }
  
  @Override
  protected final void execute() {
    int satisfaction = 0;
    for(Employee employee: _receiver.getHotel().getEmployees()){
      satisfaction += round(_receiver.getHotel().calculateEmployeeSatisfaction(employee.getId()));
    }
    for (Animal animal: _receiver.getHotel().getAnimals()){
      satisfaction += round(_receiver.getHotel().calculateAnimalSatisfaction(animal.getId()));
    }
    _display.addLine(satisfaction);
    _display.display();
  }
}