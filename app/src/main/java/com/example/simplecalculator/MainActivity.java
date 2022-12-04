package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.simplecalculator.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding activityMainBinding;
    private String pheptinh = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        //Toast.makeText(this, ""+button.getText(), Toast.LENGTH_SHORT).show();
        activityMainBinding.xoa.setEnabled(true);
        if(pheptinh.length()==0&&button.getText().equals("Xóa")){
            activityMainBinding.xoa.setEnabled(false);
            return;
        }
        if(button.getText().equals("Xóa")){
           pheptinh = pheptinh.substring(0,pheptinh.length()-1);
           activityMainBinding.tvPheptinh.setText(pheptinh);
           return;
        }
        if(button.getText().equals("=")){
            Expression expression = new Expression(pheptinh);
            String result = String.valueOf(expression.calculate());
            if (result.endsWith(".0")){
                result = result.replace(".0","");
                activityMainBinding.etKetqua.setText(result);
                return;
            }
            if(result.equals("NaN")){
                Snackbar snackbar = Snackbar.make(view,"Không thể thực hiện phép tính",Snackbar.LENGTH_SHORT);
                snackbar.show();
                return;
            }
            activityMainBinding.etKetqua.setText(result);
            return;
        }
        if(button.getText().equals("C")){
            pheptinh = "";
            activityMainBinding.etKetqua.setText("");
            activityMainBinding.tvPheptinh.setText(pheptinh);
            return;
        }else{
            pheptinh = pheptinh + button.getText().toString();
        }
        activityMainBinding.tvPheptinh.setText(pheptinh);

    }
}