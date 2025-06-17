package com.pedro.menu;

import java.util.List;
import java.util.Scanner;

import com.pedro.config.IO;
import com.pedro.models.Aluno;
import com.pedro.models.Endereco;
import com.pedro.models.Professor;
import com.pedro.service.AlunoService;
import com.pedro.service.ProfessorService;
import com.pedro.utils.ColunaUtils;

public class LeitorMenu {

    private Scanner scanner;
    AlunoService alunoService = new AlunoService();
    ProfessorService professorService = new ProfessorService();
    EnderecoMenu enderecoMenu = new EnderecoMenu();

    public LeitorMenu() {
        scanner = new Scanner(System.in);
    }

    public void imprimirMenu() {
        IO io = new IO();
        List<String> opcoesMenu = List.of(
                "[1] Cadastrar Leitor",
                "[2] Listar Leitores",
                "[3] Editar Leitor",
                "[4] Excluir Leitor",
                "[5] Voltar");
        int opc = io.imprimirMenuRetornandoOpcao(opcoesMenu, "Gerenciar Leitores");
        while (opc != 5) {
            switch (opc) {
                case 1:
                    cadastrarLeitor();
                    break;
                case 2:
                    List<Aluno> alunos = alunoService.listarAlunos();
                    List<Professor> professores = professorService.listarProfessors();
                    System.out.println(
                            "----------------------------------------------------------------------------------------------------------LEITORES-------------------------------------------------------------------------------------------------------");
                    System.out.println(
                            "| " + ColunaUtils.formatarColuna("ID", 6) + " | " + ColunaUtils.formatarColuna("Nome", 20)
                                    + " | " + ColunaUtils.formatarColuna("CPF", 14) +
                                    " | " + ColunaUtils.formatarColuna("Email", 25) + " | "
                                    + ColunaUtils.formatarColuna("Telefone", 12) +
                                    " | " + ColunaUtils.formatarColuna("Matrícula/Credencial", 20) + " | "
                                    + ColunaUtils.formatarColuna("Usuário", 12) +
                                    " | " + ColunaUtils.formatarColuna("ID End.", 6) + " | "
                                    + ColunaUtils.formatarColuna("RUA", 12) +
                                    " | " + ColunaUtils.formatarColuna("BAIRRO", 12) + " | "
                                    + ColunaUtils.formatarColuna("NÚMERO", 6) +
                                    " | " + ColunaUtils.formatarColuna("CEP", 10) + " | "
                                    + ColunaUtils.formatarColuna("CIDADE", 12)
                                    +
                                    " | " + ColunaUtils.formatarColuna("ESTADO", 6) + " |");
                    System.out.println("-".repeat(217));
                    if (!alunos.isEmpty()) {
                        for (Aluno aluno : alunos) {
                            System.out.println(
                                    "| " + ColunaUtils.formatarColuna(String.valueOf(aluno.getId()), 6) + " | "
                                            + ColunaUtils.formatarColuna(aluno.getNome(), 20) + " | " +
                                            ColunaUtils.formatarColuna(aluno.getCpf(), 14) + 
                                            " | " + ColunaUtils.formatarColuna(aluno.getEmail(), 25) + " | "
                                            + ColunaUtils.formatarColuna(aluno.getTelefone(), 12) +
                                            " | " + ColunaUtils.formatarColuna(aluno.getMatricula(), 20) + " | "
                                            + ColunaUtils.formatarColuna(aluno.getLogin(), 12) +
                                            " | " + enderecoMenu.imprimirEnderecoPorId(aluno.getEnderecoId()) + " |");
                        };
                    }

                    if (!professores.isEmpty()) {
                        for (Professor professor : professores) {

                            System.out.println(
                                "| " + ColunaUtils.formatarColuna(String.valueOf(professor.getId()), 6) + " | " +
                                ColunaUtils.formatarColuna(professor.getNome(), 20) + " | " + ColunaUtils.formatarColuna(professor.getCpf(), 14) +
                                " | " + ColunaUtils.formatarColuna(professor.getEmail(), 25) + " | " + 
                                ColunaUtils.formatarColuna(professor.getTelefone(), 12) + " | " + ColunaUtils.formatarColuna(professor.getCredencial(), 20) +
                                " | " + ColunaUtils.formatarColuna(professor.getLogin(), 12) + " | "  + enderecoMenu.imprimirEnderecoPorId(professor.getEnderecoId()) + " |"
                            );
                        }
                    }
                    System.out.println("-".repeat(217));
                    break;
                case 3:
                    editarLeitor();
                    break;
                case 4:
                    excluirLeitor();
                    break;
                default:
                    System.out.println("[!] Opção inválida");
            }

            opc = io.imprimirMenuRetornandoOpcao(opcoesMenu, "Gerenciar Leitores");
        }

    }

    public static void main(String[] args) {
        LeitorMenu leitorMenu = new LeitorMenu();
        leitorMenu.imprimirMenu();
    }

