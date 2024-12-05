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

public class Vente extends JFrame {
	Connectage cn=new Connectage();
	Statement st;
	ResultSet rst;
	JTable tb1;
	JScrollPane scrl1;
	JLabel lbtitre3,lbtitre4,lbtitre5,lbdervente_id,lbidv2,lbvente_id,lbnom2,lbquantite,lbtotal;
	JTextField tfnombre,tfvente_id,tfquantite,tfcode3,tfidv2;
	JButton bt_sup3,bt_add2,bt_add3,bt_prod,bt_totalv,breq;
	 JComboBox combonom2;
 public Vente(){
	 this.setTitle("chcode_appli");
		this.setSize(900,600);
		this.setLocationRelativeTo(null);
		JPanel pn=new JPanel();
		pn.setLayout(null);
		pn.setBackground(new Color(153,153,153));
		add(pn);
		
		//titre3
		lbtitre3=new JLabel("Enregistrement d'une vente");
		lbtitre3.setBounds(30,10,300,30);
		lbtitre3.setFont(new Font("Arial",Font.BOLD,18));
		pn.add(lbtitre3);
		
//vente id
		lbvente_id=new JLabel("ID vente");
		lbvente_id.setBounds(80,80,100,25);
		lbvente_id.setFont(new Font("Arial",Font.BOLD,15));
		pn.add(lbvente_id);
		
		
		
		tfvente_id=new JTextField();
		tfvente_id.setBounds(150,80,100,25);
		pn.add(tfvente_id);
//nomproduit vendu
		lbnom2=new JLabel("Nom produit");
		lbnom2.setBounds(45,110,100,25);
		lbnom2.setFont(new Font("Arial",Font.BOLD,15));
		pn.add(lbnom2);
		
		combonom2 =new JComboBox();
		combonom2.setBounds(150,110,120,25);
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
		
//Qte vendue
	    lbquantite=new JLabel("Quantite ");
	    lbquantite.setBounds(70,140,120,25);
	    lbquantite.setFont(new Font("Arial",Font.BOLD,15));
		pn.add(lbquantite);
	
		tfquantite=new JTextField();
		tfquantite.setBounds(150,140,100,25);
		pn.add(tfquantite);
//bouton enregistrer vente
		bt_add3=new JButton("Enregistrer");
		bt_add3.setBounds(40,180,100,25);
		bt_add3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String a,b,c,d,f;
				
				a=tfvente_id.getText();
				b=combonom2.getSelectedItem().toString();
				c=tfquantite.getText();
				//ajout vente
		String sq="insert into vente(idv,produit,quantite,datev) values('"+a+"','"+b+"','"+c+"',NOW())";
		 //reduction automatique du nombre d'un produit lors d'une vente:
		String sq2="update produit set qtedispo=qtedispo-'"+c+"' where nomprod='"+b+"'"; 
		try{
			  st=cn.laconnexion().createStatement();
			  if(JOptionPane.showConfirmDialog(null,"Voulez-vous enregistrer cette vente?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
				   st.executeUpdate(sq);
				   st.executeUpdate(sq2);
				   JOptionPane.showMessageDialog(null,"Vente enregistr�e !");
				   dispose();
				   Vente sl=new Vente();
				   sl.setVisible(true);
			  }
		  }
		  catch(SQLException ex){
			  JOptionPane.showMessageDialog(null," Echec ajout !",null,JOptionPane.ERROR_MESSAGE);
			  
		  }
				
			}

			
		});
		pn.add(bt_add3);
