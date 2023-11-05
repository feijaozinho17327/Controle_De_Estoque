package com.example;

import java.util.HashMap;
import java.util.Map;

public class Catalogo {

    Map<Integer, Produto> produtoMap;

    public Catalogo() {

        this.produtoMap = new HashMap<>();
    }

    public void adicionarProdutoCatalogo(Integer codigo, String nome, Double preco) {
        Produto produto = new Produto(nome, preco);

        produtoMap.put(codigo, produto);
    }

    public String removerProdutoCatalogo(Integer codigo) {
        if (produtoMap.containsKey(codigo)) {
            produtoMap.remove(codigo);
            return "Produto removido com sucesso";
        } else {
            return "Código inválido";
        }

    }

    public String mudarPrecoProduto(Integer codigo, Double novoPreco) {
        if (produtoMap.containsKey(codigo)) {
            Produto produto = produtoMap.get(codigo);
            produto.setPreco(novoPreco);

            return "Preço modificado com sucesso";
        } else {
            return "Código inválido";
        }
    }

    public void exibirProdutos() {
        for (Map.Entry<Integer, Produto> entry : produtoMap.entrySet()) {
            Integer codigo = entry.getKey();
            Produto produto = entry.getValue();
            System.out.println("Código: " + codigo + ", Nome: " + produto.getNome() + ", Preço: " + produto.getPreco());
        }
    }

}
