package TicTacToe;

import GameInterfaces.Board;
import GameInterfaces.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import Game.Move;

public class TTT_Game implements Game {
    private int turn;
    private String id;
    private boolean active;
    private TTT_Board board;
    private List<Move> moveHistory;

    TTT_Game() {
        id = UUID.randomUUID().toString();
        board = null;
        moveHistory = null;
        active = false;
        turn = 0;
    }



    @Override
    public void startGame() throws Exception {
        if(board != null)
            throw new Exception("Game has already started!");
        else {
            board = new TTT_Board();
            moveHistory = new ArrayList<>();
            active = true;
            turn = 0;
        }
    }

    @Override
    public void endGame() throws Exception {active = false;}

    @Override
    public boolean isFinished() {return !board.hasMovesLeft();}

    @Override
    public void saveGame() throws Exception {}

    @Override
    public String getGameId() {return id;}

    @Override
    public void performMove(Move nextMove) throws Exception {
        if(!active)
            throw new Exception("Illegal Move Exception: Move attempted on an inactive game!");
        else if(!board.hasMovesLeft())
            throw new Exception("Illegal Move Exception: Move attempted on a finished game!");
        else if(nextMove.getPlayer() != turn%2 + 1)
            throw new Exception("Illegal Move Exception: Move attempted by player" + nextMove.getPlayer()
                    + "on player" + turn%2+1 + "'s turn!");
        else {
            board.setPosition(nextMove.getPlayer(), nextMove.getRow(), nextMove.getColumn());
            moveHistory.add(nextMove);
            turn++;
        }
    }

    @Override
    public void undoMove() throws Exception {
        if(!active)
            throw new Exception("Illegal Move Exception: Undo-move attempted on an inactive game!");
        else if(moveHistory.isEmpty())
            throw new Exception("Illegal Move Exception: Undo-move attempted before a move has taken place!");
        else {
            Move lastMove = moveHistory.get(moveHistory.size() - 1);
            board.unsetPosition(lastMove.getRow(), lastMove.getColumn());
            moveHistory.remove(lastMove);
            turn--;
        }
    }

    @Override
    public void restart() throws Exception {
        if(active) {
            moveHistory.clear();
            board.clearBoard();
            turn = 0;
        }
        else
            throw new Exception("Game is not active!");
    }

    @Override
    public int getNextPlayer() {return turn%2 + 1;}

    @Override
    public int getWinner() throws Exception {return board.getWinningPlayer();}

    @Override
    public <T extends Move> List<Move> getMoveHistory() {return moveHistory;}

    @Override
    public Board getBoard() throws Exception {
        if(board == null)
            throw new Exception("Game has not been started!");

        return board;
    }

   /* @Override
    public void addGameListener(GameListener listener) {

    }

    @Override
    public void removeGameListener(GameListener listener) {

    }*/
}
