package projectfiles;
import java.util.*;
import java.io.*;

public class Football
{
    String lea;
    public Football(String l)throws IOException
    {
        lea=l;
        String temp;
        BufferedReader fin=new BufferedReader(new FileReader("years.txt"));            
        while((temp=fin.readLine())!=null)
                year=temp;
        fin.close();
    }

    AllOps a=new AllOps();
    static Scanner sc=new Scanner(System.in);
    String year;
    String table[][]=new String[20][7];

    public double predictionpoints(String team)throws IOException
    {
        String s="";
        int tpos=-1;
        double predpoints;
        BufferedReader reader=new BufferedReader(new FileReader(lea+year+".txt"));
        int j=0;
        int k=0;
        String i;
        while((i=reader.readLine())!=null)
        {
            s=i;
            table[j][k]=s;
            k++;
            if(k==7)
            {
                j++;
                k=0;
            }
        }
        reader.close();
        for(j=0;j<20;j++)
        {
            if(table[j][0].equalsIgnoreCase(team))
                tpos=j;        
            if(tpos>-1)
                break;
        }
        predpoints=0.1*(20-tpos);
        predpoints+=a.streakcalculate(table[tpos][6]);
        predpoints+=0.2*(20-a.previousposition(team,lea));
        int lost=Integer.parseInt(table[tpos][1])-(Integer.parseInt(table[tpos][2])+Integer.parseInt(table[tpos][3]));
        int gd=Integer.parseInt(table[tpos][4])-Integer.parseInt(table[tpos][5]);
        predpoints-=0.01*lost;
        predpoints+=0.01*gd;
        return predpoints;
    }

