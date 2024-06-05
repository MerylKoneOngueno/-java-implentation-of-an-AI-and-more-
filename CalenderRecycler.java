package newPizzaOng;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
/*
 Es werden alle Jahre x angezeigt ,in denen alle Wochentage auf die selben Daten fallen, wie in dem Jahr y das eingegeben wurde. 
 Es werden nur Jahre angezeigt die größer sind als y
 
  
  Beispiel:
  wenn meine eingabe 1789 ist, dann wird mir zb das Jahr 1795 angezeigt. Dh 08.12.1795 hat den selben Wochentag wie der 08.12.1789. 
 ->Dies gilt für alle Daten der beiden Jahre
*/
public class CalenderRecycler {

	private JFrame frame;
	private JTextField textField;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalenderRecycler window = new CalenderRecycler();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CalenderRecycler() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 516, 235);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(3, 3));
		
		JLabel yearOld = new JLabel("Year of old Calendar");
		frame.getContentPane().add(yearOld, BorderLayout.WEST);
		
		JLabel ausgabe = new JLabel("New label");
		frame.getContentPane().add(ausgabe, BorderLayout.SOUTH);
		ausgabe.setPreferredSize(new Dimension(200,100));
		textField = new JTextField();
		frame.getContentPane().add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		JButton go = new JButton("GO");
		go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int buttonEingabe= Integer.parseInt(textField.getText());
				
				ausgabe.setText(calCheck(buttonEingabe)+" ");
			}
		});
		frame.getContentPane().add(go, BorderLayout.EAST);
	}
	
	private static List<LocalDate> calCheck(int eingabeJahr) {
		//Calendar cal = Calendar.getInstance();
		LocalDate startDatum=  LocalDate.of(eingabeJahr,01,01);
		LocalDate schaltCheck=  LocalDate.of(2020,01,01);
		LocalDate currentDate= LocalDate.now();
		
		
		List<LocalDate> datenInRange   = new ArrayList<>();
		List<LocalDate> datenGefiltert = new ArrayList<>();
		List<LocalDate> ergebnisListe  = new ArrayList<>();

		
		for(int i =eingabeJahr+1;i<=currentDate.getYear(); i++) {
			datenInRange.add(LocalDate.of(i, 01, 01));
		}


		datenGefiltert= datenInRange.stream()
						.filter((a)-> a.getDayOfWeek().equals(startDatum.getDayOfWeek()))
						.toList();
		if(startDatum.lengthOfYear()!= schaltCheck.lengthOfYear()) {
		ergebnisListe=datenGefiltert.stream()
					  .filter((b)-> b.lengthOfYear()!=schaltCheck.lengthOfYear())
					  .toList();
		}
		else {
			ergebnisListe=datenGefiltert.stream()
					  .filter((b)-> b.lengthOfYear()==schaltCheck.lengthOfYear())
					  .toList();
		}
	
		
		return ergebnisListe;
	}

}
