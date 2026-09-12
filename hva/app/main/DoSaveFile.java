package hva.app.main;

import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
import java.io.IOException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;


/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<HotelManager> {
  DoSaveFile(HotelManager receiver) {
    super(Label.SAVE_FILE, receiver, r -> r.getHotel() != null);
  }

  @Override
  protected final void execute() {
    try {
        String currentFileName = _receiver.getHotel().getFileName(); 
        if (currentFileName != null) {
            _receiver.save();
        } else {
            String newFileName = Form.requestString(Prompt.newSaveAs());
            _receiver.saveAs(newFileName);
            _receiver.getHotel().setFileName(newFileName);
        }
        _receiver.getHotel().saved();
    } catch (IOException | MissingFileAssociationException e) {
        _display.addLine(e.getMessage());
        _display.display();
    }
  }
}