package com.example.pulselog.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulselog.ui.theme.*

@Composable
fun AnalyticsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(48.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text("PulseLog", color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Icon(Icons.Default.Notifications, contentDescription = null, tint = TextPrimary)
        }
        Spacer(Modifier.height(8.dp))
        Text("PERFORMANCE REVIEW", color = TextSecondary, fontSize = 11.sp, letterSpacing = 1.sp)
        Text("Analytics", color = TextPrimary, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))

        // AI Insight Card
        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = CardBackground)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = AccentPurple, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("AI INSIGHT", color = AccentPurple, fontSize = 11.sp, letterSpacing = 1.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.height(8.dp))
                Text("Your chest volume increased 12% compared to the previous month.", color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(12.dp))
                OutlinedButton(onClick = {}, shape = RoundedCornerShape(8.dp), colors = ButtonColors(Color.Transparent, TextPrimary, Color.Transparent, TextSecondary), border = androidx.compose.foundation.BorderStroke(1.dp, TextSecondary)) {
                    Text("VIEW DETAILS", fontSize = 12.sp, letterSpacing = 1.sp)
                }
            }
        }
        Spacer(Modifier.height(16.dp))

        // Strength Progression
        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = CardBackground)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Strength Progression", color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text("LAST 6\nMONTHS", color = TextSecondary, fontSize = 10.sp)
                }
                Spacer(Modifier.height(16.dp))
                val bars = listOf(0.3f to "JAN", 0.45f to "FEB", 0.4f to "MAR", 0.55f to "APR", 0.75f to "MAY", 0.95f to "JUN")
                Row(modifier = Modifier.fillMaxWidth().height(100.dp), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    bars.forEach { (h, _) ->
                        Box(modifier = Modifier.weight(1f).fillMaxHeight(h).clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)).background(TextPrimary))
                    }
                }
                Spacer(Modifier.height(4.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    bars.forEach { (_, label) ->
                        Text(label, color = TextSecondary, fontSize = 10.sp, modifier = Modifier.weight(1f))
                    }
                }
            }
        }
        Spacer(Modifier.height(16.dp))

        // Recovery
        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = CardBackground)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Recovery", color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text("Readiness score is high. Optimal for heavy intensity today.", color = TextSecondary, fontSize = 13.sp)
                Spacer(Modifier.height(16.dp))
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                    CircularProgressIndicator(progress = { 0.9f }, modifier = Modifier.size(100.dp), color = TextPrimary, trackColor = ProgressTrack, strokeWidth = 8.dp)
                    Text("90%", color = TextPrimary, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.height(12.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("SLEEP", color = TextSecondary, fontSize = 11.sp, letterSpacing = 1.sp)
                        Text("8.2h", color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("HRV", color = TextSecondary, fontSize = 11.sp, letterSpacing = 1.sp)
                        Text("74ms", color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
        Spacer(Modifier.height(16.dp))

        // 1RM Estimates
        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = CardBackground)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("1RM Estimates", color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(12.dp))
                val lifts = listOf(
                    Triple("BACK SQUAT", "165", "+5kg since last week"),
                    Triple("BENCH PRESS", "110", "No change"),
                    Triple("DEADLIFT", "210", "+10kg since last month"),
                    Triple("OH PRESS", "75", "-2.5kg fatigue factor")
                )
                lifts.forEach { (name, weight, note) ->
                    Text(name, color = TextSecondary, fontSize = 11.sp, letterSpacing = 1.sp)
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(weight, color = TextPrimary, fontSize = 36.sp, fontWeight = FontWeight.Bold)
                        Text("KG", color = TextSecondary, fontSize = 13.sp, modifier = Modifier.padding(bottom = 6.dp, start = 4.dp))
                    }
                    Text(note, color = TextSecondary, fontSize = 12.sp)
                    Spacer(Modifier.height(12.dp))
                }
            }
        }
        Spacer(Modifier.height(16.dp))

        // Heatmap
        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = CardBackground)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text("Workload Heatmap", color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("LESS", color = TextSecondary, fontSize = 10.sp)
                        listOf(0.1f, 0.3f, 0.6f, 0.9f).forEach { alpha ->
                            Spacer(Modifier.width(3.dp))
                            Box(modifier = Modifier.size(12.dp).clip(RoundedCornerShape(2.dp)).background(TextPrimary.copy(alpha = alpha)))
                        }
                        Spacer(Modifier.width(3.dp))
                        Text("MORE", color = TextSecondary, fontSize = 10.sp)
                    }
                }
                Spacer(Modifier.height(12.dp))
                val heatValues = listOf(
                    listOf(0.2f, 0.8f, 0.1f, 0.6f, 0.9f, 0.3f, 0.5f),
                    listOf(0.7f, 0.1f, 0.9f, 0.2f, 0.4f, 0.8f, 0.1f),
                    listOf(0.3f, 0.5f, 0.2f, 0.9f, 0.1f, 0.6f, 0.7f),
                    listOf(0.9f, 0.2f, 0.6f, 0.1f, 0.8f, 0.2f, 0.4f),
                    listOf(0.1f, 0.7f, 0.4f, 0.5f, 0.2f, 0.9f, 0.3f)
                )
                heatValues.forEach { row ->
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        row.forEach { v ->
                            Box(modifier = Modifier.weight(1f).aspectRatio(1f).clip(RoundedCornerShape(3.dp)).background(TextPrimary.copy(alpha = v)))
                        }
                    }
                    Spacer(Modifier.height(4.dp))
                }
            }
        }
        Spacer(Modifier.height(16.dp))
    }
}