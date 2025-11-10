package com.Feira_On_Line.Feira.Model;

import jakarta.persistence.*;

@Entity // Marca esta classe como uma tabela no banco
@Table(name = "feiras") // Nome da tabela
public class Feira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome; // Ex: "Feira da Vila Operária"

    @Column(nullable = false)
    private String diaDaSemana; // Ex: "Domingo"

    @Column(nullable = false)
    private String horarioInicio; // Ex: "08:00"

    @Column(nullable = false)
    private String horarioFim; // Ex: "12:00"

    @Column(nullable = false)
    private String localizacao; // Ex: "Praça da Igreja"

    private String endereco; // Ex: "Av. Brasil, 1234"

    private String bairro; // Ex: "Zona 03"

    // Getters e Setters (O Spring precisa deles)
    // <...> (Você pode gerar via IDE ou adicionar manualmente)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(String horarioFim) {
        this.horarioFim = horarioFim;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}