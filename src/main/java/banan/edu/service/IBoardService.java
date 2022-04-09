package banan.edu.service;

import banan.edu.model.Card;
import banan.edu.model.Suit;

import java.util.List;

public interface IBoardService {
    void shuffleDeck(List<Card> deck);
    List<Card> getStack();
    List<Card> getPlayerCards();
    List<Card> getDealerCards();
    List<Card> getPlayerMoves();
    List<Card> getDealerMoves();
    List<Card> getTrash();
    String getMessage();

    Suit getTrump();
}
