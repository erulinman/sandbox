package info.erulinman.animations.animationutils

import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton

fun FloatingActionButton.startAnimationWithActionAfter(
    animResId: Int,
    iconResId: Int,
    onClick: () -> Unit
) {
    val listener = object : Animation.AnimationListener {
        override fun onAnimationStart(p0: Animation?) {
            setImageResource(iconResId)
            setOnClickListener { onClick() }
        }
        override fun onAnimationRepeat(p0: Animation?) {/* DO NOTHING */}
        override fun onAnimationEnd(p0: Animation?) {/* DO NOTHING */}
    }

    val animation = AnimationUtils.loadAnimation(this.context, animResId)
        .apply { setAnimationListener(listener) }

    startAnimation(animation)
}