    private void excluirLeitor() {
        boolean sucesso = false;
        System.out.println("Tipo de Usuário: ");
        System.out.println("[1] Aluno");
        System.out.println("[2] Professor");
        String tipoUsuario = scanner.nextLine().trim();
        int tipoUsuarioInt = Integer.parseInt(tipoUsuario);

        if (tipoUsuarioInt != 1 && tipoUsuarioInt != 2) {
            System.out.println("[!] Tipo de Usuário inválido");
        }
        System.out.print("ID do Leitor: ");
        String ID = scanner.nextLine().trim();
        int IDInt = Integer.parseInt(ID);
        if (tipoUsuarioInt == 1) {

            sucesso = alunoService.excluirAluno(IDInt);
        } else {

            sucesso = professorService.excluirProfessor(IDInt);
        }
        if (!sucesso) {
            System.err.println("[!] Não foi possível excluir o leitor.");
        } else {
            System.out.println("[!] Leitor excluído.");
        }
    }

    private void editarLeitor() {
        boolean sucesso = false;
        System.out.println("Tipo de Usuário: ");
        System.out.println("[1] Aluno");
        System.out.println("[2] Professor");
        String tipoUsuario = scanner.nextLine().trim();
        int tipoUsuarioInt = Integer.parseInt(tipoUsuario);

        if (tipoUsuarioInt != 1 && tipoUsuarioInt != 2) {
            System.out.println("[!] Tipo de Usuário inválido");
        }
        System.out.print("ID do Leitor: ");
        String ID = scanner.nextLine().trim();
        int IDInt = Integer.parseInt(ID);
        if (tipoUsuarioInt == 1) {
            Aluno aluno = lerDadosAluno(true);
            sucesso = alunoService.editarAluno(IDInt, aluno);
        } else {
            Professor professor = lerDadosProfessor(true);
            sucesso = professorService.editarProfessor(IDInt, professor);
        }
        if (!sucesso) {
            System.err.println("[!] Não foi possível editar o leitor.");
        } else {
            System.out.println("[!] Leitor editado.");
        }
    }

    private void cadastrarLeitor() {
        boolean sucesso = false;
        System.out.println("Tipo de Usuário: ");
        System.out.println("[1] Aluno");
        System.out.println("[2] Professor");
        String tipoUsuario = scanner.nextLine().trim();
        int tipoUsuarioInt = Integer.parseInt(tipoUsuario);
        if (tipoUsuarioInt != 1 && tipoUsuarioInt != 2) {
            System.out.println("[!] Tipo de Usuário inválido");
        } else if (tipoUsuarioInt == 1) {
            Aluno aluno = lerDadosAluno(false);
            sucesso = alunoService.registrarAluno(aluno);
        } else {
            Professor professor = lerDadosProfessor(false);
            sucesso = professorService.registrarProfessor(professor);
        }
        if (!sucesso) {
            System.err.println("[!] Não foi possível cadastrar o leitor.");
        } else {
            System.out.println("[!] Leitor cadastrado com sucesso.");
        }
    }

    private Professor lerDadosProfessor(boolean modoEditar) {
        Professor prof = new Professor();
        EnderecoMenu enderecoMenu = new EnderecoMenu();
        System.out.print("[!] NOME: ");
        prof.setNome(scanner.nextLine());

        System.out.print("[!] CPF: ");
        prof.setCpf(scanner.nextLine());

        System.out.print("[!] TELEFONE (opcional, pressione [ENTER] para pular): ");
        prof.setTelefone(scanner.nextLine());

        System.out.print("[!] EMAIL: ");
        prof.setEmail(scanner.nextLine());

        System.out.println("[!] ENDEREÇO");
        Endereco endereco = enderecoMenu.cadastrarOuPular();
        if (endereco != null)
            prof.setEnderecoId(endereco.getId());

        System.out.print("[!] DISCIPLINA: ");
        prof.setDisciplina(scanner.nextLine());

        System.out.print("[!] CREDENCIAL: ");
        prof.setCredencial(scanner.nextLine());

        if (!modoEditar) {
            System.out.print("[!] LOGIN: ");
            prof.setLogin(scanner.nextLine());

            System.out.print("[!] SENHA: ");
            prof.setSenha(scanner.nextLine());
        }
        return prof;

    }

    private Aluno lerDadosAluno(boolean modoEditar) {
        Aluno aluno = new Aluno();
        EnderecoMenu enderecoMenu = new EnderecoMenu();
        System.out.print("[!] NOME: ");
        aluno.setNome(scanner.nextLine());

        System.out.print("[!] CPF: ");
        aluno.setCpf(scanner.nextLine());

        System.out.print("[!] TELEFONE (opcional, pressione [ENTER] para pular): ");
        aluno.setTelefone(scanner.nextLine());

        System.out.print("[!] EMAIL: ");
        aluno.setEmail(scanner.nextLine());

        System.out.print("[!] CURSO: ");
        aluno.setCurso(scanner.nextLine());

        System.out.print("[!] PERÍODO: ");
        aluno.setPeriodo(scanner.nextLine());

        System.out.print("[!] MATRÍCULA: ");
        aluno.setMatricula(scanner.nextLine());

        System.out.print("[!] TURNO: ");
        aluno.setTurno(scanner.nextLine());

        System.out.println("[!] ENDEREÇO");
        Endereco endereco = enderecoMenu.cadastrarOuPular();
        if (endereco != null)
            aluno.setEnderecoId(endereco.getId());

        if (!modoEditar) {
            System.out.print("[!] LOGIN: ");
            aluno.setLogin(scanner.nextLine());

            System.out.print("[!] SENHA: ");
            aluno.setSenha(scanner.nextLine());
        }

        return aluno;
    }

}
