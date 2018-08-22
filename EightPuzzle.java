import java.util.*;
class State{
	public int mat[][] = new int[3][3];
	public ArrayList<State> visited = new ArrayList<State>();
	public State(int m[][]){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				mat[i][j] = m[i][j];
			}
		}
	}
    public State(State s){
        visited = new ArrayList<State>(s.visited);
        for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				mat[i][j] = s.mat[i][j];
			}
		}
    }
    public State(){}
	public boolean compareState(State s){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(s.mat[i][j] != mat[i][j]) return false;
			}
		}
		return true;
	}
    public boolean checkIfVisited(State state){
		boolean result = false;
		for(State s: visited){
            if(s.compareState(state)) result = true;
		}
		return result;
	}
}	
public class EightPuzzle{
	public static void main(String args[]){		
		State initial = new State(new int[][]{
					{1, 8, 2},
					{4, 6, 3},
					{7, 5, 0}
				});				
		State goal = new State(new int[][]{
					{1, 2, 3},
					{4, 5, 6},
					{7, 8, 0}
				});
		SteepestAscent(initial, goal);	
	}
    
	public static void SteepestAscent(State initState, State goalState){
        State temp = initState;
        System.out.println("Solution Path:");
        while(!temp.compareState(goalState)){
            int currCost = 0;
			State[] s = getPossibleStates(temp);
            for(int i=0; i<s.length; i++){
			    if(getCost(s[i], goalState) > currCost && !s[i].checkIfVisited(s[i])){
                    temp = s[i];
                    currCost = getCost(s[i], goalState);
                }
            }
            System.out.println("Cost : " + currCost + " " + Arrays.deepToString(temp.mat));
            temp.visited.add(temp);           
        }
        System.out.println("Found : " + Arrays.deepToString(temp.mat));
	}
	public static int getCost(State s, State goal){
		int quality = 0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(s.mat[i][j] == goal.mat[i][j]) quality++;
			}
		}
		return quality;
	}
	public static State[] getPossibleStates(State s){
		State[] result = new State[4];
		int[] coord = getBlankSpace(s);
		int x = coord[0], y = coord[1], i = 0;
		if(x-1 < 3 && x-1 >=0){
			result[i] = swappedState(s, x, y, x-1, y);
			i++;
		}
		if(x+1 < 3 && x+1 >= 0){
			result[i] = swappedState(s, x, y, x+1, y);			
            i++;
		}
		if(y+1 < 3 && y+1 >= 0){
			result[i] = swappedState(s, x, y, x, y+1);
            i++;
		}		
		if(y-1 < 3 && y-1 >= 0){
			result[i] = swappedState(s, x, y, x, y-1);
            i++;
		}
        
        //resizing the array
        State r[] = Arrays.copyOf(result, i);
        
		return r;
	}
	public static State swappedState(State s, int  x, int y, int toX, int toY){
		State temp = new State(s);
		temp.mat[x][y] = s.mat[toX][toY];
		temp.mat[toX][toY] = 0;
		return temp;
	}
	public static int[] getBlankSpace(State s){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(s.mat[i][j] == 0) return new int[]{i, j};
			}
		}
		return new int[]{-1, -1};		
	}	
}