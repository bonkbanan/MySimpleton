package banan.edu.service;

import banan.edu.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements IGameService {

    @Autowired
    BoardServiceImpl boardService;

    public void makeMove(int cardId) {
        if(boardService.getTurn()){
            if(boardService.getDealerMoves().size() <= boardService.getPlayerMoves().size()) {
                if(boardService.getPlayerMoves().size() < 6) {
                    Card card = boardService.getPlayerCards().stream().filter(el -> el.getId() == cardId).findFirst().get();
                    if (boardService.getPlayerMoves().size() == 0) {
                        boardService.getPlayerMoves().add(card);
                        boardService.getPlayerCards().remove(card);
                        boardService.setTurn(!boardService.getTurn());
                    } else {
                        List<Card> cardsInAction = new ArrayList<>();
                        cardsInAction.addAll(boardService.getPlayerMoves());
                        cardsInAction.addAll(boardService.getDealerMoves());
                        List<Denomination> denominationsInAction =
                                cardsInAction.stream()
                                        .map(el -> el.getDenomination())
                                        .collect(Collectors.toList());
                        Denomination cardDenomination = card.getDenomination();
                        if (denominationsInAction.contains(cardDenomination)) {
                            boardService.getPlayerMoves().add(card);
                            boardService.getPlayerCards().remove(card);
                            boardService.setTurn(!boardService.getTurn());
                        }
                    }
                }
            }else{
                //Ми захищаємося
                if(isItPosible(cardId)){
                    Card card = boardService.getPlayerCards().stream().filter(el -> el.getId() == cardId).findFirst().get();
                    boardService.getPlayerMoves().add(card);
                    boardService.getPlayerCards().remove(card);
                    boardService.setTurn(!boardService.getTurn());
                }
            }
            if(boardService.getPlayerCards().size() == 0 && boardService.getStack().size() == 0){
                boardService.setMessage("You Win");
                return;
            }
            if(boardService.getDealerCards().size() == 0 && boardService.getStack().size() == 0){
                boardService.setMessage("You Lose!");
                return;
            }
            if(boardService.getDealerCards().size() == 0 && boardService.getPlayerCards().size() == 0 && boardService.getStack().size() == 0){
                boardService.setMessage("Draw");
                return;
            }
        }
    }

    @Override
    public Card dealerMove() {
        if(boardService.getTurn()==false) {
            if (boardService.getDealerCards().size() > 0) {
                if (boardService.getDealerMoves().size() < 6) {
                    if (boardService.getPlayerMoves().size() == 0) {
                        Card dealerAttack = boardService.getDealerCards().stream().min(Comparator.comparing(Card::getValue)).get();
                        boardService.getDealerCards().remove(dealerAttack);
                        boardService.getDealerMoves().add(dealerAttack);
                        boardService.setTurn(!boardService.getTurn());
                    } else {
                        List<Card> cardsInAction = new ArrayList<>();
                        cardsInAction.addAll(boardService.getDealerMoves());
                        cardsInAction.addAll(boardService.getPlayerMoves());
                        List<Denomination> denominations = cardsInAction.stream()
                                .map(Card::getDenomination).collect(Collectors.toList());
                        Card cardToAttack = boardService.getDealerCards().stream()
                                .filter(el -> denominations.contains(el.getDenomination()))
                                .sorted(Comparator.comparing(Card::getValue)).findFirst().orElse(null);
                        if (cardToAttack != null) {
                            boardService.getDealerCards().remove(cardToAttack);
                            boardService.getDealerMoves().add(cardToAttack);
                            boardService.setTurn(!boardService.getTurn());
                        } else {
                            moveToTrash();
                        }
                    }
                }
            }else{
                if(boardService.getPlayerCards().size() > 0 && boardService.getStack().size() == 0){
                    boardService.setMessage("You Lose!");
                }
            }
        }
        return null;
    }

    @Override
    public Card myDefence() {
        Card cardWhichIsAttacking = takeLastCard(boardService.getDealerMoves());
        Card cardToDefence = findCardToDeffence(cardWhichIsAttacking,boardService.getPlayerCards());
        return cardToDefence;
    }

    @Override
    public void dealerDefence() {
        if(boardService.getPlayerMoves().size() > boardService.getDealerMoves().size()) {
            List<Card> playerMoves = boardService.getPlayerMoves();
            if (playerMoves.size() != 0) {
                Card cardAttack = playerMoves.get(playerMoves.size() - 1);

                if (playerMoves.size() == boardService.getDealerMoves().size()) {
                    return;
                }

                if (playerMoves.size() < boardService.getDealerMoves().size()) {
                    boardService.setTurn(!boardService.getTurn());
                    return;
                }

                List<Card> trumps = boardService.getDealerCards().stream()
                        .filter(el -> el.getSuit().equals(boardService.getTrump()))
                        .filter(el -> el.getValue() > cardAttack.getValue())
                        .collect(Collectors.toList());
                List<Card> suits = boardService.getDealerCards().stream()
                        .filter(el -> el.getSuit().equals(cardAttack.getSuit()))
                        .filter(el -> el.getValue() > cardAttack.getValue()).collect(Collectors.toList());

                List<Card> listDeffence = new ArrayList<>();
                listDeffence.addAll(trumps);
                listDeffence.addAll(suits);
                Card cardAsDeffence;
                if (listDeffence.isEmpty()) {
                    cardAsDeffence = null;
                } else {
                    cardAsDeffence = listDeffence.stream().min(Comparator.comparing(Card::getValue)).get();
                }
                if (cardAsDeffence != null && cardAsDeffence.getValue() > cardAttack.getValue()) {
                    boardService.getDealerCards().remove(cardAsDeffence);
                    boardService.getDealerMoves().add(cardAsDeffence);
                    boardService.setTurn(!boardService.getTurn());
                } else {
                    boardService.getDealerCards().addAll(playerMoves);
                    boardService.getDealerCards().addAll(boardService.getDealerMoves());
                    boardService.getDealerMoves().removeAll(boardService.getDealerMoves());
                    boardService.getPlayerMoves().removeAll(boardService.getPlayerMoves());
                    boardService.setTurn(!boardService.getTurn());
                    giveCards();
                }
                if(boardService.getPlayerCards().size() == 0 && boardService.getStack().size() == 0){
                    boardService.setMessage("You Win");
                    return;
                }
                if(boardService.getDealerCards().size() == 0 && boardService.getStack().size() == 0){
                    boardService.setMessage("You Lose!");
                    return;
                }
                if(boardService.getDealerCards().size() == 0 && boardService.getPlayerCards().size() == 0 && boardService.getStack().size() == 0){
                    boardService.setMessage("Draw");
                    return;
                }
            }
        }else{
            dealerMove();
        }
    }

    public void giveCards() {
        if (boardService.getStack().size() > 0) {
            int playerHasCards = boardService.getPlayerCards().size();
            int mustGivetoPlayer = 6 - playerHasCards;
            if (boardService.getStack().size() > mustGivetoPlayer) {
                if (playerHasCards < 7 && !boardService.getStack().isEmpty()) {
                    for (int i = 0; i < mustGivetoPlayer; i++) {
                        Card card = boardService.getStack().get(boardService.getStack().size() - 1);
                        if (card.getSuit().equals(boardService.getTrump())) {
                            card.setValue(card.getValue() + 13);
                        }
                        boardService.getStack().remove(card);
                        boardService.getPlayerCards().add(card);
                    }
                }
            }else{
                for (int i = 0; i < boardService.getStack().size(); i++) {
                    Card card = boardService.getStack().get(boardService.getStack().size() - 1);
                    if (card.getSuit().equals(boardService.getTrump())) {
                        card.setValue(card.getValue() + 13);
                    }
                    boardService.getStack().remove(card);
                    boardService.getPlayerCards().add(card);
                }
            }
        }
        boardService.getPlayerCards().sort(Comparator.comparing(Card::getSuit).thenComparing(Card::getValue));
    }

    public void giveCardsToDealer() {
        if (boardService.getStack().size() > 0) {
            int dealerHasCards = boardService.getDealerCards().size();
            int mustGivetoDealer = 6 - dealerHasCards;
            if (boardService.getStack().size() > mustGivetoDealer) {
                if (dealerHasCards < 7 && !boardService.getStack().isEmpty()) {
                    for (int i = 0; i < mustGivetoDealer; i++) {
                        Card card = boardService.getStack().get(boardService.getStack().size() - 1);
                        if (card.getSuit().equals(boardService.getTrump())) {
                            card.setValue(card.getValue() + 13);
                        }
                        boardService.getStack().remove(card);
                        boardService.getDealerCards().add(card);
                    }
                }
            }else{
                for (int i = 0; i < boardService.getStack().size(); i++) {
                    Card card = boardService.getStack().get(boardService.getStack().size() - 1);
                    if (card.getSuit().equals(boardService.getTrump())) {
                        card.setValue(card.getValue() + 13);
                    }
                    boardService.getStack().remove(card);
                    boardService.getDealerCards().add(card);
                }
            }
        }
        boardService.getDealerCards().sort(Comparator.comparing(Card::getSuit).thenComparing(Card::getValue));
    }

    @Override
    public List<Card> moveToTrash() {
        if(boardService.getDealerMoves().size() == boardService.getPlayerMoves().size() &&
                boardService.getDealerMoves().size() != 0 && boardService.getPlayerMoves().size() !=0) {
            boardService.getTrash().addAll(boardService.getDealerMoves());
            boardService.getTrash().addAll(boardService.getPlayerMoves());
            boardService.getDealerMoves().removeAll(boardService.getDealerMoves());
            boardService.getPlayerMoves().removeAll(boardService.getPlayerMoves());
            boardService.setTurn(!boardService.getTurn());
            giveCards();
            giveCardsToDealer();
        }
        if (boardService.getTurn() == false) {
            dealerMove();
        }
        if(boardService.getStack().size() == 52){
            giveCards();
            giveCardsToDealer();
        }
            return null;
    }

    @Override
    public List<Card> giveUpAndTakeCards() {
        takeCards();
        if(boardService.getDealerCards().size() == 0 && boardService.getStack().size() == 0){
            boardService.setMessage("You Lose!");
        }
        return null;
    }


    private Card takeLastCard(List<Card> cards){
        if(cards.size()!=0){
            return cards.get(cards.size()-1);
        }else{
            return null;
        }
    }


    private Card findCardToDeffence(Card card,List<Card> cards){
        Card result = new Card();
        List<Card> listOfSuitToDefence = cards.stream()
                .filter(el->el.getSuit().equals(card.getSuit()))
                .filter(el->el.getValue() > card.getValue())
                .collect(Collectors.toList());
        List<Card> trumps = cards.stream()
                .filter(el->el.getSuit().equals(boardService.getTrump())).collect(Collectors.toList());
        List<Card> listToDefence = new ArrayList<>();
        listToDefence.addAll(listOfSuitToDefence);
        listToDefence.addAll(trumps);
        result = listToDefence.stream().min(Comparator.comparing(Card::getValue)).orElse(null);
        return result;
    }

    private Boolean isItPosible(int cardId){
        List<Card> cards = boardService.getPlayerCards();
        Card card = takeLastCard(boardService.getDealerMoves());
        List<Card> listOfSuitToDefence = cards.stream()
                .filter(el->el.getSuit().equals(card.getSuit()))
                .filter(el->el.getValue() > card.getValue())
                .collect(Collectors.toList());
        List<Card> trumps = cards.stream()
                .filter(el->el.getSuit().equals(boardService.getTrump())).collect(Collectors.toList());
        List<Card> listToDefence = new ArrayList<>();
        listToDefence.addAll(listOfSuitToDefence);
        listToDefence.addAll(trumps);
        Card chosenCard = boardService.getPlayerCards().stream().filter(el -> el.getId() == cardId).findFirst().get();
        return (listToDefence.contains(chosenCard));
    }

    private void takeCards(){
        boardService.getPlayerCards().addAll(boardService.getPlayerMoves());
        boardService.getPlayerCards().addAll(boardService.getDealerMoves());
        boardService.getDealerMoves().removeAll(boardService.getDealerMoves());
        boardService.getPlayerMoves().removeAll(boardService.getPlayerMoves());
        boardService.setTurn(false);
    }
}
