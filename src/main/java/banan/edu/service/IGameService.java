package banan.edu.service;

import banan.edu.model.Card;

import java.util.List;

public interface IGameService {
    Card dealerMove();
    Card myDefence();
    void dealerDefence();

    List<Card> moveToTrash();
    List<Card> giveUpAndTakeCards();
}
