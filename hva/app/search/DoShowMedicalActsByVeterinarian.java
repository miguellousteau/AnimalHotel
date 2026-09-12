package hva.app.search;

import hva.core.Hotel;
import hva.core.Vaccination;
import hva.core.Veterinarian;
import hva.app.exception.UnknownVeterinarianKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all medical acts of a given veterinarian.
 **/
class DoShowMedicalActsByVeterinarian extends Command<Hotel> {

  DoShowMedicalActsByVeterinarian(Hotel receiver) {
    super(Label.MEDICAL_ACTS_BY_VET, receiver);
    addStringField("employeeId", hva.app.employee.Prompt.employeeKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    if (_receiver.getVeterinarianById(stringField("employeeId")) == null){throw new UnknownVeterinarianKeyException(stringField("employeeId"));}
    Veterinarian veterinarian = _receiver.getVeterinarianById(stringField("employeeId"));
    for(Vaccination vaccination: veterinarian.getVaccinations()){
      _display.addLine(vaccination.addLine());
    }
    _display.display();
  }
}