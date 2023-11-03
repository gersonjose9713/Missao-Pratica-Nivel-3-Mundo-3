package cadastrobd.model;

public class Pessoa {
    private int idPessoa;
    private String Nome;
    private String Logradouro;
    private String Cidade;
    private String Estado;
    private String Telefone;
    private String Email;

    public int getIdPessoa() {
        return idPessoa;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getLogradouro() {
        return Logradouro;
    }

    public void setLogradouro(String logradouro) {
        Logradouro = logradouro;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Pessoa() {
    }


    public Pessoa(int idPessoa, String Nome, String Logradouro, String Cidade, String Estado, String Telefone, String Email) {
        this.idPessoa = idPessoa;
        this.Nome = Nome;
        this.Logradouro = Logradouro;
        this.Cidade = Cidade;
        this.Estado = Estado;
        this.Telefone = Telefone;
        this.Email = Email;
    }

    public void exibir() {
        System.out.println("ID: " + idPessoa);
        System.out.println("Nome: " + Nome);
        System.out.println("Logradouro: " + Logradouro);
        System.out.println("Cidade: " + Cidade);
        System.out.println("Estado: " + Estado);
        System.out.println("Telefone: " + Telefone);
        System.out.println("Email: " + Email);
    }
}