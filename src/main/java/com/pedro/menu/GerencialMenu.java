package com.pedro.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.pedro.config.IO;
import com.pedro.models.Aluno;
import com.pedro.service.AlunoService;
import com.pedro.service.LivroService;
import com.pedro.service.ProfessorService;


public class GerencialMenu {
    private IO io;
    private EditoraMenu editoraMenu;
    private AutorMenu autorMenu;
    private GeneroMenu generoMenu;
    private EnderecoMenu enderecoMenu;
    private AlunoService alunoService = new AlunoService();
    private LivroService livroService = new LivroService();
    private ProfessorService professorService = new ProfessorService();

    public GerencialMenu(){
        io = new IO();
        editoraMenu = new EditoraMenu();
        autorMenu = new AutorMenu();
        generoMenu = new GeneroMenu();
        enderecoMenu = new EnderecoMenu();
    }

    public void imprimirMenuGerencial(){
        List<String> opcoesGerencial = new ArrayList<String>(
                Arrays.asList(
                        "1. Editora",
                        "2. Autor",
                        "3. Gênero",
                        "4. Endereço",
                        "5. Consultar Aluno por Locação",
                        "6. Consultar Locações por Livro",
                        "7. Consultar Professor com mais Locações",
                        "8. Voltar"
                )
        );
        int opc = io.imprimirMenuRetornandoOpcao(opcoesGerencial, "MENU GERENCIAL");

        while (opc != 8) {
            switch (opc) {
                case 1:
                    editoraMenu.imprimirMenu();
                    break;
                case 2:
                    autorMenu.imprimirMenu();
                    break;
                case 3:
                    generoMenu.imprimirMenu();
                    break;
                case 4:
                    enderecoMenu.imprimirMenu();
                    break;
                case 5:
                    consultarAlunoPorLocacao();
                    break;
                case 6:
                    obterQLocPLivroID();
                    break;
                case 7:
                    obterProfessorMaisLocou();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
            opc = io.imprimirMenuRetornandoOpcao(opcoesGerencial, "MENU GERENCIAL");
        }

    }

    private void obterProfessorMaisLocou() {
        String resultado = professorService.obterProfessorComMaisLocacoes();
        System.out.println(resultado);
    }
    private void obterQLocPLivroID() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o ID do livro: ");
        String locacaoIDString = scanner.nextLine().trim();
        int livroID = Integer.parseInt(locacaoIDString);
        int qtd = livroService.obterQuantidadeDeLocacoesPorLivro(livroID);
        if (qtd != 0) {
            System.out.println("Quantidade de locações: " + qtd);
        }
    }

    private void consultarAlunoPorLocacao() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o ID da locação: ");
        String locacaoIDString = scanner.nextLine().trim();
        int locacaoID = Integer.parseInt(locacaoIDString);
        Aluno aluno = alunoService.consultarAlunoPorLocacaoId(locacaoID);
        if (aluno == null) {
            System.out.println("Locação não identificada.");
        } else {
            System.out.println("Aluno: ");
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("CPF: " + aluno.getCpf());
            System.out.println("Curso: " + aluno.getCurso());
            System.out.println("Email: " + aluno.getEmail());
        }
    }

    public static void main(String[] args) {
        GerencialMenu gerencial = new GerencialMenu();
        System.out.println("RESULTADO: ");
        gerencial.imprimirMenuGerencial();
    }
}
