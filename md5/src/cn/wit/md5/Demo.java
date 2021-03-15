package cn.wit.md5;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Demo {
	public static void main(String[] args) {
		//md5加密
		Md5Hash hash=new Md5Hash("123");
		System.out.println(hash.toString());
		//md5加盐
		hash=new Md5Hash("123","wit");
		System.out.println(hash.toString());
		//md5加盐 散列两次，散列次数越多，密码越安全
		hash=new Md5Hash("123","wit",2);
		System.out.println(hash.toString());
		hash=new Md5Hash("456","wit",2);
		System.out.println(hash.toString());
		
	}
}
