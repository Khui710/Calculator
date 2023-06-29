package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.View;

import com.example.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {//继承自AppCompatActivity的类MainActivity

    private ActivityMainBinding  binding;//用于存储布局文件中的组件
    private MyViewModel myViewModel;//数据模型,处理数据逻辑

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //调用父类的onCreate方法
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);//获取所欲控件，使用setContentView方法将布局文件activity_main.xml与当前活动绑定，并将绑定结果存储在binding对象中
        myViewModel = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);//获取数据模型，
        //事件监听
        myViewModel.getMainNum().observe(this, new Observer<String>() {//使用observe方法监听myViewModel中的mainNum数据的变化，并在数据变化时更新myTextView的显示内容
            @Override
            public void onChanged(String s) { //用于监听mainNum数据发生改变
                binding.myTextView.setText(myViewModel.getMainNum().getValue());//让myTextView显示mainNum的数值，在数据变化时更新myTextView的显示内容
                                                                                //当mainNum数据发生变化时，调用此方法，将myTextView的文本内容设置为mainNum的值
                //让textView显示用户当前输入的式子
                if(myViewModel.sign2.equals("")) {
                    if (myViewModel.sign1.equals("")) {
                        binding.textView.setText(myViewModel.getMainNum().getValue());
                    } else {//如果是像a+b这种式子
                        binding.textView.setText(myViewModel.num[0] + myViewModel.sign1 + myViewModel.getMainNum().getValue());
                    }
                }else{//如果是像a+b*c这种式子
                    binding.textView.setText(myViewModel.num[0] + myViewModel.sign1 + myViewModel.num[1] + myViewModel.sign2 +myViewModel.getMainNum().getValue());
                }
            }
        });
         /*为每个按钮设置点击事件监听器，当按钮被点击时，
        调用myViewModel的setMainNum方法更新mainNum的值*/
        binding.button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("0");
            }
        });
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("1");
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("2");
            }
        });
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("3");
            }
        });
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("4");
            }
        });
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("5");
            }
        });
        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("6");
            }
        });
        binding.button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("7");
            }
        });
        binding.button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("8");
            }
        });
        binding.button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("9");
            }
        });
        binding.buttonPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!myViewModel.havePoint){
                    myViewModel.getMainNum().setValue(myViewModel.getMainNum().getValue()+".");
                    myViewModel.havePoint=true;
                }
            }
        });
            /*
            首先判断myViewModel中的sign1是否为空，如果为空，则表示当前式子是以加号开头的，需要将sign1设置为"+"，
            将myViewModel中的num[0]设置为当前输入的数字，将myViewModel中的mainNum设置为"0"，并将havePoint设置为false。
             */
            /*
            如果sign1不为空，再判断myViewModel中的sign2是否为空。如果sign2为空，则表示当前式子是类似"a+b"的形式，需要进行加法运算
            调用mainNumWithNum_0_Tocal方法,将num[0]和mainNum进行加法运算，将结果存储在num[0]中，然后将sign1设置为"+"，
            将myViewModel中的mainNum设置为"0"，并将havePoint设置为false。
             */
            /*
            如果sign2不为空，则表示当前式子是类似"a+b*c"的形式，需要先进行乘法运算，再进行加法运算。
            首先调用myViewModel的mainNumWithNum_1_Tocal方法，将num[1]和mainNum进行乘法运算，
            将结果存储在mainNum中，然后将sign2设置为，将num[1]设置为空，
            再调用myViewModel的mainNumWithNum_0_Tocal方法，将num[0]和mainNum进行加法运算，
            将结果存储在num[0]中，最后将sign1设置为"+"，将myViewModel中的mainNum设置为"0"，并将havePoint设置为false。
             */
        binding.buttonjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1="+";
                    myViewModel.num[0] = myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }else if(myViewModel.sign2.equals("")){//如果是像a+b这种形式的式子
                    myViewModel.num[0] = myViewModel.mainNumWithNum_0_Tocal();
                    myViewModel.sign1 = "+";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }else{//如果是像a+b*c这种形式的式子
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_1_Tocal());
                    myViewModel.sign2 = "";
                    myViewModel.num[1] = "";
                    myViewModel.num[0] = myViewModel.mainNumWithNum_0_Tocal();
                    myViewModel.sign1 = "+";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }
            }
        });
        binding.buttonJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1="-";
                    myViewModel.num[0] = myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }else if(myViewModel.sign2.equals("")){//如果是像a+b这种形式的式子
                    myViewModel.num[0] = myViewModel.mainNumWithNum_0_Tocal();
                    myViewModel.sign1 = "-";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }else{//如果是像a+b*c这种形式的式子
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_1_Tocal());
                    myViewModel.sign2 = "";
                    myViewModel.num[1] = "";
                    myViewModel.num[0] = myViewModel.mainNumWithNum_0_Tocal();
                    myViewModel.sign1 = "-";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }
            }
        });
        binding.buttonChen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1="*";
                    myViewModel.num[0]=myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint =false;
                }else if(myViewModel.sign2.equals("")){
                    if(myViewModel.sign1.equals("*") || myViewModel.sign1.equals("/")){//按顺序进行计算
                        myViewModel.num[0] = myViewModel.mainNumWithNum_0_Tocal();
                        myViewModel.sign1 = "*";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint=false;
                    }else{//如果sign1是减号或者加号
                        myViewModel.num[1] = myViewModel.getMainNum().getValue();
                        myViewModel.sign2 = "*";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint=false;
                    }
                }else{//如果像a+b*c这种形式的式子
                    myViewModel.num[1] = myViewModel.mainNumWithNum_1_Tocal();
                    myViewModel.sign2 = "*";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }
            }
        });
        binding.buttonChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1="/";
                    myViewModel.num[0]=myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint =false;
                }else if(myViewModel.sign2.equals("")){
                    if(myViewModel.sign1.equals("*") || myViewModel.sign1.equals("/")){//按顺序进行计算
                        myViewModel.num[0] = myViewModel.mainNumWithNum_0_Tocal();
                        myViewModel.sign1 = "/";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint=false;
                    }else{//如果sign1是减号或者加号
                        myViewModel.num[1] = myViewModel.getMainNum().getValue();
                        myViewModel.sign2 = "/";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint=false;
                    }
                }else{//如果像a+b*c这种形式的式子
                    myViewModel.num[1] = myViewModel.mainNumWithNum_1_Tocal();
                    myViewModel.sign2 = "/";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }
            }
        });
        binding.buttonAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.sign2="";
                myViewModel.num[1]="";
                myViewModel.sign1="";
                myViewModel.num[0]="";
                myViewModel.getMainNum().setValue("0");
                myViewModel.havePoint=false;
            }
        });
        binding.buttonDengyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign2.equals("")){
                    if(!myViewModel.sign1.equals("")){
                        myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_0_Tocal());
                        if(myViewModel.getMainNum().getValue().contains(".")){
                            myViewModel.havePoint=true;
                        }else{
                            myViewModel.havePoint=false;
                        }
                        myViewModel.num[0]="";
                        myViewModel.sign1="";
                    }
                }else{//如果是像A+B*C这种形式的式子的时候
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_1_Tocal());
                    myViewModel.num[1]="";
                    myViewModel.sign2="";
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_0_Tocal());
                    if(myViewModel.getMainNum().getValue().contains(".")){
                        myViewModel.havePoint=true;
                    }else{
                        myViewModel.havePoint=false;
                    }
                    myViewModel.num[0]="";
                    myViewModel.sign1="";
                }
                binding.textView.setText(myViewModel.getMainNum().getValue());
            }
        });
        binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!myViewModel.getMainNum().getValue().equals("0")){
                    myViewModel.getMainNum().setValue(myViewModel.getMainNum().getValue().substring(0,myViewModel.getMainNum().getValue().length()-1));
                    if(myViewModel.getMainNum().getValue().equals("")){
                        myViewModel.getMainNum().setValue("0");
                    }
                }
            }
        });
        binding.buttonZhengFu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 在这里添加正负号按钮的点击事件处理逻辑
                if (myViewModel.getMainNum().getValue().equals("0")) {
                    return;
                }
                if (myViewModel.getMainNum().getValue().startsWith("-")) {
                    myViewModel.getMainNum().setValue(myViewModel.getMainNum().getValue().substring(1));
                } else {
                    myViewModel.getMainNum().setValue("-" + myViewModel.getMainNum().getValue());
                }
            }
        });
        /*
        开方运算按钮事件监听
         */
        binding.buttonKaifang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.getMainNum().setValue(myViewModel.calculateSquareRoot());
            }
        });

    }
}