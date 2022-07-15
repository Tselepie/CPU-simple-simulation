package sample;
import java.util.ArrayList;


public class SRTFF extends Scheduler{

    public  SRTFF(){

    }


    public void addProcess(Process p) {

        this.processes.add(p);
    }

    public Process getNextProcess(){
        Process d=new processes.get(0);
        int cp=-1;
        int nn=1000000;
        for(int i=0;i<=processes.getsize();i++){
            if(processes[i].getBurstTime()<nn){
                nn=getBurstTime();
                cp=processes[i];
            }
        }
        cp.run;
        if(cp.getBurstTime()>0)
        {
            cp.=>>READY;

        }
        if(cp.getBurstTime()<=0)
        {
            cp.==>TERMINATED;
        }




        return d;
    }



    public void addProcess(Process p ){
        this.processes.add(p);
    }}