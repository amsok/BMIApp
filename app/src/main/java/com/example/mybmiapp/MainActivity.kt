package com.example.mybmiapp
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.mybmiapp.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private var bmi = 1.0;
    private var historyList = ArrayList<Item>()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(PrefConfig.readListFromPref(applicationContext) != null)
            historyList = PrefConfig.readListFromPref(applicationContext)!!
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
                var jsonString = Gson().toJson(historyList)
        var intentHistory = Intent(this@MainActivity, History::class.java)
        intentHistory.putExtra("historyList", jsonString)
        startActivity(intentHistory)
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
    binding.apply {

        outState?.run {
            putDouble("MY_BMI", bmi)

        }
    }
        super.onSaveInstanceState(outState)
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
            super.onRestoreInstanceState(savedInstanceState)
            bmi = savedInstanceState.getDouble("MY_BMI")
            binding.bmiTV.setText(String.format("%.2f", bmi))
            historyList = PrefConfig.readListFromPref(applicationContext)!!
    }
    fun showBMIinfo(view: View) {

        binding.apply {
            val intent = Intent(this@MainActivity, Result::class.java)
            intent.putExtra("bmi", bmi)
            startActivity(intent)
        }
    }

    private fun getDate(): String {
        return java.util.Calendar.getInstance().toString()
    }

    fun count(view: View) {
        binding.apply {

            if (heightET.text.isBlank() || massET.text.isBlank()) {
                heightET.error = getString(R.string.height_is_empty)
            }
            val h = heightET.text.toString().toFloat()
            val m = massET.text.toString().toFloat()

            if(m.equals(0.0) || h.equals(0.0) ){
                heightET.error = getString(R.string.height_is_empty)
            }
            else
                bmi = (10000*m/h/h).toDouble()

            if(changeUnits.text.equals("lb/in"))
                bmi *= 0.7030704

            bmiTV.text = String.format("%.2f", bmi)
            historyList.add(Item(bmi,h.toDouble(),m.toDouble(), getDate()))
            refreshList()
            PrefConfig.writeListInPref(applicationContext, historyList)
            when {
                bmi > 25.0 -> bmiTV.setTextColor(Color.RED)
                bmi > 18.5 -> bmiTV.setTextColor(Color.GREEN)
                else -> bmiTV.setTextColor(Color.BLUE)
            }
        }
    }
    fun refreshList(){
        while(historyList.size > 10)
            historyList.removeAt(10)
    }


    fun swap(view: View){
        binding.apply {
            if(massTV.text.equals(getResources().getString(R.string.mass_kg))){
                massTV.setText(getResources().getString(R.string.mass_lb))
                heightTV.setText(getResources().getString(R.string.height_in))
                changeUnits.setText("lb/in")
            }
            else{
                massTV.setText(getResources().getString(R.string.mass_kg))
                heightTV.setText(getResources().getString(R.string.height_cm))
                changeUnits.setText("kg/cm")
            }
        }
    }
}