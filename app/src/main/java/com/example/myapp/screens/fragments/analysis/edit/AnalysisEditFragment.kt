package com.example.myapp.screens.fragments.analysis.edit

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.myapp.models.Analysis
import com.example.myapp.screens.fragments.analysis.base.AnalysisBaseFragment

//@AndroidEntryPoint
class AnalysisEditFragment: AnalysisBaseFragment() {
    private val viewModel: AnalysisEditViewModel by viewModels()

    private lateinit var analysis: Analysis

    override val idOrgan: Long
        get() = analysis.organ

    override val newAnalysis: Analysis
        get() = analysis.copy().apply {
            name = binding.editNameAnalysis.text.toString()
            comment = binding.editComment.text.toString()
            filePath = binding.tvFile.text.toString()
        }

    override fun onResume() {
        super.onResume()

        viewModel.analysis.observe(viewLifecycleOwner) {
            analysis = it

            binding.editNameAnalysis.setText(analysis.name)
            binding.editComment.setText(analysis.comment)
            binding.tvFile.text = analysis.filePath
        }
    }
}