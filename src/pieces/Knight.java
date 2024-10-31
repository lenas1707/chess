package pieces;

import utils.PieceColor;
import utils.Position;

public class Knight extends Piece {
    public Knight(PieceColor color, Position position) {
        // Construtor que inicializa com uma cor
        // e uma posicao chamando o construtor da classe base Piece
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        if (newPosition.equals(this.position)) {
            // Verifica se a nova posicao é igual a posicao atual e se for retorna false
            // pois o cavalo nao pode se mover para a mesma casa
            return false;
        }

        int rowDiff = Math.abs(this.position.getRow() - newPosition.getRow());
        int colDiff = Math.abs(this.position.getColumn() - newPosition.getColumn());
        // Calcula a diferenca entre a posicao inicial e final

        boolean isValidLMove = (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
        // O cavalo movimenta em 'L' (duas em uma direcao e uma perpendicular ou vice-versa)

        if (!isValidLMove) {
            return false;
        }

        Piece targetPiece = board[newPosition.getRow()][newPosition.getColumn()];
        if (targetPiece == null) {
            return true;
        } else {
            return targetPiece.getColor() != this.getColor();
        }
        // Cavalo salta as pecas que estao no seu caminho
    }
}