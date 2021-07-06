import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;
import java.util.Vector;

public class ProfilePage extends JFrame {
    JList prieteni;
    JTextField search;
    JTextArea informatii_resume;
    public ProfilePage(Consumer consumer, String titlu) {
        super(titlu);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 800);
        JPanel panel = new JPanel();
        Vector<Consumer> vector_prieteni = new Vector<Consumer>();
        for(Consumer caut : consumer.cunoscuti) {
            vector_prieteni.add(caut);
        }
        prieteni = new JList(vector_prieteni);
        search = new JTextField();
        search.setPreferredSize(new Dimension(200,50));
        panel.add(search);
        add(panel,BorderLayout.NORTH);
        Vector<Consumer> consumers = new Vector<Consumer>();
        consumers.addAll(Application.getInstance().users);
        for(Company company : Application.getInstance().companies) {
            consumers.addAll(company.getAllEmployees());
            consumers.add(company.manager);
        }
        DefaultListModel model = new DefaultListModel();
        JList cautat = new JList(model);
        cautat.setPreferredSize(new Dimension(200,300));
        panel.add(cautat);
        prieteni.setPreferredSize(new Dimension(400,300));
        if(this.getClass().getName().equals("EmployeePage")) {
            Employee emp = (Employee) consumer;
            JTextArea field = new JTextArea("salariu: ");
            String salariu = Long.toString(((Employee) consumer).salariu);
            field.append(salariu + "\n");
            field.append("nume companie: " + ((Employee) consumer).nume_companie);
            field.append("\n" + "nume: " + consumer.cv.getPersonal_datas().getNumeComplet());
            field.setPreferredSize(new Dimension(400,50));
            JScrollPane pane = new JScrollPane(field);
            panel.add(pane);
            pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        }
        if(this.getClass().getName().equals("UserPage")) {
            User user = (User) consumer;
            Vector<String> notifications = new Vector<String>();
            notifications.addAll(((User) consumer).notificari);
            JList notif = new JList(notifications);
            JScrollPane pane = new JScrollPane(notif);
            pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            pane.setPreferredSize(new Dimension(400,50));
            panel.add(pane);
        }
        if(this.getClass().getName().equals("RecruiterPage")) {
            Recruiter rec = (Recruiter) consumer;
            JTextArea field = new JTextArea("salariu: ");
            String salariu = Long.toString(((Recruiter) consumer).salariu);
            field.append(salariu + "\n");
            field.append("nume companie: " + ((Recruiter) consumer).nume_companie);
            field.append("\n" + "nume: " + consumer.cv.getPersonal_datas().getNumeComplet());
            field.append("\n" + "rating: " + ((Recruiter) consumer).rating);
            field.setPreferredSize(new Dimension(400,100));
            JScrollPane pane = new JScrollPane(field);
            panel.add(pane);
            pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        }
        panel.add(prieteni);
        JButton back = new JButton("Back to OpenPage");
        add(back,BorderLayout.SOUTH);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                OpenPage open = new OpenPage(Application.getInstance(), "open");
                open.setVisible(true);
            }
        });
        search.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    model.removeAllElements();
                    String nume_cautat = search.getText();
                    consumers.sort(new Comparator<Consumer>() {
                        @Override
                        public int compare(Consumer o1, Consumer o2) {
                            int c1 = nume_cautat.compareTo(o1.cv.getPersonal_datas().getNumeComplet());
                            int c2 = nume_cautat.compareTo(o2.cv.getPersonal_datas().getNumeComplet());
                            if(Math.abs(c1) > Math.abs(c2))
                                return 1;
                            else if(Math.abs(c1) < Math.abs(c2))
                                return -1;
                            return 0;
                        }
                    });
                    Vector<String> doar_nume = new Vector<String>();
                    for(Consumer fiecare : consumers) {
                        doar_nume.add(fiecare.cv.getPersonal_datas().getNumeComplet());
                    }
                    for(String string : doar_nume)
                        model.addElement(string);
                    cautat.setModel(model);
                    setVisible(false);
                    setVisible(true);
                    JScrollPane tabel = new JScrollPane();
                    informatii_resume = new JTextArea();
                    add(tabel,BorderLayout.CENTER);
                    tabel.getViewport().add(informatii_resume);
                    cautat.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            tabel.setBackground(Color.RED);
                            tabel.setPreferredSize(new Dimension(1400,600));
                            int index = cautat.getSelectedIndex();
                            Consumer selectat = consumers.get(index);
                            tabel.remove(informatii_resume);
                            informatii_resume = new JTextArea();
                            informatii_resume.setPreferredSize(new Dimension(1400,550));
                            informatii_resume.setText("Name: " + selectat.cv.getPersonal_datas().getNumeComplet() + "\nEmail: " + selectat.cv.getPersonal_datas().getEmail());
                            informatii_resume.append("\nTelefon: " + selectat.cv.getPersonal_datas().getTelefon() + "\nData nastere: " + selectat.cv.getPersonal_datas().getData_nastere() + "\nGender: " + selectat.cv.getPersonal_datas().getSex());
                            informatii_resume.append("\nLimbi Vorbite: " + "\n\tBeginner: " + selectat.cv.getPersonal_datas().afisare_limbi(0) + "\n\tAdvanced: " + selectat.cv.getPersonal_datas().afisare_limbi(1) + "\n\tExperienced: " + selectat.cv.getPersonal_datas().afisare_limbi(2));
                            informatii_resume.append("\nEducation");
                            for(int i = 0; i < selectat.cv.getEducation().size(); i++) {
                                informatii_resume.append("\n\t" + selectat.cv.getEducation().get(i).toString());
                                informatii_resume.append("\n");
                            }
                            informatii_resume.append("\nExperience");
                            for(int i = 0; i < selectat.cv.getExperience().size(); i++) {
                                informatii_resume.append("\n\t" + selectat.cv.getExperience().get(i).toString());
                                informatii_resume.append("\n");
                            }
                            add(tabel,BorderLayout.CENTER);
                            tabel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                            tabel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                            tabel.getViewport().add(informatii_resume);
                            setVisible(false);
                            setVisible(true);
                        }
                    });
                }
            }
        });
    }
}
