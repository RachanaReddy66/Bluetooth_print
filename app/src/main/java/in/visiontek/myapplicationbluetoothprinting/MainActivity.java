package in.visiontek.myapplicationbluetoothprinting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;




import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    // Bluetooth variables
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice bluetoothDevice;
    private BluetoothSocket bluetoothSocket;
    private static final String PRINTER_MAC_ADDRESS = "99:87:65:43:21:00"; // Replace with your printer's MAC address

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Bluetooth adapter
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // Check if Bluetooth is supported on the device
        if (bluetoothAdapter == null) {
            // Device does not support Bluetooth
            showToast("Bluetooth is not supported on this device");
            return;
        }

        // Check if Bluetooth is enabled, if not, request to enable it
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 0);
        }

        // Get the Bluetooth device
        bluetoothDevice = bluetoothAdapter.getRemoteDevice(PRINTER_MAC_ADDRESS);

        // Connect to the Bluetooth device
        try {
            bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            bluetoothSocket.connect();

            // Printing functionality here using the connected bluetoothSocket
            OutputStream outputStream = bluetoothSocket.getOutputStream();

            // Add your printing functionality here using the outputStream
            // For example:
            outputStream.write("Your print data Your print data Your print data Your print data Your print data Your print data Your print data Your print data Your print data Your print data Your print data Your print data Your print data".getBytes());

            // Close the Bluetooth socket
            bluetoothSocket.close();

            // Display a toast to indicate that printing is complete
            showToast("Printing completed successfully");
        } catch (IOException e) {
            e.printStackTrace();
            showToast("Bluetooth connection failed: " + e.getMessage());
        }
    }

    // Helper method to show a toast message
    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
