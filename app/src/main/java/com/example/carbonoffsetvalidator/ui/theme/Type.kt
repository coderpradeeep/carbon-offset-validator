package com.example.carbonoffsetvalidator.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val conclusion = "Pre-project vegetation-based offset capacity: ~945 tons CO₂e\n" +
        "Post-construction projected offset (10-year potential): ~500 tons CO₂e\n" +
        "Net carbon offset reduction: 445 tons CO₂e (47.1% drop)\n" +
        "Reported emissions: 1,297 tons CO₂e\n" +
        "Analysis accuracy: 96.3% match\n" +
        "Conclusion: Significant offset loss; bridging via 300-tree expansion, rooftop solar, and external carbon credits."

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)