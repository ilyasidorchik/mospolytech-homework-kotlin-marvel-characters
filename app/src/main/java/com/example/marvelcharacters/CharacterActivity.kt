package com.example.marvelcharacters

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import kotlinx.android.synthetic.main.activity_character.*

class CharacterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        setTitle(intent.getStringExtra("characterName"))

        val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
        Glide.with(this)
            .load(intent.getStringExtra("characterImageLarge"))
            .transition(DrawableTransitionOptions.withCrossFade(factory))
            .placeholder(R.drawable.standard_fantastic)
            .into(characterImage)

        characterName.text = intent.getStringExtra("characterName")
        characterDesc.text = intent.getStringExtra("characterDesc")
    }

}