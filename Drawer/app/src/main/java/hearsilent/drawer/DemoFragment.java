package hearsilent.drawer;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DemoFragment extends Fragment implements View.OnClickListener {
	private static final String ARG_PARAM1 = "param1";

	private String mParam1 = "";

	private OnFragmentFInteractionListener mListener;
	TextView textView;

	public DemoFragment newInstance(String param1) {
		DemoFragment fragment = new DemoFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		fragment.setArguments(args);
		return fragment;
	}

	public DemoFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		View fl = inflater.inflate(R.layout.fragment_demo, container, false);

		textView = (TextView) fl.findViewById(R.id.textView);
		textView.setText(mParam1);
		// Inflate the layout for this fragment
		return fl;
	}

	public void onClick(View view) {
		Toast.makeText(this.getActivity(), "Button is clicked!", Toast.LENGTH_LONG).show();
		if (mListener != null) {
			mListener.onFragmentFInteraction(view);
		}
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		try {
			mListener = (OnFragmentFInteractionListener) context;
		} catch (ClassCastException e) {
			throw new ClassCastException(
					context.toString() + " must implement OnFragmentFInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	public interface OnFragmentFInteractionListener {
		void onFragmentFInteraction(View view);
	}
}
