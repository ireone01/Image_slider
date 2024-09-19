package com.example.image_slider

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.image_slider.databinding.ActivityMainBinding
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val STORAGE_PERMISSION_CODE = 100
    private val PICK_IMAGE_REQUEST_CODE = 101
    val imageList = mutableListOf<Any>()
    private lateinit var adapter: CarouselAdapter
    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                addImageToCarouse(it)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        imageList.add(R.drawable.goku_001)
        imageList.add(R.drawable.goku_002)
        imageList.add(R.drawable.goku_003)
        imageList.add(R.drawable.goku_004)
        imageList.add(R.drawable.goku_005)
        imageList.add(R.drawable.goku_006)
        imageList.add(R.drawable.goku_007)

         adapter = CarouselAdapter(imageList)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = CarouselLayoutManager()
        CarouselSnapHelper().attachToRecyclerView(binding.recyclerView)

        binding.recyclerView.adapter = adapter

        val initialPosition = adapter.itemCount / 2 - (adapter.itemCount / 2) % imageList.size
        binding.recyclerView.scrollToPosition(initialPosition)
        binding.recyclerView.setItemViewCacheSize(5)

        binding.btnSelectImg.setOnClickListener {
            if (checkPermission()) {
                openGallery()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            } else {
                Toast.makeText(this, "No accept permission", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            data?.data?.let { uri ->
                addImageToCarouse(uri)
            }
        }
    }

    private fun checkPermission(): Boolean {
        val permission = Manifest.permission.READ_MEDIA_IMAGES

        return if (ContextCompat.checkSelfPermission(
                this,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            true
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(permission), STORAGE_PERMISSION_CODE)
            false
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE)
    }

    private fun addImageToCarouse(uri: Uri) {
        imageList.add(uri)
        adapter.notifyItemInserted(imageList.size - 1)
    }


}

