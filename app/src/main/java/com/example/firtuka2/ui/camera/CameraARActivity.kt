package com.example.firtuka2.ui.camera

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firtuka2.databinding.ActivityCameraBinding
import com.example.firtuka2.ui.decoration.Button
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.view.OrientationEventListener
import android.view.Surface
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import com.example.firtuka2.data.helper.getImageUri
import com.example.firtuka2.data.helper.reduceFileImage
import com.example.firtuka2.data.helper.requestPermissionLauncher
import com.example.firtuka2.data.helper.uriToFile
import com.example.firtuka2.data.retrofit.ApiConfig
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

//import com.example.firtuka2.ui.camera.OpenCameraActivity.Companion.CAMERAX_RESULT

class CameraARActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding
    private lateinit var cameraViewModel: CameraViewModel
    private var currentImageUri: Uri? = null
    private lateinit var customButton: Button
    private var imageCapture: ImageCapture? = null
    private var edittext : EditText? = null


    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "Permission request granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Permission request denied", Toast.LENGTH_LONG).show()
            }
        }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            this,
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var apiRepo = ApiConfig.getApiService()

        requestPermissionLauncher(this, REQUIRED_PERMISSION)
        cameraViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[CameraViewModel::class.java]

        edittext = binding.editGender
        customButton = binding.btnUpload

        cameraViewModel.story.observe(this){ result ->
            if (result.status.equals("")){
                Toast.makeText(this@CameraARActivity, result.message, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@CameraARActivity, result.message, Toast.LENGTH_SHORT).show()
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
        binding.cameraXButton.setOnClickListener {
            startCameraX()
        }
        binding.btnUpload.setOnClickListener {
            uploadImage()
        }
    }

    private fun uploadImage() {

        if (currentImageUri != null && binding.editGender.text.isNotEmpty()){
            currentImageUri?.let { uri ->

                val imageFile = uriToFile(uri, this)
                Log.d("ImageFile", "showImage: ${imageFile.path}")
                val getFile = imageFile
                val file = reduceFileImage(getFile as File)
                val requestImageFile = file.asRequestBody("image/jpeg".toMediaType())
                val imageMultipart: MultipartBody.Part =
                    MultipartBody.Part.createFormData("files", file.name, requestImageFile)
                val gender =  RequestBody.create("text/plain".toMediaType(), "")
//                val gender =  binding.editGender.text.toString()
                cameraViewModel.getGlassesByPhoto(imageMultipart, gender)
            }
        } else {
            Toast.makeText(this@CameraARActivity, "Please select an image and fill in the description", Toast.LENGTH_SHORT).show()
        }

    }



    private fun startCameraX() {
        currentImageUri = getImageUri(this)
//        val intent = Intent(this, OpenCameraActivity::class.java)
        launcherIntentCameraX.launch(currentImageUri)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) {isSuccess ->
        if (
            isSuccess
//            it.resultCode == CAMERAX_RESULT
            ) {
//            currentImageUri = it.data?.getStringExtra(OpenCameraActivity.EXTRA_CAMERAX_IMAGE)?.toUri()
            showImage()
        }
    }
    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.imgPreview.setImageURI(it)
        }
    }

    private val orientationEventListener by lazy {
        object : OrientationEventListener(this) {
            override fun onOrientationChanged(orientation: Int) {
                if (orientation == ORIENTATION_UNKNOWN) {
                    return
                }
                val rotation = when (orientation) {
                    in 45 until 135 -> Surface.ROTATION_270
                    in 135 until 225 -> Surface.ROTATION_180
                    in 225 until 315 -> Surface.ROTATION_90
                    else -> Surface.ROTATION_0
                }
                imageCapture?.targetRotation = rotation
            }
        }
    }
    override fun onStart() {
        super.onStart()
        orientationEventListener.enable()
    }
    override fun onStop() {
        super.onStop()
        orientationEventListener.disable()
    }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }
}