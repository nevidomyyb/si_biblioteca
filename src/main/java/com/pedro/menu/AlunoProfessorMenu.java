package com.pedro.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pedro.config.IO;
import com.pedro.models.Autor;
import com.pedro.models.Editora;
import com.pedro.models.Exemplar;
import com.pedro.models.Genero;
import com.pedro.service.AutorService;
import com.pedro.service.EditoraService;
import com.pedro.service.ExemplarService;
import com.pedro.service.GeneroService;
import com.pedro.utils.ColunaUtils;

public class AlunoProfessorMenu {
    private IO io;
    LivroMenu livroMenu;
    ExemplarMenu exemplarMenu;
    ExemplarService exemplarService;

    public AlunoProfessorMenu(){
        io = new IO();
        livroMenu = new LivroMenu();
        exemplarMenu = new ExemplarMenu();
        exemplarService = new ExemplarService();
    }


    public void imprimirMenuAlunoProfessor(){
        List<String> opcoesAlunoProfessor = new ArrayList<String>(Arrays.asList("1. Área Exemplares", "2. Consultar Editoras", "3. Consultar Autor", "4. Consultar Gênero", "5. Voltar"));
        int opc = io.imprimirMenuRetornandoOpcao(opcoesAlunoProfessor, "ÁREA PRINCIPAL");

        while (opc != 5) {
            switch (opc) {
                case 1:
                    List<Exemplar> exemplares = exemplarService.listarExemplares();
                    System.out.println("----------------------------------EXEMPLAR-----------------------------------");
                    System.out.println(
                        "| " + ColunaUtils.formatarColuna("Título", 12) + " | " + ColunaUtils.formatarColuna("Edição", 12) +
                        " | " + ColunaUtils.formatarColuna("Sinopse", 25) + " |"
                    );

                    System.out.println("-".repeat(77));
                    if(!exemplares.isEmpty()){
                        for (Exemplar exemplar : exemplares){
                            System.out.println(
                                "| " + livroMenu.imprimirLivroParaLeitores(exemplar.getLivroId()) + " |"
                            );
                        }
                        System.out.println("-".repeat(77));
                    }

                case 2:
                    EditoraService editoraService = new EditoraService();
                    List<Editora> editoras = editoraService.listar();
                    System.out.println("Editoras");
                    System.out.println("    Nome");
                    if(!editoras.isEmpty()){
                        int contador = 1;
                        for(Editora editora : editoras){
                            int max = editora.getNome().length() < 11 ? editora.getNome().length() : 12;
                            System.out.println("[" + contador + "] " + editora.getNome().substring(0, max));
                            contador++;
                        }
                    }
                    break;

                case 3:
                    AutorService autorService = new AutorService();
                    List<Autor> autores = autorService.listar();
                    System.out.println("Autores");
                    System.out.println("     Nome | Data Nascimento | Pseudônimo");
                    if(!autores.isEmpty()){
                        int contador = 1;
                        for(Autor autor : autores){
                            int max = autor.getNome().length() < 11 ? autor.getNome().length() : 12;
                            System.out.println("[" + contador + "] " + autor.getNome().substring(0, max) + " | " + autor.getDataNascimento() + " | " + autor.getPseudonimo() );
                            contador++;
                        }
                    }
                    break;

                case 4:
                    GeneroService generoService = new GeneroService();
                    List<Genero> generos = generoService.listar();
                    System.out.println("Gêneros");
                    System.out.println("    DESCRIÇÃO");
                    if(!generos.isEmpty()){
                        int contador = 1;
                        for(Genero genero : generos){
                            int max = genero.getGenero().length() < 11 ? genero.getGenero().length() : 12;
                            System.out.println("["+ contador + "] " + genero.getGenero().substring(0, max));
                            contador++;
                        }
                    }
                    break;

                default:
                    System.out.println("[!] Opção Inválida.");
            }
            
            opc = io.imprimirMenuRetornandoOpcao(opcoesAlunoProfessor, "ÁREA PRINCIPAL");
        }

    }

}
