package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.Vaccination;
import java.util.*;
import pt.tecnico.uilib.menus.Command;

/**
 * Show all applied vaccines by all veterinarians of this zoo hotel.
 **/
class DoShowVaccinations extends Command<Hotel> {

  DoShowVaccinations(Hotel receiver) {
    super(Label.SHOW_VACCINATIONS, receiver);
  }
  
  @Override
  protected final void execute() {
    List<Vaccination> vaccinations = new ArrayList<>(_receiver.getVaccinations());
    for (Vaccination vaccination : vaccinations){
      _display.addLine(vaccination.addLine());
    }
    _display.display();
  }
}