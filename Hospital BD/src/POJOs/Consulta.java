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
public class Consulta{
    private String nomeMedico;
    private String crmMedico;
    private String nomePaciente;
    private String cpfPaciente;
    private String data;
    private String horario;
    private String receitas;
    private int id;
    
    public Consulta(ArrayList<Object> dados){
        this.nomeMedico = dados.get(0).toString();
        this.crmMedico = dados.get(1).toString();
        this.nomePaciente = dados.get(2).toString();
        this.cpfPaciente = dados.get(3).toString();
        this.data = dados.get(4).toString();
        this.horario = dados.get(5).toString();
        this.receitas = dados.get(6).toString();
        if(dados.size() == 8){
            this.id = (int) dados.get(7);
        }
    }

    public ArrayList<Object> toArrayObjects(){
        ArrayList<Object> listaObj = new ArrayList<>();
        
        listaObj.add(this.nomeMedico);
        listaObj.add(this.crmMedico);
        listaObj.add(this.nomePaciente);
        listaObj.add(this.cpfPaciente);
        listaObj.add(this.data);
        listaObj.add(this.horario);
        listaObj.add(this.receitas);
        listaObj.add(this.id);
        
        return listaObj;
    }
    
    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getCrmMedico() {
        return crmMedico;
    }

    public void setCrmMedico(String crmMedico) {
        this.crmMedico = crmMedico;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getReceitas() {
        return receitas;
    }

    public void setReceitas(String receitas) {
        this.receitas = receitas;
    }  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
