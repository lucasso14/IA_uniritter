import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordHunt {
    private static List<String> palavrasValidas;

    public static void main(String[] args) {
        //Pegando a lista de palavras válidas do dicionário
        palavrasValidas = carregarPalavrasValidas("dicionario.txt");

        char[][] letras = {
            {'E', 'F', 'A', 'T'},
            {'R', 'I', 'O', 'P'},
            {'J', 'U', 'R', 'E'},
            {'R', 'I', 'P', 'A'}
        };

        String[] palavras = buscaPalavras(letras);

        System.out.println("Palavras encontradas:");
        for (String palavra : palavras) {
            System.out.println(palavra);
        }
    }

    private static List<String> carregarPalavrasValidas(String arquivo) {
        List<String> palavrasValidas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                palavrasValidas.add(linha.trim().toUpperCase());
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar o dicionário: " + e.getMessage());
        }
        return palavrasValidas;
    }

    public static String[] buscaPalavras(char[][] letras) {
        List<String> palavrasEncontradas = new ArrayList<>();

        //Buscando palavras horizontais
        for (int i = 0; i < letras.length; i++) {
            for (int j = 0; j < letras[i].length; j++) {
                for (int k = j; k < letras[i].length; k++) {
                    String palavra = "";
                    for (int l = j; l <= k; l++) {
                        palavra += letras[i][l];
                    }
                    if (palavrasValidas.contains(palavra.toUpperCase()) && palavra.length() > 1 && !palavrasEncontradas.contains(palavra)) {
                        palavrasEncontradas.add(palavra);
                    }
                }
            }
        }

        //Buscando palavras verticais
        for (int j = 0; j < letras[0].length; j++) {
            for (int i = 0; i < letras.length; i++) {
                for (int k = i; k < letras.length; k++) {
                    String palavra = "";
                    for (int l = i; l <= k; l++) {
                        palavra += letras[l][j];
                    }
                    if (palavrasValidas.contains(palavra.toUpperCase()) && palavra.length() > 1 && !palavrasEncontradas.contains(palavra)) {
                        palavrasEncontradas.add(palavra);
                    }
                }
            }
        }

        // Buscando palavras diagonais
        for (int i = 0; i < letras.length; i++) {
            for (int j = 0; j < letras[i].length; j++) {
                int k = i;
                int l = j;
                while (k < letras.length && l < letras[k].length) {
                    String palavra = "";
                    for (int m = 0; m <= k - i; m++) {
                        palavra += letras[i + m][j + m];
                    }
                    if (palavrasValidas.contains(palavra.toUpperCase()) && palavra.length() > 1 && !palavrasEncontradas.contains(palavra)) {
                        palavrasEncontradas.add(palavra);
                    }
                    k++;
                    l++;
                }

                k = i;
                l = j;
                while (k < letras.length && l >= 0) {
                    String palavra = "";
                    for (int m = 0; m <= k - i; m++) {
                        palavra += letras[i + m][j - m];
                    }
                    if (palavrasValidas.contains(palavra.toUpperCase()) && palavra.length() > 1 && !palavrasEncontradas.contains(palavra)) {
                        palavrasEncontradas.add(palavra);
                    }
                    k++;
                    l--;
                }
            }
        }

        return palavrasEncontradas.toArray(new String[0]);
    }
}