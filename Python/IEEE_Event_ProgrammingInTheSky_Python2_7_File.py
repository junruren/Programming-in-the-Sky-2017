from random import *
board = [
          ["_","_","_"],
          ["_","_","_"],
          ["_","_","_"]
        ]
def printBoard():
  
  print(' ' + board[0][0] + ' | ' + board[0][1] + ' | ' + board[0][2])
  print('-----------')
  print(' ' + board[1][0] + ' | ' + board[1][1] + ' | ' + board[1][2])
  print('-----------')
  print(' ' + board[2][0] + ' | ' + board[2][1] + ' | ' + board[2][2])
    
def isWon(player):
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

def validMove(row, col):
  return board[row][col] is "_"

def humanTurn( symbol ):
  row, col = raw_input("Enter your move: (row column):").split()
  row = int(row)
  col = int(col)
  while( validMove(row, col) is False ):
    print("Sorry that cell is already occupied. Please try again.")
    row, col = raw_input("Enter your move: (row column):")
  
  board[row][col] = symbol
  printBoard()
  
  if isWon(symbol):
    print("You Won!")
    return True
  else:
    return False

def computerTurn( symbol ):
  row = randint(0, 2)
  col = randint(0, 2)
  while( validMove(row, col) is False ):
    row = randint(0, 2)
    col = randint(0, 2)
  
  print("My move is {} {}".format(row, col))
  board[row][col] = symbol
  printBoard()
  if isWon(symbol):
    print("I won!")
    return True
  else:
    return False

def humanFirst():
  for i in range(4):
    if humanTurn("x") is True:
      break
    if computerTurn("o") is True:
      break
  else:
    if humanTurn("x") is False:
      print("A tie!")

def computerFirst():
  computerTurn("x")
  for i in range(4):
    if humanTurn("o") is True:
      break
    if computerTurn("x") is True:
      break
  else:
    print("A tie!")

def main():
  move = randint(0,1)
  if move is 0:
    print("You start first. You get x and I get o")
    humanFirst()
  else:
    print("I start first. I get x and you get o")
    computerFirst()

main()