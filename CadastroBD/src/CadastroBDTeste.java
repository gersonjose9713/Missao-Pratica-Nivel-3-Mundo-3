import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastro.model.util.ConectorBD;
import cadastrobd.model.PessoaJuridicaDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CadastroBDTeste {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = ConectorBD.getConnection();
            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(connection);
            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO(connection);

            PessoaFisica novaPessoaFisica = new PessoaFisica(0,"João", "Rua Ísis", "Cidade de Deus", "AM", "9236062646", "João@123", "71394575653");
            pessoaFisicaDAO.incluir(novaPessoaFisica);
            System.out.println("Pessoa física incluída com sucesso.");

            int idPessoaFisicaAlterar = 4;
            PessoaFisica pessoaFisicaAlterar = pessoaFisicaDAO.getPessoa(idPessoaFisicaAlterar);
            if (pessoaFisicaAlterar != null) {
                pessoaFisicaAlterar.setNome("Erick");
                pessoaFisicaAlterar.setLogradouro("Rua Doutor Vieira");
                pessoaFisicaAlterar.setCidade("Manaus");
                pessoaFisicaAlterar.setEstado("AM");
                pessoaFisicaAlterar.setTelefone("9226801129");
                pessoaFisicaAlterar.setEmail("erick@123.com.br");
                pessoaFisicaAlterar.setCpf("64373442100");
                pessoaFisicaDAO.alterar(pessoaFisicaAlterar);
                System.out.println("Pessoa física alterada com sucesso.");
            } else {
                System.out.println("Pessoa física não encontrada para alteração.");
            }

            List<PessoaFisica> todasPessoasFisicas = pessoaFisicaDAO.getPessoas();
            System.out.println("Todas as pessoas físicas:");
            for (PessoaFisica pessoa : todasPessoasFisicas) {
                pessoa.exibir();
            }

            int idPessoaFisicaExcluir = 1;
            pessoaFisicaDAO.excluir(idPessoaFisicaExcluir);
            System.out.println("Pessoa física excluída com sucesso.");

            PessoaJuridica novaPessoaJuridica = new PessoaJuridica(0,"Pietra e Andreia Telas ME", "Rua Oswaldo de Souza", "São Vicente", "SP", "1328489102", "seguranca@pietraeandreiatelasme.com.br", "87272284000153");
            pessoaJuridicaDAO.incluir(novaPessoaJuridica);
            System.out.println("Pessoa jurídica incluída com sucesso.");

            int idPessoaJuridicaAlterar = 1;
            PessoaJuridica pessoaJuridicaAlterar = pessoaJuridicaDAO.getPessoaJuridica(idPessoaJuridicaAlterar);
            if (pessoaJuridicaAlterar != null) {
                pessoaJuridicaAlterar.setNome("Rodrigo e Ian Restaurante Ltda");
                pessoaJuridicaAlterar.setLogradouro("Rua Serra da Leoa");
                pessoaJuridicaAlterar.setCidade("Praia Grande");
                pessoaJuridicaAlterar.setEstado("SP");
                pessoaJuridicaAlterar.setTelefone("1335650444");
                pessoaJuridicaAlterar.setEmail("contabil@rodrigoeianrestauranteltda.com.br");
                pessoaJuridicaAlterar.setCnpj("18739827000187");
                pessoaJuridicaDAO.alterar(pessoaJuridicaAlterar);
                System.out.println("Pessoa jurídica alterada com sucesso.");
            } else {
                System.out.println("Pessoa jurídica não encontrada para alteração.");
            }

            List<PessoaJuridica> todasPessoasJuridicas = pessoaJuridicaDAO.getPessoasJuridicas();
            System.out.println("Todas as pessoas jurídicas:");
            for (PessoaJuridica pessoa : todasPessoasJuridicas) {
                pessoa.exibir();
            }

            int idPessoaJuridicaExcluir = 1;
            pessoaJuridicaDAO.excluir(idPessoaJuridicaExcluir);
            System.out.println("Pessoa jurídica excluída com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(connection);
        }
    }
}