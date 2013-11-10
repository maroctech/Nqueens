package nqueensbeta;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

//essai
public class Fenetre extends JFrame{
    //panel
    private JPanel p;
    private JScrollPane scroll;
    private Panneau hold;
    private Tables t;
    Fenetre() throws InterruptedException {
         //Composants du Menu
    JMenuBar menu = new JMenuBar();
    JMenu m = new JMenu("Opération");
    JMenu aide = new JMenu("Aide");
    JMenu md = new JMenu("Mode");
    final JMenu al = new JMenu("Algorithmes");
    JMenuItem n = new JMenuItem("Nouveau");
    JMenuItem q = new JMenuItem("Quitter");
    JMenuItem a = new JMenuItem("A propos");
    JMenuItem me=new JMenuItem("Manuel d'utilisation");
    final JRadioButtonMenuItem algo = new JRadioButtonMenuItem("Algorithmes", true);
    final JRadioButtonMenuItem jeu = new JRadioButtonMenuItem("Jeu", false);
    final JRadioButtonMenuItem comp = new JRadioButtonMenuItem("Compraison", false);
    ButtonGroup gr2 = new ButtonGroup();
    final JRadioButtonMenuItem aleat = new JRadioButtonMenuItem("Exhaustive", true);
    final JRadioButtonMenuItem puraleat = new JRadioButtonMenuItem("Pure Aléatoire", false);
    final JRadioButtonMenuItem amelior = new JRadioButtonMenuItem("Amélioration itérative", false);
    final JRadioButtonMenuItem rel=new JRadioButtonMenuItem("La Relance",false);
    final JRadioButtonMenuItem recuit = new JRadioButtonMenuItem("Recuit Simulé", false);
    final JRadioButtonMenuItem tabou = new JRadioButtonMenuItem("Recherche Tabou", false);
    ButtonGroup gr = new ButtonGroup();
    ///////////////////////////////////////////////////////////
        this.p = new JPanel(new GridBagLayout());
        setTitle("NQueens Algorithms");
        setSize(600, 600);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Menu////////////////
        q.setMnemonic(KeyEvent.VK_Q);
        n.setMnemonic(KeyEvent.VK_N);
        a.setMnemonic(KeyEvent.VK_A);
        m.setMnemonic(KeyEvent.VK_O);
        al.setMnemonic(KeyEvent.VK_L);
        me.setMnemonic('M');
        algo.setMnemonic('A');
        jeu.setMnemonic('J');
        md.setMnemonic('M');
        md.add(algo);
        md.add(jeu);
        md.add(comp);
        gr2.add(comp);
        comp.setMnemonic('C');
        gr2.add(algo);
        gr2.add(jeu);
        aleat.setMnemonic('E');
        rel.setMnemonic('L');
        puraleat.setMnemonic('P');
        amelior.setMnemonic('M');
        aide.setMnemonic(KeyEvent.VK_A);
        recuit.setMnemonic('R');
        tabou.setMnemonic('T');
        gr.add(aleat);
        gr.add(puraleat);
        gr.add(amelior);
        gr.add(rel);
        gr.add(recuit);
        gr.add(tabou);
        al.add(aleat);
        al.add(puraleat);
        al.add(amelior);
        al.add(rel);
        al.add(recuit);
        al.add(tabou);
        m.add(n);
        m.add(q);
        aide.add(me);
        aide.add(a);
        menu.add(m);
        menu.add(md);
        menu.add(al);
        menu.add(aide);
        setJMenuBar(menu);
     /////////////////////////////////////////////////////////////////////////
        comp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                al.setEnabled(false);
            }
        });
        
        
        jeu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                al.setEnabled(false);
            }
        });

        algo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                al.setEnabled(true);
            }
        });
        /////////////////////

        /////
        File fich = new File("about.html");
        final JEditorPane j = new JEditorPane();
        try {
            j.setPage(fich.toURI().toURL());
        } catch (IOException ex) {
            Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
        }
        j.setEditable(false);
        /////

        //ajout des composants de l'ecran d'accueil
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel t = new JLabel("<html><h1><font color='green'/>NQueens Algorithms</h1></html>");
        JLabel e = new JLabel("\n\n");
        JLabel bn=new JLabel("<html><h2><font color='green'/>Bienvenue</h2></html>");
        ImageIcon img = new ImageIcon("new.png");
        JButton imag = new JButton(img);
        imag.setPreferredSize(new Dimension(180, 180));
        gbc.gridx = gbc.gridy = 0;
        p.add(t, gbc);
        gbc.gridy++;
        p.add(e, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(8, 8, 8, 8);
        p.add(imag, gbc);
        gbc.gridy++;
        p.add(bn,gbc);
        add(p);

        //les Actions sur les différents composants du menu
        //À propos:
        a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JComponent[] input = {j};
                JOptionPane.showMessageDialog(null, input, "À propos", JOptionPane.PLAIN_MESSAGE);
            }
        });
        //fin À propos
        
        //Manuel d'utilisation
        me.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                File fich=new File("Manuel.html");
                try {
                    Desktop.getDesktop().open(fich);
                } catch (IOException ex) {
                    System.out.println("le fichier n'existe pas");
                }
            }
        });
        //fin Manuel d'utilisation
        //Quitter
        q.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        //Quitter

        //Nouveau
        n.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (algo.isSelected()) {
                    if (aleat.isSelected()) {
                        actionaleat();
                    }
                    if (puraleat.isSelected()) {
                        actionpuraleat();
                    }
                    if (amelior.isSelected()) {
                        actionamelior();
                    }
                    if (recuit.isSelected()) {
                        recuit();
                    }
                    
                    if(rel.isSelected()){relance();}
                    
                    if (tabou.isSelected()) {
                        tabou();
                    }
                }
                if (jeu.isSelected()) {
                    nqueenjeu();
                }
                
                if(comp.isSelected()){
                    comparaison();
                
                }
            }
        });
        //

        scroll = new JScrollPane(p);
        add(scroll);
        pack();
        setVisible(true);

    }

    void actionaleat() {
        final int input;
        JTextField r = new JTextField();
        r.setText("");
        JComponent[] inputs = {new JLabel("veuillez entrer un nombre positif non null inférieur ou égale à 9"), 
                               new JLabel("\n"), new JLabel("Nombre de reines :"), r};
        JOptionPane.showMessageDialog(null, inputs, "Exhaustive !",JOptionPane.PLAIN_MESSAGE);
        if("".equals(r.getText())){}
        else{
        input=Algorithmes.string2int(r.getText(),9);
        if(input==0){
        Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, 
                   "Veuillez entrer un nombre positif non null inférieur ou égale à 9", "Avertisement", 
                    JOptionPane.ERROR_MESSAGE);
    }
        else{
        if(input==-1) {Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null, "Veuillez entrer un entier valide, positif non null inférieur ou égale à 9", "Avertisement", JOptionPane.ERROR_MESSAGE);
        }
        else {
                         Thread th = new Thread(new Runnable() {
                            @Override
                            public void run() {

                                //bug de la réduction de la fenêtre
                                GridBagConstraints gbc = new GridBagConstraints();
                                JPanel[][]pa = new JPanel[input][input];
                                hold = new Panneau(input);
                                hold.Echiquier(pa, input);
                                int[] tab;
                               LinkedList<int[]> permu = new LinkedList();
                                int tmp, ran, con;
                                Algorithmes.permutations(input, permu);
                                tmp = Algorithmes.aleatoire(permu.size());
                                tab = permu.get(tmp);
                                con = Algorithmes.Conflits(tab);
                                Panneau.Placernreines(pa, tab);
                                p.setVisible(false);
                                p.removeAll();
                                p.setLayout(new GridBagLayout());
                                // JTable
                                String[] titres = {"Iteration", "Permutation", "Conflits"};
                                LinkedList<String[]> list = new LinkedList();
                                t = new Tables(titres);
                                int o = 1;
                                list.add(new String[]{"<html><font color='blue'/><code><b>" + o + "</b></code></html>",
                                    "<html><font color='blue'/><code><b>" + Algorithmes.toString(tab) + "</b></code></html>",
                                    "<html><font color='blue'/><code><b>" + con + "</b></code></html>"});
                                t.ajouterligne(list.get(o - 1));
                                t.affichetab("Exhaustive");
                                //JTable
                                JPanel pn = new JPanel();
                                pn.setLayout(new GridBagLayout());
                                gbc.gridx = 0;
                                gbc.gridy = 0;
                                pn.add(hold, gbc);
                                gbc.gridy = 1;
                                pn.add(new JLabel("Conflits : " + Algorithmes.Conflits(tab)), gbc);
                                gbc.gridx = 0;
                                gbc.gridy = 0;
                                p.add(pn, gbc);
                                final JPanel pn2 = new JPanel(new GridBagLayout());
                                final boolean[] paus = {false, true};
                                final JButton pause = new JButton("Pause");
                                final JButton rep = new JButton("Reprendre");
            // action sur les boutton pause et reprendre
                rep.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        p.setVisible(false);
                        pn2.remove(rep);
                        GridBagConstraints gbc2 = new GridBagConstraints();
                        gbc2.gridx = 0;
                        gbc2.gridy = 0;
                        gbc2.anchor = GridBagConstraints.EAST;
                        pn2.add(pause, gbc2);
                        pn2.revalidate();
                        p.revalidate();
                        p.setVisible(true);
                        paus[0] = false;
                    }
                });
                pause.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        if (paus[1]) {
                            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
                            paus[0] = true;
                            GridBagConstraints gbc2 = new GridBagConstraints();
                            gbc2.gridx = 0;
                            gbc2.gridy = 0;
                            gbc2.anchor = GridBagConstraints.EAST;
                            pn2.remove(pause);
                            pn2.add(rep, gbc2);
                            pn2.revalidate();
                            p.setVisible(false);
                            p.revalidate();
                            p.setVisible(true);
                        }



                                    }
                                });
             ///////////////////////////////////////////////////////////////////////////fin
                                gbc.gridx = 0;
                                gbc.gridy = 0;
                                gbc.ipadx = 0;
                                gbc.anchor = GridBagConstraints.EAST;
                                pn2.add(pause, gbc);
                                gbc.gridx = 0;
                                gbc.gridy = 1;
                                pn2.add(t, gbc);
                                gbc.gridx = 1;
                                gbc.gridy = 0;
                                gbc.ipadx = 30;
                                p.add(pn2, gbc);
                                gbc.ipadx = 0;
                                p.revalidate();
                                p.setVisible(true);
                                while (con != 0) {
                                    if (paus[0]) {
                                    } else {

                ran = Algorithmes.aleatoire(permu.size());
                if (ran != tmp) {
                    if (con > Algorithmes.Conflits(permu.get(ran))) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                        }
                        permu.remove(tmp);
                        if (tmp > ran) {
                            tmp = ran;
                        } else {
                            tmp = ran - 1;
                        }
                        hold = null;
                        hold = new Panneau(input);
                        pa = null;
                        pn.removeAll();
                        pn.setLayout(new GridBagLayout());
                        pa = new JPanel[input][input];
                        hold.Echiquier(pa, input);
                        tab = permu.get(tmp);
                        con = Algorithmes.Conflits(tab);
                        //JTable
                        o++;
                        list.add(new String[]{"<html><font color='blue'/><code><b>" + o + "</b></code></html>",
                            "<html><font color='blue'/><code><b>" + Algorithmes.toString(tab) + "</b></code></html>",
                            "<html><font color='blue'/><code><b>" + con + "</b></code></html>"});


                        t.ajouterligne(list.get(o - 1));
                        //Jtable
                        Panneau.Placernreines(pa, tab);
                        gbc.gridx = 0;
                        gbc.gridy = 0;
                        gbc.anchor = GridBagConstraints.CENTER;
                        pn.add(hold, gbc);
                        gbc.gridy = 1;
                        pn.add(new JLabel("Conflits : " + Algorithmes.Conflits(tab)), gbc);
                        pn.revalidate();
                    } else {
                        o++;
                        list.add(new String[]{"<html><font color='black'/><code>" + o + "</code></html>",
                            "<html><font color='black'/><code>" + Algorithmes.toString(permu.get(ran)) + "</code></html>",
                            "<html><font color='black'/><code>" + Algorithmes.Conflits(permu.get(ran)) + "</code></html>"});
                        t.ajouterligne(list.get(o - 1));
                        permu.remove(ran);
                    }
                    if (ran < tmp) {
                        tmp--;
                    }

                }

                        }
                        if (con == 0) {
                            pause.setEnabled(false);
                        }
                    }
                    permu.clear();
                            }
                        });
                        th.start();
                    }
        } 
        }
    }//fin aleatoire

    public void actionpuraleat() {

        final int it,reine;
        JTextField r = new JTextField();
        r.setText("");
        JTextField iter = new JTextField();
        iter.setText("");
        JComponent[] inputs = new JComponent[]{
            new JLabel("Veuillez remplir ces champs avec des nombres "),
            new JLabel("positifs non nuls"), new JLabel("\n"),
            new JLabel("Nombre de Reines (<=20):"), new JLabel("\n"), r, new JLabel("\n"),
            new JLabel("Nombre d'iterations (jusqu'à 1 million):"), new JLabel("\n"), iter,
            new JLabel("\n")
        };
        JOptionPane.showMessageDialog(null, inputs, "Pure Aléatoire !", JOptionPane.PLAIN_MESSAGE);
        //
        if(iter.getText().equals("") && r.getText().equals("")){}
        else{reine=Algorithmes.string2int(r.getText(), 20);
        it=Algorithmes.string2int(iter.getText(), 1000000);
        if (it==0 || reine==0) {
            Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, 
                   "Veuillez entrer des nombres respéctant les contraintes", "Avertisement", 
                    JOptionPane.ERROR_MESSAGE);
        } else {
            if(it==-1 || reine==-1){
                Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, 
                   "Veuillez entrer des nombres valide, respéctant les contraintes", "Avertisement", 
                    JOptionPane.ERROR_MESSAGE);
            }
            else{
            
            Thread thread2;
            thread2 = new Thread(new Runnable() {
                @Override
                public void run() {

                    int conf, iteration = 1;
                    GridBagConstraints gbc3 = new GridBagConstraints();
                    int[] tab2 = new int[reine];
                    Algorithmes.tabaleat(tab2);
                    conf = Algorithmes.Conflits(tab2);
                    JPanel[][]pa = new JPanel[reine][reine];
                    hold = new Panneau(reine);
                    hold.Echiquier(pa, reine);
                    Panneau.Placernreines(pa, tab2);
                    p.setVisible(false);
                    p.removeAll();
                    //JTable
                    String[] titres = {"Iteration", "Permutation", "Conflits"};
                    LinkedList<String[]> list = new LinkedList();
                    t = new Tables(titres);
                    int o = 1;
                    list.add(new String[]{"<html><font color='blue'/><b>" + o + "</b></html>",
                        "<html><font color='blue'/><b><code>" + Algorithmes.toString(tab2) + "</code></b></html>",
                        "<html><font color='blue'/><b><code>" + conf + "</code></b></html>"});
                    t.ajouterligne(list.get(o - 1));
                    t.affichetab("Pure Aléatoire");
                    //JTable
                    JPanel pn = new JPanel(new GridBagLayout());
                    gbc3.gridx = 0;
                    gbc3.gridy = 1;
                    pn.add(hold, gbc3);
                    gbc3.gridy = 2;
                    JPanel cnf = new JPanel();
                    cnf.add(new JLabel("Conflits : " + conf), gbc3);
                    pn.add(cnf, gbc3);
                    JPanel Piter = new JPanel();
                    JLabel Liter = new JLabel("Iteration : " + iteration + "/" + it);
                    Piter.add(Liter);
                    gbc3.gridx = 0;
                    gbc3.gridy = 0;
                    pn.add(Piter, gbc3);
                    gbc3.gridx = 0;
                    gbc3.gridy = 0;
                    p.add(pn, gbc3);
                    //JTable
                    final JPanel pn2 = new JPanel(new GridBagLayout());
                    gbc3.gridx = 0;
                    gbc3.gridy = 1;
                    pn2.add(t, gbc3);
                    //JTable
                    final boolean[] paus = {false, true};
                    final JButton pause = new JButton("Pause");
                    final JButton rep = new JButton("Reprendre");
                    rep.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            p.setVisible(false);
                            pn2.remove(rep);
                            GridBagConstraints gbc2 = new GridBagConstraints();
                            gbc2.gridx = 0;
                            gbc2.gridy = 0;
                            gbc2.anchor = GridBagConstraints.EAST;
                            gbc2.ipadx = 0;
                            pn2.add(pause, gbc2);
                            pn2.revalidate();
                            p.revalidate();
                            p.setVisible(true);
                            paus[0] = false;
                        }
                    });
                    pause.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            if (paus[1]) {
                                GridBagConstraints gbc2 = new GridBagConstraints();
                                gbc2.gridx = 0;
                                gbc2.gridy = 0;
                                gbc2.anchor = GridBagConstraints.EAST;
                                gbc2.ipadx = 0;
                                p.setVisible(false);
                                pn2.remove(pause);
                                pn2.add(rep, gbc2);
                                pn2.revalidate();
                                p.revalidate();
                                p.setVisible(true);
                                paus[0] = true;
                            }
                        }
                    });
                    gbc3.gridx = 0;
                    gbc3.gridy = 0;
                    gbc3.anchor = GridBagConstraints.EAST;
                    pn2.add(pause, gbc3);
                    gbc3.gridx = 1;
                    gbc3.gridy = 0;
                    gbc3.anchor = GridBagConstraints.CENTER;
                    gbc3.ipadx = 30;
                    p.add(pn2, gbc3);
                    gbc3.ipadx = 0;
                    p.revalidate();
                    p.setVisible(true);
                    while (conf != 0 && iteration != it) {
                        while (paus[0]) {
                        }
                        iteration++;
                        int[] tabl = new int[reine];
                        Algorithmes.tabaleat(tabl);
                        int confl = Algorithmes.Conflits(tabl);
                        if (confl < conf) {
                            conf = confl;
                            tab2 = tabl;
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {
                            }
                            hold = null;
                            hold = new Panneau(reine);
                            pa = null;
                            pa = new JPanel[reine][reine];
                            hold.Echiquier(pa, reine);
                            Panneau.Placernreines(pa, tab2);
                            o++;
                            list.add(new String[]{"<html><font color='blue'/><b>" + o + "</b></html>",
                                "<html><font color='blue'/><b><code>" + Algorithmes.toString(tab2) + "</code></b></html>",
                                "<html><font color='blue'/><b><code>" + conf + "</code></b></html>"});
                            t.ajouterligne(list.get(o - 1));
                            pn.removeAll();
                            gbc3.gridx = 0;
                            gbc3.gridy = 1;
                            pn.add(hold, gbc3);
                            gbc3.gridy = 2;
                            cnf.removeAll();
                            cnf.add(new JLabel("Conflits : " + conf), gbc3);
                            cnf.revalidate();
                            pn.add(cnf, gbc3);
                            Liter = new JLabel("Iteration : " + iteration + "/" + it);
                            Piter.removeAll();
                            Piter.add(Liter);
                            Piter.revalidate();
                            gbc3.gridy = 0;
                            pn.add(Piter, gbc3);
                            pn.revalidate();
                        } else {
                            o++;
                            list.add(new String[]{"<html><font color='black'/>" + o + "</html>",
                                "<html><font color='black'/><code>" + Algorithmes.toString(tabl) + "</code></html>",
                                "<html><font color='black'/><code>" + confl + "</code></html>"});
                            t.ajouterligne(list.get(o - 1));
                        }
                    }
                    pause.setEnabled(false);
                    if (iteration == it) {
                        JComponent[] inputs2 = new JComponent[]{
                            new JLabel("Toutes les " + iteration + " iterations ont été exaucés")};
                        JOptionPane.showMessageDialog(null, inputs2, "Pure Aléatoire !", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            });

            thread2.start();
        }
        }}
    }//fin purealeat

    public void actionamelior() {
         final int reine,it;
        JTextField r = new JTextField();
        r.setText("");
        JTextField iter = new JTextField();
        iter.setText("");
        JComponent[] inputs = new JComponent[]{
            new JLabel("Veuillez remplir ces champs avec des nombres "),
            new JLabel("positifs non nuls"), new JLabel("\n"),
            new JLabel("Nombre de Reines (<=20):"), new JLabel("\n"), r, new JLabel("\n"),
            new JLabel("Nombre d'iterations (jusqu'à 1 million):"), new JLabel("\n"), iter,
            new JLabel("\n")
        };
        JOptionPane.showMessageDialog(null, inputs, "Amélioration itérative !", JOptionPane.PLAIN_MESSAGE);
        if(iter.getText().equals("") && r.getText().equals("")){}
        else{
        reine=Algorithmes.string2int(r.getText(), 20);
        it=Algorithmes.string2int(iter.getText(), 1000000);
        if (it==0 || reine==0) {
            Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, 
                   "Veuillez entrer des nombres respéctant les contraintes", "Avertisement", 
                    JOptionPane.ERROR_MESSAGE);
        } else {
            if(it==-1 || reine==-1){
                Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, 
                   "Veuillez entrer des nombres valide, respéctant les contraintes", "Avertisement", 
                    JOptionPane.ERROR_MESSAGE);
            } else {
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean arret = false;
                    int conf, iteration = 0;
                    GridBagConstraints gbc3 = new GridBagConstraints();
                    int[] tab2 = new int[reine];
                    Algorithmes.tabaleat(tab2);
                    conf = Algorithmes.Conflits(tab2);
                    JPanel[][]pa = new JPanel[reine][reine];
                    hold = new Panneau(reine);
                    hold.Echiquier(pa, reine);
                    Panneau.Placernreines(pa, tab2);
                    p.setVisible(false);
                    p.removeAll();
                    JPanel pn = new JPanel(new GridBagLayout());
                    JPanel Piter = new JPanel(new GridBagLayout());
                    String[] titres = {"Région/Iteration", "Permutation", "Conflits"};
                    LinkedList<String[]> list = new LinkedList();
                    t = new Tables(titres);
                    int o = 1;
                    final JPanel pn2 = new JPanel(new GridBagLayout());
                    list.add(new String[]{"<html><font color='blue'/><b>" + (o - 1) + "</b></html>",
                        "<html><font color='blue'/><b><code>" + Algorithmes.toString(tab2) + "</code></b></html>",
                        "<html><font color='blue'/><b><code>" + conf + "</code></b></html>"});
                    t.ajouterligne(list.get(o - 1));
                    t.affichetab("Amélioration Itérative");
                    //pause
                     final boolean[] paus = {false, true};
                    final JButton pause = new JButton("Pause");
                    final JButton rep = new JButton("Reprendre");
                    rep.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            p.setVisible(false);
                            pn2.remove(rep);
                            GridBagConstraints gbc2 = new GridBagConstraints();
                            gbc2.gridx = 0;
                            gbc2.gridy = 0;
                            gbc2.anchor = GridBagConstraints.EAST;
                            gbc2.ipadx = 0;
                            pn2.add(pause, gbc2);
                            pn2.revalidate();
                            p.revalidate();
                            p.setVisible(true);
                            paus[0] = false;
                        }
                    });
                    pause.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            if (paus[1]) {
                                GridBagConstraints gbc2 = new GridBagConstraints();
                                gbc2.gridx = 0;
                                gbc2.gridy = 0;
                                gbc2.anchor = GridBagConstraints.EAST;
                                gbc2.ipadx = 0;
                                p.setVisible(false);
                                pn2.remove(pause);
                                pn2.add(rep, gbc2);
                                pn2.revalidate();
                                p.revalidate();
                                p.setVisible(true);
                                paus[0] = true;
                            }
                        }
                    });
                    //fin pause
                    Piter.add(new JLabel("Iterations : " + iteration + "/" + it));
                    gbc3.gridy = 0;
                    gbc3.gridx = 0;
                    pn.add(Piter, gbc3);
                    gbc3.gridx = 0;
                    gbc3.gridy = 1;
                    pn2.add(t, gbc3);
                    gbc3.gridx=0;
                    gbc3.gridy=0;
                    gbc3.anchor=GridBagConstraints.EAST;
                    pn2.add(pause,gbc3);
                     gbc3.anchor=GridBagConstraints.CENTER;
                    gbc3.gridx = 0;
                    gbc3.gridy = 1;
                    pn.add(hold, gbc3);
                    JPanel cnf = new JPanel();
                    gbc3.gridy = 2;
                    cnf.add(new JLabel("Conflits : " + conf));
                    pn.add(cnf, gbc3);
                    gbc3.gridx = 0;
                    gbc3.gridy = 0;
                    gbc3.ipadx=30;
                    p.add(pn, gbc3);
                    gbc3.gridx=1;
                    gbc3.gridy=0;
                    gbc3.ipadx=0;
                    p.add(pn2,gbc3);
                    p.revalidate();
                    p.setVisible(true);
                    while (conf != 0 && iteration != it && arret != true) {
                        if(paus[0]){}
                        else{
                        iteration++;
                        int[] tabl = Algorithmes.region(tab2);
                        int confl = Algorithmes.Conflits(tabl);
                        if (confl >= conf) {
                            o++;
                            list.add(new String[]{"<html><font color='red'/><b>" + (o - 1) + "</b></html>",
                                "<html><font color='red'/><b><code>" + Algorithmes.toString(tabl) + "</code></b></html>",
                                "<html><font color='red'/><b><code>" + confl + "</code></b></html>"});
                            t.ajouterligne(list.get(o - 1));
                            arret = true;
                            JComponent[] inputs2 = new JComponent[]{
                                new JLabel("l'algorithme est bloqué aucune amélioration n'est possible")};
                            JOptionPane.showMessageDialog(null, inputs2, "Amélioation Iterative !", JOptionPane.PLAIN_MESSAGE);

                        } else {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {
                            }
                            o++;
                            tab2 = tabl;
                            conf = confl;
                            pa = new JPanel[reine][reine];
                            hold = null;
                            hold = new Panneau(reine);
                            hold.Echiquier(pa, reine);
                            Panneau.Placernreines(pa, tab2);
                            list.add(new String[]{"<html><font color='blue'/><b>" + (o - 1) + "</b></html>",
                                "<html><font color='blue'/><b><code>" + Algorithmes.toString(tab2) + "</code></b></html>",
                                "<html><font color='blue'/><b><code>" + conf + "</code></b></html>"});
                            t.ajouterligne(list.get(o - 1));
                            pn.removeAll();
                            Piter.removeAll();
                            gbc3.gridx = 0;
                            gbc3.gridy = 1;
                            pn.add(hold, gbc3);
                            gbc3.gridy = 2;
                            cnf.removeAll();
                            cnf.add(new JLabel("Conflits : " + conf));
                            pn.add(cnf, gbc3);
                            Piter.add(new JLabel("Iteration : " + iteration + "/" + it));
                            Piter.revalidate();
                            gbc3.gridy = 0;
                            pn.add(Piter, gbc3);
                            pn.revalidate();
                        }
                    }}
                    pause.setEnabled(false);
                    if (iteration == it) {
                        JComponent[] inputs2 = new JComponent[]{
                            new JLabel("Toutes les " + iteration + " iterations ont été exaucés")};
                        JOptionPane.showMessageDialog(null, inputs2, "Amélioration Iterative !", JOptionPane.PLAIN_MESSAGE);
                    }
                    //
                }
            });
            th.start();
        }
        }}
    }// fin amelioration

    void nqueenjeu() {
         final int input;
        JTextField r = new JTextField();
        JComponent[] inputs = {new JLabel("veuillez entrer un nombre positif non null inférieur ou égale à 10"), new JLabel("\n"), new JLabel("Nombre de reines :"), r};
        JOptionPane.showMessageDialog(null, inputs, "NQueens le Jeu !", JOptionPane.PLAIN_MESSAGE);
        input=Algorithmes.string2int(r.getText(),10);
        if(input==0){Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, 
                   "Veuillez entrer un nombre positif non null inférieur ou égale à 10", "Avertisement", 
                    JOptionPane.ERROR_MESSAGE);}
        else{
        if(input==-1) {Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null, "Veuillez entrer un entier valide, positif non null inférieur ou égale à 10", "Avertisement", JOptionPane.ERROR_MESSAGE);
        } else {
                        Thread th = new Thread(new Runnable() {
                            @Override
                            public void run() {

                                GridBagConstraints gbc = new GridBagConstraints();
                               final JPanel[][] pa = new JPanel[input][input];
                                final int[] tab = new int[input];
                                hold = new Panneau(input);
                                p.setVisible(false);
                                p.removeAll();
                                p.setLayout(new GridBagLayout());
                                JLabel j = new JLabel("NQueens le jeu");
                                hold.EchiquierJeu(pa, input, tab);
                                gbc.gridx = 0;
                                gbc.gridy = 0;
                                p.add(j, gbc);
                                gbc.gridx = 0;
                                gbc.gridy = 1;
                                gbc.insets = new Insets(10, 10, 10, 10);
                                p.add(hold, gbc);
                                JPanel but=new JPanel(new GridBagLayout());
                                JButton val = new JButton("valider");
                                gbc.gridx=1;
                                gbc.gridy=0;
                                but.add(val,gbc);
                                JButton rec=new JButton("Recommencer");
                                 gbc.gridx=0;
                                gbc.gridy=0;
                                but.add(rec,gbc);
                                gbc.gridx = 0;
                                gbc.gridy = 2;
                                p.add(but, gbc);
                                p.revalidate();
                                p.setVisible(true);


                                //Boutton Recommencer
                                rec.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent event) {
                                    GridBagConstraints gbc=new GridBagConstraints();
                                        for(int i=0; i<tab.length; i++)
                                        tab[i]=0;
                                    p.remove(hold);
                                    hold=new Panneau(input);
                                    JPanel[][] pa2=new JPanel[input][input];
                                    hold.EchiquierJeu(pa2, input, tab);
                                    hold.revalidate();
                                gbc.gridx = 0;
                                gbc.gridy = 1;
                                gbc.insets = new Insets(10, 10, 10, 10);
                                p.add(hold, gbc);
                                    p.revalidate();
                                    }});
                                //Boutton valider
                                val.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent event) {

                                        if (Algorithmes.nothere(tab, (int) 0)) {

                                            JComponent[] inputs2 = new JComponent[]{new JLabel("Vous avez entré la permutation :"+Algorithmes.toString(tab)),
                                                new JLabel("\n"),new JLabel("Votre score est : " + Algorithmes.Conflits(tab) + " Conflits")};
                                            JOptionPane.showMessageDialog(null, inputs2, "Fin du jeu !", JOptionPane.PLAIN_MESSAGE);




                                        } else {
                                            JOptionPane.showMessageDialog(null, "Attention, vous avez laissé des lignes vides", "Avertisement", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                });
                                //Fin boutton valider
                            }
                        });
                        th.start();
                    }//else2
                }//else1
       
    }// fin nqueenjeu

    void recuit() {
        boolean t1=true;
        final int reine, it;final float temp;
        JFrame g = new JFrame();
       final JTextField r = new JTextField("");
        final JTextField iter = new JTextField("");
        final JTextField tmp = new JTextField("");
        final float prob = (float) 0.5;
        tmp.setEnabled(false);
        JRadioButton cl = new JRadioButton("Calculé automatiquement");
        JRadioButton sp = new JRadioButton("la spécifier");
        ButtonGroup grp = new ButtonGroup();
        grp.add(cl);
        grp.add(sp);
        cl.setSelected(true);
        cl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                tmp.setEnabled(false);
                tmp.setText("");
            }
        });
        sp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                tmp.setEnabled(true);
            }
        });
        JComponent[] inputs = new JComponent[]{
            new JLabel("Veuillez remplir ces champs avec des nombres "),
            new JLabel("positifs non nuls"), new JLabel("\n"),
            new JLabel("Nombre de Reines :(<=20)"), new JLabel("\n"), r, new JLabel("\n"),
            new JLabel("Nombre d'iterations :(jusqu'à 1 million)"), new JLabel("\n"), iter,
            new JLabel("\n"), new JLabel("Température initiale : "), new JLabel("\n"), cl, sp,
            new JLabel("\n"), tmp
        };
        JOptionPane.showMessageDialog(null, inputs, "Recuit simulé !", JOptionPane.PLAIN_MESSAGE);
        if (iter.getText().length() == 0 || r.getText().length() == 0 || (tmp.getText().length() == 0 && tmp.isEnabled())) {
        } else {
            it=Algorithmes.string2int(iter.getText(), 1000000);
            reine=Algorithmes.string2int(r.getText(), 20);
            if (it==0 || reine==0) {
            Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, 
                   "Veuillez entrer des nombres respéctant les contraintes", "Avertisement", 
                    JOptionPane.ERROR_MESSAGE);
        } else {
            if(it==-1 || reine==-1){
                Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, 
                   "Veuillez entrer des nombres valide, respéctant les contraintes", "Avertisement", 
                    JOptionPane.ERROR_MESSAGE);
            }else{ if (cl.isSelected() ) {
                        temp = Recuit.tinit(prob, reine);
                         
                    }
                     else {
                        temp = Algorithmes.string2int(tmp.getText(), 100);
                        if(temp==0){
                            t1=false;
                            Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, 
                   "Veuillez entrer des nombres respéctant les contraintes", "Avertisement", 
                    JOptionPane.ERROR_MESSAGE);
                        }
                        else{if(temp==-1){ t1=false;
                            JOptionPane.showMessageDialog(null, 
                   "Veuillez entrer des nombres valide, respéctant les contraintes", "Avertisement", 
                    JOptionPane.ERROR_MESSAGE);
                        }}}
                        if(t1){
                
                Thread th;
                th = new Thread(new Runnable() {
             @Override
             public void run() {
                 int conf, iteration = 1, df, confl;
                 float temp2=temp;
                 float[] param = new float[2];
                 GridBagConstraints gbc3 = new GridBagConstraints();
                 int[] tab2 = new int[reine];
                 Algorithmes.tabaleat(tab2);
                 conf = Algorithmes.Conflits(tab2);
                JPanel[][] pa = new JPanel[reine][reine];
                 hold = new Panneau(reine);
                 hold.Echiquier(pa, reine);
                 Panneau.Placernreines(pa, tab2);
                 
                 p.setVisible(false);
                 p.removeAll();
                 //JTable
                 String[] titres = {"Iteration", "Permutation", "Conflits", "Probabilités", "température"};
                 LinkedList<String[]> list = new LinkedList();
                 t = new Tables(titres);
                 int o = 1;
                 list.add(new String[]{"<html><font color='blue'/><b>" + o + "</b></html>",
                     "<html><font color='blue'/><b><code>" + Algorithmes.toString(tab2) + "</code></b></html>",
                     "<html><font color='blue'/><b><code>" + conf + "</code></b></html>",
                     "<html><font color='blue'/><b><code>" + prob + "(initial)</code></b></html>",
                     "<html><font color='blue'/><b><code>" + temp2 + "</code></b></html>"});
                 t.ajouterligne(list.get(o - 1));
                 t.affichetab("Recuit Simulé");
                 //JTable
                 JPanel pn = new JPanel(new GridBagLayout());
                 gbc3.gridx = 0;
                 gbc3.gridy = 1;
                 pn.add(hold, gbc3);
                 gbc3.gridy = 2;
                 JPanel cnf = new JPanel(new GridBagLayout());
                 cnf.add(new JLabel("Conflits : " + conf), gbc3);
                 pn.add(cnf, gbc3);
                 JPanel Piter = new JPanel(new GridBagLayout());
                 JLabel Liter = new JLabel("Iteration : " + iteration + "/" + it);
                 Piter.add(Liter);
                 gbc3.gridx = 0;
                 gbc3.gridy = 0;
                 pn.add(Piter, gbc3);
                 gbc3.gridx = 0;
                 gbc3.gridy = 0;
                 p.add(pn, gbc3);
                 //JTable
                 final JPanel pn2 = new JPanel(new GridBagLayout());
                 gbc3.gridx = 0;
                 gbc3.gridy = 1;
                 pn2.add(t, gbc3);
                 //JTable
                 final boolean[] paus = {false, true};
                 final JButton pause = new JButton("Pause");
                 final JButton rep = new JButton("Reprendre");
                 rep.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent event) {
                         p.setVisible(false);
                         pn2.remove(rep);
                         GridBagConstraints gbc2 = new GridBagConstraints();
                         gbc2.gridx = 0;
                         gbc2.gridy = 0;
                         gbc2.anchor = GridBagConstraints.EAST;
                         gbc2.ipadx = 0;
                         pn2.add(pause, gbc2);
                         pn2.revalidate();
                         p.revalidate();
                         p.setVisible(true);
                         paus[0] = false;
                     }
                 });
                 pause.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent event) {
                         if (paus[1]) {
                             GridBagConstraints gbc2 = new GridBagConstraints();
                             gbc2.gridx = 0;
                             gbc2.gridy = 0;
                             gbc2.anchor = GridBagConstraints.EAST;
                             gbc2.ipadx = 0;
                             p.setVisible(false);
                             pn2.remove(pause);
                             pn2.add(rep, gbc2);
                             pn2.revalidate();
                             p.revalidate();
                             p.setVisible(true);
                             paus[0] = true;
                         }
                     }
                 });
                 gbc3.gridx = 0;
                 gbc3.gridy = 0;
                 gbc3.anchor = GridBagConstraints.EAST;
                 pn2.add(pause, gbc3);
                 gbc3.gridx = 1;
                 gbc3.gridy = 0;
                 gbc3.anchor = GridBagConstraints.CENTER;
                 gbc3.ipadx = 30;
                 p.add(pn2, gbc3);
                 gbc3.ipadx = 0;
                 p.revalidate();
                 p.setVisible(true);

                 while (it != iteration && conf != 0) {
                     if (paus[0]) {
                     } else {
                         int[] tab3 = Recuit.voisin(tab2);
                         confl = Algorithmes.Conflits(tab3);
                         df = confl - conf;
                         if (Recuit.accepter(df, temp2, param)) {
                             tab2 = tab3;
                             conf = confl;
                             try {
                                 Thread.sleep(2000);
                             } catch (InterruptedException ex) {
                             }
                             hold = null;
                             hold = new Panneau(reine);
                             pa = null;
                             pa = new JPanel[reine][reine];
                             hold.Echiquier(pa, reine);
                             Panneau.Placernreines(pa, tab2);
                             o++;
                             list.add(new String[]{"<html><font color='blue'/><b>" + o + "</b></html>",
                                 "<html><font color='blue'/><b><code>" + Algorithmes.toString(tab2) + "</code></b></html>",
                                 "<html><font color='blue'/><b><code>" + conf + "</code></b></html>",
                                 "<html><font color='blue'/><b><code>" + param[1] + "/" + param[0] + "</code></b></html>",
                                 "<html><font color='blue'/><b><code>" + temp2 + "</code></b></html>"});
                             t.ajouterligne(list.get(o - 1));
                             pn.removeAll();
                             gbc3.anchor = GridBagConstraints.CENTER;
                             gbc3.gridx = 0;
                             gbc3.gridy = 1;
                             pn.add(hold, gbc3);
                             gbc3.gridy = 2;
                             cnf.removeAll();
                             cnf.add(new JLabel("Conflits : " + conf), gbc3);
                             cnf.revalidate();
                             pn.add(cnf, gbc3);
                             Liter = new JLabel("Iteration : " + iteration + "/" + it);
                             Piter.removeAll();
                             Piter.add(Liter);
                             Piter.revalidate();
                             gbc3.gridy = 0;
                             pn.add(Piter, gbc3);
                             pn.revalidate();
                         } else {
                             o++;
                             list.add(new String[]{"<html><font color='black'/>" + o + "</html>",
                                 "<html><font color='black'/><code>" + Algorithmes.toString(tab3) + "</code></html>",
                                 "<html><font color='black'/><code>" + confl + "</code></html>",
                                 "<html><font color='black'/><code>" + param[1] + "/" + param[0] + "</code></html>",
                                 "<html><font color='black'/><code>" + temp + "</code></html>"});
                             t.ajouterligne(list.get(o - 1));
                         }
                         iteration++;
                         temp2 =  (float) (0.99 * temp2);
                     }
                 }
                 pause.setEnabled(false);
                 if (iteration == it) {
                     JComponent[] inputs2 = new JComponent[]{
                         new JLabel("Toutes les " + iteration + " iterations ont été exaucés")};
                     JOptionPane.showMessageDialog(null, inputs2, "Recuit Simulé !", JOptionPane.PLAIN_MESSAGE);

                 }

             }
         });
            th.start();}
                        }
                    }
}
        }

    
    void tabou() {
        final int it,reine,ten;
    
        JTextField r = new JTextField("");
        JTextField iter = new JTextField("");
        final JTextField tenure = new JTextField("3");
        JComponent[] inputs = new JComponent[]{
            new JLabel("Veuillez remplir ces champs avec des nombres "),
            new JLabel("positifs non nuls"), new JLabel("\n"),
            new JLabel("Nombre de Reines (<=20):"), new JLabel("\n"), r, new JLabel("\n"),
            new JLabel("Nombre d'iterations (jusqu'à 1 million) :"), new JLabel("\n"), iter, new JLabel("\n"),
            new JLabel("Taille de la liste Tabou (<=100):"), new JLabel("\n"), tenure, new JLabel("\n")
        };
        JOptionPane.showMessageDialog(null, inputs, "Recherche Tabou !", JOptionPane.PLAIN_MESSAGE);
        //
        if(iter.getText().equals("") && r.getText().equals("")){}
        else{
        reine=Algorithmes.string2int(r.getText(), 20);
        it=Algorithmes.string2int(iter.getText(), 1000000);
        ten=Algorithmes.string2int(tenure.getText(),100);
        if (it==0 || reine==0) {
            Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, 
                   "Veuillez entrer des nombres respéctant les contraintes", "Avertisement", 
                    JOptionPane.ERROR_MESSAGE);
        } else {
            if(it==-1 || reine==-1){
                Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, 
                   "Veuillez entrer des nombres valide, respéctant les contraintes", "Avertisement", 
                    JOptionPane.ERROR_MESSAGE);
            }{
            Thread thread2;
            thread2 = new Thread(new Runnable() {
                @Override
    public void run() {

        int conf, iteration = 1;
        GridBagConstraints gbc3 = new GridBagConstraints();
        int[] tab2 = new int[reine];
        Map<Integer, int[]> ltabou = Tabou.listabou(reine);
        Algorithmes.tabaleat(tab2);
        conf = Algorithmes.Conflits(tab2);
        System.out.println("initial:" + Algorithmes.toString(tab2) + "\t" + conf);
        JPanel[][]pa = new JPanel[reine][reine];
        hold = new Panneau(reine);
        hold.Echiquier(pa, reine);
        Panneau.Placernreines(pa, tab2);
        p.setVisible(false);
        p.removeAll();
        //JTable
        String[] titres = {"Iteration", "Permutation", "Conflits", "Tabou("+tenure.getText()+")"};
        LinkedList<String[]> list = new LinkedList();
        t = new Tables(titres);
        int o = 1;
        list.add(new String[]{"<html><font color='blue'/><b><code>" + (o-1) + "</code></b><</html>",
            "<html><font color='blue'/><b><code>" + Algorithmes.toString(tab2) + "</code></b></html>",
            "<html><font color='blue'/><b><code>" + conf + "</code></b></html>", ""});
        t.ajouterligne(list.get(o - 1));
        t.affichetab("Recherche Tabou");
        //JTable
        JPanel pn = new JPanel(new GridBagLayout());
        gbc3.gridx = 0;
        gbc3.gridy = 1;
        pn.add(hold, gbc3);
        gbc3.gridy = 2;
        JPanel cnf = new JPanel();
        cnf.add(new JLabel("Conflits : " + conf), gbc3);
        pn.add(cnf, gbc3);
        JPanel Piter = new JPanel();
        JLabel Liter = new JLabel("Iteration : " + iteration + "/" + it);
        Piter.add(Liter);
        gbc3.gridx = 0;
        gbc3.gridy = 0;
        pn.add(Piter, gbc3);
        gbc3.gridx = 0;
        gbc3.gridy = 0;
        p.add(pn, gbc3);
        //JTable
        final JPanel pn2 = new JPanel(new GridBagLayout());
        gbc3.gridx = 0;
        gbc3.gridy = 1;
        pn2.add(t, gbc3);
        //JTable
        final boolean[] paus = {false, true};
        final JButton pause = new JButton("Pause");
        final JButton rep = new JButton("Reprendre");
        rep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                p.setVisible(false);
                pn2.remove(rep);
                GridBagConstraints gbc2 = new GridBagConstraints();
                gbc2.gridx = 0;
                gbc2.gridy = 0;
                gbc2.anchor = GridBagConstraints.EAST;
                gbc2.ipadx = 0;
                pn2.add(pause, gbc2);
                pn2.revalidate();
                p.revalidate();
                p.setVisible(true);
                paus[0] = false;
            }
        });
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            if (paus[1]) {
                GridBagConstraints gbc2 = new GridBagConstraints();
                gbc2.gridx = 0;
                gbc2.gridy = 0;
                gbc2.anchor = GridBagConstraints.EAST;
                gbc2.ipadx = 0;
                p.setVisible(false);
                pn2.remove(pause);
                pn2.add(rep, gbc2);
                pn2.revalidate();
                p.revalidate();
                p.setVisible(true);
                paus[0] = true;
            }
            }
        });
        gbc3.gridx = 0;
        gbc3.gridy = 0;
        gbc3.anchor = GridBagConstraints.EAST;
        pn2.add(pause, gbc3);
        gbc3.gridx = 1;
        gbc3.gridy = 0;
        gbc3.anchor = GridBagConstraints.CENTER;
        gbc3.ipadx = 30;
        p.add(pn2, gbc3);
        gbc3.ipadx = 0;
        p.revalidate();
        p.setVisible(true);
        while (it != iteration && conf != 0) {
            if (paus[0]) {
                         }
         else {
        iteration++;
        int[] tab = Algorithmes.region(tab2);
        int conf2 = Algorithmes.Conflits(tab);
        int[] change = Tabou.swap(tab2, tab);
        if (conf2 < conf || Tabou.notexistabou(ltabou, change)) {
            tab2 = tab;
            conf = conf2;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
            }
            hold = null;
            hold = new Panneau(reine);
            pa = null;
            pa = new JPanel[reine][reine];
            hold.Echiquier(pa, reine);
            Panneau.Placernreines(pa, tab2);
            o++;
            list.add(new String[]{"<html><font color='blue'/><b><code>" + (o-1) + "</code></b></html>",
                "<html><font color='blue'/><b><code>" + Algorithmes.toString(tab2) + "</code></b></html>",
                "<html><font color='blue'/><b><code>" + conf + "</code></b></html>",
                "<html><font color='blue'/><b><code>" + Panneau.listabou(ltabou, reine) + "</code></b></html>"});
            t.ajouterligne(list.get(o - 1));
            pn.removeAll();
            gbc3.gridx = 0;
            gbc3.gridy = 1;
            pn.add(hold, gbc3);
            gbc3.gridy = 2;
            cnf.removeAll();
            cnf.add(new JLabel("Conflits : " + conf), gbc3);
            cnf.revalidate();
            pn.add(cnf, gbc3);
            Liter = new JLabel("Iteration : " + iteration + "/" + it);
            Piter.removeAll();
            Piter.add(Liter);
            Piter.revalidate();
            gbc3.gridy = 0;
            pn.add(Piter, gbc3);
            pn.revalidate();
            Tabou.miseajour(ltabou, change, reine, ten);
        } else {
            change[0] = 0;
            change[1] = 0;
            o++;
            list.add(new String[]{"<html><font color='black'/>" + (o-1) + "</html>",
                "<html><font color='black'/><code>" + Algorithmes.toString(tab) + "</code></html>",
                "<html><font color='black'/><code>" + conf2 + "</code></html>",
                "<html><font color='black'/>" + Panneau.listabou(ltabou, reine) + "</html>"});
            t.ajouterligne(list.get(o - 1));
            Tabou.miseajour(ltabou, change, reine, ten);


                }

            }

        }
        pause.setEnabled(false);
        if (iteration == it) {
            JComponent[] inputs2 = new JComponent[]{
                new JLabel("Toutes les " + iteration + " iterations ont été exaucés")};
            JOptionPane.showMessageDialog(null, inputs2, "Recherche Tabou !", JOptionPane.PLAIN_MESSAGE);
        }
    }
}); //fin du thread
thread2.start();

        }//fin du else


    }}//fin tabou
}
    
    void relance(){
        final int reine,it;
       JTextField r = new JTextField("");
        JTextField iter = new JTextField("");
        JComponent[] inputs = new JComponent[]{
            new JLabel("Veuillez remplir ces champs avec des nombres "),
            new JLabel("positifs non nuls"), new JLabel("\n"),
            new JLabel("Nombre de Reines (<=20):"), new JLabel("\n"), r, new JLabel("\n"),
            new JLabel("Nombre de relances (jusqu'à 1 million):"), new JLabel("\n"), iter,
            new JLabel("\n")
        };
        JOptionPane.showMessageDialog(null, inputs, "La Relance !", JOptionPane.PLAIN_MESSAGE);
        if(iter.getText().equals("") && r.getText().equals("")){}
        else{
        reine=Algorithmes.string2int(r.getText(), 20);
        it=Algorithmes.string2int(iter.getText(), 1000000);
        if (it==0 || reine==0) {
            Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, 
                   "Veuillez entrer des nombres respéctant les contraintes", "Avertisement", 
                    JOptionPane.ERROR_MESSAGE);
        } else {
            if(it==-1 || reine==-1){
                Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, 
                   "Veuillez entrer des nombres valide, respéctant les contraintes", "Avertisement", 
                    JOptionPane.ERROR_MESSAGE);
            } else {
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean arret = false;
                    int relance=1;
                    int conf, iteration = 0,best;
                    GridBagConstraints gbc3 = new GridBagConstraints();
                    int[] tab2 = new int[reine];
                    Algorithmes.tabaleat(tab2);
                    best = Algorithmes.Conflits(tab2);
                    conf=best;
                    JPanel[][]pa = new JPanel[reine][reine];
                    hold = new Panneau(reine);
                    hold.Echiquier(pa, reine);
                    Panneau.Placernreines(pa, tab2);
                    p.setVisible(false);
                    p.removeAll();
                    JPanel pn = new JPanel(new GridBagLayout());
                    JPanel Piter = new JPanel(new GridBagLayout());
                    String[] titres = {"Relance/Iteration", "Permutation", "Conflits"};
                    LinkedList<String[]> list = new LinkedList();
                    t = new Tables(titres);
                    int o = 1;
                    final JPanel pn2 = new JPanel(new GridBagLayout());
                    list.add(new String[]{"<html><font color='green'/><b>" + relance+"/"+(o - 1) + "</b></html>",
                        "<html><font color='green'/><b><code>" + Algorithmes.toString(tab2) + "</code></b></html>",
                        "<html><font color='green'/><b><code>" + best + "</code></b></html>"});
                    t.ajouterligne(list.get(o - 1));
                    t.affichetab("La Relance");
                    //pause
                     final boolean[] paus = {false, true};
                    final JButton pause = new JButton("Pause");
                    final JButton rep = new JButton("Reprendre");
                    rep.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            p.setVisible(false);
                            pn2.remove(rep);
                            GridBagConstraints gbc2 = new GridBagConstraints();
                            gbc2.gridx = 0;
                            gbc2.gridy = 0;
                            gbc2.anchor = GridBagConstraints.EAST;
                            gbc2.ipadx = 0;
                            pn2.add(pause, gbc2);
                            pn2.revalidate();
                            p.revalidate();
                            p.setVisible(true);
                            paus[0] = false;
                        }
                    });
                    pause.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            if (paus[1]) {
                                GridBagConstraints gbc2 = new GridBagConstraints();
                                gbc2.gridx = 0;
                                gbc2.gridy = 0;
                                gbc2.anchor = GridBagConstraints.EAST;
                                gbc2.ipadx = 0;
                                p.setVisible(false);
                                pn2.remove(pause);
                                pn2.add(rep, gbc2);
                                pn2.revalidate();
                                p.revalidate();
                                p.setVisible(true);
                                paus[0] = true;
                            }
                        }
                    });
                    //fin pause
                    Piter.add(new JLabel("Iterations : " + iteration + ",relance :"+relance+"/" + it));
                    gbc3.gridy = 0;
                    gbc3.gridx = 0;
                    pn.add(Piter, gbc3);
                    gbc3.gridx = 0;
                    gbc3.gridy = 1;
                    pn2.add(t, gbc3);
                    gbc3.gridx=0;
                    gbc3.gridy=2;
                    pn2.add(new JLabel("<html><font color='green'>Début d'une relance</font>"
                            + "<br><font color='blue'>Amélioration</font><br>"
                            + "<font color='red'>Bloquage</font><br><font color='black'>Aucune amélioration"
                            + "</font></html>"),gbc3);
                    gbc3.gridx=0;
                    gbc3.gridy=0;
                    gbc3.anchor=GridBagConstraints.EAST;
                    pn2.add(pause,gbc3);
                     gbc3.anchor=GridBagConstraints.CENTER;
                    gbc3.gridx = 0;
                    gbc3.gridy = 1;
                    pn.add(hold, gbc3);
                    JPanel cnf = new JPanel();
                    gbc3.gridy = 2;
                    cnf.add(new JLabel("Conflits : " + best));
                    pn.add(cnf, gbc3);
                    gbc3.gridx = 0;
                    gbc3.gridy = 0;
                    gbc3.ipadx=30;
                    p.add(pn, gbc3);
                    gbc3.gridx=1;
                    gbc3.gridy=0;
                    gbc3.ipadx=0;
                    p.add(pn2,gbc3);
                    p.revalidate();
                    p.setVisible(true);
                    while(relance!=it+1 && best!=0){
                    while (best != 0 && arret != true) {
                        if(paus[0]){}
                        else{
                        iteration++;
                        int[] tabl = Algorithmes.region(tab2);
                        int confl = Algorithmes.Conflits(tabl);
                        if (confl >= conf) {
                            o++;
                            list.add(new String[]{"<html><font color='red'/><b>" + relance+"/"+(o - 1) + "</b></html>",
                                "<html><font color='red'/><b><code>" + Algorithmes.toString(tabl) + "</code></b></html>",
                                "<html><font color='red'/><b><code>" + confl + "</code></b></html>"});
                            t.ajouterligne(list.getLast());
                            arret = true;
                            
                        } 
                        else {if(confl<best){
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {
                            }
                            best=confl;
                            o++;
                            tab2 = tabl;
                            conf = confl;
                            pa = new JPanel[reine][reine];
                            hold = null;
                            hold = new Panneau(reine);
                            hold.Echiquier(pa, reine);
                            Panneau.Placernreines(pa, tab2);
                            list.add(new String[]{"<html><font color='blue'/><b>" + relance+"/"+(o - 1) + "</b></html>",
                                "<html><font color='blue'/><b><code>" + Algorithmes.toString(tab2) + "</code></b></html>",
                                "<html><font color='blue'/><b><code>" + conf + "</code></b></html>"});
                            t.ajouterligne(list.getLast());
                            pn.removeAll();
                            Piter.removeAll();
                            gbc3.gridx = 0;
                            gbc3.gridy = 1;
                            pn.add(hold, gbc3);
                            gbc3.gridy = 2;
                            cnf.removeAll();
                            cnf.add(new JLabel("Conflits : " + conf));
                            pn.add(cnf, gbc3);
                            Piter.add(new JLabel("Iteration : " + iteration + ",relance :"+relance+"/" + it));
                            Piter.revalidate();
                            gbc3.gridy = 0;
                            pn.add(Piter, gbc3);
                            pn.revalidate();}
                        else{o++;
                        tab2=tabl;
                        conf=confl;
                        list.add(new String[]{"<html><font color='black'/>" + relance+"//"+(o - 1) + "</html>",
                                "<html><font color='black'/>" + Algorithmes.toString(tab2) + "</html>",
                                "<html><font color='black'/>" + conf + "</html>"});
                            t.ajouterligne(list.getLast());
                        }
                        }
                    }
                  } //prépartion pour la prochaine  relance
                   if(arret==true && relance!=it) {
                    relance++;
                    o=1;
                   iteration=0;
                    arret=false;
                    tab2=null;
                    tab2=new int[reine];
                    Algorithmes.tabaleat(tab2);
                    conf = Algorithmes.Conflits(tab2);
                            list.add(new String[]{"<html><font color='green'/><b>" + relance+"/"+(o - 1) + "</b></html>",
                                "<html><font color='green'/><b><code>" + Algorithmes.toString(tab2) + "</code></b></html>",
                                "<html><font color='green'/><b><code>" + conf + "</code></b></html>"});
                            t.ajouterligne(list.getLast());
                            }
                   else{relance++;}
                    }
                    pause.setEnabled(false);
                    if (relance == it+1 && conf!=0) {
                        JComponent[] inputs2 = new JComponent[]{
                            new JLabel("Toutes les " + (relance-1) + " relances ont été exaucés")};
                        JOptionPane.showMessageDialog(null, inputs2, "La Relance !", JOptionPane.PLAIN_MESSAGE);
                    }
                    //
                }
            });
            th.start();
        }
        }}
    }// fin relance

    void comparaison(){
        final int reine;
       JTextField r = new JTextField();
        JTextField iter = new JTextField();
        JComponent[] inputs = new JComponent[]{
            new JLabel("Veuillez remplir ces champs avec des nombres "),
            new JLabel("positifs non nuls"), new JLabel("\n"),
            new JLabel("Nombre de Reines (<= 1000):"), new JLabel("\n"), r, new JLabel("\n")
        };
        JOptionPane.showMessageDialog(null, inputs, "Comparaison !", JOptionPane.PLAIN_MESSAGE);
        reine=Algorithmes.string2int(r.getText(), 1000);
        if (reine==0) {
            Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, 
                   "Veuillez entrer des nombres respéctant les contraintes", "Avertisement", 
                    JOptionPane.ERROR_MESSAGE);
        } else {
            if(reine==-1){
                Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, 
                   "Veuillez entrer des nombres valide, respéctant les contraintes", "Avertisement", 
                    JOptionPane.ERROR_MESSAGE);
            }
            
            else{
                Thread t=new Thread(new Runnable(){
                public void run(){
            p.removeAll();
            GridBagConstraints gbc=new GridBagConstraints();
            JProgressBar prog = new JProgressBar();
            prog.setIndeterminate(true);
            prog.setPreferredSize(new Dimension(100,30));
            JPanel ait=new JPanel(new GridBagLayout());
            JPanel rec=new JPanel(new GridBagLayout());
            gbc.gridx=0;
            gbc.gridy=0;
            gbc.insets=new Insets(8,8,8,8);
            ait.add(new JLabel("Amélioration itérative"),gbc);
            rec.add(new JLabel("Recuit Simulé"),gbc);
            gbc.gridx=1;
            ait.add(prog,gbc);
            gbc.gridx=0;
            gbc.gridy=0;
            p.add(ait,gbc);
            gbc.gridy=1;
            p.add(rec,gbc);
            p.setVisible(false);
            p.revalidate();
            p.setVisible(true);
            long start=System.currentTimeMillis();
            int status=aitcomp(reine);
            long end=System.currentTimeMillis()-start;
            end = (long) (end/1000F);
            ait.remove(prog);
            gbc.gridx=1;
            gbc.gridy=0;
            String bl;
            if(status!=0){bl="Bloqué";}
            else bl="OK";
            ait.add(new JLabel("Fini ! Status :"+bl+" Durré :"+end+"sec"),gbc);
            ait.revalidate();
            rec.add(prog,gbc);
            rec.revalidate();
            start=System.currentTimeMillis();
            recuitcomp(reine);
            end=System.currentTimeMillis()-start;
            end = (long) (end/1000F);
            rec.remove(prog);
            rec.add(new JLabel("Fini ! "+" Durré :"+end+"sec"),gbc);
            rec.revalidate();
                }
                });
            t.start();
            }
    
    
    }



}
    //amélioratio itérative
   static int aitcomp(int reine){
        int[] tab=new int[reine];
        Algorithmes.tabaleat(tab);
        int conf=Algorithmes.Conflits(tab);
        int [] tab2=Algorithmes.region(tab);
        int conf2=Algorithmes.Conflits(tab2);
    while(conf2<conf){
    tab=tab2;
    conf=conf2;
    tab2=Algorithmes.region(tab);
    conf2=Algorithmes.Conflits(tab2);
    }
    return conf;
    }
   //recuit simulé
   static void recuitcomp(int reine){
   int[] tab=new int[reine];
   float[] param=new float[2]; // ce paramètre ne sert à rien içi, juste pour utiliser accepter
   Algorithmes.tabaleat(tab);
   int conf=Algorithmes.Conflits(tab);
   int conf2;
   float prob=(float) 0.5;
   float temp=Recuit.tinit(prob, reine);
   while(conf!=0){
   int[] tab2=Recuit.voisin(tab);
   conf2=Algorithmes.Conflits(tab2);
   if(Recuit.accepter(conf2-conf, temp, param)){
   conf=conf2;
   tab=tab2;
   }
   temp=(float) (temp*0.99); //diminution de la température
   }
       System.out.println(""+Algorithmes.toString(tab)+" :\t"+conf);
   }
   
   static void puraleatcomp(int reine){
   int[]tab=new int[reine];
   Algorithmes.tabaleat(tab);
   int conf=Algorithmes.Conflits(tab);
   while(conf!=0){
   int[] tab2=new int[reine];
   Algorithmes.tabaleat(tab2);
   int conf2=Algorithmes.Conflits(tab2);
   if(conf2<conf){conf=conf2; tab=tab2;}
   }
   }
   
    static void taboucomp(int reine){
   int[] tab=new int[reine];
   int t=3;
   int[] tab2;
   int[] swap;
   int conf2;
   Algorithmes.tabaleat(tab);
   Map<Integer,int[]> ltabou=Tabou.listabou(reine);
   int conf=Algorithmes.Conflits(tab);
   while(conf!=0){
   tab2=Algorithmes.region(tab);
   conf2=Algorithmes.Conflits(tab2);
   swap=Tabou.swap(tab, tab2);
   if(conf2<conf || Tabou.notexistabou(ltabou, swap)){
   tab=tab2;
   conf=conf2;
   Tabou.miseajour(ltabou, swap, conf, t);
   }
   }
  
   }
    
    static void relancecomp(int reine){
    int[] tab=new int[reine];
        Algorithmes.tabaleat(tab);
        int conf=Algorithmes.Conflits(tab);
        int [] tab2=Algorithmes.region(tab);
        int conf2=Algorithmes.Conflits(tab2);
        while(conf!=0){
            Algorithmes.tabaleat(tab);
        conf=Algorithmes.Conflits(tab);
        tab2=Algorithmes.region(tab);
       conf2=Algorithmes.Conflits(tab2);
    while(conf2<conf){
    tab=tab2;
    conf=conf2;
    tab2=Algorithmes.region(tab);
    conf2=Algorithmes.Conflits(tab2);
    }}
    }
   
   static String tabousanscyclagecomp(int reine){
   int[] tab=new int[reine];
   int[] prec=new int[reine]; // ce variable a été introduit pour détecté le cyclage
   int t=3;
   int[] tab2;
   int[] swap;
   int conf2;
   Algorithmes.tabaleat(tab);
   Map<Integer,int[]> ltabou=Tabou.listabou(reine);
   int conf=Algorithmes.Conflits(tab);
   while(conf!=0){
   tab2=Algorithmes.region(tab);
   conf2=Algorithmes.Conflits(tab2);
   swap=Tabou.swap(tab, tab2);
   if(conf2<conf || Tabou.notexistabou(ltabou, swap)){
   if(!(Algorithmes.nothesame(prec, tab2))){
       return "Cyclage "+conf;
   }
   else{
   prec=tab;
   tab=tab2;
   conf=conf2;
   Tabou.miseajour(ltabou, swap, conf, t);
   }
   }
  
   }
        return "OK";
   
   }
   
    public static void main(String[] args){
        relancecomp(100);
//        System.out.println("");
        System.out.println("ok");
}
}
