package com.example.myapp.screens.fragments.analysis.create

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.example.myapp.models.Analysis
import com.example.myapp.screens.fragments.analysis.base.AnalysisBaseFragment
import kotlin.random.Random


class AnalysisCreateFragment : AnalysisBaseFragment() {
    private val viewModel: AnalysisCreateViewModel by viewModels()

    override val newAnalysis: Analysis
        get() = Analysis(
            id = Random.nextLong(),
            name = binding.editNameAnalysis.text.toString(),
            comment = binding.editComment.text.toString(),
            filePath = binding.tvFile.text.toString(),
            organ = idOrgan
        )

    override val idOrgan: Long
        get() = requireNotNull(arguments?.getLong(KEY_ID_ORGAN)) {"Не удалось получить идентификатор органа"}

    companion object {
        private const val KEY_ID_ORGAN = "KEY_ID_ORGAN"

        fun getBundleCreateAnalysis(idOrgan: Long) = bundleOf(KEY_ID_ORGAN to idOrgan)
    }
}