/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ali
 */
public class trader extends UnicastRemoteObject implements Serializable, CallBack{
    private transient Scanner scan = null;
    MarketPlace stub=null;
    trader reference = this;
    public String traderName ="";
    Account myAccount;
    Bank bankObj;
    
    public trader() throws RemoteException{
        super();
        
    }
    
        public void traderLoop(MarketPlace s) throws RejectedException{  
            
          try {  
            scan  = new Scanner(System.in);
            this.stub = s;
            String nameOfClient= JOptionPane.showInputDialog("Please register with entering your name:");
            
            setTradername(nameOfClient);
            try {
                bankObj = (Bank) Naming.lookup("Nordea");
                stub.register(this,nameOfClient);
                String accountname = JOptionPane.showInputDialog("Please create your bank account by putting your name:");
                myAccount = bankObj.newAccount(accountname);
                System.out.println("account Successfully created on your name");
            } catch (NotBoundException ex) {
                Logger.getLogger(trader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(trader.class.getName()).log(Level.SEVERE, null, ex);
            }
         
            while(true){
                System.out.print("search,wish or purchase any item! Enter command:");
                String input = scan.nextLine();
                
                if(input.equalsIgnoreCase("wish item")){
                    
                    System.out.print("What item do you want to wish?:");
                    System.out.print("Item name:");
                    String itemname= scan.nextLine();
                    System.out.print("wished price:");
                    String price= scan.nextLine();
                    
                    
                    try {
                        stub.wishForItem(this,itemname,price,getTraderName());
                    } catch (RemoteException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
                if(input.equalsIgnoreCase("sell")){
                    System.out.println("What item do you want to sell?:");
                    System.out.println("Item name:");
                    String myitemname= scan.nextLine();
                    System.out.print("wished price:");
                    String myprice= scan.nextLine();
                    
                    try {
                        stub.sellItem(reference,myitemname,myprice);
                    } catch (RemoteException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
                
                if(input.equalsIgnoreCase("buy")){
                    System.out.println("What item do you want to buy?:");
                    System.out.println("Item name:");
                    String mywisheditemname= scan.nextLine();
                    
                    try {
                        stub.buyItem(reference,myAccount,mywisheditemname);
                    } catch (RemoteException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
                if(input.equalsIgnoreCase("show items")){
                     try {
                      stub.showItems(reference);
                    } catch (RemoteException ex) {
                      Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                
                if(input.equalsIgnoreCase("deposit")){
                    System.out.println("how much do you want to deposit?;");
                    float dep = scan.nextFloat();
                    myAccount.deposit(dep);
                }
                
                if(input.equalsIgnoreCase("balance")){
                    System.out.println(myAccount.getBalance());
                }
                
                if(input.equalsIgnoreCase("unregister")){
                    try {
                        
                        stub.unregister(this);
                        System.exit(0);
                    } catch (RemoteException ex) {
                        Logger.getLogger(trader.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        } catch (RemoteException ex) {
            Logger.getLogger(trader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void callback(String n,String name) throws RemoteException {
        if(name.equalsIgnoreCase(getTraderName())){
             System.out.println(n);
        }    
    }

    @Override
    public void setTradername(String name) throws RemoteException {
        this.traderName = name;
    }

    @Override
    public void itemSoled(String s) throws RemoteException {
        
        System.out.println("\n Your item"+ s +" has now beed soled");
    
    }

    @Override
    public void viewItems(Map<Object[], CallBack> map) throws RemoteException {
        
        for(Object[] itemsinlist : map.keySet()){
            
            for(int x =0; x < itemsinlist.length;x++){
                System.out.print(itemsinlist[x]+" : ");
            }
            System.out.println("");
        }
    
    }

    @Override
    public Account getTraderAccount() throws RemoteException {
        return myAccount;
    }

    @Override
    public void cannotBuy() throws RemoteException {
        System.out.println("Not Enough money to buy the product!");
    }

    @Override
    public void boughtItem(String item) throws RemoteException {
        System.out.println("You successfully bought a(n) "+item+" !");
    }

    @Override
    public String getTraderName() throws RemoteException {
        return this.traderName;
    }
}