//bouton supprimer vente				
		bt_sup3=new JButton("Supprimer");
		bt_sup3.setBounds(150,180,100,25);
		bt_sup3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String a,b,c,d,f;
				
				a=tfvente_id.getText();
				b=combonom2.getSelectedItem().toString();
				//c=tfquantite.getText();
			//"insert into vente values('$idv','$code_pr','$qte_pr',now())"	
		String sq="delete from vente where idv='"+a+"' and produit='"+b+"'";
		  try{
			  st=cn.laconnexion().createStatement();
			  if(JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer cette vente?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
				   st.executeUpdate(sq);
				   JOptionPane.showMessageDialog(null,"Suppr�ssion reussie !");
				   dispose();
				   Vente sl=new Vente();
				   sl.setVisible(true);
			  }
		  }
		  catch(SQLException ex){
			  JOptionPane.showMessageDialog(null," Echec suppr�ssion !",null,JOptionPane.ERROR_MESSAGE);
			  
		  }
				
			}

			
		});
		pn.add(bt_sup3);
		//retour au formulaire produit
		bt_prod=new JButton("Produits");
		bt_prod.setBounds(320,180,100,25);
		bt_prod.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				Produit_crud pr=new Produit_crud();
				pr.setVisible(true);
				
			}

			
		});
		pn.add(bt_prod);
		//bouton requete
		//retour au formulaire produit
				breq=new JButton("Requetes globales");
				breq.setBounds(430,180,200,25);
				breq.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						dispose();
						Requetes rq=new Requetes();
						rq.setVisible(true);
						
					}

					
				});
				pn.add(breq);
//liste des ventes du jour
		lbtitre4=new JLabel("Liste des ventes ");
		lbtitre4.setBounds(30,230,300,30);
		lbtitre4.setFont(new Font("Arial",Font.BOLD,18));
		pn.add(lbtitre4);
		
		DefaultTableModel df=new DefaultTableModel();
		init();
		df.addColumn("ID VENTE");
		df.addColumn("NOM PRODUIT");
		df.addColumn("QUANTITE");
		df.addColumn("PRIX UNITAIRE ($)");
		df.addColumn("MONTANT ($)");
		df.addColumn("DATE");
		tb1.setModel(df);
		pn.add(scrl1);
		//String sql="select * from vente where datediff(datev,now())=0";
		String sql="select idv,produit,quantite,prix,quantite*prix"
		+ " as montant,datev from produit inner join vente on produit.nomprod=vente.produit order by idv desc";
		
	 cn=new Connectage();
		try{
			st=cn.laconnexion().createStatement();
			rst=st.executeQuery(sql);
			while(rst.next()){
		df.addRow(new Object[]{
				rst.getString("idv"),
				rst.getString("produit"),
				rst.getString("quantite"),
				rst.getString("prix"),
				rst.getString("montant"),
				rst.getString("datev")
				
		});
			}
		}
		catch(SQLException ex){
			
		}
	//totaliser les ventes
		lbtitre5=new JLabel("Montant total des produits d'une vente");
		lbtitre5.setBounds(520,10,360,30);
		lbtitre5.setFont(new Font("Arial",Font.BOLD,18));
		pn.add(lbtitre5);
		
		lbidv2=new JLabel("ID Vente");
		lbidv2.setBounds(540,60,110,24);
		lbidv2.setFont(new Font("Arial",Font.BOLD,15));
		pn.add(lbidv2);
		
		tfidv2=new JTextField();
		tfidv2.setBounds(620,60,110,24);
		pn.add(tfidv2);
		
		lbtotal=new JLabel();
		lbtotal.setBounds(610,120,310,24);
		lbtotal.setFont(new Font("Arial",Font.BOLD,19));
		lbtotal.setForeground(new Color(200,100,100));
		pn.add(lbtotal);
		
		bt_totalv=new JButton("OK");
		bt_totalv.setBounds(740,60,70,24);
		bt_totalv.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String a,b,c,d,f;
				
				a=tfidv2.getText();
				
				String sq="select sum(montant) as total from som where idv='"+a+"' group by idv";
		  try{
			  st=cn.laconnexion().createStatement();
			  rst=st.executeQuery(sq);
			  if(rst.next()){
				  String mtotal=rst.getString("total");
				  lbtotal.setText("TOTAL = "+mtotal+" $");
			  }
			  
		  }
		  catch(SQLException ex){
			  
			  
		  }
				
			}

			
		});
		pn.add(bt_totalv);
		
		
 }
 private void init(){
		tb1=new JTable();
		scrl1=new JScrollPane();
		scrl1.setViewportView(tb1);
		scrl1.setBounds(20,270,847,270);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
     Vente vt=new Vente();
     vt.setVisible(true);
	}

}
