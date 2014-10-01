<!DOCTYPE html>
<html>

  <head>
    <title>Java TTT: Hi</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css">
  </head>
  <body>
    <form action="game">
      <div id="grid"> 
        <div class="cell">
          <p>X is...</p>
          <p><input id="x" type="radio" value="HumanPlayer" name="x" checked="true" />
          Human
          </p>
          <p><input type="radio" value="ComputerPlayer" name="x" />
          Computer 
          </p>
        </div>
        <div class="cell">
          <p>O is...</p>
          <p><input type="radio" value="HumanPlayer" name="o" checked="true" />
          Human
          </p>
          <p><input type="radio" value="ComputerPlayer" name="o" />
          Computer
          </p>
        </div>
        <div class="cell">
          <p>Board is...</p>
          <p><input type="radio" value="3" name="size" checked="true" />
          3x3
          </p>
          <p><input type="radio" value="4" name="size"/>
          4x4
          </p>
        </div>
        <div class="cell">
          <p><input type="submit" autofocus default
                    value="Play"/>
          </p>
        </div>
      </div>
    </form>
  </body>
</html>
