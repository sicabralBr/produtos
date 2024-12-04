package db;

import models.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDB {
    private List<Produto> produtosList = new ArrayList<>();

    public List<Produto> getProdutosList() {
        return produtosList;
    }

    public void adicionaNovoProduto(Produto produto){
        produtosList.add(produto);
    }
}
