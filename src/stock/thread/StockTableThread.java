/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.thread;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import stock.constant.ConstantVariables;
import stock.info.StockDetails;
import stock.info.StockHtmlInfo;
import stock.util.HtmlConnectUtil;

/**
 *
 * @author Ivan
 */
public class StockTableThread extends Thread {
    JTable tableStockDetails;
    JComboBox searchItemBox;
    JButton searchbtn;
     public StockTableThread(JTable tableStockDetails, JComboBox searchItemBox, JButton searchbtn) {
       this.tableStockDetails = tableStockDetails;
       this.searchItemBox = searchItemBox;
       this.searchbtn = searchbtn;
   }
    
     public void run() {
         removeAllRow();
         searchbtn.setEnabled(false);
         searchItemBox.setEnabled(false);
         String searchStatus = searchItemBox.getSelectedItem().toString();
          NumberFormat formatter = new DecimalFormat("00000");
         DefaultTableModel model = (DefaultTableModel) tableStockDetails.getModel();
        boolean showAll = false;
        boolean showShort = false;
        boolean showOption = false;
        boolean showFuture = false;
        
        if("All".equals(searchStatus)){
            showAll = true;
        } else if("Short".equals(searchStatus)){
            showShort = true;
        } else if("Option".equals(searchStatus)){
            showOption = true;
        } else if("Future".equals(searchStatus)){
            showFuture = true;
        }

         for(StockHtmlInfo s : ConstantVariables.getAllStocks().getAllStockIndex()){     
             boolean show = false;
               if(showAll){
                   show = true;
               } else if (showShort){
                   if(s.isCanShort()){
                        show = true;
                   }
               } else if (showOption){
                   if(s.isCanOption()){
                        show = true;
                   }
               } else if (showFuture){
                   if(s.isCanFuture()){
                        show = true;
                   }
               }
               
               if(show){
                    String tempNum = formatter.format(Integer.parseInt(s.getStockIndex()));
                    Object[] args = {tempNum};

                 StockDetails stockInfo = new StockDetails(HtmlConnectUtil.createLinkToWebpage(ConstantVariables.getStockDetailsUrl().format(args)), s.getStockIndex());
                 if (stockInfo.isStockExists()){
                     System.out.println(stockInfo.getIndex());
                     model.addRow(new Object[]{ stockInfo.getIndex(), stockInfo.getName() + " (" + stockInfo.getIndex() + ")", stockInfo.getPrice(), stockInfo.getMarketCap() });            
                 }              
               }
         }     
         searchbtn.setEnabled(true);
         searchItemBox.setEnabled(true);
    } 
     
     private void removeAllRow(){
         DefaultTableModel dm = (DefaultTableModel) tableStockDetails.getModel();
        int rowCount = dm.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
     }
}
