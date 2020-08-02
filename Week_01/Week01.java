package Homework;

import java.util.*;


class ListNode {
	      int val;
	      ListNode next;
	      ListNode() {}
	      ListNode(int val) { this.val = val; }
	      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	  }



public class Week01 {
	
	
	
	//改写Deque
	public void reWrite()
	{
		Deque<String> deque = new LinkedList<String>();
		deque.addLast("a");
		deque.addLast("b");
		deque.addLast("c");
		System.out.println(deque);
		
		String str = deque.peekLast();
		System.out.println(str);
		System.out.println(deque);
		
		while(deque.size() > 0)
		{
			System.out.println(deque.removeLast());
		}
		System.out.println(deque);
	}
	

	
	//删除排序数组中的重复项
	public int removeDuplicates(int[] nums) {
	    if(nums.length == 0)
	    {
	        return 0;
	    }

	    int len = 1;
	    for(int i = 0, j = 0; i < nums.length; i++)
	    {
	        if(nums[i] != nums[j])
	        {
	            nums[++j] = nums[i];
	            len++;
	        }
	    }
	    return len;
	}
	

	
	
	//旋转数组
	public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
	
    
    
    
    //合并两个有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        prev.next = l1 == null ? l2 : l1;
        return prehead.next;
    }
    
    
    
    
    //合并两个有序数组
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int [] nums1_copy = new int[m];
        System.arraycopy(nums1, 0, nums1_copy, 0, m);

        int p1 = 0;
        int p2 = 0;
        int p = 0;

        while ((p1 < m) && (p2 < n))
        {
          nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];
        }

        if (p1 < m)
          System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        if (p2 < n)
          System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
      }

    
    
    
    //两数之和
    public int[] twoSum(int[] nums, int target) {
        int m=0,n=0,k,board=0;
        int[] res=new int[2];
        int[] tmp1=new int[nums.length];
        System.arraycopy(nums,0,tmp1,0,nums.length);
        
        Arrays.sort(nums);

        for(int i=0,j=nums.length-1;i<j;){
            if(nums[i]+nums[j]<target)
                i++;
            else if(nums[i]+nums[j]>target)
                j--;
            else if(nums[i]+nums[j]==target){
                m=i;
                n=j;
                break;
            }
        }
        for(k=0;k<nums.length;k++){
            if(tmp1[k]==nums[m]){
                res[0]=k;
                break;
            }
        }
        for(int i=0;i<nums.length;i++){
            if(tmp1[i]==nums[n]&&i!=k)
                res[1]=i;
        }
        return res;
    }
    
    
    
    
    //移动零
    public void moveZeroes(int[] nums) {
        if(nums.length < 2)
        {
            return;
        }

        int numOfZero = 0, zero = 0;
        for(int pointer = 1; pointer < nums.length; pointer++)
        {
            if(nums[pointer] == 0)
            {
                numOfZero++;
            }
            else
            {
                if(nums[zero] != 0)
                {
                    zero ++;
                }
                nums[zero] = nums[pointer];
            }
        }

        for(int pointer = zero + 1; pointer < nums.length; numOfZero--)
        {
            nums[pointer++] = 0;            
        }
    }
    
    
    
    
    //加一
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
    
    
    
    
    //接雨水
    public int trap(int[] height) {
        if(height.length < 2)
        {
            return 0;
        }

        int res = 0;
        int left = 0, right = height.length - 1;
        int leftMax = height[left], rightMax = height[right];
        while(left < right)
        {
            if(leftMax < rightMax)
            {
                res += (leftMax - height[left++]);
                leftMax = Math.max(leftMax, height[left]);
            }
            else
            {
                res += (rightMax - height[right--]);
                rightMax = Math.max(rightMax, height[right]);
            }
        }
        return res;
    }
}


	//设计循环双端队列
class MyCircularDeque {

    private int[] data;
    private int capacity, size;
    private int start;
    private int end = -1;
    
    
    public MyCircularDeque(int k) {
        data = new int[k];
        capacity = k;
    }
    
    public boolean insertFront(int value) {
        if (isFull()) return false;
        
        if (!isEmpty())
            start = (start - 1 + capacity) % capacity;
        else 
            start = end = 0;
        
        data[start] = value;
        size++;
        
        return true;
    }
    
    public boolean insertLast(int value) {
        if (isFull()) return false;
        
        end = (end + 1) % capacity;
        data[end] = value;
        size++;
        return true;
    }
    
    public boolean deleteFront() {
        if (isEmpty()) return false;
        
        start = (start + 1) % capacity;
        size--;
        return true;
    }
    
    public boolean deleteLast() {
        if (isEmpty()) return false;
        
        end = (end - 1 + capacity) % capacity;
        size--;
        
        return true;
    }
    
    public int getFront() {   
        return isEmpty() ? -1 : data[start];
    }
    
    public int getRear() {
        return isEmpty() ? -1 : data[end];
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == capacity;
    }
}





