package banan.edu.controlers;

import banan.edu.model.Card;
import banan.edu.model.Suit;
import banan.edu.service.BoardServiceImpl;
import banan.edu.service.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/ui")
public class BoardUIController {

    public Card forMoves(List<Card> person, int i, int position, int rotation,int number, int top){
        return new Card(person.get(i).getId(),person.get(i).getSuit(),person.get(i).getDenomination(),
                person.get(i).getValue(),person.get(i).getImage(),position,rotation,number,top);
    }

    void forBoard(Model model){
        gameService.dealerMove();
        List<Card> deck = new ArrayList<>();
        List<Card> trash = new ArrayList<>();
        for (int i = 1; i < boardService.getStack().size(); i++) {
            deck.add(new Card(i,"/image/back.png",i*3));
        }
        if(boardService.getStack().size()!=0) {
            deck.add(new Card(0, boardService.getStack().get(0).getImage(), 0));
        }else{
            if(boardService.getTrump().equals(Suit.CLUB)){
                deck.add(new Card(2,"/image/Club.png",3));
            }
            if(boardService.getTrump().equals(Suit.DIAMOND)){
                deck.add(new Card(2,"/image/Diamond.png",3));
            }
            if(boardService.getTrump().equals(Suit.HEART)){
                deck.add(new Card(2,"/image/Heart.png",3));
            }
            if(boardService.getTrump().equals(Suit.SPADE)){
                deck.add(new Card(2,"/image/Spade.png",3));
            }
        }
        String size = "";
        if(deck.size()>0 && deck.get(0).getId()!=2){
            size += "cards: ";
            size += deck.size();
        }
        for (int i = 60; i < boardService.getTrash().size()+60; i++) {
            trash.add(new Card(i,"/image/back.png",i*3));
        }

        List<Card> dealerCards = new ArrayList<>();
        int number = 200;
        int top = boardService.getDealerCards().size();
        int rotation = boardService.getDealerCards().size()/2 +1;
        for (int i = 0; i < boardService.getDealerCards().size(); i++) {
            if(i==0){
                dealerCards.add(new Card(boardService.getDealerCards().get(i).getId(),
                        boardService.getDealerCards().get(i).getSuit(), boardService.getDealerCards().get(i).getDenomination(),
                        boardService.getDealerCards().get(i).getValue(), boardService.getDealerCards().get(i).getImage(),
                        i * 25, rotation * 3, number, -4));
            }
            if(i!=0) {
                if (i > boardService.getDealerCards().size() / 2 - 2) {
                    dealerCards.add(new Card(boardService.getDealerCards().get(i).getId(),
                            boardService.getDealerCards().get(i).getSuit(), boardService.getDealerCards().get(i).getDenomination(),
                            boardService.getDealerCards().get(i).getValue(), boardService.getDealerCards().get(i).getImage(),
                            i * 25, rotation * 3, number, top));
                } else {
                    dealerCards.add(new Card(boardService.getDealerCards().get(i).getId(),
                            boardService.getDealerCards().get(i).getSuit(), boardService.getDealerCards().get(i).getDenomination(),
                            boardService.getDealerCards().get(i).getValue(), boardService.getDealerCards().get(i).getImage(),
                            i * 25, rotation * 3, number, 0));
                }
            }
            top++;
            rotation--;
            number++;
        }



        List<Card> playerCards = new ArrayList<>();
        number = 100;
        top = boardService.getPlayerCards().size();
        rotation = -1 * boardService.getPlayerCards().size()/2 - 1;
        for (int i = 0; i < boardService.getPlayerCards().size(); i++) {
            if(i < boardService.getPlayerCards().size()/2) {
                playerCards.add(new Card(boardService.getPlayerCards().get(i).getId(),
                        boardService.getPlayerCards().get(i).getSuit(), boardService.getPlayerCards().get(i).getDenomination(),
                        boardService.getPlayerCards().get(i).getValue(), boardService.getPlayerCards().get(i).getImage(),
                        i * 25, rotation * 2, number, top));
            }else{
                playerCards.add(new Card(boardService.getPlayerCards().get(i).getId(),
                        boardService.getPlayerCards().get(i).getSuit(), boardService.getPlayerCards().get(i).getDenomination(),
                        boardService.getPlayerCards().get(i).getValue(), boardService.getPlayerCards().get(i).getImage(),
                        i * 25, (int)(rotation * 3.5), number, 0));
            }
            top--;
            rotation++;
            number++;
        }
        int position = 0;
        number = 300;
        List<Card> moves = new ArrayList<>();
        boolean movesBool = boardService.getDealerMoves().size() > boardService.getPlayerMoves().size();
        for (int i = 0; i < ((movesBool)? boardService.getDealerMoves().size() : boardService.getPlayerMoves().size()); i++) {
            if(movesBool) {
                moves.add(forMoves(boardService.getDealerMoves(), i, position, -8, number, 0));
                if ( i < boardService.getPlayerMoves().size()) {
                    number++;
                    position+=5;
                    moves.add(forMoves(boardService.getPlayerMoves(), i, position, 8, number, 5));
                }
            }else{
                moves.add(forMoves(boardService.getPlayerMoves(), i, position, -8, number, 0));
                if( i < boardService.getDealerMoves().size()){
                    number++;
                    position+=5;
                    moves.add(forMoves(boardService.getDealerMoves(), i, position, 8, number, 5));
                }
            }
            number++;
            position += 100;
        }

        String turnAsString = (boardService.getTurn())? "player move" : "dealer move";
        model.addAttribute("turn",turnAsString);
        model.addAttribute("dealerAvatar",boardService.getDealerAvatar());
        model.addAttribute("dealerName",boardService.getDealerName());
        model.addAttribute("moves",moves);
        model.addAttribute("deck",deck);
        model.addAttribute("playerCards",playerCards);
        model.addAttribute("dealerCards",dealerCards);
        model.addAttribute("cardsSize",size);
        model.addAttribute("finalMessage",boardService.getMessage());
        model.addAttribute("trash",trash);
    }

