package packageDND;














import java.util.* ;

public class StatGenerator {
	
	private final static int Size   = 1000 ;  //Total SampleSize = Size ^ Layers
	private final static int Layers = 3 ;

	public static void main(String[] args) {
		
		ArrayList<Double> Stats = new ArrayList<Double>() ;

		System.out.println( "\tTesting " + Size + " Size and " + Layers + " Layers." );

		for( int i2 = 0; i2 < 10; i2++ ) {
			System.out.print( "\n Test" + (i2+1) + ": \t" );
			Stats = GenerateStatsLayered( Size , Layers ) ;
			for( int i = 0; i < Stats.size(); i++ ) {
				System.out.print( Stats.get(i) + "\t" );
			}
		}
		
	}

	private static ArrayList<Double> GenerateStatsLayered( int size , int Layers ) {

		ArrayList<Double> Stats = new ArrayList<Double>() ;

		for( int i = 0; i < 7; i++ ) { Stats.add( (double) 0 ) ; }

		ArrayList<Double> Temp = new ArrayList<Double>() ;
		
		if( Layers > 1 ){
			for( int i = 0 ; i< size ; i++ ) {
				Temp = GenerateStatsLayered( size , Layers - 1 ) ;
				for( int in = 0; in < Stats.size(); in++ ) {
					Stats.set( in , ( Stats.get(in) + (Temp.get(in) / size ) ) ) ;
				}
			}
		}else {
			for( int i = 0 ; i< size ; i++ ) {
				Temp = GenerateStat() ;
				for( int in = 0; in < Stats.size(); in++ ) {
					Stats.set( in , ( Stats.get(in) + (Temp.get(in) / size ) ) ) ;
				}
			}
		}
		return Stats ;
	}
	
	private static ArrayList<Double> GenerateStat() {
		

		ArrayList<Integer> TempStats = RollStats() ;
		ArrayList<Double> Stats = new ArrayList<Double>() ;

		double avg = 0 ;
		for( int i = 0; i < TempStats.size(); i++ ) {
			Stats.add( (double)TempStats.get(i) ) ;
			avg += Stats.get(i)/TempStats.size() ;
		}
		Stats.add(avg) ;
		
		return Stats;
	}
	
	private static ArrayList<Integer> RollStats(){
		
		ArrayList<Integer> Stats = new ArrayList<Integer>() ;
		
		for( int i = 0; i < 6; i++ ) {
			int total = 0 ;
			int min = RollDice() ;
			int temp = 0 ;
			for( int ind = 0 ; ind < 3 ; ind++ ) {
				temp = RollDice() ;
				if ( temp < min ) {
					total += min ;
					min = temp ;
				}else {
					total += temp ;
				}
			}
			Stats.add(total) ;
		}
		
		Collections.sort(Stats) ;
		
		return Stats ;
	}
	
	private static int RollDice() {
		return (int)(Math.random()*6)+1 ;
	}
}
