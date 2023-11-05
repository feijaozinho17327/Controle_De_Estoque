package com.example;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class ControleCatalogo {

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Catalogo caixa = new Catalogo();
        try (Scanner scan = new Scanner(System.in)) {
            // Ler o arquivo JSON se existir
            File arquivoJSON = new File("catalogo.json");
            if (arquivoJSON.exists()) {
                try (Reader reader = new FileReader(arquivoJSON)) {
                    Map<Integer, Produto> catalogo = gson.fromJson(reader, new TypeToken<Map<Integer, Produto>>() {
                    }.getType());
                    caixa.produtoMap = catalogo;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            
            int aux = 0;

            do {
                Integer codigo = null;
                String nome = null;
                Double preco = null;

                System.out.println("Digite: \n1 para adicionar produto. \n2 para excluir produto. "
                        + "\n3 para editar preço \n4 para exibir produtos \n5 para sair");
                aux = scan.nextInt();

                try {
                    switch (aux) {
                        case 1:
                            System.out.println("Digite o código");
                            codigo = Integer.parseInt(scan.next());
                            System.out.println("Digite o nome");
                            nome = scan.next();
                            System.out.println("Digite o preço");
                            preco = Double.parseDouble(scan.next());
                            caixa.adicionarProdutoCatalogo(codigo, nome, preco);
                            break;

                        case 2:
                            System.out.println("Digite o código");
                            codigo = Integer.parseInt(scan.next());
                            caixa.removerProdutoCatalogo(codigo);
                            break;

                        case 3:
                            System.out.println("Digite o código");
                            codigo = Integer.parseInt(scan.next());
                            System.out.println("Digite o preço");
                            preco = Double.parseDouble(scan.next());
                            caixa.mudarPrecoProduto(codigo, preco);
                            break;

                        case 4:
                            caixa.exibirProdutos();
                            break;

                        default:
                            
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(
                            "Entrada inválida. Certifique-se de que o código seja um número inteiro e o preço seja um número válido.");
                }
            } while (aux != 5);

            // Salvar o catálogo no arquivo JSON
            try (Writer writer = new FileWriter(arquivoJSON)) {
                gson.toJson(caixa.produtoMap, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JsonIOException | JsonSyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}