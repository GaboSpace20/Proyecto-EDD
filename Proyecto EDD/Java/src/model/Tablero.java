package model;

public class Tablero {
    public LetraNodo[][] nodos;

    public Tablero(char[][] letras) {
        nodos = new LetraNodo[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                nodos[i][j] = new LetraNodo(letras[i][j], i, j);
            }
        }
        conectarAdyacentes();
    }

    private void conectarAdyacentes() {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int d = 0; d < 8; d++) {
                    int ni = i + dx[d];
                    int nj = j + dy[d];
                    if (ni >= 0 && nj >= 0 && ni < 4 && nj < 4) {
                        nodos[i][j].agregarAdyacente(nodos[ni][nj]);
                    }
                }
            }
        }
    }
}