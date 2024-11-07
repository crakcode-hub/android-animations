import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

data class Confetti(
    var position: Offset,
    var color: Color,
    var size: Float,
    var velocity: Offset
)

@Composable
fun ConfettiAnimation() {
    val confettiPieces = remember { generateConfettiPieces() }

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val yOffsetAnimation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(50000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Canvas(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        confettiPieces.forEach { confetti ->
            val newX = (confetti.position.x + confetti.velocity.x * yOffsetAnimation.value) % size.width
            val newY = (confetti.position.y + confetti.velocity.y * yOffsetAnimation.value * size.height) % size.height


            confetti.position = Offset(newX, newY)

            //confetti as a small circle
            drawCircle(
                color = confetti.color,
                radius = confetti.size,
                center = Offset(newX, newY)
            )
        }
    }
}

fun generateConfettiPieces(): List<Confetti> {
    val colors = listOf(Color.Red, Color.Blue, Color.Yellow, Color.Green, Color.Magenta)
    return List(100) {
        Confetti(
            color = colors.random(),
            position = Offset(
                x = Random.nextFloat() * 1000f,
                y = Random.nextFloat() * 100f
            ),
            velocity = Offset(
                x = Random.nextFloat() * 1.0f - 0.5f, // Horizontal speed
                y = Random.nextFloat() * 0.3f + 0.3f // Downward speed
            ),
            size = Random.nextFloat() * 10f + 5f // size
        )
    }
}

@Composable
fun ConfettiButton() {
    var showConfetti by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { showConfetti = !showConfetti }
            ) {
                Text("Toggle Confetti Animation")
            }

            if (showConfetti) {
                ConfettiAnimation()
            }
        }
    }
}
