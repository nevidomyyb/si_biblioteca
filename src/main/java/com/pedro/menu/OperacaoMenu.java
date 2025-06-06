package com.pedro.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.pedro.config.IO;
import com.pedro.models.Operacao;
import com.pedro.models.TipoOperacao;
import com.pedro.service.OperacaoService;

public class OperacaoMenu {
    private IO io = new IO();
    private Scanner scanner = new Scanner(System.in);
    private OperacaoService operacaoService = new OperacaoService();


    public void imprimirMenuOperacao(){
        List<String> opcoesOperacao = new ArrayList<String>(Arrays.asList( 
            "1. Locação", 
            "2. Devolução", 
            "3. Sair"
            ));
        
        int opc = io.imprimirMenuRetornandoOpcao(opcoesOperacao, "MENU OPERAÇÕES");
        while (opc != 3) {
            switch (opc) {
                case 1:
                    System.out.println("[!] ID do funcionário responsável pela LOCAÇÃO: ");
                    int funcionario_locacao = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("[!] Qual tipo de usuário gostaria de realizar a locação?");
                    System.out.println("[1] Aluno");
                    System.out.println("[2] Professor");
                    System.out.println("[3] Funcionário");

                    String tipoUsuario = null;
                    int tipoUsuarioInt = scanner.nextInt();
                    scanner.nextLine();
                    int locador = 0;

                    if(tipoUsuarioInt != 1 && tipoUsuarioInt != 2 && tipoUsuarioInt != 3){
                        System.out.println("[!] Tipo de Usuário inválido");
                    } else if(tipoUsuarioInt == 1) {
                        System.out.println("[!] ID do Aluno: ");
                        locador = scanner.nextInt();
                        tipoUsuario = "aluno";
                    } else if (tipoUsuarioInt == 2){
                        System.out.println("[!] ID do Professor: ");
                        locador = scanner.nextInt();
                        tipoUsuario = "professor";
                    } else if (tipoUsuarioInt == 3) {
                        System.out.println("[!] ID do Funcionário: ");
                        locador = scanner.nextInt();
                        tipoUsuario = "funcionario";
                    }

                    System.out.println("[!] ID do Exemplar:");
                    int exemplar = scanner.nextInt();
                    scanner.nextLine();

                    java.util.Date dataAtualLocacao = new java.util.Date();
                    java.sql.Date dataLocacao = new java.sql.Date(dataAtualLocacao.getTime());                    
                    
                    Calendar calendario = Calendar.getInstance();
                    calendario.setTime(dataAtualLocacao);
                    calendario.add(Calendar.DAY_OF_MONTH, 14);
                    java.sql.Date dataDevolucao = new java.sql.Date(calendario.getTimeInMillis());

                    Operacao operacao = new Operacao(
                        funcionario_locacao, 
                        locador, 
                        exemplar, 
                        TipoOperacao.LOCACAO, 
                        dataLocacao, 
                        dataDevolucao
                        );
                    
                    operacaoService.inserir(operacao, tipoUsuario);
                    break;
                
                    case 2:
                        System.out.println("[!] ID da Operação: ");
                        int idOperacao = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("[!] ID do funcionário responsável pela devolução: ");
                        int funcionario_devolucao = scanner.nextInt();
                        scanner.nextLine();

                        java.util.Date dataAtualDevolvido = new java.util.Date();
                        java.sql.Date dataDevolvido = new java.sql.Date(dataAtualDevolvido.getTime());

                        operacaoService.fecharOperacao(idOperacao, funcionario_devolucao, dataDevolvido);
                        break;

                default:
                    System.out.println("[!] Opção Inválida.");
            }
            opc = io.imprimirMenuRetornandoOpcao(opcoesOperacao, "MENU OPERAÇÕES");
        }

    }
}
