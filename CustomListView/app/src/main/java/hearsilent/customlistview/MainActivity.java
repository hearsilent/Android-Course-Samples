package hearsilent.customlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	ArrayList<String> arrayList = new ArrayList<>();
	ListView listView;

	CustomAdapter customAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.listView);
		for (int i = 0; i < 100; i++) {
			arrayList.add(Integer.toString(i));
		}
		/** Normal **/
		//listView.setAdapter(
		//		new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList));
		/** Custom **/
		customAdapter = new CustomAdapter();
		listView.setAdapter(customAdapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(MainActivity.this, arrayList.get(position), Toast.LENGTH_SHORT)
						.show();

				arrayList.remove(position);
				customAdapter.notifyDataSetChanged();
			}
		});
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
			holder.textView_date.setText("");

			return convertView;
		}

		class ViewHolder {
			TextView textView_title;
			TextView textView_from;
			TextView textView_date;
		}
	}
}
