import java.util.Scanner;

public class 公农历转换 {

	public static void main(String[] args) {
		//变量区
		String A="公历";
		String C="农历";
    
    //这三个变量是储存输入的日期的，这个值是我写这一段代码的公历日期
		int day=6;
		int month=9;
		int year=2018;
    
    //这三个是用来储存最后计算出来的结果的，日期是我的生日啦
		int nday=26;
		int nmonth=1;
		int nyear=1999;
    
    //这是服务下面的计算结果的一些变量
    double x;//暂存器
		int Q=0; //Q,R,n 是网上公式给出的变量
		int R=0;
		int n;
		double temp;//暂存器
		int td=0;// td:total day 这是储存总日子用的
		int r;//暂存器
		int t;//暂存器
		int s=1;// 1为公历，2为阴历
		
		//输入区
		Scanner in = new Scanner(System.in);
		System.out.print("公历年份：");
		year=in.nextInt();
		System.out.print("公历月份：");
		month=in.nextInt();
		System.out.print("公历日份：");
		day=in.nextInt();
		System.out.println("您输入的公历日期为：");
		System.out.println(A+": "+year+"/"+month+"/"+day);
		System.out.println("其农历日期为");
		
		//计算区
		if(month==1) {
			td=day+0;
		}else if(month==2){
			td=day+31;
		}else{
			r=year/4;
			t=r*4;
			if(year==t) { //区分闰年和平年（将年份除以四，用int储存没有小数所以平年被除了以后再乘四回来会不等于原有的年数借此判断闰年和平年。）
				switch(month) { //暴力计算闰年天数
				case 3 :
					td=day+31+29;
					break;
				case 4 :
					td=day+31+29+31;
					break;
				case 5 :
					td=day+31+29+31+30;
					break;
				case 6 :
					td=day+31+29+31+30+31;
					break;
				case 7 :
					td=day+31+29+31+30+31+30;
					break;
				case 8 :
					td=day+31+29+31+30+31+30+31;
					break;
				case 9 :
					td=day+31+29+31+30+31+30+31+31;
					break;
				case 10 :
					td=day+31+29+31+30+31+30+31+31+30;
					break;
				case 11 :
					td=day+31+29+31+30+31+30+31+31+30+31;
					break;
				case 12 :
					td=day+31+29+31+30+31+30+31+31+30+31+30;
					break;
				}
				//System.out.println("闰:"+td);//调试用
			}else {
				switch(month) { //暴力计算平年天数
				case 3 :
					td=day+31+28;
					break;
				case 4 :
					td=day+31+28+31;
					break;
				case 5 :
					td=day+31+28+31+30;
					break;
				case 6 :
					td=day+31+28+31+30+31;
					break;
				case 7 :
					td=day+31+28+31+30+31+30;
					break;
				case 8 :
					td=day+31+28+31+30+31+30+31;
					break;
				case 9 :
					td=day+31+28+31+30+31+30+31+31;
					break;
				case 10 :
					td=day+31+28+31+30+31+30+31+31+30;
					break;
				case 11 :
					td=day+31+28+31+30+31+30+31+31+30+31;
					break;
				case 12 :
					td=day+31+28+31+30+31+30+31+31+30+31+30;
					break;
				}
				//System.out.println("平:"+td);//调试用
			}
		}
		
		if(s==1) {//这个if是预备给以后做农历转公历用的
			Q=(year-1977)/4;//公式来源于百度
			R=year-1977-4*Q;//这是百度出来的公式的调整版，所以看起来和原公式不太一样
      //https://zhidao.baidu.com/question/4405236.html(这是百度那个公式的网址
			//System.out.println(Q); //调试用
			//System.out.println(n); //调试用
			temp=14*Q+10.6*(R+1)+td;
			x=temp/29.5;//这个部分依旧是之前提到的算法
			//System.out.println(x);//调试用
			n=Double.valueOf(x).intValue();//将double变量转换为int变量消除小数
			x=temp-n*29.5;//获取除法里的余数
			nday=Double.valueOf(x).intValue();//将double变量转换为int变量消除小数
			
      //下面这一段开始就是人为的靠经验主义去消除一些误差了
      //原文的公式是不提供月份的计算的
      //所以所有的月份都是我自己靠经验主义瞎编的判断
      
      if(year>1997) {//这里是我消除1997年以后大量出现的日期误差一天的情况用的
				nday=nday+1;
			}
      
			if((nday-day)>9) {//这里是我暴力利用几次计算的结果编辑的月份计算（瞎猜）
				nmonth=month-2;
				if(month==1) {
					nmonth=11;
				}else if(month==2) {
					nmonth=12;
				}
			}else if((nday-day)>0||(day-nday)>10) {
				nmonth=month-1;
				if(month==1) {
					nmonth=12;
				}
			}else {
				nmonth=month;
			}
      
			if((month-nmonth)>=0) {//这里是年份的计算也是经验主义暴力猜
				nyear=year;
			}else {
				nyear=year-1;
			}
			//System.out.println(A+": "+year+"/"+month+"/"+day);//没有什么卵用
			System.out.println(C+": "+nyear+"/"+nmonth+"/"+nday);
		}
		
	}

}
