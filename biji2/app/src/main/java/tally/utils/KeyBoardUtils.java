package tally.utils;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.example.biji.R;

//自定义键盘

public class KeyBoardUtils {
    private final Keyboard k1;
    private KeyboardView keyboardView;
    private EditText editText;

    public interface OnEnsureListener{
        public void onEnsure();
    }
    OnEnsureListener onEnsureListener;

    public void setOnEnsureListener(OnEnsureListener onEnsureListener) {
        this.onEnsureListener = onEnsureListener;
    }

    public KeyBoardUtils(KeyboardView keyboardView, EditText editText) {
        this.keyboardView = keyboardView;
        this.editText = editText;
        this.editText.setInputType(InputType.TYPE_NULL);  //取消弹出系统自带的键盘
        k1 = new Keyboard(this.editText.getContext(), R.xml.key);//获取对象

        this.keyboardView.setKeyboard(k1);  //设置显示样式
        this.keyboardView.setEnabled(true);
        this.keyboardView.setPreviewEnabled(false);
        this.keyboardView.setOnKeyboardActionListener(listener);  //传入监听
    }

    KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int primaryCode) {
        }
        @Override
        public void onRelease(int primaryCode) {
        }
        @Override//查找判断，点击了什么键
        public void onKey(int primaryCode, int[] keyCodes) {
            Editable editable = editText.getText();
            int start = editText.getSelectionStart();
            switch (primaryCode) {//ASKII码
                case Keyboard.KEYCODE_DELETE:   //点击了删除
                    if (editable!=null &&editable.length()>0) {
                        if (start>0) {
                            editable.delete(start-1,start);
                        }
                    }
                    break;
                case Keyboard.KEYCODE_CANCEL:   //点击了清零
                    editable.clear();
                    break;
                case Keyboard.KEYCODE_DONE:    //点击了完成
                    onEnsureListener.onEnsure();   //通过接口回调的方法，当点击确定时，可以调用这个方法
                    break;
                default:  //数字键
                    editable.insert(start,Character.toString((char)primaryCode));
                    break;
            }
        }
        @Override
        public void onText(CharSequence text) {
        }
        @Override
        public void swipeLeft() {
        }
        @Override
        public void swipeRight() {
        }
        @Override
        public void swipeDown() {
        }
        @Override
        public void swipeUp() {
        }
    };

    //    显示键盘
    public void showKeyboard(){
        int visibility = keyboardView.getVisibility();
        //如果状态没显示>>显示
        if (visibility == View.INVISIBLE ||visibility==View.GONE) {
            keyboardView.setVisibility(View.VISIBLE);
        }
    }

    //    隐藏键盘（这个没有用到...）
    public void hideKeyboard(){
        int visibility = keyboardView.getVisibility();
        if (visibility== View.VISIBLE||visibility==View.INVISIBLE) {
            keyboardView.setVisibility(View.GONE);
        }
    }
}