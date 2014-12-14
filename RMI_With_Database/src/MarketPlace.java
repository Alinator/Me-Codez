/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

/**
 *
 * @author Ali
 */
public interface MarketPlace extends Remote {
    
    void welcome() throws RemoteException;
    public void wishForItem(CallBack tradeRef,String name, String price,String wisher) throws RemoteException;
    public void sellItem(CallBack ref,String name, String price) throws RemoteException;
    public void showItems(CallBack ref)throws RemoteException;
    public void buyItem(CallBack myref,Account myAccount,String mywisheditem)throws RemoteException;
    public void register(CallBack ref,String name,String password) throws RemoteException;
    public void unregister(CallBack ref) throws RemoteException;
    public void checkIfMatch(String n,int mp,CallBack sellerRef) throws RemoteException;
    public void login(CallBack ref,String username,String password) throws RemoteException;
    public void showMyAccountStatus(CallBack ref) throws RemoteException;
    
}
