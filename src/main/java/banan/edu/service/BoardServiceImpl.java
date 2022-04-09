package banan.edu.service;

import banan.edu.model.Board;
import banan.edu.model.Card;
import banan.edu.model.Deck;
import banan.edu.model.Suit;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements IBoardService {
     Deck deck;
     Board board = new Board();

    public BoardServiceImpl() {

        deck = new Deck();
        if(board.getCards()==52 && deck.getDeck().size() < 52){
            deck.allDeck();
        }
        if(board.getCards() == 36 && deck.getDeck().size() < 36){
            deck.partDeck();
        }
        List<Card> deckCopy = new ArrayList<>(deck.getDeck());
        shuffleDeck(deckCopy);
        board.getStack().addAll(deckCopy);
        generateDealerNameAndAvatar();

    }

    public void generateDealerNameAndAvatar(){

        List<String> avatars = new ArrayList<>(
                Arrays.asList(
                        "/avatars/Abraham.png", "/avatars/Apu.png",
                        "/avatars/Homer.jpeg", "/avatars/Lisa.jpeg",
                        "/avatars/Marge.png", "/avatars/Montgomery Burns.jpeg",
                        "/avatars/Second number Bob.jpeg"
                )
        );
        List<String> names = new ArrayList<>(
                Arrays.asList(
                        "Abraham Simpson","Apu","Homer Simpson",
                        "Lisa Simpson", "Marge Simpson",
                        "Montgomery Burns","Second number Bob"
                )
        );
        int random = (int) (Math.random()*names.size());

        board.setDealerName(names.get(random));
        board.setDealerAvatar(avatars.get(random));
    }

    // привіт світ
    @Override
    public void shuffleDeck(List<Card> deck) {
        if(board.getDealerCards().size()>0){
            board.getDealerCards().stream().filter(el->el.getSuit().equals(board.getTrump()))
                    .forEach(el->el.setValue(el.getValue()-13));
            deck.addAll(board.getDealerCards());
            getDealerCards().removeAll(getDealerCards());
        }
        if(board.getPlayerCards().size()>0){
            board.getPlayerCards().stream().filter(el->el.getSuit().equals(board.getTrump()))
                    .forEach(el->el.setValue(el.getValue()-13));
            deck.addAll(getPlayerCards());
            getPlayerCards().removeAll(getPlayerCards());
        }
        Collections.shuffle(deck);
        board.setTrump(deck.get(0).getSuit());
        board.setTrumpCard(deck.get(0));
    }

    @Override
    public List<Card> getStack() {
        List<Card> cards = board.getStack();
        return cards;
    }

    @Override
    public List<Card> getPlayerCards() {
        return board.getPlayerCards();
    }

    @Override
    public List<Card> getDealerCards() {
        return board.getDealerCards();
    }

    @Override
    public List<Card> getPlayerMoves() {
        return board.getPlayerMoves();
    }

    @Override
    public List<Card> getDealerMoves() {
        return board.getDealerMoves();
    }

    @Override
    public List<Card> getTrash() {
        return board.getTrash();
    }

    @Override
    public String getMessage() {
        return board.getMessage();
    }

    public void firstGetTurn(){
        Card dealercard = getDealerCards().stream().filter(el->el.getSuit().equals(getTrump()))
                .min(Comparator.comparing(Card::getValue)).orElse(null);
        Card playercard = getPlayerCards().stream().filter(el->el.getSuit().equals(getTrump()))
                .min(Comparator.comparing(Card::getValue)).orElse(null);
        if(dealercard == null && playercard == null){
            board.setTurn(true);
            return;
        }
        if(dealercard == null && playercard != null){
            board.setTurn(true);
            return;
        }
        if(dealercard != null && playercard == null){
            board.setTurn(false);
            return;
        }
        if(playercard.getValue() > dealercard.getValue()){
            board.setTurn(false);
            return;
        }else{
            board.setTurn(true);
            return;
        }
    }

    public boolean getTurn() {
        return board.isTurn();
    }
    void setTurn(boolean turn){
        board.setTurn(turn);
    }

    @Override
    public Suit getTrump() {
        return board.getTrump();
    }
    public Card getTrumpCard() {
        return board.getTrumpCard();
    }

    public void rechargeCards() {
        int cards = board.getCards();
        board = new Board();
        if(cards==52){
            deck.clear();
            deck.allDeck();
        }
        if(cards == 36){
            deck.clear();
            deck.partDeck();
        }


        board.setCards(cards);
        List<Card> deckCopy = new ArrayList<>();
        deckCopy.addAll(deck.getDeck());
        shuffleDeck(deckCopy);
        board.getStack().addAll(deckCopy);

        generateDealerNameAndAvatar();
    }

    public void setMessage(String text){
        board.setMessage(text);
    }

    public void setCards(int cards){board.setCards(cards);}

    public int getCards(){return board.getCards();}

    public String getDealerName(){
        return board.getDealerName();
    }

    public String getDealerAvatar(){
        return board.getDealerAvatar();
    }

}
