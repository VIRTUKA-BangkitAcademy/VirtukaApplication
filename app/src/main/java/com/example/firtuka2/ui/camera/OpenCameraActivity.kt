package com.example.firtuka2.ui.camera
//
//import android.annotation.SuppressLint
//import android.content.ContentValues.TAG
//import android.content.Intent
//import android.os.Build
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.firtuka2.databinding.ActivityOpenCameraBinding
import java.io.File

//import android.os.Bundle
//import android.util.Log
//import android.view.WindowInsets
//import android.view.WindowManager
//import android.widget.Toast
//import androidx.activity.viewModels
//import androidx.camera.core.CameraSelector
//import androidx.camera.core.ImageAnalysis
//import androidx.camera.core.ImageCapture
//import androidx.camera.core.ImageCaptureException
//import androidx.camera.core.ImageProxy
//import androidx.camera.core.Preview
//import androidx.camera.lifecycle.ProcessCameraProvider
//import androidx.core.content.ContextCompat
//import com.example.firtuka2.R
//import com.example.firtuka2.data.helper.createCustomTempFile
//import com.example.firtuka2.databinding.ActivityCameraBinding
//import com.example.firtuka2.databinding.ActivityOpenCameraBinding
//import com.google.mlkit.vision.common.InputImage
//import com.google.mlkit.vision.face.FaceDetection
//import com.google.mlkit.vision.face.FaceDetector
//import com.google.mlkit.vision.face.FaceDetectorOptions
//import java.util.concurrent.Executors
//
//class OpenCameraActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityOpenCameraBinding
//    private var getFile: File? = null
//    private val uploadViewModel by viewModels<UploadImageViewModel> {
//        ViewModelFactory.getInstance(application)
//    }
//    companion object {
//        const val CAMERA_X_RESULT = 200
//    }
//
//
//}
//
//    private lateinit var binding: ActivityOpenCameraBinding
//    private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
//    private var imageCapture: ImageCapture? = null
//    private lateinit var processCameraProvider: ProcessCameraProvider
//    private lateinit var cameraPreview: Preview
//    private lateinit var imageAnalysis: ImageAnalysis
//
//    private val cameraViewModel = viewModels<CameraViewModel>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityOpenCameraBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.switchCamera.setOnClickListener {
//            startCamera()
//        }
//        binding.captureImage.setOnClickListener { takePhoto() }
//
//        binding.switchCamera.setOnClickListener {
//            cameraSelector = if (cameraSelector.equals(CameraSelector.DEFAULT_BACK_CAMERA)) CameraSelector.DEFAULT_FRONT_CAMERA
//            else CameraSelector.DEFAULT_BACK_CAMERA
//
//            startCamera()
//        }
//    }
//
//    public override fun onResume() {
//        super.onResume()
//        hideSystemUI()
//        startCamera()
//    }
//
//    private fun startCamera() {
//        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
//
//        cameraProviderFuture.addListener({
//            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
//            val preview = Preview.Builder()
//                .build()
//                .also {
//                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
//                }
//            imageCapture = ImageCapture.Builder().build()
//            try {
//                cameraProvider.unbindAll()
//                cameraProvider.bindToLifecycle(
//                    this,
//                    cameraSelector,
//                    preview,
//                    imageCapture
//                )
//            } catch (exc: Exception) {
//                Toast.makeText(
//                    this@OpenCameraActivity,
//                    "Gagal memunculkan kamera.",
//                    Toast.LENGTH_SHORT
//                ).show()
//                Log.e(TAG, "startCamera: ${exc.message}")
//            }
//        }, ContextCompat.getMainExecutor(this))
//    }
//
//    private fun takePhoto()  {
//        val imageCapture = imageCapture ?: return
//        val photoFile = createCustomTempFile(application)
//        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
//
//        imageCapture.takePicture(
//            outputOptions,
//            ContextCompat.getMainExecutor(this),
//            object : ImageCapture.OnImageSavedCallback {
//                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
//                    val intent = Intent()
//                    intent.putExtra(EXTRA_CAMERAX_IMAGE, output.savedUri.toString())
//                    setResult(CAMERAX_RESULT, intent)
//                    finish()
//                    Toast.makeText(
//                        this@OpenCameraActivity,
//                        "Berhasil mengambil gambar.",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//                override fun onError(exc: ImageCaptureException) {
//                    Toast.makeText(
//                        this@OpenCameraActivity,
//                        "Gagal mengambil gambar.",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    Log.e(TAG, "onError: ${exc.message}")
//                }
//            }
//        )
//    }
//
//    private fun bindInputAnalyser() {
//        val detector = FaceDetection.getClient(
//            FaceDetectorOptions.Builder()
//                .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
//                .setContourMode(FaceDetectorOptions.CONTOUR_MODE_NONE)
//                .build()
//        )
//        imageAnalysis = ImageAnalysis.Builder()
//            .setTargetRotation(binding.viewFinder.display.rotation)
//            .build()
//
//        val cameraExecutor = Executors.newSingleThreadExecutor()
//
//        imageAnalysis.setAnalyzer(cameraExecutor) { imageProxy ->
//            processImageProxy(detector, imageProxy)
//        }
//
//        try {
//            processCameraProvider.bindToLifecycle(this, cameraSelector, imageAnalysis)
//        } catch (illegalStateException: IllegalStateException) {
//            Log.e(TAG, illegalStateException.message ?: "IllegalStateException")
//        } catch (illegalArgumentException: IllegalArgumentException) {
//            Log.e(TAG, illegalArgumentException.message ?: "IllegalArgumentException")
//        }
//    }
//
//    @SuppressLint("UnsafeOptInUsageError")
//    private fun processImageProxy(detector: FaceDetector, imageProxy: ImageProxy) {
//        val inputImage =
//            InputImage.fromMediaImage(imageProxy.image!!, imageProxy.imageInfo.rotationDegrees)
//        detector.process(inputImage).addOnSuccessListener { faces ->
//            binding.graphicOverlay.clear()
//            faces.forEach { face ->
//                val faceBox = FaceBox(binding.graphicOverlay, face, imageProxy.image!!.cropRect)
//                binding.graphicOverlay.add(faceBox)
//            }
//        }.addOnFailureListener {
//            it.printStackTrace()
//        }.addOnCompleteListener {
//            imageProxy.close()
//        }
//    }
//
//    private fun hideSystemUI() {
//        @Suppress("DEPRECATION")
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            window.insetsController?.hide(WindowInsets.Type.statusBars())
//        } else {
//            window.setFlags(
//                WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN
//            )
//        }
//        supportActionBar?.hide()
//    }
//
//    companion object {
//        private const val TAG = "CameraActivity"
//        const val EXTRA_CAMERAX_IMAGE = "CameraX Image"
//        const val CAMERAX_RESULT = 200
//    }
//}