package course.labs.lab5_datalab

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.os.Build
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import org.w3c.dom.Text
import java.io.*

class External : AppCompatActivity() {

    companion object{
        val TAG = "External"
        val MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1
        val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1
    }
    protected lateinit var inputText: EditText
    protected lateinit var response: TextView
    protected lateinit var saveButton: Button
    protected lateinit var readButton: Button

    private val filename = "SampleFile.txt"
    private val filepath = "MyFileStorage"
    protected lateinit var myExternalFile: File
    internal var myData = ""
    private val isExternalStorageReadOnly: Boolean
        get() {
            val extStorageState = Environment.getExternalStorageState()
            return Environment.MEDIA_MOUNTED_READ_ONLY == extStorageState
        }

    private val isExternalStorageAvailable: Boolean
        get() {
            val extStorageState = Environment.getExternalStorageState()
            return Environment.MEDIA_MOUNTED == extStorageState
        }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_external)
        inputText = findViewById<EditText>(R.id.myInputText)
        response = findViewById<TextView>(R.id.response)
        saveButton = findViewById<Button>(R.id.saveExternalStorage)
        readButton = findViewById<Button>(R.id.getExternalStorage)
        myExternalFile = File(getExternalFilesDir(filepath),filename)
        saveButton.setOnClickListener{
            Log.i(TAG, "clicked save button")
            save(myExternalFile)
        }
        readButton.setOnClickListener{
            Log.i(TAG, "clicked read button")
            read(myExternalFile)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun save(file : File){
        if(ActivityCompat.checkSelfPermission(this,WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            try{
                val fileOutputStream = FileOutputStream(myExternalFile)
                fileOutputStream.write(inputText.text.toString().toByteArray())
                fileOutputStream.close()
            }catch(e: IOException){
                Log.e(TAG,"error writing to the file")
            }
            myData = inputText.text.toString()
            inputText.setText("")
            response.setText(filename + " saved to " + filepath + "...")
        }else{
            requestPermissions(arrayOf(WRITE_EXTERNAL_STORAGE),MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE)
        }
    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun read(file : File){
        if(ActivityCompat.checkSelfPermission(this,READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            var fileInputStream = FileInputStream(myExternalFile)
            var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder: StringBuilder = StringBuilder()
            var text: String? = null
            while ({ text = bufferedReader.readLine(); text }() != null) {
                stringBuilder.append(text)
            }
            fileInputStream.close()
            inputText.setText(stringBuilder.toString())
            response.setText(filename + " data retrieved from " + filepath +"...")
        }else{
            requestPermissions(arrayOf(READ_EXTERNAL_STORAGE),MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE)
        }
    }
}
