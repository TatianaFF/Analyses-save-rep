package com.example.myapp.screens.fragments.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapp.R
import com.example.myapp.adapters.AdapterExp
import com.example.myapp.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private var adapter: AdapterExp? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    @SuppressLint("CommitTransaction")
    override fun onResume() {
        super.onResume()

        viewModel.listOrganSystemOrgans.observe(viewLifecycleOwner) { listPair ->
            val titleList = arrayListOf<String>()
            val dataList = hashMapOf<String, List<String>>()
            listPair.forEach { pair ->
                titleList.add(pair.first)
                dataList[pair.first] = pair.second.map { it.name }
            }

            adapter = AdapterExp(requireContext(), titleList, dataList)
            binding.listViewExp.setAdapter(adapter)

            binding.listViewExp.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
                val bundle = bundleOf(KEY_ID_NAME_ORGAN to Pair(listPair[groupPosition].second[childPosition].id, listPair[groupPosition].second[childPosition].name))
                findNavController().navigate(R.id.listAnalysesFragment, bundle)
//                replaceMainFragmentAnalysisFragment(dataList[(titleList)[groupPosition]]!![childPosition], (titleList)[groupPosition])
//                Toast.makeText(
//                    requireContext(),
//                    "Clicked: " + listPair[groupPosition].second[childPosition],
//                    Toast.LENGTH_SHORT
//                ).show()
                false
            }
        }
    }

    private fun replaceMainFragmentAnalysisFragment(nameOrgan: String, nameOrganSystem: String) {
//        val analysisFragment = AnalysisFragment.newInstance(nameOrgan, nameOrganSystem)
//        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.nav_fragment, analysisFragment)?.addToBackStack(null)?.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val KEY_ID_NAME_ORGAN = "ID_NAME_ORGAN"
    }
}