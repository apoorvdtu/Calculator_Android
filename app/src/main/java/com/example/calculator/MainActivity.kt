package com.example.calculator


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var lastDot = false;
    var lastNumeric = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onDigit(view: View){
//        tvInput.append("1");
        tvInput.append((view as Button).text);
        lastNumeric = true;

    }
    fun onClear(view: View){
       tvInput.text = "";
        lastDot = false;
        lastNumeric = false;
    }
    fun onDecimalPoint(view : View){
        if(lastNumeric&&!lastDot){
            lastDot = true;
            lastNumeric = false;
            tvInput.append(".")
        }
    }
    fun onOperator(view:View){

        if(tvInput.text.length==0){
            val ch = (view as Button).text;
            if(ch.equals("-")){

                tvInput.append("-")
            }
        }
        else if(tvInput.text.length==1&&tvInput.text.get(tvInput.text.length - 1)=='-'){
            val ch = (view as Button).text.get(0);
//            if(ch.equals("+")||ch.equals())
            if(ch>='0'&&ch<='9'){
                tvInput.append(ch+"");
            }
        }
        else{

            var ch = tvInput.text.get(tvInput.text.length - 1);
            if(ch>='0'&&ch<='9'){
                tvInput.append((view as Button).text)
            }
            else{
                tvInput.text = tvInput.text.substring(0, tvInput.text.length - 1);
                tvInput.append((view as Button).text)


            }
        }
        lastNumeric = true;
        lastDot = false;

    }
    fun onEqual(view:View){
        if(tvInput.text.length==0){

        }
        else if(tvInput.text.length==1&&tvInput.text.get(0)=='-')
        {

        }

        else if(tvInput.text.contains("-")||tvInput.text.contains("+")||tvInput.text.contains("/")||tvInput.text.contains("*")){
            val last = tvInput.text.get(tvInput.text.length-1)
            if(last=='-'||last=='+'||last=='*'||last=='/'){
                tvInput.text = tvInput.text.substring(0,tvInput.text.length - 1);
            }
            var arr = ArrayList<String>();
            var str = tvInput.text.toString();
            var temp = "";
        Toast.makeText(this,"$str",Toast.LENGTH_LONG);
            for(i in 0 until str.length){
                var ch = str.get(i);
                if(ch=='+'||ch=='-'||ch=='/'||ch=='*'){
                    if(temp.length!=0){
                        arr.add(temp);
                        temp = "";
                    }
                    arr.add(ch+"");
                }
                else{
                    temp += ch;
                }
            }
            if(temp.isNotEmpty())
                arr.add(temp)


            var i = 0;
            var sum = 0.0;
            while(i<arr.size){
                var ch = arr.get(i);
                if(ch.equals("-")){
                    sum = sum - arr.get(i+1).toDouble();
                    i+=2;
                }
                else if(ch.equals("+")){
                    sum = sum + arr.get(i+1).toDouble();
                    i+=2;
                }
                else if(ch.equals("*")){
                    sum = sum * arr.get(i+1).toDouble();
                    i+=2;
                }
                else if(ch.equals("/")){
                    sum = sum / arr.get(i+1).toDouble();
                    i+=2;
                }
                else {
                    sum += ch.toDouble();
                    i++;
                }
            }
            tvInput.text = removeZeroAfterDot((sum).toString());
            if(tvInput.text.contains(".")) {
                lastDot = true
                lastNumeric = false;
            }
            else{
                lastDot = false;
                lastNumeric = true;
            }

        }

    }
   private fun removeZeroAfterDot(result:String):String{
        var value = result;
        if(result.contains(".0")){
            value = result.substring(0,result.length - 2);
        }
        return value
    }
}