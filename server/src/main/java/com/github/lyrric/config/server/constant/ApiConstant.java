package com.github.lyrric.config.server.constant;

/**
 * Created on 2019/3/20.
 *
 * @author wangxiaodong
 */
public class ApiConstant {
    /**
     * api前缀
     */
    public static final String API_PREFIX = "/api/v1.0";

    public static boolean isMatch(String s, String p) {
        int i = 0, j = 0;
        while(i<s.length() && j<p.length()){
            if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'){
                i++;
                if(j<p.length()-1 && p.charAt(j+1) != '*'){
                    j++;
                }else if(j == p.length()-1){
                    j++;
                }
            }else if(j<p.length()-1 && p.charAt(j+1) == '*'){
                j+=2;
            }else{
                return false;
            }
        }
        if(i == s.length() && j == p.length()){
            return true;
        }else if(j == (p.length()-2) && p.charAt(j+1) == '*'){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isMatch("mississippi", "mis*is*p*."));
    }
}
