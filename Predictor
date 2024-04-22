import javax.swing.*;
import java.util.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;

// can only be executed in intelliJ
// What this code does: the user is able to click the a or b button on the GUI. The program predicts the next user input. 

public class Predictor extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton buttonA;
    private JButton buttonB;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private StringBuilder predictionString = new StringBuilder();
    private Timer timer = new Timer();
    StringBuilder baby = new StringBuilder();

    public Predictor() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("Predict Me");
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        //h
        //t

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            //key events: bei a, A, oder click auf A auslösen
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_TYPED) {
                    char keyChar = e.getKeyChar();
                    if (keyChar == 'a' || keyChar == 'A') {
                        buttonA.doClick();
                    } else if (keyChar == 'b' || keyChar == 'B') {
                        buttonB.doClick();
                    }
                }
                return false;
            }
        });


        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (buttonOK.getText().equals("Start")) {
                    startTimer();
                    buttonOK.setText("Stop");
                } else {
                    stopTimer();
                    buttonOK.setText("Start");
                }
            }
        });


        buttonA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(buttonOK.getText().equals("Stop")){
                    //eingabestring printen, timer checken
                    stopTimer();
                    baby.append("A");
                    textField1.setText(baby.toString());

                    if(textField2.getText().length() < 9) {
                        predictionString.append("A");
                        textField2.setText(predictionString.toString());
                    } else {
                        //bis zu 9 nur kopieren um was für Vorhersage zu haben, danach vorhersagen
                        predictAndCompare();
                    }
                    startTimer();

                }}
        });

        buttonB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(buttonOK.getText().equals("Stop")){
                    baby.append("B");
                    textField1.setText(baby.toString());

                    if(textField2.getText().length() < 9) {
                        predictionString.append("B");
                        textField2.setText(predictionString.toString());
                    } else {
                        predictAndCompare();
                    }
                    stopTimer();
                    startTimer();
                }}
        });

    }
    //in anderer Methode gelöst
    /*
    private void processInput(Character input) {
        //h
        //t
    }

    private Character computePrediction(String text1) {
        //h
        //t
        return null;
    }
    */


    private String cutoff(String s, int i) {
        if (s.length() < i) {
            return s;
        }
        return s.substring(s.length() - i);
    }


    private void onOK() {
        //h
        //t
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Predictor dialog = new Predictor();
                dialog.pack();
                dialog.setVisible(true);
                System.exit(0);
            }
        });
    }


    private void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!buttonA.isSelected() && !buttonB.isSelected()) {
                    JOptionPane.showMessageDialog(Predictor.this, "Du bist ein Verlierer!");
                }
            }
        }, 2500);
    }

    private void stopTimer() {
        timer.cancel();
    }

    void predictAndCompare() {
        //bei jedem Aufruf checken ob String zu groß
        cutoff(predictionString.toString(), 100);
        //wenn die Sequenze gefunden wurde, wie oft ist darauf ein A, wie oft ein B gefolgt?
        int occurenceA = 0;
        int occurenceB = 0;

        char[] input = textField1.getText().toCharArray();
        String finde = "";
        //nach 5er-Sequenzen suchen
        int sequenceLength = 5;
        finde = new String(input, input.length - 5, 5);

        for (int i = 0; i < input.length - 10; i++) {
            String suche = new String(input, i, 5);
            if (suche.equals(finde)) {
                //wenn Sequenz gefunden, und es folgt A
                if (input[i + 4] == 'A') {
                    occurenceA++;
                    //es folgt B
                } else {
                    occurenceB++;
                }
                break;
            }
        }
        //wenn öfter B folgt, dann ist die Vorhersage B
        if (occurenceA < occurenceB) {

            predictionString.append("B");
            textField2.setText(predictionString.toString());


        } else if (occurenceA > occurenceB) {
            //sonst A
            predictionString.append("A");
            textField2.setText(predictionString.toString());

            //wenn occurences gleich sind: auf das Muster (z.B.) AAAAA folgt 5x A, 5x B, dann...
        } else {
            int newOccA =0;
            int newOccB =0;
            //such nach kleineren Sequenzen, gleiche Logik
            for(int i= input.length-9;i<input.length;i++){
                if(input[i]=='A'){
                    newOccA++;
                }else{
                    newOccB++;
                }
            }
            //theoretisch, für mehr Präzision, hätte man das für immer kleinere Sequenzen machen können...
            if(newOccA>newOccB){
                predictionString.append("A");
            }else {
                predictionString.append("B");
            }

            textField2.setText(predictionString.toString());

        }

        String stringQuote= "";

        String eingabeString= textField1.getText();
        String vorhersageString= textField2.getText();
        int wasCorrect=0;
        int wasWrong=0;

        for(int i=0; i<eingabeString.length();i++){
            //wenn Eingabe = Vorhersage an einer bestimmten Stelle, dann...
            if(eingabeString.charAt(i)==vorhersageString.charAt(i)){
                //waren wir korrekt
                stringQuote+= "=";
                wasCorrect++;
                textField3.setText(stringQuote);
            } else {
                //sonst nicht korrekt
                stringQuote+="!";
                wasWrong++;
                textField3.setText(stringQuote);
            }
            //Quote ist: wie oft waren wir korrekt (/) (GETEILT DURCH) wie viele Inputs gab es insgesamt
            double quote = Double.valueOf(wasCorrect)/ Double.valueOf(eingabeString.length());
            textField4.setText(quote*100+"%");
        }
    }

}

