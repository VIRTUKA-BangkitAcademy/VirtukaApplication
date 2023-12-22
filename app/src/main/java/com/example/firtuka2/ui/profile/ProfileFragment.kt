package com.example.firtuka2.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.Recomposer
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import com.example.firtuka2.R
import com.example.firtuka2.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

        private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.composeView.apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setContent {
                // In Compose world
                ProfileScreen()
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



//    private var _binding: FragmentProfileBinding? = null
//
//    // This property is only valid between onCreateView and
//    // onDestroyView.
//    private val binding get() = _binding!!
//
//    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
//    ): View {
//        val notificationsViewModel =
//            ViewModelProvider(this).get(ProfileViewModel::class.java)
//
////        _binding = FragmentProfileBinding.inflate(inflater, container, false)
////        val root: View = binding.root
//
//        return root
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.apply {
//            btnToUpdate.setOnClickListener { toUpdate() }
//            btnToexit.setOnClickListener { logout() }
//        }
//    }
//    private fun toUpdate() {
//    }
//
//    private fun logout() {
//        val builder = AlertDialog.Builder(requireContext())
//        builder.setMessage("Yakin ingin keluar?")
//            .setPositiveButton("Ya") { _, _ ->
//                val intent = Intent(this@ProfileFragment.requireContext(), LoginActivity::class.java)
//                startActivity(intent)
//                requireActivity().finish()
//            }
//            .setNegativeButton("Tidak") {dialog, _->
//                dialog.dismiss()
//            }
//            .show()
//    }


@Composable
 fun ProfileScreen(
) {
    val image = painterResource(id = R.drawable.avatar_icon_girl)
    val username = "Anna"
    val age = "20"
    val height = "157"
    val gender = "Female"


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .semantics { contentDescription = "about_page" }
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Box(
                modifier = Modifier
                    .size(144.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = image,
                    contentDescription = "about_image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            androidx.compose.material.Text(
                text = username,
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = Modifier.width(4.dp))
                androidx.compose.material.Text(
                    text = age,
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = gender,
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }



            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = Modifier.width(4.dp))
                androidx.compose.material.Text(
                    text = height,
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
        }
    }
}
