package com.klepto.labs.imageloadersampleapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.klepto.labs.imageloader.ImageLoader
import kotlinx.android.synthetic.main.fragment_photo_preview.*

private const val URL = "url"


class PhotoPreviewFragment : Fragment() {
    private var url: String  = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(URL)

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photo_preview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        context?.let {
            ImageLoader.Builder().with(it).load(url).into(imageView)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(url: String) =
            PhotoPreviewFragment().apply {
                arguments = Bundle().apply {
                    putString(URL, url)
                }
            }
    }
}
