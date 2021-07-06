import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Login_Consumer<K> extends JFrame {
    Application my_app;
    ArrayList<String> consumers;
    public Login_Consumer(Application my_app, ArrayList<K> tip, ArrayList<String> consumers, String titlu) {
        super(titlu);
        this.my_app = my_app;
        this.consumers = consumers;
        setSize(1400,800);
        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(),3));
        JTextField name = new JTextField("Pune mail-ul: ");
        JTextField login = new JTextField();
        name.setFont(new Font("Serif",Font.BOLD,40));
        login.setFont(new Font("Serif",Font.BOLD,40));
        JButton log = new JButton("Login: ");
        add(name);
        add(login);
        add(log);
        log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tip.get(0).getClass().getName().equals("User")) {
                    ArrayList<User> lista_de_cautat = (ArrayList<User>) tip;
                    for(User caut : lista_de_cautat) {
                        if(caut.cv.getPersonal_datas().getEmail().equals(login.getText())) {
                              dispose();
                            UserPage nou = new UserPage(caut,"user");
                            nou.setVisible(true);
                        }
                    }
                }
                else if(tip.get(0).getClass().getName().equals("Employee")) {
                    ArrayList<Employee> lista_de_cautat = (ArrayList<Employee>) tip;
                    for(Employee caut : lista_de_cautat) {
                        if(caut.cv.getPersonal_datas().getEmail().equals(login.getText())) {
                            dispose();
                            EmployeePage nou = new EmployeePage(caut,"employee");
                            nou.setVisible(true);
                        }
                    }
                }
                else if(tip.get(0).getClass().getName().equals("Recruiter")) {
                    ArrayList<Recruiter> lista_de_cautat = (ArrayList<Recruiter>) tip;
                    for(Recruiter caut : lista_de_cautat) {
                        if(caut.cv.getPersonal_datas().getEmail().equals(login.getText())) {
                            dispose();
                            RecruiterPage nou = new RecruiterPage(caut, "recruiter");
                            nou.setVisible(true);
                        }
                    }
                }
            }
        });
    }

}
