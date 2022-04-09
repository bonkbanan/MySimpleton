<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Board</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Sansita+Swashed&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/main.css">
    <#list deck as card>
        <style>
            #card${card.id}{
                position: absolute;
                left: ${card.value}px;
            }
        </style>
    </#list>
    <#list trash as card>
        <style>
            #card${card.id}{
                position: absolute;
                transform: rotate(${card.value}deg);
            }
        </style>
    </#list>
    <#list playerCards as card>
        <style>
            #card${card.number}{
                position: relative;
                top: ${card.top}px;
                left: ${card.position}px;
                transform: rotate(${card.rotation}deg);
            }
        </style>
    </#list>
    <#list dealerCards as card>
        <style>
            #card${card.number}{
                position: relative;
                top: ${card.top}px;
                left: ${card.position}px;
                transform: rotate(${card.rotation}deg);
                margin: 0;
                background: transparent;
                border: none;
                width: 10px;
                display: flex;
                align-items: start;
            }
        </style>
    </#list>
    <#list moves as card>
        <style>
            #card${card.number}{
                position: relative;
                top: ${card.top}px;
                left: ${card.position}px;
                transform: rotate(${card.rotation}deg);
                margin: 0;
                background: transparent;
                border: none;
                width: 10px;
            }
        </style>
    </#list>

</head>
<body>
<div class="boards">
    <div class="dealerCards">
        <div class="dealerLeft">
            <img src="${dealerAvatar}" alt="" class="dealerAvatar">
            <h3 class="delaerName">${dealerName}</h3>
        </div>
        <div class="dealCards2">
            <div class="cards">
                <#list dealerCards as card>
                    <button id="card${card.number}" ><img src="/image/back.png" alt=""></button>
                </#list>
            </div>
        </div>
        <div class="dealerRight"></div>
    </div>
    <div class="tableCenter">
        <div class="leftCenter">
            <button onclick="window.open('/ui/trash')">
                <#list trash as card>
                    <img id="card${card.id}" src="${card.image}" alt="">
                </#list>
            </button>
        </div>
        <div class="table">
            <div class="moves">
                <h1 class="finalMessage">${finalMessage}</h1>
                <#list moves as card>
                    <button id="card${card.number}" ><img src="${card.image}" alt=""></button>
                </#list>
            </div>

        </div>
        <div class="rightCenter">
            <h1 class="message">${turn}</h1>
            <div class="deck">
                    <#list deck as card>
                        <img src="${card.image}" id="card${card.id}" alt="">
                    </#list>
                    <h3 class="cardsNumb">${cardsSize}</h3>
            </div>
        </div>
    </div>
    <div class="bottom">
        <div class="leftBottom">
            <img class="playerAvatar" src="/avatars/Bart.jpeg" alt="">
            <h3 class="ourNick">Bart Simpson</h3>
        </div>
        <div class="playerCards">
            <div class="cards">
                <#list playerCards as card>
                    <button id="card${card.number}" onclick="window.location.href='/ui/player/throw/${card.id}'"><img src="${card.image}" alt=""></button>
                </#list>
            </div>
        </div>
        <div class="buttons">
            <button type="button" onclick="window.location.href='/ui/move/to/trash'">move to trash</button>
            <button type="button" onclick="window.location.href='/ui/give/up'">give up</button>
            <button type="button" onclick="window.location.href='/ui/new/deck'">recharge cards</button>
        </div>
    </div>
</div>
</body>
</html>