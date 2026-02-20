package com.beanpilot.core.domain.model.bean;

import java.util.*;

public final class CoffeeBean {

    private final UUID id;
    private String name;
    private String roastery;
    private BrewSetting brewSetting;
    private FlavorProfile flavorProfile;
    private final List<CoffeePackage> packages = new ArrayList<>();

    private CoffeeBean(UUID id,
                       String name,
                       String roastery,
                       BrewSetting brewSetting,
                       FlavorProfile flavorProfile) {
        this.id = Objects.requireNonNull(id, "id cannot be null");
        setName(name);
        setRoastery(roastery);
        this.brewSetting = Objects.requireNonNull(brewSetting, "brew-setting cannot be null");
        this.flavorProfile = Objects.requireNonNull(flavorProfile, "flavor-profile cannot be null");
    }

    public static CoffeeBean create(String name,
                                    String roastery,
                                    BrewSetting brewSetting,
                                    FlavorProfile flavorProfile) {
        return new CoffeeBean(UUID.randomUUID(), name, roastery, brewSetting, flavorProfile);
    }

    private void setName(String name) {
        String n = Objects.requireNonNull(name, "name cannot be null").trim();
        if (n.isEmpty()) throw new IllegalArgumentException("name must not be blank");
        this.name = n;
    }

    private void setRoastery(String roastery) {
        String r = Objects.requireNonNull(roastery, "roastery cannot be null").trim();
        if (r.isEmpty()) throw new IllegalArgumentException("roastery must not be blank");
        this.roastery = r;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getRoastery() { return roastery; }
    public BrewSetting getBrewSetting() { return brewSetting; }
    public FlavorProfile getFlavorProfile() { return flavorProfile; }
    public List<CoffeePackage> getPackages() { return Collections.unmodifiableList(packages); }


    public void updateBrewSetting(BrewSetting newSettings) {
        this.brewSetting = Objects.requireNonNull(newSettings, "new BrewSetting cannot be null");
    }

    public void updateFlavorProfile(FlavorProfile newProfile) {
        this.flavorProfile = Objects.requireNonNull(newProfile, "new FlavorProfile cannot be null");
    }

    // Adds a newly purchased package to this bean
    public CoffeePackage addPackage(Price price, int grams, java.util.Date purchaseDate) {
        CoffeePackage p = CoffeePackage.create(price, grams, purchaseDate);
        this.packages.add(p);
        return p;
    }

    // Consumes coffee from a specific package by id, enforcing invariants.
    public void consumeFromPackage(String packageId, int grams) {
        CoffeePackage p = packages.stream()
                .filter(pkg -> pkg.getId().equals(packageId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("consumption not possible: package not found: " + packageId));
        p.consume(grams);
    }

    public int totalRemainingGrams() {
        return packages.stream().mapToInt(CoffeePackage::getRemainingGrams).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CoffeeBean)) return false;
        CoffeeBean that = (CoffeeBean) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CoffeeBean[" + id + ", " + name + ", " + roastery + "]";
    }
}