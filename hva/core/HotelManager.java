package hva.core;

import hva.core.exception.*;
import java.io.*;

/**
 * Class representing the manager of this application. It manages the current
 * zoo hotel.
 **/
public class HotelManager {

  /** The current zoo hotel */
  private Hotel _hotel = new Hotel();
  
  /**
   * Saves the serialized application's state into the file associated with the current hotel.
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current hotel does not have a file.
   * @throws IOException if there is some error while serializing the state of the hotel to disk.
   **/
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(_hotel.getFileName()))) {
      out.writeObject(_hotel);
    }
  }
  
  /**
   * Saves the serialized application's state into the specified file. The current hotel is
   * associated with this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current hotel does not have a file.
   * @throws IOException if there is some error while serializing the state of the hotel to disk.
   **/
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
      out.writeObject(_hotel);
    }
    _hotel.setFileName(filename);
  }
  
  /**
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   **/
  public void load(String filename) throws UnavailableFileException {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
      _hotel = (Hotel) in.readObject();
      _hotel.setFileName(filename); 
      
    } catch (FileNotFoundException e) {
      throw new UnavailableFileException(filename);
    } catch (IOException | ClassNotFoundException e) {
      throw new UnavailableFileException("IO error while loading the file: " + e.getMessage());
    }
  }
  
  /**
   * Reads a text input file and initializes the current zoo hotel (which should be empty)
   * with the domain entities represented in the import file.
   *
   * @param filename name of the text input file
   * @throws ImportFileException if some error happens during the processing of the
   * import file.
   **/
  public void importFile(String filename) throws ImportFileException {
    try {
      _hotel.importFile(filename);
    } catch (IOException | UnrecognizedEntryException e) {
      throw new ImportFileException(filename, e);
    }
  } 
  
  /**
   * Returns the zoo hotel managed by this instance.
   *
   * @return the current zoo hotel
   **/
  public final Hotel getHotel() {return _hotel;}

  public final void setHotel(Hotel newHotel) {_hotel = newHotel;}
}