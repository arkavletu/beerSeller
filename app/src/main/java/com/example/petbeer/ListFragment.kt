package com.example.petbeer


//import ru.netology.myrecipes.ItemTouchHelper.Callback
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.myrecipes.databinding.ListFragmentBinding

class ListFragment : Fragment() {
    val viewModel by viewModels<BeerViewModel>(
        ownerProducer = ::requireParentFragment
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModel.firstTest()

        setFragmentResultListener(BeerContentFragment.REQUEST_KEY)
        { requestKey, bundle ->
            if (requestKey != BeerContentFragment.REQUEST_KEY) return@setFragmentResultListener
            val newContent = bundle.getStringArray(
                BeerContentFragment.RESULT_KEY
            ) ?: return@setFragmentResultListener
            viewModel.contentArray = newContent
            viewModel.onSaveClicked(newContent)
        }

        viewModel.navigateToEditScreenEvent.observe(this)
        { initialContent ->
            val direction = ListFragmentDirections.actionListFragment2ToBeerContentFragment(
                initialContent.get(0),
                initialContent.get(1).toFloat(),
                initialContent.get(2).toFloat(),
                initialContent.get(3).toFloat(),
                initialContent.get(4).toFloat()
            )
            findNavController().navigate(direction)

        }

        viewModel.navigateToNewScreenEvent.observe(this) {
            val direction =
                ListFragmentDirections.actionListFragment2ToBeerContentFragment("", 0F, 0F, 0F, 0F)
            findNavController().navigate(direction)

        }

        viewModel.navigateToSellFragment.observe(this){
            val direction = ListFragmentDirections.actionListFragment2ToSellFragment()
            findNavController().navigate(direction)

        }
//
        viewModel.navigateToBeerCardFragment.observe(this)
        { id ->
            val direction = ListFragmentDirections.actionListFragment2ToBeerCardFragment(id)
            findNavController().navigate(direction)
        }

    }
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ) = ListFragmentBinding.inflate(layoutInflater, container, false).also {
            val adapter = BeerAdapter(viewModel)
//        var data = adapter.data
//        adapter.submitList(data)
            it.includedList.list.adapter = adapter
            viewModel.data.observe(viewLifecycleOwner) { beer ->
                adapter.submitList(beer)
            }

            it.fab.setOnClickListener {
                viewModel.onFabClicked()
            }
//        val searchView = it.includedList.search
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
//            android.widget.SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                if (query.isBlank()) return false
//                return true
//            }
//
//            override fun onQueryTextChange(newQuery: String): Boolean {
//                if (newQuery.isBlank()) {
//                    adapter.submitList(viewModel.data.value)
//                    return false
//                }
//                adapter.submitList(viewModel.data.value?.filter {
//                    it.name.contains(
//                        newQuery,
//                        true
//                    )
//                })
//                return false
//            }
//        })
//        it.includedList.filterIcon.setOnClickListener {
//            val singleItems = arrayOf("All") + Categories.array
//            var checkedItem = 0
//            MaterialAlertDialogBuilder(requireActivity())
//                .setTitle(resources.getString(R.string.category))
//                .setSingleChoiceItems(singleItems, checkedItem) { _, which ->
//                    checkedItem = which
//                }.setPositiveButton(resources.getString(R.string.save_me)) { _, _ ->
//                    if (checkedItem == 0) adapter.submitList(viewModel.data.value)
//                    else
//                        viewModel.getFiltered(singleItems[checkedItem]).observe(viewLifecycleOwner){ filtered ->
//                            adapter.submitList(filtered)
//                        }
//                }.show()
//        }
//        val callback: ItemTouchHelper.Callback = Callback(adapter)
//        val touchHelper = ItemTouchHelper(callback)
//        touchHelper.attachToRecyclerView(it.includedList.list)
        }.root

    }


