package com.example.marvelcharacters.list

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.marvelcharacters.R
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

class CharacterAdapter(private val context: Context, private val dataSource: ArrayList<CharacterItem>): BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.view_item, parent, false)

        val characterImageView = rowView.findViewById(R.id.characterImage) as ImageView
        val characterNameView = rowView.findViewById(R.id.characterName) as TextView

        val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
        Glide.with(context)
            .load(getItem(position).characterImage)
            .transition(withCrossFade(factory))
            .placeholder(R.drawable.standard_large)
            .into(characterImageView)

        characterNameView.text = getItem(position).characterName

        return rowView
    }

    override fun getItem(position: Int): CharacterItem {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }
}