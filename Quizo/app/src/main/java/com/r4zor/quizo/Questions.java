package com.r4zor.quizo;

import org.json.JSONArray;

/**
 * Created by mane on 01/11/18.
 */

public class Questions {
    static JSONArray questionArray;

    Questions() {
        new FetchQuestions().execute();
    }
}
