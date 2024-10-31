package pieces;

import utils.PieceColor;
import utils.Position;

public class Bishop extends Piece {
    public Bishop(PieceColor color, Position position) {
        // Construtor que inicializa com uma cor
        // e uma posicao chamando o construtor da classe base Piece
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        // Valida o movimento do bispo baseando-se nas regras
        int rowDiff = Math.abs(position.getRow() - newPosition.getRow());
        int colDiff = Math.abs(position.getColumn() - newPosition.getColumn());
        // Calcula as diferencas das linhas/colunas inicias e finais

        if (rowDiff != colDiff) {
            // Caso a operacao nao resulte em valores iguais, o movimento é invalido
            // pois o bispo se movimenta diagonalmente
            return false;
        }

        int rowStep = newPosition.getRow() > position.getRow() ? 1 : -1;
        int colStep = newPosition.getColumn() > position.getColumn() ? 1 : -1;
        // Define a direcao do movimento: cima/baixo e esquerda/direita
        int steps = rowDiff - 1;
        // Calcula quantos passos o bispo ira dar ignorando a casa inicial

        for (int i = 1; i <= steps; i++) {
            // Percorre todas as casas entre a posicao inicial e final analisando se existe alguma peca
            // nesse percurso caso existe o movimento nao ocorre
            if (board[position.getRow() + i * rowStep][position.getColumn() + i * colStep] != null) {
                return false;
            }
        }

        Piece destinationPiece = board[newPosition.getRow()][newPosition.getColumn()];
        // Obtem a peca na nova posicao e caso lá já exista alguma peca lá o movimento
        // só é validado caso seja de cor diferente indicando uma captura
        if (destinationPiece == null) {
            return true;
        } else if (destinationPiece.getColor() != this.getColor()) {
            return true;
        }

        return false;
    }
}
