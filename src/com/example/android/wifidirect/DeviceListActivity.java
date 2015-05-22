
package com.example.android.wifidirect;

import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;




//import com.example.android.BluetoothChat.R;




//import net.londatiga.android.bluetooth.DeviceListAdapter;
//import net.londatiga.android.bluetooth.R;
//import net.londatiga.android.bluetooth.R;
//import net.londatiga.android.bluetooth.DeviceListAdapter.OnPairButtonClickListener;
//import net.londatiga.android.bluetooth.DeviceListAdapter.ViewHolder;
import android.app.Activity;
//import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
////import android.content.BroadcastReceiver;
//import android.content.Context;
import android.content.Intent;
//import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
//import android.view.LayoutInflater;
import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.ListAdapter;
import android.widget.ListView;
//import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
//import android.widget.Toast;

public class DeviceListActivity extends Activity {
	// Debugging
	private static final String TAG = "DeviceListActivity";
	//private static final boolean D = true;

	// Return Intent extra
	public static String EXTRA_DEVICE_ADDRESS = "device_address";

	// Member fields
	//private BluetoothAdapter mBtAdapter;
	private ArrayAdapter<String> mPairedDevicesArrayAdapter;
	//private ArrayAdapter<String> mNewDevicesArrayAdapter;
	private ArrayList<BluetoothDevice> mDeviceList;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bt_list);
		mDeviceList		= getIntent().getExtras().getParcelableArrayList("list");
		ListView mListView = (ListView) findViewById(R.id.lv_paired);
		
		//DeviceListAdapter mAdapter = new DeviceListAdapter();
		mPairedDevicesArrayAdapter = new ArrayAdapter<String>(this,
				R.layout.device_name);		//mAdapter.setData(mDeviceList);
		for(BluetoothDevice m : mDeviceList) {
			mPairedDevicesArrayAdapter.add(m.getName());
			Log.d(TAG, m.getName()+" "+m);
		}
		mListView.setAdapter(mPairedDevicesArrayAdapter);
		mListView.setOnItemClickListener(mDeviceClickListener);

	}
	private OnItemClickListener mDeviceClickListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
			// Cancel discovery because it's costly and we're about to connect
			//mBtAdapter.cancelDiscovery();

			// Get the device MAC address, which is the last 17 chars in the
			// View
			//String info = ((TextView) v).getText().toString();
			//String address = info.substring(info.length() - 17);
			BluetoothDevice m = mDeviceList.get(arg2);

			// Create the result Intent and include the MAC address
			Intent intent = new Intent();
			intent.putExtra(EXTRA_DEVICE_ADDRESS, m.getAddress());
			// Set result and finish this Activity
			setResult(Activity.RESULT_OK, intent);
			finish();
		}
	};
}