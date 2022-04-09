<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Rules</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Sansita+Swashed&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/rules.css">
</head>
<body>
<div class="board">
    <h1 class="topic"> Rules of Game Simpleton</h1>

    <ul>
        <li><h3>The deck is shuffled, and each player is dealt six cards. The bottom card of the stock is turned and placed face up on the table, its suit determining the trump suit for the current deal.</h3></li>
        <li><h3>The player who has the lowest trump card will be the first attacker. If the attack succeeds, the defender loses their turn and the attack passes to you. If the attack fails, the defender becomes the next attacker and you becomes a defender.</h3></li>
        <li><h3>A trump card of any rank beats all cards in the other three suits. </h3></li>
        <li><h3>The defender attempts to beat the attack card by playing a higher-ranking defending card from their hand, and the same suit as the attack card or play a card of the trump suit (there is no obligation to play the card of the same suit, you can use trump cards to beat off the attack anytime)</h3></li>
        <li><h3>After the original attack, attacks can only be made if the new attack card matches the rank of any card which has already been played during that round. </h3></li>
        <li><h3>There cannot be more than six attacks in each round.</h3></li>
        <li><h3>At any point during a round of attacks, if a defender is unwilling or unable to beat the most recent attack card, they may give up their defence and must pick up all the cards played during that round of attack (both defending and attacking).</h3></li>
        <li><h3>If, however, the defender has beaten all attacking cards and no other players are willing to make another attack or if the defender beats the sixth attack card, the defender has won the round of attacks. In this case all cards from that round of attack are placed in the trash and the defender starts a new round of attacks as the attacker</h3></li>
        <li><h3>The last person left with cards in their hand is the loser (the fool or durak). The winner of the round to be the first player to empty their hand. If opponent and you don't have cards, it is draw.</h3></li>
        <li><h3>Over deck you can see whose turn is it. When there writes "Player move", you can press at any card from your hand to move. Your opponent will make all automatically</h3></li>
        <li><h3>If you don't have any card to make move in this round, press "move to trash", cards from table will be moved to "trash".</h3></li>
        <li><h3>When you defend, and you can't beat your opponent card, press "give up".</h3></li>
        <li><h3>After end of the game, you can start a new game, for this please press the button "recharge cards".</h3></li>
        <li><h3>If you want, you can see cards from trash, for this just press on the card from there.</h3></li>
    </ul>
    <div class="buttons">
        <button type="button" onclick="window.location.href='/'">Back to Menu</button>
    </div>
</div>

</body>
</html>