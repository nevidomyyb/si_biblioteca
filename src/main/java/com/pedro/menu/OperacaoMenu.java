package com.pedro.menu;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.AttributeSet.ColorAttribute;

import com.pedro.config.IO;
import com.pedro.models.Operacao;
import com.pedro.models.TipoOperacao;
import com.pedro.service.OperacaoService;
import com.pedro.utils.ColunaUtils;
import com.pedro.utils.DataUtils;

public class OperacaoMenu {
    private IO io;
    private Scanner scanner;
    private OperacaoService operacaoService;

    public OperacaoMenu(){
        io = new IO();
        scanner = new Scanner(System.in);
        operacaoService = new OperacaoService();
    }

    public void imprimirMenuOperacao() {
        List<String> opcoesOperacao = new ArrayList<String>(Arrays.asList(
                "1. Locação",
                "2. Devolução",
                "3. Consultar Operações",
                "4. Editar Operação",
                "5. Excluir Operação",
                "6. Sair"
                ));

        int opc = io.imprimirMenuRetornandoOpcao(opcoesOperacao, "MENU OPERAÇÕES");
        while (opc != 6) {
            switch (opc) {
                case 1:
                    locacao();
                    break;

                case 2:
                    devolucao();
                    break;
                case 3:
                    listarOperacoes();
                    break;
                case 4:
                    editarOperacao();
                    break;
                case 5:
                    excluirOperacao();
                    break;
                default:
                    System.out.println("[!] Opção Inválida.");
            }
            opc = io.imprimirMenuRetornandoOpcao(opcoesOperacao, "MENU OPERAÇÕES");
        }
    }

    public void locacao() {
        System.out.print("[!] ID do Exemplar: ");
        int exemplar = scanner.nextInt();
        scanner.nextLine();

        System.out.print("[!] ID do funcionário responsável pela LOCAÇÃO: ");
        int funcionario_locacao = scanner.nextInt();
        scanner.nextLine();

        System.out.println("[!] Qual tipo de usuário gostaria de realizar a locação?");
        System.out.println("[1] Aluno");
        System.out.println("[2] Professor");
        System.out.println("[3] Funcionário");
        
        String tipoUsuario = scanner.nextLine().trim();

        int locador = 0;

        if (tipoUsuario.equals("1")) {
            System.out.print("[!] ID do Aluno: ");
            locador = scanner.nextInt();
        } else if (tipoUsuario.equals("2")) {
            System.out.print("[!] ID do Professor: ");
            locador = scanner.nextInt();
        } else if (tipoUsuario.equals("3")) {
            System.out.print("[!] ID do Funcionário: ");
            locador = scanner.nextInt();
        }

        Date dataLocacao = DataUtils.getDataSqlAtual();
        Date dataDevolucao = DataUtils.getFuturaSqlDate(14);

        Operacao operacao = new Operacao(
                funcionario_locacao,
                locador,
                exemplar,
                TipoOperacao.LOCACAO,
                dataLocacao,
                dataDevolucao);

        operacao.setTipoUsuario(tipoUsuario);

        operacaoService.locacao(operacao);
    }

    public void devolucao() {
        System.out.print("[!] ID da Operação: ");
        int idOperacao = scanner.nextInt();
        scanner.nextLine();

        System.out.print("[!] ID do funcionário responsável pela devolução: ");
        int funcionario_devolucao = scanner.nextInt();
        scanner.nextLine();

        Date dataDevolvido = DataUtils.getDataSqlAtual();
        operacaoService.devolucao(idOperacao, funcionario_devolucao, dataDevolvido);
    }

