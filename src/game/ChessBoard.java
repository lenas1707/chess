package game;

import pieces.*;
import utils.PieceColor;
import utils.Position;

public class ChessBoard {
    private Piece[][] board;

    public ChessBoard() {
        // Construtor da classe, ao ser instanciado cria o tabuleiro como uma matriz 8x8
        // e chama o metodo setupPieces() para posicionar as peças
        this.board = new Piece[8][8];
        setupPieces();
    }

    public Piece[][] getBoard() {
        // Retorna a matriz do tabuleiro e possibilita o acesso diretos as peças
        return board;
    }

    public Piece getPiece(int row, int column) {
        // Retorna a peça na posição (linha, coluna), caso não tiver peça retorna nulo
        return board[row][column];
    }

    public void setPiece(int row, int column, Piece piece) {
        // Define uma peça especifica no tabuleiro e se ela nao for nula, atualiza sua localizacao no tabuleiro
        board[row][column] = piece;
        if (piece != null) {
            piece.setPosition(new Position(row, column));
        }
    }

    private void setupPieces() {
        // Insere Torres
        board[0][0] = new Rook(PieceColor.BLACK, new Position(0, 0));
        board[0][7] = new Rook(PieceColor.BLACK, new Position(0, 7));
        board[7][0] = new Rook(PieceColor.WHITE, new Position(7, 0));
        board[7][7] = new Rook(PieceColor.WHITE, new Position(7, 7));
        // Insere Cavalos
        board[0][1] = new Knight(PieceColor.BLACK, new Position(0, 1));
        board[0][6] = new Knight(PieceColor.BLACK, new Position(0, 6));
        board[7][1] = new Knight(PieceColor.WHITE, new Position(7, 1));
        board[7][6] = new Knight(PieceColor.WHITE, new Position(7, 6));
        // Insere Bispos
        board[0][2] = new Bishop(PieceColor.BLACK, new Position(0, 2));
        board[0][5] = new Bishop(PieceColor.BLACK, new Position(0, 5));
        board[7][2] = new Bishop(PieceColor.WHITE, new Position(7, 2));
        board[7][5] = new Bishop(PieceColor.WHITE, new Position(7, 5));
        // Insere Rainhas
        board[0][3] = new Queen(PieceColor.BLACK, new Position(0, 3));
        board[7][3] = new Queen(PieceColor.WHITE, new Position(7, 3));
        // Insere Reis
        board[0][4] = new King(PieceColor.BLACK, new Position(0, 4));
        board[7][4] = new King(PieceColor.WHITE, new Position(7, 4));
        // Insere Peões
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(PieceColor.BLACK, new Position(1, i));
            board[6][i] = new Pawn(PieceColor.WHITE, new Position(6, i));
        }
    }

    public void movePiece(Position start, Position end) {
        // Move a peca de uma posicao inical para uma final, verifica se existe uma peca na posicao inicial
        // e se o movimento para a final é valido (isValidMove()) se as duas verificacoes passarem a peca é movida
        // e sua posicao inicial vira nula
        if (board[start.getRow()][start.getColumn()] != null &&
                board[start.getRow()][start.getColumn()].isValidMove(end, board)) {

            board[end.getRow()][end.getColumn()] = board[start.getRow()][start.getColumn()];
            board[end.getRow()][end.getColumn()].setPosition(end);
            board[start.getRow()][start.getColumn()] = null;
        }
    }
}
