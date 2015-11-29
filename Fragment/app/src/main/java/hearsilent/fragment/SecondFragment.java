package hearsilent.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentSInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment implements View.OnClickListener {
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	private String mParam1 = "";
	private String mParam2 = "";

	AppCompatImageView imageView;
	AppCompatTextView textView1, textView2;

	private OnFragmentSInteractionListener mListener;

	public SecondFragment newInstance(String param1, String param2) {
		SecondFragment fragment = new SecondFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public SecondFragment() {
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
		View fl = inflater.inflate(R.layout.fragment_second, container, false);

		imageView = (AppCompatImageView) fl.findViewById(R.id.imageView);
		imageView.setOnClickListener(this);

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
			mListener.onFragmentSInteraction(view);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFragmentSInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(
					activity.toString() + " must implement OnFragmentSInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	public interface OnFragmentSInteractionListener {
		void onFragmentSInteraction(View view);
	}
}
