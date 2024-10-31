package pieces;

import utils.PieceColor;
import utils.Position;

public class King extends Piece {
    public King(PieceColor color, Position position) {
        // Construtor que inicializa com uma cor
        // e uma posicao chamando o construtor da classe base Piece
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        // Valida o movimento do Rei baseando-se nas regras do xadrez
        int rowDiff = Math.abs(position.getRow() - newPosition.getRow());
        int colDiff = Math.abs(position.getColumn() - newPosition.getColumn());
        // Calcula a diferenca entre a posicao inicial e final

        boolean isOneSquareMove = rowDiff <= 1 && colDiff <= 1 && !(rowDiff == 0 && colDiff == 0);
        // Verifica se o rei esta se movendo apenas uma casa (podendo ser nenhuma) na diagonal, vertical ou horizontal

        if (!isOneSquareMove) {
            // Caso nao seja um movimento valido
            return false;
        }

        Piece destinationPiece = board[newPosition.getRow()][newPosition.getColumn()];
        return destinationPiece == null || destinationPiece.getColor() != this.getColor();
        // Verifica se existe uma nova peca na posicao final e caso tenha e seja de cor
        // diferente o movimento é completado e é feito uma captura ou caso a posicao
        // esteja vazia
    }
}