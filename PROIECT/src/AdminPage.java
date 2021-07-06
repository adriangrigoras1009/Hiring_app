import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class AdminPage extends JFrame{
    Application nou;
    JList Users;
    JTree Companies;
    DefaultMutableTreeNode companies;
    DefaultTreeModel treeModel;
    Vector<String> vector_Users;

    public AdminPage(Application nou, String titlu) {
        super(titlu);
        this.nou = nou;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));
        setSize(1400, 800);
        companies = new DefaultMutableTreeNode("Companies");
        treeModel = new DefaultTreeModel(companies);
        create_tree(companies);
        Companies = new JTree(treeModel);
        add(Companies);
        vector_Users = new Vector<String>();
        for(User user : nou.users)
            vector_Users.add(user.cv.getPersonal_datas().getNume() + " " + user.cv.getPersonal_datas().getPrenume());
        Users = new JList(vector_Users);
        add(Users);
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
    }

    private void create_tree(DefaultMutableTreeNode root) {
        for(Company iterator : nou.companies) {
            DefaultMutableTreeNode comp = new DefaultMutableTreeNode(iterator.nume_companie);
            root.add(comp);
            for(Department iterator2 : iterator.departamente) {
                DefaultMutableTreeNode dep = new DefaultMutableTreeNode(iterator2.getClass().getName());
                comp.add(dep);
                DefaultMutableTreeNode lista_angajati = new DefaultMutableTreeNode("Lista angajati");
                dep.add(lista_angajati);
                DefaultMutableTreeNode lista_joburi = new DefaultMutableTreeNode("Lista joburi");
                dep.add(lista_joburi);
                DefaultMutableTreeNode salariu = new DefaultMutableTreeNode("Buget Salarii: " + iterator2.getTotalSalaryBudget());
                dep.add(salariu);
                for(Employee iterator3 : iterator2.angajati) {
                    DefaultMutableTreeNode emp = new DefaultMutableTreeNode(iterator3.cv.getPersonal_datas().getNume() + " " + iterator3.cv.getPersonal_datas().getPrenume());
                    lista_angajati.add(emp);
                }
                for(Job iterator4: iterator2.joburi_disponibile) {
                    DefaultMutableTreeNode job = new DefaultMutableTreeNode(iterator4.nume_job);
                    lista_joburi.add(job);
                }
            }
        }
    }
}
