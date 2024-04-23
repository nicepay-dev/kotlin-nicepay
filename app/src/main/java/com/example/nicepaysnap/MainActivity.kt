package com.example.nicepaysnap

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.nicepaysnap.databinding.ActivityMainBinding
import com.example.nicepaysnap.view.ewallet.EwalletMenu
import com.example.nicepaysnap.view.payout.PayoutMenu
import com.example.nicepaysnap.view.qris.QrisMenu
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var addFAB: FloatingActionButton
    lateinit var homeFAB: FloatingActionButton
    lateinit var qrFAB: FloatingActionButton
    lateinit var payoutFAB: FloatingActionButton
    lateinit var settingsFAB: FloatingActionButton
    var fabVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        addFAB = findViewById(R.id.fab)
        homeFAB = findViewById(R.id.idFABHome)
        settingsFAB = findViewById(R.id.idFABSettings)
        qrFAB = findViewById(R.id.idFABQr)
        payoutFAB = findViewById(R.id.idFABReceipt)

        fabVisible = false

        binding.fab.setOnClickListener { view ->
            if (!fabVisible) {
                homeFAB.show()
                settingsFAB.show()
                qrFAB.show()
                payoutFAB.show()

                homeFAB.visibility = View.VISIBLE
                settingsFAB.visibility = View.VISIBLE
                qrFAB.visibility = View.VISIBLE
                payoutFAB.visibility = View.VISIBLE

                addFAB.setImageDrawable(resources.getDrawable(R.drawable.ic_close))

                homeFAB.setOnClickListener{
                    val intent = Intent(this, virtualAccount::class.java)
                    startActivity(intent)
                }

                settingsFAB.setOnClickListener {
                    val intent = Intent(this, EwalletMenu::class.java)
                    startActivity(intent)
                }

                qrFAB.setOnClickListener {
                    val intent = Intent(this, QrisMenu::class.java)
                    startActivity(intent)
                }

                payoutFAB.setOnClickListener {
                    val intent = Intent(this, PayoutMenu::class.java)
                    startActivity(intent)
                }

                fabVisible = true
            } else {
                homeFAB.hide()
                settingsFAB.hide()
                qrFAB.hide()
                payoutFAB.hide()

                homeFAB.visibility = View.GONE
                settingsFAB.visibility = View.GONE
                qrFAB.visibility = View.GONE
                payoutFAB.visibility = View.GONE

                addFAB.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_shopping_cart))

                fabVisible = false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}