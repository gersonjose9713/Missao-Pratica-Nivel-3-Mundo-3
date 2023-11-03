package cadastrobd.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {
    private Connection connection;

    public PessoaJuridicaDAO(Connection connection) {
        this.connection = connection;
    }

    public PessoaJuridica getPessoaJuridica(int id) throws SQLException {
        String sql = "SELECT pj.PessoaID, pj.Cnpj, p.Nome, p.Logradouro, p.Cidade, p.Estado, p.Telefone, p.Email " +
                "FROM PessoaJuridica pj " +
                "INNER JOIN Pessoas p ON pj.PessoaID = p.ID" +
                "WHERE pj.PessoaID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int pessoaId = resultSet.getInt("PessoaID");
                    String cnpj = resultSet.getString("CNPJ");
                    String nome = resultSet.getString("Nome");
                    String logradouro = resultSet.getString("Logradouro");
                    String cidade = resultSet.getString("Cidade");
                    String estado = resultSet.getString("Estado");
                    String telefone = resultSet.getString("Telefone");
                    String email = resultSet.getString("Email");

                    return new PessoaJuridica(pessoaId, nome, logradouro, cidade, estado, telefone, email, cnpj);
                }
            }
        }
        return null;
    }

    public List<PessoaJuridica> getPessoasJuridicas() throws SQLException {
        List<PessoaJuridica> pessoas = new ArrayList<>();
        String sql = "SELECT pj.PessoaID, pj.Cnpj, p.Nome, p.Logradouro, p.Cidade, p.Estado, p.Telefone, p.Email " +
                "FROM PessoaJuridica pj " +
                "INNER JOIN Pessoas p ON pj.PessoaID = p.ID";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int pessoaId = resultSet.getInt("PessoaID");
                String cnpj = resultSet.getString("CNPJ");
                String nome = resultSet.getString("Nome");
                String logradouro = resultSet.getString("Logradouro");
                String cidade = resultSet.getString("Cidade");
                String estado = resultSet.getString("Estado");
                String telefone = resultSet.getString("Telefone");
                String email = resultSet.getString("Email");

                pessoas.add(new PessoaJuridica(pessoaId, nome, logradouro, cidade, estado, telefone, email, cnpj));
            }
        }
        return pessoas;
    }

    public void incluir(PessoaJuridica pessoa) throws SQLException {
        String sqlInserirPessoas = "INSERT INTO Pessoas (Nome, Logradouro, Cidade, Estado, Telefone, Email) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInserirPessoas, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setString(2, pessoa.getLogradouro());
            preparedStatement.setString(3, pessoa.getCidade());
            preparedStatement.setString(4, pessoa.getEstado());
            preparedStatement.setString(5, pessoa.getTelefone());
            preparedStatement.setString(6, pessoa.getEmail());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int pessoaId = generatedKeys.getInt(1);

                    String sqlInserirPessoaJuridica = "INSERT INTO PessoaJuridica (PessoaID, CNPJ) VALUES (?, ?)";
                    try (PreparedStatement psPessoaJuridica = connection.prepareStatement(sqlInserirPessoaJuridica)) {
                        psPessoaJuridica.setInt(1, pessoaId);
                        psPessoaJuridica.setString(2, pessoa.getCnpj());
                        psPessoaJuridica.executeUpdate();
                    }
                }
            }
        }
    }

    public void alterar(PessoaJuridica pessoa) throws SQLException {
        String atualizarPessoas = "UPDATE Pessoas SET Nome = ?, Logradouro = ?, Cidade = ?, Estado = ?, Telefone = ?, Email= ? WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(atualizarPessoas)) {
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setString(2, pessoa.getLogradouro());
            preparedStatement.setString(3, pessoa.getCidade());
            preparedStatement.setString(4, pessoa.getEstado());
            preparedStatement.setString(5, pessoa.getTelefone());
            preparedStatement.setString(6, pessoa.getEmail());
            preparedStatement.executeUpdate();
        }

        String atualizarPessoaJuridica = "UPDATE PessoaJuridica SET CNPJ= ? WHERE PessoaID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(atualizarPessoaJuridica)) {
            preparedStatement.setString(1, pessoa.getCnpj());
            preparedStatement.setInt(2, pessoa.getIdPessoa());
            preparedStatement.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String excluirPessoaJuridica = "DELETE FROM PessoaJuridica WHERE PessoaID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(excluirPessoaJuridica)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }

        String excluirPessoas = "DELETE FROM Pessoas WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(excluirPessoas)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}