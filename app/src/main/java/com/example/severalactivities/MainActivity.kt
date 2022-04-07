package com.example.severalactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.severalactivities.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val bt_toact2 = binding.buttonToact2
        bt_toact2.setOnClickListener {
//           Intent - намерение, механизм описания операции
            //создать намерение вызвать вторую активити
            val act2_start = Intent(this, activ2::class.java)
            var nickname = binding.Nickname.text.toString()
            if(nickname == ""){
                nickname = "Player"
            }
            var damage = binding.Damage.text.toString()
            if(damage.toString().toInt() < 1) {
                damage = "1"
            }

            //метод putExtra класса Intent позволяет отдавать данные в связке ключ+значение
            act2_start.putExtra("nickname", nickname)
            act2_start.putExtra("damage", damage)
            //запуск второго активити
startActivity(act2_start)
        }
    }
}