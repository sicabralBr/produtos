import db.ProdutosDB;
import models.Produto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    static ProdutosDB produtosDB = new ProdutosDB();

    public static void main(String[] args) throws Exception {

        System.out.println("---- PEDIDO DE VENDAS  ----");

        int id = 0;
        String descricao = "";
        double preco = 0.00;
        Date dataValidade = new Date();
        int opcao = 1 ;

        //Adicionar ou listar produtos
        do{
            switch (opcao){
                case 1:
                    adicionarProduto(descricao, id, preco, dataValidade);
                    opcao = menuOpcao();
                    break;
                case 2:
                    listarProdutos();
                    opcao = menuOpcao();
                    break;
                default :
                    break;
            }

        }while (opcao != 0);
    }

    public static void adicionarProduto (String descricao, int id, double preco, Date dataValidade) throws Exception {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Qual a descrição do novo produto: ");
        descricao = scanner.nextLine();

        System.out.print("Qual o ID do novo produto: ");
        id = scanner.nextInt();

        System.out.print("Qual o preço do novo produto: ");
        preco = scanner.nextDouble();

        System.out.print("Qual a data de validade do novo produto: ");
        String dataString = scanner.next();

        dataValidade = new SimpleDateFormat("dd/MM/yyyy").parse(dataString);

        Produto novoProduto = new Produto(id, descricao, preco, dataValidade);

        produtosDB.adicionaNovoProduto(novoProduto);

        if (novoProduto.getId() < 1 || novoProduto.getDescricao() == null) {
            System.out.println("Dados inválidos!!!!");
            System.out.println("Digite novamente.");

        } else {
            System.out.println("Produto criado com sucesso: ");
        }
    }

    public static int menuOpcao () throws Exception {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Menu de opções: ");
        System.out.println("0 - Sair");
        System.out.println("1 - Continuar");
        System.out.println("2 - Listar produtos cadastrados");
        System.out.println("Escolha uma das opções: ");
        int opcaoEscolhida = scanner.nextInt();
        return opcaoEscolhida;
    }

    public static void listarProdutos(){
        List<Produto> listaProdutos = produtosDB.getProdutosList();
        for(Produto produto: listaProdutos){
            System.out.println("---- ID: " + produto.getId());
            System.out.println("---- Descrição: " + produto.getDescricao());
            System.out.println("---- Preço: " + String.format("%1$,.2f", produto.getPreco()));
            System.out.println("---- Validade: " + produto.getDataValidade());
        }
    }
}