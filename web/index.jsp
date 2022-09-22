

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <link rel="icon" href="img/favicon.jpg">
  <title>Ketering služba - Pocetna strana</title>
   <link rel="icon" href="img/favicon.jpg">
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/all.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="css/mojstil.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="js/includeScript.js"></script>
</head> 
<body>
  <div class="container">
  <div w3-include-html="header.jsp"></div>
  <div class="row">
        <div class="col-md-12"><div id="carousel-example-generic" class="carousel slide" data-ride="carousel" data-interval="4000">
          <!-- Indicators -->
          <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
          </ol>

          <!-- Wrapper for slides -->
          <div class="carousel-inner" role="listbox"> <!-- Ovo je pocetak carousela -->
            <div class="item active">
              <img class="slideimg" src="img/slanahrana.jpg" alt="...">
              <div class="carousel-caption">
                <h2> Slana Hrana</h2>
                <p><a class="curlink" href="slaniproizvodi" class="curlink">Poruci</a></p>
              </div>
            </div>
            <div class="item">
              <img src="img/slatkahrana.jpg" alt="...">
              <div class="carousel-caption">
                <h2> Slatka hrana</h2>
                <p><a class="curlink" href="slatkiproizvodi" class="curlink">Poruci</a></p>
              </div>
            </div>
          </div> <!-- Ovo je kraj carousela -->
          <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Prethodna</span>
          </a>
          <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Sledeća</span>
          </a>
        </div></div>

      </div>
  <div w3-include-html="footer.html"></div>
</div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script>
    includeHTML();
  </script>
</body>
</html>