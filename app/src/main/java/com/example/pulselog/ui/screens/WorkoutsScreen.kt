package com.example.pulselog.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulselog.ui.theme.*
import com.example.pulselog.ui.navigation.Screen

@Composable
fun WorkoutsScreen(navController: androidx.navigation.NavController) {
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
            Text("PulseLog", color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Row {
                Icon(Icons.Default.Notifications, contentDescription = null, tint = TextPrimary)
                Spacer(Modifier.width(12.dp))
                Icon(Icons.Default.Person, contentDescription = null, tint = TextPrimary)
            }
        }
        Spacer(Modifier.height(20.dp))
        Text("Workouts", color = TextPrimary, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))

        // Start New Workout Button
        Button(
            onClick = { navController.navigate(Screen.ActiveWorkout.route) },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Text("START NEW WORKOUT", color = Color.Black, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
        }
        Spacer(Modifier.height(16.dp))

        // Search Bar
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Search exercises or routines...", color = TextSecondary) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = TextSecondary) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = AccentPurple,
                unfocusedContainerColor = CardBackground,
                focusedContainerColor = CardBackground,
                unfocusedTextColor = TextPrimary,
                focusedTextColor = TextPrimary
            )
        )
        Spacer(Modifier.height(24.dp))

        // Active Templates
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("ACTIVE TEMPLATES", color = TextSecondary, fontSize = 11.sp, letterSpacing = 1.sp)
            Text("View All", color = AccentPurple, fontSize = 12.sp)
        }
        Spacer(Modifier.height(12.dp))

        val templates = listOf(
            Triple("Push", "Chest, Shoulders, Triceps • 6 Exercises", "STRENGTH"),
            Triple("Pull", "Back, Biceps, Rear Delts • 5 Exercises", "STRENGTH"),
            Triple("Legs", "Quads, Hams, Calves • 7 Exercises", "STRENGTH")
        )
        templates.forEach { (name, desc, tag) ->
            WorkoutTemplateCard(name, desc, tag)
            Spacer(Modifier.height(10.dp))
        }

        Spacer(Modifier.height(16.dp))
        Text("WORKOUT HISTORY", color = TextSecondary, fontSize = 11.sp, letterSpacing = 1.sp)
        Spacer(Modifier.height(12.dp))

        val history = listOf(
            Triple("Leg Day – Destruction", "Yesterday • 1h 12m • 14,250 lbs volume", "425"),
            Triple("Upper Body Power", "Monday, Oct 23 • 58m • 9,800 lbs volume", "310"),
            Triple("Active Recovery Flow", "Sunday, Oct 22 • 45m • Bodyweight", "185")
        )
        history.forEach { (name, detail, kcal) ->
            WorkoutHistoryCard(name, detail, kcal)
            Spacer(Modifier.height(10.dp))
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun WorkoutTemplateCard(name: String, desc: String, tag: String) {
    var toggled by remember { mutableStateOf(name == "Push") }
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(tag, color = TextSecondary, fontSize = 11.sp, letterSpacing = 1.sp)
            Spacer(Modifier.height(4.dp))
            Text(name, color = TextPrimary, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text(desc, color = TextSecondary, fontSize = 13.sp)
            if (name == "Push") {
                Spacer(Modifier.height(8.dp))
                Switch(
                    checked = toggled,
                    onCheckedChange = { toggled = it },
                    colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = AccentPurple)
                )
            }
        }
    }
}

@Composable
fun WorkoutHistoryCard(name: String, detail: String, kcal: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(40.dp).clip(RoundedCornerShape(10.dp)).background(ProgressTrack),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.FitnessCenter, contentDescription = null, tint = AccentPurple)
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(name, color = TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                Text(detail, color = TextSecondary, fontSize = 12.sp)
                Spacer(Modifier.height(4.dp))
                Text("$kcal KCAL", color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Icon(Icons.Default.MoreVert, contentDescription = null, tint = TextSecondary)
        }
    }
}