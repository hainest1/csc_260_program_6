/*
	Author: Thomas Haines
	Course: CSC260.002
	Date: 10/16/2016
	Assignment: #6
	Instructor: Fox
*/

/*
	Description: This program generates a random array and sorts
	the elements in a separate array. It then calculates a 
	trimmed mean and mode for both the sorted and unsorted arrays,
	and then removes duplicate elements from each. 
	
*/

import java.util.*;

public class ArraySorting {
	public static void main(String[] args)
	{
		int[] unsorted = new int[30];	//instantiate thhe sorted and unsorted arrays 
		int[] sorted = new int[30];
		int numberUnsorted, numberSorted;
		
		unsorted = fillArray();		//Fill unsorted with random int values
		
		for (int i = 0; i < 30; i++)	//copy contents of unsorted into sorted
			sorted[i] = unsorted [i];
		
		Arrays.sort(sorted); 	//Sort sorted array using Arrays class
		
		System.out.printf("Unsorted trimmed mean: %5.2f%n", trimmedMeanUnsorted(unsorted));	//output mean and mode
		System.out.printf("Sorted trimmed mean: %5.2f%n", trimmedMeanSorted(sorted));
		System.out.println("Unsorted mode: " + modeUnsorted(unsorted));
		System.out.println("Sorted mode: " + modeSorted(sorted));
		System.out.println("");
		
		numberUnsorted = removeDuplicatesUnsorted(unsorted);	//remove duplicates and return new array length
		numberSorted = removeDuplicatesSorted(sorted);
		
		System.out.println("Unsorted list with duplicates removed: ");	//print new unsorted list
		for (int i = 0; i < numberUnsorted; i++)
			System.out.println(unsorted[i]);
		
		System.out.println("");
		
		System.out.println("Sorted list with duplicates removed: ");	//print new sorted list
		for (int i = 0; i < numberSorted; i++)
			System.out.println(sorted[i]);
		
	}
	
	//Fill an array with 30 random elements
	public static int[] fillArray()
	{
		Random g = new Random();	//create random number generator
		int[] filled = new int[30];	//new array to store random values
		
		for(int i = 0; i < 30; i++)	//fill array with random values
			filled[i] = g.nextInt(30) + 1;
		
		return filled;	//return the filled array
		
	}
	
	//Return trimmed mean for unsorted array
	public static double trimmedMeanUnsorted(int[] unsorted)
	{
		int sum = 0, min = 30, max = 1;
		
		for (int i = 0; i < 30; i++)	//add up each element in the array
		{
			sum += unsorted[i];
			
			if (unsorted[i] < min)	//set a new low value if lower than current min
				min = unsorted[i];
			if (unsorted[i] > max) //set a new high value if higher than current max
				max = unsorted[i];
		}
		
		return (double) (sum - min - max) / 28;	//calculate mean without the min and max values
		
	}
	
	//Return trimmed mean for sorted array
	public static double trimmedMeanSorted(int[] sorted)
	{
		int sum = 0;
		
		for (int i = 1; i < 29; i++)	//add up 2nd through 29th elements
			sum += sorted[i];
		
		return (double) sum / 28;	//return mean of these elements
		
	}
	
	//return mode for unsorted array
	public static int modeUnsorted(int[] unsorted)
	{
		int current, max = 0, mode = 0;
		
		for (int i = 0; i < 30 ; i++)	
		{
			current = 1; // we match unsorted[i]
			for(int j = i + 1 ; j < 30 ; j++)	//check each element following i
				
				if(unsorted[i] == unsorted[j]) //if they are equal increase current count
					current++;
			
			if(current > max)	//if current is higher than max, set max to current and mode to the current array element
			{
				max = current;
				mode = unsorted[i];
			}
		}
		
		return mode;
		
	}
	
	//returns mode for sorted array
	public static int modeSorted(int[] sorted)
	{
		int current = 1, max = 0, mode = 0;
		
		for (int i = 0; i < 29 ; i++)
		{
			if (sorted[i] == sorted[i + 1])	//increment countt if i and i+1 are the same
				current++;
			else	//reset count if a new number is detected
				current = 1;

			if(current > max)	//if the current number has more occurances than the max, set a new max and set mode to current array element
			{
				max = current;
				mode = sorted[i];
			}
		}
		
		return mode;
		
	}
	
	//remove duplicates from unsorted array
	public static int removeDuplicatesUnsorted(int[] unsorted)
	{
		int n = unsorted.length;
		
		for (int i = 0; i < n; i++)
		{
			for(int j = i + 1; j < n; j++)
			{
				if(unsorted[i] == unsorted[j]) 
				{
					//remove unsorted[j]
					for(int k = j; k < n - 1; k++)
						unsorted[k] = unsorted[k + 1];

					n--;
				}
			}
		}

		return n;
		
	}
	
	//remove duplicates from sorted array
	public static int removeDuplicatesSorted(int[] sorted)
	{
		int n = sorted.length;
		
		for (int i = 0; i < n - 1; i++)
			while(sorted[i] == sorted[i + 1])	//if two consecutive numbers are equal
			{
					for(int k = i + 1; k < n - 1; k++)	//shift each number following i+1 left by 1
						sorted[k] = sorted[k + 1];
				
				if (i < n)	//if i is less than n, decrement n
					n--;
			}

		return n;
		
	}
	
}

/* Output:

	Unsorted trimmed mean: 12.50
	Sorted trimmed mean: 12.50
	Unsorted mode: 3
	Sorted mode: 3
	
	Unsorted list with duplicates removed: 
	25
	3
	11
	4
	1
	15
	10
	21
	7
	22
	13
	16
	19
	8
	24
	26
	6
	18
	14
	
	Sorted list with duplicates removed: 
	1
	3
	4
	6
	7
	8
	10
	11
	13
	14
	15
	16
	18
	19
	21
	22
	24
	25
	26

*/