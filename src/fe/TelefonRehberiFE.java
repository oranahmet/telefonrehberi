package fe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import contract.Person;
import dal.PersonDAL;
import helper.Helper;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelefonRehberiFE extends JFrame {

	private JPanel contentPane;
	private JTable tblRehber;
	private JTextField txtAra;
	private JTextField txtAd;
	private JTextField txtSoyad;
	private JTextField txtTel;
	private JTextField txtEmail;
	private JTextArea txtAdres;
	private DefaultTableModel personModel;
	
	private Object[] personData;
    PersonDAL personDal=new PersonDAL();
    private JTextField txtId;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelefonRehberiFE frame = new TelefonRehberiFE();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelefonRehberiFE() {
		
		personModel=new DefaultTableModel();
		Object[] colPerson=new Object[6];
		colPerson[0]="ID";
		colPerson[1]="Ad";
		colPerson[2]="Soyad";
		colPerson[3]="Tel";
		colPerson[4]="Email";
		colPerson[5]="Adres";
		personModel.setColumnIdentifiers(colPerson);
		listAll();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 879, 515);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		tblRehber = new JTable(personModel);
		
		tblRehber.setBounds(420, 329, 315, 133);
//		contentPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 95, 420, 271);
		scrollPane.setViewportView(tblRehber);
		contentPane.add(scrollPane);
		
		txtAra = new JTextField();
		
		txtAra.setBounds(67, 64, 379, 20);
		contentPane.add(txtAra);
		txtAra.setColumns(10);
		
		JLabel lblArama = new JLabel("Arama");
		lblArama.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblArama.setBounds(11, 67, 46, 14);
		contentPane.add(lblArama);
		
		JLabel lblAd = new JLabel("Ad");
		lblAd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAd.setBounds(508, 67, 37, 14);
		contentPane.add(lblAd);
		
		txtAd = new JTextField();
		txtAd.setBounds(582, 64, 182, 20);
		contentPane.add(txtAd);
		txtAd.setColumns(10);
		
		JLabel lblSoyad = new JLabel("Soyad");
		lblSoyad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSoyad.setBounds(508, 88, 64, 27);
		contentPane.add(lblSoyad);
		
		txtSoyad = new JTextField();
		txtSoyad.setColumns(10);
		txtSoyad.setBounds(582, 95, 182, 20);
		contentPane.add(txtSoyad);
		
		txtTel = new JTextField();
		txtTel.setColumns(10);
		txtTel.setBounds(582, 127, 182, 20);
		contentPane.add(txtTel);
		
		JLabel lblTel = new JLabel("Tel");
		lblTel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTel.setBounds(508, 120, 64, 27);
		contentPane.add(lblTel);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(508, 153, 64, 27);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(582, 160, 182, 20);
		contentPane.add(txtEmail);
		
		JLabel lblAdres = new JLabel("Adres");
		lblAdres.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdres.setBounds(508, 191, 64, 27);
		contentPane.add(lblAdres);
		
		txtAdres = new JTextArea();
		txtAdres.setBounds(582, 199, 182, 99);
		contentPane.add(txtAdres);
		
		JButton btnEkle = new JButton("Ekle");
		btnEkle.setBounds(456, 332, 89, 48);
		contentPane.add(btnEkle);
		
		JButton btnSil = new JButton("Sil");
		
		btnSil.setBounds(551, 332, 89, 48);
		contentPane.add(btnSil);
		
		JButton btnGuncelle = new JButton("Guncelle");
		btnGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGuncelle.setBounds(650, 332, 89, 48);
		contentPane.add(btnGuncelle);
		
		JButton btnTemizle = new JButton("Temizle");
		btnTemizle.setBounds(749, 332, 89, 48);
		contentPane.add(btnTemizle);
		
		txtId = new JTextField();
		txtId.setBounds(540, 414, 96, 19);
		txtId.setVisible(false);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		
		txtAra.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
            List<Person> personList;
            String ara=txtAra.getText();
            if (!ara.equals("")) {
                try {
                    personList=personDal.getBySearch(ara);
                    personModel.setRowCount(0);
                    personData=new Object[6];
                    for (int i = 0; i < personList.size(); i++) {
                        personData[0]=personList.get(i).getId();
                        personData[1]=personList.get(i).getAd();
                        personData[2]=personList.get(i).getSoyad();
                        personData[3]=personList.get(i).getTel();
                        personData[4]=personList.get(i).getEmail();
                        personData[5]=personList.get(i).getAdres();
                        personModel.addRow(personData);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            }
        });
		
		btnGuncelle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!txtId.getText().isEmpty()) {
                    int id = Integer.parseInt(txtId.getText());
                    Person person = new Person(id, txtAd.getText(), txtSoyad.getText(), txtTel.getText(),
                            txtEmail.getText(), txtAdres.getText());
                    
                    try {
						boolean control=personDal.update(person);
						if (control) {
                            Helper.showMsg("success");
                            listAll();
                            temizle();
                        } else {
                            Helper.showMsg("error");
                        }
					} catch (Exception e2) {
						// TODO: handle exception
					}
                    
                } else {
                    Helper.showMsg("Lutfen bir kisi seciniz");
                }

            }
        });

        btnSil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Helper.confirm("sure")) {
                    if (!txtId.getText().isEmpty()) {
                        int id = Integer.parseInt(txtId.getText());
                        try {
                            boolean control = personDal.delete(id);
                            if (control) {
                                Helper.showMsg("success");
                                listAll();
                                temizle();
                            } else {
                                Helper.showMsg("error");
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    } else {
                        Helper.showMsg("Lutfen bir kisi seciniz");
                    }

                }

            }
        });
		tblRehber.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  txtId.setText(personModel.getValueAt(tblRehber.getSelectedRow(), 0).toString());
	                txtAd.setText(personModel.getValueAt(tblRehber.getSelectedRow(), 1).toString());
	                txtSoyad.setText(personModel.getValueAt(tblRehber.getSelectedRow(), 2).toString());
	                txtTel.setText(personModel.getValueAt(tblRehber.getSelectedRow(), 3).toString());
	                txtEmail.setText(personModel.getValueAt(tblRehber.getSelectedRow(), 4).toString());
	                txtAdres.setText(personModel.getValueAt(tblRehber.getSelectedRow(), 5).toString());
				
			}
		});
		
		
		btnTemizle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			temizle();	
			}
		});
		
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Person person =new Person(txtAd.getText(), txtSoyad.getText(), txtTel.getText(),txtEmail.getText(),txtAdres.getText());
				try {
					boolean control=personDal.insert(person);
					if(control) {
						Helper.showMsg("success");
						listAll();
						temizle();
						
					}else {
						Helper.showMsg("error");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void listAll() {
		personModel.setRowCount(0);
		personData=new Object[6];
		
		try {
			for (int i = 0; i <personDal.getAll().size(); i++) {
				personData[0]=personDal.getAll().get(i).getId();
				personData[1]=personDal.getAll().get(i).getAd();
				personData[2]=personDal.getAll().get(i).getSoyad();
				personData[3]=personDal.getAll().get(i).getTel();
				personData[4]=personDal.getAll().get(i).getEmail();
				personData[5]=personDal.getAll().get(i).getAdres();
				personModel.addRow(personData);
//				System.out.println(personData[0]);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void temizle() {
		txtAd.setText("");
		txtSoyad.setText("");
		txtTel.setText("");
		txtEmail.setText("");
		txtAdres.setText("");
		txtId.setText("");
		
	}
}