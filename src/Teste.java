import java.util.ArrayList;
import java.util.Scanner;

import model.Banco;
import model.Cliente;
import model.Conta;
import model.Poupanca;

public class Teste {

    public static Conta createAccount(int numero) {
        Scanner sc = new Scanner(System.in);
        String nome, cpf, nomeAgencia;
        int numeroAgencia;
        double saldo;

        System.out.println("\n\nCriar Conta Corrente\n\n");
        System.out.print("Informe seu nome: ");
        nome = sc.nextLine();

        System.out.print("Informe seu CPF: ");
        cpf = sc.nextLine();

        Cliente cliente = new Cliente(nome, cpf);

        System.out.print("Informe o número da agência: ");
        numeroAgencia = Integer.parseInt(sc.nextLine());

        System.out.print("Informe o nome da agência: ");
        nomeAgencia = sc.nextLine();

        Banco banco = new Banco(numeroAgencia, nomeAgencia);

        System.out.print("Informe o valor do depósito: ");
        saldo = Double.parseDouble(sc.nextLine());

        Conta conta = new Conta(numero, banco, cliente, saldo);

        return conta;
    }

    public static Poupanca CreateSavingsAccount(int numero) {
        Scanner sc = new Scanner(System.in);
        String nome, cpf, nomeAgencia;
        int numeroAgencia;
        double saldo, juros;

        System.out.println("\n\nCriar Conta Poupança\n\n");
        System.out.print("Informe seu nome: ");
        nome = sc.nextLine();

        System.out.print("Informe seu CPF: ");
        cpf = sc.nextLine();

        Cliente cliente = new Cliente(nome, cpf);

        System.out.print("\nInforme o número da agência: ");
        numeroAgencia = Integer.parseInt(sc.nextLine());

        System.out.print("Informe o nome da agência: ");
        nomeAgencia = sc.nextLine();

        Banco banco = new Banco(numeroAgencia, nomeAgencia);

        System.out.print("\nInforme o valor do primeiro depósito: ");
        saldo = Double.parseDouble(sc.nextLine());

        System.out.print("\nInforme o valor do juros: ");
        juros = Double.parseDouble(sc.nextLine());

        Poupanca poupanca = new Poupanca(numero, banco, cliente, saldo, juros);

        return poupanca;
    }

    public static void clientNotExists() {
        System.out.println("\nCliente não encontrado.");
    }

