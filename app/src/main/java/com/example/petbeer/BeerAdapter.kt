package com.example.petbeer




import android.annotation.SuppressLint
import android.net.Uri.parse
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.myrecipes.R
import ru.netology.myrecipes.databinding.BeerBinding
import java.util.*
import kotlin.collections.ArrayList


class BeerAdapter(

    private val actionListener: BeerActionListener
) : ListAdapter<Beer, BeerAdapter.ViewHolder>(DiffSearcher) {
    var data = ArrayList<Beer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BeerBinding.inflate(inflater, parent, false)

        return ViewHolder(binding, actionListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position))
        data.add(getItem(position))
        holder.itemView.setOnClickListener {
            actionListener.onBeerClicked(getItem(position).id)
        }
    }


    inner class ViewHolder(
        private val binding: BeerBinding,
        listener: BeerActionListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var beer: Beer

        private val popupMenu by lazy {
            PopupMenu(itemView.context, binding.options).apply {
                inflate(R.menu.options)
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.remove -> {
                            listener.onDeleteClicked(beer)
                            true
                        }
                        R.id.edit -> {

                            listener.onEditClicked(beer)
                            true
                        }
                        R.id.sell ->{
                            listener.onSellClicked(beer)
                            true
                        }
//
                        else -> false
                    }

                }
            }
        }
//
//        init {
//            binding.favor.setOnClickListener {
//                listener.onLikeClicked(recipe)
//            }
//
//        }

        @SuppressLint("SetTextI18n")
        fun bind(beer: Beer) {
            this.beer = beer
            with(binding)
            {
                ammount.text = "Остаток - ${beer.ammount}"
                name.text = beer.name
                price05.text = "0,5 - ${beer.priceFor0_5}   "
                price1.text = "1 - ${beer.priceFor1}   "
                price15.text = "1,5 - ${(beer.priceFor1*1.5)+0.5}   "

                options.setOnClickListener { popupMenu.show() }
//                favor.isChecked = recipe.isFavorite
//                if(recipe.imageUrl.isBlank()) {
//                    image.setImageResource(R.drawable.no_image)
//                } else image.setImageURI(parse(recipe.imageUrl))


            }
        }
    }

    private object DiffSearcher : DiffUtil.ItemCallback<Beer>() {
        override fun areItemsTheSame(oldItem: Beer, newItem: Beer) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Beer, newItem: Beer) = oldItem == newItem

    }

//    override fun onMove(fromPosition: Int, toPosition: Int) {
//        if (fromPosition < toPosition) {
//            for (i in fromPosition until toPosition) {
//                Collections.swap(data, i, i + 1)
//            }
//        } else {
//            for (i in fromPosition downTo toPosition + 1) {
//                Collections.swap(data, i, i - 1)
//            }
//        }
//        notifyItemMoved(fromPosition, toPosition)
//
//    }
//
//    override fun onItemDismiss(position: Int) {
//        data.removeAt(position)
//        notifyItemRemoved(position)
//    }




}