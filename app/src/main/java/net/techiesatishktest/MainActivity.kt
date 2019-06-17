package net.techiesatishktest

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*


private const val Request_Permission = 1


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private var permissions= arrayOf(Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE)
    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context=this
        if (!hasPermission(context,permissions)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissions, Request_Permission)
            }
        }





    }





    private fun hasPermission(context: Context,permissions: Array<String>):Boolean {
       var allSuccess=true
        for( i in permissions.indices){
            if (checkCallingOrSelfPermission(permissions[i])==PackageManager.PERMISSION_DENIED){
                allSuccess=false
            }
        }
        return allSuccess
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
       super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode== Request_Permission ){
            var allSuccess=true
            for(i in permissions.indices){
                allSuccess=false
                var reqAgain=  Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && shouldShowRequestPermissionRationale(permissions[i])
            if(reqAgain){
            Toast.makeText(context,"Permssion denied",Toast.LENGTH_SHORT).show()
              }
            else{
                Toast.makeText(context,"Please grant permission from Settings",Toast.LENGTH_SHORT).show()
            }

            }
            if(allSuccess){
                Toast.makeText(context,"All permissions are granted",Toast.LENGTH_SHORT).show()
            }
            navController = Navigation.findNavController(this, R.id.fragment)
            bottomNav.setupWithNavController(navController)
            NavigationUI.setupActionBarWithNavController(this, navController)
        }
    }


}


