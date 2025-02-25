package loja.jdbc;

import javax.swing.*;
import java.awt.Container;
import java.awt.FlowLayout;
import java.sql.*;

public class Dados extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection con;
	private Statement st;

	public Dados() throws SQLException{
		String driver = "org.postgresql.Driver";
		String sUsuario = "postgres";
		String sSenha = "Sungmin07#";
		String sFonte = "jdbc:postgresql://localhost:5432/postgres";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(sFonte, sUsuario, sSenha);
			JOptionPane.showMessageDialog(this,"Banco conectado com sucesso!","Mensagem", JOptionPane.WARNING_MESSAGE);
		}catch (SQLException eSQL) {
			// exceções de SQL
			eSQL.printStackTrace();
			JOptionPane.showMessageDialog(this,"Falha na conexão com o banco!\n" +"Mensagem: " + eSQL.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
		}catch (Exception e) {
			// demais exceções
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,"Falha na conexão com o banco!\n" +"Mensagem: " + e.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		try{
			//cria statement para consultar banco de dados
			st = con.createStatement();
			//Cadastro de valores pre-definidos
			st.executeUpdate ("INSERT INTO Loja (codProduto, produto) values (3, 'Macarrao')");
			
		}catch(SQLException eSQL){
			JOptionPane.showMessageDialog(this,"Erro na expressão do INSERT!\n" +"Mensagem: " + eSQL.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		
		try {
			st.close();
			con.close();
		}catch(Exception exception){
			exception.printStackTrace();
			System.exit(2);
		}
		
		Container P = getContentPane();
		P.setLayout(new FlowLayout());
		JLabel mensagem = new JLabel("Produto Inserido com Sucesso");
		P.add(mensagem);
	}
	
	public static void main(String args[]) throws SQLException{
		Dados ex = new Dados();
		ex.setDefaultCloseOperation(EXIT_ON_CLOSE);
		ex.setVisible(true);
		ex.setTitle("Dados Atualizados");
		ex.setSize(400,200);
	}
	
}