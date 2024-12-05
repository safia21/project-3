package prdt;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Requetes extends JFrame {
	
	private static final long serialVersionUID = 1L;
	Connectage cn = new Connectage();
	Statement st;
	ResultSet rst;
	JTable tb1;
	JScrollPane scrl1;
	JLabel lbtitre, lbdate, lbtvente;
	JTextField tfdate;
	JButton btdate, btv;

	public Requetes() {
		this.setTitle("chcode_appli");
		this.setSize(485, 600);
		this.setLocationRelativeTo(null);
		JPanel pn = new JPanel();
		pn.setLayout(null);
		pn.setBackground(new Color(153, 153, 153));
		add(pn);

		lbtitre = new JLabel("Montant total des ventes par produit par jour");
		lbtitre.setBounds(20, 10, 800, 30);
		lbtitre.setFont(new Font("Arial", Font.BOLD, 18));
		pn.add(lbtitre);

		lbdate = new JLabel("Date : ");
		lbdate.setBounds(40, 60, 100, 30);
		lbdate.setFont(new Font("Arial", Font.BOLD, 15));
		pn.add(lbdate);

		tfdate = new JTextField(" aaaa-mm-jj");
		tfdate.setBounds(90, 60, 100, 25);
		pn.add(tfdate);

		lbtvente = new JLabel();
		lbtvente.setBounds(40, 500, 200, 30);
		lbtvente.setFont(new Font("Arial", Font.BOLD, 18));
		lbtvente.setForeground(new Color(200, 100, 100));
		pn.add(lbtvente);

		btv = new JButton("Vente");
		btv.setBounds(240, 500, 100, 25);
		btv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Vente vt = new Vente();
				vt.setVisible(true);
			}
		});
		pn.add(btv);

		btdate = new JButton("OK");
		btdate.setBounds(196, 60, 70, 25);
		btdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel df = new DefaultTableModel();
				init();

				df.addColumn("Nom produit ");
				df.addColumn("Montant vente ($)");
				df.addColumn("DATE");
				tb1.setModel(df);
				pn.add(scrl1);
				String sql = "select produit,sum(montant) as tmontant,datev from som where datev='"
						+ tfdate.getText() + "' group by produit ";
				cn = new Connectage();
				try {
					st = cn.laconnexion().createStatement();
					rst = st.executeQuery(sql);
					while (rst.next()) {
						df.addRow(new Object[] { rst.getString("produit"), rst.getString("tmontant"),
								rst.getString("datev") });
					}
				} catch (SQLException ex) {

				}

				String sql2 = "select sum(montant) as mt from som where datev='" + tfdate.getText() + "'";
				// totalvente
				try {
					st = cn.laconnexion().createStatement();
					rst = st.executeQuery(sql2);
					if (rst.next()) {
						String mt = rst.getString("mt");
						lbtvente.setText("Total vente = " + mt + " $");
					}
				} catch (SQLException ex) {

				}
			}
		});
		pn.add(btdate);

	}

	private void init() {
		tb1 = new JTable();
		scrl1 = new JScrollPane();
		scrl1.setViewportView(tb1);
		scrl1.setBounds(10, 100, 450, 390);
	}

	public static void main(String[] args) {
		Requetes rq = new Requetes();
		rq.setVisible(true);
	}

}
