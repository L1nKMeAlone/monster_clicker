package com.example.severalactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.severalactivities.databinding.ActivityActiv2Binding
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class activ2 : AppCompatActivity() {
    private lateinit var binding: ActivityActiv2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityActiv2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //получаем список аргументов, присланных из первого активити
        //и если эти аргументы не null, то обрабатываем
        val arguments = intent.extras
        var nickname: String = "Player1"
        var damage: String = "10"
        if (arguments != null) {
            nickname = arguments.get("nickname").toString()
            damage = arguments.get("damage").toString()
        }
        binding.textNickname.text = nickname + ", урон " + damage

        var MonsterHP: Double = 50.0
        var MonsterStartHP: Double = MonsterHP
        var PlayerDamage: Double = damage.toDouble()
        binding.textHP.text = MonsterHP.toInt().toString() + " HP"

        var MonsterImage = binding.monsterView
        MonsterImage.setImageResource(R.drawable.m1)


        val MonsterView: ImageView = findViewById(R.id.monsterView)
        val MonsterAttackView: TextView = findViewById(R.id.attack)
        // Анимация для восхода солнца
        val MonsterAppearanceAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.appearance)
        // Подключаем анимацию к нужному View
        val MonsterAttackAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.attack)
        MonsterView.startAnimation(MonsterAppearanceAnimation)

        var MonsterCount: Int = 0
        var MonsterCountView = binding.monsterCount
        MonsterCountView.text = "Монстров побеждено: " + MonsterCount

        val button_hit = binding.buttonHit
        button_hit.setOnClickListener {
            MonsterHP -= PlayerDamage.toInt()
            if (MonsterHP > 0) {
                binding.textHP.text = MonsterHP.toInt().toString() + " HP"
                binding.attack.text = "-" +PlayerDamage.toInt().toString()+" HP"
                MonsterAttackView.startAnimation(MonsterAttackAnimation)
            } else {
                MonsterView.startAnimation(MonsterAppearanceAnimation)
                MonsterHP = MonsterStartHP * 1.5
                MonsterStartHP = MonsterHP
                binding.textHP.text = MonsterHP.toInt().toString() + " HP"
                PlayerDamage *= 1.4
                binding.textNickname.text = nickname + ", урон " + PlayerDamage.toInt().toString()
                var randomImage = (1..3).random()
                if (randomImage == 1) {
                    MonsterImage.setImageResource(R.drawable.m1)
                } else if (randomImage == 2) {
                    MonsterImage.setImageResource(R.drawable.m2)
                } else {
                    MonsterImage.setImageResource(R.drawable.m3)
                }
                MonsterCount++
                MonsterCountView.text = "Монстров побеждено: " + MonsterCount

            }
        }

        val btn_toact1 = binding.buttonToact1
        btn_toact1.setOnClickListener {
            val act1_start = Intent(this, MainActivity::class.java)
            startActivity(act1_start)
        }
    }
}