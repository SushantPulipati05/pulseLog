package com.example.pulselog.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulselog.ui.theme.*
import kotlinx.coroutines.delay

data class WorkoutSet(
    val id: Int,
    var weight: String = "",
    var reps: String = "",
    var completed: Boolean = false
)

@Composable
fun ActiveWorkoutScreen(onFinish: () -> Unit) {
    var seconds by remember { mutableIntStateOf(0) }
    var sets by remember { mutableStateOf(listOf(
        WorkoutSet(1, "140", "8", true),
        WorkoutSet(2, "140", "8", true),
        WorkoutSet(3, "", "", false)
    )) }

    // Timer
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            seconds++
        }
    }

    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val secs = seconds % 60
    val timerText = "%02d:%02d:%02d".format(hours, minutes, secs)

    val totalVolume = sets.filter { it.completed }
        .sumOf { ((it.weight.toFloatOrNull() ?: 0f) * (it.reps.toFloatOrNull() ?: 0f)).toDouble() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(48.dp))

        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Timer, contentDescription = null, tint = TextPrimary, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(6.dp))
                Text("PulseLog", color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Button(
                onClick = onFinish,
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text("Finish Workout", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            }
        }

        Spacer(Modifier.height(20.dp))

        // Duration Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = CardBackground)
        ) {
            Column(
                modifier = Modifier.padding(20.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("DURATION", color = TextSecondary, fontSize = 11.sp, letterSpacing = 1.sp)
                Spacer(Modifier.height(4.dp))
                Text(
                    timerText,
                    color = TextPrimary,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        // Total Volume Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = CardBackground)
        ) {
            Column(
                modifier = Modifier.padding(20.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("TOTAL VOLUME", color = TextSecondary, fontSize = 11.sp, letterSpacing = 1.sp)
                Spacer(Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        "%,.0f".format(totalVolume + 11010f),
                        color = TextPrimary,
                        fontSize = 42.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.width(4.dp))
                    Text("KG", color = TextSecondary, fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp))
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // Exercise Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = CardBackground)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Exercise Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Barbell Back Squat", color = TextPrimary, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text("PRIMARY LIFT • LOWER BODY", color = TextSecondary, fontSize = 11.sp, letterSpacing = 0.5.sp)
                    }
                    Icon(Icons.Default.MoreHoriz, contentDescription = null, tint = TextSecondary)
                }

                Spacer(Modifier.height(16.dp))

                // Table Header
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text("SET", color = TextSecondary, fontSize = 11.sp, letterSpacing = 1.sp, modifier = Modifier.width(36.dp))
                    Text("WEIGHT (KG)", color = TextSecondary, fontSize = 11.sp, letterSpacing = 1.sp, modifier = Modifier.weight(1f))
                    Text("REPS", color = TextSecondary, fontSize = 11.sp, letterSpacing = 1.sp, modifier = Modifier.weight(1f))
                    Text("STATUS", color = TextSecondary, fontSize = 11.sp, letterSpacing = 1.sp)
                }

                Spacer(Modifier.height(12.dp))

                // Sets
                sets.forEachIndexed { index, set ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "${set.id}",
                            color = if (set.completed) TextPrimary else TextSecondary,
                            fontSize = 16.sp,
                            fontWeight = if (set.completed) FontWeight.Bold else FontWeight.Normal,
                            modifier = Modifier.width(36.dp)
                        )
                        OutlinedTextField(
                            value = set.weight,
                            onValueChange = { newVal ->
                                sets = sets.toMutableList().also { it[index] = set.copy(weight = newVal) }
                            },
                            modifier = Modifier.weight(1f).padding(end = 6.dp).height(52.dp),
                            shape = RoundedCornerShape(10.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            placeholder = { Text("0", color = TextSecondary) },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = if (set.completed) Color.Transparent else TextSecondary.copy(alpha = 0.3f),
                                focusedBorderColor = AccentPurple,
                                unfocusedContainerColor = if (set.completed) ProgressTrack else CardBackground,
                                focusedContainerColor = CardBackground,
                                unfocusedTextColor = TextPrimary,
                                focusedTextColor = TextPrimary
                            ),
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = set.reps,
                            onValueChange = { newVal ->
                                sets = sets.toMutableList().also { it[index] = set.copy(reps = newVal) }
                            },
                            modifier = Modifier.weight(1f).padding(end = 6.dp).height(52.dp),
                            shape = RoundedCornerShape(10.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            placeholder = { Text("0", color = TextSecondary) },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = if (set.completed) Color.Transparent else TextSecondary.copy(alpha = 0.3f),
                                focusedBorderColor = AccentPurple,
                                unfocusedContainerColor = if (set.completed) ProgressTrack else CardBackground,
                                focusedContainerColor = CardBackground,
                                unfocusedTextColor = TextPrimary,
                                focusedTextColor = TextPrimary
                            ),
                            singleLine = true
                        )
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(if (set.completed) Color.White else Color.Transparent)
                                .border(1.dp, if (set.completed) Color.White else TextSecondary, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            IconButton(onClick = {
                                sets = sets.toMutableList().also {
                                    it[index] = set.copy(completed = !set.completed)
                                }
                            }, modifier = Modifier.size(36.dp)) {
                                Icon(
                                    Icons.Default.Check,
                                    contentDescription = null,
                                    tint = if (set.completed) Color.Black else TextSecondary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))

                // Add Set Button
                OutlinedButton(
                    onClick = {
                        sets = sets + WorkoutSet(sets.size + 1)
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, TextSecondary.copy(alpha = 0.4f)),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = TextPrimary)
                ) {
                    Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("Add Set", fontSize = 14.sp)
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        // Form Check Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = CardBackground)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(ProgressTrack),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.FitnessCenter, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(56.dp))
                }
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Form Check", color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text("Keep chest up, weight in heels.", color = TextSecondary, fontSize = 13.sp)
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        // Add Exercise Button
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth().height(54.dp),
            shape = RoundedCornerShape(27.dp),
            colors = ButtonDefaults.buttonColors(containerColor = CardBackground)
        ) {
            Icon(Icons.Default.AddCircleOutline, contentDescription = null, tint = TextPrimary)
            Spacer(Modifier.width(8.dp))
            Text("Add Exercise", color = TextPrimary, fontWeight = FontWeight.SemiBold)
        }

        Spacer(Modifier.height(24.dp))
    }
}