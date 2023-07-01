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
public class Paciente {
    private String nome;
    private String dataNascimento;
    private char sexo;
    private String tipoSanguineo;
    private String endereco;
    private String cpf;
    private String convenio;
    private String telefone;
    private String historico;
    
    public Paciente(ArrayList<Object> dadosPaciente){
        this.nome = dadosPaciente.get(0).toString();
        this.dataNascimento = dadosPaciente.get(1).toString();
        this.sexo = dadosPaciente.get(2).toString().charAt(0);
        this.tipoSanguineo = dadosPaciente.get(3).toString();
        this.endereco = dadosPaciente.get(4).toString();
        this.cpf = dadosPaciente.get(5).toString();
        this.convenio = dadosPaciente.get(6).toString();
        this.telefone = dadosPaciente.get(7).toString();
        this.historico = dadosPaciente.get(8).toString();
        
    }
    
    public ArrayList<Object> toArrayObjects(){
        ArrayList<Object> listaObj = new ArrayList<>();
        
        listaObj.add(this.nome);
        listaObj.add(this.dataNascimento);
        listaObj.add(this.sexo);
        listaObj.add(this.tipoSanguineo);
        listaObj.add(this.endereco);
        listaObj.add(this.cpf);
        listaObj.add(this.convenio);
        listaObj.add(this.telefone);
        listaObj.add(this.historico);
        
        return listaObj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }
    
    
}
