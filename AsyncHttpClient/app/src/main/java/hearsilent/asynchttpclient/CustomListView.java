package hearsilent.asynchttpclient;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

public class CustomListView extends ListView {

	OnBottomReachedListener mBottomReachedListener;

	public CustomListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		View view = getChildAt(getChildCount() - 1);
		if (view == null) {
			return;
		}
		int diff = (view.getBottom() - (getHeight() + getScrollY()));

		OnBottomReachedListener listener = getOnBottomReachedListener();
		if (diff == 0 && listener != null) {
			listener.onBottomReached();
		}
		super.onScrollChanged(l, t, oldl, oldt);
	}

	public OnBottomReachedListener getOnBottomReachedListener() {
		return mBottomReachedListener;
	}

	public void setOnBottomReachedListener(OnBottomReachedListener onBottomReachedListener) {
		mBottomReachedListener = onBottomReachedListener;
	}

	public interface OnBottomReachedListener {
		void onBottomReached();
	}
}
