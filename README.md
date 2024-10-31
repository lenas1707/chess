**Xadrez**

Um tabuleiro de xadrez pode ser representado por uma matriz quadrada A<sub>8x8</sub> , contendo 16 peças para cada um dos dois jogadores. As peças são distribuidas igualmente e da seguinte forma: 1 rei, 1 rainha, 2 torres, 2 cavalos, 2 bispos e 8 peões. Tradicionalmente, um jogador utiliza as peças de cor branca e outros, as de cor preta. O objetivo é forçar o rei adversãrio a uma posição de xeque-mate, onde ele não possui movimentos legais disponíveis para escapar da ameaça.

<p align="center">
<img src="https://latex.codecogs.com/png.image?\inline&space;\dpi{110}\bg{white}\begin{bmatrix}a_{11}&a_{12}&\cdots&a_{18}\\a_{21}&a_{22}&\cdots&a_{28}\\\vdots&\vdots&\ddots&\vdots\\a_{81}&a_{82}&\cdots&a_{88}\\\end{bmatrix}" title="\begin{bmatrix}a_{11}&a_{12}&\cdots&a_{18}\\a_{21}&a_{22}&\cdots&a_{28}\\\vdots&\vdots&\ddots&\vdots\\a_{81}&a_{82}&\cdots&a_{88}\\\end{bmatrix}" />
</p>

Cada tipo de peça tem um padrão de movimentação distinto:

- **Rei**: pode mover-se em qualquer direção, mas apenas um quadrado de cada vez.
- **Rainha**: pode mover-se em qualquer direção, sem restrições quanto à distância.
- **Torre**: pode mover-se em linha reta, horizontal ou verticalmente, sem restrições de distância.
- **Bispo**: pode mover-se diagonalmente, também sem restrições de distância.
- **Cavalo**: movimenta-se em um padrão em ‘L’, ou seja, dois quadrados(ignorando o quadrado inicial) em uma direção e um quadrado perpendicular, podendo saltar sobre outras peças.
- **Peão**: avança em linha reta, geralmente um quadrado de cada vez (pode avançar dois quadrados no primeiro movimento), captura peças na diagonal e possui movimentos especiais como "en passant" e promoção.

As seguintes condições de jogo são definidas:

- **Xeque**: a situação em que o rei está ameaçado de captura, mas ainda possui movimentos legais para evitar a captura.
- **Xeque-Mate**: a situação em que o rei está ameaçado e não possui movimentos legais que lhe permitam escapar da captura.
- **Impasse**: a situação em que o jogador cuja vez de mover não está em xeque, mas não possui movimentos legais disponíveis.

Posicionando as peças na matriz  $A_{8\times8}$ :
> Usando como base a indexação de Array Bidimensional

### Torre
$t_{00}\quad t_{07}\quad t_{70}\quad t_{77}$

### Rainha
$ra_{03}\quad ra_{73}$

### Rei
$r_{04}\quad r_{74}$

### Cavalo
$c_{01}\quad c_{06}\quad c_{71}\quad c_{76}$

### Bispo
$b_{02}\quad b_{05}\quad b_{72}\quad b_{75}$

### Peão
$p_{10}\quad \cdots \quad p_{17}\\
p_{60}\quad \cdots \quad p_{67}$



## VER 1.0
Proximas atualizações:
- Necessário atualizar package ui e adicionar melhorias para o gráfico
- Implementar conexão com DB e sistema de ranking
- Implementar ChessAI
- Possivelmente adicionar mais regras de xadrez e movimentos como en passant e promoção
- Remover comentários excessivos das classes

  
