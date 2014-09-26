
/**
 * Write a description of class TSP here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TSP
{
    public static void main()
    {
        FileIO reader = new FileIO();
        String [] towns = reader.load("C:\\Users\\John\\Documents\\Computer Science\\TSP\\towns.txt");
        String [] lats = reader.load("C:\\Users\\John\\Documents\\Computer Science\\TSP\\lat.txt");
        String [] longs = reader.load("C:\\Users\\John\\Documents\\Computer Science\\TSP\\long.txt");
        town [] array = new town[towns.length];
        town [] sorted = new town[towns.length];
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
        
        int current=0;
        double distance=999;
        boolean[] visited = new boolean[array.length];
        int dest = 0;

        for(int i=0;i<sorted.length;i++)
        {
            distance=999;
            for(int j=0;j<sorted.length;j++)
            {                
                if(visited[j]==false)
                {
                    System.out.println(j + " " + array[current].id + array[current].name + " to " + array[j].name + " is " + routes[current][j]);
                }
                if(visited[j]==false)
                {
                    if(distance>routes[current][j]&&routes[current][j]!=0.0)
                    {
                        dest=j;
                        distance=routes[current][j];
                    }
                }
            }
            System.out.println(" ");
            System.out.println("New town is " + array[dest].name);
            System.out.println(" ");
            current=dest;
            sorted[i]=array[current];
            visited[current]=true;
        }
        for(int i=0;i<sorted.length;i++)
        {
            System.out.print(sorted[i].id + ".");
        }
        
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

}
