/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.manager;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Profile;

/**
 *
 * @author Алёшечка
 */
public class ManagerWindow extends javax.swing.JFrame {
    private Profile profile;    
    /**
     * Creates new form ManagerWindow
     */
    public ManagerWindow(Profile profile) {
        initComponents();         
        this.profile = profile;
        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension d = tool.getScreenSize();
        this.setBounds(0, 0, d.width, d.height);
        
        ActionListener newCreatOrderListener = (t) -> {
            if (CreateOrder.getCount() == 0) {
                CreateOrder createOrderInternalFrame = new CreateOrder(jDesktopPane1, profile);
                createOrderInternalFrame.setBounds(50, 50, 560, 400);
                jDesktopPane1.add(createOrderInternalFrame);

                createOrderInternalFrame.setVisible(true);
            }
        };
        jButton1.addActionListener(newCreatOrderListener);
        jMenuItem1.addActionListener(newCreatOrderListener);
        
//        ActionListener createOrderListener = new CreateOrderListener();
//        jButton1.addActionListener(createOrderListener);
//        jMenuItem1.addActionListener(createOrderListener);

        ActionListener addCustomerListener = new AddCustomerListener();
        jButton2.addActionListener(addCustomerListener);
        jMenuItem2.addActionListener(addCustomerListener);
        
        ActionListener showCustomerListener = new ShowCustomerListener();
        jMenuItem4.addActionListener(showCustomerListener);
        
        ActionListener addProductListener = new AddProductListener();
        jButton3.addActionListener(addProductListener);
        jMenuItem3.addActionListener(addProductListener);
        
        ActionListener showProductListener = new ShowProductListener();
        jMenuItem5.addActionListener(showProductListener);
        
        jMenuItem7.addActionListener((ActionEvent a) -> {
            if (ShowOrders.getCount() == 0) {
                ShowOrders showOrdersInternalFrame = new ShowOrders(profile);
                showOrdersInternalFrame.setBounds(50, 50, 350, 275);
                jDesktopPane1.add(showOrdersInternalFrame);
                showOrdersInternalFrame.setVisible(true);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        jButton1.setText("CreateOrder");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton1);

        jButton2.setText("AddCustomer");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton2);

        jButton3.setText("AddProduct");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton3);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
        );

        getContentPane().add(jDesktopPane1, java.awt.BorderLayout.CENTER);

        jMenu2.setText("Order");

        jMenuItem1.setText("CreateOrder");
        jMenu2.add(jMenuItem1);

        jMenuItem7.setText("ShowOrders");
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Customer");

        jMenuItem2.setText("AddCustomer");
        jMenu3.add(jMenuItem2);

        jMenuItem4.setText("ShowCustomer");
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Product");

        jMenuItem3.setText("AddProduct");
        jMenu4.add(jMenuItem3);

        jMenuItem5.setText("ShowProduct");
        jMenu4.add(jMenuItem5);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    
    
    private class AddCustomerListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (AddCustomer.getCount() == 0) {
                AddCustomer addCustomerInternalFrame = new AddCustomer();
                addCustomerInternalFrame.setBounds(50, 50, 370, 250);
                jDesktopPane1.add(addCustomerInternalFrame);
                addCustomerInternalFrame.setVisible(true);
            }
        }        
    }
    
    private class ShowCustomerListener implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (ShowCustomer.getCount() == 0) {
                ShowCustomer showCustomerInternalFrame = new ShowCustomer();
                showCustomerInternalFrame.setBounds(50, 50, 320, 300);
                jDesktopPane1.add(showCustomerInternalFrame);
                showCustomerInternalFrame.setVisible(true);
            }
        }      
    }
    
    private class AddProductListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (AddProduct.getCount() == 0) {
                AddProduct addProductInternalFrame = new AddProduct(null);
                addProductInternalFrame.setBounds(50, 50, 255, 235);
                jDesktopPane1.add(addProductInternalFrame);
                addProductInternalFrame.setVisible(true);
            }
        }       
    }
    
    private class ShowProductListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (ProductWindow.getCount() == 0) {
                ProductWindow productWindowInternalFrame = new ProductWindow(jDesktopPane1);
                productWindowInternalFrame.setBounds(50, 50, 565, 330);
                jDesktopPane1.add(productWindowInternalFrame);
                productWindowInternalFrame.setVisible(true);
            }
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
