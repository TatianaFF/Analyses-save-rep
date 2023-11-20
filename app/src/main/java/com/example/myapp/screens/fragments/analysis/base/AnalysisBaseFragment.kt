package com.example.myapp.screens.fragments.analysis.base

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapp.R
import com.example.myapp.databinding.FragmentAnalysisBinding
import com.example.myapp.models.Analysis
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
abstract class AnalysisBaseFragment: Fragment() {
    private var _binding: FragmentAnalysisBinding? = null
    protected val binding
        get() = _binding!!

    private val viewModel: AnalysisBaseViewModel by viewModels()

    abstract val idOrgan: Long
    abstract val newAnalysis: Analysis

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnalysisBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.btnSave.setOnClickListener {
            if (validateAnalysis()) {
                saveAnalysis()
                findNavController().popBackStack()
            }
        }

        binding.btnChooseFile.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "application/pdf"
            pdfPickerLauncher.launch(intent)
        }

        binding.imgBtnDelFile.setOnClickListener {
            val file = File(binding.tvFile.text.toString())
            val result = file.delete()
            binding.tvFile.text = getString(R.string.text_path_file)
        }
    }

    private fun saveAnalysis() {
        viewModel.updateAnalysis(newAnalysis)
    }

    private fun validateAnalysis(): Boolean {
        var result = true
        if (binding.editNameAnalysis.text.isNullOrBlank() || binding.editNameAnalysis.text.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "Заполните название анализа", Toast.LENGTH_SHORT).show()
            result = false
        }

        return result
    }

    //разрешения
    private val pdfPickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                val file = saveFile(uri)
                binding.tvFile.text = file.path
            }
        }}

    private fun saveFile(uri: Uri): File {
        val inputStream = activity?.contentResolver?.openInputStream(uri)
        val nameFile = requireNotNull(getFileName(uri)) {"Ошибка при определении названия файла"}
        var file: File?
        inputStream.use { input ->
            val nameFolder = idOrgan
            val folder = File(activity?.getExternalFilesDir(null), nameFolder.toString()).apply { mkdir() }
            file = File(folder, nameFile)
            FileOutputStream(file).use { output ->
                val buffer = ByteArray(4 * 1024)
                var read: Int = -1
                while (input?.read(buffer).also { it?.let { read = it }} != -1) {
                    output.write(buffer, 0, read)
                }
                output.flush()
            }
        }
        return requireNotNull(file) {"Не удалось скопировать файл"}
    }

    private fun getFileName(uri: Uri): String? {
        var result: String? = null
        if (uri.scheme == "content") {
            requireActivity().contentResolver.query(uri, null, null, null, null).use { cursor ->
                if (cursor != null && cursor.moveToFirst()) {
                    val columnIndex: Int = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    if (columnIndex != -1) {
                        result = cursor.getString(columnIndex)
                    }
                }
            }
        }
        if (result == null) {
            result = uri.lastPathSegment
        }
        return result
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}