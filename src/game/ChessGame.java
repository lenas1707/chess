package game;

import pieces.King;
import pieces.Piece;
import utils.PieceColor;
import utils.Position;

import java.util.List;
import java.util.ArrayList;

public class ChessGame {
    private ChessBoard board;
    private boolean whiteTurn = true; // Pela regra do xadrez sempre as peças brancas devem iniciar
    private Position selectedPosition;


    public ChessGame() {
        // Construtor que inicializa um novo tabuleiro de xadrez
        this.board = new ChessBoard();
    }

    public ChessBoard getBoard() {
        // Retorna o tabuleiro atual e permite acesso
        return this.board;
    }

    public void resetGame() {
        // Reinicia o jogo e define as pecas brancas como as primeira a jogar
        this.board = new ChessBoard();
        this.whiteTurn = true;
    }

    public PieceColor getCurrentPlayerColor() {
        // Retorna a cor da peca do jogador atual
        return whiteTurn ? PieceColor.WHITE : PieceColor.BLACK;
    }

    public boolean isPieceSelected() {
        // Apenas verifica se a peca foi selecionada e retorna true caso tenha sido selecionada (nao for null)
        return selectedPosition != null;
    }

    public boolean handleSquareSelection(int row, int col) {
        // Gerencia a selecao de uma casa do tabuleiro, verifica se a peca selecionada em determinada casa
        // pertence ao jogador do turno, caso a peca selecionada tenha sido selecionada ele tenta fazer o movimento
        // para a nova posicao e retorna true se o movimento foi completado
        if (selectedPosition == null) {
            Piece selectedPiece = board.getPiece(row, col);
            if (selectedPiece != null
                    && selectedPiece.getColor() == (whiteTurn ? PieceColor.WHITE : PieceColor.BLACK)) {
                selectedPosition = new Position(row, col);
                return false;
            }
        } else {
            boolean moveMade = makeMove(selectedPosition, new Position(row, col));
            selectedPosition = null;
            return moveMade;
        }
        return false;
    }




    public boolean makeMove(Position start, Position end) {
        // Tenta movimentar uma peca de um inicio para um fim, verifica se a peca pertence ao jogador do turno
        // e analisa se o movimento é valido, se for valido realiza e altera o turno de jogada
        Piece movingPiece = board.getPiece(start.getRow(), start.getColumn());
        if (movingPiece == null || movingPiece.getColor() != (whiteTurn ? PieceColor.WHITE : PieceColor.BLACK)) {
            return false;
        }

        if (movingPiece.isValidMove(end, board.getBoard())) {
            board.movePiece(start, end);
            whiteTurn = !whiteTurn;
            return true;
        }
        return false;
    }

