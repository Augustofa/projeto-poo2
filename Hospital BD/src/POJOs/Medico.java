/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJOs;

import java.util.ArrayList;

/**
 *
 * @author Augusto
 */
public class Medico {
    private String nome;
    private String telefone;
    private String endereco;
    private String numIdentificador;
    private String crm;
    private String email;
    private String convenios;
    private String especialidade;
    private String formacoes;
    
    public Medico(ArrayList dadosMedico){
        this.nome = dadosMedico.get(0).toString();
        this.telefone = dadosMedico.get(1).toString();
        this.endereco = dadosMedico.get(2).toString();
        this.numIdentificador = dadosMedico.get(3).toString();
        this.crm = dadosMedico.get(4).toString();
        this.email = dadosMedico.get(5).toString();
        this.convenios = dadosMedico.get(6).toString();
        this.especialidade = dadosMedico.get(7).toString();
        this.formacoes = dadosMedico.get(8).toString();  
    }
    
    public ArrayList<Object> toArrayObjects(){
        ArrayList<Object> listaObj = new ArrayList<>();
        
        listaObj.add(this.nome);
        listaObj.add(this.telefone);
        listaObj.add(this.endereco);
        listaObj.add(this.numIdentificador);
        listaObj.add(this.crm);
        listaObj.add(this.email);
        listaObj.add(this.convenios);
        listaObj.add(this.especialidade);
        listaObj.add(this.formacoes);
        
        return listaObj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumIdentificador() {
        return numIdentificador;
    }

    public void setNumIdentificador(String numIdentificador) {
        this.numIdentificador = numIdentificador;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConvenios() {
        return convenios;
    }

    public void setConvenios(String convenios) {
        this.convenios = convenios;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getFormacoes() {
        return formacoes;
    }

    public void setFormacoes(String formacoes) {
        this.formacoes = formacoes;
    }
    

}