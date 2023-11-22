package com.example.myapp.screens.fragments.list_analyses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapp.R
import com.example.myapp.adapters.AdapterListAnalyses
import com.example.myapp.databinding.FragmentListAnalysesBinding
import com.example.myapp.models.Analysis
import com.example.myapp.screens.activity.MainActivity
import com.example.myapp.screens.fragments.analysis.create.AnalysisCreateFragment
import com.example.myapp.screens.fragments.analysis.create.AnalysisCreateViewModel
import com.example.myapp.screens.fragments.analysis.edit.AnalysisEditViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListAnalysesFragment : Fragment() {
    private var _binding: FragmentListAnalysesBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: ListAnalysesViewModel by viewModels()

    private var analyses = emptyList<Analysis>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListAnalysesBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity). setTitleToolbar(viewModel.nameOrgan)

        val adapter = AdapterListAnalyses(requireContext())
        binding.rvOrganSystems.adapter = adapter
        viewModel.analyses.observe(viewLifecycleOwner) { _analyses ->
            analyses = _analyses
            adapter.submitList(analyses.map { it.name })
            Toast.makeText(requireContext(), "${analyses.size}", Toast.LENGTH_SHORT).show()
        }

        adapter.onItemClick = { position ->
            findNavController().navigate(
                R.id.analysisEditFragment,
                AnalysisEditViewModel.getBundle(analyses[position].id)
            )
        }
        adapter.onDelClick = { position ->
            viewModel.deleteAnalysis(analyses[position].id)
        }

        binding.btnFloatCreateAnalisis.setOnClickListener {
            findNavController().navigate(
                R.id.analysisCreateFragment,
                AnalysisCreateFragment.getBundleCreateAnalysis(viewModel.idOrgan)
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}