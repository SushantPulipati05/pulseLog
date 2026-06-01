package com.example.pulselog.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulselog.ui.theme.*

@Composable
fun RoutinesScreen() {
    var selectedFilter by remember { mutableStateOf("Muscle Gain") }
    val filters = listOf("Muscle Gain", "Fat Loss")
    val days = listOf("14" to "MON", "15" to "TUE", "16" to "WED", "17" to "THU", "18" to "FRI", "19" to "SAT", "20" to "SUN")

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
        Spacer(Modifier.height(20.dp))
        Text("Routines", color = TextPrimary, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))

        // Filter chips
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            filters.forEach { filter ->
                val selected = selectedFilter == filter
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (selected) TextPrimary else Color.Transparent)
                        .border(1.dp, if (selected) TextPrimary else TextSecondary, RoundedCornerShape(20.dp))
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(filter, color = if (selected) Background else TextSecondary, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                }
            }
        }
        Spacer(Modifier.height(16.dp))

        // Schedule card
        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = CardBackground)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("ACTIVE SCHEDULE", color = TextSecondary, fontSize = 11.sp, letterSpacing = 1.sp)
                    Text("Oct 14 — 20", color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                }
                Spacer(Modifier.height(12.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    days.forEach { (date, day) ->
                        val isToday = date == "15"
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(day, color = TextSecondary, fontSize = 10.sp)
                            Spacer(Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(if (isToday) TextPrimary else Color.Transparent),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(date, color = if (isToday) Background else TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                            }
                            if (date == "15" || date == "18") {
                                Spacer(Modifier.height(3.dp))
                                Box(modifier = Modifier.size(4.dp).clip(CircleShape).background(AccentPurple))
                            }
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(20.dp))

        // My Routines
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text("My Routines", color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Add, contentDescription = null, tint = AccentPurple, modifier = Modifier.size(16.dp))
                Text("NEW", color = AccentPurple, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            }
        }
        Spacer(Modifier.height(12.dp))

        // Full Body Focus card
        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = CardBackground)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("3 DAYS PER WEEK", color = TextSecondary, fontSize = 10.sp, letterSpacing = 1.sp)
                Spacer(Modifier.height(4.dp))
                Text("Full Body Focus", color = TextPrimary, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(40.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    listOf("SQ", "BR", "DL").forEach { abbr ->
                        Box(modifier = Modifier.size(28.dp).clip(CircleShape).background(ProgressTrack), contentAlignment = Alignment.Center) {
                            Text(abbr, color = TextPrimary, fontSize = 8.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(Modifier.width((-8).dp))
                    }
                    Spacer(Modifier.width(16.dp))
                    Text("12 Exercises", color = TextSecondary, fontSize = 13.sp)
                }
            }
        }
        Spacer(Modifier.height(10.dp))

        // Morning Cardio card
        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = CardBackground)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("DAILY MORNING", color = TextSecondary, fontSize = 10.sp, letterSpacing = 1.sp)
                Spacer(Modifier.height(4.dp))
                Text("Morning Cardio", color = TextPrimary, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(20.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Timer, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("45 Minutes", color = TextSecondary, fontSize = 13.sp)
                }
            }
        }
        Spacer(Modifier.height(20.dp))

        // Expert Plans
        Text("Expert Prebuilt Plans", color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))
        val plans = listOf(
            Triple("Push Pull Legs", "The classic science-based split for hypertrophy.", Icons.Default.FitnessCenter),
            Triple("Arnold Split", "High volume aesthetic training from the gold era.", Icons.Default.Star)
        )
        plans.forEach { (name, desc, icon) ->
            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = CardBackground)) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(40.dp).clip(RoundedCornerShape(10.dp)).background(ProgressTrack), contentAlignment = Alignment.Center) {
                        Icon(icon, contentDescription = null, tint = AccentPurple)
                    }
                    Spacer(Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(name, color = TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                        Text(desc, color = TextSecondary, fontSize = 12.sp)
                    }
                    Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextSecondary)
                }
            }
            Spacer(Modifier.height(10.dp))
        }
        Spacer(Modifier.height(16.dp))
    }
}