package com.example.petbeer


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.netology.myrecipes.databinding.BeerCardFragmentBinding


class BeerCardFragment : Fragment() {
    private val args by navArgs<BeerCardFragmentArgs>()

    val viewModel by viewModels<BeerViewModel>(
        ownerProducer = ::requireParentFragment
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.navigateToEditScreenEvent.observe(this)
        { initialContent ->
            val direction =
                BeerCardFragmentDirections.actionBeerCardFragmentToBeerContentFragment(
                    initialContent.get(0), initialContent.get(1).toFloat(),
                    initialContent.get(2).toFloat(),initialContent.get(3).toFloat(),initialContent.get(4).toFloat()                )
            findNavController().navigate(direction)
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = BeerCardFragmentBinding.inflate(layoutInflater, container, false).also {
        val id = args.id
        //viewModel.currentBeer.value = viewModel.get(id).steps.toMutableList()
        val adapter = BeerAdapter(viewModel)
        it.oneBeer.list.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) { beer ->
            adapter.submitList(beer.filter { it.id == id })
        }

    }.root


}