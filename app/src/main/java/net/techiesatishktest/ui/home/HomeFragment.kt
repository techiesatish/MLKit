package net.techiesatishktest.ui.home


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import kotlinx.android.synthetic.main.fragment_home.*
import net.techiesatishktest.R




class HomeFragment : Fragment() {

    val REQUEST_IMAGE_CAPTURE = 1


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val intents= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intents, REQUEST_IMAGE_CAPTURE )
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
//            val bounds = barcode.boundingBox
//            val corners = barcode.cornerPoints

//            val rawValue = barcode.rawValue
//            val valueType = barcode.valueType
//
//            when (valueType) {
//                FirebaseVisionBarcode.TYPE_WIFI -> {
//                    val ssid = barcode.getWifi()!!.getSsid()
//                    val password = barcode.getWifi()!!.getPassword()
//                    val type = barcode.getWifi()!!.getEncryptionType()
//                }
//                FirebaseVisionBarcode.TYPE_URL -> {
//                    val title = barcode.getUrl()!!.getTitle()
//                    val url = barcode.getUrl()!!.getUrl()
//                }
//                FirebaseVisionBarcode.TYPE_UNKNOWN ->{
//
//                    Log.d("TEXTRECOG","UNKNOWN BARCODE")
//                }
//            }
        }

    }

    private fun updateLabel(message: String){
        tv_decode.setText(message)
        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show()
    }



}