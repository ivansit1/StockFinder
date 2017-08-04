/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.thread;

import java.awt.Image;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import stock.constant.ConstantVariables;
import stock.finder.MainPage;
import stock.info.StockDetails;
import stock.util.HtmlConnectUtil;

/**
 *
 * @author Ivan
 */
public class StockGraphThread extends Thread {
     private JLabel horlyGraphLbl;
     private JLabel dailyGraphLbl;
    private JLabel weeklyGraphLbl;
    private JLabel monthlyGraphLbl;
    private String index;
    private String tempIndex;
    private String selectedGraphType;
     public StockGraphThread(String index, JLabel horlyGraphLbl, JLabel dailyGraphLbl, JLabel weeklyGraphLbl, JLabel monthlyGraphLbl, ButtonGroup graphBtnGrp) {         
       this.horlyGraphLbl = horlyGraphLbl;
       this.dailyGraphLbl = dailyGraphLbl;
       this.weeklyGraphLbl = weeklyGraphLbl;
       this.monthlyGraphLbl = monthlyGraphLbl;
       
       this.index = index;
       this.selectedGraphType = getSelectedButtonText(graphBtnGrp);       
   }
    
     public void run() {
         NumberFormat formatter = new DecimalFormat("00000");         
         tempIndex = formatter.format(Integer.parseInt(index));
        setLabelGraph(horlyGraphLbl, "5023", selectedGraphType);
        setLabelGraph(dailyGraphLbl, "6", selectedGraphType);
        setLabelGraph(weeklyGraphLbl, "11", selectedGraphType);
        setLabelGraph(monthlyGraphLbl, "18", selectedGraphType);
     }
     
     private void setLabelGraph(JLabel stockGraphLbl, String gPeriod, String graphType){
           try {    
            Object[] args = {tempIndex, gPeriod};             
            URL url = new URL(ConstantVariables.getStockGraphUrl(graphType).format(args));
            Image image = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(image); 
            stockGraphLbl.setIcon(icon);
        } catch (Exception ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        } 
     }
     
     private String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
}
