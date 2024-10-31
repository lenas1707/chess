package pieces;

import utils.PieceColor;
import utils.Position;

public class Pawn extends Piece {
    public Pawn(PieceColor color, Position position) {
        // Construtor que inicializa com uma cor
        // e uma posicao chamando o construtor da classe base Piece
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        int forwardDirection = color == PieceColor.WHITE ? -1 : 1;
        // Determina a direcao que o peao se move, branco -1 e preto 1
        int rowDiff = (newPosition.getRow() - position.getRow()) * forwardDirection;
        int colDiff = newPosition.getColumn() - position.getColumn();

        if (colDiff == 0 && rowDiff == 1 && board[newPosition.getRow()][newPosition.getColumn()] == null) {
            // Verifica o movimento simples e se a posicao esta vazia
            return true;
        }

        boolean isStartingPosition = (color == PieceColor.WHITE && position.getRow() == 6) ||
                (color == PieceColor.BLACK && position.getRow() == 1);
        // Verifica se o peao esta na posicao inicial(linha 6 brancos e 1 pretos)
        // e tenta se mover duas casas para frente verificando se a casa esta vazia
        if (colDiff == 0 && rowDiff == 2 && isStartingPosition
                && board[newPosition.getRow()][newPosition.getColumn()] == null) {
            int middleRow = position.getRow() + forwardDirection;
            if (board[middleRow][position.getColumn()] == null) {
                return true;
            }
        }

        if (Math.abs(colDiff) == 1 && rowDiff == 1 && board[newPosition.getRow()][newPosition.getColumn()] != null &&
                board[newPosition.getRow()][newPosition.getColumn()].color != this.color) {
            // Verifica se tem uma peca adversaria na diagonal da peca e possibilita sua captura
            return true;
        }

        return false;
        // Caso nenhuma condicao seja satisfeita o movimento nao é valido
    }
}