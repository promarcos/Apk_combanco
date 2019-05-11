package coneccao;

public class Livro {
    private String Nome;
    private String Autor;
    private Integer Ano;
    private Integer Id;

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public Integer getAno() {
        return Ano;
    }

    public void setAno(Integer ano) {
        Ano = ano;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Livro(String nome, String autor, Integer ano, Integer id) {
        Nome = nome;
        Autor = autor;
        Ano = ano;
        Id = id;
    }

    public Livro() {

    }
}
