
package com.example.petbeer

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ListAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.netology.myrecipes.R
import ru.netology.myrecipes.databinding.FragmentSellBinding

class SellFragment : Fragment() {
    //private val args by navArgs<SellFragmentArgs>()
    val viewModel by viewModels<BeerViewModel>(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // viewModel.onCheckClicked()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSellBinding.inflate(layoutInflater, container, false).also { binding ->

        viewModel.names.observe(viewLifecycleOwner){
            val adapter = ArrayAdapter(viewModel.getApplication(), R.layout.list_names, it)
            (binding.enterBeer.editText as? AutoCompleteTextView)?.setAdapter(adapter)
//            (binding.enterBeer2.editText as? AutoCompleteTextView)?.setAdapter(adapter)
//            (binding.enterBeer3.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        }

        val bottles = arrayOf(0.5,1,1.5)
        val bottlesAdapter = ArrayAdapter(viewModel.getApplication(), R.layout.list_bottles,bottles)
        (binding.enterBottle.editText as? AutoCompleteTextView)?.setAdapter(bottlesAdapter)
        (binding.enterBottle2.editText as? AutoCompleteTextView)?.setAdapter(bottlesAdapter)


        fun getCheck() {
            val beer = viewModel.beerToMe(binding.enterBeer.editText?.text.toString())
            if(binding.enterAmmount.editText?.text?.isNotBlank() == true ||
                binding.enterAmmount2.editText?.text?.isNotBlank() == true){
            val ammountFirst: Int = Integer.parseInt(binding.enterAmmount.editText?.text.toString())
            val ammountSecond: Int = Integer.parseInt(binding.enterAmmount2.editText.toString())

            val priceFirstBottle: Float =
                (when (binding.enterBottle.editText?.text.toString()) {
                    "0.5" -> beer.priceFor0_5
                    "1" -> beer.priceFor1
                    "1.5" -> beer.priceFor1_5
                    else -> 0F
                })

            val priceSecondBottle: Float =
                when (binding.enterBottle2.editText?.text.toString()) {
                    "0.5" -> beer.priceFor0_5
                    "1" -> beer.priceFor1
                    "1.5" -> beer.priceFor1_5
                    else -> 0F
                }

            val sum =
                ((priceFirstBottle * ammountFirst) + (priceSecondBottle * ammountSecond))
            binding.sum.editText?.text?.append(sum.toString())
        }
        binding.check.setOnClickListener{
            if(binding.enterBeer.editText?.text?.isNotBlank() == true) {

                getCheck()
            }
        }}

    }.root


}