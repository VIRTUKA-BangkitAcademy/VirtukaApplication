package com.example.firtuka2.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firtuka2.R
import com.example.firtuka2.data.Glasses
import com.example.firtuka2.data.ListGlassesAdapter
import com.example.firtuka2.data.repo.GlassesDatabase
import com.example.firtuka2.data.repo.GlassesRepository
import com.example.firtuka2.data.response.FramesItem
import com.example.firtuka2.data.retrofit.ApiConfig
import com.example.firtuka2.data.retrofit.ApiService
import com.example.firtuka2.databinding.FragmentHomeBinding
import com.example.firtuka2.ui.ViewModelFactory
import com.example.firtuka2.ui.deskription.DescriptionActivity

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var glassesAdapter: ListGlassesAdapter
    private lateinit var listGlassesViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding = null
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        activity?.title = getString(R.string.app_name)

        recyclerView = binding.rvGlasses
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        glassesAdapter = ListGlassesAdapter()

        recyclerView.adapter = glassesAdapter

        val glassesRepository = GlassesRepository(ApiConfig.getApiService())
        listGlassesViewModel =
            ViewModelProvider(this, ViewModelFactory(glassesRepository))[HomeViewModel::class.java]

        listGlassesViewModel =
            ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[HomeViewModel::class.java]

        listGlassesViewModel.getAll()
        listGlassesViewModel.glassesPaging.observe(viewLifecycleOwner) { glasses ->
            glassesAdapter.submitData(lifecycle, glasses)
        }
    }

//    private fun setupAction() {
//
//        binding.btn.setOnClickListener {
//            startActivity(Intent(requireContext(), DescriptionActivity::class.java))
//        }
//
//    }
//
//
//    private fun getListGlasses(): ArrayList<Glasses> {
//        val dataName = resources.getStringArray(R.array.data_name)
//        val dataType = resources.getStringArray(R.array.data_type)
//        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
//        val listHero = ArrayList<Glasses>()
//        for (i in dataName.indices) {
//            val hero = Glasses(dataName[i], dataType[i], dataPhoto.getResourceId(i, -1))
//            listHero.add(hero)
//        }
//        return listHero
//    }
//
//    private fun getData(){
//        listGlassesViewModel.glassesPaging.observe(viewLifecycleOwner){
//            if (it != null){
//                setStory(it)
//                Log.d("IS_ERROR", it.toString())
//            }
//            Log.e("TesGetData", setStory(it).toString())
//        }
//    }
//
//    private fun setStory(story: PagingData<FramesItem>){
//
//        glassesAdapter.submitData(lifecycle, story)
//
//        glassesAdapter.setOnItemClickCallback(object : ListGlassesAdapter.OnItemClickCallback{
//            override fun onItemClicked(data: FramesItem) {
//
//            }
//        })
//    }
//
//    private fun showRecyclerList() {
//        rvGlasses.layoutManager = GridLayoutManager(requireContext(), 2)
//        val listGlassesAdapter = ListGlassesAdapter()
//        rvGlasses.adapter = listGlassesAdapter
//    }
//
//
//
//    override fun onResume() {
//        super.onResume()
//        listGlassesViewModel.getAll()
//    }
//    private val getResult =
//        registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult()) {
//            if(it.resultCode == Activity.RESULT_OK){
////                getData()
//                glassesAdapter.refresh()
//                glassesAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
//                    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
//                        if (positionStart == 0) {
//                            binding.rvGlasses.scrollToPosition(0)
//                        }
//                    }
//                })
//            }
//        }
}