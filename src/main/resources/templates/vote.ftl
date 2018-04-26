<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <meta charset="UTF-8">
    <style>
        body{
            display: block;
            margin: 30px 10px 10px 50px;
            font-family:  Century Gothic;
        }
        button{
            display: block;
            width:auto;
            margin: 10px;
            margin-top: 20px;
            vertical-align: top;
            padding: 7px 20px;
            font-family:Сomic Sans MS;
            font-size: 20px;
            color: #111;
            text-decoration: none;
            text-shadow: 0 0 2px rgba(255, 255, 255, 1);
            background-color: #ccc;
            border: 1px solid;
            -moz-border-radius:  5px; /* Firefox */
            -webkit-border-radius:  5px; /* Safari 4 */
            border-radius:  5px;
        }
        h2{
            font-style: normal;
            font-weight: normal;
        }
        input[type="text"]{
            margin-top: 21px;
            width: 300px;
            border-radius:  7px;
        }
        input[type="radio"]{
            width: 60px;
        }
        lable{
            margin: 30px;
            text-transform:  capitalize;
            font-size: 1.3em;
            font-family:Сomic Sans MS ;
        }
    </style>
</head>
<body >
<h2>Форма голосования</h2>
<form action="/" method="get">
    <input  type="text" placeholder="Ваше ФИО" name="fio">
    <h2>${question}</h2>
<#list options as option>
    <div  class="radio">
        <input  type="radio" name="radio" value="voteOption" id="rb" onclick="document.getElementById('voteResult').value='${option.name}'" >
        <lable for="rb">${option.name}</lable>
    </div>
</#list>
    <input type="hidden" name="voteResult" id="voteResult">
    <button type="submit">Проголосовать</button>
</form>
</body>
</html>