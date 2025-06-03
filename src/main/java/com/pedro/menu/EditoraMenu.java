package com.pedro.menu;

import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import com.pedro.models.Editora;
import com.pedro.models.Endereco;
import com.pedro.service.EditoraService;

public class EditoraMenu {

    private Scanner scanner = new Scanner(System.in);
    private EditoraService editoraService = new EditoraService();

    public void cadastrarEditora() {
        System.out.println("Digite o nome da Editora: ");
        String nomeEditora = scanner.nextLine();

        System.out.println("Digite o CNPJ da Editora: ");
        String cnpjEditora = scanner.nextLine();

        System.out.println("Deseja inserir o endereço da editora (S/N)? ");
        String resposta = scanner.nextLine().toLowerCase().trim();

        Endereco endereco = null;
        if (resposta.equals("s")) {
            System.out.println("RUA: ");
            String rua = scanner.nextLine();

            System.out.println("BAIRRO: ");
            String bairro = scanner.nextLine();

            System.out.println("NÚMERO: ");
            int numero = scanner.nextInt();

            System.out.println("CEP: ");
            String cep = scanner.nextLine();

            System.out.println("CIDADE: ");
            String cidade = scanner.nextLine();

            System.out.println("ESTADO: ");
            String estado = scanner.nextLine();

            endereco = new Endereco(rua, bairro, numero, cep, cidade, estado);
        }

        editoraService.inserir(new Editora(nomeEditora, cnpjEditora));
        return;
    }

    public void listarEditora() {
        List<Editora> editoras = editoraService.listar();
        System.out.println("Editoras");
        System.out.println("ID | Nome | CNPJ ");
        if (!editoras.isEmpty()) {
            for(Editora editora : editoras){
                int max = editora.getNome().length() < 11 ? editora.getNome().length() : 12;
                System.out.println("[" + editora.getId() + "] " + editora.getNome().substring(0, max) + " - " + editora.getCnpj());
            }
        }
    }

    public void editarEditora(){
        try{
            listarEditora();

            System.out.println();

            System.out.println("Escolha o id da editora: ");
            int id_editora = scanner.nextInt();
            scanner.nextLine();
            
            System.out.println("Digite o nome da Editora: ");
            String nomeEditora = scanner.nextLine();

            System.out.println("Digite o CNPJ da Editora: ");
            String cnpjEditora = scanner.nextLine();

            System.out.println("Deseja inserir o endereço da editora (S/N)? ");
            String resposta = scanner.nextLine().toLowerCase().trim();
        
        
            Endereco endereco = null;
            if(resposta.equals("s")){
                System.out.println("RUA: ");
                String rua = scanner.nextLine();

                System.out.println("BAIRRO: ");
                String bairro = scanner.nextLine();

                System.out.println("NÚMERO: ");
                int numero = scanner.nextInt();

                System.out.println("CEP: ");
                String cep = scanner.nextLine();

                System.out.println("CIDADE: ");
                String cidade = scanner.nextLine();

                System.out.println("ESTADO: ");
                String estado = scanner.nextLine();

                endereco = new Endereco(rua, bairro, numero, cep, cidade, estado);
            }

            editoraService.editar(id_editora, new Editora(nomeEditora, cnpjEditora));

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void excluirEditora(){
        listarEditora();

        System.out.println("Escolha o id da editora: ");
        int id_editora = scanner.nextInt();
        scanner.nextLine();
        
        editoraService.excluir(id_editora);
    }

}
