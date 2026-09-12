package hva.app.vaccine;

import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownVaccineKeyException;
import hva.app.exception.UnknownVeterinarianKeyException;
import hva.app.exception.VeterinarianNotAuthorizedException;
import hva.core.Animal;
import hva.core.Hotel;
import hva.core.Species;
import hva.core.Vaccination;
import hva.core.Vaccine;
import hva.core.Veterinarian;
import java.util.ArrayList;
import java.util.List;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Vaccinate by a given veterinarian a given animal with a given vaccine.
 **/
class DoVaccinateAnimal extends Command<Hotel> {
  DoVaccinateAnimal(Hotel receiver) {
    super(Label.VACCINATE_ANIMAL, receiver);
    addStringField("vaccineId", Prompt.vaccineKey());
    addStringField("veterinarianId", Prompt.veterinarianKey());
    addStringField("animalId", hva.app.animal.Prompt.animalKey());
  }

  @Override
  protected final void execute() throws CommandException {
    Boolean correctVaccine = false;

    Vaccine vaccine = _receiver.getVaccineById(stringField("vaccineId"));
    Veterinarian veterinarian = _receiver.getVeterinarianById(stringField("veterinarianId"));
    Animal animal = _receiver.getAnimalById(stringField("animalId"));

    if (vaccine == null){throw new UnknownVaccineKeyException(stringField("vaccineId"));}
    if (veterinarian == null || veterinarian.getType() != "VET"){throw new UnknownVeterinarianKeyException(stringField("veterinarianId"));}
    if (animal == null){throw new UnknownAnimalKeyException(stringField("animalId"));}

    Species species = _receiver.getSpeciesById(animal.getSpeciesId());
    if (!veterinarian.getResponsibilities().contains(animal.getSpeciesId())){throw new VeterinarianNotAuthorizedException(stringField("veterinarianId"), species.getId());}

    List<String> speciesNames = new ArrayList<>();
    for(String vaccineSpeciesId: vaccine.getSpeciesIds()){
      String vaccineSpeciesName = _receiver.getSpeciesById(vaccineSpeciesId).getName();
      speciesNames.add(vaccineSpeciesName);
      if(vaccineSpeciesName.equals(species.getName())){correctVaccine = true;}
    }

    if(!correctVaccine){_display.addLine(Message.wrongVaccine(stringField("vaccineId"), stringField("animalId")));}
    _display.display();

    veterinarian.vaccinate(animal, vaccine, species, speciesNames, correctVaccine);

    Vaccination vaccination = new Vaccination(stringField("vaccineId"), stringField("veterinarianId"), species.getId(), correctVaccine);
    vaccine.addVaccination(vaccination);
    veterinarian.addVaccination(vaccination);
    animal.addVaccination(vaccination);
    
    _receiver.addVaccination(vaccination);
  }
}