package utils;

public class Position {
    private int row;
    private int column;

    public Position(int row, int column) {
        // Construtor que inicializa linha e coluna com valores fornecidos
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        // Retorna o valor de linha
        return row;
    }

    public int getColumn() {
        // Retorna o valor de coluna
        return column;
    }
}