package com.arsenal.sync.features.home.presentation.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arsenal.sync.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GunVerificationCard() {
    var gunType by remember { mutableStateOf("") }
    var gunModel by remember { mutableStateOf("") }
    var gunNumber by remember { mutableStateOf("") }
    var isSubmitting by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    var gunModelExpanded by remember { mutableStateOf(false) }

    val gunTypes = listOf("GLOCK", "BERETTA")

    val glockModels = listOf(
        // Full-Size
        "G17", "G20", "G21", "G22", "G37",
        // Compact
        "G19", "G23", "G38", "G45",
        // Subcompact
        "G26", "G27", "G29", "G30", "G33",
        // Slimline / Single Stack
        "G42", "G43", "G43X", "G48",
        // Crossover & Special Editions
        "G17L", "G34", "G35", "G44", "G47",
        // Generations (not models but for reference)
        "Gen1", "Gen2", "Gen3", "Gen4", "Gen5"
    )

    val berettaModels = listOf(
        // 92 Series
        "92FS / M9", "92G", "92A1", "M9A1", "M9A3", "92X", "92X Performance",
        // PX4 Storm
        "PX4 Full Size", "PX4 Compact", "PX4 Subcompact",
        // APX Series
        "APX Full Size", "APX Centurion", "APX Carry", "APX A1 Carry",
        // Compact / Pocket
        "Beretta 84 Cheetah", "Beretta 85FS", "Beretta Pico", "Beretta Tomcat 3032",
        // Tactical / Competition
        "92X Performance Defensive", "92 Brigadier Tactical", "APX Combat", "APX RDO",
        // Historical / Classic
        "Beretta 1951 Brigadier", "Beretta 1934", "Beretta 1935", "Beretta 70", "Beretta 71"
    )


    val modelList =
        if (gunType == gunTypes[0]) glockModels else if (gunType == gunTypes[1]) berettaModels else emptyList()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Shield Icon
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(32.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(R.string.gun_verification),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.please_verify_your_firearm_to_access_safety_features),
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.tertiary,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Firearm Type Dropdown
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(R.string.firearm_type),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = gunType,
                            onValueChange = {},
                            readOnly = true,
                            placeholder = { Text(stringResource(R.string.select_firearm_type)) },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(MenuAnchorType.PrimaryNotEditable),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline
                            )
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            gunTypes.forEach { type ->
                                DropdownMenuItem(
                                    text = { Text(type) },
                                    onClick = {
                                        gunType = type
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    // Show Gun types
                    AnimatedVisibility(
                        visible = gunType.isNotEmpty(),
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = stringResource(R.string.firearm_model),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            ExposedDropdownMenuBox(
                                expanded = gunModelExpanded,
                                onExpandedChange = { gunModelExpanded = !gunModelExpanded }
                            ) {
                                OutlinedTextField(
                                    value = gunModel,
                                    onValueChange = {},
                                    readOnly = true,
                                    placeholder = { Text(stringResource(R.string.select_firearm_model)) },
                                    trailingIcon = {
                                        ExposedDropdownMenuDefaults.TrailingIcon(
                                            expanded = gunModelExpanded
                                        )
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .menuAnchor(MenuAnchorType.PrimaryNotEditable),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                    )
                                )

                                ExposedDropdownMenu(
                                    expanded = gunModelExpanded,
                                    onDismissRequest = { gunModelExpanded = false }
                                ) {
                                    modelList.forEach { type ->
                                        DropdownMenuItem(
                                            text = { Text(type) },
                                            onClick = {
                                                gunModel = type
                                                gunModelExpanded = false
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Unique Gun Number
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(R.string.unique_gun_number),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = gunNumber,
                        onValueChange = { gunNumber = it },
                        placeholder = { Text(stringResource(R.string.enter_serial_number)) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        )
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Submit Button
                Button(
                    onClick = { isSubmitting = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    shape = RoundedCornerShape(8.dp),
                    enabled = !isSubmitting
                ) {
                    Text(
                        text = if (isSubmitting) stringResource(R.string.verifying) else stringResource(
                            R.string.submit_for_verification
                        ),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
