package com;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

class Sjf {
    String process(HashMap<String, ArrayList<Integer>> hashMap, JTextArea resultTxtArea){
        int noProcesses = hashMap.size();
        int processes[] = new int[noProcesses];
        int burstTime[] = new int[noProcesses];
        int arrivalTime[] = new int[noProcesses];
        int deliveryTime[] = new int[noProcesses];
        int responseTime[] = new int[noProcesses];
        int waitingTime[] = new int[noProcesses];
        int flag[] = new int[noProcesses];
        int kBurstTime[] = new int[noProcesses];

        int i, st=0, tot=0;
        float avgwt=0, avgta=0;

        for (i=0;i<noProcesses;i++)
        {
            processes[i]= i+1;
            String pid = "P" + (i+1);
            burstTime[i]= hashMap.get(pid).get(0);
            arrivalTime[i]= hashMap.get(pid).get(1);
            kBurstTime[i]= burstTime[i];
            flag[i]= 0;
        }

        while(true){
            int min=99,c=noProcesses;
            if (tot==noProcesses)
                break;

            for ( i=0;i<noProcesses;i++)
            {
                if ((arrivalTime[i]<=st) && (flag[i]==0) && (burstTime[i]<min))
                {
                    min=burstTime[i];
                    c=i;
                }
            }

            if (c==noProcesses)
                st++;
            else
            {
                burstTime[c]--;
                st++;
                if (burstTime[c]==0)
                {
                    deliveryTime[c]= st;
                    flag[c]=1;
                    tot++;
                }
            }
        }

        for(i=0;i<noProcesses;i++)
        {
            responseTime[i] = deliveryTime[i] - arrivalTime[i];
            waitingTime[i] = responseTime[i] - kBurstTime[i];
            avgwt+= waitingTime[i];
            avgta+= responseTime[i];
        }

        for(i=0;i<noProcesses;i++)
        {
            resultTxtArea.append("P" + processes[i] + "\t" + deliveryTime[i] + "\t" + responseTime[i] + "\t" + waitingTime[i] + "\n");
        }

        resultTxtArea.append("\nPromedio de espera es: " + avgwt/noProcesses);

        return "ret";
    }
}
