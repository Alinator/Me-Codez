/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

import com.mysql.jdbc.ResultSet;

/**
 *
 * @author Ali
 */
public interface CallBack  extends Remote{
    
    void callback(String n) throws RemoteException;
    void setTradername(String name) throws RemoteException;
    void itemSoled(String sold)throws RemoteException;
    void boughtItem(String item) throws RemoteException;
    void cannotBuy()throws RemoteException;
    void viewItems(Map<String,String> list)throws RemoteException;
    public void register(String cmd) throws RemoteException;
    public void logIn(String cmd) throws RemoteException;
    public void welcome(String cmd) throws RemoteException;
    public void unregister()throws RemoteException;
    public void showMyAccountStatus(Map<String,String> mylist) throws RemoteException;
    String getTraderName()throws RemoteException;
    Account getTraderAccount()throws RemoteException;
}
