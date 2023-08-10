package 回文数;

import org.junit.Test;

public class Solution {
    public boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }
        String s=x+"";
        char[] chars = s.toCharArray();
        int t=chars.length-1,h=0;

        while (t>h){
            if (chars[h]!=chars[t]){
                return false;
            }
            h++;
            t--;
        }
        return true;


    }

}
