package Week07_Lab;

// Lab 7 java -> Memory allocation

//Java Program for Bankers Algorithm 
public class Bankers 
{ 
	private static int n = 5; // Number of processes  
	private static int m = 3; // Number of resources 
	private static int need[][] = new int[n][m]; 
	private static int [][]max; 
	private static int [][]alloc; 
	private static int []avail; 
	private static int safeSequence[] = new int[n]; 

void initializeValues() { 
  // P0, P1, P2, P3, P4 are the Process names here  
  // Allocation Matrix  
  alloc = new int[][] { { 0, 1, 0 }, //P0    
                        { 2, 0, 0 }, //P1 
                        { 3, 0, 2 }, //P2 
                        { 2, 1, 1 }, //P3 
                        { 0, 0, 2 } }; //P4 
        
  // MAX Matrix 
  max = new int[][] { { 7, 5, 3 }, //P0 
	  			        { 3, 2, 2 }, //P1 
						{ 9, 0, 2 }, //P2 
						{ 2, 2, 2 }, //P3  
						{ 4, 3, 3 } }; //P4 
  
	// Available Resources   
	avail = new int[] { 3, 3, 2 };  
} 

public boolean isSafe(int pavail[], int palloc[][], int pneed[][], int n, int m ) { 
	int count=0; 
  
	//finish array to find the already allocated process 
	boolean finish[] = new boolean[n];  
	for (int i = 0;i < n; i++) { 
		finish[i] = false; 
	} 
      
	//work array to store the copy of available resources 
	int work[] = new int[m];     
	for (int i = 0;i < m; i++) { 
		work[i] = pavail[i]; 
	} 

	//While all processes are not finished 
	while (count<n) { 
		boolean flag = false; 
			
		// Find a process P_i that did not finish: Finish[i] = False
		for (int i = 0;i < n; i++) { 
			if (finish[i] == false) { 
				int j; 
					
				//Check if has sufficient resources: 
				//for (j =0; j < m; j ++) {check Need[i][j] <= Work[j];} 
				for (j = 0;j < m; j++) {         
					if (pneed[i][j] > work[j]) 
						break; 
				} 
					
				//all resources have been locked at
				if (j == m) { 
					safeSequence[count++]=i; 
					finish[i]=true; 
					flag=true; 
           
					// If it has sufficient resources then allocated them: 
					//for (j =0; j < m; j ++) {Work[j] += Allocation[i][j];}        
					for (j = 0;j < m; j++) { 
						work[j] = work[j]+palloc[i][j]; 
					} 
				} 
			} 
		} 
		if (flag == false) { 
			break; 
		} 
	}

	//Stop when all processes have been looked at	
	if (count < n) { 
		System.out.println("  " + "The System is UnSafe!");
		return false;
	} 
	else{ 
		System.out.println("  " + "The System is Safe!");
		/*
		System.out.println("Following is the SAFE Sequence"); 
		for (int i = 0;i < n; i++) { 
			System.out.print("P" + safeSequence[i]); 
			if (i != n-1) 
				System.out.print(" -> "); 
		} */
      return true;
	} 
} 
  
void calculateNeed() { 
	for (int i = 0;i < n; i++) { 
		for (int j = 0;j < m; j++) { 
			need[i][j] = max[i][j]-alloc[i][j]; 
		} 
	}         
} 

/*
 * TODO: provide an implementation for the following method
 * follow the instructions in the Lab 07 PDF
 */
//for a particular process P_i and his array of resource requests Request
public boolean resourceRequest(int i, int pavail[], int palloc[][], int pneed [][], int req[], int n, int m) {
	/*
	 * TODO add code here 
	 */
	
	return false;
    
}

public static void main(String[] args) {   

	Bankers gfg = new Bankers(); 
       
	gfg.initializeValues();     
		
	//Calculate the Need Matrix     
	gfg.calculateNeed();             
          
	// Check whether system is in safe state or not  
	System.out.println("Check whether system is in safe state or not: ");   
	System.out.println("  " + gfg.isSafe(avail, alloc, need,n,m));
	
	//check to allocate resources
	System.out.println("Try to allocate resources");
	int i = 1;
	// for i = 1 we have 
	  // alloc[1] = { 2, 0, 0 }; 
	  // max [1]  = { 3, 2, 2 } and 
	  // avail    = { 3, 3, 2 };  
	int req[] = new int[] { 1, 2, 2 }; 
	gfg.resourceRequest(i, avail, alloc, need, req,  n, m);
} 


} 
