package com.pedro.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.pedro.config.IO;
import com.pedro.models.Endereco;
import com.pedro.service.EnderecoService;

public class EnderecoMenu {
    
    private Scanner scanner;
    private EnderecoService enderecoService;
    private IO io;

    public EnderecoMenu(){
        scanner = new Scanner(System.in);
        enderecoService = new EnderecoService();
        io = new IO();
    }

    public void imprimirMenu(){
        List<String> opcoesEndereco = new ArrayList<String>(Arrays.asList(
            "[1] Cadastrar endereço",
                "[2] Consultar endereços",
                "[3] Editar endereço",
                "[4] Excluir endereço",
                "[5] Voltar"
        ));
        int opc = io.imprimirMenuRetornandoOpcao(opcoesEndereco, "MENU ENDEREÇOS");
        while (opc != 5) {
            switch (opc) {
                case 1:
                    System.out.println("[!] RUA: ");
                    String rua = scanner.nextLine();
                    System.out.println("BAIRRO: ");
                    String bairro = scanner.nextLine();
                    System.out.println("[!] NÚMERO: ");
                    String numero = scanner.nextLine();
                    System.out.println("[!] CIDADE: ");
                    String cidade = scanner.nextLine();
                    System.out.println("[!] ESTADO: ");
                    String estado = scanner.nextLine();
                    System.out.println("[!] INFORMAÇÕES ADICIONAIS:");
                    System.out.println("[!] Caso não deseje inserir uma nova informação, pressione [ENTER]");
                    System.out.println("[!] CEP: ");
                    String cep = scanner.nextLine();

                    Endereco cadastrar_endereco = new Endereco(rua, bairro, numero, cidade, estado);
                    if(!cep.isEmpty()){
                        cadastrar_endereco.setCep(cep);
                    }

                    enderecoService.cadastrarEndereco(cadastrar_endereco);
                    break;
                
                case 2:
                    List<Endereco> enderecos = enderecoService.listarEnderecos();
                    System.out.println("ENDEREÇOS");
                    System.out.println("ID | RUA | BAIRRO | NÚMERO | CEP | CIDADE | ESTADO ");
                    if(!enderecos.isEmpty()){
                        for(Endereco endereco : enderecos){
                            int max = endereco.getRua().length() < 11 ? endereco.getRua().length()  : 12;
                            System.out.println(
                                "[" + endereco.getId() + "] " + endereco.getRua().substring(0, max) + " - " +
                                endereco.getNumero() + " - " + endereco.getBairro() + " - " + endereco.getCidade() +
                                 " - " + endereco.getEstado()
                                );
                        }
                    }
                    break;
                
                case 3:
                    System.out.println("[!] ID do Endereço: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("[!] Digite os novos valores ou pressione [ENTER] para manter os atuais.");
                    System.out.print("[!] RUA: ");
                    String edicao_rua = scanner.nextLine();
                    System.out.println("[!] BAIRRO: ");
                    String edicao_bairro = scanner.nextLine();
                    System.out.println("[!] NÚMERO: ");
                    String edicao_numero = scanner.nextLine();
                    System.out.println("[!] CIDADE:");
                    String edicao_cidade = scanner.nextLine();
                    System.out.println("[!] ESTADO:");
                    String edicao_estado = scanner.nextLine();
                    System.out.println("[!] CEP: ");
                    String edicao_cep = scanner.nextLine();

                    Endereco edicao_endereco = new Endereco(edicao_rua, edicao_bairro, edicao_numero, edicao_cidade, edicao_estado);
                    edicao_endereco.setCep(edicao_cep);

                    enderecoService.editar(id, edicao_endereco);
                    break;
                
                case 4:
                    System.out.println("[!] ID do Endereço: ");
                    int excluir_id = scanner.nextInt();

                    enderecoService.excluirEndereco(excluir_id);
                    break; 
                default:
                    System.err.println("[!] Opção Inválida.");
            }
            opc = io.imprimirMenuRetornandoOpcao(opcoesEndereco, "MENU ENDEREÇOS");
        }
    }

    public static void main(String[] args) {
        EnderecoMenu enderecoMenu = new EnderecoMenu();
        enderecoMenu.imprimirMenu();
    }

}
