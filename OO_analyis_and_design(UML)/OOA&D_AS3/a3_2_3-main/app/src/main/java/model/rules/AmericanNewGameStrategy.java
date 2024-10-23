package model.rules;

import model.Dealer;
import model.Deck;
import model.Player;

class AmericanNewGameStrategy implements NewGameStrategy {

  public boolean newGame(Deck deck, Dealer dealer, Player player) {
    

    //player get first card
    dealer.giveCard(player, deck.getCard(), true);

    //dealer get first card and this card not hidden
    dealer.giveCard(dealer, deck.getCard(), true);

    //player get second card
    dealer.giveCard(player, deck.getCard(), true);

    //dealer get second card and this is hidden
    dealer.giveCard(dealer, deck.getCard(), false);

    return true;
  }
}