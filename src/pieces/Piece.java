package pieces;

import utils.PieceColor;
import utils.Position;

public abstract class Piece {
    // Abstract por ser apenas uma estrutura comum a todas as pecas especificas,
    // nao faz sentido existir uma peca generica, previne instanciacao e
    // protected foi optada por permitir um melhor acesso aos atributos
    // e metodos pelas subclasses
    protected Position position;
    protected PieceColor color;

    public Piece(PieceColor color, Position position) {
        // Construtor base para as pecas
        this.color = color;
        this.position = position;
    }

    public PieceColor getColor() {
        // Permitir que outras classes verifiquem a cor da peca
        return color;
    }

    public Position getPosition() {
        // Retorna a posicao atual de uma peca no tabuleiro
        return position;
    }

    public void setPosition(Position position) {
        // Permite que a posicoa da peca seja atualizada, usado quando a peca
        // se movimenta
        this.position = position;
    }

    public abstract boolean isValidMove(Position newPosition, Piece[][] board);
    // Metodo abstrato para ser implementado em cada classe que estende Piece
    // e disponibilizar sua propria logica considerando o tabuleiro e as posicoes
    // forcando as subclasses a isso
}