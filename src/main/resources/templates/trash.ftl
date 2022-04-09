<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Trash</title>
    <style>
        body {
            background: url("/image/background.jpeg");
            background-size: 2550px ;
            height: 100%;
            max-height: 1320px;
        }

        html{
            height: 100%;
        }

        .cards{
            display: flex;
            margin: 0 auto;
            width: 95%;
            flex-wrap: wrap;
            margin-top: 20px;
        }
        img{
            height: 130px;
            margin-right: 7px;
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
    <div class="cards">
        <#list trash as card>
            <img src="${card.image}" alt="">
        </#list>
    </div>
</body>
</html>