package string;

import java.io.*;
import java.util.*;

class EncodeDemo
{
    /**
     * 使用String.getBytes() 将字符串进行编码 使用newString 的方式进行解码
     * 如果先用UTF-8 进行解码然后进行 
     * 
     */
    public static void main(String[] args) {

        String demo = "变化";
        try {
            byte [] b = demo.getBytes("GBK"); //gbk 编码的byte
            String str = new String(b,0,b.length,"UTF-8"); //使用utf-8 进行解码 会在原来的基础上 增加新内容
            byte [] ba = str.getBytes("UTF-8"); // 还是用 utf-8 编码 结果 现在得到的byte数组 已经和最原始的gbk 数组不相同了
            String str2 = new String(ba,0,ba.length,"GBK"); // 得到一个新的字符串
            System.out.println(str2);
        } catch (Exception e) {

        }
    }
}