<!DOCTYPE html> 
<html>
  <head>
    <title>Java TTT</title>
    <link rel="stylesheet" type="text/css" href="/styles.css" />
    <script src="/javascripts/jquery-2.1.1.min.js"></script>
    <script src="/javascripts/application.js"></script>
  </head>
  <body>
    <table data-id="board">
      <% for (int row = 0; row < board_size; ++row) { %>
        <tr>
          <% for (int row = 0; row < board_size; ++col) { %>
          <% (0...@board_size).each do |col| %>
            <td data-id="sq-<%= row * board_size + col %>">
              <a />
            </td>
          <% } %>
        </tr>
      <% } %>
    </table>
    <p data-id="status" />
  </body>
  <script>
    <!--
      new NoughtsAndCrosses.Game($(document.body), window.location.href).start()
    -->
  </script>
</html>


