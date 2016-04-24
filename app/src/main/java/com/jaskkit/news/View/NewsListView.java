package com.jaskkit.news.View;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaskkit.news.R;
import com.jaskkit.news.View.state.BundleSavedState;


/**
 * Created by jaskkit on 4/20/16.
 */
public class NewsListView extends FrameLayout {
    public TextView title;
    public ImageView newImg;
    public NewsListView(Context context) {
        super(context);
        initInflate();
        initInStances();
    }

    public NewsListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInStances();
    }

    public NewsListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInStances();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NewsListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInStances();
    }

    private void initInflate() {
        inflate(getContext(), R.layout.newlists, this);
    }

    private void initInStances() {
        title = (TextView) findViewById(R.id.title);
        newImg = (ImageView) findViewById(R.id.NewImg);

    }
    

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        // Save Children's state as a Bundle
        Bundle childrenStates = new Bundle();
        for (int i = 0; i < getChildCount(); i++) {
            int id = getChildAt(i).getId();
            if (id != 0) {
                SparseArray childrenState = new SparseArray();
                getChildAt(i).saveHierarchyState(childrenState);
                childrenStates.putSparseParcelableArray(String.valueOf(id), childrenState);
            }
        }

        // Save it to Parcelable
        BundleSavedState ss = new BundleSavedState(superState);
        ss.getBundle().putBundle("childrenStates", childrenStates);
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        // Restore SparseArray
        Bundle childrenStates = ss.getBundle().getBundle("childrenStates");
        // Restore Children's state
        for (int i = 0; i < getChildCount(); i++) {
            int id = getChildAt(i).getId();
            if (id != 0) {
                if (childrenStates.containsKey(String.valueOf(id))) {
                    SparseArray childrenState =
                            childrenStates.getSparseParcelableArray(String.valueOf(id));
                    getChildAt(i).restoreHierarchyState(childrenState);
                }
            }
        }
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width * 4/5;
        int newheightMeasureSpec = MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY);
        //หลอก ลูก
        super.onMeasure(widthMeasureSpec, newheightMeasureSpec);
        //ตัวเอง
        setMeasuredDimension(widthMeasureSpec,newheightMeasureSpec);
    }
    public void setTitle(String title){
        this.title.setText(title);
    }
    public ImageView getImg() {
        return newImg;
    }

}
