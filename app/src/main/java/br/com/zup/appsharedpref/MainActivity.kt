package br.com.zup.appsharedpref

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.zup.appsharedpref.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClick.setOnClickListener {
            getInformation()
        }

        updateFields()
    }

    private fun getInformation() {
        val text = binding.etText.text.toString()
        val number = binding.etNumber.text.toString()

        if(binding.stSelect.isChecked){
            if(text.isNotBlank() && number.isNotBlank())
            saveSharedPrefs(text, number.toInt())
        }
    }

    private fun saveSharedPrefs(text: String, number: Int){
        val sharedPrefs = getSharedPreferences("DATA", MODE_PRIVATE).edit()
        with(sharedPrefs){
            putString("TEXT", text)
            putInt("NUMBER", number)
            apply()
        }
    }

    private fun updateFields(){
        val sharedPrefs = getSharedPreferences("DATA", MODE_PRIVATE) ?: return

        val text = sharedPrefs.getString("TEXT", "")
        val number = sharedPrefs.getInt("NUMBER", 0)

        binding.etText.setText(text)
        binding.etNumber.setText(number.toString())
    }
}