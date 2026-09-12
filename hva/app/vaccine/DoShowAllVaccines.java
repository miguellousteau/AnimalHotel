package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.Vaccine;
import java.util.*;
import pt.tecnico.uilib.menus.Command;

/**
 * Show all vaccines.
 **/
class DoShowAllVaccines extends Command<Hotel> {

  DoShowAllVaccines(Hotel receiver) {
    super(Label.SHOW_ALL_VACCINES, receiver);
  }
  
  @Override
  protected final void execute() {
    List<Vaccine> sortedVaccines = new ArrayList<>(_receiver.getVaccines());
    sortedVaccines.sort(Comparator.comparing(vaccine -> vaccine.getId().toLowerCase()));

    for (Vaccine vaccine: sortedVaccines){
      _display.addLine(vaccine.addLine());
    }
    _display.display();
  }
}