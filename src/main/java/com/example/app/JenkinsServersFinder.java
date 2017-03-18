package com.example.app;

import javax.jmdns.*;

class JenkinsServersFinder implements ServiceListener  {

   public void serviceAdded(ServiceEvent event) {
      System.out.println("serviceAdded: " + event.getName());
   }
   
   public void serviceRemoved(ServiceEvent event) {
      System.out.println("serviceRemoved: " + event.getName());
   }
   
   public void serviceResolved(ServiceEvent event) {
      System.out.println("serviceResolved: " + event.getName());
   }
   
   public static void main(String [] args) {
      try {
        String bonjourServiceType = "_hudson._tcp.local.";
        JmDNS jmdns = JmDNS.create();
   
        jmdns.addServiceListener(bonjourServiceType, new JenkinsServersFinder());
        ServiceInfo[] serviceInfos = jmdns.list(bonjourServiceType);
        for (ServiceInfo info : serviceInfos) {
          System.out.println("Found Jenkins service: " + info.getName() + " : " + info.getURL());
        }
        jmdns.close();
        System.out.println("We are done in JenkinsServersFinder");
      }
      catch (Exception e) {
        System.out.println(e);
      }
   }
}
