board = [
          ["x","o","o"],
          ["_","x","o"],
          ["_","_","x"]
        ]
def printBoard():
  '''
  print the board with updated pieces
  '''
  print(' ' + board[0][0] + ' | ' + board[0][1] + ' | ' + board[0][2])
  print('-----------')
  print(' ' + board[1][0] + ' | ' + board[1][1] + ' | ' + board[1][2])
  print('-----------')
  print(' ' + board[2][0] + ' | ' + board[2][1] + ' | ' + board[2][2])
  
def isWon(player):
  '''
  check if the player has won the game by checking each row, col and diagonal
  return True if the player wins the game, False otherwise.
  '''
  for i in range(3):
    if board[i][0] == player and board[i][1] == player and board[i][2] == player:
      return True
  
  for i in range(3):
    if board[0][i] == player and board[1][i] == player and board[2][i] == player:
      return True
          
  if board[0][0] == player and board[1][1] == player and board[2][2] == player:
    return True
  
  if board[0][2] == player and board[1][1] == player and board[2][0] == player:
    return True
  
  return False


printBoard()
result = isWon("x")
if result:
  print("x won!")
else:
  print("No, x does not win the game")
