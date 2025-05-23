package com.union_test.toutiao.activity;


import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.union_test.toutiao.R;
import com.union_test.toutiao.utils.TempFileUtils;

public class IpPortToolActivity extends Activity {
    private Button mPreserveBtn;
    private Button mClearBtn;
    private EditText mIpEditText;
    private EditText mPortEditText;
    private TempFileUtils mTempFileUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ip_port_tool);
        findViewById(R.id.btn_aip_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mClearBtn = findViewById(R.id.btn_clear_ip);
        mPreserveBtn = findViewById(R.id.btn_preserve);
        mIpEditText = findViewById(R.id.test_ip_edit);
        mPortEditText = findViewById(R.id.test_port_edit);
        mPreserveBtn.setOnClickListener(onClickListener);
        mClearBtn.setOnClickListener(onClickListener);
        mTempFileUtils = new TempFileUtils();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()== R.id.btn_clear_ip){
                //删除现有的本地文件
                mTempFileUtils.deleteFile("testIp.txt");
                Toast.makeText(IpPortToolActivity.this, "Clear Success！", Toast.LENGTH_LONG).show();
            }
            if(v.getId()== R.id.btn_preserve){
                String ipAdress = String.valueOf(mIpEditText.getText());
                String port = String.valueOf(mPortEditText.getText());
                if (TextUtils.isEmpty(ipAdress)) {
                    Toast.makeText(IpPortToolActivity.this, "Ip can't be null ！", Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(port)) {
                    Toast.makeText(IpPortToolActivity.this, "Port can't be null ！", Toast.LENGTH_LONG).show();
                }else{
                    //保存到本地
                    String content = "http://"+ipAdress+":"+port;
                    mTempFileUtils.writeToFile("testIp.txt",content);
                    Toast.makeText(IpPortToolActivity.this, "Preserve Success！", Toast.LENGTH_LONG).show();
                }
            }
        }
    };
}
