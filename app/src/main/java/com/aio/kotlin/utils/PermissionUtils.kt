package com.aio.kotlin.utils


import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

class PermissionUtils {

    fun askNotificationPermission(context: Context, activity: FragmentActivity) {
        // permission 선택에 관한 결과
        val requestPermissionLauncher = activity.registerForActivityResult(
            ActivityResultContracts.RequestPermission(),
        ) { isGranted: Boolean ->
            if (isGranted) {
                Log.d("PermissionUtils", "권한이 허가 되었습니다.")
            } else {
                Log.d("PermissionUtils", "권한이 허가 되었습니다.")
            }
        }

        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // 이미 Permission이 허락되어 있다면
                Log.d("PermissionUtils", "권한이 이미 허가되었습니다.")
            } else if (shouldShowRequestPermissionRationale(
                    activity,
                    Manifest.permission.POST_NOTIFICATIONS
                )
            ) {
                // 권한을 거부한 경우
                Log.d("PermissionUtils", "권한이 이미 거부되었습니다.")
            } else {
                // 처음 권한을 요청하는 경우
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}