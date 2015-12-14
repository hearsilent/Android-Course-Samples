package hearsilent.drawer;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity
		implements DemoFragment.OnFragmentFInteractionListener {

	NavigationView mNavigationView;

	DrawerLayout mDrawerLayout;

	FrameLayout frameLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

		frameLayout = (FrameLayout) findViewById(R.id.view);

		mNavigationView = (NavigationView) findViewById(R.id.navigationView);
		mNavigationView.setNavigationItemSelectedListener(
				new NavigationView.OnNavigationItemSelectedListener() {
					@Override
					public boolean onNavigationItemSelected(MenuItem item) {
						switch (item.getItemId()) {
							case R.id.nav_about:
								break;
							case R.id.nav_bus:
								break;
							case R.id.nav_course:
								break;
							case R.id.nav_leave:
								break;
							case R.id.nav_messages:
								break;
							case R.id.nav_score:
								break;
							case R.id.nav_settings:
								break;
							case R.id.nav_simcourse:
								break;
						}

						mDrawerLayout.closeDrawer(mNavigationView);
						return true;
					}
				});

		FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
		fragTrans.replace(R.id.view, new DemoFragment().newInstance("123"));
		fragTrans.commit();
	}

	@Override
	public void onFragmentFInteraction(View view) {

	}
}
