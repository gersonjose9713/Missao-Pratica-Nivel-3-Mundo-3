package cadastrobd.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {
    private Connection connection;

    public PessoaFisicaDAO(Connection connection) {
        this.connection = connection;
    }

    public PessoaFisica getPessoa(int id) throws SQLException {
        String sql = "SELECT pf.PessoaID, pf.CPF, p.Nome, p.Logradouro, p.Cidade, p.Estado, p.Telefone, p.Email " +
                "FROM PessoaFisica pf " +
                "INNER JOIN Pessoas p ON pf.PessoaID = p.ID " +
                "WHERE pf.PessoaID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int pessoaId = resultSet.getInt("PessoaID");
                    String cpf = resultSet.getString("CPF");
                    String nome = resultSet.getString("Nome");
                    String logradouro = resultSet.getString("Logradouro");
                    String cidade = resultSet.getString("Cidade");
                    String estado = resultSet.getString("Estado");
                    String telefone = resultSet.getString("Telefone");
                    String email = resultSet.getString("Email");

                    return new PessoaFisica(pessoaId, nome, logradouro, cidade, estado, telefone, email, cpf);
                }
            }
        }
        return null;
    }

    public List<PessoaFisica> getPessoas() throws SQLException {
        List<PessoaFisica> pessoas = new ArrayList<>();
        String sql = "SELECT pf.PessoaID, pf.CPF, p.Nome, p.Logradouro, p.Cidade, p.Estado, p.Telefone, p.Email " +
                "FROM PessoaFisica pf " +
                "INNER JOIN Pessoas p ON pf.PessoaID = p.ID";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int pessoaId = resultSet.getInt("PessoaID");
                String cpf = resultSet.getString("CPF");
                String nome = resultSet.getString("Nome");
                String logradouro = resultSet.getString("Logradouro");
                String cidade = resultSet.getString("Cidade");
                String estado = resultSet.getString("Estado");
                String telefone = resultSet.getString("Telefone");
                String email = resultSet.getString("Email");

                pessoas.add(new PessoaFisica(pessoaId, nome, logradouro, cidade, estado, telefone, email, cpf));
            }
        }
        return pessoas;
    }

    public void incluir(PessoaFisica pessoa) throws SQLException {
        String inserirPessoa = "INSERT INTO Pessoas (Nome, Logradouro, Cidade, Estado, Telefone, Email) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(inserirPessoa, PreparedStatement.RETURN_GENERATED_KEYS)) {
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

                    String inserirPessoaFisica = "INSERT INTO PessoaFisica (PessoaID, CPF) VALUES (?, ?)";
                    try (PreparedStatement psPessoaFisica = connection.prepareStatement(inserirPessoaFisica)) {
                        psPessoaFisica.setInt(1, pessoaId);
                        psPessoaFisica.setString(2, pessoa.getCpf());
                        psPessoaFisica.executeUpdate();
                    }
                }
            }
        }
    }

    public void alterar(PessoaFisica pessoa) throws SQLException {
        String atualizarPessoa = "UPDATE Pessoas SET Nome = ?, Logradouro = ?, Cidade = ?, Estado = ?, Telefone = ?, Email= ? WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(atualizarPessoa)) {
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setString(2, pessoa.getLogradouro());
            preparedStatement.setString(3, pessoa.getCidade());
            preparedStatement.setString(4, pessoa.getEstado());
            preparedStatement.setString(5, pessoa.getTelefone());
            preparedStatement.setString(6, pessoa.getEmail());
            preparedStatement.setInt(7, pessoa.getIdPessoa());
            preparedStatement.executeUpdate();
        }

        String atualizarPessoaFisica = "UPDATE PessoaFisica SET CPF = ? WHERE PessoaID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(atualizarPessoaFisica)) {
            preparedStatement.setString(1, pessoa.getCpf());
            preparedStatement.setInt(2, pessoa.getIdPessoa());
            preparedStatement.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String excluirPessoaFisica = "DELETE FROM PessoaFisica WHERE PessoaID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(excluirPessoaFisica)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }

        String excluirPessoa = "DELETE FROM Pessoas WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(excluirPessoa)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}