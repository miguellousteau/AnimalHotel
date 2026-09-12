package hva.app.main;

import hva.core.HotelManager;

import hva.app.exception.FileOpenFailedException;
import hva.core.exception.UnavailableFileException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<HotelManager> {
  DoOpenFile(HotelManager receiver) {
    super(Label.OPEN_FILE, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    if (!_receiver.getHotel().getSavedState()){
      if (Form.confirm(Prompt.saveBeforeExit())){
        new DoSaveFile(_receiver).execute();
      }
    }

    String fileNameToOpen = Form.requestString(Prompt.openFile());

    try {
        _receiver.load(fileNameToOpen);
    } catch (UnavailableFileException e) {throw new FileOpenFailedException(e);}
  }
}