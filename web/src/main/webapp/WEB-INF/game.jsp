<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Java TTT</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css" />
    <script src="/resources/js/jquery-2.1.1.min.js"></script>
    <script src="/resources/js/URI.js"></script>
    <script src="/resources/js/game.js"></script>
  </head>
  <body>
    <table data-id="board">
      <c:forEach begin="0" end="${boardSize-1}" var="row">
        <tr>
          <c:forEach begin="0" end="${boardSize-1}" var="col">
          <td data-id="sq-${row * boardSize + col}">
              <a />
            </td>
          </c:forEach>
        </tr>
      </c:forEach>
    </table>
    <p data-id="status" />
  </body>
  <script>
    <!--
      new NoughtsAndCrosses.Game($(document.body), window.location.href).start()
    -->
  </script>
</html>


