package com.pedro.menu;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import com.pedro.models.Autor;
import com.pedro.service.AutorService;
import com.pedro.utils.DataUtils;

public class AutorMenu {
    private Scanner scanner = new Scanner(System.in);
    private AutorService autorService = new AutorService();

    public void inserirAutor(){
        System.out.println("Digite o nome do autor: ");
        String nome = scanner.nextLine();

        System.out.println("Digite a data de nascimento do autor: ");
        Date dataNascimento = DataUtils.stringToSqlDate(scanner.nextLine());

        System.out.println("Digite o pseudonimo do Autor");
        String pseudonimo = scanner.nextLine();

        autorService.inserir(new Autor(nome, dataNascimento, pseudonimo));

    }

    public void listarAutores(){
        List<Autor> autores = autorService.listar();
        System.out.println("Autores");
        System.out.println("ID | Nome | Data Nascimento | Pseudônimo");
        if(!autores.isEmpty()){
            for(Autor autor : autores){
                int max = autor.getNome().length() < 11 ? autor.getNome().length() : 12;
                System.out.println("[" + autor.getId() + "] " + autor.getNome().substring(0, max) + " - " + autor.getDataNascimento() + " - " + autor.getPseudonimo());
            }
        }
    }

    public void editarAutor(){
        try{
            listarAutores();
            System.out.println();

            System.out.println("[!] Escolha o id do Autor: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.println("[!] Digite o nome do autor: ");
            String nome = scanner.nextLine();

            System.out.println("[!] Digite a data de nascimento do autor: ");
            Date dataNascimento = DataUtils.stringToSqlDate(scanner.nextLine());

            System.out.println("[!] Digite o pseudonimo do Autor");
            String pseudonimo = scanner.nextLine();

            autorService.editar(id, new Autor(nome, dataNascimento, pseudonimo));
        
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void excluirAutor(){
        try{
            listarAutores();
            System.out.println();

            System.out.println("[!] Escolha o id do Autor: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            autorService.excluir(id);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
