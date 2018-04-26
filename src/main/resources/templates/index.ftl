<html>
<head>
<meta charset="UTF-8">
  <link href="style.css" rel="stylesheet" type="text/css" media="all" />
<style>
    body{
        display: block;
        margin: 30px 10px 10px 50px;
    font-family:  Century Gothic;
    }
    button{
         display: block;
  width:auto;
 margin-top: 15px;
  vertical-align: top;
  padding: 7px 20px;
  font-family:Сomic Sans MS;
  font-size: 20px;
  color: #111;
  text-decoration: none;
  text-shadow: 0 0 2px rgba(255, 255, 255, 1);
  background-color: #ccc;
  border: 1px solid;
  border-color: #202020 #1a1a1a #111;
  border-radius: 2px;
  -moz-border-radius:  5px; /* Firefox */
-webkit-border-radius:  5px; /* Safari 4 */
border-radius:  5px; 
    }
#progress {
    width: 400px; /* Ширина индикатора */

    position: relative; /* Относительное позиционирование */
    background: #fff; /* Цвет фона */
    margin:8px; 
    height: 5%;
    box-shadow: 0 0 4px 2px #ccc;/* свечение */
   }
   #progress .bg {
    background: #b2a3ff; /* Цвет фона индикатора */
    position: absolute; /* Абсолютное позиционирование */
    height: 100%; /* Фон занимает всю высоту */
   }
   #progress .begin, #progress .end {
    position: absolute; /* Абсолютное позиционирование */
    font-size: 0.9em; /* Размер текста */
    top: 25%; /* Сдвигаем вниз */
   } 
   #progress .end { 
    right: 0; /* Располагаем по правому краю */
    padding-right: 4px; /* Отступ справа */
   }
   #progress .current { 
    position: relative; /* Относительное позиционирование */
    text-align: left; 
    margin-left: 7px;
   }


</style>
</head> 
<body>
<form action="/">
    <button type="submit">Обновить</button>
</form>
<form action="/vote" method="get">
    <h2>${subject}</h2>
    <button type="submit">Проголосовать</button>
<h2>Результаты голосования</h2> 
<#list options as option> 
  <div id="progress">
   <div class="bg" style="width:${option.percent}%"></div>
   <span class="end">${option.percent}%</span>
   <div class="current">${option.name}</div>
  </div>
  </#list> 

</form> 
       </body> 
</html>

