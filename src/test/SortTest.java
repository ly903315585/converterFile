package test;

import com.util.sort.NumberSort;
import com.util.sort.NumberSort1;
import com.util.sort.QuicSort;

public class SortTest {

    public static void main(String[] args) {
        int[] arr = new int[]{29,4,55,67,22,99,27,44,2,30,100,88};
        NumberSort sort = new NumberSort();
        NumberSort1 sort1 = new NumberSort1();
        /***实现1***/
        //sort1.simpleSelectionSort(arr);//简单选择排序
        //sort1.selection2Sort(arr);//二元选择排序
        //sort1.bubbleSort(arr);//冒泡排序
        //sort1.quickSort(arr);//快速排序
        
        /***实现2***/
        //sort.bubbleSort(arr);
        //sort.insertSort(arr);
        //sort.selectSort(arr);
        //sort.mergeSort(arr, 0, 10);
        //sort.quickSort(arr, 0, 10);
        
        String s = "1234567890asdfghjklqwertyu";
        System.out.println("substring(0, 5)="+s.substring(0, 5));
        System.out.println("substring(5, 6)="+s.substring(5, 6));
        System.out.println("substring(8)="+s.substring(8));
    }
}
