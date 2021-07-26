package projectfiles;
import java.io.*;

class UnabletoUpdate extends Exception
{
    UnabletoUpdate(String s){
        super(s);
    }
}

public class AllOps 
{
    public void addspace(int i)
    {
        for(int j=0;j<i;j++)
            System.out.print(" ");
    }

    public void updatetable(int t1c,int t2c,int t1details[],int t2details[],int win,String year,String lea) throws IOException
    {
        BufferedReader bin=new BufferedReader(new FileReader(lea+year+".txt"));
        BufferedWriter bout=new BufferedWriter(new FileWriter(lea+year+"y.txt"));
        String i;
        int count=0;
        while((i=bin.readLine())!=null)
        {
            if(count>t1c && count<(t1c+7))
            {
                if(count==(t1c+6))
                {
                    if(win==1)
                        bout.write("w"+i.substring(0,4));
                    else if(win==2)
                        bout.write("l"+i.substring(0,4));
                    else
                        bout.write("d"+i.substring(0,4));
                }
                else
                {
                    String s=String.valueOf(t1details[(count-t1c)-1]);              
                    bout.write(s);
                }
            }
            else if(count>t2c && count<(t2c+7))
            {
                if(count==(t2c+6))
                {
                    if(win==2)
                        bout.write("w"+i.substring(0,4));
                    else if(win==1)
                        bout.write("l"+i.substring(0,4));
                    else
                        bout.write("d"+i.substring(0,4));
                }
                else
                {
                    String s=String.valueOf(t2details[(count-t2c)-1]);             
                    bout.write(s);
                }
            }
            else
                bout.write(i);
            bout.newLine();
            count++;
        }
        bin.close();
        bout.close();
        File f1=new File(lea+year+".txt");
        f1.delete();
        File oldName=new File(lea+year+"y.txt");
        File newName =new File(lea+year+".txt");
        try
        {  
            if (oldName.renameTo(newName)) 
                System.out.println("DETAILS UPDATED");        
            else 
                throw new UnabletoUpdate("Sorry, Unable to update the file");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void sorttable(String year,String lea)throws IOException
    {         
       BufferedReader bin=new BufferedReader(new FileReader(lea+year+".txt"));
       BufferedWriter bout=new BufferedWriter(new FileWriter(lea+year+"y.txt"));
       int[][] det = new int[20][3];
       String table[][]=new String[20][7];
       int points=0,mp=0,gs=0,gc=0;
       int k=0,j=0;
       String i,s="";
       while((i=bin.readLine())!=null)
        {
            s=i;
            table[j][k]=i;
            k++;
            if(k==2)
                mp=Integer.parseInt(s);
            if(k==3)
                points=Integer.parseInt(s)*3;
            if(k==4)
                points+=Integer.parseInt(s);   
            if(k==5)
                gs=Integer.parseInt(s);
            if(k==6)
                gc=Integer.parseInt(s);
            if(k==7)
            {
                det[j][0] = points;
                det[j][1] = mp;
                det[j][2] = gs-gc;
                j++;
                k=0;
            }
        }
        int n = 20;
        for (int a = 0; a < n-1; a++)
            for (j = 0; j < n-a-1; j++)
                if (det[j][0] < det[j+1][0])
                {
                  for(int x = 0; x < 7 ; x++)
                  {
                    String temp1 = table[j][x];
                    table[j][x] = table[j+1][x];
                    table[j+1][x] = temp1;
                  }
                  for(int x = 0; x < 3 ; x++)
                  {
                    int temp2 = det[j][x];
                    det[j][x] = det[j+1][x];
                    det[j+1][x] = temp2;
                  }
                }
                else if(det[j][0] == det[j+1][0])
                {
                  if (det[j][1] > det[j+1][1])
                  {
                     for(int x = 0; x < 7 ; x++)
                     {
                       String temp1 = table[j][x];
                       table[j][x] = table[j+1][x];
                       table[j+1][x] = temp1;
                     }
                     for(int x = 0; x < 3 ; x++)
                     {
                       int temp2 = det[j][x];
                       det[j][x] = det[j+1][x];
                       det[j+1][x] = temp2;
                     }

                  }
                else if (det[j][1] == det[j+1][1])
                {
                    if (det[j][2] < det[j+1][2])
                    {
                        for(int x = 0; x < 7 ; x++)
                        {
                            String temp1 = table[j][x];
                            table[j][x] = table[j+1][x];
                            table[j+1][x] = temp1;
                        }
                        for(int x = 0; x < 3 ; x++)
                        {
                            int temp2 = det[j][x];
                            det[j][x] = det[j+1][x];
                            det[j+1][x] = temp2;
                        }
                    }
                }
            }
       for (int a = 0; a < 20; a++)
       {
         for ( j = 0; j < 7; j++) 
         {
            bout.write(table[a][j]);
            bout.newLine();
         }   
       }
        bin.close();
        bout.close();
        File f1=new File(lea+year+".txt");
        f1.delete();
        File oldName=new File(lea+year+"y.txt");
        File newName =new File(lea+year+".txt");  
        if (oldName.renameTo(newName)) 
            System.out.println("");        
        else 
            System.out.println("Error");
    }

    double streakcalculate(String s)
    {
        double points=0.0;
        int count=0;
        for(int i=0;i<s.length();i++)
        {
            char ch=s.charAt(i);
            if(ch=='w')
            {
                count++;
                continue;
            }
            else
                break;
        }
        if(count>0 && count<3)
            points=0.5;
        else if(count>=3 && count<5)
            points=1.0;
        else if(count==5)
            points=2.0;
        return points;
    }

    int previousposition(String str,String lea)throws IOException
    {
        BufferedReader finn=new BufferedReader(new FileReader(lea+"2020.txt"));
        String table[][]=new String[20][7];
        int position=-1;
        String i,s;
        int j=0;
        int k=0;
        while((i=finn.readLine())!=null)
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
        finn.close();
        for(int q=0;q<20;q++)
        {
            if(table[q][0].equalsIgnoreCase(str))
                return q;
        }
        return position;
    }

    void positioncheck(String s,String table[][])
    {
        int i;
        for(i=0;i<20;i++)
        {
            if(table[i][0].equalsIgnoreCase(s))
            break;
        }
        System.out.println(s+" is in the position: "+(++i));
    }

    void positioncheck(int i,String table[][])
    {
        System.out.println("The team in "+i+"th position is "+table[i-1][0]);
    }
}