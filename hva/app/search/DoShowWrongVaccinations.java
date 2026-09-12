package hva.app.search;

import hva.core.Hotel;
import hva.core.Vaccination;
import java.util.List;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all vaccines applied to animals belonging to an invalid species.
 **/
class DoShowWrongVaccinations extends Command<Hotel> {

  DoShowWrongVaccinations(Hotel receiver) {
    super(Label.WRONG_VACCINATIONS, receiver);
  }

  @Override
  protected void execute() throws CommandException {
    List<Vaccination> vaccinations = _receiver.getVaccinations();
    for(Vaccination vaccination: vaccinations){
      if (!vaccination.getCorrect())
        _display.addLine(vaccination.addLine());
    }
    _display.display();
  }
}