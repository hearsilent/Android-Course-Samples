package hearsilent.asynchttpclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
		implements CustomListView.OnBottomReachedListener {

	CustomListView listView;
	CustomAdapter customAdapter;

	ArrayList<String> arrayList = new ArrayList<>();
	boolean isUpdate = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Helper.getNotification(this, 1, new GeneralCallback() {
			@Override
			public void onSuccess(String data) {
				super.onSuccess(data);
				Log.d("HearSilent", data);
			}
		});

		listView = (CustomListView) findViewById(R.id.listView);
		for (int i = 0; i < 10; i++) {
			arrayList.add(Integer.toString(i));
		}
		customAdapter = new CustomAdapter();
		listView.setAdapter(customAdapter);

		listView.setOnBottomReachedListener(this);
	}

	@Override
	public void onBottomReached() {
		if (!isUpdate) {
			isUpdate = true;
			int count = customAdapter.getCount();
			for (int i = count; i < count + 10; i++) {
				arrayList.add(Integer.toString(i));
			}
			listView.postDelayed(new Runnable() {
				@Override
				public void run() {
					customAdapter.notifyDataSetChanged();
					isUpdate = false;
				}
			}, 800);
		}
	}

	private class CustomAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return arrayList.size();
		}

		@Override
		public Object getItem(int position) {
			return arrayList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;

			if (convertView == null) {
				LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
				convertView = inflater.inflate(R.layout.list_custom, parent, false);

				holder = new ViewHolder();
				holder.textView_title = (TextView) convertView.findViewById(R.id.textView_title);
				holder.textView_from = (TextView) convertView.findViewById(R.id.textView_from);
				holder.textView_date = (TextView) convertView.findViewById(R.id.textView_date);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.textView_title.setText(arrayList.get(position));
			holder.textView_from.setText("Test " + arrayList.get(position));
			holder.textView_date.setText("Test2 " + arrayList.get(position));

			return convertView;
		}

		class ViewHolder {
			TextView textView_title;
			TextView textView_from;
			TextView textView_date;
		}
	}
}