    public boolean isInCheck(PieceColor kingColor) {
        // Verifica se o rei de um dado jogador esta em xeque, apenas pegando a posicao do rei em questao
        // e observando se alguma peca do outro jogador pode se movimentar ate essa mesma posicao
        Position kingPosition = findKingPosition(kingColor);
        for (int row = 0; row < board.getBoard().length; row++) {
            for (int col = 0; col < board.getBoard()[row].length; col++) {
                Piece piece = board.getPiece(row, col);
                if (piece != null && piece.getColor() != kingColor) {
                    if (piece.isValidMove(kingPosition, board.getBoard())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Position findKingPosition(PieceColor color) {
        // Localiza o rei de um dado jogador, caso nao encontre (por algum motivo) retorna uma
        // excecao
        for (int row = 0; row < board.getBoard().length; row++) {
            for (int col = 0; col < board.getBoard()[row].length; col++) {
                Piece piece = board.getPiece(row, col);
                if (piece instanceof King && piece.getColor() == color) {
                    return new Position(row, col);
                }
            }
        }
        resetGame();
        throw new RuntimeException("Rei sumiu ????????.");
        //throw new RuntimeException("King not found ?????????");
    }

    public boolean isCheckMate(PieceColor kingColor) {
        // Verifica se o rei de um dado jogador esta em xeque-mate, analisa primeiro se ele esta em xeque
        // e depois analisa as movimentacoes possiveis e que nao deixariam ele em xeque novamente
        if (!isInCheck(kingColor)) {
            return false;
        }

        Position kingPosition = findKingPosition(kingColor);
        King king = (King) board.getPiece(kingPosition.getRow(), kingPosition.getColumn());

        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int colOffset = -1; colOffset <= 1; colOffset++) {
                if (rowOffset == 0 && colOffset == 0) {
                    continue;
                }
                Position newPosition = new Position(kingPosition.getRow() + rowOffset,
                        kingPosition.getColumn() + colOffset);

                if (isPositionOnBoard(newPosition) && king.isValidMove(newPosition, board.getBoard())
                        && !wouldBeInCheckAfterMove(kingColor, kingPosition, newPosition)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isPositionOnBoard(Position position) {
        // Apenas certifica que a posicao esta dentro dos limites do tabuleiro
        return position.getRow() >= 0 && position.getRow() < board.getBoard().length &&
                position.getColumn() >= 0 && position.getColumn() < board.getBoard()[0].length;
    }

    private boolean wouldBeInCheckAfterMove(PieceColor kingColor, Position from, Position to) {
        // Faz uma simulacao se ao terminar um movimento from-to o rei de um dado jogador
        // ficara em xeque
        Piece temp = board.getPiece(to.getRow(), to.getColumn());
        board.setPiece(to.getRow(), to.getColumn(), board.getPiece(from.getRow(), from.getColumn()));
        board.setPiece(from.getRow(), from.getColumn(), null);

        boolean inCheck = isInCheck(kingColor);

        board.setPiece(from.getRow(), from.getColumn(), board.getPiece(to.getRow(), to.getColumn()));
        board.setPiece(to.getRow(), to.getColumn(), temp);

        return inCheck;
    }

    public List<Position> getLegalMovesForPieceAt(Position position) {
        // Retorna a lista de movimento legais para cada peca e chama um metodo para implementar
        // seus movimentos legais
        Piece selectedPiece = board.getPiece(position.getRow(), position.getColumn());
        if (selectedPiece == null)
            return new ArrayList<>();

        List<Position> legalMoves = new ArrayList<>();
        switch (selectedPiece.getClass().getSimpleName()) {
            case "Pawn":
                addPawnMoves(position, selectedPiece.getColor(), legalMoves);
                break;
            case "Rook":
                addLineMoves(position, new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } }, legalMoves);
                break;
            case "Knight":
                addSingleMoves(position, new int[][] { { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 }, { 1, 2 }, { -1, 2 },
                        { 1, -2 }, { -1, -2 } }, legalMoves);
                break;
            case "Bishop":
                addLineMoves(position, new int[][] { { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 } }, legalMoves);
                break;
            case "Queen":
                addLineMoves(position, new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, -1 },
                        { 1, -1 }, { -1, 1 } }, legalMoves);
                break;
            case "King":
                addSingleMoves(position, new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, -1 },
                        { 1, -1 }, { -1, 1 } }, legalMoves);
                break;
        }
        return legalMoves;
    }

    private void addLineMoves(Position position, int[][] directions, List<Position> legalMoves) {
        // Adiciona os movimentos em linha (Rainha, Torre ou Bispo) e continua adicionando posicao ate a peca
        // ser encontrada ou chegar no limite do tabuleiro
        for (int[] d : directions) {
            Position newPos = new Position(position.getRow() + d[0], position.getColumn() + d[1]);
            while (isPositionOnBoard(newPos)) {
                if (board.getPiece(newPos.getRow(), newPos.getColumn()) == null) {
                    legalMoves.add(new Position(newPos.getRow(), newPos.getColumn()));
                    newPos = new Position(newPos.getRow() + d[0], newPos.getColumn() + d[1]);
                } else {
                    if (board.getPiece(newPos.getRow(), newPos.getColumn()).getColor() != board
                            .getPiece(position.getRow(), position.getColumn()).getColor()) {
                        legalMoves.add(newPos);
                    }
                    break;
                }
            }
        }
    }

    private void addSingleMoves(Position position, int[][] moves, List<Position> legalMoves) {
        // Adiciona movimentos unicos(Rei, Cavalo) verificando se as posicao tao dentro do tabuleiro
        // e se podem ser ocupadas
        for (int[] move : moves) {
            Position newPos = new Position(position.getRow() + move[0], position.getColumn() + move[1]);
            if (isPositionOnBoard(newPos) && (board.getPiece(newPos.getRow(), newPos.getColumn()) == null ||
                    board.getPiece(newPos.getRow(), newPos.getColumn()).getColor() != board
                            .getPiece(position.getRow(), position.getColumn()).getColor())) {
                legalMoves.add(newPos);
            }
        }
    }

    private void addPawnMoves(Position position, PieceColor color, List<Position> legalMoves) {
        // Adiciona a movimentacao do Peao, verifica se o peao pode se mover para frente ou capturar na diagonal
        // e permitir avanco duplo caso esteja em sua posicao inicial
        int direction = color == PieceColor.WHITE ? -1 : 1;
        Position newPos = new Position(position.getRow() + direction, position.getColumn());
        if (isPositionOnBoard(newPos) && board.getPiece(newPos.getRow(), newPos.getColumn()) == null) {
            legalMoves.add(newPos);
        }

        if ((color == PieceColor.WHITE && position.getRow() == 6)
                || (color == PieceColor.BLACK && position.getRow() == 1)) {
            newPos = new Position(position.getRow() + 2 * direction, position.getColumn());
            Position intermediatePos = new Position(position.getRow() + direction, position.getColumn());
            if (isPositionOnBoard(newPos) && board.getPiece(newPos.getRow(), newPos.getColumn()) == null
                    && board.getPiece(intermediatePos.getRow(), intermediatePos.getColumn()) == null) {
                legalMoves.add(newPos);
            }
        }

        int[] captureCols = { position.getColumn() - 1, position.getColumn() + 1 };
        for (int col : captureCols) {
            newPos = new Position(position.getRow() + direction, col);
            if (isPositionOnBoard(newPos) && board.getPiece(newPos.getRow(), newPos.getColumn()) != null &&
                    board.getPiece(newPos.getRow(), newPos.getColumn()).getColor() != color) {
                legalMoves.add(newPos);
            }
        }
    }

}