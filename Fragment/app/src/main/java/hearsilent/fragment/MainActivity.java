package hearsilent.fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
		implements FirstFragment.OnFragmentFInteractionListener,
		SecondFragment.OnFragmentSInteractionListener, View.OnClickListener {

	FragmentManager fragmentManager;
	FragmentTransaction fragmentTransaction;
	private TextView textView;
	Button button;
	private int whichFragment = 1;
	private FirstFragment fragment1;
	private SecondFragment fragment2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textView = (TextView) findViewById(R.id.textView);
		button = (Button) findViewById(R.id.button_change_fragment);
		//fragment1 = new FirstFragment();
		fragment1 = new FirstFragment().newInstance("test 1", "test 2");
		//fragment2 = new SecondFragment();
		fragment2 = new SecondFragment().newInstance("test 1", "test 2");

		button.setOnClickListener(this);

		FirstFragment fragment = new FirstFragment();
		fragmentManager = getFragmentManager();
		fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.fragment_second, fragment);
		fragmentTransaction.commit();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	@Override
	public void onClick(View v) {
		FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
		switch (whichFragment) {
			case 1:
				fragTrans.replace(R.id.fragment_first, fragment1, "frag_first");
				whichFragment = 2;
				break;
			case 2:
				fragTrans.replace(R.id.fragment_first, fragment2, "frag_second");
				whichFragment = 1;
				break;
		}
		fragTrans.commit();
	}

	@Override
	public void onFragmentFInteraction(View view) {
		textView.setText("first");
	}

	public void onFragmentSInteraction(View view) {
		textView.setText("second");
	}
}
