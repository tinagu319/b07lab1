import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
public class Polynomial
{
    public double[] coe;  
    public int[] exponents;
    public Polynomial()
    {
        this.coe=new double[1];
        this.coe[0]=0;
        this.exponents=new int[1];
        this.exponents[0]=0;
    }
    public Polynomial(double[] coe, int[] exponents)
    {
        this.coe=new double[coe.length];
        this.exponents=new int[exponents.length];
        for (int i=0;i<coe.length;i++)
        {
            this.coe[i]=coe[i];
            this.exponents[i]=exponents[i];
        }

    }
    public Polynomial(File file)
    {
        try
        {
            Scanner input=new Scanner(file);
            String line=input.nextLine();
            input.close();
            line=line.replace("-", "+-");
            String[] terms=line.split("\\+");
            this.coe=new double[terms.length];
            this.exponents=new int[terms.length];
            for (int i=0;i<terms.length;i++)
            {
    
                String[] parts=terms[i].split("x");
                if (parts.length==1)
                {
                    this.coe[i]=Double.parseDouble(terms[i]);
                    this.exponents[i]=0;
                }
                else
                {
                    this.coe[i]=Double.parseDouble(parts[0]);
                    this.exponents[i]=Integer.parseInt(parts[1]);
                }
            
            }
            
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Fail"+e.getMessage());
        }
    }

    public Polynomial add(Polynomial otherPoly)
    {
        int len=this.coe.length+otherPoly.coe.length;
        double[] newcoe=new double[len];
        int[] newexponents=new int[len];
        int num=len;
        int y=0;
        for (int i=0;i<this.coe.length;i++)
        {
            newcoe[y]=this.coe[i];
            newexponents[y]=this.exponents[i];
            y+=1;
        }
        for (int i=0;i<otherPoly.coe.length;i++)
        {
            newcoe[y]=otherPoly.coe[i];
            newexponents[y]=otherPoly.exponents[i];
            y+=1;
        }
        for (int i=0;i<newcoe.length;i++)
        {
            for (int j=0;j<i;j++)
            {
                if (newexponents[i]==newexponents[j])
                {
                    newcoe[j]=newcoe[i]+newcoe[j];
                    newcoe[i]=0;
                    break;
                }
                
            }
        }
        for (int i=0;i<len;i++)
        {
            if (newcoe[i]==0) num=num-1;
        }
        double[] c=new double[num];
        int[] e=new int[num];
        int g=0;
        for (int i=0;i<len;i++)
        {
           if (newcoe[i]!=0)
           {
                c[g]=newcoe[i];
                e[g]=newexponents[i];
                g+=1;
           }
        }
        
        return new Polynomial(c, e);
    }
    public double evaluate(double x)
    {
        double result=0;
        for (int i=0;i<this.coe.length;i++)
        {
            result+=this.coe[i]*Math.pow(x,this.exponents[i]);
        }
        return result;
    }
    public boolean hasRoot(double x)
    {
        return Math.abs(this.evaluate(x))<1e-9;
    }
    public Polynomial multiply(Polynomial other)
    {
        int len=this.coe.length*other.coe.length;
        double[] newcoe=new double[len];
        int[] newexponents=new int[len];
        int x=0;
        for (int i=0;i<this.coe.length;i++)
        {
            for (int j=0;j<other.coe.length;j++)
            {
                newcoe[x]=this.coe[i]*other.coe[j];
                newexponents[x]=this.exponents[i]+other.exponents[j];
                x+=1;
            }
        }
        int num=0;
        for (int i=0;i<newcoe.length;i++)
        {
            for (int j=0;j<i;j++)
            {
                if (newexponents[i]==newexponents[j])
                {
                    newcoe[j]=newcoe[j]+newcoe[i];
                    newcoe[i]=0;
                
                }
            }
        }
        for (int i=0;i<newcoe.length;i++)
        {
            if (newcoe[i]==0) num+=1;
        }
        int g=len-num;
        double[] c=new double[g];
        int[] e=new int[g];
        int j=0;
        for (int i=0;i<len;i++)
        {
            if(newcoe[i]!=0)
            {
                e[j]=newexponents[i];
                c[j]=newcoe[i];
                j+=1;
            }
        }
        return new Polynomial(c, e);

    }
    
    public void saveToFile(String string)
    {
        try
        {  
            PrintStream output = new PrintStream(string+".txt");

            for (int i=0;i<this.coe.length;i++)
            {
                if (this.exponents[i]==0)
                {
                    if (this.coe[i]>0 && i!=0)
                    {
                        output.print("+");
                    }
                    output.print(this.coe[i]);
                }
                else
                {
                    if (this.coe[i]>0 && i!=0)
                    {
                        output.print("+");
                    }
                    output.print(this.coe[i]+"x"+this.exponents[i]);
                }
            }
            output.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Fail"+e.getMessage());
        }
    }
} 

 