package banan.edu.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Deck {
    private List<Card> deck = new ArrayList<>();

    public List<Card> getDeck() {
        return deck;
    }

    public List<Card> partDeck(){
            getLastCards();
        return deck;
    }

    public List<Card> allDeck(){
        deck.add(new Card (0, Suit.CLUB , Denomination.TWO , 2, "/image/C2.png"));
        deck.add(new Card (1,Suit.CLUB , Denomination.THREE , 3, "/image/C3.png"));
        deck.add(new Card (2,Suit.CLUB , Denomination.FOUR , 4, "/image/C4.png"));
        deck.add(new Card (3,Suit.CLUB , Denomination.FIVE , 5, "/image/C5.png"));
        deck.add(new Card (13,Suit.HEART , Denomination.TWO , 2, "/image/H2.png"));
        deck.add(new Card (14,Suit.HEART , Denomination.THREE , 3, "/image/H3.png"));
        deck.add(new Card (15,Suit.HEART , Denomination.FOUR , 4, "/image/H4.png"));
        deck.add(new Card (16,Suit.HEART , Denomination.FIVE , 5, "/image/H5.png"));
        deck.add(new Card (26,Suit.SPADE , Denomination.TWO , 2, "/image/P2.png"));
        deck.add(new Card (27,Suit.SPADE , Denomination.THREE , 3, "/image/P3.png"));
        deck.add(new Card (28,Suit.SPADE , Denomination.FOUR , 4, "/image/P4.png"));
        deck.add(new Card (29,Suit.SPADE , Denomination.FIVE , 5, "/image/P5.png"));
        deck.add(new Card (39,Suit.DIAMOND , Denomination.TWO , 2, "/image/T2.png"));
        deck.add(new Card (40,Suit.DIAMOND , Denomination.THREE , 3, "/image/T3.png"));
        deck.add(new Card (41,Suit.DIAMOND , Denomination.FOUR , 4, "/image/T4.png"));
        deck.add(new Card (42,Suit.DIAMOND , Denomination.FIVE , 5, "/image/T5.png"));
        getLastCards();
        return deck;}

        public void getLastCards(){
            deck.add(new Card (4,Suit.CLUB , Denomination.SIX , 6, "/image/C6.png"));
            deck.add(new Card (5,Suit.CLUB , Denomination.SEVEN , 7, "/image/C7.png"));
            deck.add(new Card (6,Suit.CLUB , Denomination.EIGHT , 8, "/image/C8.png"));
            deck.add(new Card (7,Suit.CLUB , Denomination.NINE , 9, "/image/C9.png"));
            deck.add(new Card (8,Suit.CLUB , Denomination.TEN , 10, "/image/C10.png"));
            deck.add(new Card (9,Suit.CLUB , Denomination.JACK , 11, "/image/CJ.png"));
            deck.add(new Card (10,Suit.CLUB , Denomination.QUEEN , 12, "/image/CQ.png"));
            deck.add(new Card (11,Suit.CLUB , Denomination.KING , 13, "/image/CK.png"));
            deck.add(new Card (12,Suit.CLUB , Denomination.ACE , 14, "/image/CA.png"));
            deck.add(new Card (17,Suit.HEART , Denomination.SIX , 6, "/image/H6.png"));
            deck.add(new Card (18,Suit.HEART , Denomination.SEVEN , 7, "/image/H7.png"));
            deck.add(new Card (20,Suit.HEART , Denomination.NINE , 9, "/image/H9.png"));
            deck.add(new Card (21,Suit.HEART , Denomination.TEN , 10, "/image/H10.png"));
            deck.add(new Card (22,Suit.HEART , Denomination.JACK , 11, "/image/HJ.png"));
            deck.add(new Card (19,Suit.HEART , Denomination.EIGHT , 8, "/image/H8.png"));
            deck.add(new Card (23,Suit.HEART , Denomination.QUEEN , 12, "/image/HQ.png"));
            deck.add(new Card (24,Suit.HEART , Denomination.KING , 13, "/image/HK.png"));
            deck.add(new Card (25,Suit.HEART , Denomination.ACE , 14, "/image/HA.png"));
            deck.add(new Card (30,Suit.SPADE , Denomination.SIX , 6, "/image/P6.png"));
            deck.add(new Card (31,Suit.SPADE , Denomination.SEVEN , 7, "/image/P7.png"));
            deck.add(new Card (32,Suit.SPADE , Denomination.EIGHT , 8, "/image/P8.png"));
            deck.add(new Card (33,Suit.SPADE , Denomination.NINE , 9, "/image/P9.png"));
            deck.add(new Card (34,Suit.SPADE , Denomination.TEN , 10, "/image/P10.png"));
            deck.add(new Card (35,Suit.SPADE , Denomination.JACK , 11, "/image/PJ.png"));
            deck.add(new Card (36,Suit.SPADE , Denomination.QUEEN , 12, "/image/PQ.png"));
            deck.add(new Card (37,Suit.SPADE , Denomination.KING , 13, "/image/PK.png"));
            deck.add(new Card (38,Suit.SPADE , Denomination.ACE , 14, "/image/PA.png"));
            deck.add(new Card (43,Suit.DIAMOND , Denomination.SIX , 6, "/image/T6.png"));
            deck.add(new Card (44,Suit.DIAMOND , Denomination.SEVEN , 7, "/image/T7.png"));
            deck.add(new Card (45,Suit.DIAMOND , Denomination.EIGHT , 8, "/image/T8.png"));
            deck.add(new Card (46,Suit.DIAMOND , Denomination.NINE , 9, "/image/T9.png"));
            deck.add(new Card (47,Suit.DIAMOND , Denomination.TEN , 10, "/image/T10.png"));
            deck.add(new Card (48,Suit.DIAMOND , Denomination.JACK , 11, "/image/TJ.png"));
            deck.add(new Card (49,Suit.DIAMOND , Denomination.QUEEN , 12, "/image/TQ.png"));
            deck.add(new Card (50,Suit.DIAMOND , Denomination.KING , 13, "/image/TK.png"));
            deck.add(new Card (51,Suit.DIAMOND , Denomination.ACE , 14, "/image/TA.png"));
        }


        public void clear(){
            deck.removeAll(deck);
        }
}
