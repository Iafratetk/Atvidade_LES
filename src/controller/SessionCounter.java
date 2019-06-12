package controller;

import javax.servlet.http.*;  
public class SessionCounter implements HttpSessionListener{  
  static private int sessoesAtivas =0;  
  
  public static int getActiveSessions(){  
    return sessoesAtivas;  
  }  
  
  public void sessionCreated(HttpSessionEvent event){  
    sessoesAtivas++;  
  }  
  
  public void sessionDestroyed(HttpSessionEvent event){  
    sessoesAtivas--;  
  }  
}  