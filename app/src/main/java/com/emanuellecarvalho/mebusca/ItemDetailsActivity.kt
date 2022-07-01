package com.emanuellecarvalho.mebusca

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.emanuellecarvalho.mebusca.databinding.ActivityItemDetailsBinding

class ItemDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}