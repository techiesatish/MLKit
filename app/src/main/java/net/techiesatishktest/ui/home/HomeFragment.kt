package net.techiesatishktest.ui.home


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import kotlinx.android.synthetic.main.fragment_home.*
import net.techiesatishktest.R
import net.techiesatishktest.db.entity.Codes
import java.sql.Timestamp


class HomeFragment : Fragment() {

    val REQUEST_IMAGE_CAPTURE = 1
    private lateinit var mViewModel: HomeViewModel
    private lateinit var contexts: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        contexts=this.context!!
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        tv_decode.setOnClickListener {

            val intents= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intents, REQUEST_IMAGE_CAPTURE )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_IMAGE_CAPTURE) {
            val extras = data?.getExtras()
            val imageBitmap = extras?.get("data") as Bitmap
            snapShotView.setImageBitmap(imageBitmap)
            decodeBarcode(imageBitmap)
        } else {
            System.out.println("Failed to load image");
        }
    }




    private fun decodeBarcode(imageBitmap: Bitmap) {
        val image = FirebaseVisionImage.fromBitmap(imageBitmap!!)
        val detector = FirebaseVision.getInstance().visionBarcodeDetector


        detector.detectInImage(image)
            .addOnSuccessListener {barcodes -> processBarcodes(barcodes)

            }
            .addOnFailureListener{exception ->
                print(exception.localizedMessage)
            }
    }



    private fun processBarcodes(barcodes: List<FirebaseVisionBarcode>){


        for (barcode in barcodes) {
            val displayValue = barcode.displayValue
            val msg = displayValue?.let{ it }?:"Undefined"
            updateLabel("Barcode:$msg")
        }

    }

    private fun updateLabel(message: String){
        tv_decode.setText(message)
//        val time =Calendar.getInstance().time.toString("yyyy/MM/dd HH:mm:ss")
        Toast.makeText(activity,message+" "+ Timestamp(System.currentTimeMillis()) ,Toast.LENGTH_SHORT).show()
        mViewModel.insert(Codes(message,Timestamp(System.currentTimeMillis()).toString()))

    }



}