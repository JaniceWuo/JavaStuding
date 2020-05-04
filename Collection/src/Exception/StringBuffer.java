package Exception;

public class StringBuffer {
    int capacity = 20;
    char[] value;
    int length = 0;

    public StringBuffer(String str){
        value = new char[capacity];
        if(str==null) return;
        if(capacity<str.length()){
            capacity = str.length()*2;
            value = new char[capacity];
        }
        if(capacity>=str.length()){
            System.arraycopy(str.toCharArray(), 0, value, 0, str.length());
        }
        length = str.length();

    }
    public void insert(int pos,char b) throws IndexIsNagetiveException,IndexIsOutofRangeException{
        insert(pos,String.valueOf(b));
    }
    public void insert(int pos,String b) throws IndexIsNagetiveException,IndexIsOutofRangeException{
        if(pos<0){
            throw new IndexIsNagetiveException("下标为负异常");
        }
        if(pos>length){
            throw new IndexIsOutofRangeException("下标超出范围");
        }
    }

    public int length(){
        return length;
    }

    public class IndexIsOutofRangeException extends Exception{
        public IndexIsOutofRangeException(){

        }
        public IndexIsOutofRangeException(String s){
            super(s);
        }
    }

    public class IndexIsNagetiveException extends Exception{
        public IndexIsNagetiveException(){

        }
        public IndexIsNagetiveException(String s){
            super(s);
        }
    }

    public static void main(String[] args) {
        StringBuffer str = new StringBuffer("hhh world");
        System.out.println(str);
        try {
            str.insert(20,"let");
        }catch (IndexIsOutofRangeException | IndexIsNagetiveException e){
            if (e instanceof IndexIsOutofRangeException){
                System.out.println("下标超出范围");
            }
            if (e instanceof IndexIsNagetiveException){
                System.out.println("下标为负");
            }
            System.out.println("异常具体原因："+e.getMessage());
            e.printStackTrace();
        }
    }
}
