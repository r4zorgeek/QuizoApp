package com.r4zor.quizo;

import android.util.Log;

/**
 * Created by mane on 30/10/18.
 */

public class AppSettings {
    // Default Questions Category
    private String QUESTIONS_CATEGORY = "0";
    // Default Questions Difficulty
    private String QUESTIONS_DIFFICULTY = "0";
    // Default Questions Type
    private String QUESTIONS_TYPE = "0";
    // Default Questions Category
    private String QUESTIONS_CATEGORY_ID = "0";
    // Questions Category List
    private String[] QuestionsCategoryArray = Categories.categories;
    

    // Questions Difficulty List
    private String[] QuestionsDifficultyArray = {
            "Any Difficulty",
            "Easy",
            "Medium",
            "Hard"
    };

    // Questions Type List
    private String[] QuesitonsTypeArray = {
            "Any Type",
            "Multiple Choice",
            "True / False"
    };

    public String[] getQuestionsCategoryArray() {
        return this.QuestionsCategoryArray;
    }

    public String[] getQuestionsDifficultyArray() {
        return this.QuestionsDifficultyArray;
    }

    public String[] getQuesitonsTypeArray() {
        return this.QuesitonsTypeArray;
    }

    public String getQuestionsCategory() {
        return this.QUESTIONS_CATEGORY;
    }

    public String getQuestionsDifficulty() {
        return this.QUESTIONS_DIFFICULTY.toLowerCase();
    }

    public String getQuestionsType() {
        return this.QUESTIONS_TYPE;
    }

    public String getQuestionsCategoryId() {
        return this.QUESTIONS_CATEGORY_ID;
    }

    public void setQuestionsCategory(String s) {
        this.QUESTIONS_CATEGORY = s;
        setQuestionsCategoryId(Categories.categoriesMapping.get(s));
    }

    public void setQuestionsDifficulty(String s) {
        if (s.equals("Any Difficulty")) return;
        this.QUESTIONS_DIFFICULTY = s;
    }

    public void setQuestionsType(String s) {
        if (s.equals("True / False")) {
            this.QUESTIONS_TYPE = "boolean";
        }
        else if (s.equals("Multiple Choice")) {
            this.QUESTIONS_TYPE = "multiple";
        }
    }

    public void setQuestionsCategoryId(String s) {
        this.QUESTIONS_CATEGORY_ID = s;
    }
}
