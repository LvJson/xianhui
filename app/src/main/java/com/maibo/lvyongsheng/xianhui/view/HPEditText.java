package com.maibo.lvyongsheng.xianhui.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by LYS on 2016/11/24.
 */

public class HPEditText extends EditText {
    private boolean isRun = false;
    private String d = "";
    public HPEditText(Context context) {
        super(context);
        setBankCardTypeOn();
    }

    public HPEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBankCardTypeOn();
    }

    public HPEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBankCardTypeOn();
    }


    private void setBankCardTypeOn() {
        HPEditText.this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(isRun){
                    isRun=false;
                    return;
                }
                isRun = true;
                d = "";
                String newStr = s.toString();
                newStr = newStr.replace(",", "");
                int index = 0;
                while ((index + 3) < newStr.length()){
                    d += (newStr.substring(index, index + 3) + ",");
                    index += 3;
                }
                d += (newStr.substring(index, newStr.length()));
                int i = getSelectionStart();
                HPEditText.this.setText(d);
                try {
                    if (i % 4 == 0 && before == 0) {
                        if (i + 1 <= d.length()) {
                            HPEditText.this.setSelection(i + 1);
                        } else {
                            HPEditText.this.setSelection(d.length());
                        }
                    } else if (before == 1 && i < d.length()) {
                        HPEditText.this.setSelection(i);
                    } else if (before == 0 && i < d.length()) {
                        HPEditText.this.setSelection(i);
                    } else
                        HPEditText.this.setSelection(d.length());
                }catch (Exception e){

                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    //对外提供暴漏的方法
    private void insertText(EditText editText, String mText) {
        editText.getText().insert(getSelectionStart(), mText);

    }
}