    @Autowired
    BoardServiceImpl boardService;

    @Autowired
    GameServiceImpl gameService;

    @GetMapping("/rules")
    String getHowToPlay(){
        return "rules";
    }

    @GetMapping("/36")
    String getBoard(Model model){
        boardService.setCards(36);
        boardService.rechargeCards();
        gameService.giveCards();
        gameService.giveCardsToDealer();
        boardService.firstGetTurn();
        forBoard(model);
        return "board";
    }

    @GetMapping("/52")
    String getSecondBoard(Model model){
        boardService.setCards(52);
        boardService.rechargeCards();
        gameService.giveCards();
        gameService.giveCardsToDealer();
        boardService.firstGetTurn();
        forBoard(model);
        return "board";
    }

    @GetMapping("/player/throw/{id}")
    String getPlayerThrowCard(Model model, @PathVariable("id") int id){
        gameService.makeMove(id);
        gameService.dealerDefence();
        forBoard(model);
        return "board";
    }

    @GetMapping("/move/to/trash")
    String getMoveToTrash(Model model){
        gameService.moveToTrash();
        forBoard(model);
        return "board";
    }

    @GetMapping("/give/up")
    String giveUp(Model model){
        gameService.giveUpAndTakeCards();
        gameService.giveCards();
        gameService.giveCardsToDealer();
        gameService.dealerMove();
        forBoard(model);
        return "board";
    }

    @GetMapping("/new/deck")
    String rechargeCards(Model model){
        boardService.rechargeCards();
        gameService.giveCards();
        gameService.giveCardsToDealer();
        boardService.firstGetTurn();
        if(boardService.getTurn() == false){
            gameService.dealerMove();
        }
        forBoard(model);
        return "board";
    }

    @GetMapping("/trash")
    String getTrash(Model model){
        List<Card> trash = new ArrayList<>();
        for (int i = 0; i < boardService.getTrash().size(); i++) {
            trash.add(new Card(i,boardService.getTrash().get(i).getImage(),i));
        }
        model.addAttribute("trash",trash);
        return "trash";
    }
}
