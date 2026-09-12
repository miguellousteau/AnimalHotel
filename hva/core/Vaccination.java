package hva.core;

import java.io.Serializable;

public class Vaccination implements Serializable{
    private static final long serialVersionUID = 202407081733L;
    private String _vaccineId;
    private String _veterinarianId;
    private String _speciesId;
    private boolean _correct;
    
    public Vaccination(String vaccineId, String veterinarianId, String speciesId, Boolean correct){
        _vaccineId = vaccineId;
        _veterinarianId = veterinarianId;
        _speciesId = speciesId;
        _correct = correct;
    }

    public String addLine() {
        return "VACCINATION-RECORD|" + _vaccineId + "|" + _veterinarianId + "|" + _speciesId;
    }

    public String getVaccineId(){return _vaccineId;}
    public String getVeterinarianId(){return _veterinarianId;}
    public String getSpeciesId(){return _speciesId;}
    public Boolean getCorrect(){return _correct;}
}
