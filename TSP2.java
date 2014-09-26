
/**
 * Write a description of class TSP2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TSP2
{
    public static void main(String args[])
    {
        FileIO reader = new FileIO();
        String [] towns = reader.load("C:\\Users\\John\\Documents\\Computer Science\\TSP\\towns2.txt");
        String [] lats = reader.load("C:\\Users\\John\\Documents\\Computer Science\\TSP\\lat2.txt");
        String [] longs = reader.load("C:\\Users\\John\\Documents\\Computer Science\\TSP\\long2.txt");
        town [] array = new town[towns.length];
        town [] sorted = new town[towns.length];
        town [] Final = new town[towns.length];
        double [][] routes = new double [array.length][array.length];
        
        for(int i=0;i<array.length;i++)
        {
            array[i]= new town(towns[i],Double.valueOf(lats[i]),Double.valueOf(longs[i]),i+1);
        }
        for(int i=0;i<array.length;i++)
        {
            for(int k=0;k<array.length;k++)
            {
                routes[i][k]=getDistance(array[i].lat,array[i].lon,array[k].lat,array[k].lon);
            }
        }
        double Winner=1000000;
        double total=0;
        for(int c=0;c<array.length;c++)
        {
            int current=c;
            double distance=999;
            boolean [] visited = new boolean[array.length];
            int dest = 0;
            
            


            for(int i=0;i<sorted.length;i++)
            {
                distance=999;
                for(int j=0;j<sorted.length;j++)
                {                                
                    if(visited[j]==false)
                    {
                        if(distance>routes[current][j]&&routes[current][j]!=0.0)
                        {
                            dest=j;
                            distance=routes[current][j];
                            
                        }
                    }
                }
                sorted[i]=array[current];
                visited[current]=true;
                current=dest;                                                
            }
            total=calcDistance(sorted,routes);
            System.out.println("At c = " + c + ", total = " + total + " and winner = " + Winner);
                       
            if(Winner>total)
            {                              
                Winner=total;
                System.out.println("New Winner = " + Winner);
                System.arraycopy(sorted,0,Final,0,sorted.length);
            }
        }
        
        
        for(int i=0;i<sorted.length;i++)
        {
            System.out.print(Final[i].id + ".");
            
        }
        System.out.println(" ");
        System.out.println(calcDistance(Final,routes));
        //System.out.println(Winner);
        //System.out.println(Win);
    }
    public static double getDistance(double lat1, double lon1, double lat2, double lon2)
    { 
            double R = 6371; 
            double dLat = Math.toRadians((lat2-lat1)); 
            double dLon = Math.toRadians((lon2-lon1)); 
            double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon/2) * Math.sin(dLon/2); 
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
            double d = R * c; 
            return d; 
    }
    public static double calcDistance(town[] input, double[][] input2)
    {
        double total=0.0;
        for(int i=0;i<input.length-1;i++)
        {
            total = total + getDistance(input[i].lat,input[i].lon,input[i+1].lat,input[i+1].lon);
        }
        total=total+getDistance(input[input.length-1].lat,input[input.length-1].lon,input[0].lat,input[0].lon);
        return total;
    }
    
    
}
