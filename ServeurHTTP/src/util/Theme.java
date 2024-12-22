package util;

import java.awt.Color;

public class Theme {
    public static Color BACKGROUND;
    public static Color SECONDARY;
    public static Color ACCENT_COLOR;
    public static Color TEXT_COLOR;

    // Couleurs thème sombre
    public static void DarkTheme() {
        Theme.BACKGROUND = new Color(45, 45, 45);
        Theme.SECONDARY = new Color(60, 60, 60);
        Theme.ACCENT_COLOR = new Color(0, 120, 215);
        Theme.TEXT_COLOR = Color.WHITE;
    }

    // Couleurs thème clair
    public static void LightTheme() {
        Theme.BACKGROUND = new Color(240, 240, 240);
        Theme.SECONDARY = new Color(220, 220, 220);
        Theme.ACCENT_COLOR = new Color(0, 120, 215);
        Theme.TEXT_COLOR = Color.BLACK;
    }

    // Couleurs thème pastel
    public static void PastelTheme() {
        Theme.BACKGROUND = new Color(250, 240, 230); // Beige léger
        Theme.SECONDARY = new Color(235, 210, 220); // Rose pâle
        Theme.ACCENT_COLOR = new Color(150, 200, 240); // Bleu ciel doux
        Theme.TEXT_COLOR = new Color(60, 60, 60); // Gris foncé
    }

    // Couleurs thème nature
    public static void NatureTheme() {
        Theme.BACKGROUND = new Color(34, 49, 34); // Vert foncé (feuille)
        Theme.SECONDARY = new Color(78, 121, 78); // Vert forêt
        Theme.ACCENT_COLOR = new Color(184, 134, 11); // Or (rayons de soleil)
        Theme.TEXT_COLOR = Color.WHITE;
    }

    // Couleurs thème océan
    public static void OceanTheme() {
        Theme.BACKGROUND = new Color(10, 25, 50); // Bleu foncé (profondeur océanique)
        Theme.SECONDARY = new Color(50, 100, 130); // Bleu océan
        Theme.ACCENT_COLOR = new Color(0, 200, 255); // Cyan vif
        Theme.TEXT_COLOR = Color.WHITE;
    }

    // Couleurs thème Sunset
    public static void SunsetTheme() {
        Theme.BACKGROUND = new Color(252, 157, 154); // Rose orangé
        Theme.SECONDARY = new Color(249, 205, 173); // Orange doux
        Theme.ACCENT_COLOR = new Color(255, 94, 77); // Rouge orangé éclatant
        Theme.TEXT_COLOR = new Color(60, 60, 60); // Gris foncé
    }

    // Couleurs thème Galaxy
    public static void GalaxyTheme() {
        Theme.BACKGROUND = new Color(25, 25, 112); // Bleu nuit
        Theme.SECONDARY = new Color(75, 0, 130); // Indigo
        Theme.ACCENT_COLOR = new Color(255, 223, 0); // Jaune brillant (étoiles)
        Theme.TEXT_COLOR = Color.WHITE;
    }

    // Couleurs thème Cyberpunk
    public static void CyberpunkTheme() {
        Theme.BACKGROUND = new Color(20, 20, 20); // Noir profond (ambiance néon)
        Theme.SECONDARY = new Color(55, 55, 100); // Bleu métallique doux
        Theme.ACCENT_COLOR = new Color(255, 0, 255); // Rose néon
        Theme.TEXT_COLOR = new Color(0, 255, 255); // Cyan néon
    }

    // Couleurs thème Automne
    public static void AutumnTheme() {
        Theme.BACKGROUND = new Color(101, 67, 33); // Marron foncé (tronc d'arbre)
        Theme.SECONDARY = new Color(205, 133, 63); // Beige automnal
        Theme.ACCENT_COLOR = new Color(255, 69, 0); // Rouge feuillage
        Theme.TEXT_COLOR = Color.WHITE;
    }

    // Couleurs thème Hiver
    public static void WinterTheme() {
        Theme.BACKGROUND = new Color(230, 230, 250); // Lavande douce (neige)
        Theme.SECONDARY = new Color(176, 224, 230); // Bleu glacier
        Theme.ACCENT_COLOR = new Color(0, 191, 255); // Bleu ciel
        Theme.TEXT_COLOR = new Color(50, 50, 50); // Gris foncé
    }

    // Couleurs thème Printemps
    public static void SpringTheme() {
        Theme.BACKGROUND = new Color(240, 255, 240); // Vert clair (herbe fraîche)
        Theme.SECONDARY = new Color(255, 182, 193); // Rose tendre (fleur)
        Theme.ACCENT_COLOR = new Color(50, 205, 50); // Vert vif (jeunes pousses)
        Theme.TEXT_COLOR = Color.BLACK;
    }

    // Couleurs thème Or
    public static void GoldTheme() {
        Theme.BACKGROUND = new Color(40, 40, 40); // Noir profond
        Theme.SECONDARY = new Color(184, 134, 11); // Or antique
        Theme.ACCENT_COLOR = new Color(255, 215, 0); // Jaune doré brillant
        Theme.TEXT_COLOR = Color.WHITE;
    }

    public static Color getBACKGROUND() {
        return BACKGROUND;
    }

    public static Color getSECONDARY() {
        return SECONDARY;
    }

    public static Color getACCENT_COLOR() {
        return ACCENT_COLOR;
    }

    public static Color getTEXT_COLOR() {
        return TEXT_COLOR;
    }
}
