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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulselog.ui.theme.*

@Composable
fun ProfileScreen() {
    var darkMode by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(48.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(AccentPurple), contentAlignment = Alignment.Center) {
                    Text("P", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.width(8.dp))
                Text("PulseLog", color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Icon(Icons.Default.Notifications, contentDescription = null, tint = TextPrimary)
        }
        Spacer(Modifier.height(28.dp))

        // Avatar
        Box(contentAlignment = Alignment.BottomEnd) {
            Box(
                modifier = Modifier.size(100.dp).clip(CircleShape).border(2.dp, TextSecondary, CircleShape).background(CardBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(56.dp))
            }
            Box(
                modifier = Modifier.size(28.dp).clip(CircleShape).background(CardBackground).border(1.dp, TextSecondary, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Settings, contentDescription = null, tint = TextPrimary, modifier = Modifier.size(16.dp))
            }
        }
        Spacer(Modifier.height(12.dp))
        Text("Marcus Thorne", color = TextPrimary, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("ELITE TIER MEMBER", color = TextSecondary, fontSize = 12.sp, letterSpacing = 1.sp)
        Spacer(Modifier.height(24.dp))

        // Stats
        val stats = listOf(Triple("HEIGHT", "188", "cm"), Triple("WEIGHT", "92.4", "kg"), Triple("BODY FAT", "12.5", "%"))
        stats.forEach { (label, value, unit) ->
            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = CardBackground)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(label, color = TextSecondary, fontSize = 11.sp, letterSpacing = 1.sp)
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(value, color = TextPrimary, fontSize = 48.sp, fontWeight = FontWeight.Bold)
                        Text(unit, color = TextSecondary, fontSize = 16.sp, modifier = Modifier.padding(bottom = 10.dp, start = 4.dp))
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
        }

        // Achievements
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text("Achievements", color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text("VIEW ALL", color = AccentPurple, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        }
        Spacer(Modifier.height(12.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            val achievements = listOf(Icons.Default.Star to "YEAR ONE", Icons.Default.FitnessCenter to "1000 REPS", Icons.Default.Bolt to "STREAK 30")
            achievements.forEach { (icon, label) ->
                Card(modifier = Modifier.weight(1f), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = CardBackground)) {
                    Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(icon, contentDescription = null, tint = TextPrimary, modifier = Modifier.size(28.dp))
                        Spacer(Modifier.height(6.dp))
                        Text(label, color = TextSecondary, fontSize = 10.sp, letterSpacing = 0.5.sp)
                    }
                }
            }
        }
        Spacer(Modifier.height(20.dp))

        // Settings
        Text("System Settings", color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(12.dp))
        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = CardBackground)) {
            Column {
                SettingsRow(Icons.Default.Devices, "Connected Devices") { Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextSecondary) }
                HorizontalDivider(color = ProgressTrack)
                SettingsRow(Icons.Default.Download, "Export Data") { Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextSecondary) }
                HorizontalDivider(color = ProgressTrack)
                SettingsRow(Icons.Default.DarkMode, "Dark Mode") {
                    Switch(checked = darkMode, onCheckedChange = { darkMode = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = AccentPurple))
                }
            }
        }
        Spacer(Modifier.height(16.dp))

        // Sign Out
        OutlinedButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth().height(54.dp),
            shape = RoundedCornerShape(27.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, TextSecondary),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = TextPrimary)
        ) {
            Text("SIGN OUT", fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun SettingsRow(icon: ImageVector, label: String, trailing: @Composable () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(12.dp))
        Text(label, color = TextPrimary, fontSize = 15.sp, modifier = Modifier.weight(1f))
        trailing()
    }
}