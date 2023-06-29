package com.example.calculator;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

//用来储存数据模型
public class MyViewModel extends ViewModel {//创建一个继承自ViewModel的类MyViewModel
    private MutableLiveData<String> mainNum;//主数值（用户正在操作的数）
    public String num[] = {"",""};//储存参与计算的数值
    public String sign1 = "",sign2="";//用于储存运算符
    public boolean havePoint = false;//主数值中是否包含小数点
    public boolean isNegative = false;//判断当前数值是否为负数
    public MutableLiveData<String> getMainNum(){//获取mainNum对象，如果mainNum为空，
                                                // 则创建一个新的MutableLiveData对象，并将初始值设置为"0"
        if(mainNum == null){
            mainNum = new MutableLiveData<>();
            mainNum.setValue("0");
        }
        return mainNum;
    }
    /*
    setMainNum方法，用于设置mainNum的值，如果有负号设置isNegative的值为"true"，
    则直接将n设置为mainNum的值；否则，在mainNum的值后面追加n
     */
    public void setMainNum(String n){
        if (n.equals("-")) {
            isNegative = true;
        } else {
            if (mainNum.getValue().equals("0")) {
                mainNum.setValue(n);
            } else {
                mainNum.setValue(mainNum.getValue() + n);
            }
        }
    }
    /*
    用于进行第一次运算，根据两个数的类型（整数或带小数点的数）进行相应的运算，并返回运算结果
     */
    public String mainNumWithNum_0_Tocal(){
        String value = "0";
        if(mainNum.getValue().contains(".") || num[0].contains(".")) {//如果两个数的其中一个数有小数点
            switch (sign1){
                case "+":
                    value = String.valueOf(Double.valueOf(num[0]) + Double.valueOf(mainNum.getValue()));
                    break;
                case "-":
                    value = String.valueOf(Double.valueOf(num[0]) - Double.valueOf(mainNum.getValue()));
                    break;
                case "*":
                    value = String.valueOf(Double.valueOf(num[0]) * Double.valueOf(mainNum.getValue()));
                    break;
                case "/":
                    if(mainNum.getValue().equals("0")){
                        mainNum.setValue("1");
                    }
                    value = String.valueOf(Double.valueOf(num[0]) / Double.valueOf(mainNum.getValue()));

            }
        }else{//如果两个数都是整数的话
            switch (sign1){
                case "+":
                    value = String.valueOf(Integer.valueOf(num[0]) + Integer.valueOf(mainNum.getValue()));
                    break;
                case "-":
                    value = String.valueOf(Integer.valueOf(num[0]) - Integer.valueOf(mainNum.getValue()));
                    break;
                case "*":
                    value = String.valueOf(Integer.valueOf(num[0]) * Integer.valueOf(mainNum.getValue()));
                    break;
                case "/":
                    if(mainNum.getValue().equals("0")){
                        mainNum.setValue("1");
                    }
                    value = String.valueOf(Double.valueOf(num[0]) / Double.valueOf(mainNum.getValue()));

            }
        }
        return value;
    }
    /*
    用于进行第二次运算，根据两个数的类型（整数或带小数点的数）进行相应的运算，并返回运算结果
     */
    public String mainNumWithNum_1_Tocal(){
        String value = "0";
        if(mainNum.getValue().contains(".") || num[1].contains(".")) {//如果两个数的其中一个数有小数点
            switch (sign2){
                case "*":
                    value = String.valueOf(Double.valueOf(num[1]) * Double.valueOf(mainNum.getValue()));
                    break;
                case "/":
                    if(mainNum.getValue().equals("0")){
                        mainNum.setValue("1");
                    }
                    value = String.valueOf(Double.valueOf(num[1]) / Double.valueOf(mainNum.getValue()));

            }
        }else{//如果两个数都是整数的话
            switch (sign2){
                case "*":
                    value = String.valueOf(Integer.valueOf(num[1]) * Integer.valueOf(mainNum.getValue()));
                    break;
                case "/":
                    if(mainNum.getValue().equals("0")){
                        mainNum.setValue("1");
                    }
                    value = String.valueOf(Double.valueOf(num[1]) / Double.valueOf(mainNum.getValue()));

            }
        }
        return value;
    }
    /*
    开平方运算
     */
    public String calculateSquareRoot() {
        double value = Double.valueOf(mainNum.getValue());
        double result = Math.sqrt(value);
        return String.valueOf(result);
    }
}
