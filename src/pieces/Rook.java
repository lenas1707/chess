package pieces;

import utils.PieceColor;
import utils.Position;

public class Rook extends Piece {
    public Rook(PieceColor color, Position position) {
        // Construtor que inicializa com uma cor
        // e uma posicao chamando o construtor da classe base Piece
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        // Verifica se a torre esta se movendo na horizontal ou vertical
        if (position.getRow() == newPosition.getRow()) {
            int columnStart = Math.min(position.getColumn(), newPosition.getColumn()) + 1;
            int columnEnd = Math.max(position.getColumn(), newPosition.getColumn());
            for (int column = columnStart; column < columnEnd; column++) {
                if (board[position.getRow()][column] != null) {
                    return false;
                }
                // Movimento Horizontal, se a posicao final for a mesma linha apenas calcula a variacao
                // da coluna, itera pelas colunas entre as posicoes e verfifica se existem pecas no caminho
            }
        } else if (position.getColumn() == newPosition.getColumn()) {
            int rowStart = Math.min(position.getRow(), newPosition.getRow()) + 1;
            int rowEnd = Math.max(position.getRow(), newPosition.getRow());
            for (int row = rowStart; row < rowEnd; row++) {
                if (board[row][position.getColumn()] != null) {
                    return false;
                }
            }
        } else {
            return false;
        }
        // Movimento vertical, se a posicao final for na mesma coluna apenas calcula a variacao
        // da linha, itera pelas linhas entre as posicoes e verfifica se existem pecas no caminho

        Piece destinationPiece = board[newPosition.getRow()][newPosition.getColumn()];
        if (destinationPiece == null) {
            return true;
        } else if (destinationPiece.getColor() != this.getColor()) {
            return true;
        }

        return false;
        // Verifica se a posicao de destino esta vazia ou se contem alguma peca de cor diferente
        // para executar uma captura
    }

}