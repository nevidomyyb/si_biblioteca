package com.pedro.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.text.AttributeSet.ColorAttribute;

import com.mysql.cj.protocol.a.ColumnDefinitionFactory;
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
    ExemplarService exemplarService;
    EditoraService editoraService;
    AutorService autorService;
    GeneroService generoService;

    public AlunoProfessorMenu(){
        io = new IO();
        livroMenu = new LivroMenu();
        exemplarService = new ExemplarService();
        editoraService = new EditoraService();
        autorService = new AutorService();
        generoService = new GeneroService();
    }


    public void imprimirMenuAlunoProfessor(){
        List<String> opcoesAlunoProfessor = new ArrayList<String>(Arrays.asList("1. Consultar Exemplares", "2. Consultar Editoras", "3. Consultar Autores", "4. Consultar Gêneros", "5. Voltar"));
        int opc = io.imprimirMenuRetornandoOpcao(opcoesAlunoProfessor, "ÁREA PRINCIPAL");

        while (opc != 5) {
            switch (opc) {
                case 1:
                    List<Exemplar> exemplares = exemplarService.listarExemplares();
                    System.out.println("-----------------------------------------EXEMPLAR-------------------------------------------------");
                    System.out.println(
                        "| " + ColunaUtils.formatarColuna("Título", 30) + " | " + ColunaUtils.formatarColuna("Edição", 12) +
                        " | " + ColunaUtils.formatarColuna("Sinopse", 45) + " |"
                    );

                    System.out.println("-".repeat(98));
                    if(!exemplares.isEmpty()){
                        for (Exemplar exemplar : exemplares){
                            System.out.println(
                                "| " + livroMenu.imprimirLivroParaLeitores(exemplar.getLivroId()) + " |"
                            );
                        }
                        System.out.println("-".repeat(98));
                    }
                    break;

                case 2:
                    List<Editora> editoras = editoraService.listar();
                    System.out.println("-----EDITORAS----");
                    System.out.println(
                        "| " + ColunaUtils.formatarColuna("Nome", 12) + " |"
                    );
                    System.out.println("-".repeat(17));
                    for(Editora editora : editoras){
                        System.out.println(
                            "| " + ColunaUtils.formatarColuna(editora.getNome(), 12) + " |"
                        );
                    }
                    System.out.println("-".repeat(17));
                    break;

                case 3:
                    List<Autor> autores = autorService.listar();
                    System.out.println("------------------------AUTORES------------------------");
                    System.out.println(
                        "| " + ColunaUtils.formatarColuna("Nome", 12) + " | " +
                        ColunaUtils.formatarColuna("Pseudônimo", 12) +  " | " +
                        ColunaUtils.formatarColuna("Data de Nascimento", 20) + " |"
                    );
                    System.out.println("-".repeat(55));
                    for(Autor autor : autores){
                        System.out.println(
                            "| " + ColunaUtils.formatarColuna(autor.getNome(), 12) + " | " + 
                            ColunaUtils.formatarColuna(autor.getPseudonimo(), 12) + " | " +
                            ColunaUtils.formatarColuna(
                                autor.getDataNascimento() == null ? null : autor.getDataNascimento().toString(), 20) + " |"
                        );
                    }
                    System.out.println("-".repeat(55));
                    break;

                case 4:
                    List<Genero> generos = generoService.listar();
                    System.out.println("------GÊNERO-----");
                    System.out.println(
                        "| " + ColunaUtils.formatarColuna("Gênero", 12) + " |"
                    );
                    System.out.println("-".repeat(17));
                    for(Genero genero : generos){
                        System.out.println(
                            "| " + ColunaUtils.formatarColuna(genero.getGenero(), 12) + " |"
                        );
                    }   
                    System.out.println("-".repeat(17));                 
                    break;

                default:
                    System.out.println("[!] Opção Inválida.");
            }
            
            opc = io.imprimirMenuRetornandoOpcao(opcoesAlunoProfessor, "ÁREA PRINCIPAL");
        }

    }


    public static void main(String[] args) {
        new AlunoProfessorMenu().imprimirMenuAlunoProfessor();
    }
}
