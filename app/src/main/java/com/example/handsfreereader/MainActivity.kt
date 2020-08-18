package com.example.handsfreereader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.folioreader.Config
import com.folioreader.FolioReader
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions

class MainActivity : AppCompatActivity() {

    var array = arrayOf("E-book")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter(this, R.layout.listview_ebook_item, array)

        val ebookListView:ListView = findViewById(R.id.ebook_list_view)
        ebookListView.adapter = adapter

        ebookListView.onItemClickListener = object : AdapterView.OnItemClickListener {

            override fun onItemClick(p0: AdapterView<*>?, view: View, position: Int, id: Long) {

                // Real-time contour detection
                val realTimeOpts = FaceDetectorOptions.Builder()
                    .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
                    .build()

                val faceDetector = FaceDetection.getClient()

                val itemValue = ebookListView.getItemAtPosition(position)

                val config:Config = Config()
                    .setAllowedDirection(Config.AllowedDirection.ONLY_HORIZONTAL)
                    .setDirection(Config.Direction.HORIZONTAL)
                var folioReader:FolioReader = FolioReader.get()
                    .setConfig(config, true)

                folioReader.openBook("/mnt/sdcard/Download/adventures.epub")
            }
        }
    }
    inner class ImageProcessor : ImageAnalysis.Analyzer {
        private val TAG = javaClass.simpleName
        override fun analyze(imageProxy: ImageProxy) {
            val mediaImage = imageProxy.image
            }
        }
}