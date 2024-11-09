import android.content.*;
import android.graphics.*;
import android.os.*;
import android.widget.*;
import de.robv.android.xposed.*;
import de.robv.android.xposed.callbacks.*;
import java.util.*;
import android.app.*;

/* 注意：该类不要自己写构造方法，否者可能会hook不成功
 * 开发Xposed模块完成以后，关闭bebug以提升性能
 * debug模式只需要第一次重启，以后修改hook代码就不用重启了
 */

public class xposed implements IXposedHookLoadPackage
{
public static Activity 上下文;

	@Override
	public void handleLoadPackage(XC_LoadPackage.LoadPackageParam 包名) throws Throwable {
		//判断是不是这个软件包名,不判断的话，全部的软件都会被hook
		if (!包名.packageName.equals("jihua.cn.fanqie"))  //填软件包名
			return;
		//提示("已hook的包名∶" + 包名.packageName);
		XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {  
   @Override  
   protected void afterHookedMethod(MethodHookParam param) throws Throwable {  
       Context context = (Context) param.args[0];  
       ClassLoader classLoader = context.getClassLoader();
	if(上下文==null){XposedHelpers.findAndHookMethod(Activity.class,"onCreate",Bundle.class,new XC_MethodHook() {
		@Override
		protected void afterHookedMethod(MethodHookParam 参数) throws Throwable
		{super.afterHookedMethod(参数);
		if(上下文==null){上下文=(Activity)参数.thisObject;
		提示("功能模块已开启");
		XposedBridge.log("得到Activity上下文");}}});}
	
	
		//查找和hook方法，演示hook boolean方法
		XposedHelpers.findAndHookMethod(
			"jihua.cn.fanqie.entity.user.UserVipInfo"//要hook的应用类名
			,classLoader,
			"isVip"//要hook的方法
			//,String.class//函数名，可以注释掉,String就是String.class
			, new XC_MethodHook()//回调
			{@Override
				protected void afterHookedMethod(MethodHookParam 参数) throws Throwable
				{//拦截之后要做什么
					super.afterHookedMethod(参数);
					参数.setResult(true);////强制返回boolean为true
				}});
	    XposedHelpers.findAndHookMethod(
			"jihua.cn.fanqie.entity.user.UserVipInfo"//要hook的应用类名
			,classLoader,
			"isForeverVip"//要hook的方法
			//,String.class//函数名，可以注释掉,String就是String.class
			, new XC_MethodHook()//回调
			{@Override
				protected void afterHookedMethod(MethodHookParam 参数) throws Throwable
				{//拦截之后要做什么
					super.afterHookedMethod(参数);
					参数.setResult(true);////强制返回boolean为true
				}});
		}
		
		private void 提示(String 内容)
	{try{
		Toast.makeText(上下文, 内容,  1000).show();
		}catch(Exception e){XposedBridge.log("提示异常:"+e);}
		XposedBridge.log("免重启方法输出"+内容);
		// 雨 道  汉 化: 实现这个方法
}

});};} 
