# video de lab 1


https://github.com/user-attachments/assets/33b33d36-5d3e-4d5a-a5bf-1fe5ff2433af


## Aperçu

**HelloToast** est une application Android de démonstration qui introduit les bases du développement Android natif en Java. Elle met en œuvre deux interactions simples avec l'utilisateur via des boutons.

---

## Fonctionnalités

| Fonctionnalité | Description |
|---|---|
| **Afficher un Toast** | Affiche une notification temporaire "Hi! Toast :)" |
| **Incrémenter un compteur** | Incrémente et affiche un compteur à l'écran |

---

## Structure du projet

```
HelloToast/
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/example/hellotoast/
│           │       └── MainActivity.java      ← Logique de l'application
│           └── res/
│               └── layout/
│                   └── activity_main.xml      ← Interface utilisateur
└── README.md
```

---

## Explication des fichiers

### `MainActivity.java`

Fichier principal contenant toute la **logique** de l'application.

#### Variable d'instance

```java
int compteur = 0;
```
Stocke la valeur actuelle du compteur, accessible depuis toute la classe.

---

#### `onCreate()` — Point d'entrée

```java
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ...
}
```

Méthode appelée automatiquement au **lancement de l'application**. Elle :
1. Appelle le constructeur parent (`super.onCreate`)
2. Charge l'interface XML avec `setContentView()`

---

#### Liaison Java ↔ XML

```java
TextView tvCompteur   = findViewById(R.id.tvCompteur);
Button btnToast       = findViewById(R.id.btnToast);
Button btnIncrementer = findViewById(R.id.btnIncrementer);
```

`findViewById()` recherche les composants visuels dans le XML **par leur identifiant** et les retourne comme objets Java manipulables.

---

#### Bouton Toast

```java
btnToast.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this, "Hi! Toast :)", Toast.LENGTH_SHORT).show();
    }
});
```

| Paramètre | Valeur | Rôle |
|---|---|---|
| Contexte | `MainActivity.this` | Référence à l'activité courante |
| Message | `"Hi! Toast :)"` | Texte affiché |
| Durée | `Toast.LENGTH_SHORT` | ~2 secondes |

---

#### Bouton Incrémenter

```java
btnIncrementer.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        compteur++;
        tvCompteur.setText("Compteur : " + compteur);
    }
});
```

À chaque clic :
- `compteur++` → incrémente la variable
- `setText()` → met à jour le texte affiché dans le `TextView`

---

### `activity_main.xml`

Fichier définissant l'**interface graphique** de l'application.

#### Conteneur principal — `LinearLayout`

```xml
<LinearLayout
    android:orientation="vertical"
    android:gravity="center"
    android:padding="16dp">
```

| Attribut | Valeur | Effet |
|---|---|---|
| `orientation` | `vertical` | Éléments empilés de haut en bas |
| `gravity` | `center` | Tout est centré dans l'écran |
| `padding` | `16dp` | Marge intérieure de 16dp |

---

#### TextView — Affichage du compteur

```xml
<TextView
    android:id="@+id/tvCompteur"
    android:text="Compteur : 0"
    android:textSize="32sp"
    android:textStyle="bold"
    android:layout_marginBottom="40dp"/>
```

| Attribut | Rôle |
|---|---|
| `id` | Identifiant pour `findViewById()` |
| `text` | Valeur affichée par défaut |
| `textSize="32sp"` | Taille adaptée aux préférences d'accessibilité |
| `textStyle="bold"` | Texte en gras |
| `layout_marginBottom` | Espacement sous le composant |

> ℹ️ **sp vs dp** : `sp` (scale-independent pixels) est recommandé pour les textes car il respecte les réglages de taille de police de l'utilisateur.

---

#### Boutons

```xml
<Button
    android:id="@+id/btnToast"
    android:text="Afficher un Toast"
    android:layout_marginBottom="16dp"/>

<Button
    android:id="@+id/btnIncrementer"
    android:text="Incrémenter"/>
```

Chaque bouton possède :
- Un **`id`** unique → permet au Java de le retrouver
- Un **`text`** → libellé affiché sur le bouton

---

## Concepts clés

### Séparation des responsabilités

```
activity_main.xml   →   Apparence  (QUOI afficher)
MainActivity.java   →   Comportement (QUOI faire)
```

### Cycle de vie Android simplifié

```
App lancée
    │
    ▼
onCreate()  ←── Initialisation des vues et des listeners
    │
    ▼
Écran affiché
    │
    ├── [Clic btnToast]      → Affiche le Toast
    └── [Clic btnIncrementer] → compteur++ → setText()
```

### `setOnClickListener` — Pattern Observateur

Le pattern **écouteur d'événement** est fondamental en Android :
1. On définit **quoi faire** (`onClick`)
2. On **attache** ce comportement au bouton (`setOnClickListener`)
3. Android **déclenche** automatiquement `onClick` à chaque clic
