package com.example.bluetoothserialcommunication

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.bluetoothserialcommunication.ui.theme.BluetoothSerialCommunicationTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bluetoothManager: BluetoothManager = getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.getAdapter()
        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
        }
        if (bluetoothAdapter?.isEnabled == false) {
//            startActivityForReesult(enableBtIntent, REQUEST_ENABLE_BT)

            val requestPermissionLauncher =
                registerForActivityResult(
                    ActivityResultContracts.RequestPermission()
                ) { isGranted: Boolean ->
                    if (isGranted) {
                        // パーミションを許可した時
                        Log.d("takashii" , "true")
                    } else {
                        // パーミッションを許可しない時
                        Log.d("takashii" , "false")
                    }
                }

            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) == PackageManager.PERMISSION_GRANTED -> {
                    // CAMERA パーミッションが既に付与されている場合の処理
                    Log.d("takashii" , "PERMISSION_GRANTED")

                }
                ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.BLUETOOTH_CONNECT
                ) -> {
                    // ユーザーに説明が必要な場合の処理
                    Log.d("takashii" , "Manifest.permission.CAMERA")
                }
                else -> {
                    // CAMERA パーミッションをリクエストする
                    Log.d("takashii" , "else")
                    requestPermissionLauncher.launch(Manifest.permission.BLUETOOTH_CONNECT)
                }
            }
        }


        setContent {
            BluetoothSerialCommunicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BluetoothSerialCommunicationTheme {1
        Greeting("Android")
    }
}