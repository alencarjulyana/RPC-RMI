package com.gugawag.rpc.banco;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AppClienteBanco {

  public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
    String servidor = args.length > 0 ? args[0] : "localhost";
    Registry registry = LocateRegistry.getRegistry(servidor, 1099);
    BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");

    menu();
    Scanner entrada = new Scanner(System.in);
    int opcao = entrada.nextInt();

    while (opcao != 9) {
      switch (opcao) {
        case 2: {
          System.out.println("Digite o número da conta:");
          String conta = entrada.next();
          System.out.println("Saldo: " + banco.saldo(conta));
          break;
        }
        case 3: {
          System.out.println("Quantidade de contas: " + banco.quantidadeContas());
          break;
        }
        case 1: {
          System.out.println("Digite o número da nova conta:");
          String numero = entrada.next();
          System.out.println("Digite o saldo inicial:");
          double saldo = entrada.nextDouble();
          banco.adicionarConta(numero, saldo);
          System.out.println("Conta adicionada!");
          break;
        }
        case 4: {
          System.out.println("Digite o número da conta para pesquisar:");
          String numero = entrada.next();
          Conta conta = banco.pesquisarConta(numero);
          System.out.println(conta != null ? conta : "Conta não encontrada!");
          break;
        }
        case 5: {
          System.out.println("Digite o número da conta para remover:");
          String numero = entrada.next();
          boolean removido = banco.removerConta(numero);
          System.out.println(removido ? "Conta removida!" : "Conta não encontrada!");
          break;
        }
        default:
          System.out.println("Opção inválida!");
      }
      menu();
      opcao = entrada.nextInt();
    }
  }

  public static void menu() {
    System.out.println("\n=== BANCO RMI ===");
    System.out.println("1 - Adicionar nova conta");
    System.out.println("2 - Saldo da conta");
    System.out.println("3 - Quantidade de contas");
    System.out.println("4 - Pesquisar conta");
    System.out.println("5 - Remover conta");
    System.out.println("9 - Sair");
  }
}
