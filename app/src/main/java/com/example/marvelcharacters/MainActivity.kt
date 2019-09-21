package com.example.marvelcharacters

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.marvelcharacters.list.CharacterAdapter
import com.example.marvelcharacters.list.CharacterItem
import com.example.marvelcharacters.net.NetHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NetHelper.instance.getCharacters()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(

                onNext = {
                    Log.d("MainActivity", it.toString())

                    val dataCharacterItem = ArrayList<CharacterItem>()

                    for (character in it.data.results) {
                        val imageUrl = "${character.thumbnail.path}/standard_large.${character.thumbnail.extension}".replace("http", "https")
                        val imageLargeUrl = "${character.thumbnail.path}/standard_fantastic.${character.thumbnail.extension}".replace("http", "https")

                        dataCharacterItem.add(CharacterItem(imageUrl, imageLargeUrl, character.name, character.description))
                    }

                    charactersListView.adapter = CharacterAdapter(this, dataCharacterItem)

                    charactersListView.setOnItemClickListener{parent, view, position, id ->
                        val listItem = dataCharacterItem[position]

                        val intent = Intent(this, CharacterActivity::class.java)
                        intent.putExtra("characterImageLarge", listItem.characterImageLarge)
                        intent.putExtra("characterName", listItem.characterName)
                        intent.putExtra("characterDesc", listItem.characterDesc)

                        startActivity(intent)
                    }
                },

                onError = {
                    Log.d("MainActivity", it.toString())
                }

            )
    }
}