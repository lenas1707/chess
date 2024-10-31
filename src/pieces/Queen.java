package pieces;

import utils.PieceColor;
import utils.Position;

public class Queen extends Piece {
    public Queen(PieceColor color, Position position) {
        // Construtor que inicializa com uma cor
        // e uma posicao chamando o construtor da classe base Piece
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        // Verifica se o movimento é valido
        if (newPosition.equals(this.position)) {
            // Se a nova posicao é igual a atual o movimento nao é valido
            return false;
        }

        int rowDiff = Math.abs(newPosition.getRow() - this.position.getRow());
        int colDiff = Math.abs(newPosition.getColumn() - this.position.getColumn());
        // Determina se o movimento é em linha reta(pela linha ou pela coluna)
        // ou diagonal
        boolean straightLine = this.position.getRow() == newPosition.getRow()
                || this.position.getColumn() == newPosition.getColumn();

        boolean diagonal = rowDiff == colDiff;

        if (!straightLine && !diagonal) {
            return false;
            // Caso nao seja nenhum dos dois movimento retorna false
        }

        int rowDirection = Integer.compare(newPosition.getRow(), this.position.getRow());
        int colDirection = Integer.compare(newPosition.getColumn(), this.position.getColumn());
        // Determinar a direcao do movimento(cima,baixo, esquerda ou direita) usando Integer.compare()
        // opcao por usar pois ele retorna o modulo entre dois numeros ([-1,1])
        // x < 0: a1 < a2 -> direcao acima(linha menor)
        // x = 0: a1 = a2 -> na mesma linha
        // x > 0: a1 > a2 -> direcao abaixo(linha maior)

        int currentRow = this.position.getRow() + rowDirection;
        int currentCol = this.position.getColumn() + colDirection;
        while (currentRow != newPosition.getRow() || currentCol != newPosition.getColumn()) {
            if (board[currentRow][currentCol] != null) {
                // Verifica se existem pecas bloqueando o caminho
                return false;
            }
            currentRow += rowDirection;
            currentCol += colDirection;
        }

        Piece destinationPiece = board[newPosition.getRow()][newPosition.getColumn()];
        return destinationPiece == null || destinationPiece.getColor() != this.getColor();
        // Verifica se a posicao de destino esta vazia ou se contem alguma peca de cor diferente
        // para executar uma captura
    }
}