package com.sp45.android_animations.animations

import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.DeferredTargetAnimation
import androidx.compose.animation.core.ExperimentalAnimatableApi
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ApproachLayoutModifierNode
import androidx.compose.ui.layout.ApproachMeasureScope
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.LookaheadScope
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import androidx.compose.ui.unit.sp

@Composable
fun AnimatedPlacement() {
    var isInColumn by remember { mutableStateOf(true) }
    Column {
        BasicText(
            "With LookaheadScope",
            style = TextStyle.Default.copy(
                fontWeight = FontWeight.W900,
                fontSize = 30.sp
            ),
            modifier = Modifier
                .padding(30.dp)
                .align(Alignment.CenterHorizontally)
        )
        LookaheadScope {
            val items = remember {
                movableContentOf {
                    colors.forEach { color ->
                        Box(
                            Modifier
                                .padding(15.dp)
                                .size(100.dp, 80.dp)
                                .then(AnimatePlacementNodeElement(this))
                                .background(color, RoundedCornerShape(20))
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { isInColumn = !isInColumn }) {
                if (isInColumn) {
                    Column(Modifier.fillMaxSize()) { items() }
                } else {
                    Row { items() }
                }
            }
        }
    }
}

class AnimatedPlacementModifierNode(var lookaheadScope: LookaheadScope) :
    ApproachLayoutModifierNode, Modifier.Node() {
    @OptIn(ExperimentalAnimatableApi::class)
    val offsetAnimation: DeferredTargetAnimation<IntOffset, AnimationVector2D> =
        DeferredTargetAnimation(IntOffset.VectorConverter)

    override fun isMeasurementApproachInProgress(lookaheadSize: IntSize): Boolean {
        return false
    }

    @OptIn(ExperimentalAnimatableApi::class)
    override fun Placeable.PlacementScope.isPlacementApproachInProgress(
        lookaheadCoordinates: LayoutCoordinates,
    ): Boolean {
        val target =
            with(lookaheadScope) {
                lookaheadScopeCoordinates.localLookaheadPositionOf(lookaheadCoordinates).round()
            }
        offsetAnimation.updateTarget(target, coroutineScope, animationSpec = tween(300))
        return !offsetAnimation.isIdle
    }

    @OptIn(ExperimentalAnimatableApi::class)
    override fun ApproachMeasureScope.approachMeasure(
        measurable: Measurable,
        constraints: Constraints,
    ): MeasureResult {
        val placeable = measurable.measure(constraints)
        return layout(placeable.width, placeable.height) {
            val coordinates = coordinates
            if (coordinates != null) {
                val target =
                    with(lookaheadScope) {
                        lookaheadScopeCoordinates.localLookaheadPositionOf(coordinates).round()
                    }
                val animatedOffset = offsetAnimation.updateTarget(target, coroutineScope)
                val placementOffset =
                    with(lookaheadScope) {
                        lookaheadScopeCoordinates
                            .localPositionOf(coordinates, Offset.Zero)
                            .round()
                    }
                // Calculates the delta between animated position in scope and current
                // position in scope, and places the child at the delta offset. This puts
                // the child layout at the animated position.
                val (x, y) = animatedOffset - placementOffset
                placeable.place(x, y)
            } else {
                placeable.place(0, 0)
            }
        }
    }
}

data class AnimatePlacementNodeElement(val lookaheadScope: LookaheadScope) :
    ModifierNodeElement<AnimatedPlacementModifierNode>() {

    override fun update(node: AnimatedPlacementModifierNode) {
        node.lookaheadScope = lookaheadScope
    }

    override fun create(): AnimatedPlacementModifierNode {
        return AnimatedPlacementModifierNode(lookaheadScope)
    }
}

val colors = listOf(Color(0xffff6f69), Color(0xffffcc5c), Color(0xff264653), Color(0xff2a9d84))
