/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw2.ed.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author kristiam
 */
public class ThreadN implements ServletContextListener {

    private MyThreadClass myThread = null;

    public void contextInitialized(ServletContextEvent sce) {
        if ((myThread == null) || (!myThread.isAlive())) {
            myThread = new MyThreadClass();
           
             myThread.start();
             
        }
    }

    public void contextDestroyed(ServletContextEvent sce){
        try {
            myThread.doShutdown();
            myThread.interrupt();
        } catch (Exception ex) {
        }
    }
}
