package com.doanchung.shoes_shop_ecommerce.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.doanchung.shoes_shop_ecommerce.R
import com.doanchung.shoes_shop_ecommerce.databinding.ActivityIntroBinding
class IntroActivity : BaseActivity() {
    private lateinit var binding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener {
            Toast.makeText(this@IntroActivity, "Button clicked!", Toast.LENGTH_SHORT).show()

            startActivity(
                Intent(
                    this@IntroActivity, MainActivity::class.java
                )
            )
        }
    }
}