package com.example.fastcampusadvanced.chapter1

import android.graphics.PointF
import android.graphics.RectF
import android.os.Bundle
import android.util.SizeF
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.transition.TransitionManager
import com.example.face_recognition.Camera
import com.example.face_recognition.recognition.FaceAnalyzerListener
import com.example.fastcampusadvanced.databinding.ActivityFaceBinding

class FaceActivity : AppCompatActivity(), FaceAnalyzerListener {
    private lateinit var binding: ActivityFaceBinding

    private val camera = Camera(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFaceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setProgressText("시작하기를 눌러주세요.")

        camera.initCamera(binding.cameraL, this@FaceActivity)

        binding.startDetectBtn.setOnClickListener {
            it.isVisible = false
            binding.faceOverlayV.reset()
            camera.startFaceDetect()
            setProgressText("얼굴을 보여주세요.")
        }
    }

    override fun detect() {
    }

    override fun stopDetect() {
        camera.stopFaceDetect()
        reset()
    }

    override fun notDetect() {
        binding.faceOverlayV.reset()
    }

    override fun detectProgress(progress: Float, message: String) {
        setProgressText(message)
        binding.faceOverlayV.setProgress(progress)
    }

    override fun faceSize(rectF: RectF, sizeF: SizeF, pointF: PointF) {
        binding.faceOverlayV.setSize(rectF, sizeF, pointF)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        camera.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun reset() {
        binding.startDetectBtn.isVisible = true
    }

    private fun setProgressText(text: String) {
        TransitionManager.beginDelayedTransition(binding.root)
        binding.progressTv.text = text
    }
}