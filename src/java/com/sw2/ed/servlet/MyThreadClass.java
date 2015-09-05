/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw2.ed.servlet;

import com.sw2.ed.bean.BEvento;
import com.sw2.ed.bean.BRecordatorio;
import com.sw2.ed.dao.DBase;
import com.sw2.ed.dao.DEventos;
import com.sw2.ed.mail.Mail;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kristiam
 */
class MyThreadClass extends Thread{
    Mail m = new Mail();

//    boolean isAlive() {
//        throw new UnsupportedOperationException("Not yet implemented");
//    }

//    void start() {
//        
//        int i=0;
//        while(true)
//        {
//            
//            System.out.println(i);
//            i++;
//        }
//        
//    }

    void doShutdown() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

//    void interrupt() {
//        throw new UnsupportedOperationException("Not yet implemented");
//    }

    @Override
    public void run() {
        
        
        int i=0;
        while(true)
        {
            try {
                Thread.currentThread().sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MyThreadClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            DEventos event = new DEventos();
            ArrayList<BRecordatorio> L = new ArrayList<BRecordatorio>();
            L = event.ListarEventosRecordatorio();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            System.out.println("hilohhh");
            Calendar Cal= Calendar.getInstance();
            
            Date fecha = Cal.getTime();
            for (BRecordatorio tmp : L)
            {
                
                
                
                try {
                    
                    Date record = formatoDeFecha.parse(tmp.getRecordatorio());
                        System.out.println("holaa");
                    if (record.compareTo(fecha) < 0)
                    {
                        //Ingresar el envio de correo con el mensaje
                        //
                        //Recordatorio del evento %nombre del evento (tmp.getNombreEvento();)%
                        //Fecha: record
                        //
                        //
                        System.out.println("h1");
                        m.enviaCorreoRecordatorio(tmp.getCorreo(),tmp.getNombreUsuario(), tmp.getRecordatorio(), tmp.getNombreEvento());
                        //borrar el evento de la lista de eventos
                        event.DesactivarEvento(tmp.getIdcalendario());
                        
                        
                        System.out.println(record);
                        
                        
                        
                        
                    }
                    
                    
                    
                    
                } catch (ParseException ex) {
                    Logger.getLogger(MyThreadClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        
        
    }
    
}
