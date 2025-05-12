package dss.utils;

import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class RoleUtils {
    private static final Map<String, String> ROLE_TRANSLATIONS = Map.of(
            "ROLE_ADMIN", "Адміністратор",
            "ROLE_USER", "Користувач",
            "ANALYST", "Аналітик",
            "ECOLOGIST", "Еколог",
            "ECONOMIST", "Економіст",
            "LAWYER", "Юрист",
            "POWER_ENGINEER", "Енергетик"
    );

    public static String getLocalizedName(String roleName) {
        return ROLE_TRANSLATIONS.getOrDefault(roleName, roleName);
    }
}
