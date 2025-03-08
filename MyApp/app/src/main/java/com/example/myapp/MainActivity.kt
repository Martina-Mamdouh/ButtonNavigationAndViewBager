package com.example.myapp

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.myapp.databinding.ActivityMainBinding
import com.example.myapp.fragment.CricketFragment
import com.example.myapp.fragment.FootballFragment
import com.example.myapp.fragment.HistoryFragment
import com.example.myapp.fragment.HockeyFragment
import com.example.myapp.fragment.HomeFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var cricketFragment: CricketFragment
    private lateinit var footballFragment: FootballFragment
    private lateinit var hockeyFragment: HockeyFragment
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var historyFragment: HistoryFragment
    private lateinit var homeFragment: HomeFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
         initialize()
        initiallyHideAllFragments()
        setTabLayoutWithViewPager()
        setUpBottomNavigation()
    }
    private fun setUpBottomNavigation()
    {
        binding.bottomNav.setOnItemSelectedListener{item->
            when(item.itemId){
          R.id.homeFragment->{
         showFragment(cricketFragment)
              showTabLayout()
           true
           }
                R.id.historyFragment->{
                    showFragment(historyFragment)
                    hideTabLayout()
                    true
                }
                else->false
            }
        }
    }

    private fun hideTabLayout() {
        binding.tabLayout.visibility= View.GONE
    }

    private fun showTabLayout() {
        binding.tabLayout.visibility= View.VISIBLE
    }

    private fun showFragment(fragment:Fragment) {
       supportFragmentManager.beginTransaction()
           .hide(cricketFragment)
           .hide(footballFragment)
           .hide(hockeyFragment)
           .hide(historyFragment)
           .show(fragment)
           .commitNow()
        binding.viewPager.visibility=if(fragment==historyFragment) View.GONE else View.VISIBLE
        binding.frameLayout.visibility=if(fragment==historyFragment) View.VISIBLE else View.GONE
    }

    private fun setTabLayoutWithViewPager()
    {
        binding.viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,positions->
            tab.text=when(positions){
                0->"Cricket"
                1->"Football"
                2->"Hockey"
                else->""
            }
        }.attach()
    }
    private fun initiallyHideAllFragments()
    {
        supportFragmentManager.beginTransaction()
            .add(R.id.frameLayout,cricketFragment)
            .hide(cricketFragment)
            .add(R.id.frameLayout,footballFragment)
            .hide(footballFragment)
            .add(R.id.frameLayout,hockeyFragment)
            .hide(hockeyFragment)
            .add(R.id.frameLayout,historyFragment)
            .hide(historyFragment)
            .commitNow()
    }
    private fun initialize()
    {
        cricketFragment=CricketFragment()
        footballFragment=FootballFragment()
        hockeyFragment=HockeyFragment()
        historyFragment=HistoryFragment()
        viewPagerAdapter=ViewPagerAdapter(this)
        binding.viewPager.offscreenPageLimit=2
        binding.viewPager.adapter = viewPagerAdapter
    }
}