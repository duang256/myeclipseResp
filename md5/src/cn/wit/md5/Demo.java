package cn.wit.md5;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Demo {
	public static void main(String[] args) {
		//md5����
		Md5Hash hash=new Md5Hash("123");
		System.out.println(hash.toString());
		//md5����
		hash=new Md5Hash("123","wit");
		System.out.println(hash.toString());
		//md5���� ɢ�����Σ�ɢ�д���Խ�࣬����Խ��ȫ
		hash=new Md5Hash("123","wit",2);
		System.out.println(hash.toString());
		hash=new Md5Hash("456","wit",2);
		System.out.println(hash.toString());
		
	}
}
