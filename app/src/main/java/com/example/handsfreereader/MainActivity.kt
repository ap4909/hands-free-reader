package com.example.handsfreereader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.folioreader.Config
import com.folioreader.FolioReader

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

                val itemValue = ebookListView.getItemAtPosition(position)

                val config:Config = Config()
                    .setAllowedDirection(Config.AllowedDirection.ONLY_HORIZONTAL)
                    .setDirection(Config.Direction.HORIZONTAL)
                var folioReader:FolioReader = FolioReader.get()
                    .setConfig(config, true)

                folioReader.openBook("")
            }
        }
    }
}