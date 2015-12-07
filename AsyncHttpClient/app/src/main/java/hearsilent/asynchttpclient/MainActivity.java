package hearsilent.asynchttpclient;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
		implements CustomListView.OnBottomReachedListener, SwipeRefreshLayout.OnRefreshListener {

	CustomListView listView;
	CustomAdapter customAdapter;

	SwipeRefreshLayout swipeRefreshLayout;

	ArrayList<NotifyModule> arrayList = new ArrayList<>();
	boolean isUpdate = false;

	int page = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
		swipeRefreshLayout.setColorSchemeResources(new int[]{R.color.red_500, R.color.blue_500});
		//		swipeRefreshLayout
		//				.setColorSchemeColors(new int[]{ContextCompat.getColor(this, R.color.red_500)});

		listView = (CustomListView) findViewById(R.id.listView);
		listView.setOnBottomReachedListener(this);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Uri uri = Uri.parse(arrayList.get(position).link);
				Intent i = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(i);
			}
		});

		swipeRefreshLayout.post(new Runnable() {
			@Override
			public void run() {
				swipeRefreshLayout.setRefreshing(true);
			}
		});
		swipeRefreshLayout.setOnRefreshListener(this);

		getData();
	}

	@Override
	public void onRefresh() {
		if (!isUpdate) {
			page = 1;
			swipeRefreshLayout.postDelayed(new Runnable() {
				@Override
				public void run() {
					getData();
				}
			}, 2500);
		}
	}

	private void getData() {
		Helper.getNotification(this, page, new GeneralCallback() {
			@Override
			public void onFail(String errorMessage) {
				super.onFail(errorMessage);

				swipeRefreshLayout.setRefreshing(false);
				Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onSuccess(ArrayList<NotifyModule> modules) {
				super.onSuccess(modules);

				if (arrayList.size() == 0 || page == 1) {
					arrayList = modules;
					customAdapter = new CustomAdapter();
					listView.setAdapter(customAdapter);
				} else {
					arrayList.addAll(modules);
					customAdapter.notifyDataSetChanged();
				}
				page++;
				isUpdate = false;
				swipeRefreshLayout.setRefreshing(false);
			}
		});
	}

	@Override
	public void onBottomReached() {
		if (!isUpdate && !swipeRefreshLayout.isRefreshing()) {
			isUpdate = true;
			getData();
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

			holder.textView_title.setText(arrayList.get(position).title);
			holder.textView_from.setText(arrayList.get(position).dept);
			holder.textView_date.setText(arrayList.get(position).date);

			return convertView;
		}

		class ViewHolder {
			TextView textView_title;
			TextView textView_from;
			TextView textView_date;
		}
	}
}
