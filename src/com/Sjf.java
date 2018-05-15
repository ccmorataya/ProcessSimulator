package com;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

class Sjf {
    String process(HashMap<String, ArrayList<Integer>> hashMap, JTextArea resultTxtArea, JTextArea ganttTxtArea){
        int noProcesses = hashMap.size();
        int processes[] = new int[noProcesses];
        int burstTime[] = new int[noProcesses];
        int arrivalTime[] = new int[noProcesses];
        int returnTime[] = new int[noProcesses];
        int deliveryTime[] = new int[noProcesses];
        int waitingTime[] = new int[noProcesses];
        int flag[] = new int[noProcesses];
        int originalBurstTime[] = new int[noProcesses];

        int ganttInitialTime[] = new int[noProcesses];
        int ganttEndTime[] = new int[noProcesses];

        int i, ganttPointer=0, tot=0;
        float avgwt=0, avgta=0;
        int lastC = 0;

        for (i=0;i<noProcesses;i++)
        {
            processes[i]= i+1;
            String pid = "P" + (i+1);
            burstTime[i]= hashMap.get(pid).get(0);
            arrivalTime[i]= hashMap.get(pid).get(1);
            originalBurstTime[i]= burstTime[i];
            flag[i]= 0;
        }

        while(true){
            int min=99,c=noProcesses;
            if (tot==noProcesses)
                break;

            for ( i=0;i<noProcesses;i++)
            {
                if ((arrivalTime[i]<=ganttPointer) && (flag[i]==0) && (burstTime[i]<min))
                {
                    min=burstTime[i];
                    c=i;
                }
            }

            if (c==noProcesses) {
                ganttPointer++;
                ganttTxtArea.append("|P" + (c+1));
            }
            else
            {
                if (originalBurstTime[c] == burstTime[c]) {
                    ganttInitialTime[c] = ganttPointer;
                    ganttTxtArea.append("|P" + (c+1));
                }
                if (lastC != c)
                    ganttTxtArea.append("P" + (c+1));
                ganttTxtArea.append("-");
                burstTime[c]--;
                ganttPointer++;
                if (burstTime[c]==0)
                {
                    ganttTxtArea.append("|");
                    ganttEndTime[c]= ganttPointer;
                    flag[c]=1;
                    tot++;
                }
            }
            lastC = c;
        }

        for(i=0;i<noProcesses;i++)
        {
            deliveryTime[i] = ganttEndTime[i] - arrivalTime[i];
            waitingTime[i] = deliveryTime[i] - originalBurstTime[i];
            returnTime[i] = ganttInitialTime[i] - arrivalTime[i];
            avgwt+= waitingTime[i];
            avgta+= deliveryTime[i];
        }

        for(i=0;i<noProcesses;i++)
        {
            resultTxtArea.append("P" + processes[i] + "\t" + deliveryTime[i] + "\t" + returnTime[i] + "\t" + waitingTime[i] + "\n");
        }

        resultTxtArea.append("\nPromedio de espera es: " + avgwt/noProcesses);

        return "ret";
    }
}
