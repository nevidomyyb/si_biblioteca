package com.pedro.menu;

import java.util.List;
import java.util.Scanner;

import com.pedro.models.Genero;
import com.pedro.service.GeneroService;

public class GeneroMenu {
    
    private Scanner scanner = new Scanner(System.in);
    private GeneroService generoService = new GeneroService();

    public void inserirGenero(){
        System.out.println("[!] Digite o gênero: ");
        String genero = scanner.nextLine();

        generoService.inserir(new Genero(genero));
    }

    public void listarGeneros(){
        List<Genero> generos = generoService.listar();
        System.out.println("Gêneros");
        System.out.println("ID | DESCRIÇÃO ");
        if(!generos.isEmpty()){
            for(Genero genero : generos){
                int max = genero.getGenero().length() < 11 ? genero.getGenero().length() : 12;
                System.out.println("[" + genero.getId() + "]" + genero.getGenero().substring(0, max));
            }
        }
    }

    public void editarGenero(){
        try{
            listarGeneros();
            System.out.println();

            System.out.println("[!] Escolha o id do Gênero: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.println("[!] Digite o gênero: ");
            String genero = scanner.nextLine();

            generoService.editar(id, new Genero(genero));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void excluirGenero(){
        try{

            listarGeneros();
            System.out.println();

            System.out.println("[!] Escolha o id do Gênero: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            generoService.excluir(id);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
