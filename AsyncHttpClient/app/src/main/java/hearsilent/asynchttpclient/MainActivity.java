package hearsilent.asynchttpclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Helper.getNotification(this, 1, new GeneralCallback(){
			@Override
			public void onSuccess(String data) {
				super.onSuccess(data);
				Log.d("HearSilent", data);
			}
		});
	}
}
