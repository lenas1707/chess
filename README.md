# Tabuleiro de Xadrez

Um tabuleiro de xadrez é representado por uma matriz quadrada A<sub>8x8</sub> , contendo 16 peças para cada um dos dois jogadores. As peças são distribuídas da seguinte forma: 1 rei, 1 rainha, 2 torres, 2 cavalos, 2 bispos e 8 peões. Tradicionalmente, um jogador utiliza as peças de cor branca e o outro, as de cor preta. O objetivo do jogo é forçar o rei do adversário a uma posição de xeque-mate, onde ele não possui movimentos legais disponíveis para escapar da ameaça.

Representação do tabuleiro:

$$ 
\begin{bmatrix}
a_{11} & a_{12} & \cdots & a_{18}\\     
a_{21} & a_{22} & \cdots & a_{28}\\     
\vdots & \vdots & \ddots & \vdots\\     
a_{81} & a_{82} & \cdots & a_{88}\\
\end{bmatrix}
$$

## Cada tipo de peça tem um padrão de movimentação distinto:

- **Rei**: pode mover-se em qualquer direção, mas apenas um quadrado de cada vez.
- **Rainha**: pode mover-se em qualquer direção, sem restrições quanto à distância.
- **Torre**: pode mover-se em linha reta, horizontal ou verticalmente, sem restrições de distância.
- **Bispo**: pode mover-se diagonalmente, também sem restrições de distância.
- **Cavalo**: movimenta-se em um padrão em ‘L’, podendo saltar sobre outras peças.
- **Peão**: avança em linha reta, geralmente um quadrado de cada vez (pode avançar dois quadrados no primeiro movimento).

## Condições de jogo:

- **Xeque**: o rei está ameaçado, mas ainda possui movimentos legais.
- **Xeque-Mate**: o rei está ameaçado e não possui movimentos legais que o salvem.
- **Impasse**: o jogador não está em xeque, mas não possui movimentos legais disponíveis.

## Posicionando as peças na matriz \(A<sub>8x8</sub>):

> Usando como base a indexação de Array Bidimensional:

<div align="center">

| Tipo de Peça | Posições                             |
|--------------|-------------------------------------|
| **Torre**    | t<sub>00</sub>, t<sub>07</sub>, t<sub>70</sub>, t<sub>77</sub> |
| **Rainha**   | ra<sub>03</sub>, ra<sub>73</sub>   |
| **Rei**      | r<sub>04</sub>, r<sub>74</sub>     |
| **Cavalo**   | c<sub>01</sub>, c<sub>06</sub>, c<sub>71</sub>, c<sub>76</sub> |
| **Bispo**    | b<sub>02</sub>, b<sub>05</sub>, b<sub>72</sub>, b<sub>75</sub> |
| **Peão**     | p<sub>10</sub> ... p<sub>17</sub><br>p<sub>60</sub> ... p<sub>67</sub> |  

</div>

<br>

> Representação do tabuleiro no estado inicial do jogo

$$ 
\begin{bmatrix}
t_{00} & c_{01} & b_{02} & ra_{03} & r_{04} & b_{05} & c_{06} & t_{07} \\
p_{10} & p_{11} & p_{12} & p_{13} & p_{14} & p_{15} & p_{16} & p_{17} \\
a_1 & a_2 & a_3 & a_4 & a_5 & a_6 & a_7 & c_8 \\
a_1 & a_2 & a_3 & a_4 & a_5 & a_6 & a_7 & a_8 \\
a_1 & a_2 & a_3 & a_4 & a_5 & a_6 & a_7 & a_8 \\
a_1 & a_2 & a_3 & a_4 & a_5 & a_6 & a_7 & a_8 \\
p_{60} & p_{61} & p_{62} & p_{63} & p_{64} & p_{65} & p_{66} & p_{67} \\
t_{70} & c_{71} & b_{72} & ra_{73} & r_{74} & b_{75} & c_{76} & t_{77} \\
\end{bmatrix}
$$
