/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

/**
 *
 * @author Ali
 */
public interface CallBack  extends Remote{
    
    void callback(String n,String name) throws RemoteException;
    void setTradername(String name) throws RemoteException;
    void itemSoled(String sold)throws RemoteException;
    void boughtItem(String item) throws RemoteException;
    void cannotBuy()throws RemoteException;
    void viewItems(Map<Object[],CallBack> map)throws RemoteException;
    String getTraderName()throws RemoteException;
    Account getTraderAccount()throws RemoteException;
}
