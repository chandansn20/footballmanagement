import java.io.IOException;
import java.util.*;
import projectfiles.*;

public class Driver extends Football
{
    static Scanner sc=new Scanner(System.in);
    public String league;
    Driver(String i)throws IOException
    {
        super(i);
    }

    public static void mprediction()throws IOException
    {
        System.out.print("\033[H\033[2J");
        String t1,t2;
        System.out.println("Enter the team from Premier League: ");
        t1=sc.nextLine();
        System.out.println("Enter the team from La Liga: ");
        t2=sc.nextLine();
        Driver d1=new Driver("PremierLeague");
        Driver d2=new Driver("LaLiga");
        double t1points=d1.predictionpoints(t1);
        double t2points=d2.predictionpoints(t2);
        if(t1points<t2points)
            System.out.println("We predict "+t2+" to win!");
        else
            System.out.println("We predict "+t1+" to win!");
        System.out.print("\nPress ENTER to continue...");
        sc.nextLine();
    }
    
    public void league()throws IOException
    {
        super.league();
    }
    public static void main(String[]args)throws Exception
    {
        int looprun=1;
        String choice;
        while(looprun==1)
        {
            System.out.print("\033[H\033[2J");
            System.out.println("\t\t\t\tWelcome to the Football Management system\nChoose the league: ");
            System.out.print("1) Premier League\n2) La Liga\n3) Multi League teams Match Prediction\n4) Exit\nEnter your choice: ");
            try 
            {
                choice=sc.nextLine();
                if(Integer.parseInt(choice)<1 || Integer.parseInt(choice)>3)
                    looprun=0;
                else 
                {
                    if(Integer.parseInt(choice)==3)
                    {
                        mprediction();
                    }
                    else
                    {
                        if(Integer.parseInt(choice)==1)
                        {
                            Driver d=new Driver("PremierLeague");
                            d.league();
                        }
                        else
                        {
                            Driver d=new Driver("LaLiga");
                            d.league();
                        }                        
                    }
                }
            }
            catch (Exception e)
            {
                System.out.println("Please give a proper integer as input!!!");
                System.out.print("\nPress ENTER to continue...");
                sc.nextLine();
            }            
        }
        sc.close();
    }
}