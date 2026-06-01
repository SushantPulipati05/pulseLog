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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulselog.ui.theme.*
import com.example.pulselog.ui.navigation.Screen

@Composable
fun DashboardScreen(navController: androidx.navigation.NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(48.dp))
        TopBar()
        Spacer(Modifier.height(20.dp))
        GreetingSection()
        Spacer(Modifier.height(20.dp))
        ActiveStreakCard()
        Spacer(Modifier.height(12.dp))
        WeeklyGoalCard()
        Spacer(Modifier.height(12.dp))
        MuscleRecoveryCard()
        Spacer(Modifier.height(12.dp))
        WeeklyVolumeCard()
        Spacer(Modifier.height(20.dp))
        RecentActivitySection()
        Spacer(Modifier.height(16.dp))
        StartWorkoutButton(onClick = { navController.navigate(Screen.ActiveWorkout.route) })
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(AccentPurple),
                contentAlignment = Alignment.Center
            ) {
                Text("P", color = Color.White, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.width(8.dp))
            Text("PulseLog", color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = TextPrimary)
    }
}

@Composable
fun GreetingSection() {
    Column {
        Text("Good Morning, Alex", color = TextPrimary, fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Text("You're on track to hit your weekly goal.", color = TextSecondary, fontSize = 14.sp)
    }
}

@Composable
fun ActiveStreakCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("ACTIVE STREAK", color = TextSecondary, fontSize = 11.sp, letterSpacing = 1.sp)
            Spacer(Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text("12", color = TextPrimary, fontSize = 48.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.width(4.dp))
                Text("DAYS", color = TextSecondary, fontSize = 14.sp, modifier = Modifier.padding(bottom = 10.dp))
            }
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                repeat(7) { index ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(if (index < 5) AccentPurple else ProgressTrack)
                    )
                }
            }
        }
    }
}

@Composable
fun WeeklyGoalCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("WEEKLY GOAL", color = TextSecondary, fontSize = 11.sp, letterSpacing = 1.sp)
                Icon(Icons.Default.Fullscreen, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(16.dp))
            }
            Spacer(Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text("4", color = TextPrimary, fontSize = 48.sp, fontWeight = FontWeight.Bold)
                Text("/5", color = TextSecondary, fontSize = 28.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 6.dp))
                Spacer(Modifier.width(6.dp))
                Text("WORKOUTS", color = TextSecondary, fontSize = 14.sp, modifier = Modifier.padding(bottom = 10.dp))
            }
            LinearProgressIndicator(
                progress = { 0.8f },
                modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp)),
                color = AccentPurple,
                trackColor = ProgressTrack
            )
        }
    }
}

@Composable
fun MuscleRecoveryCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("MUSCLE RECOVERY", color = TextSecondary, fontSize = 11.sp, letterSpacing = 1.sp)
            Spacer(Modifier.height(12.dp))
            MuscleRow("CHEST", 0.85f, ChestColor, "85%")
            Spacer(Modifier.height(10.dp))
            MuscleRow("BACK", 0.60f, BackColor, "60%")
            Spacer(Modifier.height(10.dp))
            MuscleRow("LEGS", 0.30f, LegsColor, "30%")
        }
    }
}

@Composable
fun MuscleRow(label: String, progress: Float, color: Color, percentage: String) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(label, color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.Medium)
            Text(percentage, color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.Medium)
        }
        Spacer(Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxWidth().height(5.dp).clip(RoundedCornerShape(3.dp)),
            color = color,
            trackColor = ProgressTrack
        )
    }
}

@Composable
fun WeeklyVolumeCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("WEEKLY VOLUME", color = TextSecondary, fontSize = 11.sp, letterSpacing = 1.sp)
            Spacer(Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text("24.5k", color = TextPrimary, fontSize = 42.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.width(4.dp))
                Text("KG", color = TextSecondary, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
            }
            Spacer(Modifier.height(12.dp))
            val bars = listOf(0.5f, 0.7f, 0.4f, 1.0f, 0.6f, 0.3f, 0.8f)
            Row(
                modifier = Modifier.fillMaxWidth().height(60.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                bars.forEachIndexed { index, value ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(value)
                            .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                            .background(if (index == 3) AccentPurple else ProgressTrack)
                    )
                }
            }
        }
    }
}

@Composable
fun RecentActivitySection() {
    val activities = listOf(
        Triple("Hypertrophy – Upper Body", "Yesterday • 1h 24m", "8,420 kg"),
        Triple("Functional Endurance", "2 days ago • 45m", "3,150 kg"),
        Triple("Leg Day Recovery", "4 days ago • 1h 05m", "12,940 kg")
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Recent Activity", color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text("VIEW ALL", color = AccentPurple, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
    }
    Spacer(Modifier.height(12.dp))
    activities.forEach { (name, time, volume) ->
        ActivityRow(name, time, volume)
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
fun ActivityRow(name: String, time: String, volume: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(ProgressTrack),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.FitnessCenter, contentDescription = null, tint = AccentPurple, modifier = Modifier.size(18.dp))
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(name, color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Medium, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(time, color = TextSecondary, fontSize = 12.sp)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(volume, color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text("VOLUME", color = TextSecondary, fontSize = 10.sp, letterSpacing = 0.5.sp)
            }
        }
    }
}

@Composable
fun StartWorkoutButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,   // 👈 was hardcoded {}, now receives action
        modifier = Modifier.fillMaxWidth().height(54.dp),
        shape = RoundedCornerShape(27.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
    ) {
        Icon(Icons.Default.Add, contentDescription = null, tint = Color.Black)
        Spacer(Modifier.width(8.dp))
        Text("START WORKOUT", color = Color.Black, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
    }
}