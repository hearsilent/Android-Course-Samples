package hearsilent.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DemoFragment extends Fragment {

	private int mPosition;

	TextView mTextView;

	public DemoFragment newInstance(int position) {
		DemoFragment demoFragment = new DemoFragment();

		Bundle bundle = new Bundle();
		bundle.putInt("position", position);
		demoFragment.setArguments(bundle);

		return demoFragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		restoreArgs();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		View view = inflater.inflate(R.layout.fragment_demo, container, false);

		mTextView = (TextView) view.findViewById(R.id.textView);
		mTextView.setText(Integer.toString(mPosition));

		return view;
	}

	private void restoreArgs() {
		Bundle args = getArguments();
		mPosition = args.getInt("position");
	}
}