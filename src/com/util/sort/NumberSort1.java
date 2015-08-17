package com.util.sort;


public class NumberSort1 {
    /**
     * 简单选择排序
     * 在要排序的一组数中，选出最小（或者最大）的一个数与第1个位置的数交换；
     * 然后在剩下的数当中再找最小（或者最大）的与第2个位置的数交换，
     * 依次类推，直到第n-1个元素（倒数第二个数）和第n个元素（最后一个数）比较为止。
     * @param arr
     */
    public static void simpleSelectionSort(int[] arr){
        System.out.print("简单选择排序:");
        for(int i=0;i<arr.length;i++){
            int min = arr[i];
            int minIndex = i;
            for(int j=i+1;j<arr.length;j++) {
                int nextValue = arr[j];
                if(min > nextValue){
                    min = nextValue;
                    minIndex = j;
                }
            }
            
            if(minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            System.out.print(arr[i]+" ");
        }
        System.out.println("");
    }
    
    /**
     * 二元选择排序
     * 简单选择排序，每趟循环只能确定一个元素排序后的定位。
     * 我们可以考虑改进为每趟循环确定两个元素（当前趟最大和最小记录）的位置,从而减少排序所需的循环次数。
     * 改进后对n个数据进行排序，最多只需进行[n/2]趟循环即可
     * @param arr
     */
    public static void selection2Sort(int[] arr){
        System.out.print("二元选择排序:");
        int end = (int) Math.floor(arr.length / 2);
        for(int i=0;i<end;i++) {
            //取最小值和最大值
            int min = arr[i];
            int max = arr[i];
            int minIndex = i;
            int maxIndex = i;
            int maxpos = 0;
            for(int j=i+1;j<arr.length-i;j++) {
                if(min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
                if(max <= arr[j]) {
                    max = arr[j];
                    maxIndex = j;
                }
            }
            
            maxpos = maxIndex-1;
            if(minIndex != i) {                     
                arr[minIndex] = arr[i];
                arr[i] = min;
                //如果最大值是当前起始值，则它被换到最小值位置上了
                //需要重新改变最大值的索引为找到的最小值的索引
                if(minIndex == i) {
                    maxIndex = minIndex;
                }
            }
            if(minIndex != maxpos) {                        
                arr[minIndex] = arr[maxpos];
                arr[maxpos] = min;
            }
            System.out.print(arr[i]+"#"+arr[maxpos]);
        }
        System.out.println(" ");
    }
    
    /**
     * 冒泡排序
     * 在要排序的一组数中，对当前还未排好序的范围内的全部数，
     * 自上而下对相邻的两个数依次进行比较和调整，让较大的数往下沉，较小的往上冒。
     * 即：每当两相邻的数比较后发现它们的排序与排序要求相反时，就将它们互换。
     * @param arr
     */
    public static void bubbleSort(int[] arr){
        System.out.print("冒泡排序:");
        /*int size = arr.length - 1;
        while(size > 0) {
            int pos = 0;
            for(int j=0;j<size;j++) {
                if(arr[j] > arr[j+1]) {
                    pos = j+1;
                    int tmp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=tmp;
                }else{
                    pos = j;
                }
            }
            size = pos;
            System.out.print(arr[pos]+" ");
        }*/
        
        int temp; // 记录临时中间值   
        int size = arr.length; // 数组大小   
        for (int i = 0; i < size - 1; i++) {   
            for (int j = i + 1; j < size; j++) {   
                if (arr[i] < arr[j]) { // 交换两数的位置   
                    temp = arr[i];   
                    arr[i] = arr[j];   
                    arr[j] = temp;   
                }   
            } 
            System.out.print(arr[i]+" ");
        }   
        
        System.out.println(" ");
    }

    /**
     * 快速排序
     * 取其中一个值，把小于此值的放到其左边，大于此值的放到其右边,如此递归。
     * @param arr
     */
    public static void quickSort(int[] arr){
        QuicSort qs=new QuicSort();  
        qs.quick(arr);  
        for(int i=0;i<arr.length;i++){  
            System.out.print(arr[i]+" ");  
        }  
        System.out.println();  
    }
}
