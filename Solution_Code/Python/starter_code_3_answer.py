'''
Starter Code 3
'''
board = [
          ["x","o","_"],
          ["o","x","x"],
          ["_","_","o"]
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

def validMove(row, col):
  '''
  check the given row and col is valid - no piece on that square
  return True if the given square is "_" meaning no piece occupied it
  False otherwise.
  '''

  # Row is the row of the board
  # Col is the column of the board

  # ------------- ONLY edit the line below! ----------------
  return board[row][col] is "_"
  
  # ------------- ONLY edit the line above ------------------

row = 0
col = 2

printBoard()

print( "My move is: " + str(row) + ", " + str(col) )

if validMove(row, col) :
  print( "This is a valid move ")
else:
  print( "Sorry that cell is already occupied. Please try again.")
