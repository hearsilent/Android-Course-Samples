package hearsilent.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class FirstFragment extends Fragment implements View.OnClickListener {
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	private String mParam1 = "";
	private String mParam2 = "";

	private OnFragmentFInteractionListener mListener;
	AppCompatButton button;
	AppCompatTextView textView1, textView2;

	public FirstFragment newInstance(String param1, String param2) {
		FirstFragment fragment = new FirstFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public FirstFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		View fl = inflater.inflate(R.layout.fragment_first, container, false);

		button = (AppCompatButton) fl.findViewById(R.id.button);
		button.setOnClickListener(this);

		textView1 = (AppCompatTextView) fl.findViewById(R.id.textView1);
		textView1.setText(mParam1);
		textView2 = (AppCompatTextView) fl.findViewById(R.id.textView2);
		textView2.setText(mParam2);
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
