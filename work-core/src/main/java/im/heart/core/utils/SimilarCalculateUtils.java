package im.heart.core.utils;

public class SimilarCalculateUtils {
	public static int fun(String source,String target){  
        int i,j;  
        int[][] d = new int[source.length()+1][target.length()+1];
        /*初始化临界值*/
        for(i=1;i<source.length()+1;i++){
            d[i][0]=i;  
        }
        /*初始化临界值*/
        for(j=1;j<target.length()+1;j++){
            d[0][j]=j;  
        }
        /*动态规划填表*/
        for(i=1;i<source.length()+1;i++){
            for(j=1;j<target.length()+1;j++){  
                if(source.substring(i-1, i).equals(target.substring(j-1, j))){
                    /*source的第i个和target的第j个相同时*/
                    d[i][j]=d[i-1][j-1];
                }else{
                    /*不同的时候则取三种操作最小的一个*/
                    d[i][j]=min(d[i][j-1]+1,d[i-1][j]+1,d[i-1][j-1]+1);  
                }  
            }  
        }  
        return d[source.length()][target.length()];  
    }  
    private static int min(int i, int j, int k) {  
        int min = i<j?i:j;  
        min = min<k?min:k;  
        return min;  
    }  
    
    
    
    public static void main(String[] args) {
        System.out.println(SimilarCalculateUtils.fun("SNOWY", "SUNNY"));
        System.out.println(SimilarCalculateUtils.fun("adb", "adbsss"));
        System.out.println(SimilarCalculateUtils.fun("abdd", "aebdd"));
        System.out.println(SimilarCalculateUtils.fun("travelling", "traveling"));
        System.out.println(SimilarCalculateUtils.fun("平整场地", "场地"));
        System.out.println(SimilarCalculateUtils.fun("平整场地 dd", "场地 dd 平整"));
        System.out.println(SimilarCalculateUtils.fun("平整场地", "我爱我d场地"));
    }
}
