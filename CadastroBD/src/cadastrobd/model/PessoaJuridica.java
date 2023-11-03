package cadastrobd.model;

public class PessoaJuridica extends Pessoa {
    private String Cnpj;

    public String getCnpj() {
        return Cnpj;
    }

    public void setCnpj(String cnpj) {
        Cnpj = cnpj;
    }

    public PessoaJuridica() {
    }

    public PessoaJuridica(int idPessoas, String nome, String logradouro, String cidade, String estado, String telefone, String email, String Cnpj) {
        super(idPessoas, nome, logradouro, cidade, estado, telefone, email);
        this.Cnpj = Cnpj;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CNPJ: " + Cnpj);
    }
}