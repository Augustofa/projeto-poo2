/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package geral;

import Cadastros.*;
import POJOs.*;
import DAOs.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Augusto
 */
public class Tabela extends javax.swing.JFrame {

    public JFrame menuAnterior;
    DefaultTableModel modelo;
    int tipo;
    private final int[] quantColunas = {9, 9, 8};
    private final int[] colunaAtributoId = {5, 4, 7};
    /**
     * Creates new form TabelaPessoas
     * @param menuAnterior
     * @param tipo
     */
    // Tipos = {"Pacientes" , "Medicos"}
    public Tabela(JFrame menuAnterior, String tipo){
        initComponents();
        
        this.botaoVisualizar.setVisible(false);
        this.botaoAtualizar.setVisible(false);
        this.botaoExcluir.setVisible(false);
        this.separador.setVisible(false);
        this.menuAnterior = menuAnterior;
        this.modelo = (DefaultTableModel) this.tabela.getModel();
        this.tabela.setDefaultEditor(Object.class, null);
        
        switch(tipo){
            case "Pacientes" -> {
                this.tipo = 0;
                this.titulo.setText("Lista de Pacientes");
                this.modelo.addColumn("Nome");
                this.modelo.addColumn("Data de Nascimento");
                this.modelo.addColumn("Sexo");
                this.modelo.addColumn("Tipo Sanguíneo");
                this.modelo.addColumn("Endereço");
                this.modelo.addColumn("CPF");
                this.modelo.addColumn("Convênio");
                this.modelo.addColumn("Telefone");
                this.modelo.addColumn("Informações Adicionais");
            }
                
            case "Medicos" -> {
                this.tipo = 1;
                this.titulo.setText("Lista de Médicos");
                this.modelo.addColumn("Nome");
                this.modelo.addColumn("Telefone");
                this.modelo.addColumn("Endereço");
                this.modelo.addColumn("CPF/CNPJ");
                this.modelo.addColumn("CRM");
                this.modelo.addColumn("Email");
                this.modelo.addColumn("Convênios");
                this.modelo.addColumn("Especialidade");
                this.modelo.addColumn("Formações Adicionais");
            }
            
            case "Consultas" -> {
                this.tipo = 2;
                this.titulo.setText("Lista de Consultas");
                this.modelo.addColumn("Nome do Médico");
                this.modelo.addColumn("CRM");
                this.modelo.addColumn("Nome do Paciente");
                this.modelo.addColumn("CPF");
                this.modelo.addColumn("Data");
                this.modelo.addColumn("Horário");
                this.modelo.addColumn("Receitas");
                this.modelo.addColumn("ID da Consulta");
            }
        }
    }
    
    public void adicionaLinha(ArrayList<Object> listaDados){
        this.modelo.addRow(listaDados.toArray());
    }
    
    public void removeLinha(Object id){
        for(int i = 0; i < this.modelo.getRowCount(); i++){
            if(id.equals(this.tabela.getValueAt(i, colunaAtributoId[tipo]))){
                modelo.removeRow(i);
            }
        }
    }
    
    public void atualizaMenu(JFrame menuAnterior){
        this.menuAnterior = menuAnterior;
    }
    
    private ArrayList<Object> linhaToArray(int linha){
        ArrayList<Object> array = new ArrayList<>();
        for(int i = 0; i < quantColunas[tipo]; i++){
            array.add(this.tabela.getValueAt(linha, i));
        }
        return array;
    }
    
    private void visualizaRegistro(){
        int linha = this.tabela.getSelectedRow();
        TelaCadastro telaCadastro = null;
        switch(tipo){
            case 0 -> telaCadastro = new CadastroPaciente(this, new Paciente(linhaToArray(linha)));
            case 1 -> telaCadastro = new CadastroMedico(this, new Medico(linhaToArray(linha)));
            case 2 -> telaCadastro = new CadastroConsulta(this, new Consulta(linhaToArray(linha)));
        }
        if(telaCadastro != null){
            telaCadastro.modoVisualizacao();
            telaCadastro.setVisible(true);
        }
        this.setVisible(false);
    }
    
    private void atualizaRegistro(){
        int linha = this.tabela.getSelectedRow();
        switch(tipo){
            case 0 -> new CadastroPaciente(this, new Paciente(linhaToArray(linha))).setVisible(true);
            case 1 -> new CadastroMedico(this, new Medico(linhaToArray(linha))).setVisible(true);
            case 2 -> new CadastroConsulta(this, new Consulta(linhaToArray(linha))).setVisible(true);
        }
    }
    
    private void excluiRegistro(){
        int linha = this.tabela.getSelectedRow();
        switch(tipo){
            case 0 -> {
                DAOPaciente daoPaciente = new DAOPaciente();
                daoPaciente.removePaciente(tabela.getValueAt(linha, colunaAtributoId[tipo]).toString());
            }
            case 1 -> {
                DAOMedico daoMedico = new DAOMedico();
                daoMedico.removeMedico(tabela.getValueAt(linha, colunaAtributoId[tipo]).toString());
            }
            case 2 -> {
                DAOConsulta daoConsulta = new DAOConsulta();
                daoConsulta.removeConsulta((int) tabela.getValueAt(linha, colunaAtributoId[tipo]));
            }
        }
        modelo.removeRow(linha);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        titulo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        botaoAtualizar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoVisualizar = new javax.swing.JButton();
        botaoVoltar = new javax.swing.JButton();
        separador = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabela.setShowGrid(true);
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        titulo.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Título");

        jPanel1.setLayout(new java.awt.GridBagLayout());

        botaoAtualizar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        botaoAtualizar.setText("Atualizar");
        botaoAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAtualizarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 30);
        jPanel1.add(botaoAtualizar, gridBagConstraints);

        botaoExcluir.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        botaoExcluir.setText("Excluir");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 80);
        jPanel1.add(botaoExcluir, gridBagConstraints);

        botaoVisualizar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        botaoVisualizar.setText("Visualizar");
        botaoVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoVisualizarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(10, 60, 10, 30);
        jPanel1.add(botaoVisualizar, gridBagConstraints);

        botaoVoltar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        botaoVoltar.setText("Voltar");
        botaoVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoVoltarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(10, 40, 10, 30);
        jPanel1.add(botaoVoltar, gridBagConstraints);

        separador.setOrientation(javax.swing.SwingConstants.VERTICAL);
        separador.setMinimumSize(new java.awt.Dimension(5, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel1.add(separador, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        this.botaoVisualizar.setVisible(true);
        this.botaoAtualizar.setVisible(true);
        this.botaoExcluir.setVisible(true);
        this.separador.setVisible(true);
    }//GEN-LAST:event_tabelaMouseClicked

    private void botaoAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtualizarActionPerformed
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente atualizar esse registro?");
        if(resposta == 0){
            this.atualizaRegistro();
            this.dispose();
        }
    }//GEN-LAST:event_botaoAtualizarActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esse registro?");
        if(resposta == 0){
            this.excluiRegistro();
        }
    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void botaoVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoVisualizarActionPerformed
        visualizaRegistro();
    }//GEN-LAST:event_botaoVisualizarActionPerformed

    private void botaoVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoVoltarActionPerformed
        this.menuAnterior.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botaoVoltarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAtualizar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoVisualizar;
    private javax.swing.JButton botaoVoltar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator separador;
    private javax.swing.JTable tabela;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
