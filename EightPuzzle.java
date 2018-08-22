import java.util.*;
class Node{
	public int mat[][] = new int[3][3];
	public ArrayList<Node> visited = new ArrayList<Node>();
	public Node(int m[][]){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				mat[i][j] = m[i][j];
			}
		}
	}
    public Node(Node s){
        visited = new ArrayList<Node>(s.visited);
        for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				mat[i][j] = s.mat[i][j];
			}
		}
    }
    public Node(){}
	public boolean compareNode(Node s){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(s.mat[i][j] != mat[i][j]) return false;
			}
		}
		return true;
	}
    public boolean checkIfVisited(Node Node){
		boolean result = false;
		for(Node s: visited){
            if(s.compareNode(Node)) result = true;
		}
		return result;
	}
}	
public class EightPuzzle{
	public static void main(String args[]){		
		Node initial = new Node(new int[][]{
					{1, 8, 2},
					{4, 6, 3},
					{7, 5, 0}
				});				
		Node goal = new Node(new int[][]{
					{1, 2, 3},
					{4, 5, 6},
					{7, 8, 0}
				});
		SteepestAscent(initial, goal);	
	}
    
	public static void SteepestAscent(Node initNode, Node goalNode){
        Node temp = initNode;
        System.out.println("Solution Path:");
        while(!temp.compareNode(goalNode)){
            int currCost = 0;
			Node[] s = getPossibleNodes(temp);
            for(int i=0; i<s.length; i++){
			    if(getCost(s[i], goalNode) > currCost && !s[i].checkIfVisited(s[i])){
                    temp = s[i];
                    currCost = getCost(s[i], goalNode);
                }
            }
            System.out.println("Cost : " + currCost + " " + Arrays.deepToString(temp.mat));
            temp.visited.add(temp);           
        }
        System.out.println("Found : " + Arrays.deepToString(temp.mat));
	}
	public static int getCost(Node s, Node goal){
		int quality = 0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(s.mat[i][j] == goal.mat[i][j]) quality++;
			}
		}
		return quality;
	}
	public static Node[] getPossibleNodes(Node s){
		Node[] result = new Node[4];
		int[] coord = getBlankSpace(s);
		int x = coord[0], y = coord[1], i = 0;
		if(x-1 < 3 && x-1 >=0){
			result[i] = swappedNode(s, x, y, x-1, y);
			i++;
		}
		if(x+1 < 3 && x+1 >= 0){
			result[i] = swappedNode(s, x, y, x+1, y);			
            		i++;
		}
		if(y+1 < 3 && y+1 >= 0){
			result[i] = swappedNode(s, x, y, x, y+1);
            		i++;
		}		
		if(y-1 < 3 && y-1 >= 0){
			result[i] = swappedNode(s, x, y, x, y-1);
		        i++;
		}
        
        	//resizing the array
        	Node r[] = Arrays.copyOf(result, i);
        
		return r;
	}
	public static Node swappedNode(Node s, int  x, int y, int toX, int toY){
		Node temp = new Node(s);
		temp.mat[x][y] = s.mat[toX][toY];
		temp.mat[toX][toY] = 0;
		return temp;
	}
	public static int[] getBlankSpace(Node s){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(s.mat[i][j] == 0) return new int[]{i, j};
			}
		}
		return new int[]{-1, -1};		
	}	
}