    public void league() throws IOException
    {
        int ch, premlooprun=1;
        String i,s="";
        try{
            while(premlooprun==1)
            {
                System.out.print("\033[H\033[2J");
                System.out.println("\nWelcome to the "+lea+" Section!\n\n");
                System.out.println("Choose one option from the following options: \n1) View the league table");
                System.out.println("2) Enter match results\n3) View Match Predictions");
                System.out.println("4) Check title contenders\n5) Check actual title contenders");
                System.out.println("6) Check possible Champions League qualifications");
                System.out.print("7) Check Induvidual Rankings\n8) Exit\nEnter your choice: ");
                ch=sc.nextInt();
                BufferedReader reader=new BufferedReader(new FileReader(lea+year+".txt"));
                int j=0;
                int k=0;
                while((i=reader.readLine())!=null)
                {
                    s=i;
                    table[j][k]=s;
                    k++;
                    if(k==7)
                    {
                        j++;
                        k=0;
                    }
                }
                reader.close();
                switch(ch)
                {
                    case 1:
                        System.out.print("\033[H\033[2J");
                        System.out.println("League table of "+year+"\n");
                        for(j=0;j<10;j++)
                        {
                            switch(j)
                            {
                                case 0:
                                    System.out.print("  TEAM");
                                    a.addspace(30-4);
                                break;
                                case 1:
                                    System.out.print("MP  ");
                                break;
                                case 2:
                                    System.out.print("W   ");
                                break;
                                case 3:
                                    System.out.print("D   ");
                                break;
                                case 4:
                                    System.out.print("L   ");
                                break;
                                case 5:
                                    System.out.print("GF  ");
                                break;
                                case 6:
                                    System.out.print("GA  ");
                                break;
                                case 7:
                                    System.out.print("GD   ");
                                break;
                                case 8:
                                    System.out.print("Pt  ");
                                break;
                                case 9:
                                    System.out.print("LAST5");
                                break;
                            }
                        }
                        System.out.println();
                        for(j=0;j<20;j++)
                        {
                            for(k=0;k<7;k++)
                            {
                                System.out.print(table[j][k]+"  ");
                                if(k==0)
                                    a.addspace(30-table[j][k].length());
                                else if(k!=6)
                                {
                                    if(Integer.parseInt(table[j][k])<10)
                                        System.out.print(" ");
                                }
                                if(k==3)
                                {
                                    int lost=Integer.parseInt(table[j][1])-(Integer.parseInt(table[j][2])+Integer.parseInt(table[j][3]));
                                    System.out.print(lost+"  ");
                                    if(lost<10)
                                        System.out.print(" ");
                                }
                                else if(k==5)
                                {
                                    int gd=Integer.parseInt(table[j][4])-Integer.parseInt(table[j][5]);
                                    System.out.print(gd+"  ");
                                    if(gd<10 && gd>0)
                                        System.out.print(" ");
                                    int points=(3*Integer.parseInt(table[j][2]))+Integer.parseInt(table[j][3]);
                                    if(gd>-10)
                                        System.out.print(" ");
                                    System.out.print(points+"  ");
                                    if(points<10)
                                        System.out.print(" ");
                                }
                            }
                            System.out.println();
                        }
                        System.out.print("\nPress ENTER to continue...");
                        sc.nextLine();
                        sc.nextLine();
                    break;

                    case 2:
                    System.out.print("\033[H\033[2J");
                        String team1,team2;
                        int t1goals,t2goals;
                        System.out.println("Enter the team 1: ");
                        sc.nextLine();
                        team1=sc.nextLine();
                        System.out.println("Enter the team 1 goals: ");
                        t1goals=sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter the team 2: ");
                        team2=sc.nextLine();
                        System.out.println("Enter the team 2 goals: ");
                        t2goals=sc.nextInt();
                            int win=3;
                            BufferedReader results=new BufferedReader(new FileReader(lea+year+".txt"));
                            int t1details[]=new int[5];
                            int t2details[]=new int[5];
                            int t1count=-1;
                            int t1subcount=0,t2subcount=0;
                            int t2count=-1;
                            int count=0;
                            while((i=results.readLine())!=null)
                            {
                                if(i.equalsIgnoreCase(team1))
                                {
                                    t1count=count;
                                }
                                if(i.equalsIgnoreCase(team2))
                                {
                                    t2count=count;
                                }
                                if(t1count>=0 && t1subcount<6)
                                {
                                    if(t1subcount!=0)
                                    t1details[t1subcount-1]=Integer.parseInt(i);
                                    t1subcount++;
                                }
                                if(t2count>=0 && t2subcount<6)
                                {
                                    if(t2subcount!=0)
                                        t2details[t2subcount-1]=Integer.parseInt(i);
                                    t2subcount++;
                                }
                                count++;
                            }
                        if(t1count==(-1) || t2count==(-1))
                        {
                            System.out.println("Sorry, the team/teams doesn't exist in this league");
                        }
                        else
                        {
                            t1details[0]++;
                            t2details[0]++;
                            if(t1goals>t2goals)
                            {
                                t1details[1]++;
                                win=1;
                            }
                            else if(t2goals>t1goals)
                            {
                                t2details[1]++;
                                win=2;
                            }
                            else{
                                t1details[2]++;
                                t2details[2]++;
                            }
                            t1details[3]+=t1goals;
                            t2details[3]+=t2goals;
                            t1details[4]-=t2goals;
                            t2details[4]-=t1goals;
                            results.close();
                            a.updatetable(t1count,t2count,t1details,t2details,win,year,lea);
                            a.sorttable(year,lea);
                        }
                        System.out.print("\nPress ENTER to continue...");
                        sc.nextLine();
                        sc.nextLine();
                    break;

                    case 3:
                        System.out.print("\033[H\033[2J");
                        System.out.println("MATCH PREDICTION\n");
                        System.out.println("Enter the team 1: ");
                        sc.nextLine();
                        team1=sc.nextLine();
                        System.out.println("Enter the team 2: ");
                        team2=sc.nextLine();
                        double predpoints1=0;
                        double predpoints2=0;
                        int t1pos=-1;
                        int t2pos=-1;
                        l1: for(j=0;j<20;j++)
                        {
                            if(table[j][0].equalsIgnoreCase(team1))
                                t1pos=j;
                            else if(table[j][0].equalsIgnoreCase(team2))
                                t2pos=j;
                            if(t1pos>-1 && t2pos>-1)
                                break l1;
                        }
                        if(t1pos<(t2pos-1))
                            predpoints1+=0.5;
                        else if(t2pos<(t1pos-1))
                            predpoints2+=0.5;
                        if(t1pos<(t2pos-5))
                            predpoints1+=0.5;
                        else if(t2pos<(t1pos-5))
                            predpoints2+=0.5;
                        if(t1pos<(t2pos-10))
                            predpoints1+=1;
                        else if(t2pos<(t1pos-10))
                            predpoints2+=1;
                        predpoints1+=a.streakcalculate(table[t1pos][6]);
                        predpoints2+=a.streakcalculate(table[t2pos][6]);
                        int prepos1=a.previousposition(team1,lea)+1;
                        int prepos2=a.previousposition(team2,lea)+1;
                        if(prepos1<prepos2)
                            predpoints1+=0.5;
                        else if(prepos2<prepos1)
                            predpoints2+=0.5;
                        if(prepos1<(prepos2+3))
                            predpoints1+=0.5;
                        else if(prepos2<(prepos1+3))
                            predpoints2+=0.5;
                        int t1lost=Integer.parseInt(table[t1pos][1])-(Integer.parseInt(table[t1pos][2])+Integer.parseInt(table[t1pos][3]));
                        int t2lost=Integer.parseInt(table[t2pos][1])-(Integer.parseInt(table[t2pos][2])+Integer.parseInt(table[t2pos][3]));
                        if(t1lost<t2lost)
                            predpoints1+=0.25;
                        else if(t2lost<t1lost)
                            predpoints2+=0.25;
                        int gd1=Integer.parseInt(table[t1pos][4])-Integer.parseInt(table[t1pos][5]);
                        int gd2=Integer.parseInt(table[t2pos][4])-Integer.parseInt(table[t2pos][5]);
                        if(gd2<gd1)
                            predpoints1+=0.25;
                        else if(gd1<gd2)
                            predpoints2+=0.25;
                        if(predpoints1>predpoints2)
                            System.out.println("We predict "+team1+" to win the match!");
                        else if(predpoints1<predpoints2)
                            System.out.println("We predict "+team2+" to win the match!");
                        else if(t1pos<t2pos)
                            System.out.println("We predict "+team1+" to win the match!");
                        else if(t1pos>t2pos)
                            System.out.println("We predict "+team2+" to win the match!");
                        System.out.print("\nPress ENTER to continue...");
                        sc.nextLine();
                    break;

                    case 4:
                        System.out.print("\033[H\033[2J");
                        ArrayList<String> contenders=new ArrayList<>();
                        int toppoints;
                        j=0;
                        k=0;
                        toppoints=(Integer.parseInt(table[0][2])*3)+Integer.parseInt(table[0][3]);
                        int currentpoints,potentialpoints;
                        for(j=0;j<20;j++)
                        {
                            currentpoints=(Integer.parseInt(table[j][2])*3)+Integer.parseInt(table[j][3]);
                            potentialpoints=currentpoints+((38-Integer.parseInt(table[j][1]))*3);
                            if(potentialpoints>=toppoints)
                            {
                                contenders.add(table[j][0]);
                            }
                        }
                        System.out.println("The title contenders are: ");
                        for(k=0;k<contenders.size();k++)
                            System.out.println(contenders.get(k));
                        System.out.print("\nPress ENTER to continue...");
                        sc.nextLine();
                        sc.nextLine();
                    break;

                    case 5:
                        System.out.print("\033[H\033[2J");
                        if(Integer.parseInt(table[0][1])<14)
                            System.out.println("Sorry, not enough matches has been played");
                        else  
                        {
                            int div=4;
                            if(Integer.parseInt(table[0][1])>28)
                                div=2;
                            ArrayList<String> actcontenders=new ArrayList<>();
                            toppoints=(Integer.parseInt(table[0][2])*3)+Integer.parseInt(table[0][3]);
                            int currentpoints1,potentialpoints1;
                            for(j=0;j<20;j++)
                            {
                                currentpoints1=(Integer.parseInt(table[j][2])*3)+Integer.parseInt(table[j][3]);
                                int pending=(38-Integer.parseInt(table[j][1]))/div;
                                potentialpoints1=currentpoints1+(pending*3);
                                if(potentialpoints1>=toppoints)
                                {
                                    actcontenders.add(table[j][0]);
                                }
                            }
                            System.out.println("The actual title contenders are: ");
                            for(k=0;k<actcontenders.size();k++)
                                System.out.println(actcontenders.get(k));
                        }
                        System.out.print("\nPress ENTER to continue...");
                        sc.nextLine();
                        sc.nextLine();
                    break;
                        
                    case 6:
                        int cho;
                        System.out.print("\033[H\033[2J");
                        System.out.println("Do you want:\n1) All possible qualifications\n2) Actually Possible Qualifications");
                        System.out.print("Enter your choice: ");
                        cho=sc.nextInt();
                        if(cho==1)
                        {
                            System.out.print("\033[H\033[2J");
                            ArrayList<String> quali=new ArrayList<>();
                            int toppo;
                            j=0;
                            k=0;
                            toppo=(Integer.parseInt(table[3][2])*3)+Integer.parseInt(table[3][3]);
                            int current,potential;
                            for(j=0;j<20;j++)
                            {
                                current=(Integer.parseInt(table[j][2])*3)+Integer.parseInt(table[j][3]);
                                potential=current+((38-Integer.parseInt(table[j][1]))*3);
                                if(potential>=toppo)
                                {
                                    quali.add(table[j][0]);
                                }
                            }
                            System.out.println("The Champions League Qualification contenders are: ");
                            for(k=0;k<quali.size();k++)
                                System.out.println(quali.get(k));
                        }
                        else     
                        {
                            System.out.print("\033[H\033[2J");
                            if(Integer.parseInt(table[3][1])<14)
                                System.out.println("Sorry, not enough matches has been played");
                            else  
                            {
                                int div=4;
                                if(Integer.parseInt(table[3][1])>28)
                                    div=2;
                                ArrayList<String> actcontenders=new ArrayList<>();
                                toppoints=(Integer.parseInt(table[3][2])*3)+Integer.parseInt(table[3][3]);
                                int currentpoints1,potentialpoints1;
                                for(j=0;j<20;j++)
                                {
                                    currentpoints1=(Integer.parseInt(table[j][2])*3)+Integer.parseInt(table[j][3]);
                                    int pending=(38-Integer.parseInt(table[j][1]))/div;
                                    potentialpoints1=currentpoints1+(pending*3);
                                    if(potentialpoints1>=toppoints)
                                    {
                                        actcontenders.add(table[j][0]);
                                    }
                                }
                                System.out.println("The actual Champions League contenders are: ");
                                for(k=0;k<actcontenders.size();k++)
                                    System.out.println(actcontenders.get(k));
                            }
                        }
                        System.out.print("\nPress ENTER to continue...");
                        sc.nextLine();
                        sc.nextLine();
                    break;
                        
                    case 7:
                        System.out.print("\033[H\033[2J");
                        sc.nextLine();
                        System.out.print("Enter the team name or position: ");
                        String teamorpos=sc.nextLine();
                        try
                        {
                            a.positioncheck(Integer.parseInt(teamorpos),table);
                        }
                        catch(Exception e)
                        {
                            a.positioncheck(teamorpos,table);
                        }
                        System.out.print("\nPress ENTER to continue...");
                        sc.nextLine();
                    break;
                    default:
                        premlooprun=0;
                }
            }
        }
        catch(Exception e){
            System.out.println("Sorry, an exception has occured: "+e);
            System.out.print("\nPress ENTER to continue...");
                        sc.nextLine();
        }       
    }
}
