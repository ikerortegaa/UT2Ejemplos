package com.iob;

import java.sql.*;

public class ej2ConexionGrafica extends javax.swing.JFrame {
    
    /** Creates new form DialogoConexion */
    public ej2ConexionGrafica() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldUsuario = new javax.swing.JTextField();
        jTextFieldContrasenya = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldURL = new javax.swing.JTextField();
        jButtonConexion = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDetalle = new javax.swing.JTextArea();

        getContentPane().setLayout(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Conexi\u00f3n");
        setName("DialogoConexion");
        jLabel1.setText("Usuario");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 100, 14);

        jLabel2.setText("Contrase\u00f1a");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(230, 10, 100, 14);

        jTextFieldUsuario.setName("jTextField_Usuario");
        getContentPane().add(jTextFieldUsuario);
        jTextFieldUsuario.setBounds(10, 50, 130, 20);

        getContentPane().add(jTextFieldContrasenya);
        jTextFieldContrasenya.setBounds(230, 50, 130, 20);

        jLabel3.setText("URL de JDBC");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(130, 90, 100, 14);

        jTextFieldURL.setText("jdbc:postgresql://");
        getContentPane().add(jTextFieldURL);
        jTextFieldURL.setBounds(10, 110, 350, 20);

        jButtonConexion.setText("Realizar Conexi\u00f3n");
        jButtonConexion.setToolTipText("Formulario de conexi\u00f3n ");
        jButtonConexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConexionActionPerformed(evt);
            }
        });

        getContentPane().add(jButtonConexion);
        jButtonConexion.setBounds(100, 150, 140, 23);

        jScrollPane1.setViewportView(jTextAreaDetalle);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 180, 350, 100);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConexionActionPerformed
// TODO add your handling code here:

        //Obtener de los jTextFields los datos
        String usuario= jTextFieldUsuario.getText();
        String pass= jTextFieldContrasenya.getText();
        String jdbcUrl= jTextFieldURL.getText();
        //Mostramos en jTextAreaDetalle el informe

        // jTextAreaDetalle.append("Driver registrado\n");

        //Conectando con la Base de Datos

        jTextAreaDetalle.append("Conectando con la base de datos...\n");
        try (Connection conexion = DriverManager.getConnection(jdbcUrl, usuario, pass);)
        {
            //Obtenemos la conexi�n
            jTextAreaDetalle.append("Conexión establecida con la base de datos...\n");
        } catch(SQLException se) {
            //Errores de JDBC
            se.printStackTrace();
            jTextAreaDetalle.append(se.getMessage());
        } catch(Exception e) {
            //Errores Class.forName
            e.printStackTrace();
            jTextAreaDetalle.append(e.getMessage());
        }
        }

    //GEN-LAST:event_jButtonConexionActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ej2ConexionGrafica d = new ej2ConexionGrafica();
                d.setSize(400,400);
                d.setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConexion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaDetalle;
    private javax.swing.JTextField jTextFieldContrasenya;
    private javax.swing.JTextField jTextFieldURL;
    private javax.swing.JTextField jTextFieldUsuario;
    // End of variables declaration//GEN-END:variables
    
}
