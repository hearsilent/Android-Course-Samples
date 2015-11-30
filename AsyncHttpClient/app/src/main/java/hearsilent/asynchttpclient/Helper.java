package hearsilent.asynchttpclient;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Helper {

	private static final AsyncHttpClient mClient = init();

	private static AsyncHttpClient init() {
		AsyncHttpClient client = new AsyncHttpClient();
		client.addHeader("Connection", "Keep-Alive");
		client.setTimeout(7 * 1000);
		client.setEnableRedirects(true, true, true);
		return client;
	}

	public static final String NOTIFICATION_URL =
			"https://kuas.grd.idv.tw:14769/latest/notifications/%s";

	public static void getNotification(final Context context, int page,
	                                   final GeneralCallback callback) {
		String url = String.format(NOTIFICATION_URL, page);
		mClient.get(url, new JsonHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				super.onSuccess(statusCode, headers, response);
				try {
					ArrayList<NotifyModule> modules = new ArrayList<>();
					JSONArray jsonArray = response.getJSONArray("notification");
					for (int i = 0; i < jsonArray.length(); i++) {
						NotifyModule module = new NotifyModule();
						JSONObject notifyObject = jsonArray.getJSONObject(i).getJSONObject("info");
						module.title = notifyObject.optString("title");
						module.date = notifyObject.optString("date");
						module.dept = notifyObject.optString("department");
						module.link = jsonArray.getJSONObject(i).optString("link");
						modules.add(module);
					}
					if (callback != null) {
						callback.onSuccess(modules);
					}
				} catch (JSONException e) {
					if (callback != null) {
						callback.onFail(response.toString());
					}
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString,
			                      Throwable throwable) {
				super.onFailure(statusCode, headers, responseString, throwable);
				if (callback != null && responseString != null) {
					callback.onFail(responseString);
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable,
			                      JSONObject errorResponse) {
				super.onFailure(statusCode, headers, throwable, errorResponse);
				if (callback != null && errorResponse != null) {
					callback.onFail(errorResponse.toString());
				}
			}
		});
	}
}
