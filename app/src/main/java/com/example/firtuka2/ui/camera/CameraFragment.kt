package com.example.firtuka2.ui.camera

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import coil.compose.rememberImagePainter
import com.example.firtuka2.BuildConfig
import com.example.firtuka2.R
import com.example.firtuka2.data.helper.requestPermissionLauncher
import com.example.firtuka2.data.helper.uriToFile
import com.example.firtuka2.databinding.FragmentCameraBinding
import com.example.firtuka2.databinding.FragmentProfileBinding
import com.example.firtuka2.ui.decoration.Button
import com.example.firtuka2.ui.decoration.navy
import com.example.firtuka2.ui.decoration.orange
import com.example.firtuka2.ui.profile.ProfileScreen
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class CameraFragment : Fragment() {

    private var _binding: FragmentCameraBinding? = null
    private lateinit var cameraViewModel: CameraViewModel
    private var currentImageUri: Uri? = null
    private lateinit var customButton: Button
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val cameraViewModel =
            ViewModelProvider(this).get(CameraViewModel::class.java)

//        requestPermissionLauncher(this, REQUIRED_PERMISSION)

        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.femaleBtn.setOnClickListener{
            navigateToNextActivity("female")
        }
        binding.maleBtn.setOnClickListener {
            navigateToNextActivity("male")
        }
    }

    private fun navigateToNextActivity(gender: String) {
        val intent = Intent (this@CameraFragment.context, CameraARActivity::class.java)
        intent.putExtra("selectedGender", gender)
        startActivity(intent)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

//@Composable
//fun CameraScreen(
//) {
//    val context = LocalContext.current
//    val file = context.createImageFile()
//    val uri = FileProvider.getUriForFile(
//        context,
//        BuildConfig.APPLICATION_ID + ".provider", file
//    )
//
//    var capturedImageUri by remember {
//        mutableStateOf<Uri>(Uri.EMPTY)
//    }
//
//    val cameraLauncher =
//        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
//            capturedImageUri = uri
//        }
//
//    val permissionLauncher = rememberLauncherForActivityResult(
//        ActivityResultContracts.RequestPermission()
//    ) {
//        if (it) {
//            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
//            cameraLauncher.launch(uri)
//        } else {
//            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    Column(
//        Modifier
//            .fillMaxSize()
//            .padding(10.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Button(
//            colors = ButtonDefaults.buttonColors(orange),
//            onClick = {
//                val permissionCheckResult =
//                    ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
//                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
//                    cameraLauncher.launch(uri)
//                } else {
//                    // Request a permission
//                    permissionLauncher.launch(Manifest.permission.CAMERA)
//                }
//            }) {
//            Text(text = "Capture Image From Camera")
//        }
//
//
////        if (capturedImageUri.path?.isNotEmpty() == true) {
////            val myFile = uriToFile(capturedImageUri, LocalContext.current)
////            val getFile = myFile
////            Image(
////                modifier = Modifier
////                    .padding(16.dp, 8.dp),
////                painter = rememberImagePainter(capturedImageUri),
////                contentDescription = null
////            )
////            Button(
////                colors = ButtonDefaults.buttonColors(navy),
////                modifier = Modifier.padding(top = 16.dp),
////                onClick = { navigateToList(capturedImageUri.toString()) },
////                content = { Text("Cari Frame") }
////            )
////        }
//    }
//}
//
//fun Context.createImageFile(): File {
//    // Create an image file name
//    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
//    val imageFileName = "JPEG_" + timeStamp + "_"
//    val image = File.createTempFile(
//        imageFileName, /* prefix */
//        ".jpg", /* suffix */
//        externalCacheDir      /* directory */
//    )
//    return image
//}