    public static void main(String[] args) throws Exception {

        ArrayList<Conta> contas = new ArrayList<Conta>();
        ArrayList<Poupanca> poupancas = new ArrayList<Poupanca>();

        Scanner sc = new Scanner(System.in);
        int menu = 10;

        do {
            System.out.println("\n\n1. Cadastrar Conta ou Poupança (Pergunte aos clientes o que ele quer cadastrar)\n"
                    + "2. Realizar depósito (Buscar pelo CPF do cliente)\n"
                    + "3. Render Juros (Buscar pelo CPF do cliente)\n"
                    + "4. Consultar número e nome da agência (Mostrar nome e o CPF do cliente)\n"
                    + "5. Alterar o número e nome da agência (Buscar pelo nome do cliente)\n" + "0. Sair");

            menu = sc.nextInt();

            switch (menu) {
                case 1:
                    if ((contas.size() + poupancas.size()) < 10) {
                        int m = 0;

                        System.out
                                .println("\n\n1. Criar Conta Corrente\n" + "2. Criar Conta Poupança\n" + "0. Cancelar");
                        m = sc.nextInt();

                        switch (m) {
                            case 1:
                                contas.add(createAccount(contas.size()));
                                break;

                            case 2:
                                poupancas.add(CreateSavingsAccount(poupancas.size()));
                                break;
                        }
                    } else {
                        System.out.println("10 clientes já foram cadastrados.");
                    }
                    break;

                case 2:
                    boolean clienteExiste = false;
                    System.out.println("\n\n Informe o seu cpf para realizar o depósito:");
                    String cpf = sc.next();
                    double novoSaldo = 0;

                    for (Conta conta : contas) {
                        if (conta.getCliente().getCpf().equals(cpf)) {
                            clienteExiste = true;
                            System.out.println("Informe o valor do depósito: ");
                            double valor = Double.parseDouble(sc.next());

                            conta.setSaldo(conta.getSaldo() + valor);
                            novoSaldo = conta.getSaldo();
                        }
                    }

                    for (Poupanca poupanca : poupancas) {
                        if (poupanca.getCliente().getCpf().equals(cpf)) {
                            clienteExiste = true;
                            System.out.println("Informe o valor do depósito: ");
                            double valor = Double.parseDouble(sc.next());

                            poupanca.setSaldo(poupanca.getSaldo() + valor);
                            novoSaldo = poupanca.getSaldo();
                        }
                    }

                    if (!clienteExiste) {
                        clientNotExists();
                    } else {
                        System.out.println("\nDepósito efetuado com sucesso.");
                        System.out.println("Seu novo saldo é: " + novoSaldo);
                    }

                    break;

                case 3:
                    clienteExiste = false;
                    novoSaldo = 0;

                    System.out.println("\n\n Informe o seu cpf para render o juros:");
                    cpf = sc.next();

                    for (Poupanca poupanca : poupancas) {
                        if (poupanca.getCliente().getCpf().equals(cpf)) {
                            clienteExiste = true;
                            poupanca.renderJuros();
                            novoSaldo = poupanca.getSaldo();
                        }
                    }

                    if (!clienteExiste) {
                        clientNotExists();
                    } else {
                        System.out.println("\n Saldo atualizado com sucesso.");
                        System.out.println("Seu novo saldo é: " + novoSaldo);
                    }

                    break;

                case 4:
                    clienteExiste = false;

                    System.out.println("Informe o numero da agencia: ");
                    int numeroAgencia = Integer.parseInt(sc.next());

                    System.out.println("Informe o nome da agencia: ");
                    String nomeAgencia = sc.next();

                    for (Poupanca poupanca : poupancas) {
                        if (poupanca.getBanco().getNumeroAgencia() == numeroAgencia
                                && poupanca.getBanco().getNomeAgencia().equals(nomeAgencia)) {
                            clienteExiste = true;
                            System.out.println("Nome do Cliente: " + poupanca.getCliente().getNome());
                            System.out.println("Nome do CPF: " + poupanca.getCliente().getCpf());
                        }
                    }

                    for (Conta conta : contas) {
                        if (conta.getBanco().getNumeroAgencia() == numeroAgencia
                                && conta.getBanco().getNomeAgencia().equals(nomeAgencia)) {
                            clienteExiste = true;
                            System.out.println("Nome do Cliente: " + conta.getCliente().getNome());
                            System.out.println("Nome do CPF: " + conta.getCliente().getCpf());
                        }
                    }

                    if (!clienteExiste) {
                        clientNotExists();
                    }

                    break;

                case 5:
                    clienteExiste = false;
                    System.out.println("Informe o nome do cliente: ");
                    String nome = sc.next();

                    for (Poupanca poupanca : poupancas) {
                        if (poupanca.getCliente().getNome().equals(nome)) {
                            clienteExiste = true;
                            System.out.println("Informe o numero da agencia: ");
                            numeroAgencia = Integer.parseInt(sc.next());

                            System.out.println("Informe o nome da agencia: ");
                            nomeAgencia = sc.next();

                            poupanca.getBanco().setNumeroAgencia(numeroAgencia);
                            poupanca.getBanco().setNomeAgencia(nomeAgencia);
                        }
                    }

                    for (Conta conta : contas) {
                        if (conta.getCliente().getNome().equals(nome)) {
                            clienteExiste = true;
                            System.out.println("Informe o numero da agencia: ");
                            numeroAgencia = Integer.parseInt(sc.next());

                            System.out.println("Informe o nome da agencia: ");
                            nomeAgencia = sc.next();

                            conta.getBanco().setNumeroAgencia(numeroAgencia);
                            conta.getBanco().setNomeAgencia(nomeAgencia);
                        }
                    }

                    if (!clienteExiste) {
                        clientNotExists();
                    }
                    break;

                default:
                    System.out.println("\n\nOpção inválida \nTente novamente.");
                    break;
            }
        } while (menu != 0);
    }
}
