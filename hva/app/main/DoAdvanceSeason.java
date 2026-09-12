package hva.app.main;

import hva.core.HotelManager;
import hva.core.Season;
import hva.core.Tree;
import java.util.*;
import pt.tecnico.uilib.menus.Command;

/**
 * Command for advancing the season of the system.
 **/
class DoAdvanceSeason extends Command<HotelManager> {
  DoAdvanceSeason(HotelManager receiver) {
    super(Label.ADVANCE_SEASON, receiver);
  }

  @Override
  protected final void execute() {
    Season currentSeason = _receiver.getHotel().getCurrentSeason();
    Season nextSeason;

    switch (currentSeason) {
        case SPRING -> {
            _display.addLine("1");
            nextSeason = Season.SUMMER;
        }
        case SUMMER -> {
            _display.addLine("2");
            nextSeason = Season.AUTUMN;
        }
        case AUTUMN -> {
            _display.addLine("3");
            nextSeason = Season.WINTER;
        }
        default -> {
            _display.addLine("0");
            nextSeason = Season.SPRING;
        }
    }
    _receiver.getHotel().setSeason(nextSeason);

    List<Tree> trees = _receiver.getHotel().getTrees();

    for (Tree tree: trees) tree.setSeason(nextSeason);
    _display.display();
  
    _receiver.getHotel().notSaved();
  }
}