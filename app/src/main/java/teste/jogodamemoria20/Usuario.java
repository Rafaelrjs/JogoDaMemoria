package teste.jogodamemoria20;

/**
 * Created by renato on 19/08/16.
 */
public class Usuario {
    private String nome;
    private int tentativas;

    public Usuario() {
        this.nome = "";
        this.tentativas = 0;
    }

    public Usuario(String nome, int tentativas) {
        this.nome = nome;
        this.tentativas = tentativas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTentativas() {
        return tentativas;
    }

    public void setTentativas(int tentativas) {
        this.tentativas = tentativas;
    }
}
