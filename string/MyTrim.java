package string;

class MyTrim 
{
    /*相当于 System.out.print(" 上海你好 ".trim());*/
    public static String trim_test(String s){
        StringBuilder builder = new StringBuilder(s);
        int firstpos = 0;
        while(builder.indexOf(" ",firstpos) == firstpos) {
            firstpos++;
        }
        int lastpos = builder.length()-1;

        while(builder.lastIndexOf(" ",lastpos) == lastpos) {
            lastpos--;
        }
        
        return builder.substring(firstpos,lastpos+1);
    }

    /*自定义一个翻转函数
    * 相当于 StringBuilder 里面的reverse
    */
    public static String myReverse(String s)
    {
        char[] ch = new char[s.length()];
        s.getChars(0,s.length(),ch,0);
        for (int a = 0; a < ch.length/2 ; a++) {
            char temp = ch[a];
            ch[a] = ch[ch.length - a-1];
            ch[ch.length-a-1] = temp;
        }
        for (int a = 0; a < ch.length ; a++) {
            System.out.println(ch[a]); 
        }
        return new String(ch);
    }

    /*出现的次数 一个字符串在另外一个字符串出现的次数*/
    public static int appearCount(String needle,String stark)
    {
        int count = 0 , pos = 0;

        while ((pos = stark.indexOf(needle,pos)) > -1) {
            count++ ;
            pos++;
        }
        return count;
    }

    /*两个字符串相同的最大的子串*/
    public static String largestSubString(String needle,String stark)
    {
        String longstring = "",shortstring = "";
        longstring = needle.length() > stark.length()? needle:stark;
        shortstring = needle.length() < stark.length()? needle:stark;
        int len = shortstring.length();
        
        for (int a = 0; a< len ; a++) {
            for (int b = 0; b <=a; b++) {
                if(longstring.indexOf(shortstring.substring(b,b+len-a)) > -1) 
                {
                    return shortstring.substring(b,b+len-a);
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {

        // System.out.print(MyTrim.largestSubString("zhas","cb"));
        // System.out.print(MyTrim.appearCount("zh","zhzhzhzh"));
        // System.out.println(new StringBuilder("上海你好").reverse());
    }
}