// ____________________________________IntelliJ Gui______________________________________________________________

<form version="1" bind-to-class="Predictor">
<grid id="cbd77" binding="contentPane" layout-manager="GridLayoutManager" row-count="3" column-count="2" same-size-horizontally="false" same-size-vertically="false" hgap="-1" vgap="-1">
<margin top="10" left="10" bottom="10" right="10"/>
<constraints>
<xy x="48" y="54" width="640" height="480"/>
</constraints>
<properties>
<maximumSize width="640" height="320"/>
<minimumSize width="640" height="320"/>
<preferredSize width="640" height="320"/>
</properties>
<border type="none"/>
<children>
<grid id="e3588" layout-manager="GridBagLayout">
<constraints>
<grid row="0" column="0" row-span="1" col-span="2" vsize-policy="3" hsize-policy="3" anchor="0" fill="3" indent="0" use-parent-layout="false"/>
</constraints>
<properties/>
<border type="none"/>
<children>
<component id="105b5" class="javax.swing.JButton" binding="buttonA">
<constraints>
<grid row="0" column="0" row-span="1" col-span="1" vsize-policy="0" hsize-policy="3" anchor="0" fill="3" indent="0" use-parent-layout="false"/>
<gridbag weightx="1.0" weighty="1.0"/>
</constraints>
<properties>
<text value="A"/>
</properties>
</component>
<component id="fe96f" class="javax.swing.JButton" binding="buttonB">
<constraints>
<grid row="0" column="1" row-span="1" col-span="1" vsize-policy="0" hsize-policy="3" anchor="0" fill="3" indent="0" use-parent-layout="false"/>
<gridbag weightx="1.0" weighty="1.0"/>
</constraints>
<properties>
<text value="B"/>
</properties>
</component>
</children>
</grid>
<grid id="28ee5" layout-manager="GridBagLayout">
<constraints>
<grid row="1" column="0" row-span="1" col-span="2" vsize-policy="1" hsize-policy="3" anchor="0" fill="1" indent="0" use-parent-layout="false"/>
</constraints>
<properties/>
<border type="none"/>
<children>
<component id="fba76" class="javax.swing.JLabel">
<constraints>
<grid row="0" column="0" row-span="1" col-span="1" vsize-policy="0" hsize-policy="0" anchor="8" fill="0" indent="0" use-parent-layout="false"/>
<gridbag weightx="0.0" weighty="0.0"/>
</constraints>
<properties>
<text value="Eingabe"/>
</properties>
</component>
<component id="e7eba" class="javax.swing.JTextField" binding="textField1">
<constraints>
<grid row="0" column="1" row-span="1" col-span="1" vsize-policy="0" hsize-policy="6" anchor="8" fill="1" indent="0" use-parent-layout="false">
<preferred-size width="150" height="-1"/>
</grid>
<gridbag weightx="1.0" weighty="0.0"/>
</constraints>
<properties>
<editable value="false"/>
<font name="Courier New"/>
<minimumSize width="49" height="30"/>
<preferredSize width="49" height="30"/>
<text value=""/>
</properties>
</component>
<component id="1b974" class="javax.swing.JLabel">
<constraints>
<grid row="1" column="0" row-span="1" col-span="1" vsize-policy="0" hsize-policy="0" anchor="8" fill="0" indent="0" use-parent-layout="false"/>
<gridbag weightx="0.0" weighty="0.0"/>
</constraints>
<properties>
<text value="Vorhersage"/>
</properties>
</component>
<component id="149a8" class="javax.swing.JTextField" binding="textField2">
<constraints>
<grid row="1" column="1" row-span="1" col-span="1" vsize-policy="0" hsize-policy="6" anchor="8" fill="1" indent="0" use-parent-layout="false">
<preferred-size width="150" height="-1"/>
</grid>
<gridbag weightx="1.0" weighty="0.0"/>
</constraints>
<properties>
<editable value="false"/>
<font name="Courier New"/>
<minimumSize width="49" height="30"/>
<preferredSize width="49" height="30"/>
</properties>
</component>
<component id="a04b3" class="javax.swing.JLabel">
<constraints>
<grid row="2" column="0" row-span="1" col-span="1" vsize-policy="0" hsize-policy="0" anchor="8" fill="0" indent="0" use-parent-layout="false"/>
<gridbag weightx="0.0" weighty="0.0"/>
</constraints>
<properties>
<text value="Ergebnis"/>
</properties>
</component>
<component id="b2b5b" class="javax.swing.JTextField" binding="textField3">
<constraints>
<grid row="2" column="1" row-span="1" col-span="1" vsize-policy="0" hsize-policy="6" anchor="8" fill="1" indent="0" use-parent-layout="false">
<preferred-size width="150" height="-1"/>
</grid>
<gridbag weightx="0.0" weighty="0.0"/>
</constraints>
<properties>
<editable value="false"/>
<font name="Courier New"/>
<minimumSize width="49" height="30"/>
<preferredSize width="49" height="30"/>
</properties>
</component>
<component id="ac07c" class="javax.swing.JLabel">
<constraints>
<grid row="3" column="0" row-span="1" col-span="1" vsize-policy="0" hsize-policy="0" anchor="8" fill="0" indent="0" use-parent-layout="false"/>
<gridbag weightx="0.0" weighty="0.0"/>
</constraints>
<properties>
<font/>
<requestFocusEnabled value="false"/>
<text value="Quote"/>
</properties>
</component>
<component id="1bdef" class="javax.swing.JTextField" binding="textField4">
<constraints>
<grid row="3" column="1" row-span="1" col-span="1" vsize-policy="0" hsize-policy="6" anchor="8" fill="1" indent="0" use-parent-layout="false">
<preferred-size width="150" height="-1"/>
</grid>
<gridbag weightx="0.0" weighty="0.0"/>
</constraints>
<properties>
<editable value="false"/>
<font name="Courier New"/>
<minimumSize width="49" height="30"/>
<preferredSize width="49" height="30"/>
</properties>
</component>
</children>
</grid>
<grid id="94766" layout-manager="GridLayoutManager" row-count="1" column-count="1" same-size-horizontally="false" same-size-vertically="false" hgap="-1" vgap="-1">
<margin top="0" left="0" bottom="0" right="0"/>
<constraints>
<grid row="2" column="0" row-span="1" col-span="2" vsize-policy="1" hsize-policy="3" anchor="0" fill="3" indent="0" use-parent-layout="false"/>
</constraints>
<properties/>
<border type="none"/>
<children>
<grid id="9538f" layout-manager="GridLayoutManager" row-count="1" column-count="3" same-size-horizontally="false" same-size-vertically="false" hgap="-1" vgap="-1">
<margin top="0" left="0" bottom="0" right="0"/>
<constraints>
<grid row="0" column="0" row-span="1" col-span="1" vsize-policy="3" hsize-policy="3" anchor="0" fill="3" indent="0" use-parent-layout="false"/>
</constraints>
<properties/>
<border type="none"/>
<children>
<component id="e7465" class="javax.swing.JButton" binding="buttonOK">
<constraints>
<grid row="0" column="0" row-span="1" col-span="1" vsize-policy="0" hsize-policy="3" anchor="0" fill="1" indent="0" use-parent-layout="false"/>
</constraints>
<properties>
<text value="Start"/>
</properties>
</component>
<component id="5723f" class="javax.swing.JButton" binding="buttonCancel">
</component>
<hspacer id="98af6">
<constraints>
<grid row="0" column="2" row-span="1" col-span="1" vsize-policy="1" hsize-policy="6" anchor="0" fill="1" indent="0" use-parent-layout="false"/>
</constraints>
</hspacer>
</children>
</grid>
</children>
</grid>
</children>
</grid>
</form> 
