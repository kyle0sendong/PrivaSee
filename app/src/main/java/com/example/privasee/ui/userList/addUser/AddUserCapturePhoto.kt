package com.example.privasee.ui.userList.addUser

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.privasee.R
import com.example.privasee.databinding.ActivityAddUserCapturePhotoBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class AddUserCapturePhoto : AppCompatActivity() {

    private lateinit var binding: ActivityAddUserCapturePhotoBinding
    private var imageCapture: ImageCapture? = null
    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService


    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityAddUserCapturePhotoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()

        binding.btnCapture.isEnabled = false
        if (allPermissionGranted()) {
            startCamera()
            binding.btnCapture.isEnabled = true
        } else
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSION)

        binding.btnCapture.setOnClickListener {
            takePhoto()
        }

    }


    private fun getOutputDirectory(): File {

        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }

        return if (mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }


    private fun takePhoto() {

        val imageCapture = imageCapture ?: return

        val photoFile = File(
            outputDirectory,
            SimpleDateFormat(
                FILE_NAME_FORMAT,
                Locale.getDefault()).format(System.currentTimeMillis())+".jpg")

        val outputOption = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputOption, ContextCompat.getMainExecutor(this),
            object :ImageCapture.OnImageSavedCallback{

                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    val msg = "Photo Saved"

                    Toast.makeText(
                        baseContext,
                        "$msg $savedUri",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e(
                        TAG,
                        "onError: ${exception.message}",
                        exception)
                }
            }
        )
    }


    // Start Front Camera Preview
    private fun startCamera() {

        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build()
                .also { mPreview ->
                    mPreview.setSurfaceProvider(
                        binding.captureView.surfaceProvider
                    )
                }

            imageCapture = ImageCapture.Builder().build()
            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )
            } catch (e:Exception) {
                Log.d(TAG, "startCamera Fail:", e)
            }
        }, ContextCompat.getMainExecutor(this))
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if ((REQUEST_CODE_PERMISSION == 111) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            startCamera()
            binding.btnCapture.isEnabled = true
        }
    }


    private fun allPermissionGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }


    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }


    companion object {
        const val TAG = "cameraX"
        const val FILE_NAME_FORMAT = "yy-MM-dd-HH-mm-ss-SSS"
        const val REQUEST_CODE_PERMISSION = 111
        val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }
}