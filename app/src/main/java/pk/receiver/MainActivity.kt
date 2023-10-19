package pk.receiver

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val readProvider = findViewById<Button>(R.id.readProvider)

        readProvider.setOnClickListener {
            val cursor = contentResolver.query(
                Uri.parse("content://pk.lab1.provider/time"),
                null,
                null,
                null,
                null
            )

            try {
                if (cursor!!.moveToFirst()) {
                    while (!cursor.isAfterLast) {
                        Log.i(
                            "ContentProvider",
                            "Username: ${cursor.getString(cursor.getColumnIndex("username"))}, number: ${
                                cursor.getInt(cursor.getColumnIndex("number"))
                            }"
                        )
                        cursor.moveToNext()
                    }
                } else {
                    Log.d("ContentProvider", "No records found")
                }
            } catch (exception: NullPointerException) {
                Log.e("ContentProvider","Failed to connect with ContentProvider: pk.lab1.provider")
            }
        }
    }
}