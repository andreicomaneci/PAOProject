package model;

import java.util.Objects;

public class Papers {

    private Recipe recipe;
    private MedicalLeave medicalLeave;

    public Papers() {
    }

    public Papers(Recipe recipe, MedicalLeave medicalLeave) {
        this.recipe = recipe;
        this.medicalLeave = medicalLeave;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Papers papers = (Papers) o;
        return Objects.equals(recipe, papers.recipe) &&
                Objects.equals(medicalLeave, papers.medicalLeave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipe, medicalLeave);
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public MedicalLeave getMedicalLeave() {
        return medicalLeave;
    }

    public void setMedicalLeave(MedicalLeave medicalLeave) {
        this.medicalLeave = medicalLeave;
    }

    @Override
    public String toString() {
        return "Papers{" +
                "recipe=" + recipe +
                ", medicalLeave=" + medicalLeave +
                '}';
    }
}
