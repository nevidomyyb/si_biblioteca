package com.pedro.menu;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

    public OperacaoMenu() {
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
                "6. Sair"));

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
        Operacao operacao = new Operacao();
        System.out.print("[!] ID do Exemplar: ");
        operacao.setExemplarId(Integer.parseInt(scanner.nextLine().trim()));

        System.out.print("[!] ID ID Funcionário Locação: ");
        operacao.setFuncionarioLocacaoId(Integer.parseInt(scanner.nextLine().trim()));

        System.out.println("[!] Tipo Usuário: ");
        System.out.println("[1] Aluno");
        System.out.println("[2] Professor");
        System.out.println("[3] Funcionário");

        operacao.setTipoUsuario(scanner.nextLine().trim());
        
        System.out.print("[!] ID do Locador: ");
        operacao.setLocador(Integer.parseInt(scanner.nextLine().trim()));

        boolean podeLocar = operacaoService.usuarioPossuiAtrasos(operacao.getLocador(), operacao.getTipoUsuario());

        if (podeLocar == true) {
            operacao.setDataLocacao(DataUtils.getDataSqlAtual());
            operacao.setDataDevolucao(DataUtils.getFuturaSqlDate(14));
            operacao.setTipoOperacao(TipoOperacao.LOCACAO);
            boolean succ = operacaoService.locacao(operacao);
            if(succ){
                System.out.println("[!] Locação realizada com sucesso.");
            } else {
                System.err.println("[!] Não foi possível realizar a locação.");
            }
        } else {
            System.err.println("[!] Locador não está liberado para realizar locações");
        }
    }

    public void devolucao() {
        System.out.print("[!] ID da Operação: ");
        int idOperacao = scanner.nextInt();
        scanner.nextLine();

        System.out.print("[!] ID Funcionário Devolução: ");
        int funcionario_devolucao = scanner.nextInt();
        scanner.nextLine();

        Date dataDevolvido = DataUtils.getDataSqlAtual();
        boolean succ = operacaoService.devolucao(idOperacao, funcionario_devolucao, dataDevolvido);
        if(succ){
            System.out.println("[!] Devolução realizada.");
        } else {
            System.err.println("[!] Não foi possível realizar a devolução.");
        }
    }

    public void listarOperacoes() {
        List<Operacao> operacoes = operacaoService.listar();
        System.out.println(
                "-------------------------------------------------------OPERAÇÕES-------------------------------------------------");
        System.out.println(
                "| " + ColunaUtils.formatarColuna("ID", 6) + " | " + ColunaUtils.formatarColuna("Func. Loc.", 6) +
                        " | " + ColunaUtils.formatarColuna("Func. Dev.", 6) + " | "
                        + ColunaUtils.formatarColuna("Locador", 6) +
                        " | " + ColunaUtils.formatarColuna("Tipo Usuário", 12) + " | "
                        + ColunaUtils.formatarColuna("Operação", 12) +
                        " | " + ColunaUtils.formatarColuna("Data Loc.", 12) + " | "
                        + ColunaUtils.formatarColuna("Data Dev.", 12) +
                        " | " + ColunaUtils.formatarColuna("Devolvido Em", 12) + " |");
        System.out.println("-".repeat(114));
        if (!operacoes.isEmpty()) {
            for (Operacao operacao : operacoes) {
                String status;

                if(operacao.getDataDevolvido() != null) {
                    status = operacao.getDataDevolvido().toString();
                } else if(DataUtils.getDataSqlAtual().after(operacao.getDataDevolucao())){
                    status = "Atrasado";
                } else {
                    status = "Pendente";
                }

                System.out.println(
                        "| " + ColunaUtils.formatarColuna(String.valueOf(operacao.getId()), 6) + " | " +
                                ColunaUtils.formatarColuna(String.valueOf(operacao.getFuncionarioLocacaoId()), 6)
                                + " | " +
                                ColunaUtils.formatarColuna(String.valueOf(operacao.getFuncionarioDevolucaoId()), 6)
                                + " | " +
                                ColunaUtils.formatarColuna(String.valueOf(operacao.getLocador()), 6) + " | " +
                                ColunaUtils.formatarColuna(operacao.getTipoUsuario(), 12) + " | " +
                                ColunaUtils.formatarColuna(operacao.getTipoOperacao().toString(), 12) + " | " +
                                ColunaUtils.formatarColuna(operacao.getDataLocacao().toString(), 12) + " | " +
                                ColunaUtils.formatarColuna(operacao.getDataDevolucao().toString(), 12) + " | " +
                                ColunaUtils.formatarColuna(status, 12)  
                                + " |");
                System.out.println("-".repeat(114));
            }
        }
    }

    public void editarOperacao() {
        System.out.print("[!] ID da Operação: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        Operacao operacao = lerDadosOperacao();
        boolean succ = operacaoService.editar(id, operacao);
        if(succ){
            System.out.println("[!] Operação editada.");
        } else {
            System.err.println("[!] Não foi possível editar a operação");
        }
    }

    public Operacao lerDadosOperacao(){
        Operacao operacao = new Operacao();

        System.out.print("[!] ID Funcionário Locação: ");
        operacao.setFuncionarioLocacaoId(Integer.parseInt(scanner.nextLine().trim()));

        System.out.print("[!] ID Funcionário Devolução (opcional, pressione [ENTER] para pular): ");
        String funcionarioDevolucao = scanner.nextLine().trim();
        if(funcionarioDevolucao.isEmpty()){
            operacao.setFuncionarioDevolucaoId(0);
        } else {
            operacao.setFuncionarioDevolucaoId(Integer.parseInt(funcionarioDevolucao));
        }

        System.out.println("[!] Tipo Usuário: ");
        System.out.println("[1] Aluno");
        System.out.println("[2] Professor");
        System.out.println("[3] Funcionário");
        operacao.setTipoUsuario(scanner.nextLine().trim());
        
        System.out.print("[!] ID do Locador: ");
        operacao.setLocador(Integer.parseInt(scanner.nextLine().trim()));

        System.out.print("[!] ID do Exemplar: ");
        operacao.setExemplarId(Integer.parseInt(scanner.nextLine().trim()));

        System.out.println("[!] Tipo Operação: ");
        System.out.println("[1] Locação");
        System.out.println("[2] Devolução");
        String tipoOperacao = scanner.nextLine().trim();

        if(tipoOperacao.equals("1")){
            operacao.setTipoOperacao(TipoOperacao.LOCACAO);
        } else if(tipoOperacao.equals("2")){
            operacao.setTipoOperacao(TipoOperacao.DEVOLUCAO);
        }

        System.out.print("[!] Data Locação: ");
        operacao.setDataLocacao(DataUtils.stringToSqlDate(scanner.nextLine().trim()));

        System.out.print("[!] Data Devolução: ");
        operacao.setDataDevolucao(DataUtils.stringToSqlDate(scanner.nextLine().trim()));

        System.out.print("[!] Data Devolvido (opcional, pressione [ENTER] para pular): ");
        String dataDevolvido = scanner.nextLine().trim();
        if(dataDevolvido.isEmpty()){
            operacao.setDataDevolvido(null);
        } else {
            operacao.setDataDevolvido(DataUtils.stringToSqlDate(dataDevolvido));
        }
        
        return operacao;
    }

    public void excluirOperacao() {
        System.out.println("[!] ID da Operação: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        boolean succ = operacaoService.excluirOperacao(id);
        if(succ){
            System.out.println("[!] Operação excluída.");
        } else {
            System.err.println("[!] Não foi possível excluir a operação");
        }
    }

    public static void main(String[] args) {
        new OperacaoMenu().imprimirMenuOperacao();
    }
}
