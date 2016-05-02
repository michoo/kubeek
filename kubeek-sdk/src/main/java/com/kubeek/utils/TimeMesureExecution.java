/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kubeek.utils;


public class TimeMesureExecution {
    
    private long start,before,after,moyenne,operation,end;
    boolean nano;
    public TimeMesureExecution(boolean nano){
        
        this.nano=nano;
        this.start=0;
        this.before=0;
        this.after=0;
        this.moyenne=0;
        this.operation=0;
        start();

    }
    public TimeMesureExecution(){
        this.start=0;
        this.before=0;
        this.after=0;
        this.moyenne=0;
        this.operation=0;
        start();
    }
    
    public void start(){
        if(nano){
            start = System.nanoTime();
        }else{
            start=System.currentTimeMillis();
          
        }
        
    }
    
    public void before(){
        this.start();
        
    }
    
    public void after(){
        if(nano){
            after = System.nanoTime();
        }else{
            after=System.currentTimeMillis();
        }
        result();
        operation++;
        
    }
    
    public void end(){
        if(nano){
            end = System.nanoTime();
        }else{
            end=System.currentTimeMillis();
        }
        operation++;
        resultGlobal();
        
    }
    
    public void result(String message){
        System.out.print("["+message+"]: ");
        result();
    }
    
    public void result(){
        if(nano){
            System.out.println("Duration: "+(after - before)+"ns");
        }else{
            System.out.println("Duration: "+(after - before)+"ms");
        }
        
    }
    
    public void resultGlobal(){
        if(nano){
            System.out.println("Moyenne: "+ (end - start)/operation +"ns Total: "+(end - start)/1000000+"ms Nombre d'operation: "+operation);
        }else{
            System.out.println("Moyenne: "+ (end - start)/operation +"ms Total: "+(end - start)+"ms Nombre d'operation: "+operation);
        }
        
        
    }
    
    public void resultGlobal(String message){
        System.out.print("["+message+"]: ");
        resultGlobal();
        
    }
}
