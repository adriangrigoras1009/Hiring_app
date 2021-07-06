import javax.swing.*;;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ManagerPage extends JFrame{
    private JList list1;
    private JButton button1;
    private JButton button2;
    Application nou;
    Manager manager;

    public ManagerPage(Application nou, String titlu, Manager manager) {
        super(titlu);
        this.manager = manager;
        this.nou = nou;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 800);
        JPanel requesturi = new JPanel();
        requesturi.setPreferredSize(new Dimension(1000,800));
        button1 = new JButton("Accept");
        button2 = new JButton("Reject");
        button1.setPreferredSize(new Dimension(100,100));
        button2.setPreferredSize(new Dimension(100,100));
        Vector<Request<Job,Consumer>> tot = new Vector<Request<Job,Consumer>>();
        for(int j = 0; j < manager.cereri.size(); j++) {
            tot.add(manager.cereri.get(j));
        }
        list1 = new JList(tot);
        list1.setPreferredSize(new Dimension(800,200));
        requesturi.add(button1);
        requesturi.add(button2);
        requesturi.add(list1);
        add(requesturi);
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
        JTextArea text = new JTextArea("Nu mai e loc", 10, 100);
        JButton process = new JButton("process");
        process.setMaximumSize(new Dimension(70,70));
        requesturi.add(process);
        process.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < nou.companies.size(); i++) {
                    if (manager.nume_companie.equals(nou.companies.get(i).nume_companie)) {
                        for (int j = 0; j < nou.companies.get(i).getJobs().size(); j++)
                            manager.process(nou.companies.get(i).getJobs().get(j));
                        break;
                    }
                }
                tot.removeAllElements();
                setVisible(false);
                setVisible(true);
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index_departamente = 0;
                int index_job_disp = 0;
                int index_companie = 0;
                int index_user = 1000;
                int index = list1.getSelectedIndex();
                Job job = manager.cereri.get(index).getKey();
                if(job.valabil == true) {
                    User user = (User) manager.cereri.get(index).getValue1();
                    for (User caut : Application.getInstance().users) {
                        if (caut.equals(user)) {
                            index_user = Application.getInstance().users.indexOf(caut);
                            break;
                        }
                    }
                    Employee employee = user.convert();
                    for (Company comp : nou.companies) {
                        if (comp.nume_companie.equals(job.nume_companie)) {
                            index_companie = nou.companies.indexOf(comp);
                            for (Department dep : comp.departamente) {
                                if (dep.getClass().getName().equals(job.nume_departament)) {
                                    index_departamente = comp.departamente.indexOf(dep);
                                    comp.add(employee, dep);
                                }
                            }
                        }
                    }
                    for (Job cautare : Application.getInstance().companies.get(index_companie).departamente.get(index_departamente).joburi_disponibile) {
                        if (cautare.equals(job)) {
                            index_job_disp = Application.getInstance().companies.get(index_companie).departamente.get(index_departamente).joburi_disponibile.indexOf(cautare);
                            break;
                        }
                    }
                    Application.getInstance().companies.get(index_companie).departamente.get(index_departamente).joburi_disponibile.get(index_job_disp).nr_candidati--;
                    if (Application.getInstance().companies.get(index_companie).departamente.get(index_departamente).joburi_disponibile.get(index_job_disp).nr_candidati == 0)
                        Application.getInstance().companies.get(index_companie).departamente.get(index_departamente).joburi_disponibile.get(index_job_disp).valabil = false;
                    manager.cereri.remove(0);
                    tot.remove(index);
                    requesturi.remove(text);
                    setVisible(false);
                    setVisible(true);
                }
                else {

                    requesturi.add(text);
                    setVisible(false);
                    setVisible(true);
                }
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list1.getSelectedIndex();
                manager.cereri.remove(index);
                tot.remove(index);
                setVisible(false);
                setVisible(true);
            }
        });
    }
}
