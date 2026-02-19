package com.beanpilot.core.domain.model.bean;

public enum Flavor {
    // reference from: https://coffee-mind.com/coffeemind-aroma-wheel/
    BERRY("Beerig"),
    FRUITY("Fruchtig"),
    DRIED_FRUIT("Getrocknete Früchte"),
    SWEET("Süß"),
    CHOCOLATE("Schokolade"),
    NUTTY("Nussig/Kakao"),
    ROASTED("Geröstet"),
    INDUSTRIAL("Industriell"),
    SPICY("Würzig"),
    CEREAL("Getreide"),
    GREEN("Grün"),
    HERB_VEGETAL("Kräuter/Pflanzlich"),
    FLORAL("Blumig");
    
    private final String displayName;

    Flavor(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}