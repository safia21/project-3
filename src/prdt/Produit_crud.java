package prdt;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class Produit_crud extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connectage cn=new Connectage();
	Statement st;
	ResultSet rst;
	JTable tb1;
	JScrollPane scrl1;
	JLabel lbtitre, lbcode,lbnom,lbprix,lbtitre2,lbcode2,lbnombre,lbtitre3,lbdervente_id,lbvente_id,lbnom2,lbquantite;
	JTextField tfcode,tfnom,tfprix,tfnombre,tfvente_id,tfquantite,tfcode3;
    JButton bt_add,bt_sup,bt_sup2,bt_sup3,bt_add2,bt_add3,bt_vente;
    JComboBox<String> combonom2;
   
	public Produit_crud(){
		this.setTitle("chcode_appli");
		this.setSize(485,600);
		this.setLocationRelativeTo(null);
		JPanel pn=new JPanel();
		pn.setLayout(null);
		pn.setBackground(new Color(153,153,153));
		add(pn);
		//titre
		lbtitre=new JLabel("Enregistrement des produits du supermarch� ");
		lbtitre.setBounds(30,10,800,30);
		lbtitre.setFont(new Font("Arial",Font.BOLD,18));
		pn.add(lbtitre);
		
		
		//nom produit
				lbnom=new JLabel("Nom produit : ");
				lbnom.setBounds(35,80,120,25);
				lbnom.setFont(new Font("Arial",Font.BOLD,15));
				pn.add(lbnom);
				
				tfnom=new JTextField();
				tfnom.setBounds(150,80,100,25);
				pn.add(tfnom);
		//nom produit
				lbprix=new JLabel("Prix produit ($) : ");
				lbprix.setBounds(17,110,120,25);
				lbprix.setFont(new Font("Arial",Font.BOLD,15));
				pn.add(lbprix);
				
				tfprix=new JTextField();
				tfprix.setBounds(150,110,100,25);
				pn.add(tfprix);
		//boutons
				bt_add=new JButton("Enregistrer");
				bt_add.setBounds(30,160,100,25);
				bt_add.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String a,b,c,d,f;
						//a=tfcode.getText();
						b=tfnom.getText();
						c=tfprix.getText();
						
				String sq="insert into produit(nomprod,prix,qtedispo) values('"+b+"','"+c+"',0) ";
				  try{
					  st=cn.laconnexion().createStatement();
					  if(JOptionPane.showConfirmDialog(null,"Voulez-vous ajoutez ?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
						   st.executeUpdate(sq);
						   JOptionPane.showMessageDialog(null,"Insertion reussie !");
						   dispose();
						   Produit_crud pr=new Produit_crud();
						   pr.setVisible(true);
					  }
				  }
				  catch(SQLException ex){
					  JOptionPane.showMessageDialog(null," Echec Insertion !",null,JOptionPane.ERROR_MESSAGE);
					  
				  }
						
					}

					
				});
				pn.add(bt_add);
				//supprimer
				bt_sup=new JButton("Supprimer");
				bt_sup.setBounds(160,160,100,25);
				bt_sup.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String a,b,c,d,f;
						//a=tfcode.getText();
						b=tfnom.getText();
						/*c=tfprix.getText();*/
						
				String sq="delete from produit where nomprod='"+b+"'";
				  try{
					  st=cn.laconnexion().createStatement();
					  if(JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer ?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
						   st.executeUpdate(sq);
						   JOptionPane.showMessageDialog(null,"Suppr�ssion r�ussie !");
						   dispose();
						   Produit_crud pr=new Produit_crud();
						   pr.setVisible(true);
					  }
				  }
				  catch(SQLException ex){
					  JOptionPane.showMessageDialog(null," Impossible de supprimer !",null,JOptionPane.ERROR_MESSAGE);
					  
				  }
						
					}

					
				});
				pn.add(bt_sup);
		
		///
				DefaultTableModel df=new DefaultTableModel();
				init();
				
				df.addColumn("Nom produit ");
				df.addColumn("Prix produit ($)");
				df.addColumn("Quantit� disponible");
				tb1.setModel(df);
				pn.add(scrl1);
				String sql="select nomprod,prix,qtedispo from produit";
			 cn=new Connectage();
				try{
					st=cn.laconnexion().createStatement();
					rst=st.executeQuery(sql);
					while(rst.next()){
				df.addRow(new Object[]{
						
						rst.getString("nomprod"),
						rst.getString("prix"),
						rst.getString("qtedispo")
						
				});
					}
				}
				catch(SQLException ex){
					
				}
				//titre2
				lbtitre2=new JLabel("Ajouter la quantit� d'un produit ");
				lbtitre2.setBounds(30,380,300,30);
				lbtitre2.setFont(new Font("Arial",Font.BOLD,18));
				pn.add(lbtitre2);
				
				//code produit
				lbnom2=new JLabel("Nom produit : ");
				lbnom2.setBounds(40,420,120,25);
				lbnom2.setFont(new Font("Arial",Font.BOLD,15));
				pn.add(lbnom2);

				
				combonom2 =new JComboBox();
				combonom2.setBounds(170,420,120,25);
				//ajout de la liste des produit au combonom2.
						String kk4="select nomprod from produit";
				try{
					st=cn.laconnexion().createStatement();
					rst=st.executeQuery(kk4);
					while(rst.next()){
					combonom2.addItem(rst.getString("nomprod"));
			
					}
				}
				catch(SQLException ex){
					
				}
				pn.add(combonom2);
				
				//nombre
				lbnombre=new JLabel("Nombre : ");
				lbnombre.setBounds(50,450,120,25);
				lbnombre.setFont(new Font("Arial",Font.BOLD,15));
				pn.add(lbnombre);
				
				tfnombre=new JTextField();
				tfnombre.setBounds(170,450,100,25);
				pn.add(tfnombre);
				//bouton
				bt_add2=new JButton("Ajouter");
				bt_add2.setBounds(50,490,100,25);
				bt_add2.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String a,b;
						a=combonom2.getSelectedItem().toString();
						b=tfnombre.getText();
						
				String sq="update produit set qtedispo=qtedispo+'"+b+"' where nomprod='"+a+"'";
				  try{
					  st=cn.laconnexion().createStatement();
					  if(JOptionPane.showConfirmDialog(null,"Voulez-vous ajoutez cette quantit�?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
						   st.executeUpdate(sq);
						   JOptionPane.showMessageDialog(null,"Ajout reussie !");
						   dispose();
						   Produit_crud pr=new Produit_crud();
						   pr.setVisible(true);
					  }
				  }
				  catch(SQLException ex){
					  JOptionPane.showMessageDialog(null," Echec ajout !",null,JOptionPane.ERROR_MESSAGE);
					  
				  }
						
					}

					
				});
				pn.add(bt_add2);
				//btsuppression produit ajout�
				bt_sup2=new JButton("Retirer");
				bt_sup2.setBounds(170,490,100,25);
				bt_sup2.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String a,b,c,d,f;
						a=combonom2.getSelectedItem().toString();
						b=tfnombre.getText();
						
				String sq="update produit set qtedispo=qtedispo -'"+b+"'  where nomprod='"+a+"'";
				  try{
					  st=cn.laconnexion().createStatement();
					  if(JOptionPane.showConfirmDialog(null,"Voulez-vous retirer cette quantit� ?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
						   st.executeUpdate(sq);
						   JOptionPane.showMessageDialog(null,"Retrait r�ussie !");
						   dispose();
						   Produit_crud pr=new Produit_crud();
						   pr.setVisible(true);
					  }
				  }
				  catch(SQLException ex){
					  JOptionPane.showMessageDialog(null," Echec retrait !",null,JOptionPane.ERROR_MESSAGE);
					  
				  }
						
					}

					
				});
				pn.add(bt_sup2);
				//bouton vente
				bt_vente=new JButton("Vente");
				bt_vente.setBounds(300,490,100,25);
				bt_vente.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						dispose();
						Vente vt=new Vente();
						vt.setVisible(true);
						
					}

					
				});
				pn.add(bt_vente);
				
				
					
	   ///
	}
	private void init(){
		tb1=new JTable();
		scrl1=new JScrollPane();
		scrl1.setViewportView(tb1);
		scrl1.setBounds(10,200,450,160);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Produit_crud prd = new Produit_crud();
		prd.setVisible(true);

	}

}
