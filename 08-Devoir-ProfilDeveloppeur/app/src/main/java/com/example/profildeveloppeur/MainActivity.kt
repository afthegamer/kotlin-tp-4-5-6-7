package com.example.profildeveloppeur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.EventAvailable
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profildeveloppeur.ui.theme.ProfilDevTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilDevTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DeveloperProfileScreen(
                        profile = sampleProfile(),
                        onContactClick = {},
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

data class DeveloperProfile(
    val name: String,
    val role: String,
    val description: String,
    val email: String,
    val location: String,
    val availability: String,
    val technologies: List<String>,
    val skills: List<Skill>,
    val projects: List<Project>,
    val experiences: List<Experience>
)

data class Skill(
    val name: String
)

data class Project(
    val name: String,
    val description: String,
    val tags: List<String>
)

data class Experience(
    val role: String,
    val company: String,
    val period: String,
    val description: String
)

fun sampleProfile(): DeveloperProfile {
    return DeveloperProfile(
        name = "Lina Bernard",
        role = "Développeuse Android Junior",
        description = "Passionnée par le développement mobile et l'expérience " +
            "utilisateur, je conçois des applications Android modernes avec " +
            "Kotlin et Jetpack Compose. Je recherche un stage pour consolider " +
            "mes compétences sur des projets concrets.",
        email = "lina.bernard@example.com",
        location = "Lyon, France",
        availability = "Disponible pour un stage (dès septembre)",
        technologies = listOf(
            "Kotlin", "Jetpack Compose", "Material 3", "Coroutines",
            "Android SDK", "Git", "Figma", "REST"
        ),
        skills = listOf(
            Skill("Kotlin"),
            Skill("Jetpack Compose"),
            Skill("Android SDK"),
            Skill("Git & GitHub"),
            Skill("UI / UX Design")
        ),
        projects = listOf(
            Project(
                name = "Product Explorer",
                description = "Application catalogue de produits construite " +
                    "entièrement en Jetpack Compose (listes, cartes, détail).",
                tags = listOf("Compose", "Material 3", "LazyColumn")
            ),
            Project(
                name = "MétéoNow",
                description = "Petite application météo affichant des données " +
                    "locales avec une interface claire et responsive.",
                tags = listOf("Kotlin", "UI", "Coroutines")
            ),
            Project(
                name = "TaskFlow",
                description = "Gestionnaire de tâches minimaliste centré sur " +
                    "la simplicité et la lisibilité.",
                tags = listOf("Compose", "Design")
            )
        ),
        experiences = listOf(
            Experience(
                role = "Développeuse Android (alternance découverte)",
                company = "Intouch",
                period = "2025 · 3 mois",
                description = "Contribution à des écrans Compose et à " +
                    "l'amélioration de composants réutilisables."
            ),
            Experience(
                role = "Projet de fin d'études",
                company = "IUT Informatique",
                period = "2024",
                description = "Conception et réalisation d'une application " +
                    "mobile en équipe, du maquettage à la livraison."
            ),
            Experience(
                role = "Formation Kotlin & Jetpack Compose",
                company = "Parcours en autonomie",
                period = "2024",
                description = "Montée en compétence sur l'écosystème Android " +
                    "moderne et les bonnes pratiques Compose."
            )
        )
    )
}

@Composable
fun DeveloperProfileScreen(
    profile: DeveloperProfile,
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            ProfileHeader(
                name = profile.name,
                role = profile.role,
                location = profile.location
            )
        }
        item {
            AboutSection(description = profile.description)
        }
        item {
            ContactCard(
                email = profile.email,
                location = profile.location,
                availability = profile.availability,
                onContactClick = onContactClick
            )
        }
        item {
            TechnologiesSection(technologies = profile.technologies)
        }
        item {
            SectionTitle(title = "Compétences")
        }
        items(
            items = profile.skills,
            key = { skill -> skill.name }
        ) { skill ->
            SkillCard(skill = skill)
        }
        item {
            SectionTitle(title = "Projets")
        }
        items(
            items = profile.projects,
            key = { project -> project.name }
        ) { project ->
            ProjectCard(project = project)
        }
        item {
            SectionTitle(title = "Expériences")
        }
        items(
            items = profile.experiences,
            key = { experience -> experience.role }
        ) { experience ->
            ExperienceItem(experience = experience)
        }
    }
}

@Composable
fun ProfileHeader(
    name: String,
    role: String,
    location: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileAvatar()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = role,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = location,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}

@Composable
fun ProfileAvatar(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.size(96.dp)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_avatar_placeholder),
                contentDescription = "Avatar de profil",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                modifier = Modifier.size(56.dp)
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(24.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(14.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF2E7D32))
            )
        }
    }
}

@Composable
fun AboutSection(
    description: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        SectionTitle(title = "À propos")
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun ContactCard(
    email: String,
    location: String,
    availability: String,
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Contact",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(12.dp))
            ReadOnlyInfoField(
                label = "E-mail",
                value = email,
                icon = Icons.Filled.Email
            )
            Spacer(modifier = Modifier.height(12.dp))
            ReadOnlyInfoField(
                label = "Localisation",
                value = location,
                icon = Icons.Filled.LocationOn
            )
            Spacer(modifier = Modifier.height(12.dp))
            ReadOnlyInfoField(
                label = "Disponibilité",
                value = availability,
                icon = Icons.Filled.EventAvailable
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onContactClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Contacter")
            }
        }
    }
}

@Composable
fun ReadOnlyInfoField(
    label: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = {},
        readOnly = true,
        label = { Text(text = label) },
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null)
        },
        singleLine = true,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun TechnologiesSection(
    technologies: List<String>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        SectionTitle(title = "Technologies")
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = technologies,
                key = { tech -> tech }
            ) { tech ->
                TechnologyChip(label = tech)
            }
        }
    }
}

@Composable
fun TechnologyChip(
    label: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLarge,
        color = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun SkillCard(
    skill: Skill,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Code,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = skill.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun ProjectCard(
    project: Project,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = project.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = project.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = project.tags,
                    key = { tag -> tag }
                ) { tag ->
                    TechnologyChip(label = tag)
                }
            }
        }
    }
}

@Composable
fun ExperienceItem(
    experience: Experience,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Icon(
            imageVector = Icons.Filled.Work,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(top = 2.dp)
                .size(22.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "${experience.role} · ${experience.company}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = experience.period,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = experience.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun SectionTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier.fillMaxWidth()
    )
}

@Preview(
    showBackground = true,
    name = "Profil - Thème clair",
    heightDp = 1400
)
@Composable
fun DeveloperProfileScreenLightPreview() {
    ProfilDevTheme(darkTheme = false) {
        DeveloperProfileScreen(
            profile = sampleProfile(),
            onContactClick = {}
        )
    }
}

@Preview(
    showBackground = true,
    name = "Profil - Thème sombre",
    heightDp = 1400
)
@Composable
fun DeveloperProfileScreenDarkPreview() {
    ProfilDevTheme(darkTheme = true) {
        DeveloperProfileScreen(
            profile = sampleProfile(),
            onContactClick = {}
        )
    }
}
