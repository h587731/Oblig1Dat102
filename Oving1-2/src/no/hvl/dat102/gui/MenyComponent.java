package no.hvl.dat102.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenyComponent {

	// Generic
	private JFrame vindu;
	public JMenu meny;
	private JMenuItem menyElement;
	private JPanel panel;
	private JButton button;
	private JTextField textField;

	// text fields for skjema
	private JTextField tittel = new JTextField(10);
	private JTextField produsent = new JTextField(10);
	private JTextField selskap = new JTextField(10);
	private JTextField sjanger = new JTextField(10);
	private JTextField aar = new JTextField(10);
	private JTextField id = new JTextField(10);

	public MenyComponent(JFrame vindu, JMenuItem menyElement, JPanel panel, JButton button, JTextField textField) {

		this.vindu = vindu;
		this.menyElement = menyElement;
		this.panel = panel;
		this.button = button;
		this.textField = textField;

	}

	public MenyComponent() {

		this.vindu = null;
		this.menyElement = null;
		this.panel = null;
		this.button = null;
		this.textField = null;
	}

	public static void chooseMethod(Meny main, MenyComponent menyComponent, int x) {

		switch (x) {
		case 1:
			main.opprettArkiv(menyComponent.textField);

			break;
		case 2:
			main.openFil2(menyComponent.textField, main);
			main.openFile(menyComponent.textField, main);

			break;
		case 3:
			main.lagreTilFil();
			main.refresh();
			break;
		case 4:
			main.lagreSomTilFil(menyComponent.textField);// Lagre som System.out.println("Thursday");
			main.refresh();
			break;
		case 5:
			main.leggTilKlikk(menyComponent);// Legg til
			main.refresh();
			break;
		case 6:
			main.slettFilm(menyComponent.textField);
			main.refresh();
			break;
		case 7:
			main.sokTittel(menyComponent.textField);
			break;
		case 8:
			main.openHjelpKlikk();
			break;
		case 9:
			main.sokProd(menyComponent.textField);
			break;
		case 10:
			main.info();
			break;
		case 11:
			main.setTypeStruktur(true);
			main.startRefresh();
			// festet fil til menybar
			break;
		case 12:
			main.setTypeStruktur(false);
			main.startRefresh();
			break;

		}

		//
		menyComponent.vindu.setVisible(false); // fjerner / lukker vindu + setter tekst til blank.
		menyComponent.textField.setText("");
	}

	/**
	 * @param menu      menu object fra hoved vindu. Fil, arkiv eller hjelp
	 * @param main      referanse til hoved objectet, brukes og sendes videre til
	 *                  chooseMethod()
	 * @param menuItemS String navn til vindu og meny element
	 * @param buttonS   String navn til knapp
	 * @param metodeNR  brukt for å velge riktig metode til riktig knapp, sendes
	 *                  videre til choosemethod()
	 */
	public static void attachComponents(JMenu menu, Meny main, String menuItemS, String buttonS, int metodeNR) {

		// lager Popup vindu, menyelement (feks lagre som), panel, knapp og tekstfield
		// til popup vindu
		MenyComponent menyComposites = new MenyComponent(new JFrame(menuItemS), new JMenuItem(menuItemS), new JPanel(),
				new JButton(buttonS), new JTextField(10));

		menu.add(menyComposites.menyElement); // feste meny element til meny
		menyComposites.vindu.setSize(300, 80);
		menyComposites.panel.add(menyComposites.textField); // fester textfield og knapp til panel
		menyComposites.panel.add(menyComposites.button);
		menyComposites.vindu.getContentPane().add(BorderLayout.SOUTH, menyComposites.panel); // adder panel til vindu
		if (metodeNR == 5) {
			menyComposites.panel.remove(menyComposites.textField);
			menyComposites.panel.remove(menyComposites.button);

			menyComposites.leggTilSkjemaSetup();

		}

		// lager action listner for knapp, textfield( om man trykker enter, og meny
		// element( som åpner pop up)
		menyComposites.button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				menyComposites.vindu.setVisible(false); // fjerner / lukker vindu + setter tekst til blank.

				chooseMethod(main, menyComposites, metodeNR);
				menyComposites.textField.setText("");
			}

		});

		menyComposites.textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				menyComposites.vindu.setVisible(false); // fjerner / lukker vindu + setter tekst til blank.

				chooseMethod(main, menyComposites, metodeNR);
				menyComposites.textField.setText("");
			}
		});

		// åpner pop up vindu, setter frame til visable
		// men ikke for meny 7 3
		if (metodeNR != 8 && metodeNR != 3 && metodeNR != 10 && metodeNR != 11 && metodeNR != 12) {
			menyComposites.menyElement.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					menyComposites.vindu.setVisible(true);

				}
			});

		} else { //

			menyComposites.menyElement.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					chooseMethod(main, menyComposites, metodeNR);

				}
			});
		}

	}

	public void leggTilSkjemaSetup() {

		vindu.setSize(400, 250);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(4, 4, 4, 4);

		panel.add(new JLabel("Film tittel: "), gbc);
		gbc.gridy++;
		panel.add(new JLabel("Produsent: "), gbc);
		gbc.gridy++;
		panel.add(new JLabel("Selskap: "), gbc);
		gbc.gridy++;
		panel.add(new JLabel("Sjanger: "), gbc);
		gbc.gridy++;
		panel.add(new JLabel("År: "), gbc);
		gbc.gridy++;
		panel.add(new JLabel("Film ID: "), gbc);
		gbc.gridy++;
		gbc.anchor = GridBagConstraints.NORTHWEST;

		gbc.gridx++;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;

		panel.add(tittel, gbc);
		gbc.gridy++;
		panel.add(produsent, gbc);
		gbc.gridy++;
		// gbc.weighty = 1;
		panel.add(selskap, gbc);
		gbc.gridy++;
		panel.add(sjanger, gbc);
		gbc.gridy++;
		panel.add(aar, gbc);
		gbc.gridy++;
		panel.add(id, gbc);
		gbc.gridy++;

		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 4));
		buttons.add(button);
		// buttons.add(lukkButton);

		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weighty = 0;

		panel.add(buttons, gbc);

	}

	// Metode som sletter tekst og skrive git gud
	public static void help(Meny main) {
		main.getMainVinduTA().setText("");
		main.getMainVinduTA().append("git gud");
	}

	public JTextField getTittel() {
		return tittel;
	}

	public JTextField getProdusent() {
		return produsent;
	}

	public JTextField getSelskap() {
		return selskap;
	}

	public JTextField getSjanger() {
		return sjanger;
	}

	public JTextField getAar() {
		return aar;
	}

	public JTextField getId() {

		return id;
	}

}