    public void listarOperacoes(){
        List<Operacao> operacoes = operacaoService.listar();
        System.out.println("-------------------------------------------------------OPERAÇÕES-------------------------------------------------");
        System.out.println(
            "| " + ColunaUtils.formatarColuna("ID", 6) + " | " + ColunaUtils.formatarColuna("Func. Loc.", 6) +
            " | " + ColunaUtils.formatarColuna("Func. Dev.", 6) + " | " + ColunaUtils.formatarColuna("Locador", 6) +
            " | " + ColunaUtils.formatarColuna("Tipo Usuário", 12) + " | " + ColunaUtils.formatarColuna("Operação", 12) +
            " | " + ColunaUtils.formatarColuna("Data Loc.", 12) + " | " + ColunaUtils.formatarColuna("Data Dev.", 12) +
            " | " + ColunaUtils.formatarColuna("Devolvido Em", 12) + " |"
        );
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        if(!operacoes.isEmpty()){
            for(Operacao operacao : operacoes){

                System.out.println(
                    "| " + ColunaUtils.formatarColuna(String.valueOf(operacao.getId()), 6) + " | " +
                    ColunaUtils.formatarColuna(String.valueOf(operacao.getFuncionarioLocacaoId()), 6) + " | " +
                    ColunaUtils.formatarColuna(String.valueOf(operacao.getFuncionarioDevolucaoId()), 6) + " | " +
                    ColunaUtils.formatarColuna(String.valueOf(operacao.getLocador()), 6) + " | " +
                    ColunaUtils.formatarColuna(operacao.getTipoUsuario(), 12) + " | " +
                    ColunaUtils.formatarColuna(operacao.getTipoOperacao().toString(), 12) + " | " +
                    ColunaUtils.formatarColuna(operacao.getDataLocacao().toString(), 12) + " | " +
                    ColunaUtils.formatarColuna(operacao.getDataDevolucao().toString(), 12) + " | " +
                    ColunaUtils.formatarColuna(operacao.getDataDevolvido() == null ? "Pendente" : operacao.getDataDevolvido().toString(), 12) + " |"
                );
                System.out.println("----------------------------------------------------------------------------------------------------------------");
            }
        }
    }

    public void editarOperacao(){
        System.out.print("[!] ID da Operação: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("[!] ID Funcionário Locação (pressione [ENTER] para manter atual): ");
        String funcionarioLocacao = scanner.nextLine().trim();

        System.out.print("[!] ID Funcionário Devolução (pressione [ENTER] para manter atual): ");
        String funcionarioDevolucao = scanner.nextLine().trim();

        System.out.println("[!] Tipo Usuário (pressione [ENTER] para manter atual): ");
        System.out.println("[1] Aluno");
        System.out.println("[2] Professor");
        System.out.println("[3] Funcionário");
        String tipoUsuario = scanner.nextLine().trim();

        System.out.print("[!] ID do Locador (pressione [ENTER] para manter atual): ");
        String locador = scanner.nextLine().trim();

        System.out.print("[!] ID do Exemplar: ");
        String exemplar = scanner.nextLine().trim();

        System.out.println("[!] Tipo Operação (pressione [ENTER] para manter atual):");
        System.out.println("[1] Locação");
        System.out.println("[2] Devolução");
        String tipoOperacao = scanner.nextLine().trim();

        System.out.print("[!] Data Locação (pressione [ENTER] para manter atual): ");
        String dataLocacao = scanner.nextLine().trim();

        System.out.print("[!] Data Devolução (pressione [ENTER] para manter atual): ");
        String dataDevolucao = scanner.nextLine().trim();

        System.out.print("[!] Data Devolvido (pressione [ENTER] para manter atual): ");
        String dataDevolvido = scanner.nextLine().trim();

        Operacao operacao = new Operacao(
            funcionarioLocacao.isEmpty() ? 0 : Integer.parseInt(funcionarioLocacao),
            locador.isEmpty() ? 0 : Integer.parseInt(locador),
            exemplar.isEmpty() ? 0 : Integer.parseInt(exemplar),
            tipoOperacao.isEmpty() ? null : tipoOperacao.equals("1") ? TipoOperacao.LOCACAO : TipoOperacao.DEVOLUCAO,
            dataLocacao.isEmpty() ? null : DataUtils.stringToSqlDate(dataLocacao),
            dataDevolucao.isEmpty() ? null : DataUtils.stringToSqlDate(dataDevolucao)
        );

        operacao.setFuncionarioDevolucaoId(funcionarioDevolucao.isEmpty() ? 0 : Integer.parseInt(funcionarioDevolucao));
        operacao.setTipoUsuario(tipoUsuario.isEmpty() ? null : tipoUsuario);
        operacao.setDataDevolvido(dataDevolvido.isEmpty() ? null : DataUtils.stringToSqlDate(dataDevolvido));
        
        operacaoService.editar(id, operacao, tipoUsuario);
    }

    public void excluirOperacao(){
        System.out.println("[!] ID da Operação: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        operacaoService.excluirOperacao(id);
    }

    public static void main(String[] args) {
        new OperacaoMenu().imprimirMenuOperacao();
    }
}
