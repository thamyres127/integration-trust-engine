
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static List<Integracao> integracoes = new ArrayList<>();
    static int proximoId = 1;

    static class Integracao {
        int id;
        String nome;
        String origem;
        String destino;
        String responsavel;
        String numeroCertificado;
        LocalDate validade;
        String situacao;

        Integracao(int id, String nome, String origem,
                   String destino, String responsavel,
                   String numeroCertificado,
                   LocalDate validade, String situacao) {

            this.id = id;
            this.nome = nome;
            this.origem = origem;
            this.destino = destino;
            this.responsavel = responsavel;
            this.numeroCertificado = numeroCertificado;
            this.validade = validade;
            this.situacao = situacao;
        }
    }

    public static void main(String[] args) {

        int opcao;

        System.out.println("==========================================");
        System.out.println("       INTEGRATION TRUST ENGINE");
        System.out.println("       Gestao de Integracoes Tecnicas");
        System.out.println("==========================================");

        do {
            exibirMenu();

            opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    cadastrarIntegracao();
                    break;

                case 2:
                    listarIntegracoes();
                    break;

                case 3:
                    consultarIntegracao();
                    break;

                case 4:
                    verificarCertificados();
                    break;

                case 5:
                    System.out.println(
                        "\nSistema encerrado. Ate a proxima!"
                    );
                    break;

                default:
                    System.out.println(
                        "\nOpcao invalida. Tente novamente."
                    );
            }

        } while (opcao != 5);

        scanner.close();
    }

    static void exibirMenu() {
        System.out.println("\n============== MENU PRINCIPAL ==============");
        System.out.println("1 - Cadastrar integracao");
        System.out.println("2 - Listar integracoes");
        System.out.println("3 - Consultar integracao por ID");
        System.out.println("4 - Verificar certificados");
        System.out.println("5 - Sair");
        System.out.println("============================================");
    }

    static void cadastrarIntegracao() {

        System.out.println("\n===== CADASTRO DE INTEGRACAO =====");

        String nome = lerTexto("Nome da integracao: ");
        String origem = lerTexto("Sistema de origem: ");
        String destino = lerTexto("Sistema de destino: ");
        String responsavel = lerTexto("Responsavel tecnico: ");
        String certificado = lerTexto(
            "Numero de serie do certificado: "
        );

        LocalDate validade = lerData(
            "Validade do certificado (AAAA-MM-DD): "
        );

        if (validade == null) {
            System.out.println(
                "Cadastro cancelado: data invalida."
            );
            return;
        }

        String situacao = lerSituacao();

        Integracao nova = new Integracao(
            proximoId,
            nome,
            origem,
            destino,
            responsavel,
            certificado,
            validade,
            situacao
        );

        integracoes.add(nova);

        System.out.println("\nIntegracao cadastrada com sucesso!");
        System.out.println("ID gerado: " + proximoId);

        proximoId++;
    }

    static void listarIntegracoes() {

        System.out.println("\n===== LISTA DE INTEGRACOES =====");

        if (integracoes.isEmpty()) {
            System.out.println(
                "Nenhuma integracao cadastrada."
            );
            return;
        }

        for (Integracao i : integracoes) {

            System.out.println("--------------------------------------------");
            System.out.println("ID: " + i.id);
            System.out.println("Nome: " + i.nome);
            System.out.println("Origem: " + i.origem);
            System.out.println("Destino: " + i.destino);
            System.out.println("Responsavel: " + i.responsavel);
            System.out.println(
                "Certificado: " + i.numeroCertificado
            );
            System.out.println("Validade: " + i.validade);
            System.out.println("Situacao: " + i.situacao);
            System.out.println(
                "Status do certificado: " +
                obterStatusCertificado(i.validade)
            );
        }

        System.out.println("--------------------------------------------");
        System.out.println(
            "Total de integracoes: " + integracoes.size()
        );
    }

    static void consultarIntegracao() {

        System.out.println("\n===== CONSULTAR INTEGRACAO =====");

        if (integracoes.isEmpty()) {
            System.out.println(
                "Nao existem integracoes cadastradas."
            );
            return;
        }

        int id = lerInteiro("Digite o ID da integracao: ");

        for (Integracao i : integracoes) {

            if (i.id == id) {

                System.out.println("\nIntegracao encontrada!");
                System.out.println("ID: " + i.id);
                System.out.println("Nome: " + i.nome);
                System.out.println("Origem: " + i.origem);
                System.out.println("Destino: " + i.destino);
                System.out.println("Responsavel: " + i.responsavel);
                System.out.println(
                    "Certificado: " + i.numeroCertificado
                );
                System.out.println("Validade: " + i.validade);
                System.out.println("Situacao: " + i.situacao);
                System.out.println(
                    "Status: " + obterStatusCertificado(i.validade)
                );

                return;
            }
        }

        System.out.println(
            "Nenhuma integracao encontrada com esse ID."
        );
    }

    static void verificarCertificados() {

        System.out.println("\n===== MONITORAMENTO DE CERTIFICADOS =====");

        if (integracoes.isEmpty()) {
            System.out.println(
                "Nenhuma integracao cadastrada."
            );
            return;
        }

        LocalDate hoje = LocalDate.now();
        int vencidos = 0;
        int proximos = 0;
        int regulares = 0;

        for (Integracao i : integracoes) {

            long dias = ChronoUnit.DAYS.between(
                hoje, i.validade
            );

            String status = obterStatusCertificado(i.validade);

            System.out.println(
                "Integracao: " + i.nome +
                " | Certificado: " + status
            );

            if (dias < 0) {
                vencidos++;
            } else if (dias <= 30) {
                proximos++;
            } else {
                regulares++;
            }
        }

        System.out.println("\n===== RESUMO =====");
        System.out.println("Certificados vencidos: " + vencidos);
        System.out.println(
            "Vencendo em ate 30 dias: " + proximos
        );
        System.out.println("Regulares: " + regulares);
    }

    static String obterStatusCertificado(LocalDate validade) {

        LocalDate hoje = LocalDate.now();

        long dias = ChronoUnit.DAYS.between(hoje, validade);

        if (dias < 0) {
            return "VENCIDO";
        }

        if (dias == 0) {
            return "VENCE HOJE";
        }

        if (dias <= 30) {
            return "PROXIMO DO VENCIMENTO (" + dias + " dias)";
        }

        return "REGULAR";
    }

    static String lerSituacao() {

        while (true) {

            System.out.println("\nSituacao operacional:");
            System.out.println("1 - ATIVA");
            System.out.println("2 - INATIVA");
            System.out.println("3 - EM MANUTENCAO");

            int opcao = lerInteiro("Escolha: ");

            switch (opcao) {
                case 1:
                    return "ATIVA";
                case 2:
                    return "INATIVA";
                case 3:
                    return "EM MANUTENCAO";
                default:
                    System.out.println("Situacao invalida.");
            }
        }
    }

    static String lerTexto(String mensagem) {

        while (true) {

            System.out.print(mensagem);
            String valor = scanner.nextLine().trim();

            if (!valor.isEmpty()) {
                return valor;
            }

            System.out.println(
                "Campo obrigatorio. Informe um valor."
            );
        }
    }

    static int lerInteiro(String mensagem) {

        while (true) {

            System.out.print(mensagem);
            String valor = scanner.nextLine().trim();

            try {
                return Integer.parseInt(valor);
            } catch (NumberFormatException e) {
                System.out.println(
                    "Digite um numero inteiro valido."
                );
            }
        }
    }

    static LocalDate lerData(String mensagem) {

        while (true) {

            System.out.print(mensagem);
            String valor = scanner.nextLine().trim();

            try {
                return LocalDate.parse(valor);
            } catch (DateTimeParseException e) {
                System.out.println(
                    "Data invalida. Utilize AAAA-MM-DD."
                );
                return null;
            }
        }
    }
}
