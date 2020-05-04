import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CompareLinkAndArray {
    private static void insertNum(List<Integer> l,String type){

        int number = 5;
        int insertIndex = 100000/2;
        for(int i = 0; i < 100000;i++){
            l.add(0,number);
        }
        long startTime = System.currentTimeMillis();
        l.add(insertIndex,10);
        long endTime = System.currentTimeMillis();
//        System.out.printf("在%s前面插入数据总耗时为%d毫秒 \n",type,endTime-startTime);
        System.out.printf("在%s中间插入数据总耗时为%d毫秒 \n",type,endTime-startTime);
    }

    public static void main(String[] args) {
        List<Integer> l;
        l = new ArrayList<>();
        insertNum(l,"ArrayList");
        l = new LinkedList<>();
        insertNum(l,"LinkedList");
    }
}
