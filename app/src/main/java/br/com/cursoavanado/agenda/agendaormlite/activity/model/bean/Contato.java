package br.com.cursoavanado.agenda.agendaormlite.activity.model.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by lucas on 26/12/14.
 */
@DatabaseTable
public class Contato {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String nome;

    @DatabaseField
    private String email;

    @DatabaseField
    private String telefone;

    @Override
    public String toString() {
        return nome +"[" + telefone + "]";
    }



    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contato contato = (Contato) o;

        if (id != contato.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
