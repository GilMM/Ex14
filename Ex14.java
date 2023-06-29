/**
* Maman 14
* @author (Gil Meshulami , 318621216)
* @version (25.12.20)
*/
public class Ex14
 {          
      /**
      * A static method that accepts as an array parameter that satisfies the above condition,
      * and returns the number that appears in the array only once.
      * Operations where the input length is small in each iteration of the loop in half (Binary Search). 
      * So the complications is O(log n).
      */                     
      static int findSingle(int []a)
      {                                   
            // Binary Search:
            int front = a.length - 1;
            int back = 0;
            while(back <= front){
                int mid = (front + back) / 2;
                if((back == mid || a[mid] != a[mid - 1]) && (front == mid || a[mid] != a[mid + 1])) 
                    return a[mid];
                if((front - mid) % 2 == 0){
                    if(a[mid] == a[mid + 1])
                        back = mid + 2;
                    else
                        front = mid - 2;
                }else{
                    if(a[mid] == a[mid + 1])
                        front = mid - 1;
                    else
                        back = mid + 1;                                   
                }                            
            }
            return a[back];
      }   
   
      /**           
       * The method finds the size of smallest subarray whose sum is greater than the number x. 
       * The method run once in the array so the complications are O(n).
       */               
      public static int smallestSubSum(int []array, int x)
      {            
         if (array != null && array.length == 0)
         {
             return -1;
         }
         int left = 0;
         int right = 0; 
         int sum = array[0];         
         int minLen = array.length + 1;
         while(right < array.length && left <= right)
         {   
             if (sum > x)
             {
                 minLen = Math.min(minLen,right - left + 1);
                 sum -= array[left++];
             }
             else
             {
                 right++;
                 if (right < array.length)
                 {
                     sum += array[right];
                 }
              }   
         }
         if (minLen == array.length + 1)
         {
            return -1;
         }
         return minLen;
      }
        
      /**
       * Prints and counts all the combinations whose first number is given and the second number begins with the value passed,
       * and continues with the same of the first number and higher values of the second number. 
       */                
      private static int solutions2(int num , int first , int second)
      {
          if (first + second >= num){
              return 0;
          }
          System.out.println(first + "+" + second + "+" + (num-first-second));
          return 1 + solutions2(num , first , second + 1);          
      }
      
      /**
       * Calls solution2 in order to print all the combinations with the given first number.
       * Then calls the same function recursively in order the get the combinations which begin in the succeeding numbers.
       */
      private static int solutions1(int num , int first)
      {
          if(first >= num - 1){
              return 0;
          }
          return solutions2(num,first,1) + solutions1(num,first + 1);                   
      }
      
      /**
       * A method that accepts as a parameter an integer positive number and returns the number of solutions to the equation between 3 and 30.
       */
      public static int solutions(int num)
      {
          if(num>30 || num<3){
              return 0;
          }
          return solutions1(num,1);         
      }    
      
      /**
       * Count the current cell if true, clears itself and all its neighbors recursively.
       * Each neighbor will also clear itself and its own neighbors when it is true. 
       * If the neighbor is already false or out of the matrix borders, the function does nothing.
       */
      private static void clearNeighbors(boolean [] [] mat, int row, int col){
          if(row < 0 || row >= mat.length || col < 0 || col >= mat.length || !mat[row][col]){
              return; 
          }
          mat[row][col] = false;
          clearNeighbors(mat,row + 1,col); //down
          clearNeighbors(mat,row - 1,col); //up
          clearNeighbors(mat,row,col + 1); //right
          clearNeighbors(mat,row,col - 1); //left
      }
      
      /**
       * If current cell is true we count a new true region. Then we clear the cell and its neighbors in order to prevent multiple countings
       * of the same regions. We return zero or one plus the count from the neighbors to right on the same row.
       */
      private static int cntTrueRegCol(boolean [] [] mat, int row, int col){
          if(col >= mat.length){
              return 0;
          }
          int count = 0;
          if(mat[row][col]){
              count = 1;
              clearNeighbors(mat,row,col);
          }
          return count + cntTrueRegCol(mat, row, col + 1);
      }
      
      /**
       * If the row is out of the matrix borders, the function does nothing. Otherwise it returns the number of two regions which begin in all 
       * columns of the current row plus all regions of the succeeding rows.
       */
      private static int cntTrueRegRow(boolean [] [] mat, int row)
      {
          if(row >= mat.length){
              return 0;
          }
          return cntTrueRegCol(mat, row, 0) + cntTrueRegRow(mat ,row + 1);
      }
      
      /**
       * A recursive method that accepts as a parameter a Boolean square matrix and returns several different true regions exist in the matrix.
       * If there is no true region the method will return zero.         
       */
      public static int cntTrueReg (boolean [] [] mat)
      {
         return cntTrueRegRow(mat, 0); 
      }
}