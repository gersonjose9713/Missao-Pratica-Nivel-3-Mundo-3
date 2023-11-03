import cadastro.model.util.ConectorBD;
import cadastrobd.model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = ConectorBD.getConnection();
            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(connection);
            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO(connection);

            Scanner scanner = new Scanner(System.in);
            int opcao;

            do {
                System.out.println("----------------------------------------------------");
                System.out.println("Menu Principal:");
                System.out.println("1. Incluir");
                System.out.println("2. Alterar");
                System.out.println("3. Excluir");
                System.out.println("4. Obter por ID");
                System.out.println("5. Obter todos");
                System.out.println("0. Sair");
                System.out.println("----------------------------------------------------");
                System.out.print("Escolha uma opção: ");

                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("----------------------------------------------------");
                        System.out.println("Escolha:");
                        System.out.println("1. Pessoa Física");
                        System.out.println("2. Pessoa Jurídica");
                        int incluir = scanner.nextInt();
                        scanner.nextLine();

                        if (incluir == 1) {
                            PessoaFisica novaPessoaFisica = lerPessoaFisica(scanner);
                            pessoaFisicaDAO.incluir(novaPessoaFisica);
                            System.out.println("----------------------------------------------------");
                            System.out.println("Pessoa física incluída com sucesso.");
                        } else if (incluir == 2) {
                            PessoaJuridica novaPessoaJuridica = lerPessoaJuridica(scanner);
                            pessoaJuridicaDAO.incluir(novaPessoaJuridica);
                            System.out.println("----------------------------------------------------");
                            System.out.println("Pessoa jurídica incluída com sucesso.");
                        } else {
                            System.out.println("----------------------------------------------------");
                            System.out.println("Opção inválida. Tente novamente.");
                        }
                        break;
                    case 2:
                        System.out.println("----------------------------------------------------");
                        System.out.println("Escolha:");
                        System.out.println("1. Pessoa Física");
                        System.out.println("2. Pessoa Jurídica");
                        int alterar = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Digite o ID da pessoa a ser alterada: ");
                        int idPessoaAlterar = scanner.nextInt();
                        scanner.nextLine();

                        if (alterar == 1) {
                            PessoaFisica pessoaFisicaAlterar = pessoaFisicaDAO.getPessoa(idPessoaAlterar);
                            if (pessoaFisicaAlterar != null) {
                                atualizarPessoaFisica(pessoaFisicaAlterar, scanner);
                                pessoaFisicaDAO.alterar(pessoaFisicaAlterar);
                                System.out.println("----------------------------------------------------");
                                System.out.println("Pessoa física alterada com sucesso.");
                            } else {
                                System.out.println("----------------------------------------------------");
                                System.out.println("Pessoa física não encontrada.");
                            }
                        } else if (alterar == 2) {
                            PessoaJuridica pessoaJuridicaAlterar = pessoaJuridicaDAO.getPessoaJuridica(idPessoaAlterar);
                            if (pessoaJuridicaAlterar != null) {
                                atualizarPessoaJuridica(pessoaJuridicaAlterar, scanner);
                                pessoaJuridicaDAO.alterar(pessoaJuridicaAlterar);
                                System.out.println("----------------------------------------------------");
                                System.out.println("Pessoa jurídica alterada com sucesso.");
                            } else {
                                System.out.println("----------------------------------------------------");
                                System.out.println("Pessoa jurídica não encontrada.");
                            }
                        } else {
                            System.out.println("----------------------------------------------------");
                            System.out.println("Opção inválida. Tente novamente.");
                        }
                        break;
                    case 3:
                        System.out.println("----------------------------------------------------");
                        System.out.println("Escolha: ");
                        System.out.println("1. Pessoa Física");
                        System.out.println("2. Pessoa Jurídica");
                        int excluir = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Digite o ID da pessoa a ser excluída: ");
                        int idPessoaExcluir = scanner.nextInt();
                        scanner.nextLine();

                        if (excluir == 1) {
                            pessoaFisicaDAO.excluir(idPessoaExcluir);
                            System.out.println("----------------------------------------------------");
                            System.out.println("Pessoa física excluída com sucesso.");
                        } else if (excluir == 2) {
                            pessoaJuridicaDAO.excluir(idPessoaExcluir);
                            System.out.println("----------------------------------------------------");
                            System.out.println("Pessoa jurídica excluída com sucesso.");
                        } else {
                            System.out.println("----------------------------------------------------");
                            System.out.println("Opção inválida. Tente novamente.");
                        }
                        break;
                    case 4:
                        System.out.println("----------------------------------------------------");
                        System.out.println("Escolha: ");
                        System.out.println("1. Pessoa Física");
                        System.out.println("2. Pessoa Jurídica");
                        int obter = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Digite o ID da pessoa a ser obtida: ");
                        int idPessoaObter = scanner.nextInt();
                        scanner.nextLine();

                        if (obter == 1) {
                            PessoaFisica pessoaFisicaObtida = pessoaFisicaDAO.getPessoa(idPessoaObter);
                            if (pessoaFisicaObtida != null) {
                                pessoaFisicaObtida.exibir();
                            } else {
                                System.out.println("----------------------------------------------------");
                                System.out.println("Pessoa física não encontrada.");
                            }
                        } else if (obter == 2) {
                            PessoaJuridica pessoaJuridicaObtida = pessoaJuridicaDAO.getPessoaJuridica(idPessoaObter);
                            if (pessoaJuridicaObtida != null) {
                                pessoaJuridicaObtida.exibir();
                            } else {
                                System.out.println("----------------------------------------------------");
                                System.out.println("Pessoa jurídica não encontrada.");
                            }
                        } else {
                            System.out.println("----------------------------------------------------");
                            System.out.println("Opção inválida. Tente novamente.");
                        }
                        break;
                    case 5:
                        System.out.println("----------------------------------------------------");
                        System.out.println("Escolha:");
                        System.out.println("1. Pessoa Física");
                        System.out.println("2. Pessoa Jurídica");
                        int obterTodos = scanner.nextInt();
                        scanner.nextLine();

                        if (obterTodos == 1) {
                            List<PessoaFisica> todasPessoasFisicas = pessoaFisicaDAO.getPessoas();
                            System.out.println("Todas as pessoas físicas:");
                            for (PessoaFisica pessoa : todasPessoasFisicas) {
                                System.out.println("----------------------------------------------------");
                                pessoa.exibir();
                            }
                        } else if (obterTodos == 2) {
                            List<PessoaJuridica> todasPessoasJuridicas = pessoaJuridicaDAO.getPessoasJuridicas();
                            System.out.println("Todas as pessoas jurídicas:");
                            for (PessoaJuridica pessoa : todasPessoasJuridicas) {
                                System.out.println("----------------------------------------------------");
                                pessoa.exibir();
                            }
                        } else {
                            System.out.println("----------------------------------------------------");
                            System.out.println("Opção inválida. Tente novamente.");
                        }
                        break;
                    case 0:
                        System.out.println("----------------------------------------------------");
                        System.out.println("Saindo do programa.");
                        System.out.println("----------------------------------------------------");
                        break;
                    default:
                        System.out.println("----------------------------------------------------");
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } while (opcao != 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(connection);
        }
    }

    private static Pessoa lerPessoa(Scanner scanner) {
        System.out.println("----------------------------------------------------");
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.print("Digite a cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Digite o estado: ");
        String estado = scanner.nextLine();
        System.out.print("Digite o telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();

        return new Pessoa(0, nome, logradouro, cidade, estado, telefone, email);
    }

    private static PessoaFisica lerPessoaFisica(Scanner scanner) {
        Pessoa dadosPessoa = lerPessoa(scanner);
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        System.out.println("----------------------------------------------------");
        return new PessoaFisica(0, dadosPessoa.getNome(), dadosPessoa.getLogradouro(), dadosPessoa.getCidade(), dadosPessoa.getEstado(), dadosPessoa.getTelefone(), dadosPessoa.getEmail(), cpf);
    }

    private static PessoaJuridica lerPessoaJuridica(Scanner scanner) {
        Pessoa dadosPessoa = lerPessoa(scanner);
        System.out.print("Digite o CNPJ: ");
        String cnpj = scanner.nextLine();
        System.out.println("----------------------------------------------------");

        return new PessoaJuridica(0, dadosPessoa.getNome(), dadosPessoa.getLogradouro(), dadosPessoa.getCidade(), dadosPessoa.getEstado(), dadosPessoa.getTelefone(), dadosPessoa.getEmail(), cnpj);
    }

    private static void atualizarPessoaFisica(PessoaFisica pessoa, Scanner scanner) {
        System.out.println("----------------------------------------------------");
        System.out.print("Digite o nome: ");
        pessoa.setNome(scanner.nextLine());
        System.out.print("Digite o logradouro: ");
        pessoa.setLogradouro(scanner.nextLine());
        System.out.print("Digite a cidade: ");
        pessoa.setCidade(scanner.nextLine());
        System.out.print("Digite o estado: ");
        pessoa.setEstado(scanner.nextLine());
        System.out.print("Digite o telefone: ");
        pessoa.setTelefone(scanner.nextLine());
        System.out.print("Digite o email: ");
        pessoa.setEmail(scanner.nextLine());
        System.out.print("Digite o CPF: ");
        pessoa.setCpf(scanner.nextLine());
        System.out.println("----------------------------------------------------");
    }

    private static void atualizarPessoaJuridica(PessoaJuridica pessoa, Scanner scanner) {
        System.out.println("----------------------------------------------------");
        System.out.print("Digite o nome: ");
        pessoa.setNome(scanner.nextLine());
        System.out.print("Digite o logradouro: ");
        pessoa.setLogradouro(scanner.nextLine());
        System.out.print("Digite a cidade: ");
        pessoa.setCidade(scanner.nextLine());
        System.out.print("Digite o estado: ");
        pessoa.setEstado(scanner.nextLine());
        System.out.print("Digite o telefone: ");
        pessoa.setTelefone(scanner.nextLine());
        System.out.print("Digite o email: ");
        pessoa.setEmail(scanner.nextLine());
        System.out.print("Digite o CNPJ: ");
        pessoa.setCnpj(scanner.nextLine());
        System.out.println("----------------------------------------------------");
    }
}