package model;

import java.util.Objects;

public class Recipe {

    private String date;
    private String recipe;
    private String medicalOfficeStamp;

    public Recipe() {
    }

    public Recipe(String date, String recipe, String medicalOfficeStamp) {
        this.date = date;
        this.recipe = recipe;
        this.medicalOfficeStamp = medicalOfficeStamp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getMedicalOfficeStamp() {
        return medicalOfficeStamp;
    }

    public void setMedicalOfficeStamp(String medicalOfficeStamp) {
        this.medicalOfficeStamp = medicalOfficeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe1 = (Recipe) o;
        return Objects.equals(date, recipe1.date) &&
                Objects.equals(recipe, recipe1.recipe) &&
                Objects.equals(medicalOfficeStamp, recipe1.medicalOfficeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, recipe, medicalOfficeStamp);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "date='" + date + '\'' +
                ", recipe='" + recipe + '\'' +
                ", medicalOfficeStamp='" + medicalOfficeStamp + '\'' +
                '}';
    }
}
