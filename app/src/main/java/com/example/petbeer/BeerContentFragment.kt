package com.example.petbeer

import ru.netology.myrecipes.R


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.netology.myrecipes.databinding.BeerContentFragmentBinding


class BeerContentFragment : Fragment() {

    private val args by navArgs<BeerContentFragmentArgs>()
    val viewModel by viewModels<BeerViewModel>(
        ownerProducer = ::requireParentFragment
    )
    var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        viewModel.addStepEvent.observe(this) {
//            val direction =
//                RecipeContentFragmentDirections.actionRecipeContentFragmentToAddStepFragment()
////                    viewModel.contentArray[0],
////                    viewModel.contentArray[1],viewModel.contentArray[2])
//            findNavController().navigate(direction)
//        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = BeerContentFragmentBinding.inflate(layoutInflater, container, false).also { binding ->
        //val items = Categories.values()
//        val adapter = ArrayAdapter(viewModel.getApplication(), R.layout.list_category, items)
//        (binding.enterCategory.editText as? AutoCompleteTextView)?.setAdapter(adapter)

//        val stepAdapter = StepsAdapter(viewModel)
//        binding.listOfSteps.adapter = stepAdapter

        fun saveResult(){
            val text = binding.enterName.editText?.text.toString()
            val text2 = binding.enterAmmount.editText?.text.toString()
            val text3 = binding.enterPrice05.editText?.text.toString()
            val text4 = binding.enterPrice1.editText?.text.toString()
            val text5 = binding.enterPrice15.editText?.text.toString()
            //val ids = arrayOf(viewModel.currentRecipe.value?.steps?.map { it.id })
            // && !stepAdapter.data.isEmpty()
            if (!text.isBlank() && !text2.isBlank() ) {
                val resultBundle = Bundle(1)
                resultBundle.putStringArray(
                    RESULT_KEY,
                    arrayOf(text, text2, text3, text4,text5)
                )
                setFragmentResult(REQUEST_KEY, resultBundle)
            }
        }

//        viewModel.currentSteps.observe(viewLifecycleOwner){steps ->
//            stepAdapter.submitList(steps)
//        }



        binding.enterName.editText?.setText(args.name)
        binding.enterAmmount.editText?.setText(args.ammount.toString())
        binding.enterPrice05.editText?.setText(args.price05.toString())
        binding.enterPrice1.editText?.setText(args.price1.toString())
        binding.enterPrice15.editText?.setText(args.price15.toString())

//        val addingMainImageLauncher = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
//            uri ?: return@registerForActivityResult
//            requireActivity().contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
//
//            binding.imagePreview.setImageURI(uri)
//            this.uri = uri
//        }
//        viewModel.ImageEvent.observe(viewLifecycleOwner) {
//            addingMainImageLauncher.launch(arrayOf("image/*"))
//        }
//        binding.pick.setOnClickListener {
//            viewModel.onImageClicked()
//        }
        binding.ok.setOnClickListener {
            saveResult()
            binding.enterAmmount.editText?.text?.clear()
            binding.enterName.editText?.text?.clear()
            binding.enterPrice05.editText?.text?.clear()
            binding.enterPrice1.editText?.text?.clear()
            binding.enterPrice15.editText?.text?.clear()

            findNavController().popBackStack()
        }

//        binding.addStep.setOnClickListener {
//            saveResult()
//            viewModel.addStep()
//        }
//
//        val callback: ItemTouchHelper.Callback = Callback(stepAdapter)
//        val touchHelper = ItemTouchHelper(callback)
//        touchHelper.attachToRecyclerView(binding.listOfSteps)



    }.root


    companion object {
        const val REQUEST_KEY = "recipeAuthorRequestKey"
        const val RESULT_KEY = "recipeNewAuthor"
    }
}