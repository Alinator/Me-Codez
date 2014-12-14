/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ali
 */
public class Server extends UnicastRemoteObject implements MarketPlace {
    static Map<CallBack,Object[]> WishedItems= new HashMap<>();
    static Map<Object[],CallBack> ItemsInMarketPlace=new HashMap<>();
    static Map<String,CallBack> objects = new HashMap<>();
    static MarketPlace stub=null;
    static int clients =0;
    
    public Server() throws RemoteException{
    }
    
      public static void main(String args[]) {
        
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            Server obj = new Server();
            Bank bankobj =  new BankImpl("Nordea") {};
            //stub = (MarketPlace) UnicastRemoteObject.exportObject(obj, 0);
            // Bind the remote object's stub in the registry
           // Registry registry = LocateRegistry.getRegistry();
            //registry.bind("marketplace", stub);
            Naming.rebind("marketplace", obj);
            Naming.rebind("Nordea",bankobj);
            
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
    @Override
    public void buyItem(CallBack buyerref,Account myAccount,String mywisheditem) throws RemoteException {
        outerloop:
          for(Object[] itemList : ItemsInMarketPlace.keySet()){
            
                for(int x=0; x < itemList.length; x++){
                 
                 System.out.println(itemList[x]);
                 String theitem = itemList[x].toString();
                 x++;
                 System.out.println(itemList[x]);
                 int theprice = Integer.parseInt((String)itemList[x].toString());
                 
                 if(theitem.equalsIgnoreCase(mywisheditem) ){
                    if(myAccount.getBalance() > theprice){
                        System.out.println("Im In!");
                       try{
                         myAccount.withdraw(theprice);
                         ItemsInMarketPlace.get(itemList).getTraderAccount().deposit(theprice);
                         
                         
                         ItemsInMarketPlace.get(itemList).itemSoled(theitem);
                         buyerref.boughtItem(theitem);
                         
                         ItemsInMarketPlace.remove(itemList);
                        break outerloop;
                        
                     } catch (RejectedException ex) {
                         Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }else{
                     buyerref.cannotBuy();
                     break;
                 }
                     }else{
                      break;
                 }
                    
                 
                 System.out.println("--------------------------------");
                }
               }
          
        
    }
  @Override
    public void sellItem(CallBack ref,String name, String price) throws RemoteException {
        
        int myprice = Integer.parseInt(price);
        Object [] itemandprice={name,myprice};
        ItemsInMarketPlace.put(itemandprice,ref);
        
        checkIfMatch(name,myprice,ref);
    }
    @Override
    public void checkIfMatch(String n,int mp,CallBack sellerRef) throws RemoteException {
         boolean found = false;
            for(Object[] buyerWishList : WishedItems.values()){
               for(CallBack itemBuyer : WishedItems.keySet()){
                for(int x=0; x < buyerWishList.length; x++){
                 
                 System.out.println(buyerWishList[x]);
                 String theitem = buyerWishList[x].toString();
                 x++;
                 System.out.println(buyerWishList[x]);
                 int theprice = Integer.parseInt((String)buyerWishList[x].toString());
                 x++;
                 System.out.println(buyerWishList[x]);
                 String thename = buyerWishList[x].toString();
                 
                 System.out.println("--------------------------------");
                   
                   if(theitem.equalsIgnoreCase(n) && mp < theprice && thename.equalsIgnoreCase(itemBuyer.getTraderName())){
                    
                       try {
                           System.out.println("-------->: "+itemBuyer.getTraderName() );
                           itemBuyer.callback("There is now a "+theitem+" in the marketplace!",itemBuyer.getTraderName());
                           found = true;
                           break;
                       } catch (RemoteException ex) {
                           Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                       }
                       
                   }else{
                       break;
                   }     
               }
            }
        }
            
    }
    
    @Override
    public void wishForItem(CallBack traderef,String wishedname, String price, String wisher) throws RemoteException {
        
        int myprice = Integer.parseInt(price);
        Object [] itemAndPrice={wishedname,myprice,wisher};
        WishedItems.put(traderef,itemAndPrice);
        System.out.println(WishedItems);        
        
    }
       @Override
    public void showItems(CallBack ref) throws RemoteException {
        ref.viewItems(ItemsInMarketPlace);
    }
    
    @Override
    public void register(CallBack reference,String nameOfClient) throws RemoteException {
         
         objects.put(nameOfClient,reference);
         System.out.println(objects);
    }

    @Override
    public void unregister(CallBack ref) throws RemoteException{
        
        ArrayList<Object[]> tmplist = new ArrayList<>();
        
        for(Object [] myitems : ItemsInMarketPlace.keySet()){
            if(ItemsInMarketPlace.get(myitems).equals(ref)){
                tmplist.add(myitems);
                WishedItems.remove(ItemsInMarketPlace.get(myitems));
            }
        }
        
        for(int x =0; x < tmplist.size();x++){
            ItemsInMarketPlace.remove(tmplist.get(x));
        }
            
        
    }

    @Override
    public void welcome() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}